package zibi.robotx.autocad.data.dxf.protocol

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class ClClass(lastElem: DxfChain, isRead: Boolean) : DxfChain() {


    // 1 	 Class DXF record name; always unique
    var NameDXFClass: String = ""
        private set

    // 2 	 C++ class name. Used to bind with software that defines object class behavior; always unique
    var NameCClass: String? = null

    // 3 	 Application name. Posted in Alert box when a class definition listed in this section is not currently loaded
    var AppName: String? = null

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
    var ProxyFlag: Int? = null

    // 91 	 Instance count for a custom class
    var InstanceCount: Int? = null

    // 280 	 Was-a-proxy flag. Set to 1 if class was not loaded when this DXF file was created, and 0 otherwise
    var WasProxyFlag: Int? = null

    // 281 	 Is-an-entity flag. Set to 1 if class was derived from the AcDbEntity class and can reside in the BLOCKS or ENTITIES section. If 0, instances may appear only in the OBJECTS section
    var EntityFlaf: Int? = null


    init {
        last(lastElem)
        if (isRead) read(lastElem.dxfContext)
    }

    constructor(lastElem: DxfChain, NameDXFClass: String ): this(lastElem, false){
        this.NameDXFClass = NameDXFClass
    }



    override fun read(dxfContext: DxfLoaderContext) {
        var cod = dxfContext.get()
        while (true) {
            when(cod) {
                "0" -> {
                    return
                }
                "1" -> {
                    NameDXFClass = dxfContext.stringValue()
                }
                "2" -> {
                    NameCClass = dxfContext.stringValue()
                }
                "3" -> {
                    AppName = dxfContext.stringValue()
                }
                "90" -> {
                    ProxyFlag = dxfContext.intValue()
                }
                "91" -> {
                    InstanceCount = dxfContext.intValue()
                }
                "280" -> {
                    WasProxyFlag = dxfContext.intValue()
                }
                "281" -> {
                    EntityFlaf = dxfContext.intValue()
                }
                else -> return
            }
            cod = dxfContext.get()
        }
    }

    override fun write(sbX: StringBuilder): StringBuilder {
        sbX.append("\n 0\nCLASS")
        sbX.append("\n 1\n$NameDXFClass") //if( NameDXFClass != null)
        if( NameCClass != null) sbX.append("\n 2\n$NameCClass")
        if( AppName != null) sbX.append("\n 3\n$AppName")  //
        if( ProxyFlag != null) sbX.append("\n 90\n$ProxyFlag")
        if( InstanceCount != null) sbX.append("\n 91\n$InstanceCount")
        if( WasProxyFlag != null) sbX.append("\n 280\n$WasProxyFlag")
        if( EntityFlaf != null) sbX.append("\n 281\n$EntityFlaf")
        return sbX
    }

    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? { return sizeMMParent }
    override fun scaleObjectToFit(maxRadiusSqr: Float): Float { return maxRadiusSqr }
    override fun collectionConnect(collectionPoint: CollectionPoint): Unit {}
    override fun xdef(): Int { return 0 }


}