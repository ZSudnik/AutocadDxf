package zibi.robotx.autocad.android.common.view

import android.view.SurfaceHolder

open class SurfaceHolderCallbackAdapter : SurfaceHolder.Callback {

    override fun surfaceCreated(holder: SurfaceHolder) {}

    override fun surfaceDestroyed(holder: SurfaceHolder) {}

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {}
}