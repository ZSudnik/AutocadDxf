package zibi.robotx.autocad.android.common.event


import kotlin.jvm.Synchronized
import java.util.HashSet

class ProcessableEventQueue : EventQueue {
    private val processors: MutableSet<EventProcessor<*>?> = HashSet<EventProcessor<*>?>()

    constructor() {}
    constructor(limit: Int) : super(limit) {}

    @Synchronized
    fun addProcessor(processor: EventProcessor<*>?) {
        processors.add(processor)
    }

    @Synchronized
    fun removeProcessor(processor: EventProcessor<*>?) {
        processors.remove(processor)
    }

    @Synchronized
    fun process() {
        while (true) {
            val event = pop()
            if (event != null) {
                val it: Iterator<EventProcessor<*>?> = processors.iterator()
                while (it.hasNext() && !it.next()!!.handleEvent(event)) {
                }
            }
        }
    }
}