package zibi.robotx.autocad.android.modelviewer.ui.render

import zibi.robotx.autocad.common.io.BufferUtil
import java.nio.ByteBuffer

class ObjRenderTexture(val width: Int, val height: Int) {

    val data: ByteBuffer?

    var id = 0

    init {
        data = BufferUtil.createByteBuffer(width * 4 * height)
    }
}