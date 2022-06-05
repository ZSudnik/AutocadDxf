package zibi.robotx.autocad.android.modelviewer.ui.loader

import zibi.robotx.autocad.data.obj.util.ObjModelUtil
import zibi.robotx.autocad.data.obj.error.ObjException
import zibi.robotx.autocad.data.obj.error.ObjCorruptException
import zibi.robotx.autocad.data.obj.error.ObjSizeException
import kotlinx.coroutines.*
import zibi.robotx.arplan.R
import zibi.robotx.autocad.android.modelviewer.data.IResourceLocator
import zibi.robotx.autocad.android.modelviewer.data.obj.ObjSceneLoader
import zibi.robotx.autocad.android.modelviewer.ui.render.ObjModelRenderer
import java.io.IOException

@Suppress("BlockingMethodInNonBlockingContext")
class ObjModelLoader( private val locator: IResourceLocator): CoroutineScope by MainScope() {


     suspend fun loadInBackground(): ModelLoaderResult = withContext(Dispatchers.IO)  {
             return@withContext try {
                 val scene = ObjSceneLoader( locator ).load( )
                 if (scene.model == null) return@withContext ModelLoaderResult(R.string.display_activity_obj_corrupt_error)
                 ObjModelUtil.centerModel(scene.model!!)
                 ObjModelUtil.scaleModelToFit(scene.model!!, 4.0f)
                 ModelLoaderResult(ObjModelRenderer(scene))
             } catch (e: ObjCorruptException) {
                 ModelLoaderResult(R.string.display_activity_obj_corrupt_error)
             } catch (e2: ObjSizeException) {
                 ModelLoaderResult(R.string.display_activity_obj_size_error)
             } catch (e3: ObjException) {
                 ModelLoaderResult(R.string.display_activity_generic_error)
             } catch (ex: IOException) {
                 ModelLoaderResult(R.string.display_activity_io_error)
             }
//         }
//        return asyncJob.getCompleted()
     }

}