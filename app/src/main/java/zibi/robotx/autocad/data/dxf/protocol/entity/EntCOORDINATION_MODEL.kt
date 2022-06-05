package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class EntCOORDINATION_MODEL(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Object name (Coordination Model)
	//	0	
	var ObjectName: String? = null

	//	 Handle
	//	5	
	var Handle: Int? = null

	//	 Soft-pointer ID/handle to owner dictionary
	//	330	
	var SoftpointerIDhandle: String? = null

	//	 Soft-pointer ID/handle to the AcDbNavisworksModelDef object
	//	340	
	var SoftpointerIDhandle1: String? = null

	var counter40 = 0
	//	 Model transform in WCS coordinates (4x4 matrix)
	//	40	
	var ModelTransform: FloatArray? = null

	//	 Insertion unit factor
	//	40	
	var InsertionUnit: Float? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"0" -> {
				ObjectName = getbfr.stringValue()
			}
			"5" -> {
				Handle = getbfr.intHexValue()
			}
			"330" -> {
				SoftpointerIDhandle = getbfr.stringValue()
			}
			"340" -> {
				SoftpointerIDhandle1 = getbfr.stringValue()
			}
			"40" -> {
				if(ModelTransform == null) ModelTransform = FloatArray(16)
				if( counter40 < 16) {
					ModelTransform!![ counter40] = getbfr.floatValue()
				}else{
					InsertionUnit = getbfr.floatValue()
				}
				counter40++
			}
			}
		}
			cod = getbfr.get()
	}
	}


    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? {
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
	//	 Subclass marker (AcDbNavisworksModel)
	//	100	
	const val AcDbNavisworksModel: String = "AcDbNavisworksModel"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( ObjectName != null) sb.append( "\n 0\n"+ObjectName )
		if( Handle != null) sb.append( "\n 5\n"+Integer.toHexString(Handle!!) )
		if( SoftpointerIDhandle != null) sb.append( "\n 330\n"+SoftpointerIDhandle )
		if( SoftpointerIDhandle1 != null) sb.append( "\n 340\n"+SoftpointerIDhandle1 )
		if( ModelTransform != null) {
			for( count in 0..15)
				sb.append( "\n 40\n"+ModelTransform!![count] )
		}
		if( InsertionUnit != null) sb.append( "\n 40\n"+InsertionUnit )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nCOORDINATION_MODEL")
			sbX.append( sb)
		}

		 return sbX
	}}