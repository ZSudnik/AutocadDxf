package zibi.robotx.autocad.data.dxf

import zibi.robotx.autocad.data.dxf.util.CollectionPoint
import zibi.robotx.autocad.data.dxf.util.*

abstract class DxfChain : ArrayList<DxfChain>() {

    private var last: DxfChain? = null
//    private val listChild: MutableList<DxfChain> = mutableListOf()
    lateinit var dxfContext: DxfLoaderContext

    open var HandlenotOmitted: Int? = null
    var id: Int = 0
    private set

    private fun connectParent(kid: DxfChain){
        this.add( kid)
    }

    fun last(parent: DxfChain) {
        this.last = parent
        parent.connectParent( this )
        this.dxfContext = parent.dxfContext
        id = dxfContext.idChain
    }

    open fun reCountCommand( counterCommand: CounterCommand){
        if( counterCommand.isCounting(id))
            HandlenotOmitted = counterCommand.intCounter
        val iter = this.iterator()
        while( iter.hasNext() ){
            val element = iter.next()
            element.reCountCommand( counterCommand )
        }
    }


//    fun getNext

    abstract fun read( dxfContext: DxfLoaderContext)
    abstract fun write( sb: StringBuilder): StringBuilder
    abstract fun centerObject( sizeMMParent: SizeMinMax?): SizeMinMax?
    abstract fun scaleObjectToFit(maxRadiusSqr: Float): Float
    abstract fun collectionConnect(collectionPoint: CollectionPoint): Unit
    abstract fun xdef(): Int
}