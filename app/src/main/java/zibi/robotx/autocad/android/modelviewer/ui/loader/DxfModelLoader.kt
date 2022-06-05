package zibi.robotx.autocad.android.modelviewer.ui.loader

import zibi.robotx.autocad.data.dxf.error.DxfException
import zibi.robotx.autocad.data.dxf.error.DxfCorruptException
import zibi.robotx.autocad.data.dxf.error.DxfUnsupportedException
import zibi.robotx.autocad.data.dxf.error.DxfSizeException
import zibi.robotx.arplan.R
import zibi.robotx.autocad.android.modelviewer.data.IResourceLocator
import zibi.robotx.autocad.android.modelviewer.ui.render.DxfModelRenderer
import zibi.robotx.autocad.data.dxf.DxfLoader
import zibi.robotx.autocad.data.dxf.util.CollectionPoint
import java.io.IOException
import kotlinx.coroutines.*
import zibi.robotx.autocad.android.modelviewer.ui.render.Contour
import zibi.robotx.autocad.android.modelviewer.ui.render.Room

@Suppress("BlockingMethodInNonBlockingContext")
class DxfModelLoader( private val locator: IResourceLocator?) : CoroutineScope by MainScope() {

      suspend fun loadInBackground(): ModelLoaderResult = withContext(Dispatchers.IO)  {
          return@withContext try {
              val inLoc = locator!!.open()
              inLoc.use {
                  val dxfObject = DxfLoader().load(it) ?: return@withContext ModelLoaderResult(R.string.display_activity_obj_corrupt_error)
                  dxfObject.scaleObjectToFit(0f)
                  dxfObject.centerObject(null)
                  dxfObject.collectionConnect(CollectionPoint())
                  ModelLoaderResult(DxfModelRenderer(dxfObject))
//                                ModelLoaderResult( Room() )
//                                ModelLoaderResult( Contour(dxfObject) )
//                      ModelLoaderResult(  Contour( dxfObject) )//Room() )
                  //              } finally {
//                  inLoc.close()
//              }
              }
          } catch (e: DxfCorruptException) {
              ModelLoaderResult(R.string.display_activity_off_corrupt_error)
          } catch (e2: DxfSizeException) {
              ModelLoaderResult(R.string.display_activity_off_size_error)
          } catch (e3: DxfUnsupportedException) {
              ModelLoaderResult(R.string.display_activity_off_unsupported_error)
          } catch (e4: DxfException) {
              ModelLoaderResult(R.string.display_activity_generic_error)
          } catch (ex: IOException) {
              ModelLoaderResult(R.string.display_activity_io_error)
          }
      }


}