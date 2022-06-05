package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.protocol.BlkBlock
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntINSERT(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Variable attributes-follow flag (optional default = 0) if the value of attributes-follow flag is 1, a series of attribute entities is expected to follow the insert, terminated by a seqend entity
	//	66	
	var VariableAttributesfollow: Int? = null

	//	 Block name
	//	2	
	var BlockName: BlkBlock? = null

	//	 Insertion point (in OCS), DXF: X value APP: 3D pointDXF: Y and Z values of insertion point (in OCS)
	//	10	20	30	
	var InsertionPoint: Vector3f? = null

	//	 X scale factor (optional default = 1)
	//	41	
	var XScale: Float? = null

	//	 Y scale factor (optional default = 1)
	//	42	
	var YScale: Float? = null

	//	 Z scale factor (optional default = 1)
	//	43	
	var ZScale: Float? = null

	//	 Rotation angle (optional default = 0)
	//	50	
	var RotationAngle: Float? = null

	//	 Column count (optional default = 1)
	//	70	
	var ColumnCount: Int? = null

	//	 Row count (optional default = 1)
	//	71	
	var RowCount: Int? = null

	//	 Column spacing (optional default = 0)
	//	44	
	var ColumnSpacing: Float? = null

	//	 Row spacing (optional default = 0)
	//	45	
	var RowSpacing: Float? = null

	//	 Extrusion direction (optional default = 0, 0, 1), DXF: X value APP: 3D vectorDXF: Y and Z values of extrusion direction (optional)
	//	210	220	230	
	var ExtrusionDirection: Vector3f? = null

    override fun read(getbfr: DxfLoaderContext) {
	var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"66" -> {
				VariableAttributesfollow = getbfr.intValue()
			}
			"2" -> {
				BlockName = dxfContext.blockValue(this)
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
			"41" -> {
				XScale = getbfr.floatValue()
			}
			"42" -> {
				YScale = getbfr.floatValue()
			}
			"43" -> {
				ZScale = getbfr.floatValue()
			}
			"50" -> {
				RotationAngle = getbfr.floatValue()
			}
			"70" -> {
				ColumnCount = getbfr.intValue()
			}
			"71" -> {
				RowCount = getbfr.intValue()
			}
			"44" -> {
				ColumnSpacing = getbfr.floatValue()
			}
			"45" -> {
				RowSpacing = getbfr.floatValue()
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
		cod = getbfr.get()
	}
	}

	var interpolatePoints: MutableList<List<Vector3f>>? = null
	private fun checkInterpolate(){
		if( interpolatePoints == null ) {
			if( BlockName == null ) {interpolatePoints = mutableListOf(); return}
			val transPoints = interpolateInsert(InsertionPoint, XScale, YScale, ZScale, RotationAngle,
					ColumnCount, RowCount,	ColumnSpacing, RowSpacing, ExtrusionDirection )
			if( ExtrusionDirection != null)
				ColumnCount = 2

			interpolatePoints = mutableListOf()
//			var scaleX = XScale ?: 1f
//			var scaleY = YScale ?: 1f
//			var scaleZ = ZScale ?: 1f
			var insertionPoint = InsertionPoint ?: Vector3f()
			val iter = BlockName!!.iterator()
			while( iter.hasNext() ) {
				val element = iter.next()
				//TODO add EntPOLYLINE EntHATCH EntPOINT EntMTEXT and changing all 3 dimension
				val points: List<Vector3f> = when( element ){
					is EntLINE -> if(element.StartPoint == null || element.EndpointWCS==null) listOf() else
						listOf( element.StartPoint!! , element.EndpointWCS!!)
					is EntLWPOLYLINE -> element.listControlPoint
					is EntPOLYLINE -> listOf() // need work
					is EntCIRCLE ->	element.checkInterpolate() ?: listOf()
					is EntELLIPSE -> element.checkInterpolate() ?: listOf()
					is EntARC -> element.checkInterpolate() ?: listOf()
					is EntSPLINE -> element.checkInterpolate() ?: listOf()
					is EntINSERT -> { //by AutoCAD "Block definitions are never nested"
//						scaleX *= element.XScale ?: 1f
//						scaleY *= element.YScale ?: 1f
//						insertionPoint.add( element.InsertionPoint)
						listOf()
					}
					else -> listOf()
				}
				interpolatePoints?.add(points)
			}

//			InsertionPoint?.scaleXYZ(scaleX , scaleY, 1f)
//			InsertionPoint?.add( Vector3f( 10.5879501577632f, 23.76315346395927f, 0.0f))
//			InsertionPoint?.scaleXYZ(0.075f, 0.075f, 0.075f)
//			InsertionPoint?.scaleXYZ(XScale!! *0.075f , YScale!! * 0.075f, ZScale)
//			InsertionPoint?.add( Vector3f( 10.5879501577632f, 23.76315346395927f, 0.0f))
			//TODO bad transformation ????
			for( list in interpolatePoints!!) {
				for (point in list) {
					point.scaleXYZ(XScale , YScale, ZScale)
					point.add(InsertionPoint)
				}
			}
			//TODO bad rotation ????
			if( RotationAngle != null && RotationAngle != 0f && InsertionPoint!= null){
//				var insertionPoint = InsertionPoint ?: Vector3f()
//				insertionPoint.scaleXYZ(XScale , YScale, ZScale )
				rotate2Dpoint( insertionPoint, RotationAngle!!, interpolatePoints!!)
			}
		}
	}



	override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? {
		checkInterpolate()
		var tSizeMM: SizeMinMax? = null
		for( list in interpolatePoints!!) {
			tSizeMM =  sizeMMParent?.findMinMax(list)
				?: SizeMinMax.findMinMax(list)
		}
		return tSizeMM
    }

    override fun scaleObjectToFit(maxRadiusSqr: Float): Float {
		checkInterpolate()
		var mRadiusSqr = maxRadiusSqr
		for( list in interpolatePoints!!) {
			for (point in list) {
				val radiusSqr = point.lengthSquared()
				if (radiusSqr > maxRadiusSqr) mRadiusSqr = radiusSqr
			}
		}
		return mRadiusSqr
    }

    override fun collectionConnect(collectionPoint: CollectionPoint): Unit {
		checkInterpolate()
		collectionPoint.createNewOneLayer( LayerNamenot )
		for( list in interpolatePoints!!) {
			if(list.isEmpty() ) continue
			collectionPoint.collectPointConnectionNewOneL( HandlenotOmitted, list )
		}
   }

 	override fun xdef(): Int { return 0 }

    init {
        last(lastElem)
        if (isRead) {
            read(lastElem.dxfContext)
        }
    }
	 companion object {

		var ile = 0
	//	 Subclass marker (AcDbBlockReference)
	//	100	
	const val AcDbBlockReference: String = "AcDbBlockReference"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( VariableAttributesfollow != null) sb.append( "\n 66\n"+VariableAttributesfollow )
		if( BlockName != null) sb.append( "\n 2\n"+BlockName )
		if( InsertionPoint != null) sb.append( "\n 10\n"+InsertionPoint?.x+"\n 20\n"+InsertionPoint?.y+"\n 30\n"+InsertionPoint?.z )
		if( XScale != null) sb.append( "\n 41\n"+XScale )
		if( YScale != null) sb.append( "\n 42\n"+YScale )
		if( ZScale != null) sb.append( "\n 43\n"+ZScale )
		if( RotationAngle != null) sb.append( "\n 50\n"+RotationAngle )
		if( ColumnCount != null) sb.append( "\n 70\n"+ColumnCount )
		if( RowCount != null) sb.append( "\n 71\n"+RowCount )
		if( ColumnSpacing != null) sb.append( "\n 44\n"+ColumnSpacing )
		if( RowSpacing != null) sb.append( "\n 45\n"+RowSpacing )
		if( ExtrusionDirection != null) sb.append( "\n 210\n"+ExtrusionDirection?.x+"\n 220\n"+ExtrusionDirection?.y+"\n 230\n"+ExtrusionDirection?.z )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nINSERT")
			sbX.append( sb)
		}

		 return sbX
	}}