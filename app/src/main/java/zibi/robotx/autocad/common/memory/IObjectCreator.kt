package zibi.robotx.autocad.common.memory

interface IObjectCreator<T> {
    fun create(): T
}