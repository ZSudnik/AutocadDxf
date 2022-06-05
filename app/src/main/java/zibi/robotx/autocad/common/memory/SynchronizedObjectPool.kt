package zibi.robotx.autocad.common.memory


class SynchronizedObjectPool<T>(private val delegate: IObjectPool<T>) : IObjectPool<T?> {
    @Synchronized
    override fun allocate(): T? {
        return delegate.allocate()
    }

    @Synchronized
    override fun release(`object`: T?) {
        delegate.release(`object`)
    }

    override fun asSynchronized(): IObjectPool<T?> {
        return this
    }
}