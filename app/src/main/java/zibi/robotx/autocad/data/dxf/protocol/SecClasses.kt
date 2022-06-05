package zibi.robotx.autocad.data.dxf.protocol

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import java.util.*

class SecClasses(lastElem: DxfChain) : DxfChain() {

    private val mapClClass: MutableMap<String, ClClass> = mutableMapOf()

    override fun read(dxfContext: DxfLoaderContext) {
        var code = dxfContext.get()
        loop@ while (true) {
            if (code == "0") {
                do{ code = dxfContext.get() }while(code == "0")
                when (code) {
                    "ENDSEC" -> {
                        return
                    }
                    "CLASS" -> {
                        val clClass = ClClass( this, true)
                        mapClClass[clClass.NameDXFClass] = clClass
                        code = dxfContext.lastCode()
                        continue@loop
                    }
                }
            }
            code = dxfContext.get()
        }
    }

    override fun write(sbX: StringBuilder): StringBuilder {
        val sb: StringBuilder = StringBuilder()
        if(mapClClass.isNotEmpty()) {
            for ( clClass in mapClClass.values) {
                clClass.write(sb)
            }
        }

        if( sb.isNotEmpty() ) {
            if( sbX.isNotEmpty()) sbX.append( "\n ")
            sbX.append( " 0\nSECTION\n 2\nCLASSES")
            sbX.append( sb)
            sbX.append( "\n 0\nENDSEC")
        }

        return sbX
    }

    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? { return sizeMMParent }
    override fun scaleObjectToFit(maxRadiusSqr: Float): Float { return maxRadiusSqr }
    override fun collectionConnect(collectionPoint: CollectionPoint) { }
    override fun xdef(): Int { return 0 }

    fun isNoClass(nameDXFClass: String): Boolean{
        return ! mapClClass.containsKey( nameDXFClass)
    }

    fun getClClass(nameDXFClass: String): ClClass {
        return when {
            mapClClass.containsKey( nameDXFClass) -> mapClClass[nameDXFClass]!!
            else                                  -> {
                val cla = ClClass(this, nameDXFClass)
                mapClClass[ nameDXFClass] = cla
                cla
            }
        }
    }

    init {
        last(lastElem)
    }
}