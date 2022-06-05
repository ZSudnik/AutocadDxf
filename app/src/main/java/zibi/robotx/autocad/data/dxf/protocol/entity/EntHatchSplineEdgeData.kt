package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector2f

class EntHatchSplineEdgeData(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Degree
	//	94	
	var Degree: Int? = null

	//	 Rational
	//	73	
	var Rational: Int? = null

	//	 Periodic
	//	74	
	var Periodic: Int? = null

	//	 Number of knots
	//	95	
	var NumberKnots: Int? = null

	//	 Number of control points
	//	96	
	var NumberControl: Int? = null

	//	 Knot values (multiple entries)
	//	40	
	var KnotValues: Float? = null

	//	 Control point (in OCS), DXF: X value APP: 2D pointDXF: Y value of control point (in OCS)
	//	10	20	
	var ControlPoint: Vector2f? = null

	//	 Weights (optional, default = 1)
	//	42	
	var WeightsDefault: Float? = null

	//	 Number of fit data
	//	97	
	var NumberFit: Int? = null

	//	 Fit datum (in OCS), DXF: X value APP: 2D pointDXF: Y value of fit datum (in OCS)
	//	11	21	
	var FitDatum: Vector2f? = null

	//	 Start tangent, DXF: X value APP: 2D vectorDXF: Y value of start tangent (in OCS)
	//	12	22	
	var StartTangent: Vector2f? = null

	//	 End tangent, DXF: X value APP: 2D vectorDXF: Y value of end tangent (in OCS)
	//	13	23	
	var EndTangent: Vector2f? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"94" -> {
				Degree = getbfr.intValue()
			}
			"73" -> {
				Rational = getbfr.intValue()
			}
			"74" -> {
				Periodic = getbfr.intValue()
			}
			"95" -> {
				NumberKnots = getbfr.intValue()
			}
			"96" -> {
				NumberControl = getbfr.intValue()
			}
			"40" -> {
				KnotValues = getbfr.floatValue()
			}
			"10" -> {
				if( ControlPoint == null ) ControlPoint = Vector2f()
				ControlPoint?.x = getbfr.floatValue()
			}
			"20" -> {
				if( ControlPoint == null ) ControlPoint = Vector2f()
				ControlPoint?.y = getbfr.floatValue()
			}
			"42" -> {
				WeightsDefault = getbfr.floatValue()
			}
			"97" -> {
				NumberFit = getbfr.intValue()
			}
			"11" -> {
				if( FitDatum == null ) FitDatum = Vector2f()
				FitDatum?.x = getbfr.floatValue()
			}
			"21" -> {
				if( FitDatum == null ) FitDatum = Vector2f()
				FitDatum?.y = getbfr.floatValue()
			}
			"12" -> {
				if( StartTangent == null ) StartTangent = Vector2f()
				StartTangent?.x = getbfr.floatValue()
			}
			"22" -> {
				if( StartTangent == null ) StartTangent = Vector2f()
				StartTangent?.y = getbfr.floatValue()
			}
			"13" -> {
				if( EndTangent == null ) EndTangent = Vector2f()
				EndTangent?.x = getbfr.floatValue()
			}
			"23" -> {
				if( EndTangent == null ) EndTangent = Vector2f()
				EndTangent?.y = getbfr.floatValue()
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

	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( Degree != null) sb.append( "\n 94\n"+Degree )
		if( Rational != null) sb.append( "\n 73\n"+Rational )
		if( Periodic != null) sb.append( "\n 74\n"+Periodic )
		if( NumberKnots != null) sb.append( "\n 95\n"+NumberKnots )
		if( NumberControl != null) sb.append( "\n 96\n"+NumberControl )
		if( KnotValues != null) sb.append( "\n 40\n"+KnotValues )
		if( ControlPoint != null) sb.append( "\n 10\n"+ControlPoint?.x+"\n 20\n"+ControlPoint?.y )
		if( WeightsDefault != null) sb.append( "\n 42\n"+WeightsDefault )
		if( NumberFit != null) sb.append( "\n 97\n"+NumberFit )
		if( FitDatum != null) sb.append( "\n 11\n"+FitDatum?.x+"\n 21\n"+FitDatum?.y )
		if( StartTangent != null) sb.append( "\n 12\n"+StartTangent?.x+"\n 22\n"+StartTangent?.y )
		if( EndTangent != null) sb.append( "\n 13\n"+EndTangent?.x+"\n 23\n"+EndTangent?.y )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nHatchSplineEdgeData")
			sbX.append( sb)
		}

		 return sbX
	}}