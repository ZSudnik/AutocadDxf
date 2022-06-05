package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class EntREGION(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Modeler format version number (currently = 1)
	//	70	
	var ModelerFormat: Int? = null

	//	 Proprietary data (multiple lines < 255 characters each)
	//	1	
	var ProprietaryData: String? = null

	//	 Additional lines of proprietary data (if previous group 1 string is greater than 255 characters) (optional)
	//	3	
	var AdditionalLines: String? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"70" -> {
				ModelerFormat = getbfr.intValue()
			}
			"1" -> {
				ProprietaryData = getbfr.stringValue()
			}
			"3" -> {
				AdditionalLines = getbfr.stringValue()
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


	//	 Subclass marker (AcDbModelerGeometry)
	//	100	
	const val AcDbModelerGeometry: String = "AcDbModelerGeometry"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( ModelerFormat != null) sb.append( "\n 70\n"+ModelerFormat )
		if( ProprietaryData != null) sb.append( "\n 1\n"+ProprietaryData )
		if( AdditionalLines != null) sb.append( "\n 3\n"+AdditionalLines )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nREGION")
			sbX.append( sb)
		}

		 return sbX
	}}