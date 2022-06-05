package zibi.robotx.autocad.android.modelviewer.data.obj

import zibi.robotx.autocad.data.obj.ObjModel
import zibi.robotx.autocad.data.obj.ObjMaterial
import zibi.robotx.autocad.data.obj.ObjMaterialLibrary
import android.graphics.Bitmap
import java.util.ArrayList
import java.util.HashMap

class ObjScene {

    var model: ObjModel? = null
    val materialLibraries: MutableList<ObjMaterialLibrary?> = ArrayList<ObjMaterialLibrary?>()
    val images: MutableMap<String?, Bitmap?> = HashMap<String?, Bitmap?>()


    fun getMaterials(): MutableList<ObjMaterial?> {
        val result: MutableList<ObjMaterial?> = ArrayList()
        for (library in materialLibraries) {
            result.addAll(library!!.materials)
        }
        return result
    }

    fun getMaterial(name: String?): ObjMaterial? {
        for (library in materialLibraries) {
            val material = library!!.getMaterial(name)
            if (material != null) {
                return material
            }
        }
        return null
    }
}