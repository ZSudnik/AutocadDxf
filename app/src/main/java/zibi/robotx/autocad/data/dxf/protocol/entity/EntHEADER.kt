package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.CollectionPoint
import zibi.robotx.autocad.data.dxf.util.SizeMinMax

class EntHEADER(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 70Five integer variables intended for use by third-party developers
	//	5	
	var IntegerVariables: String? = null

	//	 40Five real variables intended for use by third-party developers
	//	5	
	var RealVariables: String? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        when {
            super.readX( cod ) ->{}
			cod == "5" -> {
				IntegerVariables = getbfr.stringValue()
			}
			cod == "5" -> {
				RealVariables = getbfr.stringValue()
			}
			}
			cod = getbfr.get()
		}
	}


    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? {
    //    return sizeMMParent?.findMinMax( begpnt, endpnt, xtruDir )
    //            ?: SizeMinMax.findMinMax( begpnt, endpnt, xtruDir )
                  return null
    }

    override fun scaleObjectToFit(maxRadiusSqr: Float): Float {
        TODO("Not yet implemented")
    }

    override fun collectionConnect(collectionPoint: CollectionPoint) {
        TODO("Not yet implemented")
    }

    override fun xdef(): Int {
        TODO("Not yet implemented")
    }

    init {
        last(lastElem)
        if (isRead) {
            read(lastElem.dxfContext)
        }
    }
	 companion object {

	 }
}