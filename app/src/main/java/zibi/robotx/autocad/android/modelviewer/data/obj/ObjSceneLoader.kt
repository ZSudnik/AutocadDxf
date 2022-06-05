package zibi.robotx.autocad.android.modelviewer.data.obj


import kotlin.Throws
import zibi.robotx.autocad.android.modelviewer.data.IResourceLocator
import android.graphics.BitmapFactory
import zibi.robotx.autocad.data.obj.*
import zibi.robotx.autocad.data.obj.error.ObjCorruptException
import zibi.robotx.autocad.data.obj.error.ObjException
import java.io.IOException
import java.io.InputStream

class ObjSceneLoader( private val locator: IResourceLocator?) {

    private val modelLoader: IObjModelLoader = ObjModelLoader()
    private val materialLoader: IObjMaterialLoader = ObjMaterialLoader()


    @Throws(ObjException::class, IOException::class)
    fun load(): ObjScene {
        val result = ObjScene()
        loadModel(result)
        loadMaterialLibraries(result)
        loadImages(result)
        validateScene(result)
        return result
    }

    @Throws(ObjException::class, IOException::class)
    private fun loadModel(result: ObjScene) {
        val inputStream = locator!!.open()
        inputStream.use {
            result.model = modelLoader.load(it)
        }
    }

    @Throws(ObjException::class, IOException::class)
    private fun loadMaterialLibraries(result: ObjScene) {
        for (libraryRef in result.model!!.materialLibraryRefs) {
            val inputStream = openSafely(locator, libraryRef)
            inputStream?.use {
                result.materialLibraries.add(materialLoader.load(it))
            }
        }
    }

    @Throws(IOException::class)
    private fun loadImages(result: ObjScene) {
        var imageRef: String? = null
        var inputStream: InputStream? =null
        for (material in result.getMaterials()) {
            if (material!!.hasDiffuseTexture() && openSafely(
                    locator,
                    material.diffuseTextureName.also { imageRef = it }).also { inputStream = it!! } != null
            ) {
                try {
                    val image = BitmapFactory.decodeStream(inputStream)
                    if (image == null) {
                        inputStream?.close()
                    } else if (image.width == 0 || image.height == 0) {
                        inputStream?.close()
                    } else {
                        result.images[imageRef] = image
                        inputStream?.close()
                    }
                } finally {
                    inputStream?.close()
                }
            }
        }
    }

    companion object {
        private fun openSafely(locator: IResourceLocator?, child: String?): InputStream? {
            return try {
                locator!!.open(child)
            } catch (ex: IOException) {
                null
            }
        }

        @Throws(ObjCorruptException::class)
        private fun validateScene(scene: ObjScene) {
            val model = scene.model ?: return
            for (modelObj in model.objects) {
                for (mesh in modelObj.meshes) {
                    for (face in mesh.faces) {
                        for (reference in face.references) {
                            if (!isValidVertexIndex(model, reference.vertexIndex)) {
                                throw ObjCorruptException("Invalid vertex index.")
                            } else if (!isValidNormalIndex(model, reference.normalIndex)) {
                                throw ObjCorruptException("Invalid normal index.")
                            } else if (!isValidTexCoordIndex(model, reference.texCoordIndex)) {
                                throw ObjCorruptException("Invalid texture coordinate index.")
                            }
                        }
                    }
                }
            }
        }

        private fun isValidVertexIndex(model: ObjModel, index: Int): Boolean {
            return index >= 0 && index < model.vertices.size
        }

        private fun isValidNormalIndex(model: ObjModel, index: Int): Boolean {
            if (index == -1) {
                return true
            }
            return !(index < 0 || index >= model.normals.size)
        }

        private fun isValidTexCoordIndex(model: ObjModel, index: Int): Boolean {
            if (index == -1) {
                return true
            }
            return !(index < 0 || index >= model.texCoords.size)
        }
    }
}