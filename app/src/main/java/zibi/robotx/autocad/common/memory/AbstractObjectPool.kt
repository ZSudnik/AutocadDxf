package zibi.robotx.autocad.common.memory


abstract class AbstractObjectPool<T> : IObjectPool<T?> {

    override fun asSynchronized(): IObjectPool<T?> {
        return SynchronizedObjectPool<T?>(this)
    }
}