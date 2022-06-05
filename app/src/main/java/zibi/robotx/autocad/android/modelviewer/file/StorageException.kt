package zibi.robotx.autocad.android.modelviewer.file

import java.io.IOException


class StorageException : IOException {
    constructor()
    constructor(message: String?) : super(message)
    constructor(ex: Throwable?) : super(ex)
    constructor(message: String?, ex: Throwable?) : super(message, ex)

    companion object {
        private const val serialVersionUID: Long = 1
    }
}