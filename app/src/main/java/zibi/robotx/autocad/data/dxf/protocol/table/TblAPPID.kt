package zibi.robotx.autocad.data.dxf.protocol.table

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class TblAPPID(lastElem: DxfChain, isRead: Boolean) : TblCommon() {



	//	 User-supplied (or application-supplied) application name (for extended data). These table entries maintain a set of names for all registered applications
	//	2	
	var UsersuppliedApplicationsupplied: String = ""
		private set

	//	 Standard flag values (bit-coded values):,16 = If set, table entry is externally dependent on an xref,32 = If both this bit and bit 16 are set, the externally dependent xref has been successfully resolved,64 = If set, the table entry was referenced by at least one entity in the drawing the last time the drawing was edited. (This flag is for the benefit of AutoCAD commands. It can be ignored by most programs that read DXF files and need not be set by programs that write DXF files),
	//	70	
	var StandardFlag: Int? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
	      if (cod == "ENDTAB") return
			if (super.readX(cod)) {
            when (cod) {
			"2" -> {
				UsersuppliedApplicationsupplied = getbfr.stringValue()
			}
			"70" -> {
				StandardFlag = getbfr.intValue()
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


	//	 Subclass marker (AcDbRegAppTableRecord)
	//	100	
	const val AcDbRegAppTableRecord: String = "AcDbRegAppTableRecord"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( UsersuppliedApplicationsupplied != null) sb.append( "\n 2\n"+UsersuppliedApplicationsupplied )
		if( StandardFlag != null) sb.append( "\n 70\n"+StandardFlag )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nAPPID")
			sbX.append( sb)
		}

		 return sbX
	}

	constructor(lastElem: DxfChain, UsersuppliedApplicationsupplied: String ): this(lastElem, false){
		this.UsersuppliedApplicationsupplied = UsersuppliedApplicationsupplied
	}
}