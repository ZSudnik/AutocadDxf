package zibi.robotx.autocad.android.common.event

interface IEventQueue {
    fun getHead(): IEvent?
    fun pop(): IEvent?
    fun push(iEvent: IEvent?)
}