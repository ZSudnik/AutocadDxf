package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class EntMESH(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Version number
	//	71	
	var VersionNumber: Int? = null

	//	 Blend Crease property, 0 = Turn off, 1 = Turn on
	//	72	
	var BlendCrease: Int? = null

	//	 Number of subdivision level
	//	91	
	var NumberSubdivision: Int? = null

	//	 Vertex count of level 0
	//	92	
	var VertexCount: Int? = null

	//	 Vertex position
	//	10	
	var VertexPosition: Float? = null

	//	 Size of face list of level 0
	//	93	
	var SizeFace: Int? = null

	//	 Face list item
	//	90	
	var FaceList: Int? = null

	//	 Edge count of level 0
	//	94	
	var EdgeCount: Int? = null

	//	 Vertex index of each edge
	//	90	
	var VertexIndex: Int? = null

	//	 Edge crease count of level 0
	//	95	
	var EdgeCrease: Int? = null

	//	 Edge create value
	//	140	
	var EdgeCreate: Float? = null

	//	 Count of sub-entity which property has been overridden
	//	90	
	var CountSubentity: Int? = null

	//	 Sub-entity marker
	//	91	
	var Subentity: Int? = null

	//	 Count of property was overridden
	//	92	
	var CountProperty: Int? = null

	//	 Property type, 0 = Color, 1 = Material, 2 = Transparency, 3 = Material mapper
	//	90	
	var PropertyType: Int? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"71" -> {
				VersionNumber = getbfr.intValue()
			}
			"72" -> {
				BlendCrease = getbfr.intValue()
			}
			"91" -> {
				NumberSubdivision = getbfr.intValue()
			}
			"92" -> {
				VertexCount = getbfr.intValue()
			}
			"10" -> {
				VertexPosition = getbfr.floatValue()
			}
			"93" -> {
				SizeFace = getbfr.intValue()
			}
			"90" -> {
				FaceList = getbfr.intValue()
			}
			"94" -> {
				EdgeCount = getbfr.intValue()
			}
			"90" -> {
				VertexIndex = getbfr.intValue()
			}
			"95" -> {
				EdgeCrease = getbfr.intValue()
			}
			"140" -> {
				EdgeCreate = getbfr.floatValue()
			}
			"90" -> {
				CountSubentity = getbfr.intValue()
			}
			"91" -> {
				Subentity = getbfr.intValue()
			}
			"92" -> {
				CountProperty = getbfr.intValue()
			}
			"90" -> {
				PropertyType = getbfr.intValue()
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


	//	 Subclass marker (AcDbSubDMesh)
	//	100	
	const val AcDbSubDMesh: String = "AcDbSubDMesh"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( VersionNumber != null) sb.append( "\n 71\n"+VersionNumber )
		if( BlendCrease != null) sb.append( "\n 72\n"+BlendCrease )
		if( NumberSubdivision != null) sb.append( "\n 91\n"+NumberSubdivision )
		if( VertexCount != null) sb.append( "\n 92\n"+VertexCount )
		if( VertexPosition != null) sb.append( "\n 10\n"+VertexPosition )
		if( SizeFace != null) sb.append( "\n 93\n"+SizeFace )
		if( FaceList != null) sb.append( "\n 90\n"+FaceList )
		if( EdgeCount != null) sb.append( "\n 94\n"+EdgeCount )
		if( VertexIndex != null) sb.append( "\n 90\n"+VertexIndex )
		if( EdgeCrease != null) sb.append( "\n 95\n"+EdgeCrease )
		if( EdgeCreate != null) sb.append( "\n 140\n"+EdgeCreate )
		if( CountSubentity != null) sb.append( "\n 90\n"+CountSubentity )
		if( Subentity != null) sb.append( "\n 91\n"+Subentity )
		if( CountProperty != null) sb.append( "\n 92\n"+CountProperty )
		if( PropertyType != null) sb.append( "\n 90\n"+PropertyType )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nMESH")
			sbX.append( sb)
		}

		 return sbX
	}}