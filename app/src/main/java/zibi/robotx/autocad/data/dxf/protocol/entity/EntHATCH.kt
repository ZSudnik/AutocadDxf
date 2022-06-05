package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector2f
import javax.vecmath.Vector3f

class EntHATCH(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Elevation point (in OCS), DXF: X value = 0 APP: 3D point (X and Y always equal 0, Z represents the elevation)DXF: Y and Z values of elevation point (in OCS), Y value = 0, Z represents the elevation
	//	10	20	30	
	var ElevationPoint: Vector3f? = null

	//	 Extrusion direction (optional default = 0, 0, 1), DXF: X value APP: 3D vectorDXF: Y and Z values of extrusion direction
	//	210	220	230	
	var ExtrusionDirection: Vector3f? = null

	//	 Hatch pattern name
	//	2	
	var HatchPattern: String? = null

	//	 Solid fill flag (0 = pattern fill 1 = solid fill) for MPolygon, the version of MPolygon
	//	70	
	var SolidFill: Int? = null

	//	 For MPolygon, pattern fill color as the ACI
	//	63	
	var ForMPolygon: Int? = null

	//	 Associativity flag (0 = non-associative 1 = associative) for MPolygon, solid-fill flag (0 = lacks solid fill 1 = has solid fill)
	//	71	
	var AssociativityFlag: Int? = null

	//	 Number of boundary paths (loops)
	//	91	
	var NumberBoundary: Int? = null

	//	 Hatch style:, 0 = Hatch odd parity area (Normal style), 1 = Hatch outermost area only (Outer style), 2 = Hatch through entire area (Ignore style)
	//	75	
	var HatchStyle: Int? = null

	//	 Hatch pattern type:, 0 = User-defined, 1 = Predefined, 2 = Custom
	//	76	
	var HatchPattern1: Int? = null

	//	 Hatch pattern angle (pattern fill only)
	//	52	
	var HatchPattern2: Float? = null

	//	 Hatch pattern scale or spacing (pattern fill only)
	//	41	
	var HatchPattern3: Float? = null

	//	 For MPolygon, boundary annotation flag:, 0 = boundary is not an annotated boundary, 1 = boundary is an annotated boundary
	//	73	
	var ForMPolygon1: Int? = null

	//	 Hatch pattern double flag (pattern fill only):, 0 = not double, 1 = double
	//	77	
	var HatchPattern4: Int? = null

	//	 Number of pattern definition lines
	//	78	
	var NumberPattern: Int? = null

	//	 Pixel size used to determine the density to perform various intersection and ray casting operations in hatch pattern computation for associative hatches and hatches created with the Flood method of hatching
	//	47	
	var PixelSize: Float? = null

	//	 Number of seed points
	//	98	
	var NumberSeed: Int? = null

	//	 For MPolygon, offset vector
	//	11	
	var ForMPolygon2: Float? = null

	//	 For MPolygon, number of degenerate boundary paths (loops), where a degenerate boundary path is a border that is ignored by the hatch
	//	99	
	var ForMPolygon3: Int? = null

	//	 Seed point (in OCS), DXF: X value APP: 2D point (multiple entries)DXF: Y value of seed point (in OCS) (multiple entries)
	//	10	20	
	var SeedPoint: Vector2f? = null

	//	 Indicates solid hatch or gradient if solid hatch, the values for the remaining codes are ignored but must be present. Optional if code 450 is in the file, then the following codes must be in the file: 451, 452, 453, 460, 461, 462, and 470. If code 450 is not in the file, then the following codes must not be in the file: 451, 452, 453, 460, 461, 462, and 470, 0 = Solid hatch, 1 = Gradient
	//	450	
	var IndicatesSolid: Int? = null

	//	 Zero is reserved for future use
	//	451	
	var ZeroIs: Int? = null

	//	 Records how colors were defined and is used only by dialog code:, 0 = Two-color gradient, 1 = Single-color gradient
	//	452	
	var RecordsHow: Int? = null

	//	 Number of colors:, 0 = Solid hatch, 2 = Gradient
	//	453	
	var NumberColors: Int? = null

	//	 Rotation angle in radians for gradients (default = 0, 0)
	//	460	
	var RotationAngle: Float? = null

	//	 Gradient definition corresponds to the Centered option on the Gradient Tab of the Boundary Hatch and Fill dialog box. Each gradient has two definitions, shifted and non-shifted. A Shift value describes the blend of the two definitions that should be used. A value of 0.0 means only the non-shifted version should be used, and a value of 1.0 means that only the shifted version should be used.
	//	461	
	var GradientDefinition: Float? = null

	//	 Color tint value used by dialog code (default = 0, 0 range is 0.0 to 1.0). The color tint value is a gradient color and controls the degree of tint in the dialog when the Hatch group code 452 is set to 1.
	//	462	
	var ColorTint: Float? = null

	//	 Reserved for future use:, 0 = First value, 1 = Second value
	//	463	
	var ReservedFor: Float? = null

	//	 String (default = LINEAR)
	//	470	
	var DefaultLINEAR: String? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"10" -> {
				if( ElevationPoint == null ) ElevationPoint = Vector3f()
				ElevationPoint?.x = getbfr.floatValue()
			}
			"20" -> {
				if( ElevationPoint == null ) ElevationPoint = Vector3f()
				ElevationPoint?.y = getbfr.floatValue()
			}
			"30" -> {
				if( ElevationPoint == null ) ElevationPoint = Vector3f()
				ElevationPoint?.z = getbfr.floatValue()
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
			"2" -> {
				HatchPattern = getbfr.stringValue()
			}
			"70" -> {
				SolidFill = getbfr.intValue()
			}
			"63" -> {
				ForMPolygon = getbfr.intValue()
			}
			"71" -> {
				AssociativityFlag = getbfr.intValue()
			}
			"91" -> {
				NumberBoundary = getbfr.intValue()
			}
			"75" -> {
				HatchStyle = getbfr.intValue()
			}
			"76" -> {
				HatchPattern1 = getbfr.intValue()
			}
			"52" -> {
				HatchPattern2 = getbfr.floatValue()
			}
			"41" -> {
				HatchPattern3 = getbfr.floatValue()
			}
			"73" -> {
				ForMPolygon1 = getbfr.intValue()
			}
			"77" -> {
				HatchPattern4 = getbfr.intValue()
			}
			"78" -> {
				NumberPattern = getbfr.intValue()
			}
			"47" -> {
				PixelSize = getbfr.floatValue()
			}
			"98" -> {
				NumberSeed = getbfr.intValue()
			}
			"11" -> {
				ForMPolygon2 = getbfr.floatValue()
			}
			"99" -> {
				ForMPolygon3 = getbfr.intValue()
			}
			"10" -> {
				if( SeedPoint == null ) SeedPoint = Vector2f()
				SeedPoint?.x = getbfr.floatValue()
			}
			"20" -> {
				if( SeedPoint == null ) SeedPoint = Vector2f()
				SeedPoint?.y = getbfr.floatValue()
			}
			"450" -> {
				IndicatesSolid = getbfr.intValue()
			}
			"451" -> {
				ZeroIs = getbfr.intValue()
			}
			"452" -> {
				RecordsHow = getbfr.intValue()
			}
			"453" -> {
				NumberColors = getbfr.intValue()
			}
			"460" -> {
				RotationAngle = getbfr.floatValue()
			}
			"461" -> {
				GradientDefinition = getbfr.floatValue()
			}
			"462" -> {
				ColorTint = getbfr.floatValue()
			}
			"463" -> {
				ReservedFor = getbfr.floatValue()
			}
			"470" -> {
				DefaultLINEAR = getbfr.stringValue()
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


	//	 Subclass marker (AcDbHatch)
	//	100	
	const val AcDbHatch: String = "AcDbHatch"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( ElevationPoint != null) sb.append( "\n 10\n"+ElevationPoint?.x+"\n 20\n"+ElevationPoint?.y+"\n 30\n"+ElevationPoint?.z )
		if( ExtrusionDirection != null) sb.append( "\n 210\n"+ExtrusionDirection?.x+"\n 220\n"+ExtrusionDirection?.y+"\n 230\n"+ExtrusionDirection?.z )
		if( HatchPattern != null) sb.append( "\n 2\n"+HatchPattern )
		if( SolidFill != null) sb.append( "\n 70\n"+SolidFill )
		if( ForMPolygon != null) sb.append( "\n 63\n"+ForMPolygon )
		if( AssociativityFlag != null) sb.append( "\n 71\n"+AssociativityFlag )
		if( NumberBoundary != null) sb.append( "\n 91\n"+NumberBoundary )
		if( HatchStyle != null) sb.append( "\n 75\n"+HatchStyle )
		if( HatchPattern1 != null) sb.append( "\n 76\n"+HatchPattern1 )
		if( HatchPattern2 != null) sb.append( "\n 52\n"+HatchPattern2 )
		if( HatchPattern3 != null) sb.append( "\n 41\n"+HatchPattern3 )
		if( ForMPolygon1 != null) sb.append( "\n 73\n"+ForMPolygon1 )
		if( HatchPattern4 != null) sb.append( "\n 77\n"+HatchPattern4 )
		if( NumberPattern != null) sb.append( "\n 78\n"+NumberPattern )
		if( PixelSize != null) sb.append( "\n 47\n"+PixelSize )
		if( NumberSeed != null) sb.append( "\n 98\n"+NumberSeed )
		if( ForMPolygon2 != null) sb.append( "\n 11\n"+ForMPolygon2 )
		if( ForMPolygon3 != null) sb.append( "\n 99\n"+ForMPolygon3 )
		if( SeedPoint != null) sb.append( "\n 10\n"+SeedPoint?.x+"\n 20\n"+SeedPoint?.y )
		if( IndicatesSolid != null) sb.append( "\n 450\n"+IndicatesSolid )
		if( ZeroIs != null) sb.append( "\n 451\n"+ZeroIs )
		if( RecordsHow != null) sb.append( "\n 452\n"+RecordsHow )
		if( NumberColors != null) sb.append( "\n 453\n"+NumberColors )
		if( RotationAngle != null) sb.append( "\n 460\n"+RotationAngle )
		if( GradientDefinition != null) sb.append( "\n 461\n"+GradientDefinition )
		if( ColorTint != null) sb.append( "\n 462\n"+ColorTint )
		if( ReservedFor != null) sb.append( "\n 463\n"+ReservedFor )
		if( DefaultLINEAR != null) sb.append( "\n 470\n"+DefaultLINEAR )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nHATCH")
			sbX.append( sb)
		}

		 return sbX
	}}