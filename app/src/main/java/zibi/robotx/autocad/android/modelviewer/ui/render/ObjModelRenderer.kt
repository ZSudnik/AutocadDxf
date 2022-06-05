package zibi.robotx.autocad.android.modelviewer.ui.render

import android.content.Context
import android.graphics.Bitmap
import zibi.robotx.autocad.android.modelviewer.data.obj.ObjScene
import android.opengl.GLES20.*
import android.opengl.Matrix
import zibi.robotx.arplan.R
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRenderer.Companion.checkGlError
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRenderer.Companion.createAndLinkProgram
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRenderer.Companion.idProgram
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRenderer.Companion.loadShader
import java.nio.FloatBuffer
import java.nio.ShortBuffer
import java.util.HashMap


class ObjModelRenderer(scene: ObjScene?) : ModelRenderer {

    private val indexBuffer: ShortBuffer?
    private val normalBuffer: FloatBuffer?
    private val texCoordBuffer: FloatBuffer?
    private val texturedMeshes: List<ObjRenderMesh?>?
    private val textures: MutableMap<String?, ObjRenderTexture?> = HashMap<String?, ObjRenderTexture?>()
    private val untexturedMeshes: List<ObjRenderMesh?>?
    private val vertexBuffer: FloatBuffer?

    // This will be used to pass in the texture.  */
    private var mTextureUniformHndl: Int = -1

    private var mMVPMatrix: FloatArray = FloatArray(16)
    private var mMVMatrix: FloatArray = FloatArray(16)
    private val vertexStride = 3 * 4 // 4 bytes per vertex


    override fun onInitialize(context: Context) {
        for( ind in idProgram.indices) {
            if (glIsProgram(idProgram[ind])) {
                glDeleteProgram(idProgram[ind])
                idProgram[ind] = -1
            }
        }
        // prepare shaders and OpenGL program
        val vertexShaderSurface = loadShader(R.raw.obj_vertex, GL_VERTEX_SHADER, context)
        val fragmentShaderSurface = loadShader(R.raw.obj_fragment, GL_FRAGMENT_SHADER, context)

        idProgram[0] = createAndLinkProgram(vertexShaderSurface, fragmentShaderSurface,
            arrayOf("a_Position", "a_TexCoordinate", "a_Normal")
        )

//        GLES10.glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, 8448.0f)
        for ((_, texture) in textures) {
            texture!!.id = generateTexture(texture)
        }
    }

    private fun generateTexture(texture: ObjRenderTexture?): Int {
//        glPixelStorei(GL_PACK_ALIGNMENT, 1)
//        glPixelStorei(GL_UNPACK_ALIGNMENT, 1)
        val textureIds = IntArray(1)
        glGenTextures(1, textureIds, 0)
        glBindTexture(GL_TEXTURE_2D, textureIds[0])
    // 1
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR)
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR)
        texture!!.data!!.rewind()
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, texture.width, texture.height,
            0, GL_RGBA, GL_UNSIGNED_BYTE, texture.data)

    //2
//        val array = texture!!.data!!.array()
//        val bitmapOptions = BitmapFactory.Options()
//        bitmapOptions.inScaled = false
//        bitmapOptions.inMutable = true
//        val bmp = BitmapFactory.decodeByteArray( array, 0, array.size, bitmapOptions)
//        val bitmap = bmp.copy(Bitmap.Config.ARGB_8888, false)
//            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST)
//            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST)
//            texImage2D(GL_TEXTURE_2D, 0, bitmap, 0)
//            bitmap.recycle()

//        glBindTexture(GL_TEXTURE_2D, 0)
        checkGlError("glTexImage2D")
        return textureIds[0]
    }

    override fun onRender( isTwoSide:Boolean, f: Float, viewMatrix: FloatArray, projMatrix: FloatArray, modelMatrix: FloatArray) {

        Matrix.setIdentityM(mMVMatrix, 0)
        Matrix.multiplyMM(mMVMatrix, 0, viewMatrix, 0, modelMatrix, 0)
        Matrix.setIdentityM(mMVPMatrix, 0)
        Matrix.multiplyMM(mMVPMatrix, 0, projMatrix, 0, mMVMatrix, 0)

        if( glIsProgram(idProgram[0])  )  glUseProgram(idProgram[0]) else return
//        checkGlError("glUseProgram")

        val mPositionHandle = glGetAttribLocation(idProgram[0], "a_Position")
//        checkGlError("glGetAttribLocation")
        val mTextureCoordHandle = glGetAttribLocation(idProgram[0], "a_TexCoordinate")
//        checkGlError("glGetAttribLocation")
        val mMVMtrxHndl = glGetUniformLocation(idProgram[0], "u_MVMatrix")
//        checkGlError("glGetUniformLocation")
        val mMVPMtrxHndl = glGetUniformLocation(idProgram[0], "u_MVPMatrix")
//        checkGlError("glGetUniformLocation")
        val mNormalHndl = glGetAttribLocation(idProgram[0], "a_Normal")
//        checkGlError("glGetAttribLocation")
        mTextureUniformHndl = glGetUniformLocation(idProgram[0], "u_Texture")
//        checkGlError("glGetUniformLocation")


        glVertexAttribPointer(mPositionHandle, 3, GL_FLOAT, false, vertexStride, vertexBuffer)
        glEnableVertexAttribArray(mPositionHandle)

        glVertexAttribPointer(mTextureCoordHandle, 2, GL_FLOAT, false, 2*4, texCoordBuffer)
        glEnableVertexAttribArray(mTextureCoordHandle)

        glUniformMatrix4fv(mMVMtrxHndl, 1, false, mMVMatrix, 0)

        glUniformMatrix4fv(mMVPMtrxHndl, 1, false, mMVPMatrix, 0)

        glVertexAttribPointer(mNormalHndl, 3, GL_FLOAT, false, 12, normalBuffer)
        glEnableVertexAttribArray(mNormalHndl)

        for (mesh in texturedMeshes!!) {
            renderMesh(mesh)
        }
        for (mesh2 in untexturedMeshes!!) {
            renderMesh(mesh2)
        }
//        checkGlError("glGetAttribLocation")

        glDisableVertexAttribArray(mPositionHandle)
        glDisableVertexAttribArray(mTextureUniformHndl)
        glDisableVertexAttribArray(mTextureCoordHandle)
        glDisableVertexAttribArray(mNormalHndl)
        glDisableVertexAttribArray(mMVMtrxHndl)
        glDisableVertexAttribArray(mMVPMtrxHndl)
    }

    override fun onChangeSelect(touchX: Float, touchY: Float, width: Int, height: Int) {    }

    private fun renderMesh(mesh: ObjRenderMesh?) {
//        GLES10.glColor4f(1.0f, 1.0f, 1.0f, 1.0f)
        val texture = textures[mesh!!.diffuseTextureName]
        texture?.let { bindTexture(it) }
//        GLES10.glMaterialfv(GL_FRONT_AND_BACK, GL_AMBIENT, mesh.ambientColor, 0)
//        GLES10.glMaterialfv(GL_FRONT_AND_BACK, GL_DIFFUSE, mesh.diffuseColor, 0)
//        GLES10.glMaterialfv(GL_FRONT_AND_BACK, GL_SPECULAR, mesh.specularColor, 0)
//        GLES10.glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, mesh.shininess)
        indexBuffer!!.position(mesh.indexStart)
        glDrawElements(GL_TRIANGLES, mesh.indexCount, GL_UNSIGNED_SHORT, indexBuffer)
//        checkGlError("glDrawElements")

    }

    private fun bindTexture(texture: ObjRenderTexture) {
        if (texture.id > 0) {
            glBindTexture(GL_TEXTURE_2D, texture.id)
//            checkGlError("glBindTexture")
            glUniform1i(mTextureUniformHndl, 0)
//            checkGlError("glUniform1i")
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

}