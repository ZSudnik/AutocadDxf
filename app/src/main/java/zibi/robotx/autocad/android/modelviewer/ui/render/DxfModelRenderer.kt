package zibi.robotx.autocad.android.modelviewer.ui.render


import android.content.Context
import android.opengl.GLES20.*
import zibi.robotx.autocad.data.dxf.DxfObject
import zibi.robotx.arplan.R
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRenderer.Companion.checkGlError
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRenderer.Companion.idProgram
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRenderer.Companion.loadShader
import zibi.robotx.autocad.android.modelviewer.ui.render.TableNormal.normals
import zibi.robotx.autocad.common.collision.BoundingBox
import zibi.robotx.autocad.common.collision.CollisionDetection
import zibi.robotx.autocad.common.collision.Math3DUtils
import zibi.robotx.autocad.data.dxf.util.OneSurface
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.IntBuffer

class DxfModelRenderer(val dxfObject: DxfObject) : ModelRenderer {

    private val vertexBuffer: FloatBuffer
    private val elementBuffer : IntBuffer
    private val vertexStride = COORDS_PER_VERTEX * 4 // 4 bytes per vertex
    private var viewMatrix: FloatArray = floatArrayOf()
    private var projMatrix: FloatArray = floatArrayOf()
    private var modelMatrix: FloatArray = floatArrayOf()

    private var pointerVertexLine = 0
    private var pointerVertexSurface = 0
    private var pointerElementLine = 0
    private var pointerElementSurface = 0



    override fun onInitialize(context: Context) {
        for( ind in idProgram.indices) {
            if (glIsProgram(idProgram[ind])) {
                glDeleteProgram(idProgram[ind])
                idProgram[ind] = -1
            }
        }

        if(dxfObject.listOnePolyline.isNotEmpty()) {
            // prepare shaders and OpenGL program for line
            val vertexShader = loadShader(R.raw.dxf_line_vertex, GL_VERTEX_SHADER, context)
            val fragmentShader = loadShader(R.raw.dxf_line_fragment, GL_FRAGMENT_SHADER, context)
            idProgram[0] = DisplayRenderer.createAndLinkProgram(vertexShader, fragmentShader,
                arrayOf("a_Position", "a_Color")
            )
        }
        if(dxfObject.listOneSurface.isNotEmpty()) {
            // prepare shaders and OpenGL program for surface
            val vertexShaderSurface = loadShader(R.raw.dxf_surface_vertex, GL_VERTEX_SHADER, context)
            val fragmentShaderSurface = loadShader(R.raw.dxf_surface_fragment, GL_FRAGMENT_SHADER, context )
            idProgram[1] = DisplayRenderer.createAndLinkProgram(vertexShaderSurface, fragmentShaderSurface,
                arrayOf("a_Position", "u_Color", "a_Normal", "u_MMatrix", "u_VMatrix", "u_PMatrix" )
            )
        }

        // load all vertex
        val vboBufferIds: IntBuffer = IntBuffer.allocate(2)
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
        glGenBuffers(2, vboBufferIds) // change 2
        glBindBuffer(GL_ARRAY_BUFFER, vboBufferIds[0]);
        glBufferData(GL_ARRAY_BUFFER, 4* vertexBuffer.capacity(), vertexBuffer, GL_STATIC_DRAW);
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboBufferIds[1]);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, 4* elementBuffer.capacity(), elementBuffer, GL_STATIC_DRAW);

    }


    override fun onRender( isTwoSide:Boolean, f: Float, viewMatrix: FloatArray, projMatrix: FloatArray, modelMatrix: FloatArray) {
        this.viewMatrix = viewMatrix
        this.projMatrix = projMatrix
        this.modelMatrix = modelMatrix

        drawLine()

        drawSurface()

    }


    private fun drawLine(){
        if( dxfObject.listOnePolyline.isNotEmpty()  ) {
            // Add program to OpenGL environment
            if( glIsProgram(idProgram[0])  )  glUseProgram(idProgram[0]) else return

            val mPositionHandle = glGetAttribLocation(idProgram[0], "a_Position")
            val mColorHandle = glGetUniformLocation(idProgram[0], "a_Color")
            val mMMtrxHndl = glGetUniformLocation(idProgram[0], "u_MMatrix")
            val mVMtrxHndl = glGetUniformLocation(idProgram[0], "u_VMatrix")
            val mPMtrxHndl = glGetUniformLocation(idProgram[0], "u_PMatrix")

            // get handle to vertex shader's vPosition member
            glVertexAttribPointer(mPositionHandle, 3, GL_FLOAT, false, vertexStride, pointerVertexLine);
            glEnableVertexAttribArray(mPositionHandle);
            checkGlError("glGetAttribLocation")

            glUniformMatrix4fv(mMMtrxHndl, 1, false, modelMatrix, 0)
            glUniformMatrix4fv(mVMtrxHndl, 1, false, viewMatrix, 0)
            glUniformMatrix4fv(mPMtrxHndl, 1, false, projMatrix, 0)
//            checkGlError("glGetUniformLocation")

            var offset = 0
            for (oneElement in dxfObject.listOnePolyline) {
                glUniform4fv(mColorHandle, 1, oneElement.vec4Color, 0)
//                checkGlError("glGetUniformLocation")
                glDrawElements(
                    GL_LINES, oneElement.elementIndex.size, GL_UNSIGNED_INT,// oneElement.drawIndexBufferLine
                    pointerElementLine + offset)
                offset += (4 * oneElement.elementIndex.size)
            }

            glDisableVertexAttribArray(mPositionHandle)
            glDisableVertexAttribArray(mColorHandle)
            glDisableVertexAttribArray(mMMtrxHndl)
            glDisableVertexAttribArray(mVMtrxHndl)
            glDisableVertexAttribArray(mPMtrxHndl)
        }

    }

    private var isDetectCollision = false
    private fun drawSurface(){
        isDetectCollision = false
        if(dxfObject.listOneSurface.isNotEmpty() ) {
            if( glIsProgram(idProgram[1])  )  glUseProgram(idProgram[1]) else return

            val mPositionHandle = glGetAttribLocation(idProgram[1], "a_Position")
            val mNormalHndl = glGetAttribLocation(idProgram[1], "a_Normal")
            val mColorHandle = glGetUniformLocation(idProgram[1], "u_Color")
            val mMMtrxHndl = glGetUniformLocation(idProgram[1], "u_MMatrix")
            val mVMtrxHndl = glGetUniformLocation(idProgram[1], "u_VMatrix")
            val mPMtrxHndl = glGetUniformLocation(idProgram[1], "u_PMatrix")

            glVertexAttribPointer(mNormalHndl, 3, GL_FLOAT, false, vertexStride, 0);
            glEnableVertexAttribArray(mNormalHndl)
//            checkGlError("glGetAttribLocation")

            glVertexAttribPointer(mPositionHandle, 3, GL_FLOAT, false, vertexStride, pointerVertexSurface);
            glEnableVertexAttribArray(mPositionHandle);
            checkGlError("glGetAttribLocation")


            glUniformMatrix4fv(mMMtrxHndl, 1, false, modelMatrix, 0)
            glUniformMatrix4fv(mVMtrxHndl, 1, false, viewMatrix, 0)
            glUniformMatrix4fv(mPMtrxHndl, 1, false, projMatrix, 0)
            checkGlError("glUniformMatrix4fv")


            var offset = 0
            for (oneSurface in dxfObject.listOneSurface) {
                glUniform4fv(mColorHandle, 1, oneSurface.vec4Color, 0)

                glDrawElements(GL_TRIANGLES, oneSurface.elementIndex.size, GL_UNSIGNED_INT,
                    pointerElementSurface + offset)
                offset += 4 * oneSurface.elementIndex.size
            }

            glDisableVertexAttribArray(mColorHandle)
            glDisableVertexAttribArray(mPositionHandle)
            glDisableVertexAttribArray(mNormalHndl)
            glDisableVertexAttribArray(mMMtrxHndl)
            glDisableVertexAttribArray(mVMtrxHndl)
            glDisableVertexAttribArray(mPMtrxHndl)

            isDetectCollision = true
        }
    }

//    val objects: List<Object3DData>
    override fun onChangeSelect(touchX: Float, touchY: Float, width: Int, height: Int) {
        if( !isDetectCollision ) return
        val nearHit = CollisionDetection.unProject(width, height, viewMatrix, projMatrix, touchX, touchY, 0f)
        val farHit = CollisionDetection.unProject(width, height, viewMatrix, projMatrix, touchX, touchY, 1f)
        val direction = Math3DUtils.substract(farHit, nearHit)
        Math3DUtils.normalize(direction)
        var min = Float.MAX_VALUE
        var surfaceHit: OneSurface? = null
        for (obj in dxfObject.listOneSurface) {
            val box = BoundingBox.create( obj.dimensions!!, modelMatrix)
            val intersection = CollisionDetection.getBoxIntersection(nearHit, direction, box)
            if (intersection[0] > 0 && intersection[0] <= intersection[1] && intersection[0] < min) {
                min = intersection[0]
                surfaceHit = obj
            }
        }

        if( surfaceHit != null){
//            val point = CollisionDetection.getTriangleIntersection(objectHit, width, height , viewMatrix, projMatrix, touchX, touchY)
            surfaceHit.visible = !surfaceHit.visible
        }
//        if (objects.isEmpty()) return
//        val objectHit = CollisionDetection.getBoxIntersection(objects, width, height, viewMatrix, projMatrix, touchX, touchY)
//        if (objectHit != null) {
//            // intersection point
//            val point3D: Object3DData? = null
//            if ( isDetectCollision) {
//                val point = CollisionDetection.getTriangleIntersection(objectHit, width, height , viewMatrix, projMatrix, touchX, touchY)
//            }
////                    final CollisionEvent collisionEvent = new CollisionEvent(this, objectHit, x, y, point3D);
////                    AndroidUtils.fireEvent(listeners, collisionEvent);
//        }
    }


    init {

//        val object3DData: Object3DData = if( dxfObject.listOneSurface.isNotEmpty() )
//            Object3DData( dxfObject.listOneSurface[0].drawIndexBufferSurface)
//        else
//            Object3DData()
//        objects = listOf( object3DData)

        var vertexBufferSize: Int = 4 * normals.size
        var elementBufferSize: Int = 0



        if(dxfObject.listOnePolyline.isNotEmpty() ){//&& dxfObject.arrIndexPoint != null) {
            pointerVertexLine = vertexBufferSize
            vertexBufferSize += 4 * dxfObject.vertexArrayLine.size
            pointerElementLine = elementBufferSize
            elementBufferSize += 4 * dxfObject.elementArrayLine.size
        }
        if(dxfObject.listOneSurface.isNotEmpty() && dxfObject.vertexArraySurface != null){
            pointerVertexSurface = vertexBufferSize
            vertexBufferSize += 4 * dxfObject.vertexArraySurface!!.size
            pointerElementSurface = elementBufferSize
            elementBufferSize += 4 * dxfObject.elementArraySurface.size
        }

        vertexBuffer =  ByteBuffer.allocateDirect(vertexBufferSize ).order(ByteOrder.nativeOrder()).asFloatBuffer()
        elementBuffer =  ByteBuffer.allocateDirect(elementBufferSize ).order(ByteOrder.nativeOrder()).asIntBuffer()
        vertexBuffer.put( normals)
        if(dxfObject.listOnePolyline.isNotEmpty() ) {
            vertexBuffer.put(dxfObject.vertexArrayLine)
            elementBuffer.put( dxfObject.elementArrayLine )
        }
        if(dxfObject.listOneSurface.isNotEmpty() && dxfObject.vertexArraySurface != null) {
            vertexBuffer.put(dxfObject.vertexArraySurface)
            elementBuffer.put( dxfObject.elementArraySurface )
        }
        vertexBuffer.position(0)
        elementBuffer.position(0)


    }


    companion object {
        // number of coordinates per vertex in this array
        const val COORDS_PER_VERTEX = 3

    }


}