package zibi.robotx.autocad.data.dxf.protocol.table

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class TblUCS(lastElem: DxfChain, isRead: Boolean) : TblCommon() {



	//	 UCS name
	//	2	
	var UCSName: String = ""
		private set

	//	 Standard flag values (bit-coded values):,16 = If set, table entry is externally dependent on an xref,32 = If both this bit and bit 16 are set, the externally dependent xref has been successfully resolved,64 = If set, the table entry was referenced by at least one entity in the drawing the last time the drawing was edited. (This flag is for the benefit of AutoCAD commands. It can be ignored by most programs that read DXF files and need not be set by programs that write DXF files)
	//	70	
	var StandardFlag: Int? = null

	//	 Origin (in WCS),DXF: X value APP: 3D pointDXF: Y and Z values of origin (in WCS)
	//	10	20	30	
	var OriginWCSDXF: Vector3f? = null

	//	 X-axis direction (in WCS),DXF: X value APP: 3D vectorDXF: Y and Z values of X-axis direction (in WCS)
	//	11	21	31	
	var XaxisDirection: Vector3f? = null

	//	 Y-axis direction (in WCS),DXF: X value APP: 3D vectorDXF: Y and Z values of Y-axis direction (in WCS)
	//	12	22	32	
	var YaxisDirection: Vector3f? = null

	//	 Always 0
	//	79	
	var Always: Int? = null

	//	 Elevation
	//	146	
	var Elevation: Float? = null

	//	 ID/handle of base UCS if this is an orthographic. This code is not present if the 79 code is 0. If this code is not present and 79 code is non-zero, then base UCS is assumed to be WORLD
	//	346	
	var IDhandleBase: String? = null

	//	 Orthographic type (optional always appears in pairs with the 13, 23, 33 codes):,1 = Top 2 = Bottom,3 = Front 4 = Back,5 = Left 6 = Right
	//	71	
	var OrthographicType: Int? = null

	//	 Origin for this orthographic type relative to this UCS,DXF: X value of origin point APP: 3D pointDXF: Y and Z values of origin point,
	//	13	23	33	
	var OriginFor: Vector3f? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
	      if (cod == "ENDTAB") return
			if (super.readX(cod)) {
            when (cod) {
			"2" -> {
				UCSName = getbfr.stringValue()
			}
			"70" -> {
				StandardFlag = getbfr.intValue()
			}
			"10" -> {
				if( OriginWCSDXF == null ) OriginWCSDXF = Vector3f()
				OriginWCSDXF?.x = getbfr.floatValue()
			}
			"20" -> {
				if( OriginWCSDXF == null ) OriginWCSDXF = Vector3f()
				OriginWCSDXF?.y = getbfr.floatValue()
			}
			"30" -> {
				if( OriginWCSDXF == null ) OriginWCSDXF = Vector3f()
				OriginWCSDXF?.z = getbfr.floatValue()
			}
			"11" -> {
				if( XaxisDirection == null ) XaxisDirection = Vector3f()
				XaxisDirection?.x = getbfr.floatValue()
			}
			"21" -> {
				if( XaxisDirection == null ) XaxisDirection = Vector3f()
				XaxisDirection?.y = getbfr.floatValue()
			}
			"31" -> {
				if( XaxisDirection == null ) XaxisDirection = Vector3f()
				XaxisDirection?.z = getbfr.floatValue()
			}
			"12" -> {
				if( YaxisDirection == null ) YaxisDirection = Vector3f()
				YaxisDirection?.x = getbfr.floatValue()
			}
			"22" -> {
				if( YaxisDirection == null ) YaxisDirection = Vector3f()
				YaxisDirection?.y = getbfr.floatValue()
			}
			"32" -> {
				if( YaxisDirection == null ) YaxisDirection = Vector3f()
				YaxisDirection?.z = getbfr.floatValue()
			}
			"79" -> {
				Always = getbfr.intValue()
			}
			"146" -> {
				Elevation = getbfr.floatValue()
			}
			"346" -> {
				IDhandleBase = getbfr.stringValue()
			}
			"71" -> {
				OrthographicType = getbfr.intValue()
			}
			"13" -> {
				if( OriginFor == null ) OriginFor = Vector3f()
				OriginFor?.x = getbfr.floatValue()
			}
			"23" -> {
				if( OriginFor == null ) OriginFor = Vector3f()
				OriginFor?.y = getbfr.floatValue()
			}
			"33" -> {
				if( OriginFor == null ) OriginFor = Vector3f()
				OriginFor?.z = getbfr.floatValue()
			}
			}
		}
			cod = getbfr.get()
	}
	}


    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? { return sizeMMParent }
    override fun scaleObjectToFit(maxRadiusSqr: Float): Float { return maxRadiusSqr }
	override fun collectionConnect(collectionPoint: CollectionPoint){}
 	override fun xdef(): Int { return 0 }

    init {
        last(lastElem)
        if (isRead) {
            read(lastElem.dxfContext)
        }
    }
	 companion object {


	//	 Subclass marker (AcDbUCSTableRecord)
	//	100	
	const val AcDbUCSTableRecord: String = "AcDbUCSTableRecord"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( UCSName != null) sb.append( "\n 2\n"+UCSName )
		if( StandardFlag != null) sb.append( "\n 70\n"+StandardFlag )
		if( OriginWCSDXF != null) sb.append( "\n 10\n"+OriginWCSDXF?.x+"\n 20\n"+OriginWCSDXF?.y+"\n 30\n"+OriginWCSDXF?.z )
		if( XaxisDirection != null) sb.append( "\n 11\n"+XaxisDirection?.x+"\n 21\n"+XaxisDirection?.y+"\n 31\n"+XaxisDirection?.z )
		if( YaxisDirection != null) sb.append( "\n 12\n"+YaxisDirection?.x+"\n 22\n"+YaxisDirection?.y+"\n 32\n"+YaxisDirection?.z )
		if( Always != null) sb.append( "\n 79\n"+Always )
		if( Elevation != null) sb.append( "\n 146\n"+Elevation )
		if( IDhandleBase != null) sb.append( "\n 346\n"+IDhandleBase )
		if( OrthographicType != null) sb.append( "\n 71\n"+OrthographicType )
		if( OriginFor != null) sb.append( "\n 13\n"+OriginFor?.x+"\n 23\n"+OriginFor?.y+"\n 33\n"+OriginFor?.z )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nUCS")
			sbX.append( sb)
		}

		 return sbX
	}
	constructor(lastElem: DxfChain, UCSName: String ): this(lastElem, false){
		this.UCSName = UCSName
	}
}