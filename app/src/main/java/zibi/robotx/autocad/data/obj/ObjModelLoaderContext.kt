package zibi.robotx.autocad.data.obj

import zibi.robotx.autocad.data.obj.util.ObjModelUtil.normalize
import kotlin.Throws
import zibi.robotx.autocad.data.obj.error.ObjException
import zibi.robotx.autocad.data.obj.error.ObjCorruptException
import zibi.robotx.autocad.data.obj.error.ObjSizeException
import java.io.BufferedReader
import java.io.IOException

internal class ObjModelLoaderContext(private val loader: ObjModelLoader,private val reader: BufferedReader) {
    private val model = ObjModel()
    var oo = 0
    @Throws(IOException::class)
    fun load(): ObjModel {
        while (true) {
//            oo++
//            if (oo > 156139) {
//                oo += 1
//            }
            val line = reader.readLine() ?: return model
//            if(oo==1313) return model
//            println(" $oo $line")
            val command = ObjParseCommand(line)
            if (!command.isEmpty) {
                when (command.name) {
                    COMMAND_VERTEX -> readVertex(command)
                    COMMAND_TEXCOORD -> readTexCoord(command)
                    COMMAND_NORMAL -> readNormal(command)
                    COMMAND_OBJECT -> readObject(command)
                    COMMAND_FACE -> readFace(command)
                    COMMAND_MATERIAL_REF -> readMaterialReference(command)
                    COMMAND_MATERIAL_LIB -> loadMaterialResource(command)
//                    else -> return  model
                }
            }
        }
    }

    @Throws(ObjException::class)
    private fun readVertex(command: ObjParseCommand) {
        if (command.parameterCount < 3) {
            return
//            throw ObjCorruptException("Insufficient vertex data.")
        }
        val vertex = ObjVertex()
        vertex.x = command.getFloat(0)
        vertex.y = command.getFloat(1)
        vertex.z = command.getFloat(2)
        if (model.vertices.size >= loader.maxVertexCount) {
            throw ObjSizeException("Max vertex count reached.")
        }
        model.vertices.add(vertex)
    }

    @Throws(ObjException::class)
    private fun readTexCoord(command: ObjParseCommand) {
        if (command.parameterCount == 0) {
            return
//            throw ObjCorruptException("Insufficient texture coordinate data.")
        }
        val texCoord = ObjTexCoord()
        texCoord.u = command.getFloat(0)
        if (command.parameterCount > 1) {
            texCoord.v = command.getFloat(1)
        }
        if (command.parameterCount > 2) {
            texCoord.w = command.getFloat(2)
        }
        if (model.texCoords.size >= loader.maxVertexCount) {
            throw ObjSizeException("Max texture coordinate count reached.")
        }
        model.texCoords.add(texCoord)
    }

    @Throws(ObjException::class)
    private fun readNormal(command: ObjParseCommand) {
        if (command.parameterCount < 3) {
            return
//            throw ObjCorruptException("Insufficient normal data.")
        }
        val normal = ObjNormal()
        normal.x = command.getFloat(0)
        normal.y = command.getFloat(1)
        normal.z = command.getFloat(2)
        normalize(normal)
        if (model.normals.size >= loader.maxVertexCount) {
            throw ObjSizeException("Max normal count reached.")
        }
        model.normals.add(normal)
    }

    @Throws(ObjException::class)
    private fun readObject(command: ObjParseCommand) {
        val name = command.remainder
        if (name.isEmpty()) {
            throw ObjCorruptException("Missing object name.")
        }
        model.objects.add(ObjObject(name))
    }

    @Throws(ObjException::class)
    private fun readFace(command: ObjParseCommand) {
        if (command.parameterCount < 3) {
            return
//            throw ObjCorruptException("Insufficient face data.")
        }
        val face = ObjFace()
        for (i in 0 until command.parameterCount) {
            val reference = ObjLoaderFaceReference(command.getParameter(i))
            val vertexReference = ObjVertexReference()
            val vertexRef = reference.vertexReference //.intValue();
            if (vertexRef > 0) {
                vertexReference.vertexIndex = vertexRef - 1
            } else {
                vertexReference.vertexIndex = model.vertices.size + vertexRef
            }
            face.setHasTexCoords(reference.hasTexCoordReference())
            if (reference.hasTexCoordReference()) {
                val texCoordRef = reference.texCoordReference!! //.intValue();
                if (texCoordRef > 0) {
                    vertexReference.texCoordIndex = texCoordRef - 1
                } else {
                    vertexReference.texCoordIndex = model.texCoords.size + texCoordRef
                }
            }
            face.setHasNormals(reference.hasNormalReference())
            if (reference.hasNormalReference()) {
                val normalRef = reference.normalReference!! //.intValue();
                if (normalRef > 0) {
                    vertexReference.normalIndex = normalRef - 1
                } else {
                    vertexReference.normalIndex = model.normals.size + normalRef
                }
            }
            face.references.add(vertexReference)
        }
        val mesh = resolveMesh()
        if (mesh.faces.size >= loader.maxFaceCount) {
            throw ObjSizeException("Max face count reached.")
        }
        mesh.faces.add(face)
    }

    private fun readMaterialReference(command: ObjParseCommand) {
        val currentMesh = resolveMesh()
        if (currentMesh.faces.isNotEmpty()) {
            val mesh = ObjMesh()
            if (command.parameterCount > 0) {
                mesh.materialName = command.remainder
            }
            resolveObject().meshes.add(mesh)
        } else if (command.parameterCount > 0) {
            currentMesh.materialName = command.remainder
        } else {
            currentMesh.materialName = ""
        }
    }

    @Throws(IOException::class)
    private fun loadMaterialResource(command: ObjParseCommand) {
        for (i in 0 until command.parameterCount) {
            model.materialLibraryRefs.add(command.getParameter(i))
        }
    }

    private fun resolveObject(): ObjObject {
        if (model.objects.isNotEmpty()) {
            return model.objects[model.objects.size - 1]
        }
        val obj = ObjObject()
        model.objects.add(obj)
        return obj
    }

    private fun resolveMesh(): ObjMesh {
        val obj = resolveObject()
        if (obj.meshes.isNotEmpty()) {
            return obj.meshes[obj.meshes.size - 1]
        }
        val mesh = ObjMesh()
        obj.meshes.add(mesh)
        return mesh
    }

    companion object {
        private const val COMMAND_FACE = "f"
        private const val COMMAND_MATERIAL_LIB = "mtllib"
        private const val COMMAND_MATERIAL_REF = "usemtl"
        private const val COMMAND_NORMAL = "vn"
        private const val COMMAND_OBJECT = "o"
        private const val COMMAND_TEXCOORD = "vt"
        private const val COMMAND_VERTEX = "v"
    }
}