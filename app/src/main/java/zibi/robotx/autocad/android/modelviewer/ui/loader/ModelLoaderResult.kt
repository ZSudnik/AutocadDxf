package zibi.robotx.autocad.android.modelviewer.ui.loader

import zibi.robotx.autocad.android.modelviewer.ui.render.ModelRenderer

open class ModelLoaderResult {
    val errorMessageId: Int
    private val exception: Throwable?
    val modelRenderer: ModelRenderer?

    constructor(modelRenderer: ModelRenderer?) {
        this.modelRenderer = modelRenderer
        errorMessageId = -1
        exception = null
    }

    constructor(errorMessageId: Int) {
        this.errorMessageId = errorMessageId
        modelRenderer = null
        exception = null
    }

    constructor(errorMessageId: Int, ex: Throwable?) {
        this.errorMessageId = errorMessageId
        modelRenderer = null
        exception = ex
    }

    fun isSuccessful(): Boolean {
        return modelRenderer != null
    }


    fun getException(): Throwable? {
        return exception
    }
}