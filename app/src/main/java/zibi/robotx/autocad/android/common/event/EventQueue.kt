package zibi.robotx.autocad.android.common.event


import java.util.*

open class EventQueue @JvmOverloads constructor(limit: Int = 0) : IEventQueue {
    private val events: Queue<IEvent?>
    private val limit: Int
    @Synchronized
    override fun getHead(): IEvent? {
        val peek: IEvent? = if (events.isEmpty()) {
            null
        } else {
            events.peek()
        }
        return peek
    }

    @Synchronized
    override fun push(iEvent: IEvent?) {
        if (limit == 0 || events.size < limit) {
            events.offer(iEvent)
        }
    }

    @Synchronized
    override fun pop(): IEvent? {
        val poll: IEvent?
        poll = if (events.isEmpty()) {
            null
        } else {
            events.poll()
        }
        return poll
    }

    init {
        events = LinkedList<IEvent>()
        this.limit = limit
    }
}