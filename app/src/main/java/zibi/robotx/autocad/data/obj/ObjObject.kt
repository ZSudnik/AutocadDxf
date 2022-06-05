package zibi.robotx.autocad.data.obj

class ObjObject {

    val meshes: MutableList<ObjMesh> = mutableListOf()
    var name: String = "Default"
        set(name) {
            assertNameNotNull(name)
            field = name
        }

    constructor()

    constructor(name: String) {
        this.name = name
    }


    companion object {
        const val DEFAULT_NAME = "Default"
        private fun assertNameNotNull(name: String?) {
            requireNotNull(name) { "Name cannot be null." }
        }
    }
}