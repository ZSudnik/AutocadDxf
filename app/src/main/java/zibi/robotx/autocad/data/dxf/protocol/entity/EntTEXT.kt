package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntTEXT(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Thickness (optional default = 0)
	//	39	
	var ThicknessDefault: Float? = null

	//	 First alignment point (in OCS), DXF: X value APP: 3D pointDXF: Y and Z values of first alignment point (in OCS)
	//	10	20	30	
	var FirstAlignment: Vector3f? = null

	//	 Text height
	//	40	
	var TextHeight: Float? = null

	//	 Default value (the string itself)
	//	1	
	var DefaultValue: String? = null

	//	 Text rotation (optional default = 0)
	//	50	
	var TextRotation: Float? = null

	//	 Relative X scale factor width (optional default = 1),  This value is also adjusted when fit-type text is used
	//	41	
	var RelativeX: Float? = null

	//	 Oblique angle (optional default = 0)
	//	51	
	var ObliqueAngle: Float? = null

	//	 Text style name (optional, default = STANDARD)
	//	7	
	var TextStyle: String? = null

	//	 Text generation flags (optional, default = 0): 2 = Text is backward (mirrored in X), 4 = Text is upside down (mirrored in Y)
	//	71	
	var TextGeneration: Int? = null

	//	 Horizontal text justification type (optional, default = 0) integer codes (not bit-coded): 0 = Left,  1= Center, , 2 = Right, , 3 = Aligned (if vertical alignment = 0), , 4 = Middle (if vertical alignment = 0), , 5 = Fit (if vertical alignment = 0), , See the Group 72 and 73 integer codes table for clarification
	//	72	
	var HorizontalText: Int? = null

	//	 Second alignment point (in OCS) (optional),  DXF: X value APP: 3D point,  This value is meaningful only if the value of a 72 or 73 group is nonzero (if the justification is anything other than baseline/left)DXF: Y and Z values of second alignment point (in OCS) (optional)
	//	11	21	31	
	var SecondAlignment: Vector3f? = null

	//	 Extrusion direction (optional default = 0, 0, 1),  DXF: X value APP: 3D vectorDXF: Y and Z values of extrusion direction (optional)
	//	210	220	230	
	var ExtrusionDirection: Vector3f? = null

	//	 Vertical text justification type (optional, default = 0): integer codes (not bit-coded): 0 = Baseline, 1 = Bottom , 2 = Middle, 3 = Top
	//	73	
	var VerticalText: Int? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"39" -> {
				ThicknessDefault = getbfr.floatValue()
			}
			"10" -> {
				if( FirstAlignment == null ) FirstAlignment = Vector3f()
				FirstAlignment?.x = getbfr.floatValue()
			}
			"20" -> {
				if( FirstAlignment == null ) FirstAlignment = Vector3f()
				FirstAlignment?.y = getbfr.floatValue()
			}
			"30" -> {
				if( FirstAlignment == null ) FirstAlignment = Vector3f()
				FirstAlignment?.z = getbfr.floatValue()
			}
			"40" -> {
				TextHeight = getbfr.floatValue()
			}
			"1" -> {
				DefaultValue = getbfr.stringValue()
			}
			"50" -> {
				TextRotation = getbfr.floatValue()
			}
			"41" -> {
				RelativeX = getbfr.floatValue()
			}
			"51" -> {
				ObliqueAngle = getbfr.floatValue()
			}
			"7" -> {
				TextStyle = getbfr.stringValue()
			}
			"71" -> {
				TextGeneration = getbfr.intValue()
			}
			"72" -> {
				HorizontalText = getbfr.intValue()
			}
			"11" -> {
				if( SecondAlignment == null ) SecondAlignment = Vector3f()
				SecondAlignment?.x = getbfr.floatValue()
			}
			"21" -> {
				if( SecondAlignment == null ) SecondAlignment = Vector3f()
				SecondAlignment?.y = getbfr.floatValue()
			}
			"31" -> {
				if( SecondAlignment == null ) SecondAlignment = Vector3f()
				SecondAlignment?.z = getbfr.floatValue()
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
			"73" -> {
				VerticalText = getbfr.intValue()
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


	//	 Subclass marker (AcDbText)
	//	100	
	const val AcDbText: String = "AcDbText"

	//	 Subclass marker (AcDbText)
	//	100	
	const val AcDbText1: String = "AcDbText"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( ThicknessDefault != null) sb.append( "\n 39\n"+ThicknessDefault )
		if( FirstAlignment != null) sb.append( "\n 10\n"+FirstAlignment?.x+"\n 20\n"+FirstAlignment?.y+"\n 30\n"+FirstAlignment?.z )
		if( TextHeight != null) sb.append( "\n 40\n"+TextHeight )
		if( DefaultValue != null) sb.append( "\n 1\n"+DefaultValue )
		if( TextRotation != null) sb.append( "\n 50\n"+TextRotation )
		if( RelativeX != null) sb.append( "\n 41\n"+RelativeX )
		if( ObliqueAngle != null) sb.append( "\n 51\n"+ObliqueAngle )
		if( TextStyle != null) sb.append( "\n 7\n"+TextStyle )
		if( TextGeneration != null) sb.append( "\n 71\n"+TextGeneration )
		if( HorizontalText != null) sb.append( "\n 72\n"+HorizontalText )
		if( SecondAlignment != null) sb.append( "\n 11\n"+SecondAlignment?.x+"\n 21\n"+SecondAlignment?.y+"\n 31\n"+SecondAlignment?.z )
		if( ExtrusionDirection != null) sb.append( "\n 210\n"+ExtrusionDirection?.x+"\n 220\n"+ExtrusionDirection?.y+"\n 230\n"+ExtrusionDirection?.z )
		if( VerticalText != null) sb.append( "\n 73\n"+VerticalText )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nTEXT")
			sbX.append( sb)
		}

		 return sbX
	}}