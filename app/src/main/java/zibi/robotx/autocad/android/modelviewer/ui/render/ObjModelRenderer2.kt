package zibi.robotx.autocad.android.modelviewer.ui.render

import android.content.Context
import android.graphics.Bitmap
import zibi.robotx.autocad.android.modelviewer.data.obj.ObjScene
import android.opengl.GLES10
import android.opengl.GLES10.*
import android.opengl.GLES20
import android.opengl.Matrix
import java.nio.FloatBuffer
import java.nio.ShortBuffer
import java.util.HashMap

class ObjModelRenderer2(scene: ObjScene?) : ModelRenderer {

    private val indexBuffer: ShortBuffer?
    private val normalBuffer: FloatBuffer?
    private val texCoordBuffer: FloatBuffer?
    private val texturedMeshes: List<ObjRenderMesh?>?
    private val textures: MutableMap<String?, ObjRenderTexture?> = HashMap<String?, ObjRenderTexture?>()
    private val untexturedMeshes: List<ObjRenderMesh?>?
    private val vertexBuffer: FloatBuffer?

    private var mIdSurface: Int = -1
    private var mMVPMatrix: FloatArray = FloatArray(16)
    private var mMVMatrix: FloatArray = FloatArray(16)
    private val vertexStride = 3 * 4 // 4 bytes per vertex


    private fun createTexture(bitmap: Bitmap?): ObjRenderTexture {
        val width = bitmap!!.width
        val height = bitmap.height
        val result = ObjRenderTexture(width, height)
        val data = result.data
        for (y in height - 1 downTo 0) {
            for (x in 0 until width) {
                val color = bitmap.getPixel(x, y)
                data!!.put((color shr 16 and 255).toByte())
                data.put((color shr 8 and 255).toByte())
                data.put((color and 255).toByte())
                data.put((color shr 24 and 255).toByte())
            }
        }
        data!!.rewind()
        return result
    }

    override fun onInitialize(context: Context) {
//        val vertexShaderSurfaceRaw = context.resources.getRawTextFile(R.raw.obj_vertex)
//        val fragmentShaderSurfaceRaw = context.resources.getRawTextFile(R.raw.obj_fragment)
//        // prepare shaders and OpenGL program
//        val vertexShaderSurface = DisplayRenderer.loadShader(GLES20.GL_VERTEX_SHADER, vertexShaderSurfaceRaw)
//        val fragmentShaderSurface = DisplayRenderer.loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentShaderSurfaceRaw)
//
//        mIdSurface = DisplayRenderer.createAndLinkProgram(vertexShaderSurface, fragmentShaderSurface,
//            arrayOf("a_Position", "a_Color", "a_Normal", "a_TexCoordinate")
//        )

//        GLES10.glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, 8448.0f)
        for ((_, texture) in textures) {
            texture!!.id = generateTexture(texture)
        }
        //mTextureUniformHndl = GLES20.glGetUniformLocation(mHexahedronPstnHndl, "u_Texture")
    }

    private fun generateTexture(texture: ObjRenderTexture?): Int {
        GLES20.glPixelStorei(GL_PACK_ALIGNMENT, 1)
        GLES20.glPixelStorei(GL_UNPACK_ALIGNMENT, 1)
        val textureIds = IntArray(1)
        GLES20.glGenTextures(1, textureIds, 0)
        val textureId = textureIds[0]
        GLES20.glBindTexture(GL_TEXTURE_2D, textureId)
        GLES20.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT)
        GLES20.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT)
        GLES20.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
        GLES20.glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        texture!!.data!!.rewind()
        GLES20.glTexImage2D(
            GL_TEXTURE_2D, 0, GL_RGBA,
            texture.width, texture.height,
            0, GL_RGBA, GL_UNSIGNED_BYTE, texture.data
        )
        return textureId
    }

    override fun onRender( isTwoSide:Boolean, f: Float, viewMatrix: FloatArray, projMatrix: FloatArray, modelMatrix: FloatArray) {
        GLES20.glUseProgram(mIdSurface)
        // get handle to vertex shader's vPosition member
        val mPositionHandle = GLES20.glGetAttribLocation(mIdSurface, "a_Position")
        GLES20.glEnableVertexAttribArray(mPositionHandle)
        GLES20.glVertexAttribPointer(
            mPositionHandle, Contour.COORDS_PER_VERTEX, GL_FLOAT,
            false, vertexStride, vertexBuffer)

        Matrix.setIdentityM(mMVMatrix, 0)
        Matrix.multiplyMM(mMVMatrix, 0, viewMatrix, 0, modelMatrix, 0)
        val mMVMtrxHndl = GLES20.glGetUniformLocation(mIdSurface, "u_MVMatrix")
        GLES20.glUniformMatrix4fv(mMVMtrxHndl, 1, false, mMVMatrix, 0)

        Matrix.setIdentityM(mMVPMatrix, 0)
        Matrix.multiplyMM(mMVPMatrix, 0, projMatrix, 0, mMVMatrix, 0)
        val mMVPMtrxHndl = GLES20.glGetUniformLocation(mIdSurface, "u_MVPMatrix")
        GLES20.glUniformMatrix4fv(mMVPMtrxHndl, 1, false, mMVPMatrix, 0)
//        checkGlError("glGetUniformLocation")

        val mNormalHndl = GLES20.glGetAttribLocation(mIdSurface, "a_Normal")
        GLES20.glEnableVertexAttribArray(mNormalHndl)
        GLES20.glVertexAttribPointer(
            mNormalHndl, 3, GLES20.GL_FLOAT,
            false, 12, normalBuffer
        )

//        val mColorHandle = GLES20.glGetAttribLocation(mIdSurface, "a_Color")
//        GLES20.glEnableVertexAttribArray(mColorHandle)
//        GLES20.glVertexAttribPointer(
//            mColorHandle, 4, GLES20.GL_FLOAT,
//            false, 16, colorBuffer
//        )

        GLES20.glDrawElements(GL_TRIANGLES, indexBuffer!!.capacity(), GL_UNSIGNED_SHORT, indexBuffer)



        GLES10.glEnableClientState(GL_VERTEX_ARRAY)
        GLES10.glEnableClientState(GL_NORMAL_ARRAY)
        GLES10.glEnableClientState(GL_TEXTURE_COORD_ARRAY)
        GLES10.glVertexPointer(3, GL_FLOAT, 0, vertexBuffer)
        GLES10.glNormalPointer(GL_FLOAT, 0, normalBuffer)
        GLES10.glTexCoordPointer(2, GL_FLOAT, 0, texCoordBuffer)
        for (mesh in texturedMeshes!!) {
            GLES10.glEnable(GL_TEXTURE_2D)
            renderMesh(mesh)
            GLES10.glDisable(GL_TEXTURE_2D)
        }
        GLES10.glDisableClientState(GL_TEXTURE_COORD_ARRAY)
        for (mesh2 in untexturedMeshes!!) {
            renderMesh(mesh2)
        }
        GLES10.glDisableClientState(GL_NORMAL_ARRAY)
        GLES10.glDisableClientState(GL_VERTEX_ARRAY)
    }

    override fun onChangeSelect(x: Float, y: Float, width: Int, height: Int) {    }

    private fun renderMesh(mesh: ObjRenderMesh?) {
        GLES10.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        val texture = textures[mesh!!.diffuseTextureName]
        texture?.let { bindTexture(it) }
        GLES10.glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, mesh.ambientColor, 0)
        GLES10.glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, mesh.diffuseColor, 0)
        GLES10.glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, mesh.specularColor, 0)
        GLES10.glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, mesh.shininess)
        indexBuffer!!.position(mesh.indexStart)
        GLES10.glDrawElements(4, mesh.indexCount, GL_UNSIGNED_SHORT, indexBuffer)
    }

    private fun bindTexture(texture: ObjRenderTexture) {
        if (texture.id > 0) {
            GLES20.glBindTexture(GL_TEXTURE_2D, texture.id)
        }
    }

    init {
        val builder = ObjRenderMeshBuilder(scene)
        builder.build()
        vertexBuffer = builder.vertexBuffer
        normalBuffer = builder.normalBuffer
        texCoordBuffer = builder.texCoordBuffer
        indexBuffer = builder.indexBuffer
        texturedMeshes = builder.texturedMeshes
        untexturedMeshes = builder.untexturedMeshes
        for ((key, value) in scene!!.images) {
            textures[key] = createTexture(value)
        }
    }

}