package zibi.robotx.autocad.android.modelviewer.ui.loader


import android.content.Context
import zibi.robotx.arplan.R

class InvalidSchemeModelLoader(context: Context?) : ModelLoaderResult(R.string.display_activity_scheme_error) {//AsyncTaskLoader<ModelLoaderResult>(context) {

    fun loadInBackground(): ModelLoaderResult {
        return ModelLoaderResult(R.string.display_activity_scheme_error)
    }
}