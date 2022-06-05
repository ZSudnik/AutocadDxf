package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class EntOLEFRAME(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 OLE version number
	//	70	
	var OLEVersion: Int? = null

	//	 Length of binary data
	//	90	
	var LengthBinary: Int? = null

	//	 Binary data (multiple lines)
	//	310	
	var BinaryData: String? = null

	//	 End of OLE data (the string OLE)
	//	1	
	var EndOLE: String? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"70" -> {
				OLEVersion = getbfr.intValue()
			}
			"90" -> {
				LengthBinary = getbfr.intValue()
			}
			"310" -> {
				BinaryData = getbfr.stringValue()
			}
			"1" -> {
				EndOLE = getbfr.stringValue()
			}
			}
		}
			cod = getbfr.get()
	}
	}


    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? {
    //    return sizeMMParent?.findMinMax( begpnt, endpnt, xtruDir )
    //            ?: SizeMinMax.findMinMax( begpnt, endpnt, xtruDir )
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

    init {
        last(lastElem)
        if (isRead) {
            read(lastElem.dxfContext)
        }
    }
	 companion object {


	//	 Subclass marker (AcDbOleFrame)
	//	100	
	const val AcDbOleFrame: String = "AcDbOleFrame"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( OLEVersion != null) sb.append( "\n 70\n"+OLEVersion )
		if( LengthBinary != null) sb.append( "\n 90\n"+LengthBinary )
		if( BinaryData != null) sb.append( "\n 310\n"+BinaryData )
		if( EndOLE != null) sb.append( "\n 1\n"+EndOLE )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nOLEFRAME")
			sbX.append( sb)
		}

		 return sbX
	}}