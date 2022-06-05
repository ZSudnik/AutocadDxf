package zibi.robotx.autocad.common.memory

interface IObjectPool<T> {
    fun allocate(): T?
    fun asSynchronized(): IObjectPool<T>
    fun release(t: T?)
}