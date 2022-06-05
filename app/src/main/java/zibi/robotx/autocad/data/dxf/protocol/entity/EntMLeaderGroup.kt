package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class EntMLeaderGroup(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Leader Style Id
	//	340	
	var LeaderStyle: String? = null

	//	 Property Override Flag
	//	90	
	var PropertyOverride: Int? = null

	//	 LeaderLineType
	//	170	
	var LeaderLineType: Int? = null

	//	 Leade LineColor
	//	91	
	var LeadeLineColor: Int? = null

	//	 LeaderLineTypeID
	//	341	
	var LeaderLineTypeID: String? = null

	//	 LeaderLine Weight
	//	171	
	var LeaderLineWeight: Int? = null

	//	 Enable Landing
	//	290	
	var EnableLanding: Boolean? = null

	//	 Enable Dogleg
	//	291	
	var EnableDogleg: Boolean? = null

	//	 Dogleg Length
	//	41	
	var DoglegLength: Float? = null

	//	 Arrowhead ID
	//	342	
	var ArrowheadID: String? = null

	//	 Arrowhead Size
	//	42	
	var ArrowheadSize: Float? = null

	//	 Content Type
	//	172	
	var ContentType: Int? = null

	//	 Text Style ID
	//	343	
	var TextStyle: String? = null

	//	 Text Left Attachment Type
	//	173	
	var TextLeft: Int? = null

	//	 Text Right Attachement Type
	//	95	
	var TextRight: Int? = null

	//	 Text Angle Type
	//	174	
	var TextAngle: Int? = null

	//	 Text Alignment Type
	//	175	
	var TextAlignment: Int? = null

	//	 Text Color
	//	92	
	var TextColor: Int? = null

	//	 Enable Frame Text
	//	292	
	var EnableFrame: Boolean? = null

	//	 Block Content ID
	//	344	
	var BlockContent: String? = null

	//	 Block Content Color
	//	93	
	var BlockContent1: Int? = null

	//	 Block Content Scale
	//	10	
	var BlockContent2: Float? = null

	//	 Block Content Rotation
	//	43	
	var BlockContent3: Float? = null

	//	 Block Content Connection Type
	//	176	
	var BlockContent4: Int? = null

	//	 Enable Annotation Scale
	//	293	
	var EnableAnnotation: Boolean? = null

	//	 Arrowhead Index
	//	94	
	var ArrowheadIndex: Int? = null

	//	 Arrowhead ID
	//	345	
	var ArrowheadID1: String? = null

	//	 Block AttributerId
	//	330	
	var BlockAttributerId: String? = null

	//	 Block Attribute Index
	//	177	
	var BlockAttribute: Int? = null

	//	 Block Attribute Width
	//	44	
	var BlockAttribute1: Float? = null

	//	 Block Attribute Text String
	//	302	
	var BlockAttribute2: String? = null

	//	 Text Direction Negative
	//	294	
	var TextDirection: Boolean? = null

	//	 Text Align in IPE
	//	178	
	var TextAlign: Int? = null

	//	 Text Attachment Point
	//	179	
	var TextAttachment: Int? = null

	//	 Text attachment direction for MText contents:, 0 = Horizontal, 1 = Vertical
	//	271	
	var TextAttachment1: Int? = null

	//	 Bottom text attachment direction:, 9 = Center, 10 = Underline and Center
	//	272	
	var BottomText: Int? = null

	//	 Top text attachment direction:, 9 = Center, 10 = Overline and Center
	//	273	
	var TopText: Int? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"340" -> {
				LeaderStyle = getbfr.stringValue()
			}
			"90" -> {
				PropertyOverride = getbfr.intValue()
			}
			"170" -> {
				LeaderLineType = getbfr.intValue()
			}
			"91" -> {
				LeadeLineColor = getbfr.intValue()
			}
			"341" -> {
				LeaderLineTypeID = getbfr.stringValue()
			}
			"171" -> {
				LeaderLineWeight = getbfr.intValue()
			}
			"290" -> {
				EnableLanding = getbfr.booleanValue()
			}
			"291" -> {
				EnableDogleg = getbfr.booleanValue()
			}
			"41" -> {
				DoglegLength = getbfr.floatValue()
			}
			"342" -> {
				ArrowheadID = getbfr.stringValue()
			}
			"42" -> {
				ArrowheadSize = getbfr.floatValue()
			}
			"172" -> {
				ContentType = getbfr.intValue()
			}
			"343" -> {
				TextStyle = getbfr.stringValue()
			}
			"173" -> {
				TextLeft = getbfr.intValue()
			}
			"95" -> {
				TextRight = getbfr.intValue()
			}
			"174" -> {
				TextAngle = getbfr.intValue()
			}
			"175" -> {
				TextAlignment = getbfr.intValue()
			}
			"92" -> {
				TextColor = getbfr.intValue()
			}
			"292" -> {
				EnableFrame = getbfr.booleanValue()
			}
			"344" -> {
				BlockContent = getbfr.stringValue()
			}
			"93" -> {
				BlockContent1 = getbfr.intValue()
			}
			"10" -> {
				BlockContent2 = getbfr.floatValue()
			}
			"43" -> {
				BlockContent3 = getbfr.floatValue()
			}
			"176" -> {
				BlockContent4 = getbfr.intValue()
			}
			"293" -> {
				EnableAnnotation = getbfr.booleanValue()
			}
			"94" -> {
				ArrowheadIndex = getbfr.intValue()
			}
			"345" -> {
				ArrowheadID1 = getbfr.stringValue()
			}
			"330" -> {
				BlockAttributerId = getbfr.stringValue()
			}
			"177" -> {
				BlockAttribute = getbfr.intValue()
			}
			"44" -> {
				BlockAttribute1 = getbfr.floatValue()
			}
			"302" -> {
				BlockAttribute2 = getbfr.stringValue()
			}
			"294" -> {
				TextDirection = getbfr.booleanValue()
			}
			"178" -> {
				TextAlign = getbfr.intValue()
			}
			"179" -> {
				TextAttachment = getbfr.intValue()
			}
			"271" -> {
				TextAttachment1 = getbfr.intValue()
			}
			"272" -> {
				BottomText = getbfr.intValue()
			}
			"273" -> {
				TopText = getbfr.intValue()
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
		if( LeaderStyle != null) sb.append( "\n 340\n"+LeaderStyle )
		if( PropertyOverride != null) sb.append( "\n 90\n"+PropertyOverride )
		if( LeaderLineType != null) sb.append( "\n 170\n"+LeaderLineType )
		if( LeadeLineColor != null) sb.append( "\n 91\n"+LeadeLineColor )
		if( LeaderLineTypeID != null) sb.append( "\n 341\n"+LeaderLineTypeID )
		if( LeaderLineWeight != null) sb.append( "\n 171\n"+LeaderLineWeight )
		if( EnableLanding != null) sb.append( "\n 290\n"+EnableLanding )
		if( EnableDogleg != null) sb.append( "\n 291\n"+EnableDogleg )
		if( DoglegLength != null) sb.append( "\n 41\n"+DoglegLength )
		if( ArrowheadID != null) sb.append( "\n 342\n"+ArrowheadID )
		if( ArrowheadSize != null) sb.append( "\n 42\n"+ArrowheadSize )
		if( ContentType != null) sb.append( "\n 172\n"+ContentType )
		if( TextStyle != null) sb.append( "\n 343\n"+TextStyle )
		if( TextLeft != null) sb.append( "\n 173\n"+TextLeft )
		if( TextRight != null) sb.append( "\n 95\n"+TextRight )
		if( TextAngle != null) sb.append( "\n 174\n"+TextAngle )
		if( TextAlignment != null) sb.append( "\n 175\n"+TextAlignment )
		if( TextColor != null) sb.append( "\n 92\n"+TextColor )
		if( EnableFrame != null) sb.append( "\n 292\n"+EnableFrame )
		if( BlockContent != null) sb.append( "\n 344\n"+BlockContent )
		if( BlockContent1 != null) sb.append( "\n 93\n"+BlockContent1 )
		if( BlockContent2 != null) sb.append( "\n 10\n"+BlockContent2 )
		if( BlockContent3 != null) sb.append( "\n 43\n"+BlockContent3 )
		if( BlockContent4 != null) sb.append( "\n 176\n"+BlockContent4 )
		if( EnableAnnotation != null) sb.append( "\n 293\n"+EnableAnnotation )
		if( ArrowheadIndex != null) sb.append( "\n 94\n"+ArrowheadIndex )
		if( ArrowheadID1 != null) sb.append( "\n 345\n"+ArrowheadID1 )
		if( BlockAttributerId != null) sb.append( "\n 330\n"+BlockAttributerId )
		if( BlockAttribute != null) sb.append( "\n 177\n"+BlockAttribute )
		if( BlockAttribute1 != null) sb.append( "\n 44\n"+BlockAttribute1 )
		if( BlockAttribute2 != null) sb.append( "\n 302\n"+BlockAttribute2 )
		if( TextDirection != null) sb.append( "\n 294\n"+TextDirection )
		if( TextAlign != null) sb.append( "\n 178\n"+TextAlign )
		if( TextAttachment != null) sb.append( "\n 179\n"+TextAttachment )
		if( TextAttachment1 != null) sb.append( "\n 271\n"+TextAttachment1 )
		if( BottomText != null) sb.append( "\n 272\n"+BottomText )
		if( TopText != null) sb.append( "\n 273\n"+TopText )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nMLeaderGroup")
			sbX.append( sb)
		}

		 return sbX
	}}