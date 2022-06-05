package zibi.robotx.autocad.android.modelviewer.ui.render

import android.content.Context
import android.opengl.GLES20
import android.opengl.GLES20.*
import android.opengl.Matrix
import zibi.robotx.arplan.R
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRenderer.Companion.createAndLinkProgram
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRenderer.Companion.loadShader
import zibi.robotx.autocad.common.math.Float3
import zibi.robotx.autocad.data.dxf.DxfObject
import javax.vecmath.Vector3f

class Contour(): ModelRenderer {

    private var mIdLine: Int = -1
    private var mIdSurface: Int = -1
    //    private var mPositionHandle = 0
//    private var mColorHandle = 0
//    private var mMVPMatrixHandle = 0
    private val vertexStride = COORDS_PER_VERTEX * 4 // 4 bytes per vertex
    private var dxfObject: DxfObject? = null

    override fun onInitialize(context: Context) {
//        glClearColor(0.0f, 0.0f, 0.0f, 1.0f)
        // prepare shaders and OpenGL program
        val vertexShader = loadShader(R.raw.dxf_line_vertex, GL_VERTEX_SHADER, context)
        val fragmentShader = loadShader(R.raw.dxf_line_fragment, GL_FRAGMENT_SHADER, context)
        mIdLine = createAndLinkProgram(vertexShader, fragmentShader,
            arrayOf("a_Position", "a_Color", "a_Normal", "a_TexCoordinate"))
        // prepare shaders and OpenGL program
        val vertexShaderSurface = loadShader(R.raw.dxf_surface_vertex, GL_VERTEX_SHADER, context)
        val fragmentShaderSurface = loadShader(R.raw.dxf_surface_fragment, GL_FRAGMENT_SHADER, context)

        mIdSurface = createAndLinkProgram(vertexShaderSurface, fragmentShaderSurface,
            arrayOf("a_Position", "a_Color", "a_Normal", "a_TexCoordinate"))

    }

    // number of coordinates of normal vector
    private val mNrmlDataSize : Int = 3
    private var mMVPMatrix: FloatArray = FloatArray(16)
    private var mMVMatrix: FloatArray = FloatArray(16)


    override fun onRender( isTwoSide:Boolean, f: Float, viewMatrix: FloatArray, projMatrix: FloatArray, modelMatrix: FloatArray) {
        // Add program to OpenGL environment
        glUseProgram(mIdLine)
//z
//        val mMVPMtrxHndl = GLES20.glGetUniformLocation(mIdLine, "u_MVPMatrix")
//        val mMVMtrxHndl = GLES20.glGetUniformLocation(mIdLine, "u_MVMatrix")
//        val mLightPsHndl = GLES20.glGetUniformLocation(mIdLine, "u_LightPos")
//        mTextureUniformHndl = GLES20.glGetUniformLocation(mHexahedronPstnHndl, "u_Texture")
//        val mHexahedronPstnHndl = GLES20.glGetAttribLocation(mIdLine, "a_Position")
//        val mHexahedronClrHndl = GLES20.glGetAttribLocation(mIdLine, "a_Color")
//        val mHexahedronNrmlHndl = GLES20.glGetAttribLocation(mIdLine, "a_Normal")
//        val mTextureCoordHndl = GLES20.glGetAttribLocation(mIdLine, "a_TexCoordinate")


//        val mHexahedronNrmlHndl = glGetAttribLocation(mIdLine, "a_Normal")
//        mHexahedronNrmls!!.position(0)
//        glVertexAttribPointer(mHexahedronNrmlHndl, mNrmlDataSize, GL_FLOAT, false,
//            0, mHexahedronNrmls)
//        glEnableVertexAttribArray(mHexahedronNrmlHndl)
//        checkGlError("glEnableVertexAttribArray")

//z
        // get handle to vertex shader's vPosition member
        var mPositionHandle = glGetAttribLocation(mIdLine, "a_Position")
        // Enable a handle to the triangle vertices
        glEnableVertexAttribArray(mPositionHandle)
        // Prepare the triangle coordinate data
//        glVertexAttribPointer(
//                mPositionHandle, COORDS_PER_VERTEX, GL_FLOAT,
//                false, vertexStride, dxfObject?.vertexBufferLine
//            )

        Matrix.setIdentityM(mMVMatrix, 0)
        Matrix.multiplyMM(mMVMatrix, 0, viewMatrix, 0, modelMatrix, 0)
        // modelview
        var mMVMtrxHndl = glGetUniformLocation(mIdLine, "u_MVMatrix")
        GLES20.glUniformMatrix4fv(mMVMtrxHndl, 1, false, mMVMatrix, 0)

        Matrix.setIdentityM(mMVPMatrix, 0)
        Matrix.multiplyMM(mMVPMatrix, 0, projMatrix, 0, mMVMatrix, 0)
        // final matrix
        var mMVPMtrxHndl = glGetUniformLocation(mIdLine, "u_MVPMatrix")
        GLES20.glUniformMatrix4fv(mMVPMtrxHndl, 1, false, mMVPMatrix, 0)
//        checkGlError("glGetUniformLocation")

        // get handle to fragment shader's vColor member
        var mColorHandle = glGetUniformLocation(mIdLine, "a_Color")
        for (oneElement in dxfObject!!.listOnePolyline) {
            // Set color for drawing the triangle
            glUniform4fv(mColorHandle, 1, oneElement.vec4Color, 0)
            // Draw the square
//            glDrawElements(
//                GL_LINES, oneElement.elementIndex.size,
//                GL_UNSIGNED_SHORT, oneElement.drawIndexBufferLine
//            )
        }
        // Disable vertex array
        glDisableVertexAttribArray(mPositionHandle)
        glDisableVertexAttribArray(mMVMtrxHndl)
        glDisableVertexAttribArray(mMVPMtrxHndl)
        glDisableVertexAttribArray(mColorHandle)

        glUseProgram(mIdSurface)
        mPositionHandle = glGetAttribLocation(mIdSurface, "a_Position")
        glEnableVertexAttribArray(mPositionHandle)
//        glVertexAttribPointer(
//            mPositionHandle, COORDS_PER_VERTEX, GL_FLOAT,
//            false, vertexStride, dxfObject?.vertexBufferSurface
//        )

        mMVMtrxHndl = glGetUniformLocation(mIdSurface, "u_MVMatrix")
        GLES20.glUniformMatrix4fv(mMVMtrxHndl, 1, false, mMVMatrix, 0)

        mMVPMtrxHndl = glGetUniformLocation(mIdSurface, "u_MVPMatrix")
        GLES20.glUniformMatrix4fv(mMVPMtrxHndl, 1, false, mMVPMatrix, 0)

        mColorHandle = glGetUniformLocation(mIdSurface, "a_Color")
        for (oneSurface in dxfObject!!.listOneSurface) {
            // Set color for drawing the triangle
            glUniform4fv(mColorHandle, 1, oneSurface.vec4Color, 0)
            // Draw the square
            glDrawElements(
                GL_TRIANGLES, oneSurface.elementIndex.size,
                GL_UNSIGNED_SHORT, oneSurface.drawIndexBufferSurface
            )
        }
        // Disable vertex array
        glDisableVertexAttribArray(mPositionHandle)
        glDisableVertexAttribArray(mMVMtrxHndl)
        glDisableVertexAttribArray(mMVPMtrxHndl)
        glDisableVertexAttribArray(mColorHandle)
    }


    override fun onChangeSelect(x: Float, y: Float, width: Int, height: Int) {

    }


    constructor( dxfObject: DxfObject): this(){
        this.dxfObject = dxfObject
    }


    private fun evaluateNormal( mapPoint: MutableMap<Int, Vector3f>, face: Map.Entry<Int, List<Int>?>): Float3 {
        var result = Float3()
        if( face.value != null)
        for (i in 1 until face.value!!.size - 1) {
            val list = face.value!!
            val vertexA = mapPoint[ list[0] ] //offObject.getVertex(face.vertexReferences[0]!!)
            val vertexB = mapPoint[ list[i] ] //offObject.getVertex(face.vertexReferences[i]!!)
            val vertexC = mapPoint[ list[i+1] ] //offObject.getVertex(face.vertexReferences[i + 1]!!)
            val vectorA = Float3()
            vectorA.x = vertexB!!.x - vertexA!!.x
            vectorA.y = vertexB.y - vertexA.y
            vectorA.z = vertexB.z - vertexA.z
            val vectorB = Float3()
            vectorB.x = vertexC!!.x - vertexA.x
            vectorB.y = vertexC.y - vertexA.y
            vectorB.z = vertexC.z - vertexA.z
            result = Float3.Companion.getCrossProduct(vectorA, vectorB)
            if (result.length > 0.001f) {
                break
            }
        }
        result.length = 1.0f
        return result
    }


    companion object {
        // number of coordinates per vertex in this array
        const val COORDS_PER_VERTEX = 3

       }


}