package zibi.robotx.autocad.data.obj


import zibi.robotx.autocad.data.obj.error.ObjCorruptException


internal class ObjLoaderFaceReference(component: String?) {
    var normalReference: Int? = null
    var texCoordReference: Int? = null
    val vertexReference: Int
    fun hasTexCoordReference(): Boolean {
        return texCoordReference != null
    }

    fun hasNormalReference(): Boolean {
        return normalReference != null
    }

    companion object {
        @Throws(ObjCorruptException::class)
        private fun parseIntSafe(text: String): Int {
            return try {
                text.toInt()
            } catch (ex: NumberFormatException) {
                throw ObjCorruptException("Could not parse int value.", ex)
            }
        }
    }

    init {
        val references = component!!.split("/").toTypedArray()
        vertexReference = Integer.valueOf(
            parseIntSafe(
                references[0]
            )
        )
        if (references.size < 2 || references[1].isEmpty()) {
            texCoordReference = null
        } else {
            texCoordReference = Integer.valueOf(
                parseIntSafe(
                    references[1]
                )
            )
        }
        if (references.size < 3 || references[2].isEmpty()) {
            normalReference = null
        } else {
            normalReference = Integer.valueOf(
                parseIntSafe(
                    references[2]
                )
            )
        }
    }
}