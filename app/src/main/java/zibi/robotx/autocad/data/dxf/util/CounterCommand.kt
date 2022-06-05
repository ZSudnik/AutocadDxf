package zibi.robotx.autocad.data.dxf.util

import zibi.robotx.autocad.data.dxf.DxfChain

class CounterCommand(newStart: Int) {

    var intCounter: Int = newStart
    get()  {
        field++
        return field
    }
    private set


    private val allSetDxfChain: MutableSet<Int> = mutableSetOf()

    fun isCounting( dxfChainId: Int): Boolean{
        return if( allSetDxfChain.contains( dxfChainId ) ) {
            false
        }else{
            allSetDxfChain.add( dxfChainId )
            true
        }
    }

}