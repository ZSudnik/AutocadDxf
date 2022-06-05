package zibi.robotx.autocad.data.dxf.protocol

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class ObjObject : DxfChain {

    //    0 	 Record type (CLASS). Identifies beginning of a CLASS record
    var recordTyp: String? = null

    // 1 	 Class DXF record name; always unique
    var nameDXFClass: String? = null

    // 2 	 C++ class name. Used to bind with software that defines object class behavior; always unique
    var nameCClass: String? = null

    // 3 	 Application name. Posted in Alert box when a class definition listed in this section is not currently loaded
    var appName: String? = null

    // 90 	 Proxy capabilities flag. Bit-coded value that indicates the capabilities of this object as a proxy:
        //        0 = No operations allowed (0)
        //        1 = Erase allowed (0x1)
        //        2 = Transform allowed (0x2)
        //        4 = Color change allowed (0x4)
        //        8 = Layer change allowed (0x8)
        //        16 = Linetype change allowed (0x10)
        //        32 = Linetype scale change allowed (0x20)
        //        64 = Visibility change allowed (0x40)
        //        128 = Cloning allowed (0x80)
        //        256 = Lineweight change allowed (0x100)
        //        512 = Plot Style Name change allowed (0x200)
        //        895 = All operations except cloning allowed (0x37F)
        //         1023 = All operations allowed (0x3FF)
        //         1024 = Disables proxy warning dialog (0x400)
        //         32768 = R13 format proxy (0x8000)
    var proxyFlag: Int? = null

    // 91 	 Instance count for a custom class
    var instanceCount: String? = null

    // 280 	 Was-a-proxy flag. Set to 1 if class was not loaded when this DXF file was created, and 0 otherwise
    var wasProxyFlag: Int? = null

    // 281 	 Is-an-entity flag. Set to 1 if class was derived from the AcDbEntity class and can reside in the BLOCKS or ENTITIES section. If 0, instances may appear only in the OBJECTS section
    var entityFlaf: Int? = null


    constructor(lastElem: DxfChain, isRead: Boolean) {
        last(lastElem)
        if (isRead) read(lastElem.dxfContext)
    }


    override fun read(dxfContext: DxfLoaderContext) {
        var cod = dxfContext.get()
        while (true) {
            when(cod) {
                "0" -> {
                    return
                }
                "1" -> {
                    nameDXFClass = dxfContext.stringValue()
                }
                "2" -> {
                    nameCClass = dxfContext.stringValue()
                }
                "3" -> {
                    appName = dxfContext.stringValue()
                }
                "90" -> {
                    proxyFlag = dxfContext.intValue()
                }
                "91" -> {
                    instanceCount = dxfContext.stringValue()
                }
                "280" -> {
                    wasProxyFlag = dxfContext.intValue()
                }
                "281" -> {
                    entityFlaf = dxfContext.intValue()
                }
                else -> return
            }
            cod = dxfContext.get()
        }
    }

    override fun write(sbX: StringBuilder): StringBuilder {
        sbX.append("\n 0\nCLASS")
//        if( recordTyp != null) sbX.append( "\n 0\n"+recordTyp )
        if( nameDXFClass != null) sbX.append( "\n 1\n"+nameDXFClass )
        if( nameCClass != null) sbX.append( "\n 2\n"+nameCClass )
        if( appName != null) sbX.append( "\n 3\n"+appName )
        if( proxyFlag != null) sbX.append( "\n 90\n"+proxyFlag )
        if( instanceCount != null) sbX.append( "\n 91\n"+instanceCount )
        if( wasProxyFlag != null) sbX.append( "\n 280\n"+wasProxyFlag )
        if( entityFlaf != null) sbX.append( "\n 281\n"+entityFlaf )
        return sbX
    }

    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? {
        return sizeMMParent
    }

    override fun scaleObjectToFit(maxRadiusSqr: Float): Float {
        return maxRadiusSqr
    }

    override fun collectionConnect(collectionPoint: CollectionPoint): Unit {
        // collectionPoint
   }

 	override fun xdef(): Int {	
	 return 0	
	}


}