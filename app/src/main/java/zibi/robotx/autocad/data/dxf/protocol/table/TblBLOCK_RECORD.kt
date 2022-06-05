package zibi.robotx.autocad.data.dxf.protocol.table

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class TblBLOCK_RECORD(lastElem: DxfChain, isRead: Boolean) : TblCommon() {



	//	 Block name
	//	2	
	var BlockName: String = ""
		private set

	//	 Hard-pointer ID/handle to associated LAYOUT object
	//	340	
	var HardpointerIDhandle: String? = null //HEX

	//	 Block insertion units.
	//	70	
	var BlockInsertion: Int? = null

	//	 Block explodability
	//	280	
	var BlockExplodability: Int? = null

	//	 Block scalability
	//	281	
	var BlockScalability: Int? = null

	//	 DXF: Binary data for bitmap preview (optional)
	//	310	
	var BinaryBitmap: String? = null

	//	 Xdata application name ACAD (optional)
	//	1001	
	var XdataApplication: String? = null

	//	 Xdata string data DesignCenter Data (optional)
	//	1000	
	var XdataData: String? = null

	//	 Begin xdata { (optional)
	//	1002	
	var BeginXdata: String? = null

	//	 AutoCAD DesignCenter version number
	//	1070	
	var AutoCADDesignCenter: Int? = null

	//	 Insert units:,0 = Unitless,1 = Inches,2 = Feet,3 = Miles,4 = Millimeters,5 = Centimeters,6 = Meters,7 = Kilometers,8 = Microinches,9 = Mils,10 = Yards,11 = Angstroms,12 = Nanometers,13 = Microns,14 = Decimeters,15 = Decameters,16 = Hectometers,17 = Gigameters,18 = Astronomical units,19 = Light years,20 = Parsecs,21 = US Survey Feet,22 = US Survey Inch,23 = US Survey Yard,24 = US Survey Mile
	//	1070	
	var InsertUnits0: Int? = null

	//	 End xdata },
	//	1002	
	var EndXdata: String? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
	      if (cod == "ENDTAB") return
			if (super.readX(cod)) {
            when (cod) {
			"2" -> {
				BlockName = getbfr.stringValue()
			}
			"340" -> {
				HardpointerIDhandle = getbfr.stringValue()
			}
			"70" -> {
				BlockInsertion = getbfr.intValue()
			}
			"280" -> {
				BlockExplodability = getbfr.intValue()
			}
			"281" -> {
				BlockScalability = getbfr.intValue()
			}
			"310" -> {
				BinaryBitmap = getbfr.stringValue()
			}
			"1001" -> {
				XdataApplication = getbfr.stringValue()
			}
			"1000" -> {
				XdataData = getbfr.stringValue()
			}
			"1002" -> {
				BeginXdata = getbfr.stringValue()
			}
			"1070" -> {
				AutoCADDesignCenter = getbfr.intValue()
			}
			"1070" -> {
				InsertUnits0 = getbfr.intValue()
			}
			"1002" -> {
				EndXdata = getbfr.stringValue()
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


	//	 Subclass marker (AcDbBlockTableRecord)
	//	100	
	const val AcDbBlockTableRecord: String = "AcDbBlockTableRecord"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( BlockName != null) sb.append( "\n 2\n"+BlockName )
		if( HardpointerIDhandle != null) sb.append( "\n 340\n"+HardpointerIDhandle )
		if( BlockInsertion != null) sb.append( "\n 70\n"+BlockInsertion )
		if( BlockExplodability != null) sb.append( "\n 280\n"+BlockExplodability )
		if( BlockScalability != null) sb.append( "\n 281\n"+BlockScalability )
		if( BinaryBitmap != null) sb.append( "\n 310\n"+BinaryBitmap )
		if( XdataApplication != null) sb.append( "\n 1001\n"+XdataApplication )
		if( XdataData != null) sb.append( "\n 1000\n"+XdataData )
		if( BeginXdata != null) sb.append( "\n 1002\n"+BeginXdata )
		if( AutoCADDesignCenter != null) sb.append( "\n 1070\n"+AutoCADDesignCenter )
		if( InsertUnits0 != null) sb.append( "\n 1070\n"+InsertUnits0 )
		if( EndXdata != null) sb.append( "\n 1002\n"+EndXdata )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nBLOCK_RECORD")
			sbX.append( sb)
		}

		 return sbX
	}
	constructor(lastElem: DxfChain, BlockName: String ): this(lastElem, false){
		this.BlockName = BlockName
	}
}