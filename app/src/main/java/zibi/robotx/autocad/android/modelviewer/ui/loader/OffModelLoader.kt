package zibi.robotx.autocad.android.modelviewer.ui.loader

import zibi.robotx.autocad.data.off.util.OffObjectUtil
import zibi.robotx.autocad.data.off.error.OffException
import zibi.robotx.autocad.data.off.error.OffCorruptException
import zibi.robotx.autocad.data.off.error.OffUnsupportedException
import zibi.robotx.autocad.data.off.error.OffSizeException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.withContext
import zibi.robotx.arplan.R
import zibi.robotx.autocad.android.modelviewer.data.IResourceLocator
import zibi.robotx.autocad.data.off.OffLoader
import zibi.robotx.autocad.android.modelviewer.ui.render.OffModelRenderer
import java.io.IOException

@Suppress("BlockingMethodInNonBlockingContext")
class OffModelLoader(private val locator: IResourceLocator?): CoroutineScope by MainScope() {

    suspend fun loadInBackground(): ModelLoaderResult  = withContext(Dispatchers.IO) {
        return@withContext try {
            val inLoc = locator!!.open()
            inLoc.use {
                val offObject = OffLoader().load(it) ?: return@withContext ModelLoaderResult(R.string.display_activity_obj_corrupt_error)
                OffObjectUtil.centerObject(offObject)
                OffObjectUtil.scaleObjectToFit(offObject, 4.0f)
                ModelLoaderResult(OffModelRenderer(offObject))
            }
        } catch (e: OffCorruptException) {
            ModelLoaderResult(R.string.display_activity_off_corrupt_error)
        } catch (e2: OffSizeException) {
            ModelLoaderResult(R.string.display_activity_off_size_error)
        } catch (e3: OffUnsupportedException) {
            ModelLoaderResult(R.string.display_activity_off_unsupported_error)
        } catch (e4: OffException) {
            ModelLoaderResult(R.string.display_activity_generic_error)
        } catch (ex: IOException) {
            //            log.error("Could not load model.", (Throwable) ex);
            ModelLoaderResult(R.string.display_activity_io_error)
        }
    }

}