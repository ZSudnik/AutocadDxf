package zibi.robotx.autocad.data.obj

/* loaded from: classes.dex */
class ObjMaterial {
    var ambientColor: ObjColor
        set(ambientColor) {
            assertColorNotNull(ambientColor)
            field = ambientColor
        }
    var diffuseColor: ObjColor
        set(diffuseColor) {
            assertColorNotNull(diffuseColor)
            field = diffuseColor
        }
    var diffuseTextureName: String?
    var name: String?
        set(name) {
            assertNameNotNull(name)
            field = name
        }
    var opacity: Float
    var shininess: Float
    var specularColor: ObjColor
        set(specularColor) {
            assertColorNotNull(specularColor)
            field = specularColor
        }
    constructor() {
        name = "Default"
        diffuseTextureName = null
        ambientColor = ObjColor(1.0f, 1.0f, 1.0f)
        diffuseColor = ObjColor(1.0f, 1.0f, 1.0f)
        specularColor = ObjColor(0.0f, 0.0f, 0.0f)
        shininess = 0.0f
        opacity = 1.0f
    }

    constructor(name: String?) {
        this.name = "Default"
        diffuseTextureName = null
        ambientColor = ObjColor(1.0f, 1.0f, 1.0f)
        diffuseColor = ObjColor(1.0f, 1.0f, 1.0f)
        specularColor = ObjColor(0.0f, 0.0f, 0.0f)
        shininess = 0.0f
        opacity = 1.0f
        assertNameNotNull(name)
        this.name = name
    }

    constructor(name: String?, other: ObjMaterial?) {
        this.name = "Default"
        diffuseTextureName = null
        ambientColor = ObjColor(1.0f, 1.0f, 1.0f)
        diffuseColor = ObjColor(1.0f, 1.0f, 1.0f)
        specularColor = ObjColor(0.0f, 0.0f, 0.0f)
        shininess = 0.0f
        opacity = 1.0f
        assertNameNotNull(name)
        assertMaterialNotNull(other)
        this.name = name
        diffuseTextureName = other!!.diffuseTextureName
        ambientColor = ObjColor(other.ambientColor)
        diffuseColor = ObjColor(other.diffuseColor)
        specularColor = ObjColor(other.specularColor)
        shininess = other.shininess
        opacity = other.opacity
    }


    fun hasDiffuseTexture(): Boolean {
        return diffuseTextureName != null
    }


    companion object {
        const val DEFAULT_NAME = "Default"
        private fun assertNameNotNull(name: String?) {
            requireNotNull(name) { "Name cannot be null." }
        }

        private fun assertMaterialNotNull(material: ObjMaterial?) {
            requireNotNull(material) { "Material cannot be null." }
        }

        private fun assertColorNotNull(color: ObjColor?) {
            requireNotNull(color) { "Color cannot be null." }
        }
    }
}