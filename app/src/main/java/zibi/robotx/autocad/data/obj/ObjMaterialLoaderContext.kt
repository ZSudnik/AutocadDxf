package zibi.robotx.autocad.data.obj

import zibi.robotx.autocad.data.obj.error.ObjCorruptException
import zibi.robotx.autocad.data.obj.error.ObjException
import java.io.BufferedReader
import java.io.IOException

internal class ObjMaterialLoaderContext {
    private val library = ObjMaterialLibrary()
    private var currentMaterial = ObjMaterial()
    @Throws(ObjException::class, IOException::class)
    fun load(reader: BufferedReader): ObjMaterialLibrary {
        while (true) {
            val line = reader.readLine() ?: return library
            val command = ObjParseCommand(line)
            if (!command.isEmpty) {
                if (COMMAND_MATERIAL == command.name) {
                    readMaterial(command)
                }
                if (COMMAND_AMBIENT == command.name) {
                    readAmbientColor(command)
                }
                if (COMMAND_DIFFUSE == command.name) {
                    readDiffuseColor(command)
                }
                if (COMMAND_SPECULAR == command.name) {
                    readSpecularColor(command)
                }
                if (COMMAND_SHININESS == command.name) {
                    readShininess(command)
                }
                if (COMMAND_OPACITY == command.name) {
                    readOpacity(command)
                }
                if (COMMAND_DIFFUSE_TEXTURE == command.name) {
                    readDiffuseTexture(command)
                }
            }
        }
    }

    @Throws(ObjException::class)
    private fun readMaterial(command: ObjParseCommand) {
        currentMaterial = ObjMaterial()
        currentMaterial.name = command.remainder
        library.materials.add(currentMaterial)
    }

    @Throws(ObjException::class)
    private fun readAmbientColor(command: ObjParseCommand) {
        currentMaterial.ambientColor = readColor(command)
    }

    @Throws(ObjException::class)
    private fun readDiffuseColor(command: ObjParseCommand) {
        currentMaterial.diffuseColor = readColor(command)
    }

    @Throws(ObjException::class)
    private fun readSpecularColor(command: ObjParseCommand) {
        currentMaterial.specularColor = readColor(command)
    }

    @Throws(ObjException::class)
    private fun readColor(command: ObjParseCommand): ObjColor {
        return if (command.isEmpty) {
            throw ObjCorruptException("Insufficient color data.")
        } else if (command.parameterCount == 1) {
            val value = command.getFloat(0)
            ObjColor(value, value, value)
        } else if (SPECTRAL_COLOR.equals(
                command.getParameter(0),
                ignoreCase = true
            )
        ) {
            ObjColor()
        } else {
            if (XYZ_COLOR.equals(
                    command.getParameter(0),
                    ignoreCase = true
                )
            ) {
                return ObjColor()
            }
            if (command.parameterCount < 3) {
                throw ObjCorruptException("Insufficient color data.")
            }
            val color = ObjColor()
            color.r = command.getFloat(0)
            color.g = command.getFloat(1)
            color.b = command.getFloat(2)
            color
        }
    }

    @Throws(ObjException::class)
    private fun readShininess(command: ObjParseCommand) {
        currentMaterial.shininess = command.getFloat(0) / 1000.0f
    }

    @Throws(ObjException::class)
    private fun readOpacity(command: ObjParseCommand) {
        currentMaterial.opacity = command.getFloat(0)
    }

    private fun readDiffuseTexture(command: ObjParseCommand) {
        currentMaterial.diffuseTextureName = command.remainder
    }

    companion object {
        private const val COMMAND_AMBIENT = "Ka"
        private const val COMMAND_DIFFUSE = "Kd"
        private const val COMMAND_DIFFUSE_TEXTURE = "map_Kd"
        private const val COMMAND_MATERIAL = "newmtl"
        private const val COMMAND_OPACITY = "d"
        private const val COMMAND_SHININESS = "Ns"
        private const val COMMAND_SPECULAR = "Ks"
        private const val SPECTRAL_COLOR = "spectral"
        private const val XYZ_COLOR = "xyz"
    }
}