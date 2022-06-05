package zibi.robotx.autocad.android.modelviewer.ui.render

import android.content.Context

interface ModelRenderer {
    fun onInitialize(context: Context)
    fun onRender(isTwoSide:Boolean, f: Float,  viewMatrix: FloatArray, projMatrix: FloatArray, modelMatrix: FloatArray)
    fun onChangeSelect(touchX: Float, touchY: Float, width: Int, height: Int)
}