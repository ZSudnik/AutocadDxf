package zibi.robotx.autocad.android.modelviewer.ui.render

import android.content.Context
import android.opengl.GLES20.*
import android.opengl.*
import android.opengl.GLSurfaceView
import android.os.Bundle
import androidx.annotation.RawRes
import zibi.robotx.autocad.android.common.event.EventQueue
import zibi.robotx.autocad.android.modelviewer.ui.event.*
import zibi.robotx.autocad.android.modelviewer.util.getTextFile
import java.lang.RuntimeException
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10


class DisplayRenderer( private val context: Context) : GLSurfaceView.Renderer {
    @Volatile
    var modelRenderer: ModelRenderer? = null
        get() {
            var modelRenderer: ModelRenderer?
            synchronized(modelRendererLock) { modelRenderer = field }
            return modelRenderer
        }
        set(value) {
            synchronized(modelRendererLock) {
                isModelRendererInitialized = false
                field = value
            }
        }

    val eventQueue = EventQueue(20)
    private val modelRendererLock = Any()
    private var height: Int? = null
    private var width: Int? = null


    @Volatile
    private var isModelRendererInitialized = false
    private val xyDisplayRotation: DisplayRotation = DisplayRotationXY()
    private val freeDisplayRotation: DisplayRotation = DisplayRotationFree()
    private val blockDisplayRotation: DisplayRotation = DisplayRotationBlock()

    @Volatile
    private var currentRotation = freeDisplayRotation

    @Volatile
    private var zoom = -15.0f
    private var lastRenderTime = System.currentTimeMillis()


    override fun onSurfaceCreated(gl: GL10, config: EGLConfig) {
        lastRenderTime = System.currentTimeMillis()
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f)
//        GLES10.glShadeModel(GLES10.GL_SMOOTH)
//        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)

        val eX = 0.0f
        val eY = 0.0f
        val eZ =  -0.5f //-0.5
        val lkX = 0.0f
        val lkY = 0.0f
        val lkZ = -5.0f //-5
        val upOX = 0.0f
        val upOY = 1.0f
        val upOZ = 0.0f

        Matrix.setIdentityM(mViewMatrix, 0)
        Matrix.setLookAtM(mViewMatrix, 0, eX, eY, eZ, lkX, lkY, lkZ, upOX, upOY, upOZ)
//        Matrix.setLookAtM(mViewMatrix, 0, 0f, 0f, -3f, 0f, 0f, 0f, 0f, 1f, 0f)
        isModelRendererInitialized = false
        initializeModelRenderer()
    }

    override fun onSurfaceChanged(gl: GL10, width: Int, height: Int) {
        this.width = width
        this.height = height
        val screenHalfHeight: Float
        val screenHalfWidth: Float
        glViewport(0, 0, width, height)
//        val ratio = width.toFloat() / height
//        Matrix.setIdentityM(mProjMatrix, 0)
//        Matrix.frustumM(mProjMatrix, 0, -ratio, ratio, -1f, 1f, 3f, 7f)
       if (width == 0 || height == 0) {
            screenHalfWidth = 1.0f
            screenHalfHeight = 1.0f
        } else if (width > height) {
            screenHalfWidth = width.toFloat() / height.toFloat()
            screenHalfHeight = 1.0f
        } else {
            screenHalfWidth = 1.0f
            screenHalfHeight = height.toFloat() / width.toFloat()
        }
        Matrix.setIdentityM(mProjMatrix, 0)
        Matrix.frustumM(mProjMatrix, 0, -screenHalfWidth, screenHalfWidth,
            -screenHalfHeight, screenHalfHeight, 4f, 60f)
    }

    private val mProjMatrix = FloatArray(16)
    private val mViewMatrix = FloatArray(16)
    private val mModelMatrix = FloatArray(16)
    private var isTwoSided: Boolean = false

    override fun onDrawFrame(gl: GL10) {
        processEvents()
        glClear(GL_COLOR_BUFFER_BIT or GL_DEPTH_BUFFER_BIT)

        Matrix.setIdentityM(mModelMatrix, 0)
        Matrix.translateM(mModelMatrix, 0, 0f, 0f, zoom)
        currentRotation.apply( mModelMatrix )

        val currentTime = System.currentTimeMillis()
//        if(currentTime - lastRenderTime> 100) {
            renderModel(isTwoSided, currentTime - lastRenderTime,
                mViewMatrix, mProjMatrix, mModelMatrix)
            lastRenderTime = currentTime
//        }
    }

    private fun processEvents() {
        while (true) {
            val event = eventQueue.pop()
            if (event != null) {
                when (event) {
                    is BackgroundChangeEvent -> processBackgroundChangeEvent(event)
                    is TwoSidedChangeEvent -> processTwoSidedChangeEvent(event)
                    is LightingChangeEvent -> processLightingChangeEvent(event)
                    is RotationModeChangeEvent -> processRotationModeChangeEvent(event)
                    is RotationEvent -> processRotationEvent(event)
                    is ZoomEvent -> processZoomEvent(event)
                    is ClickPictureEvent ->  processClickPictureEvent(event)
                    is ResetPositionOffRotationEvent -> processResetPositionOffRotationEvent(event)
                }
            } else {
                return
            }
        }
    }

    private fun processBackgroundChangeEvent(event: BackgroundChangeEvent) {
        glClearColor(event.r, event.g, event.b, 1.0f)
    }

    private fun processTwoSidedChangeEvent(event: TwoSidedChangeEvent) {
        if (event.isTwoSided) {
            glDisable(GL_DEPTH_TEST)
            glDisable(GL_CULL_FACE)
            glEnable(GL_BLEND)
//            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA)
            glBlendEquation(GL_FUNC_ADD) //GL_LESS_OR_EQUAL
            glBlendFunc(GL_ONE, GL_ONE);
//            glEnable(GL_ALPHA_TEST);
        } else {
            glEnable(GL_DEPTH_TEST)
            glEnable(GL_CULL_FACE)
            glDisable(GL_BLEND)
        }
        isTwoSided = event.isTwoSided
    }

    private fun processLightingChangeEvent(event: LightingChangeEvent) {
        if (event.isLightingEnabled) {
//            glEnable(GL10.GL_LIGHTING)
        } else {
//            glDisable(GL10.GL_LIGHTING)
        }
    }

    private fun processRotationModeChangeEvent(event: RotationModeChangeEvent) {
        currentRotation = when (initSwitchTable()[event.mode.ordinal]) {
            1 ->  xyDisplayRotation
            2 -> freeDisplayRotation
            3 -> blockDisplayRotation
            else -> freeDisplayRotation
        }
    }

    private fun processRotationEvent(event: RotationEvent) {
        currentRotation.onChange(event.deltaX, event.deltaY)
    }

    private fun processZoomEvent(event: ZoomEvent) {
        zoom += event.amount
    }

    private fun processResetPositionOffRotationEvent(event: ResetPositionOffRotationEvent) {
        if( event.reset ) currentRotation.reset()
    }

    private fun processClickPictureEvent(event: ClickPictureEvent){
        synchronized(modelRendererLock) {
            if (modelRenderer != null) {
                isModelRendererInitialized = true
                modelRenderer!!.onChangeSelect(event.x, event.y, width ?: 10, height ?: 10)
            }
        }
    }

    private fun initializeModelRenderer() {
        synchronized(modelRendererLock) {
            if (modelRenderer != null) {
                isModelRendererInitialized = true
                modelRenderer!!.onInitialize(context)
            }
        }
    }

    private fun renderModel(isTwoSide: Boolean, elapsedMilliseconds: Long, viewMatrix: FloatArray,
                            projMatrix: FloatArray, modelMatrix: FloatArray) {
        synchronized(modelRendererLock) {
            if (modelRenderer != null) {
                if (!isModelRendererInitialized) {
                    modelRenderer!!.onInitialize(context)
                    isModelRendererInitialized = true
                }
                modelRenderer!!.onRender(isTwoSide, elapsedMilliseconds.toFloat() / 1000.0f,  viewMatrix, projMatrix, modelMatrix)
            }
        }
    }

    fun save(bundle: Bundle) {
        bundle.putFloat(BUNDLE_ZOOM_KEY, zoom)
        xyDisplayRotation.save(bundle)
        freeDisplayRotation.save(bundle)
//        blockDisplayRotation.save(bundle)
    }

    fun restore(bundle: Bundle) {
        zoom = bundle.getFloat(BUNDLE_ZOOM_KEY)
        xyDisplayRotation.restore(bundle)
        freeDisplayRotation.restore(bundle)
        blockDisplayRotation.restore(bundle)
    }


    companion object {
        private var SWITCH_TABLE: IntArray? = null
        private const val BUNDLE_ZOOM_KEY = "ZOOM"
//        private val ENVIRONMENT_AMBIENT = floatArrayOf(0.0f, 0.0f, 0.0f, 1.0f)
//        private val LIGHT_POSITION = floatArrayOf(0.5f, 0.5f, 1.0f, 0.0f)
//        private val LIGHT_AMBIENT = floatArrayOf(0.3f, 0.3f, 0.3f, 0.0f)
//        private val LIGHT_DIFFUSE = floatArrayOf(1.0f, 1.0f, 1.0f, 0.0f)
//        private val LIGHT_SPECULAR = floatArrayOf(0.2f, 0.2f, 0.2f, 0.0f)
        val idProgram: IntArray = IntArray(6){ -1 }

        fun initSwitchTable(): IntArray {
            if (SWITCH_TABLE == null) {
                SWITCH_TABLE = IntArray(RotationModeChangeEvent.RotationMode.values().size)
                try {
                    SWITCH_TABLE!![RotationModeChangeEvent.RotationMode.Free.ordinal] = 2
                } catch (e: NoSuchFieldError) { }
                try {
                    SWITCH_TABLE!![RotationModeChangeEvent.RotationMode.XY.ordinal] = 1
                } catch (e2: NoSuchFieldError) { }
                try {
                    SWITCH_TABLE!![RotationModeChangeEvent.RotationMode.Block.ordinal] = 3
                } catch (e2: NoSuchFieldError) {}
            }
            return SWITCH_TABLE!!
        }

        fun loadShader(@RawRes idRes: Int, type: Int, context: Context): Int {
            val shaderCode: String = getTextFile(context, idRes)
            // create a vertex shader type (GL_VERTEX_SHADER)
            // or a fragment shader type (GL_FRAGMENT_SHADER)
            val shader = glCreateShader(type)
            // add the source code to the shader and compile it
            glShaderSource(shader, shaderCode)
            glCompileShader(shader)
//            checkGlError("glCompileShader")
            return shader
        }

        // compile and link the program
        fun createAndLinkProgram(
            vertexShdrHndl: Int, fragmentShdrHndl: Int, attribs: Array<String>?): Int {
            var programHndl = glCreateProgram()

            if (programHndl != 0) {
                glAttachShader(programHndl, vertexShdrHndl)
//                checkGlError("glAttachShader")
                glAttachShader(programHndl, fragmentShdrHndl)
//                checkGlError("glAttachShader")
                // binding of attribs
                if (attribs != null) {
                    val size = attribs.size
                    for (i in 0 until size) {
                        glBindAttribLocation(programHndl, i, attribs[i])
//                        checkGlError("glBindAttribLocation")
                    }
                }
                // linking of shaders
                glLinkProgram(programHndl)
//                checkGlError("glLinkProgram")
                // getting the linkage result
                val linkResult = IntArray(1)
                glGetProgramiv(programHndl, GL_LINK_STATUS, linkResult, 0)
                // if fail
                if (linkResult[0] == 0) {
                    glDeleteProgram(programHndl)
                    programHndl = 0
                }
            }
            if (programHndl == 0) {
                return 0
//                throw RuntimeException("Impossible to create shader program.")
            }
            return programHndl
        }

        fun checkGlError(glOperation: String) {
            var error: Int
            while (glGetError().also { error = it } != GL_NO_ERROR) {
                throw RuntimeException("$glOperation: glError $error")
            }
        }


    }



}