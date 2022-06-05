package zibi.robotx.autocad.common.memory


class StaticObjectPool<T>(capacity: Int, creator: IObjectCreator<T>) : AbstractObjectPool<T>() {
    private val elements: Array<Any?>
    private var position = 0

    constructor(capacity: Int, clazz: Class<T>) : this(
        capacity,
        ClassObjectCreator<T>(clazz)
    ) {
    }

    fun getCapacity(): Int {
        return elements.size
    }

    fun getRemaining(): Int {
        return position
    }

    // zibi.robot.autocad.common.memory.IObjectPool
    override fun allocate(): T? {
        val objArr = elements
        val i = position - 1
        position = i
        return objArr[i] as T?
    }

    // zibi.robot.autocad.common.memory.IObjectPool
    override fun release(`object`: T?) {
        val objArr = elements
        val i = position
        position = i + 1
        objArr[i] = `object`
    }

    init {
        elements = arrayOfNulls(capacity)
        for (i in 0 until capacity) {
            release(creator.create())
        }
    }
}