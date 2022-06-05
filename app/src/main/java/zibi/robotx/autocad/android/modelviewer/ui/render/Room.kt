package zibi.robotx.autocad.android.modelviewer.ui.render

import android.content.Context
import android.opengl.GLES20.*
import android.opengl.Matrix
import zibi.robotx.arplan.R
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRenderer.Companion.checkGlError
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRenderer.Companion.createAndLinkProgram
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRenderer.Companion.idProgram
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRenderer.Companion.loadShader
import zibi.robotx.autocad.common.collision.old.CollisionDetection
import zibi.robotx.autocad.common.collision.old.Object3DData
import zibi.robotx.autocad.common.io.BufferUtil
import java.nio.*


class Room : ModelRenderer {

    private var vertexBuffer: FloatBuffer? = null
    private val colorBuffer: FloatBuffer
    private val normalBuffer: FloatBuffer
    private var numFaces = 1

    private var mMVPMatrix: FloatArray = FloatArray(16)
    private var mMVMatrix: FloatArray = FloatArray(16)
    private var projMatrix: FloatArray? = null
    private var viewMatrix: FloatArray? = null
    private val vertexStride = COORDS_PER_VERTEX * 4 // 4 bytes per vertex
    private val triangleIndexPoint: MutableList<IntArray> = mutableListOf()
    private var drawListBufferLine: ShortBuffer? = null
    private var faceBufferLine: MutableList<IntBuffer> = mutableListOf()

    val objects: List<Object3DData>

    // 1 -no transp, 0- full
    private val colors = arrayOf(
        floatArrayOf(1.0f, 0.0f, 1.0f, 0.5f),
        floatArrayOf(0.5f, 1.0f, 0.5f, 0.5f),
        floatArrayOf(1.0f, 0.5f, 0.5f, 0.5f),
        floatArrayOf(1.0f, 1.0f, 0.0f, 0.5f),
        floatArrayOf(0.0f, 1f, 1f, 0.5f),
        floatArrayOf(0.5f, 0.5f, 0.5f, 0.4f),
        floatArrayOf(1.0f, 0.0f, 0.0f, 0.6f)
    )

    private val normal = floatArrayOf(
        0f, 0f, 1f,
        0f, 1f, 0f,
        0f, 0f, -1f,
        0f, -1f, 0f,
        1f, 0f, 0f,
        -1f, 0f, 0f
    )

    val vertices = floatArrayOf(
        // Vertices of the 6 faces
        // FRONT
        -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
        1.0f, -1.0f, 1.0f,  // 1. right-bottom-front
        -1.0f, 1.0f, 1.0f,  // 2. left-top-front
        1.0f, 1.0f, 1.0f,  // 3. right-top-front
        // BACK 6 4 7 5 / 4 6 5 7 /
        1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
        -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
        1.0f, 1.0f, -1.0f,  // 7. right-top-back
        -1.0f, 1.0f, -1.0f,  // 5. left-top-back
        // LEFT
        -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
        -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
        -1.0f, 1.0f, -1.0f,  // 5. left-top-back
        -1.0f, 1.0f, 1.0f,  // 2. left-top-front
        // RIGHT
        1.0f, -1.0f, 1.0f,  // 1. right-bottom-front
        1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
        1.0f, 1.0f, 1.0f,  // 3. right-top-front
        1.0f, 1.0f, -1.0f,  // 7. right-top-back
        // TOP
        -1.0f, 1.0f, 1.0f,  // 2. left-top-front
        1.0f, 1.0f, 1.0f,  // 3. right-top-front
        -1.0f, 1.0f, -1.0f,  // 5. left-top-back
        1.0f, 1.0f, -1.0f,  // 7. right-top-back
        // BOTTOM
        -1.0f, -1.0f, -1.0f,  // 4. left-bottom-back
        1.0f, -1.0f, -1.0f,  // 6. right-bottom-back
        -1.0f, -1.0f, 1.0f,  // 0. left-bottom-front
        1.0f, -1.0f, 1.0f, // 1. right-bottom-front
        // Window
        1.0f, -0.5f, 0.5f,  // 1. right-bottom-front
        1.0f, -0.5f, -0.5f,  // 6. right-bottom-back
        1.0f, 0.5f, 0.5f,  // 3. right-top-front
        1.0f, 0.5f, -0.5f,  // 7. right-top-back
    )




    override fun onInitialize(context: Context) {
        for( ind in idProgram.indices) {
            if (glIsProgram(idProgram[ind])) {
                glDeleteProgram(idProgram[ind])
                idProgram[ind] = -1
            }
        }
        // prepare shaders and OpenGL program
        val vertexShaderSurface = loadShader(R.raw.room_vertex, GL_VERTEX_SHADER, context)
        val fragmentShaderSurface = loadShader(R.raw.room_fragment, GL_FRAGMENT_SHADER, context)

        idProgram[0] = createAndLinkProgram(
            vertexShaderSurface, fragmentShaderSurface,
            arrayOf("a_Position", "a_Normal", "a_Color")
        )
    }

    override fun onRender( isTwoSide:Boolean, f: Float, viewMatrix: FloatArray, projMatrix: FloatArray, modelMatrix: FloatArray) {
        this.projMatrix = projMatrix
        this.viewMatrix = viewMatrix
        Matrix.setIdentityM(mMVMatrix, 0)
        Matrix.multiplyMM(mMVMatrix, 0, viewMatrix, 0, modelMatrix, 0)
        Matrix.setIdentityM(mMVPMatrix, 0)
        Matrix.multiplyMM(mMVPMatrix, 0, projMatrix, 0, mMVMatrix, 0)

        glUseProgram(idProgram[0])

        val mPositionHandle = glGetAttribLocation(idProgram[0], "a_Position")
        glVertexAttribPointer(mPositionHandle, Contour.COORDS_PER_VERTEX, GL_FLOAT,
            false, vertexStride, vertexBuffer)
        glEnableVertexAttribArray(mPositionHandle)
        checkGlError("glGetAttribLocation")

        val mNormalHndl = glGetAttribLocation(idProgram[0], "a_Normal")
        glVertexAttribPointer(mNormalHndl, 3, GL_FLOAT, false, 3*4, normalBuffer);
        glEnableVertexAttribArray(mNormalHndl);
        checkGlError("glGetAttribLocation")


        // modelview
        val mMVMtrxHndl = glGetUniformLocation(idProgram[0], "u_MVMatrix")
        if(isTwoSide) {
            glUniformMatrix4fv(mMVMtrxHndl, 1, false, mMVMatrix, 0)
        }else{
            glUniformMatrix4fv(mMVMtrxHndl, 1, false, mMVPMatrix, 0)
        }
        glEnableVertexAttribArray(mMVMtrxHndl)
        checkGlError("glGetUniformLocation")

        // final matrix
        val mMVPMtrxHndl = glGetUniformLocation(idProgram[0], "u_MVPMatrix")
        glUniformMatrix4fv(mMVPMtrxHndl, 1, false, mMVPMatrix, 0)
        glEnableVertexAttribArray(mMVPMtrxHndl)
        checkGlError("glGetUniformLocation")


        val mColorHandle = glGetUniformLocation(idProgram[0], "u_Color")
        for (face in 0 until numFaces) {
            val vec4Color = floatArrayOf(colors[face][0], colors[face][1], colors[face][2], colors[face][3])
            glUniform4fv(mColorHandle, 1, vec4Color, 0)
            checkGlError("glGetUniformLocation")
            glDrawElements(
                GL_TRIANGLES, faceBufferLine[face].capacity(), //faceIndexPoint[face].size,
                GL_UNSIGNED_INT, faceBufferLine[face]
            )
        }
//        glDisable(GL10.GL_CULL_FACE)
    }

    var point: FloatArray? = null
    override fun onChangeSelect(touchX: Float, touchY: Float, width: Int, height: Int) {
        if (objects.isEmpty()) return

//        val objectHit = CollisionDetection.getBoxIntersection(objects, width, height, viewMatrix, projMatrix, touchX, touchY)
        val objectHit = CollisionDetection.getBoxIntersection(objects, width, height, mMVMatrix, projMatrix, touchX, touchY)
        if (objectHit != null) {
            val point3D: Object3DData? = null
            if ( true ){//isDetectCollision) {
                 point = CollisionDetection.getTriangleIntersection(
                    objectHit, width, height , mMVMatrix, projMatrix, touchX, touchY)
//                if (point != null) {
//                            point3D = Point.build(point).setColor(new float[]{1.0f, 0f, 0f, 1f});
//                }
            }

//                    final CollisionEvent collisionEvent = new CollisionEvent(this, objectHit, x, y, point3D);
//                    AndroidUtils.fireEvent(listeners, collisionEvent);
        }
//        val mouseRayProjection2 =
//            getMouseRayProjection(touchX, touchY, DisplayRenderer.width!!, DisplayRenderer.height!!, mMVMatrix, projMatrix!!)
//        val mouseRayProjection =
//            getMouseRayProjection_2(touchX, touchY, DisplayRenderer.width!!, DisplayRenderer.height!!, viewMatrix!!, projMatrix!!)
//        println("OpenGLES2 "+ "Mouse   Ray: " + floatArrayAsString(mouseRayProjection))
//        println("OpenGLES2 "+ "Mouse 2 Ray: " + floatArrayAsString(mouseRayProjection2))
//        println("OpenGLES2Test" + "ModelView: " + floatArrayAsString(mMVMatrix));
//        println("OpenGLES2Test" + "ModelViewInverse: " + floatArrayAsString(getInverseMatrix(mMVMatrix)!!) )
//        println("OpenGLES2Test" + "Mouse Coordinates: " + touchX + " , " + touchY)
//        moveIntersectionLineEndPoint(mouseRayProjection)
        for (face in 0 until numFaces) {
            val numb = triangleIndexPoint[face]
            var tp1: Int = numb[0] * 3
            var tp2: Int = numb[1] * 3
            var tp3: Int = numb[2] * 3
//            val res1 = checkPointInTriangle_3(mouseRayProjection, Vector3f(vertices[tp1] ,vertices[tp1+1] ,vertices[tp1+2] ),
//                Vector3f(vertices[tp2] ,vertices[tp2+1] ,vertices[tp2+2] ),
//                Vector3f(vertices[tp3] ,vertices[tp3+1] ,vertices[tp3+2] ))
            tp1 = numb[3] * 3
            tp2 = numb[4] * 3
            tp3 = numb[5] * 3
//            val res2 = checkPointInTriangle_3(mouseRayProjection, Vector3f(vertices[tp1] ,vertices[tp1+1] ,vertices[tp1+2] ),
//                Vector3f(vertices[tp2] ,vertices[tp2+1] ,vertices[tp2+2] ),
//                Vector3f(vertices[tp3] ,vertices[tp3+1] ,vertices[tp3+2] ))
//            if(res1) println( " GOOD face: $face result 1  ")
//            if(res2) println( " GOOD face: $face result 2  ")

        }
    }

    // Constructor - Set up the buffers
    init {
        // Setup vertex-array buffer. Vertices in float. An float has 4 bytes
        val vbb: ByteBuffer = ByteBuffer.allocateDirect(vertices.size * 4)
        vbb.order(ByteOrder.nativeOrder()) // Use native byte order
        vertexBuffer = vbb.asFloatBuffer() // Convert from byte to float
        vertexBuffer?.put(vertices) // Copy data into buffer
        vertexBuffer?.position(0) // Rewind


        numFaces = 6
        colorBuffer = BufferUtil.createFloatBuffer(numFaces * 4)
        for (face in 0 until numFaces) {
            colorBuffer.put(colors[face])
//            colorBuffer.put(colors[face][1])
//            colorBuffer.put(colors[face][2])
//            colorBuffer.put(colors[face][3])
        }
        colorBuffer.rewind()
//        for (vertexRef in face.vertexReferences) {
//            indexRefMap.put(vertexRef!!, vertexBuffer.position() / 3)
//            val vertex = dxfObject!!.getVertex(vertexRef)
//            vertexBuffer.put(vertex!!.x)
//            vertexBuffer.put(vertex.y)
//            vertexBuffer.put(vertex.z)
//            if (dxfObject.hasVertexColors) {
//                colorBuffer.put(vertex.r)
        if(numFaces >= 1) triangleIndexPoint.add(intArrayOf(1, 0, 2, 1, 2, 3))
        if(numFaces >= 2) triangleIndexPoint.add(intArrayOf(5, 4, 6, 5, 6, 7))
        if(numFaces >= 3) triangleIndexPoint.add(intArrayOf(9, 8, 10, 9, 10, 11))//9, 8, 10, 9, 10, 11
        if(numFaces >= 4) triangleIndexPoint.add(intArrayOf(13, 12, 14, 13, 14, 15))
        if(numFaces >= 5) triangleIndexPoint.add(intArrayOf(17, 16, 18, 17, 18, 19))
        if(numFaces >= 6) triangleIndexPoint.add(intArrayOf(21, 20, 22, 21, 22, 23))
        for (oneInd in triangleIndexPoint) {
            val dlbLine = ByteBuffer.allocateDirect(oneInd.size * 4)
            dlbLine.order(ByteOrder.nativeOrder())
            val bufLine = dlbLine.asIntBuffer()
            bufLine.put(oneInd)
            bufLine.position(0)
            faceBufferLine.add(bufLine)
        }

        val object3DData: Object3DData = Object3DData( vertexBuffer, triangleIndexPoint)
        objects = listOf( object3DData)


        val normals = floatArrayOf(
            1.0f, 1.0f, -1.0f,  /* Back. */
            -1.0f, 1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            -1.0f, 1.0f, 1.0f,  /* Front. */
            1.0f, 1.0f, 1.0f,
            -1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            -1.0f, 1.0f, -1.0f,  /* Left. */
            -1.0f, 1.0f, 1.0f,
            -1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f, 1.0f,
            -1.0f, 0.0f, 0.0f,
            1.0f, 1.0f, 1.0f,  /* Right. */
            1.0f, 1.0f, -1.0f,
            1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, -1.0f,
            1.0f, 0.0f, 0.0f,
            -1.0f, -1.0f, 1.0f,  /* Bottom. */
            1.0f, -1.0f, 1.0f,
            -1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            0.0f, -1.0f, 0.0f,
            -1.0f, 1.0f, -1.0f,  /* Top. */
            1.0f, 1.0f, -1.0f,
            -1.0f, 1.0f, 1.0f,
            1.0f, 1.0f, 1.0f,
            0.0f, 1.0f, 0.0f
        )
        val tangents =    floatArrayOf(-1.0f, 0.0f, 0.0f,            /* Back */
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            -1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,                /* Front */
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            0.0f, 0.0f, 1.0f,                /* Left */
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, -1.0f,                /* Right */
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            1.0f, 0.0f, 0.0f,                /* Top */
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,                /* Bottom */
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f,
            1.0f, 0.0f, 0.0f )
        val biNormals = floatArrayOf(    0.0f, 1.0f, 0.0f,                /* Back */
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,                /* Front */
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,                /* Left */
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,                 /* Right */
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 1.0f, 0.0f,
            0.0f, 0.0f, -1.0f,                /* Top */
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, -1.0f,
            0.0f, 0.0f, 1.0f,                /* Bottom */
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f,
            0.0f, 0.0f, 1.0f )
        normalBuffer =  ByteBuffer.allocateDirect(normals.size * 4)
            .order(ByteOrder.nativeOrder()).asFloatBuffer()
        normalBuffer.put( normals)
        normalBuffer.position(0)
    }


    companion object {
        const val COORDS_PER_VERTEX = 3
    }



}

//    fun getMouseRayProjection(touchX: Float, touchY: Float, windowWidth: Int, windowHeight: Int,
//        view: FloatArray?, projection: FloatArray?): FloatArray {
//
//        var rayDirection = FloatArray(4)
//        val normalizedX = 2f * touchX / windowWidth - 1f
//        val normalizedY = 1f - 2f * touchY / windowHeight
//        val normalizedZ = 1.0f
//        val rayNDC = floatArrayOf(normalizedX, normalizedY, normalizedZ)
//        var rayClip = floatArrayOf(rayNDC[0], rayNDC[1], -1f, 1f)
//        val inverseProjection = FloatArray(16)
//        Matrix.invertM(inverseProjection, 0, projection, 0)
//        val rayEye = multiplyMat4ByVec4(inverseProjection, rayClip)
//        rayClip = floatArrayOf(rayClip[0], rayClip[1], -1f, 0f)
//        val inverseView = FloatArray(16)
//        Matrix.invertM(inverseView, 0, view, 0)
//        val rayWorld4D = multiplyMat4ByVec4(inverseView, rayEye)
//        val rayWorld = floatArrayOf(rayWorld4D[0], rayWorld4D[1], rayWorld4D[2])
//        rayDirection = normalizeVector3(rayWorld)
//        return rayDirection
//    }
//