package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntPOLYLINE(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Obsolete formerly an entities follow flag (optional ignore if present)
	//	66	
	var ObsoleteFormerly: Int? = null

	//	 DXF: always 0, APP: a dummy point the X and Y values are always 0, and the Z value is the polyline's elevation (in OCS when 2D, WCS when 3D)DXF: always 0DXF: polyline's elevation (in OCS when 2D WCS when 3D)
	//	10	20	30	
	var AlwaysAPP: Vector3f? = null

	//	 Thickness (optional default = 0)
	//	39	
	var ThicknessDefault: Float? = null

	//	 Polyline flag (bit-coded default = 0):, 1 = This is a closed polyline (or a polygon mesh closed in the M direction), 2 = Curve-fit vertices have been added, 4 = Spline-fit vertices have been added, 8 = This is a 3D polyline, 16 = This is a 3D polygon mesh, 32 = The polygon mesh is closed in the N direction, 64 = The polyline is a polyface mesh, 128 = The linetype pattern is generated continuously around the vertices of this polyline
	//	70	
	var PolylineFlag: Int? = null

	//	 Default start width (optional default = 0)
	//	40	
	var DefaultStart: Float? = null

	//	 Default end width (optional default = 0)
	//	41	
	var DefaultEnd: Float? = null

	//	 Polygon mesh M vertex count (optional default = 0)
	//	71	
	var PolygonMesh: Int? = null

	//	 Polygon mesh N vertex count (optional default = 0)
	//	72	
	var PolygonMesh1: Int? = null

	//	 Smooth surface M density (optional default = 0)
	//	73	
	var SmoothSurface: Int? = null

	//	 Smooth surface N density (optional default = 0)
	//	74	
	var SmoothSurface1: Int? = null

	//	 Curves and smooth surface type (optional default = 0) integer codes, not bit-coded:, 0 = No smooth surface fitted, 5 = Quadratic B-spline surface, 6 = Cubic B-spline surface, 8 = Bezier surface
	//	75	
	var CurvesAnd: Int? = null

	//	 Extrusion direction (optional default = 0, 0, 1), DXF: X value APP: 3D vectorDXF: Y and Z values of extrusion direction (optional)
	//	210	220	230	
	var ExtrusionDirection: Vector3f? = null

    override fun read(getbfr: DxfLoaderContext) {
    var code = getbfr.get()
    while (true) {
        if (getbfr.codEquals("0")) {//"0"
			do{ code = dxfContext.get() }while(code == "0")
        	when(code) {
				"ENDSEC" -> return
				"SEQEND" -> return
				"VERTEX" -> {
					EntVERTEX(this, true)
					continue
				}
			}
		}
		if (super.readX(code)) {
            when (code) {
			"66" -> {
				ObsoleteFormerly = getbfr.intValue()
			}
			"10" -> {
				if( AlwaysAPP == null ) AlwaysAPP = Vector3f()
				AlwaysAPP?.x = getbfr.floatValue()
			}
			"20" -> {
				if( AlwaysAPP == null ) AlwaysAPP = Vector3f()
				AlwaysAPP?.y = getbfr.floatValue()
			}
			"30" -> {
				if( AlwaysAPP == null ) AlwaysAPP = Vector3f()
				AlwaysAPP?.z = getbfr.floatValue()
			}
			"39" -> {
				ThicknessDefault = getbfr.floatValue()
			}
			"70" -> {
				PolylineFlag = getbfr.intValue()
			}
			"40" -> {
				DefaultStart = getbfr.floatValue()
			}
			"41" -> {
				DefaultEnd = getbfr.floatValue()
			}
			"71" -> {
				PolygonMesh = getbfr.intValue()
			}
			"72" -> {
				PolygonMesh1 = getbfr.intValue()
			}
			"73" -> {
				SmoothSurface = getbfr.intValue()
			}
			"74" -> {
				SmoothSurface1 = getbfr.intValue()
			}
			"75" -> {
				CurvesAnd = getbfr.intValue()
			}
			"210" -> {
				if( ExtrusionDirection == null ) ExtrusionDirection = Vector3f()
				ExtrusionDirection?.x = getbfr.floatValue()
			}
			"220" -> {
				if( ExtrusionDirection == null ) ExtrusionDirection = Vector3f()
				ExtrusionDirection?.y = getbfr.floatValue()
			}
			"230" -> {
				if( ExtrusionDirection == null ) ExtrusionDirection = Vector3f()
				ExtrusionDirection?.z = getbfr.floatValue()
			}
			}
		}
			code = getbfr.get()
	}
	}


    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? {
		var sizeMM: SizeMinMax? = sizeMMParent
		val iter = this.iterator()
		while( iter.hasNext() ) {
			val element = iter.next()
			sizeMM = element.centerObject(sizeMM)
		}
		return sizeMM

	}

    override fun scaleObjectToFit(maxRadiusSqr: Float): Float {
		var mRadiusSqr = maxRadiusSqr
		val iter = this.iterator()
		while( iter.hasNext() ){
			val element = iter.next()
			mRadiusSqr = element.scaleObjectToFit( mRadiusSqr )
		}
		return mRadiusSqr
	}

	override fun collectionConnect(collectionPoint: CollectionPoint): Unit {
//		if( LayerNamenot?.LayerName.equals("shell",true) )
			tt++
		collectionPoint.createNewOneLayer( LayerNamenot )
		val iter = this.iterator()
		while( iter.hasNext() ){
			val element = iter.next()
			element.collectionConnect( collectionPoint )
		}
		//	 Polyline flag (bit-coded default = 0):, 1 = This is a closed polyline (or a polygon mesh closed in the M direction), 2 = Curve-fit vertices have been added, 4 = Spline-fit vertices have been added, 8 = This is a 3D polyline, 16 = This is a 3D polygon mesh,
		//	 32 = The polygon mesh is closed in the N direction, 64 = The polyline is a polyface mesh, 128 = The linetype pattern is generated continuously around the vertices of this polyline
		if( PolylineFlag.flag70(1) ||PolylineFlag.flag70(32))
			collectionPoint.closeConnVertexLoop()

//		endSiz += this.size
//		println(" XXXXXXXXXX $tt  + ${this.size} + $endSiz")
//		if( LayerNamenot?.LayerName.equals("AAA1",true) ) println(" XXXShellX $tt  + ${this.size} + $endSiz")
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
		 var tt = 0
		 //	 Subclass marker (AcDb2dPolyline or AcDb3dPolyline)
		//	100
		const val AcDb2dPolylineAcDb3dPolyline: String = "AcDb2dPolyline or AcDb3dPolyline"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( ObsoleteFormerly != null) sb.append( "\n 66\n"+ObsoleteFormerly )
		if( AlwaysAPP != null) sb.append( "\n 10\n"+AlwaysAPP?.x+"\n 20\n"+AlwaysAPP?.y+"\n 30\n"+AlwaysAPP?.z )
		if( ThicknessDefault != null) sb.append( "\n 39\n"+ThicknessDefault )
		if( PolylineFlag != null) sb.append( "\n 70\n"+PolylineFlag )
		if( DefaultStart != null) sb.append( "\n 40\n"+DefaultStart )
		if( DefaultEnd != null) sb.append( "\n 41\n"+DefaultEnd )
		if( PolygonMesh != null) sb.append( "\n 71\n"+PolygonMesh )
		if( PolygonMesh1 != null) sb.append( "\n 72\n"+PolygonMesh1 )
		if( SmoothSurface != null) sb.append( "\n 73\n"+SmoothSurface )
		if( SmoothSurface1 != null) sb.append( "\n 74\n"+SmoothSurface1 )
		if( CurvesAnd != null) sb.append( "\n 75\n"+CurvesAnd )
		if( ExtrusionDirection != null) sb.append( "\n 210\n"+ExtrusionDirection?.x+"\n 220\n"+ExtrusionDirection?.y+"\n 230\n"+ExtrusionDirection?.z )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nPOLYLINE")
			sbX.append( sb)
		}

		 return sbX
	}}