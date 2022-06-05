package zibi.robotx.autocad.android.common.event

abstract class EventProcessor<T : IEvent?>(private val eventClass: Class<T>) {
    abstract fun processEvent(t: T)
    fun handleEvent(event: IEvent?): Boolean {
        if (!eventClass.isInstance(event)) {
            return false
        }
        processEvent(eventClass.cast(event))
        return true
    }
}