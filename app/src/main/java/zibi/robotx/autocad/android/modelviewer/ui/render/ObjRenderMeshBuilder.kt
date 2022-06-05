package zibi.robotx.autocad.android.modelviewer.ui.render

import zibi.robotx.autocad.android.modelviewer.data.obj.ObjScene
import zibi.robotx.autocad.common.math.*
import zibi.robotx.autocad.common.io.BufferUtil
import zibi.robotx.autocad.data.obj.*
import java.nio.FloatBuffer
import java.nio.ShortBuffer
import java.util.ArrayList

class ObjRenderMeshBuilder(private val scene: ObjScene?) {

    var indexBuffer: ShortBuffer? = null
    private val model: ObjModel? = scene!!.model
    var normalBuffer: FloatBuffer? = null
    var texCoordBuffer: FloatBuffer? = null
    var texturedMeshes: MutableList<ObjRenderMesh?>? = null
    var untexturedMeshes: MutableList<ObjRenderMesh?>? = null
    var vertexBuffer: FloatBuffer? = null
    fun build() {
        correctLousyFaces()
        texturedMeshes = mutableListOf()
        untexturedMeshes = mutableListOf()
        val triangleCount = evaluateTriangleCount()
        val vertexCount = triangleCount * 3
        vertexBuffer = BufferUtil.createFloatBuffer(vertexCount * 3)
        normalBuffer = BufferUtil.createFloatBuffer(vertexCount * 3)
        texCoordBuffer = BufferUtil.createFloatBuffer(vertexCount * 2)
        indexBuffer = BufferUtil.createShortBuffer(triangleCount * 3)
        populateBuffers()
        vertexBuffer!!.rewind()
        normalBuffer!!.rewind()
        texCoordBuffer!!.rewind()
        indexBuffer!!.rewind()
    }


    private fun populateBuffers() {
        for (obj in model!!.objects) {
            for (mesh in obj.meshes) {
                populateBuffers(mesh)
            }
        }
    }

    private fun populateBuffers(mesh: ObjMesh?) {
        val renderMesh = ObjRenderMesh()
        fillMaterialData(renderMesh, mesh!!.materialName)
        renderMesh.indexStart = indexBuffer!!.position()
        for (face in mesh.faces) {
            populateBuffers(face)
        }
        renderMesh.indexCount = indexBuffer!!.position() - renderMesh.indexStart
        if (renderMesh.hasTexture()) {
            texturedMeshes!!.add(renderMesh)
        } else {
            untexturedMeshes!!.add(renderMesh)
        }
    }

    private fun fillMaterialData(renderMesh: ObjRenderMesh, materialName: String?) {
        val material = scene!!.getMaterial(materialName)
        if (material != null) {
            renderMesh.diffuseTextureName = material.diffuseTextureName
            val ambientColor = material.ambientColor
            renderMesh.ambientColor[0] = ambientColor.r
            renderMesh.ambientColor[1] = ambientColor.g
            renderMesh.ambientColor[2] = ambientColor.b
            renderMesh.ambientColor[3] = material.opacity
            val diffuseColor = material.diffuseColor
            renderMesh.diffuseColor[0] = diffuseColor.r
            renderMesh.diffuseColor[1] = diffuseColor.g
            renderMesh.diffuseColor[2] = diffuseColor.b
            renderMesh.diffuseColor[3] = material.opacity
            val specularColor = material.specularColor
            renderMesh.specularColor[0] = specularColor.r
            renderMesh.specularColor[1] = specularColor.g
            renderMesh.specularColor[2] = specularColor.b
            renderMesh.specularColor[3] = material.opacity
            renderMesh.shininess = material.shininess * 128.0f
        }
    }

    private fun populateBuffers(face: ObjFace) {
        if (face.references.size >= 3) {
            for (i in 1 until face.references.size - 1) {
                populateBuffers(face, 0)
                populateBuffers(face, i)
                populateBuffers(face, i + 1)
            }
        }
    }

    private fun populateBuffers(face: ObjFace, index: Int) {
        indexBuffer!!.put((vertexBuffer!!.position() / 3).toShort())
        val reference = face.references[index]
        val vertex = model!!.getVertex(reference)
        vertexBuffer!!.put(vertex.x)
        vertexBuffer!!.put(vertex.y)
        vertexBuffer!!.put(vertex.z)
        if (!face.hasNormals()) {
            val faceNormal = evaluateNormal(face)
            normalBuffer!!.put(faceNormal.x)
            normalBuffer!!.put(faceNormal.y)
            normalBuffer!!.put(faceNormal.z)
        } else {
            val normal = model.getNormal(reference)
            normalBuffer!!.put(normal.x)
            normalBuffer!!.put(normal.y)
            normalBuffer!!.put(normal.z)
        }
        if (!face.hasTexCoords()) {
            texCoordBuffer!!.put(0.0f)
            texCoordBuffer!!.put(0.0f)
            return
        }
        val texCoord = model.getTexCoord(reference)
        texCoordBuffer!!.put(texCoord.u)
        texCoordBuffer!!.put(texCoord.v)
    }

    private fun evaluateNormal(face: ObjFace): Float3 {
        var result = Float3()
        for (i in 1 until face.references.size - 1) {
            val vertexA = model!!.getVertex(face.references[0])
            val vertexB = model.getVertex(face.references[i])
            val vertexC = model.getVertex(face.references[i + 1])
            val vectorA = Float3()
            vectorA.x = vertexB.x - vertexA.x
            vectorA.y = vertexB.y - vertexA.y
            vectorA.z = vertexB.z - vertexA.z
            val vectorB = Float3()
            vectorB.x = vertexC.x - vertexA.x
            vectorB.y = vertexC.y - vertexA.y
            vectorB.z = vertexC.z - vertexA.z
            result = Float3.Companion.getCrossProduct(vectorA, vectorB)
            if (result.length > 0.001f) {
                break
            }
        }
        result.length = 1.0f
        return result
    }

    private fun correctLousyFaces() {
        for (obj in model!!.objects) {
            correctLousyFaces( obj )
        }
    }

    private fun correctLousyFaces(obj: ObjObject?) {
        for (mesh in getTexturedMeshes(obj)) {
            val material = scene!!.getMaterial(mesh!!.materialName)
            val correctionMesh = createCorrectionMesh(mesh)
            val faceIterator = mesh.faces.iterator()
            while (faceIterator.hasNext()) {
                val face = faceIterator.next()
                if (!face.hasTexCoords()) {
                    correctionMesh.faces.add(face)
                    faceIterator.remove()
                }
            }
            if (!correctionMesh.faces.isEmpty()) {
                if (scene.getMaterial(correctionMesh.materialName) == null) {
                    val correctionMaterial = ObjMaterial(correctionMesh.materialName, material)
                    correctionMaterial.diffuseTextureName = null
                    scene.getMaterials().add(correctionMaterial)
                }
                obj!!.meshes.add(correctionMesh)
            }
        }
    }

    private fun getTexturedMeshes(obj: ObjObject?): List<ObjMesh?> {
        val result: MutableList<ObjMesh?> = ArrayList()
        for (mesh in obj!!.meshes) {
            val material = scene!!.getMaterial(mesh.materialName)
            if (material != null && material.hasDiffuseTexture()) {
                result.add(mesh)
            }
        }
        return result
    }

    private fun createCorrectionMesh(mesh: ObjMesh?): ObjMesh {
        val result = ObjMesh()
        result.materialName = mesh!!.materialName.toString() + "_Textureless"
        return result
    }

    private fun evaluateTriangleCount(): Int {
        var result = 0
        for (obj in model!!.objects) {
            for (mesh in obj.meshes) {
                for (face in mesh.faces) {
                    result += evaluateTriangleCount(face)
                }
            }
        }
        return result
    }

    private fun evaluateTriangleCount(face: ObjFace): Int {
        val referencesCount = face.references.size
        return if (referencesCount < 3) {
            0
        } else referencesCount - 2
    }

}