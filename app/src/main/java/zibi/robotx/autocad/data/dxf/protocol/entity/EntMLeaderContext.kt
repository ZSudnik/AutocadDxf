package zibi.robotx.autocad.data.dxf.protocol.entity

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector3f

class EntMLeaderContext(lastElem: DxfChain, isRead: Boolean) : EntCommon() {



	//	 Content Scale
	//	40	
	var ContentScale: Float? = null

	//	 Content Base Position
	//	10	20	30	
	var ContentBase: Vector3f? = null

	//	 Text Height
	//	41	
	var TextHeight: Float? = null

	//	 Arrowhead Size
	//	140	
	var ArrowheadSize: Float? = null

	//	 Landing Gap
	//	145	
	var LandingGap: Float? = null

	//	 hasMText
	//	290	
	var HasMText: Boolean? = null

	//	 Default Text Contents
	//	304	
	var DefaultText: String? = null

	//	 Text Normal Direction
	//	11	21	31	
	var TextNormal: Vector3f? = null

	//	 Text Style ID
	//	340	
	var TextStyle: String? = null

	//	 Text Location
	//	12	22	32	
	var TextLocation: Vector3f? = null

	//	 Text Direction
	//	13	23	33	
	var TextDirection: Vector3f? = null

	//	 Text Rotation
	//	42	
	var TextRotation: Float? = null

	//	 Text Width
	//	43	
	var TextWidth: Float? = null

	//	 Text Height
	//	44	
	var TextHeight1: Float? = null

	//	 Text Line Spacing Factor
	//	45	
	var TextLine: Float? = null

	//	 Text Line Spacing Style
	//	170	
	var TextLine1: Int? = null

	//	 Text Color
	//	90	
	var TextColor: Int? = null

	//	 Text Attachment
	//	171	
	var TextAttachment: Int? = null

	//	 Text Flow Direction
	//	172	
	var TextFlow: Int? = null

	//	 Text Background Color
	//	91	
	var TextBackground: Int? = null

	//	 Text Background Scale Factor
	//	141	
	var TextBackground1: Float? = null

	//	 Text Background Transparency
	//	92	
	var TextBackground2: Int? = null

	//	 Is Text Background Color On
	//	291	
	var IsText: Boolean? = null

	//	 Is Text Background Fill On
	//	292	
	var IsText1: Boolean? = null

	//	 Text Column Type
	//	173	
	var TextColumn: Int? = null

	//	 Use Text Auto Height
	//	293	
	var UseText: Boolean? = null

	//	 Text Column Width
	//	142	
	var TextColumn1: Float? = null

	//	 Text Column Gutter Width
	//	143	
	var TextColumn2: Float? = null

	//	 Text Column Flow Reversed
	//	294	
	var TextColumn3: Boolean? = null

	//	 Text Column Height
	//	144	
	var TextColumn4: Float? = null

	//	 Text Use Word Break
	//	295	
	var TextUse: Boolean? = null

	//	 HasBlock
	//	296	
	var HasBlock: Boolean? = null

	//	 Block Content ID
	//	341	
	var BlockContent: String? = null

	//	 Block Content Normal Direction
	//	14	24	34	
	var BlockContent1: Vector3f? = null

	//	 Block Content Position
	//	15	25	35	
	var BlockContent2: Vector3f? = null

	//	 Block Content Scale
	//	16	
	var BlockContent3: Float? = null

	//	 Block Content Rotation
	//	46	
	var BlockContent4: Float? = null

	//	 Block Content Color
	//	93	
	var BlockContent5: Int? = null

	//	 Block Transformation Matrix
	//	47	
	var BlockTransformation: Float? = null

	//	 MLeader Plane Origin Point
	//	110	
	var MLeaderPlane: Float? = null

	//	 MLeader Plane X-Axis Direction
	//	111	
	var MLeaderPlane1: Float? = null

	//	 MLeader Plane Y-Axis Direction
	//	112	
	var MLeaderPlane2: Float? = null

	//	 MLeader Plane Normal Reversed
	//	297	
	var MLeaderPlane3: Boolean? = null

	//	 Vertex
	//	10	20	30	
	var Vertex: Vector3f? = null

	//	 Break Point Index
	//	90	
	var BreakPoint: Int? = null

	//	 Text Width
	//	43	
	var TextWidth1: Float? = null

	//	 Text Height
	//	44	
	var TextHeight2: Float? = null

	//	 Text Line Spacing Factor
	//	45	
	var TextLine2: Float? = null

	//	 Text Line Spacing Style
	//	170	
	var TextLine3: Int? = null

	//	 Text Color
	//	90	
	var TextColor1: Int? = null

	//	 Text Attachment
	//	171	
	var TextAttachment1: Int? = null

	//	 Text Flow Direction
	//	172	
	var TextFlow1: Int? = null

	//	 Text Background Color
	//	91	
	var TextBackground3: Int? = null

	//	 Text Background Scale Factor
	//	141	
	var TextBackground4: Float? = null

	//	 Text Background Transparency
	//	92	
	var TextBackground5: Int? = null

	//	 Is Text Background Color On
	//	291	
	var IsText2: Boolean? = null

	//	 Is Text Background Fill On
	//	292	
	var IsText3: Boolean? = null

	//	 Text Column Type
	//	173	
	var TextColumn5: Int? = null

	//	 Use Text Auto Height
	//	293	
	var UseText1: Boolean? = null

	//	 Text Column Width
	//	142	
	var TextColumn6: Float? = null

	//	 Text Column Gutter Width
	//	143	
	var TextColumn7: Float? = null

	//	 Text Column Flow Reversed
	//	294	
	var TextColumn8: Boolean? = null

	//	 Text Column Height
	//	144	
	var TextColumn9: Float? = null

	//	 Text Use Word Break
	//	295	
	var TextUse1: Boolean? = null

	//	 HasBlock
	//	296	
	var HasBlock1: Boolean? = null

	//	 Block Content ID
	//	341	
	var BlockContent6: String? = null

	//	 Block Content Normal Direction
	//	14	24	34	
	var BlockContent7: Vector3f? = null

	//	 Block Content Position
	//	15	25	35	
	var BlockContent8: Vector3f? = null

	//	 Block Content Scale
	//	16	
	var BlockContent9: Float? = null

	//	 Block Content Rotation
	//	46	
	var BlockContent10: Float? = null

	//	 BLock Content Color
	//	93	
	var BLockContent: Int? = null

	//	 BLock Transformation Matrix
	//	47	
	var BLockTransformation: Float? = null

	//	 Mleader Plane Origin Point
	//	110	
	var MleaderPlane: Float? = null

	//	 MLeader Plane X-Axis Direction
	//	111	
	var MLeaderPlane4: Float? = null

	//	 MLeader Plane Y-Axis Direction
	//	112	
	var MLeaderPlane5: Float? = null

	//	 MLeader Plane Normal Reversed
	//	297	
	var MLeaderPlane6: Boolean? = null

    override fun read(getbfr: DxfLoaderContext) {
    var cod = getbfr.get()
    while (true) {
        if (cod == "0") break
        if (super.readX(cod)) {
            when (cod) {
			"40" -> {
				ContentScale = getbfr.floatValue()
			}
			"10" -> {
				if( ContentBase == null ) ContentBase = Vector3f()
				ContentBase?.x = getbfr.floatValue()
			}
			"20" -> {
				if( ContentBase == null ) ContentBase = Vector3f()
				ContentBase?.y = getbfr.floatValue()
			}
			"30" -> {
				if( ContentBase == null ) ContentBase = Vector3f()
				ContentBase?.z = getbfr.floatValue()
			}
			"41" -> {
				TextHeight = getbfr.floatValue()
			}
			"140" -> {
				ArrowheadSize = getbfr.floatValue()
			}
			"145" -> {
				LandingGap = getbfr.floatValue()
			}
			"290" -> {
				HasMText = getbfr.booleanValue()
			}
			"304" -> {
				DefaultText = getbfr.stringValue()
			}
			"11" -> {
				if( TextNormal == null ) TextNormal = Vector3f()
				TextNormal?.x = getbfr.floatValue()
			}
			"21" -> {
				if( TextNormal == null ) TextNormal = Vector3f()
				TextNormal?.y = getbfr.floatValue()
			}
			"31" -> {
				if( TextNormal == null ) TextNormal = Vector3f()
				TextNormal?.z = getbfr.floatValue()
			}
			"340" -> {
				TextStyle = getbfr.stringValue()
			}
			"12" -> {
				if( TextLocation == null ) TextLocation = Vector3f()
				TextLocation?.x = getbfr.floatValue()
			}
			"22" -> {
				if( TextLocation == null ) TextLocation = Vector3f()
				TextLocation?.y = getbfr.floatValue()
			}
			"32" -> {
				if( TextLocation == null ) TextLocation = Vector3f()
				TextLocation?.z = getbfr.floatValue()
			}
			"13" -> {
				if( TextDirection == null ) TextDirection = Vector3f()
				TextDirection?.x = getbfr.floatValue()
			}
			"23" -> {
				if( TextDirection == null ) TextDirection = Vector3f()
				TextDirection?.y = getbfr.floatValue()
			}
			"33" -> {
				if( TextDirection == null ) TextDirection = Vector3f()
				TextDirection?.z = getbfr.floatValue()
			}
			"42" -> {
				TextRotation = getbfr.floatValue()
			}
			"43" -> {
				TextWidth = getbfr.floatValue()
			}
			"44" -> {
				TextHeight1 = getbfr.floatValue()
			}
			"45" -> {
				TextLine = getbfr.floatValue()
			}
			"170" -> {
				TextLine1 = getbfr.intValue()
			}
			"90" -> {
				TextColor = getbfr.intValue()
			}
			"171" -> {
				TextAttachment = getbfr.intValue()
			}
			"172" -> {
				TextFlow = getbfr.intValue()
			}
			"91" -> {
				TextBackground = getbfr.intValue()
			}
			"141" -> {
				TextBackground1 = getbfr.floatValue()
			}
			"92" -> {
				TextBackground2 = getbfr.intValue()
			}
			"291" -> {
				IsText = getbfr.booleanValue()
			}
			"292" -> {
				IsText1 = getbfr.booleanValue()
			}
			"173" -> {
				TextColumn = getbfr.intValue()
			}
			"293" -> {
				UseText = getbfr.booleanValue()
			}
			"142" -> {
				TextColumn1 = getbfr.floatValue()
			}
			"143" -> {
				TextColumn2 = getbfr.floatValue()
			}
			"294" -> {
				TextColumn3 = getbfr.booleanValue()
			}
			"144" -> {
				TextColumn4 = getbfr.floatValue()
			}
			"295" -> {
				TextUse = getbfr.booleanValue()
			}
			"296" -> {
				HasBlock = getbfr.booleanValue()
			}
			"341" -> {
				BlockContent = getbfr.stringValue()
			}
			"14" -> {
				if( BlockContent1 == null ) BlockContent1 = Vector3f()
				BlockContent1?.x = getbfr.floatValue()
			}
			"24" -> {
				if( BlockContent1 == null ) BlockContent1 = Vector3f()
				BlockContent1?.y = getbfr.floatValue()
			}
			"34" -> {
				if( BlockContent1 == null ) BlockContent1 = Vector3f()
				BlockContent1?.z = getbfr.floatValue()
			}
			"15" -> {
				if( BlockContent2 == null ) BlockContent2 = Vector3f()
				BlockContent2?.x = getbfr.floatValue()
			}
			"25" -> {
				if( BlockContent2 == null ) BlockContent2 = Vector3f()
				BlockContent2?.y = getbfr.floatValue()
			}
			"35" -> {
				if( BlockContent2 == null ) BlockContent2 = Vector3f()
				BlockContent2?.z = getbfr.floatValue()
			}
			"16" -> {
				BlockContent3 = getbfr.floatValue()
			}
			"46" -> {
				BlockContent4 = getbfr.floatValue()
			}
			"93" -> {
				BlockContent5 = getbfr.intValue()
			}
			"47" -> {
				BlockTransformation = getbfr.floatValue()
			}
			"110" -> {
				MLeaderPlane = getbfr.floatValue()
			}
			"111" -> {
				MLeaderPlane1 = getbfr.floatValue()
			}
			"112" -> {
				MLeaderPlane2 = getbfr.floatValue()
			}
			"297" -> {
				MLeaderPlane3 = getbfr.booleanValue()
			}
			"10" -> {
				if( Vertex == null ) Vertex = Vector3f()
				Vertex?.x = getbfr.floatValue()
			}
			"20" -> {
				if( Vertex == null ) Vertex = Vector3f()
				Vertex?.y = getbfr.floatValue()
			}
			"30" -> {
				if( Vertex == null ) Vertex = Vector3f()
				Vertex?.z = getbfr.floatValue()
			}
			"90" -> {
				BreakPoint = getbfr.intValue()
			}
			"43" -> {
				TextWidth1 = getbfr.floatValue()
			}
			"44" -> {
				TextHeight2 = getbfr.floatValue()
			}
			"45" -> {
				TextLine2 = getbfr.floatValue()
			}
			"170" -> {
				TextLine3 = getbfr.intValue()
			}
			"90" -> {
				TextColor1 = getbfr.intValue()
			}
			"171" -> {
				TextAttachment1 = getbfr.intValue()
			}
			"172" -> {
				TextFlow1 = getbfr.intValue()
			}
			"91" -> {
				TextBackground3 = getbfr.intValue()
			}
			"141" -> {
				TextBackground4 = getbfr.floatValue()
			}
			"92" -> {
				TextBackground5 = getbfr.intValue()
			}
			"291" -> {
				IsText2 = getbfr.booleanValue()
			}
			"292" -> {
				IsText3 = getbfr.booleanValue()
			}
			"173" -> {
				TextColumn5 = getbfr.intValue()
			}
			"293" -> {
				UseText1 = getbfr.booleanValue()
			}
			"142" -> {
				TextColumn6 = getbfr.floatValue()
			}
			"143" -> {
				TextColumn7 = getbfr.floatValue()
			}
			"294" -> {
				TextColumn8 = getbfr.booleanValue()
			}
			"144" -> {
				TextColumn9 = getbfr.floatValue()
			}
			"295" -> {
				TextUse1 = getbfr.booleanValue()
			}
			"296" -> {
				HasBlock1 = getbfr.booleanValue()
			}
			"341" -> {
				BlockContent6 = getbfr.stringValue()
			}
			"14" -> {
				if( BlockContent7 == null ) BlockContent7 = Vector3f()
				BlockContent7?.x = getbfr.floatValue()
			}
			"24" -> {
				if( BlockContent7 == null ) BlockContent7 = Vector3f()
				BlockContent7?.y = getbfr.floatValue()
			}
			"34" -> {
				if( BlockContent7 == null ) BlockContent7 = Vector3f()
				BlockContent7?.z = getbfr.floatValue()
			}
			"15" -> {
				if( BlockContent8 == null ) BlockContent8 = Vector3f()
				BlockContent8?.x = getbfr.floatValue()
			}
			"25" -> {
				if( BlockContent8 == null ) BlockContent8 = Vector3f()
				BlockContent8?.y = getbfr.floatValue()
			}
			"35" -> {
				if( BlockContent8 == null ) BlockContent8 = Vector3f()
				BlockContent8?.z = getbfr.floatValue()
			}
			"16" -> {
				BlockContent9 = getbfr.floatValue()
			}
			"46" -> {
				BlockContent10 = getbfr.floatValue()
			}
			"93" -> {
				BLockContent = getbfr.intValue()
			}
			"47" -> {
				BLockTransformation = getbfr.floatValue()
			}
			"110" -> {
				MleaderPlane = getbfr.floatValue()
			}
			"111" -> {
				MLeaderPlane4 = getbfr.floatValue()
			}
			"112" -> {
				MLeaderPlane5 = getbfr.floatValue()
			}
			"297" -> {
				MLeaderPlane6 = getbfr.booleanValue()
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
		if( ContentScale != null) sb.append( "\n 40\n"+ContentScale )
		if( ContentBase != null) sb.append( "\n 10\n"+ContentBase?.x+"\n 20\n"+ContentBase?.y+"\n 30\n"+ContentBase?.z )
		if( TextHeight != null) sb.append( "\n 41\n"+TextHeight )
		if( ArrowheadSize != null) sb.append( "\n 140\n"+ArrowheadSize )
		if( LandingGap != null) sb.append( "\n 145\n"+LandingGap )
		if( HasMText != null) sb.append( "\n 290\n"+HasMText )
		if( DefaultText != null) sb.append( "\n 304\n"+DefaultText )
		if( TextNormal != null) sb.append( "\n 11\n"+TextNormal?.x+"\n 21\n"+TextNormal?.y+"\n 31\n"+TextNormal?.z )
		if( TextStyle != null) sb.append( "\n 340\n"+TextStyle )
		if( TextLocation != null) sb.append( "\n 12\n"+TextLocation?.x+"\n 22\n"+TextLocation?.y+"\n 32\n"+TextLocation?.z )
		if( TextDirection != null) sb.append( "\n 13\n"+TextDirection?.x+"\n 23\n"+TextDirection?.y+"\n 33\n"+TextDirection?.z )
		if( TextRotation != null) sb.append( "\n 42\n"+TextRotation )
		if( TextWidth != null) sb.append( "\n 43\n"+TextWidth )
		if( TextHeight1 != null) sb.append( "\n 44\n"+TextHeight1 )
		if( TextLine != null) sb.append( "\n 45\n"+TextLine )
		if( TextLine1 != null) sb.append( "\n 170\n"+TextLine1 )
		if( TextColor != null) sb.append( "\n 90\n"+TextColor )
		if( TextAttachment != null) sb.append( "\n 171\n"+TextAttachment )
		if( TextFlow != null) sb.append( "\n 172\n"+TextFlow )
		if( TextBackground != null) sb.append( "\n 91\n"+TextBackground )
		if( TextBackground1 != null) sb.append( "\n 141\n"+TextBackground1 )
		if( TextBackground2 != null) sb.append( "\n 92\n"+TextBackground2 )
		if( IsText != null) sb.append( "\n 291\n"+IsText )
		if( IsText1 != null) sb.append( "\n 292\n"+IsText1 )
		if( TextColumn != null) sb.append( "\n 173\n"+TextColumn )
		if( UseText != null) sb.append( "\n 293\n"+UseText )
		if( TextColumn1 != null) sb.append( "\n 142\n"+TextColumn1 )
		if( TextColumn2 != null) sb.append( "\n 143\n"+TextColumn2 )
		if( TextColumn3 != null) sb.append( "\n 294\n"+TextColumn3 )
		if( TextColumn4 != null) sb.append( "\n 144\n"+TextColumn4 )
		if( TextUse != null) sb.append( "\n 295\n"+TextUse )
		if( HasBlock != null) sb.append( "\n 296\n"+HasBlock )
		if( BlockContent != null) sb.append( "\n 341\n"+BlockContent )
		if( BlockContent1 != null) sb.append( "\n 14\n"+BlockContent1?.x+"\n 24\n"+BlockContent1?.y+"\n 34\n"+BlockContent1?.z )
		if( BlockContent2 != null) sb.append( "\n 15\n"+BlockContent2?.x+"\n 25\n"+BlockContent2?.y+"\n 35\n"+BlockContent2?.z )
		if( BlockContent3 != null) sb.append( "\n 16\n"+BlockContent3 )
		if( BlockContent4 != null) sb.append( "\n 46\n"+BlockContent4 )
		if( BlockContent5 != null) sb.append( "\n 93\n"+BlockContent5 )
		if( BlockTransformation != null) sb.append( "\n 47\n"+BlockTransformation )
		if( MLeaderPlane != null) sb.append( "\n 110\n"+MLeaderPlane )
		if( MLeaderPlane1 != null) sb.append( "\n 111\n"+MLeaderPlane1 )
		if( MLeaderPlane2 != null) sb.append( "\n 112\n"+MLeaderPlane2 )
		if( MLeaderPlane3 != null) sb.append( "\n 297\n"+MLeaderPlane3 )
		if( Vertex != null) sb.append( "\n 10\n"+Vertex?.x+"\n 20\n"+Vertex?.y+"\n 30\n"+Vertex?.z )
		if( BreakPoint != null) sb.append( "\n 90\n"+BreakPoint )
		if( TextWidth1 != null) sb.append( "\n 43\n"+TextWidth1 )
		if( TextHeight2 != null) sb.append( "\n 44\n"+TextHeight2 )
		if( TextLine2 != null) sb.append( "\n 45\n"+TextLine2 )
		if( TextLine3 != null) sb.append( "\n 170\n"+TextLine3 )
		if( TextColor1 != null) sb.append( "\n 90\n"+TextColor1 )
		if( TextAttachment1 != null) sb.append( "\n 171\n"+TextAttachment1 )
		if( TextFlow1 != null) sb.append( "\n 172\n"+TextFlow1 )
		if( TextBackground3 != null) sb.append( "\n 91\n"+TextBackground3 )
		if( TextBackground4 != null) sb.append( "\n 141\n"+TextBackground4 )
		if( TextBackground5 != null) sb.append( "\n 92\n"+TextBackground5 )
		if( IsText2 != null) sb.append( "\n 291\n"+IsText2 )
		if( IsText3 != null) sb.append( "\n 292\n"+IsText3 )
		if( TextColumn5 != null) sb.append( "\n 173\n"+TextColumn5 )
		if( UseText1 != null) sb.append( "\n 293\n"+UseText1 )
		if( TextColumn6 != null) sb.append( "\n 142\n"+TextColumn6 )
		if( TextColumn7 != null) sb.append( "\n 143\n"+TextColumn7 )
		if( TextColumn8 != null) sb.append( "\n 294\n"+TextColumn8 )
		if( TextColumn9 != null) sb.append( "\n 144\n"+TextColumn9 )
		if( TextUse1 != null) sb.append( "\n 295\n"+TextUse1 )
		if( HasBlock1 != null) sb.append( "\n 296\n"+HasBlock1 )
		if( BlockContent6 != null) sb.append( "\n 341\n"+BlockContent6 )
		if( BlockContent7 != null) sb.append( "\n 14\n"+BlockContent7?.x+"\n 24\n"+BlockContent7?.y+"\n 34\n"+BlockContent7?.z )
		if( BlockContent8 != null) sb.append( "\n 15\n"+BlockContent8?.x+"\n 25\n"+BlockContent8?.y+"\n 35\n"+BlockContent8?.z )
		if( BlockContent9 != null) sb.append( "\n 16\n"+BlockContent9 )
		if( BlockContent10 != null) sb.append( "\n 46\n"+BlockContent10 )
		if( BLockContent != null) sb.append( "\n 93\n"+BLockContent )
		if( BLockTransformation != null) sb.append( "\n 47\n"+BLockTransformation )
		if( MleaderPlane != null) sb.append( "\n 110\n"+MleaderPlane )
		if( MLeaderPlane4 != null) sb.append( "\n 111\n"+MLeaderPlane4 )
		if( MLeaderPlane5 != null) sb.append( "\n 112\n"+MLeaderPlane5 )
		if( MLeaderPlane6 != null) sb.append( "\n 297\n"+MLeaderPlane6 )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nMLeaderContext")
			sbX.append( sb)
		}

		 return sbX
	}}