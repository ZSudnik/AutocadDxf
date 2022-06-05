package zibi.robotx.autocad.common.memory

import java.util.*


class DynamicObjectPool<T> : AbstractObjectPool<T> {
    private val creator: IObjectCreator<T>
    private val elements = Stack<T>()

    constructor(clazz: Class<T>) {
        creator = ClassObjectCreator<T>(clazz)
    }

    constructor(creator: IObjectCreator<T>) {
        this.creator = creator
    }

    // zibi.robot.autocad.common.memory.IObjectPool
    override fun allocate(): T? {
        return if (elements.isEmpty()) {
            creator.create()
        } else elements.pop()
    }

    // zibi.robot.autocad.common.memory.IObjectPool
    override fun release(`object`: T?) {
        elements.push(`object`)
    }
}