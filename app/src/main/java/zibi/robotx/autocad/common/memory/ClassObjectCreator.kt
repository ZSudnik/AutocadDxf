package zibi.robotx.autocad.common.memory

class ClassObjectCreator<T>(private val clazz: Class<T>) : IObjectCreator<T> {

    override fun create(): T {
        return try {
            clazz.newInstance()
        } catch (ex: Exception) {
            throw IllegalStateException("Failed to create instance.", ex)
        }
    }
}