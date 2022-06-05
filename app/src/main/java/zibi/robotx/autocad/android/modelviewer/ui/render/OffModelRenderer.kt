package zibi.robotx.autocad.android.modelviewer.ui.render


import android.content.Context
import zibi.robotx.autocad.data.off.OffObject
import zibi.robotx.autocad.data.off.OffFace
import android.opengl.GLES20.*
import android.opengl.Matrix
import android.util.SparseIntArray
import zibi.robotx.arplan.R
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRenderer.Companion.idProgram
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRenderer.Companion.loadShader
import zibi.robotx.autocad.common.math.*
import zibi.robotx.autocad.common.io.BufferUtil
import java.nio.FloatBuffer
import java.nio.IntBuffer
import kotlin.math.max

class OffModelRenderer( offObject: OffObject) : ModelRenderer {

    private val colorBuffer: FloatBuffer
    private val indexBuffer: IntBuffer
    private val normalBuffer: FloatBuffer
    private val vertexBuffer: FloatBuffer

    private var mMVPMatrix: FloatArray = FloatArray(16)
    private var mMVMatrix: FloatArray = FloatArray(16)
    private val vertexStride = 3 * 4 // 4 bytes( float) per vertex (3 - xyz)

    override fun onInitialize(context: Context) {
        for( ind in idProgram.indices) {
            if (glIsProgram(idProgram[ind])) {
                glDeleteProgram(idProgram[ind])
                idProgram[ind] = -1
            }
        }

        val vertexShaderSurface = loadShader(R.raw.off_vertex, GL_VERTEX_SHADER, context)
        val fragmentShaderSurface = loadShader(R.raw.off_fragment, GL_FRAGMENT_SHADER, context)

        idProgram[0] = DisplayRenderer.createAndLinkProgram(vertexShaderSurface, fragmentShaderSurface,
            arrayOf("a_Position", "a_Color", "a_Normal", "a_IsTwoSide", "u_MVPMatrix", "u_MVMatrix")
        )

    }

    override fun onRender( isTwoSide:Boolean, f: Float,
                           viewMatrix: FloatArray, projMatrix: FloatArray, modelMatrix: FloatArray) {
        Matrix.setIdentityM(mMVMatrix, 0)
        Matrix.multiplyMM(mMVMatrix, 0, viewMatrix, 0, modelMatrix, 0)
        Matrix.setIdentityM(mMVPMatrix, 0)
        Matrix.multiplyMM(mMVPMatrix, 0, projMatrix, 0, mMVMatrix, 0)

//        glUseProgram(idProgram[0])
        if( glIsProgram(idProgram[0])  )  glUseProgram(idProgram[0]) else return
//        checkGlError("glUseProgram")

        val mIsTwosideHndl = glGetUniformLocation(idProgram[0], "a_IsTwoSide")
        val mPositionHandle = glGetAttribLocation(idProgram[0], "a_Position")
        val mMVMtrxHndl = glGetUniformLocation(idProgram[0], "u_MVMatrix")
        val mMVPMtrxHndl = glGetUniformLocation(idProgram[0], "u_MVPMatrix")
        val mNormalHndl = glGetAttribLocation(idProgram[0], "a_Normal")
        val mColorHandle = glGetAttribLocation(idProgram[0], "a_Color")

        glUniform1i(mIsTwosideHndl, if(isTwoSide) 1 else 0)
//        checkGlError("glUniform1i")

        // get handle to vertex shader's vPosition member
        glVertexAttribPointer(mPositionHandle, 3, GL_FLOAT, false, 12, vertexBuffer)
        glEnableVertexAttribArray(mPositionHandle)
//        checkGlError("glVertexAttribPointer")

        // modelview
        glUniformMatrix4fv(mMVMtrxHndl, 1, false, mMVMatrix, 0)
//        checkGlError("glUniformMatrix4fv")

        // final matrix
        glUniformMatrix4fv(mMVPMtrxHndl, 1, false, mMVPMatrix, 0)
//        checkGlError("glUniformMatrix4fv")

        glEnableVertexAttribArray(mNormalHndl)
        glVertexAttribPointer(mNormalHndl, 3, GL_FLOAT, false, 12, normalBuffer)
//        checkGlError("glVertexAttribPointer")

        glEnableVertexAttribArray(mColorHandle)
        glVertexAttribPointer(mColorHandle, 4, GL_FLOAT, false, 16, colorBuffer)
//        checkGlError("glVertexAttribPointer")

        glDrawElements(GL_TRIANGLES, indexBuffer.capacity(), GL_UNSIGNED_INT, indexBuffer)

        glDisableVertexAttribArray(mColorHandle)
        glDisableVertexAttribArray(mPositionHandle)
        glDisableVertexAttribArray(mNormalHndl)
        glDisableVertexAttribArray(mMVMtrxHndl)
        glDisableVertexAttribArray(mMVPMtrxHndl)

    }

    override fun onChangeSelect(touchX: Float, touchY: Float, width: Int, height: Int) {}

    private fun populateBuffers(offObject: OffObject?, face: OffFace) {
        if (face.vertexReferences.size >= 3) {
            val indexRefMap = SparseIntArray()
            val normal = evaluateNormal(offObject, face)
            for (vertexRef in face.vertexReferences) {
                indexRefMap.put(vertexRef!!, vertexBuffer.position() / 3)
                val vertex = offObject!!.getVertex(vertexRef)
                vertexBuffer.put(vertex!!.x)
                vertexBuffer.put(vertex.y)
                vertexBuffer.put(vertex.z)
                if (offObject.hasVertexColors) {
                    colorBuffer.put(vertex.r)
                    colorBuffer.put(vertex.g)
                    colorBuffer.put(vertex.b)
                    colorBuffer.put(vertex.a)
                } else {
                    colorBuffer.put(face.r)
                    colorBuffer.put(face.g)
                    colorBuffer.put(face.b)
                    colorBuffer.put(face.a)
                }
                normalBuffer.put(normal.x)
                normalBuffer.put(normal.y)
                normalBuffer.put(normal.z)
            }
            for (i in 1 until face.vertexReferences.size - 1) {
                indexBuffer.put(indexRefMap[face.vertexReferences[0]!!])
                indexBuffer.put(indexRefMap[face.vertexReferences[i]!!])
                indexBuffer.put(indexRefMap[face.vertexReferences[i + 1]!!])
            }
        }

    }

    private fun evaluateNormal(offObject: OffObject?, face: OffFace): Float3 {
        var result = Float3()
        for (i in 1 until face.vertexReferences.size - 1) {
            val vertexA = offObject!!.getVertex(face.vertexReferences[0]!!)
            val vertexB = offObject.getVertex(face.vertexReferences[i]!!)
            val vertexC = offObject.getVertex(face.vertexReferences[i + 1]!!)
            val vectorA = Float3()
            vectorA.x = vertexB!!.x - vertexA!!.x
            vectorA.y = vertexB.y - vertexA.y
            vectorA.z = vertexB.z - vertexA.z
            val vectorB = Float3()
            vectorB.x = vertexC!!.x - vertexA.x
            vectorB.y = vertexC.y - vertexA.y
            vectorB.z = vertexC.z - vertexA.z
            result = Float3.getCrossProduct(vectorA, vectorB)
            if (result.length > 0.001f) {
                break
            }
        }
        result.length = 1.0f
        return result
    }

    private fun evaluateTriangleCount(offObject: OffObject): Int {
        var result = 0
        for (face in offObject.faces) {
            result += max(0, face!!.vertexReferences.size - 2)
        }
        return result
    }

    private fun evaluateVertexCount(offObject: OffObject): Int {
        var result = 0
        for (face in offObject.faces) {
            result += face!!.vertexReferences.size
        }
        return result
    }

    init {
        val triangleCount = evaluateTriangleCount(offObject)
        val vertexCount = evaluateVertexCount(offObject)
        vertexBuffer = BufferUtil.createFloatBuffer(vertexCount * 3)
        colorBuffer = BufferUtil.createFloatBuffer(vertexCount * 4)
        normalBuffer = BufferUtil.createFloatBuffer(vertexCount * 3)
        indexBuffer = BufferUtil.createIntBuffer(triangleCount * 3)
        for (face in offObject.faces) {
            populateBuffers(offObject, face!!)
        }
        indexBuffer.rewind()
        vertexBuffer.rewind()
        normalBuffer.rewind()
        colorBuffer.rewind()
    }
}