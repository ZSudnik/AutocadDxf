package zibi.robotx.autocad.data.off.error

class OffUnsupportedException : OffException {
    constructor() {}
    constructor(message: String?) : super(message) {}
    constructor(ex: Throwable?) : super(ex) {}
    constructor(message: String?, ex: Throwable?) : super(message, ex) {}

    companion object {
        private const val serialVersionUID: Long = 1
    }
}