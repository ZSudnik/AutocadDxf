package zibi.robotx.autocad.android.modelviewer.file

import android.content.Context
import android.os.Environment
import androidx.documentfile.provider.DocumentFile
import zibi.robotx.autocad.android.modelviewer.ui.activity.FolderActivity
import kotlin.Throws
import java.io.File
import java.util.*
import android.content.ContextWrapper
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.core.net.toUri
import zibi.robotx.autocad.android.modelviewer.ui.activity.DisplayActivity
import zibi.robotx.autocad.android.modelviewer.ui.activity.getRealPathFromURI


class StorageFolder( val isRoot: Boolean)  {

//    @Throws(StorageException::class)
//    val parent: StorageFolder?
//        get()  {
//            Storage.assureAvailable()
//            val parent = file.parentFile ?: return null
//            return StorageFolder(parent, true)
//        }

//    @Throws(StorageException::class)
    val children: List<StorageEntity>
    get() {
//        Storage.assureAvailable()
//        val kk = File( file.absolutePath)
//        val path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MOVIES);
//        val files = kk.listFiles(ModelFileFilter()) ?: throw StorageException("Child files not available.")
        val result = createStorageList()
//        Collections.sort(result, StorageEntityComparator())
        return result
    }

    private fun createStorageList(): List<StorageEntity> {
        val result: MutableList<StorageEntity> = mutableListOf()
        result.add( StorageFile(Uri.parse("asset:///samples/backhoe.dxf"), "Excavator.dxf"  ))
        result.add( StorageFile(Uri.parse("asset:///samples/bike.dxf") ,"Bike.dxf" ))
        result.add( StorageFile(Uri.parse("asset:///samples/cnc machine.dxf"), "CNC machine.dxf"  ))
        result.add( StorageFile(Uri.parse("asset:///samples/drilling_machine.dxf") , "Drilling machine.dxf" ))
        result.add( StorageFile(Uri.parse("asset:///samples/gekko.dxf") , "Small bike.dxf" ))
        result.add( StorageFile(Uri.parse("asset:///samples/Laurana50k.dxf"), "Laurana50k.dxf"  ))
        result.add( StorageFile(Uri.parse("asset:///samples/Mc Cormik-D3262.dxf"), "Mc Cormik-D3262.dxf"  ))
        result.add( StorageFile(Uri.parse("asset:///samples/Nikon_D90_Camera.dxf"), "Nikon_D90_Camera.dxf" ))
        result.add( StorageFile(Uri.parse("asset:///samples/sink.dxf") ,"Sink.dxf" ))
        result.add( StorageFile(Uri.parse("asset:///samples/surface.dxf"),"Surface.dxf"  ))
        result.add( StorageFile(Uri.parse("asset:///samples/Tamiya TT-01.dxf") ,"Tamiya TT-01.dxf" ))
        result.add( StorageFile(Uri.parse("asset:///samples/threeDFaces.dxf") , "3DFaces.dxf" ))
        result.add( StorageFile(Uri.parse("asset:///samples/barrel.obj") ,"Barrel.obj" ))
        result.add( StorageFile(Uri.parse("asset:///samples/crazy_bill.obj") ,"Crazy Bill.obj"))
        result.add( StorageFile(Uri.parse("asset:///samples/humanoid_tri.obj") ,"Humanoid.obj" ))
        result.add( StorageFile(Uri.parse("asset:///samples/zombie.obj") ,"Zombie.obj" ))
        result.add( StorageFile(Uri.parse("asset:///samples/penguin.obj") ,"Penguin.obj" ))
        result.add( StorageFile(Uri.parse("asset:///samples/teapot.obj") ,"Teapot.obj" ))
        result.add( StorageFile(Uri.parse("asset:///samples/teddy.obj") ,"Teddy.obj" ))
        result.add( StorageFile(Uri.parse("asset:///samples/ToyPlane.obj"),"Toy Plane.obj"  ))
        result.add( StorageFile(Uri.parse("asset:///samples/cube.off"),"Cube.off"  ))
        result.add( StorageFile(Uri.parse("asset:///samples/hdodec.off"),"Hdodec.off"  ))
        result.add( StorageFile(Uri.parse("asset:///samples/space_shuttle.off"),"Space Shuttle.off"  ))
        result.add( StorageFile(Uri.parse("asset:///samples/space_station.off") ,"Space Station.off" ))
        result.add( StorageFile(Uri.parse("asset:///samples/teapot.off"),"Teapot.off"  ))
        result.add( StorageFile(Uri.parse("asset:///samples/x29_plane.off") ,"x29 Plane.off" ))

//        for (file in files) {
//            if (file.isDirectory) {
//                result.add(StorageFolder(file, false))
//            }
//            if (file.isFile) {
//                result.add(StorageFile(file))
//            }
//        }
        return result
    }

    companion object {
//        @Throws(StorageException::class)
//        fun getRoot(): StorageFolder {
//            Storage.assureAvailable()
//            return StorageFolder(Storage.getRootFolder(), true)
//        }

//        @Throws(StorageException::class)
//        fun fromPath(path: String?): StorageFolder {
//            Storage.assureAvailable()
//            val file = File(path)
//            return StorageFolder(file, true)
//        }

        @Throws(StorageException::class)
        fun fromWorkSpace(context: Context): StorageFolder {
//            Storage.assureAvailable()
//            val path = getRealPathFromURI ( context, getWorkPath().toUri())
//            val file = File(path + File.separator + DisplayActivity.FileLocation + File.separator)
            return StorageFolder( true)
        }

//        private fun getWorkPath(): File{
//            return  if( Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q){
//                MediaStore.Downloads.EXTERNAL_CONTENT_URI.path + File.separator + FolderActivity.FileLocation + File.separator
//            }else{
//             return   Environment.
//                getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS) //.absolutePath + File.separator + FolderActivity.FileLocation + File.separator
//            }
//        }
    }
}