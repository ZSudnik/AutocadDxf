package zibi.robotx.autocad.data.obj

class ObjMaterialLibrary {
    val materials: MutableList<ObjMaterial?> = mutableListOf()
    fun getMaterial(name: String?): ObjMaterial? {
        for (material in materials) {
            if (material!!.name == name) {
                return material
            }
        }
        return null
    }
}