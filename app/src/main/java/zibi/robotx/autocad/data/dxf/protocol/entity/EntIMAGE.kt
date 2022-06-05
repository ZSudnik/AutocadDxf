package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector2f
import javax.vecmath.Vector3f

class EntIMAGE(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Class version
	//	90	
	var ClassVersion: Int? = null

	//	 Insertion point (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of insertion point (in WCS)
	//	10	20	30	
	var InsertionPoint: Vector3f? = null

	//	 U-vector of a single pixel (points along the visual bottom of the image, starting at the insertion point) (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values U-vector (in WCS)
	//	11	21	31	
	var UvectorA: Vector3f? = null

	//	 V-vector of a single pixel (points along the visual left side of the image, starting at the insertion point) (in WCS), DXF: X value APP: 3D pointDXF: Y and Z values of V-vector (in WCS)
	//	12	22	32	
	var VvectorA: Vector3f? = null

	//	 Image size in pixels, DXF: U value APP: 2D point (U and V values)DXF: V value of image size in pixels
	//	13	23	
	var ImageSize: Vector2f? = null

	//	 Hard reference to imagedef object
	//	340	
	var HardReference: String? = null

	//	 Image display properties:, 1 = Show image, 2 = Show image when not aligned with screen, 4 = Use clipping boundary, 8 = Transparency is on
	//	70	
	var ImageDisplay: Int? = null

	//	 Clipping state:, 0 = Off, 1 = On
	//	280	
	var ClippingState: Int? = null

	//	 Brightness value (0-100 default = 50)
	//	281	
	var BrightnessValue: Int? = null

	//	 Contrast value (0-100 default = 50)
	//	282	
	var ContrastValue: Int? = null

	//	 Fade value (0-100 default = 0)
	//	283	
	var FadeValue: Int? = null

	//	 Hard reference to imagedef_reactor object
	//	360	
	var HardReference1: String? = null

	//	 Clipping boundary type. 1 = Rectangular 2 = Polygonal
	//	71	
	var ClippingBoundary: Int? = null

	//	 Number of clip boundary vertices that follow
	//	91	
	var NumberClip: Int? = null

	//	 Clip boundary vertex (in OCS), DXF: X value APP: 2D point (multiple entries), NOTE 1) For rectangular clip boundary type, two opposite corners must be specified. Default is (-0.5,-0.5), (size.x-0.5, size.y-0.5). 2) For polygonal clip boundary type, three or more vertices must be specified. Polygonal vertices must be listed sequentiallyDXF: Y value of clip boundary vertex (in OCS) (multiple entries)
	//	14	24	
	var ClipBoundary: Vector2f? = null

	//	 Clip Mode:, 0 = Outside Mode, 1 = Inside Mode
	//	290	
	var ClipMode: Boolean? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"90" -> {
				ClassVersion = getbfr.intValue()
			}
			"10" -> {
				if( InsertionPoint == null ) InsertionPoint = Vector3f()
				InsertionPoint?.x = getbfr.floatValue()
			}
			"20" -> {
				if( InsertionPoint == null ) InsertionPoint = Vector3f()
				InsertionPoint?.y = getbfr.floatValue()
			}
			"30" -> {
				if( InsertionPoint == null ) InsertionPoint = Vector3f()
				InsertionPoint?.z = getbfr.floatValue()
			}
			"11" -> {
				if( UvectorA == null ) UvectorA = Vector3f()
				UvectorA?.x = getbfr.floatValue()
			}
			"21" -> {
				if( UvectorA == null ) UvectorA = Vector3f()
				UvectorA?.y = getbfr.floatValue()
			}
			"31" -> {
				if( UvectorA == null ) UvectorA = Vector3f()
				UvectorA?.z = getbfr.floatValue()
			}
			"12" -> {
				if( VvectorA == null ) VvectorA = Vector3f()
				VvectorA?.x = getbfr.floatValue()
			}
			"22" -> {
				if( VvectorA == null ) VvectorA = Vector3f()
				VvectorA?.y = getbfr.floatValue()
			}
			"32" -> {
				if( VvectorA == null ) VvectorA = Vector3f()
				VvectorA?.z = getbfr.floatValue()
			}
			"13" -> {
				if( ImageSize == null ) ImageSize = Vector2f()
				ImageSize?.x = getbfr.floatValue()
			}
			"23" -> {
				if( ImageSize == null ) ImageSize = Vector2f()
				ImageSize?.y = getbfr.floatValue()
			}
			"340" -> {
				HardReference = getbfr.stringValue()
			}
			"70" -> {
				ImageDisplay = getbfr.intValue()
			}
			"280" -> {
				ClippingState = getbfr.intValue()
			}
			"281" -> {
				BrightnessValue = getbfr.intValue()
			}
			"282" -> {
				ContrastValue = getbfr.intValue()
			}
			"283" -> {
				FadeValue = getbfr.intValue()
			}
			"360" -> {
				HardReference1 = getbfr.stringValue()
			}
			"71" -> {
				ClippingBoundary = getbfr.intValue()
			}
			"91" -> {
				NumberClip = getbfr.intValue()
			}
			"14" -> {
				if( ClipBoundary == null ) ClipBoundary = Vector2f()
				ClipBoundary?.x = getbfr.floatValue()
			}
			"24" -> {
				if( ClipBoundary == null ) ClipBoundary = Vector2f()
				ClipBoundary?.y = getbfr.floatValue()
			}
			"290" -> {
				ClipMode = getbfr.booleanValue()
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


	//	 Subclass marker (AcDbRasterImage)
	//	100	
	const val AcDbRasterImage: String = "AcDbRasterImage"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( ClassVersion != null) sb.append( "\n 90\n"+ClassVersion )
		if( InsertionPoint != null) sb.append( "\n 10\n"+InsertionPoint?.x+"\n 20\n"+InsertionPoint?.y+"\n 30\n"+InsertionPoint?.z )
		if( UvectorA != null) sb.append( "\n 11\n"+UvectorA?.x+"\n 21\n"+UvectorA?.y+"\n 31\n"+UvectorA?.z )
		if( VvectorA != null) sb.append( "\n 12\n"+VvectorA?.x+"\n 22\n"+VvectorA?.y+"\n 32\n"+VvectorA?.z )
		if( ImageSize != null) sb.append( "\n 13\n"+ImageSize?.x+"\n 23\n"+ImageSize?.y )
		if( HardReference != null) sb.append( "\n 340\n"+HardReference )
		if( ImageDisplay != null) sb.append( "\n 70\n"+ImageDisplay )
		if( ClippingState != null) sb.append( "\n 280\n"+ClippingState )
		if( BrightnessValue != null) sb.append( "\n 281\n"+BrightnessValue )
		if( ContrastValue != null) sb.append( "\n 282\n"+ContrastValue )
		if( FadeValue != null) sb.append( "\n 283\n"+FadeValue )
		if( HardReference1 != null) sb.append( "\n 360\n"+HardReference1 )
		if( ClippingBoundary != null) sb.append( "\n 71\n"+ClippingBoundary )
		if( NumberClip != null) sb.append( "\n 91\n"+NumberClip )
		if( ClipBoundary != null) sb.append( "\n 14\n"+ClipBoundary?.x+"\n 24\n"+ClipBoundary?.y )
		if( ClipMode != null) sb.append( "\n 290\n"+ClipMode )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nIMAGE")
			sbX.append( sb)
		}

		 return sbX
	}}