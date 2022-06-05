package zibi.robotx.autocad.android.modelviewer.ui.render

class ObjRenderMesh {
    var ambientColor = floatArrayOf(0.5f, 0.5f, 0.5f, 1.0f)
    var diffuseColor = floatArrayOf(0.7f, 0.7f, 0.7f, 1.0f)
    var specularColor = floatArrayOf(0.5f, 0.5f, 0.5f, 1.0f)
    var shininess = 64.0f
    var diffuseTextureName: String? = null
    var indexStart = 0
    var indexCount = 0


    fun hasTexture(): Boolean {
        return diffuseTextureName != null
    }
}