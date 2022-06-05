package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class EntMLEADERSTYLE(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Content Type
	//	170	
	var ContentType: Int? = null

	//	 DrawMLeaderOrder Type
	//	171	
	var DrawMLeaderOrderType: Int? = null

	//	 DrawLeaderOrder Type
	//	172	
	var DrawLeaderOrderType: Int? = null

	//	 MaxLeader Segments Points
	//	90	
	var MaxLeaderSegments: Int? = null

	//	 First Segment Angle Constraint
	//	40	
	var FirstSegment: Float? = null

	//	 Second Segment Angle Constraint
	//	41	
	var SecondSegment: Float? = null

	//	 LeaderLineType
	//	173	
	var LeaderLineType: Int? = null

	//	 LeaderLineColor
	//	91	
	var LeaderLineColor: Int? = null

	//	 LeaderLineType ID
	//	340	
	var LeaderLineTypeID: String? = null

	//	 LeaderLineWeight
	//	92	
	var LeaderLineWeight: Int? = null

	//	 Enable Landing
	//	290	
	var EnableLanding: Boolean? = null

	//	 Landing Gap
	//	42	
	var LandingGap: Float? = null

	//	 Enable Dogleg
	//	291	
	var EnableDogleg: Boolean? = null

	//	 Dogleg Length
	//	43	
	var DoglegLength: Float? = null

	//	 Mleader Style Description
	//	3	
	var MleaderStyle: String? = null

	//	 Arrowhead ID
	//	341	
	var ArrowheadID: String? = null

	//	 Arrowhead Size
	//	44	
	var ArrowheadSize: Float? = null

	//	 Default MText Contents
	//	300	
	var DefaultMText: String? = null

	//	 mTextStyleId
	//	342	
	var MTextStyleId: String? = null

	//	 Text Left Attachment Type
	//	174	
	var TextLeft: Int? = null

	//	 Text Angle Type
	//	175	
	var TextAngle: Int? = null

	//	 Text Alignment Type
	//	176	
	var TextAlignment: Int? = null

	//	 Text Right Attachment Type
	//	178	
	var TextRight: Int? = null

	//	 Text Color
	//	93	
	var TextColor: Int? = null

	//	 Text Height
	//	45	
	var TextHeight: Float? = null

	//	 Enable Frame Text
	//	292	
	var EnableFrame: Boolean? = null

	//	 Text Align Always Left
	//	297	
	var TextAlign: Boolean? = null

	//	 Align Space
	//	46	
	var AlignSpace: Float? = null

	//	 Block Content ID
	//	343	
	var BlockContent: String? = null

	//	 Block Content Color
	//	94	
	var BlockContent1: Int? = null

	//	 Block Content Scale on X-axis
	//	47	
	var BlockContent2: Float? = null

	//	 Block Content Scale on Y-axis
	//	49	
	var BlockContent3: Float? = null

	//	 Block Content Scale on Z-axis
	//	140	
	var BlockContent4: Float? = null

	//	 Enable Block Content Scale
	//	293	
	var EnableBlock: Boolean? = null

	//	 Block Content Rotation
	//	141	
	var BlockContent5: Float? = null

	//	 Enable Block Content Rotation
	//	294	
	var EnableBlock1: Boolean? = null

	//	 Block Content Connection Type
	//	177	
	var BlockContent6: Int? = null

	//	 Scale
	//	142	
	var Scale: Float? = null

	//	 Overwrite Property Value
	//	295	
	var OverwriteProperty: Boolean? = null

	//	 Is Annotative
	//	296	
	var IsAnnotative: Boolean? = null

	//	 Break Gap Size
	//	143	
	var BreakGap: Float? = null

	//	 Text attachment direction for MText contents:, 0 = Horizontal, 1 = Vertical
	//	271	
	var TextAttachment: Int? = null

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
			"170" -> {
				ContentType = getbfr.intValue()
			}
			"171" -> {
				DrawMLeaderOrderType = getbfr.intValue()
			}
			"172" -> {
				DrawLeaderOrderType = getbfr.intValue()
			}
			"90" -> {
				MaxLeaderSegments = getbfr.intValue()
			}
			"40" -> {
				FirstSegment = getbfr.floatValue()
			}
			"41" -> {
				SecondSegment = getbfr.floatValue()
			}
			"173" -> {
				LeaderLineType = getbfr.intValue()
			}
			"91" -> {
				LeaderLineColor = getbfr.intValue()
			}
			"340" -> {
				LeaderLineTypeID = getbfr.stringValue()
			}
			"92" -> {
				LeaderLineWeight = getbfr.intValue()
			}
			"290" -> {
				EnableLanding = getbfr.booleanValue()
			}
			"42" -> {
				LandingGap = getbfr.floatValue()
			}
			"291" -> {
				EnableDogleg = getbfr.booleanValue()
			}
			"43" -> {
				DoglegLength = getbfr.floatValue()
			}
			"3" -> {
				MleaderStyle = getbfr.stringValue()
			}
			"341" -> {
				ArrowheadID = getbfr.stringValue()
			}
			"44" -> {
				ArrowheadSize = getbfr.floatValue()
			}
			"300" -> {
				DefaultMText = getbfr.stringValue()
			}
			"342" -> {
				MTextStyleId = getbfr.stringValue()
			}
			"174" -> {
				TextLeft = getbfr.intValue()
			}
			"175" -> {
				TextAngle = getbfr.intValue()
			}
			"176" -> {
				TextAlignment = getbfr.intValue()
			}
			"178" -> {
				TextRight = getbfr.intValue()
			}
			"93" -> {
				TextColor = getbfr.intValue()
			}
			"45" -> {
				TextHeight = getbfr.floatValue()
			}
			"292" -> {
				EnableFrame = getbfr.booleanValue()
			}
			"297" -> {
				TextAlign = getbfr.booleanValue()
			}
			"46" -> {
				AlignSpace = getbfr.floatValue()
			}
			"343" -> {
				BlockContent = getbfr.stringValue()
			}
			"94" -> {
				BlockContent1 = getbfr.intValue()
			}
			"47" -> {
				BlockContent2 = getbfr.floatValue()
			}
			"49" -> {
				BlockContent3 = getbfr.floatValue()
			}
			"140" -> {
				BlockContent4 = getbfr.floatValue()
			}
			"293" -> {
				EnableBlock = getbfr.booleanValue()
			}
			"141" -> {
				BlockContent5 = getbfr.floatValue()
			}
			"294" -> {
				EnableBlock1 = getbfr.booleanValue()
			}
			"177" -> {
				BlockContent6 = getbfr.intValue()
			}
			"142" -> {
				Scale = getbfr.floatValue()
			}
			"295" -> {
				OverwriteProperty = getbfr.booleanValue()
			}
			"296" -> {
				IsAnnotative = getbfr.booleanValue()
			}
			"143" -> {
				BreakGap = getbfr.floatValue()
			}
			"271" -> {
				TextAttachment = getbfr.intValue()
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
		if( ContentType != null) sb.append( "\n 170\n"+ContentType )
		if( DrawMLeaderOrderType != null) sb.append( "\n 171\n"+DrawMLeaderOrderType )
		if( DrawLeaderOrderType != null) sb.append( "\n 172\n"+DrawLeaderOrderType )
		if( MaxLeaderSegments != null) sb.append( "\n 90\n"+MaxLeaderSegments )
		if( FirstSegment != null) sb.append( "\n 40\n"+FirstSegment )
		if( SecondSegment != null) sb.append( "\n 41\n"+SecondSegment )
		if( LeaderLineType != null) sb.append( "\n 173\n"+LeaderLineType )
		if( LeaderLineColor != null) sb.append( "\n 91\n"+LeaderLineColor )
		if( LeaderLineTypeID != null) sb.append( "\n 340\n"+LeaderLineTypeID )
		if( LeaderLineWeight != null) sb.append( "\n 92\n"+LeaderLineWeight )
		if( EnableLanding != null) sb.append( "\n 290\n"+EnableLanding )
		if( LandingGap != null) sb.append( "\n 42\n"+LandingGap )
		if( EnableDogleg != null) sb.append( "\n 291\n"+EnableDogleg )
		if( DoglegLength != null) sb.append( "\n 43\n"+DoglegLength )
		if( MleaderStyle != null) sb.append( "\n 3\n"+MleaderStyle )
		if( ArrowheadID != null) sb.append( "\n 341\n"+ArrowheadID )
		if( ArrowheadSize != null) sb.append( "\n 44\n"+ArrowheadSize )
		if( DefaultMText != null) sb.append( "\n 300\n"+DefaultMText )
		if( MTextStyleId != null) sb.append( "\n 342\n"+MTextStyleId )
		if( TextLeft != null) sb.append( "\n 174\n"+TextLeft )
		if( TextAngle != null) sb.append( "\n 175\n"+TextAngle )
		if( TextAlignment != null) sb.append( "\n 176\n"+TextAlignment )
		if( TextRight != null) sb.append( "\n 178\n"+TextRight )
		if( TextColor != null) sb.append( "\n 93\n"+TextColor )
		if( TextHeight != null) sb.append( "\n 45\n"+TextHeight )
		if( EnableFrame != null) sb.append( "\n 292\n"+EnableFrame )
		if( TextAlign != null) sb.append( "\n 297\n"+TextAlign )
		if( AlignSpace != null) sb.append( "\n 46\n"+AlignSpace )
		if( BlockContent != null) sb.append( "\n 343\n"+BlockContent )
		if( BlockContent1 != null) sb.append( "\n 94\n"+BlockContent1 )
		if( BlockContent2 != null) sb.append( "\n 47\n"+BlockContent2 )
		if( BlockContent3 != null) sb.append( "\n 49\n"+BlockContent3 )
		if( BlockContent4 != null) sb.append( "\n 140\n"+BlockContent4 )
		if( EnableBlock != null) sb.append( "\n 293\n"+EnableBlock )
		if( BlockContent5 != null) sb.append( "\n 141\n"+BlockContent5 )
		if( EnableBlock1 != null) sb.append( "\n 294\n"+EnableBlock1 )
		if( BlockContent6 != null) sb.append( "\n 177\n"+BlockContent6 )
		if( Scale != null) sb.append( "\n 142\n"+Scale )
		if( OverwriteProperty != null) sb.append( "\n 295\n"+OverwriteProperty )
		if( IsAnnotative != null) sb.append( "\n 296\n"+IsAnnotative )
		if( BreakGap != null) sb.append( "\n 143\n"+BreakGap )
		if( TextAttachment != null) sb.append( "\n 271\n"+TextAttachment )
		if( BottomText != null) sb.append( "\n 272\n"+BottomText )
		if( TopText != null) sb.append( "\n 273\n"+TopText )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nMLEADERSTYLE")
			sbX.append( sb)
		}

		 return sbX
	}}