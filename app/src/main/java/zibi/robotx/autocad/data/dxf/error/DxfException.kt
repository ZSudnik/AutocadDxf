package zibi.robotx.autocad.data.dxf.error

import java.io.IOException

open class DxfException : IOException {
    constructor() {}
    constructor(message: String?) : super(message) {}
    constructor(ex: Throwable?) : super(ex) {}
    constructor(message: String?, ex: Throwable?) : super(message, ex) {}

    companion object {
        private const val serialVersionUID: Long = 1
    }
}