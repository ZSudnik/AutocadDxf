package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class Ent3DSOLID(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Modeler format version number (currently = 1)
	//	70	
	var ModelerFormat: Int? = null

	//	 Proprietary data (multiple lines < 255 characters each)
	//	1	
	var ProprietaryData: String? = null

	//	 Additional lines of proprietary data (if previous group 1 string is greater than 255 characters) (optional)
	//	3	
	var AdditionalLines: String? = null

	//	 Soft-owner ID/handle to history object
	//	350	
	var SoftownerIDhandle: String? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
		if (getbfr.codEquals("0")) {//"0"
			cod = getbfr.get()
			when(cod) {
				"ENDSEC" -> return
				"SEQEND" -> return
				"NURBSURFACE" -> {
					Ent3DFACE(this, true)
					continue
				}
			}
		}
        if (super.readX(cod)) {
            when (cod) {
			"70" -> {
				ModelerFormat = getbfr.intValue()
			}
			"1" -> {
				ProprietaryData = getbfr.stringValue()
			}
			"3" -> {
				AdditionalLines = getbfr.stringValue()
			}
			"350" -> {
				SoftownerIDhandle = getbfr.stringValue()
			}
			}
		}
			cod = getbfr.get()
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
		val iter = this.iterator()
		while( iter.hasNext() ){
			val element = iter.next()
			element.collectionConnect( collectionPoint )
		}
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


	//	 Subclass marker (AcDbModelerGeometry)
	//	100	
	const val AcDbModelerGeometry: String = "AcDbModelerGeometry"

	//	 Subclass marker (AcDb3dSolid)
	//	100	
	const val AcDb3dSolid: String = "AcDb3dSolid"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( ModelerFormat != null) sb.append( "\n 70\n"+ModelerFormat )
		if( ProprietaryData != null) sb.append( "\n 1\n"+ProprietaryData )
		if( AdditionalLines != null) sb.append( "\n 3\n"+AdditionalLines )
		if( SoftownerIDhandle != null) sb.append( "\n 350\n"+SoftownerIDhandle )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\n3DSOLID")
			sbX.append( sb)
		}

		 return sbX
	}}