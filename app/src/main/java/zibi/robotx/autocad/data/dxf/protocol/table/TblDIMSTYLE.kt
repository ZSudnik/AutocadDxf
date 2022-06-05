package zibi.robotx.autocad.data.dxf.protocol.table

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class TblDIMSTYLE(lastElem: DxfChain, isRead: Boolean) : TblCommon() {



	//	 Dimension style name
	//	2	
	var DimensionStyle: String = ""
		private set

	//	 Standard flag values (bit-coded values):,16 = If set, table entry is externally dependent on an xref,32 = If both this bit and bit 16 are set, the externally dependent xref has been successfully resolved,64 = If set, the table entry was referenced by at least one entity in the drawing the last time the drawing was edited. (This flag is for the benefit of AutoCAD commands. It can be ignored by most programs that read DXF files and need not be set by programs that write DXF files)
	//	70	
	var StandardFlag: Int? = null

	//	 DIMPOST
	//	3	
	var DIMPOST: String? = null

	//	 DIMAPOST
	//	4	
	var DIMAPOST: String? = null

	//	 DIMBLK (obsolete, now object ID)
	//	5	
	var DIMBLKNow: Int? = null

	//	 DIMBLK1 (obsolete, now object ID)
	//	6	
	var DIMBLK1Now: String? = null

	//	 DIMBLK2 (obsolete, now object ID)
	//	7	
	var DIMBLK2Now: String? = null

	//	 DIMSCALE
	//	40	
	var DIMSCALE: Float? = null

	//	 DIMASZ
	//	41	
	var DIMASZ: Float? = null

	//	 DIMEXO
	//	42	
	var DIMEXO: Float? = null

	//	 DIMDLI
	//	43	
	var DIMDLI: Float? = null

	//	 DIMEXE
	//	44	
	var DIMEXE: Float? = null

	//	 DIMRND
	//	45	
	var DIMRND: Float? = null

	//	 DIMDLE
	//	46	
	var DIMDLE: Float? = null

	//	 DIMTP
	//	47	
	var DIMTP: Float? = null

	//	 DIMTM
	//	48	
	var DIMTM: Float? = null

	//	 DIMTXT
	//	140	
	var DIMTXT: Float? = null

	//	 DIMCEN
	//	141	
	var DIMCEN: Float? = null

	//	 DIMTSZ
	//	142	
	var DIMTSZ: Float? = null

	//	 DIMALTF
	//	143	
	var DIMALTF: Float? = null

	//	 DIMLFAC
	//	144	
	var DIMLFAC: Float? = null

	//	 DIMTVP
	//	145	
	var DIMTVP: Float? = null

	//	 DIMTFAC
	//	146	
	var DIMTFAC: Float? = null

	//	 DIMGAP
	//	147	
	var DIMGAP: Float? = null

	//	 DIMALTRND
	//	148	
	var DIMALTRND: Float? = null

	//	 DIMTOL
	//	71	
	var DIMTOL: Int? = null

	//	 DIMLIM
	//	72	
	var DIMLIM: Int? = null

	//	 DIMTIH
	//	73	
	var DIMTIH: Int? = null

	//	 DIMTOH
	//	74	
	var DIMTOH: Int? = null

	//	 DIMSE1
	//	75	
	var DIMSE1: Int? = null

	//	 DIMSE2
	//	76	
	var DIMSE2: Int? = null

	//	 DIMTAD
	//	77	
	var DIMTAD: Int? = null

	//	 DIMZIN
	//	78	
	var DIMZIN: Int? = null

	//	 DIMAZIN
	//	79	
	var DIMAZIN: Int? = null

	//	 DIMALT
	//	170	
	var DIMALT: Int? = null

	//	 DIMALTD
	//	171	
	var DIMALTD: Int? = null

	//	 DIMTOFL
	//	172	
	var DIMTOFL: Int? = null

	//	 DIMSAH
	//	173	
	var DIMSAH: Int? = null

	//	 DIMTIX
	//	174	
	var DIMTIX: Int? = null

	//	 DIMSOXD
	//	175	
	var DIMSOXD: Int? = null

	//	 DIMCLRD
	//	176	
	var DIMCLRD: Int? = null

	//	 DIMCLRE
	//	177	
	var DIMCLRE: Int? = null

	//	 DIMCLRT
	//	178	
	var DIMCLRT: Int? = null

	//	 DIMADEC
	//	179	
	var DIMADEC: Int? = null

	//	 DIMUNIT (obsolete, now use DIMLUNIT AND DIMFRAC)
	//	270	
	var DIMUNITNow: Int? = null

	//	 DIMDEC
	//	271	
	var DIMDEC: Int? = null

	//	 DIMTDEC
	//	272	
	var DIMTDEC: Int? = null

	//	 DIMALTU
	//	273	
	var DIMALTU: Int? = null

	//	 DIMALTTD
	//	274	
	var DIMALTTD: Int? = null

	//	 DIMAUNIT
	//	275	
	var DIMAUNIT: Int? = null

	//	 DIMFRAC
	//	276	
	var DIMFRAC: Int? = null

	//	 DIMLUNIT
	//	277	
	var DIMLUNIT: Int? = null

	//	 DIMDSEP
	//	278	
	var DIMDSEP: Int? = null

	//	 DIMTMOVE
	//	279	
	var DIMTMOVE: Int? = null

	//	 DIMJUST
	//	280	
	var DIMJUST: Int? = null

	//	 DIMSD1
	//	281	
	var DIMSD1: Int? = null

	//	 DIMSD2
	//	282	
	var DIMSD2: Int? = null

	//	 DIMTOLJ
	//	283	
	var DIMTOLJ: Int? = null

	//	 DIMTZIN
	//	284	
	var DIMTZIN: Int? = null

	//	 DIMALTZ
	//	285	
	var DIMALTZ: Int? = null

	//	 DIMALTTZ
	//	286	
	var DIMALTTZ: Int? = null

	//	 DIMFIT (obsolete, now use DIMATFIT and DIMTMOVE)
	//	287	
	var DIMFITNow: Int? = null

	//	 DIMUPT
	//	288	
	var DIMUPT: Int? = null

	//	 DIMATFIT
	//	289	
	var DIMATFIT: Int? = null

	//	 DIMTXSTY (handle of referenced STYLE)
	//	340	
	var DIMTXSTYReferenced: String? = null

	//	 DIMLDRBLK (handle of referenced BLOCK)
	//	341	
	var DIMLDRBLKReferenced: String? = null

	//	 DIMBLK (handle of referenced BLOCK)
	//	342	
	var DIMBLKReferenced: String? = null

	//	 DIMBLK1 (handle of referenced BLOCK)
	//	343	
	var DIMBLK1Referenced: String? = null

	//	 DIMBLK2 (handle of referenced BLOCK)
	//	344	
	var DIMBLK2Referenced: String? = null

	//	 DIMLWD (lineweight enum value)
	//	371	
	var DIMLWDEnum: Int? = null

	//	 DIMLWE (lineweight enum value),
	//	372	
	var DIMLWEEnum: Int? = null

    override fun read(dxfContext: DxfLoaderContext) {
    var cod = dxfContext.get()
    while (true) {
	      if (cod == "ENDTAB") return
			if (super.readX(cod)) {
            when (cod) {
			"2" -> {
				DimensionStyle = dxfContext.stringValue()
			}
			"70" -> {
				StandardFlag = dxfContext.intValue()
			}
			"3" -> {
				DIMPOST = dxfContext.stringValue()
			}
			"4" -> {
				DIMAPOST = dxfContext.stringValue()
			}
			"5" -> {
				DIMBLKNow = dxfContext.intHexValue()
			}
			"6" -> {
				DIMBLK1Now = dxfContext.stringValue()
			}
			"7" -> {
				DIMBLK2Now = dxfContext.stringValue()
			}
			"40" -> {
				DIMSCALE = dxfContext.floatValue()
			}
			"41" -> {
				DIMASZ = dxfContext.floatValue()
			}
			"42" -> {
				DIMEXO = dxfContext.floatValue()
			}
			"43" -> {
				DIMDLI = dxfContext.floatValue()
			}
			"44" -> {
				DIMEXE = dxfContext.floatValue()
			}
			"45" -> {
				DIMRND = dxfContext.floatValue()
			}
			"46" -> {
				DIMDLE = dxfContext.floatValue()
			}
			"47" -> {
				DIMTP = dxfContext.floatValue()
			}
			"48" -> {
				DIMTM = dxfContext.floatValue()
			}
			"140" -> {
				DIMTXT = dxfContext.floatValue()
			}
			"141" -> {
				DIMCEN = dxfContext.floatValue()
			}
			"142" -> {
				DIMTSZ = dxfContext.floatValue()
			}
			"143" -> {
				DIMALTF = dxfContext.floatValue()
			}
			"144" -> {
				DIMLFAC = dxfContext.floatValue()
			}
			"145" -> {
				DIMTVP = dxfContext.floatValue()
			}
			"146" -> {
				DIMTFAC = dxfContext.floatValue()
			}
			"147" -> {
				DIMGAP = dxfContext.floatValue()
			}
			"148" -> {
				DIMALTRND = dxfContext.floatValue()
			}
			"71" -> {
				DIMTOL = dxfContext.intValue()
			}
			"72" -> {
				DIMLIM = dxfContext.intValue()
			}
			"73" -> {
				DIMTIH = dxfContext.intValue()
			}
			"74" -> {
				DIMTOH = dxfContext.intValue()
			}
			"75" -> {
				DIMSE1 = dxfContext.intValue()
			}
			"76" -> {
				DIMSE2 = dxfContext.intValue()
			}
			"77" -> {
				DIMTAD = dxfContext.intValue()
			}
			"78" -> {
				DIMZIN = dxfContext.intValue()
			}
			"79" -> {
				DIMAZIN = dxfContext.intValue()
			}
			"170" -> {
				DIMALT = dxfContext.intValue()
			}
			"171" -> {
				DIMALTD = dxfContext.intValue()
			}
			"172" -> {
				DIMTOFL = dxfContext.intValue()
			}
			"173" -> {
				DIMSAH = dxfContext.intValue()
			}
			"174" -> {
				DIMTIX = dxfContext.intValue()
			}
			"175" -> {
				DIMSOXD = dxfContext.intValue()
			}
			"176" -> {
				DIMCLRD = dxfContext.intValue()
			}
			"177" -> {
				DIMCLRE = dxfContext.intValue()
			}
			"178" -> {
				DIMCLRT = dxfContext.intValue()
			}
			"179" -> {
				DIMADEC = dxfContext.intValue()
			}
			"270" -> {
				DIMUNITNow = dxfContext.intValue()
			}
			"271" -> {
				DIMDEC = dxfContext.intValue()
			}
			"272" -> {
				DIMTDEC = dxfContext.intValue()
			}
			"273" -> {
				DIMALTU = dxfContext.intValue()
			}
			"274" -> {
				DIMALTTD = dxfContext.intValue()
			}
			"275" -> {
				DIMAUNIT = dxfContext.intValue()
			}
			"276" -> {
				DIMFRAC = dxfContext.intValue()
			}
			"277" -> {
				DIMLUNIT = dxfContext.intValue()
			}
			"278" -> {
				DIMDSEP = dxfContext.intValue()
			}
			"279" -> {
				DIMTMOVE = dxfContext.intValue()
			}
			"280" -> {
				DIMJUST = dxfContext.intValue()
			}
			"281" -> {
				DIMSD1 = dxfContext.intValue()
			}
			"282" -> {
				DIMSD2 = dxfContext.intValue()
			}
			"283" -> {
				DIMTOLJ = dxfContext.intValue()
			}
			"284" -> {
				DIMTZIN = dxfContext.intValue()
			}
			"285" -> {
				DIMALTZ = dxfContext.intValue()
			}
			"286" -> {
				DIMALTTZ = dxfContext.intValue()
			}
			"287" -> {
				DIMFITNow = dxfContext.intValue()
			}
			"288" -> {
				DIMUPT = dxfContext.intValue()
			}
			"289" -> {
				DIMATFIT = dxfContext.intValue()
			}
			"340" -> {
				DIMTXSTYReferenced = dxfContext.stringValue()
			}
			"341" -> {
				DIMLDRBLKReferenced = dxfContext.stringValue()
			}
			"342" -> {
				DIMBLKReferenced = dxfContext.stringValue()
			}
			"343" -> {
				DIMBLK1Referenced = dxfContext.stringValue()
			}
			"344" -> {
				DIMBLK2Referenced = dxfContext.stringValue()
			}
			"371" -> {
				DIMLWDEnum = dxfContext.intValue()
			}
			"372" -> {
				DIMLWEEnum = dxfContext.intValue()
			}
			}
		}
			cod = dxfContext.get()
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


	//	 Subclass marker (AcDbDimStyleTableRecord)
	//	100	
	const val AcDbDimStyleTableRecord: String = "AcDbDimStyleTableRecord"
	 }


	 override fun write( sbX: StringBuilder): StringBuilder {
		val sb: StringBuilder = StringBuilder()
		super.write( sb )
		if( DimensionStyle != null) sb.append( "\n 2\n"+DimensionStyle )
		if( StandardFlag != null) sb.append( "\n 70\n"+StandardFlag )
		if( DIMPOST != null) sb.append( "\n 3\n"+DIMPOST )
		if( DIMAPOST != null) sb.append( "\n 4\n"+DIMAPOST )
		if( DIMBLKNow != null) sb.append( "\n 5\n"+Integer.toHexString(DIMBLKNow!!) )
		if( DIMBLK1Now != null) sb.append( "\n 6\n"+DIMBLK1Now )
		if( DIMBLK2Now != null) sb.append( "\n 7\n"+DIMBLK2Now )
		if( DIMSCALE != null) sb.append( "\n 40\n"+DIMSCALE )
		if( DIMASZ != null) sb.append( "\n 41\n"+DIMASZ )
		if( DIMEXO != null) sb.append( "\n 42\n"+DIMEXO )
		if( DIMDLI != null) sb.append( "\n 43\n"+DIMDLI )
		if( DIMEXE != null) sb.append( "\n 44\n"+DIMEXE )
		if( DIMRND != null) sb.append( "\n 45\n"+DIMRND )
		if( DIMDLE != null) sb.append( "\n 46\n"+DIMDLE )
		if( DIMTP != null) sb.append( "\n 47\n"+DIMTP )
		if( DIMTM != null) sb.append( "\n 48\n"+DIMTM )
		if( DIMTXT != null) sb.append( "\n 140\n"+DIMTXT )
		if( DIMCEN != null) sb.append( "\n 141\n"+DIMCEN )
		if( DIMTSZ != null) sb.append( "\n 142\n"+DIMTSZ )
		if( DIMALTF != null) sb.append( "\n 143\n"+DIMALTF )
		if( DIMLFAC != null) sb.append( "\n 144\n"+DIMLFAC )
		if( DIMTVP != null) sb.append( "\n 145\n"+DIMTVP )
		if( DIMTFAC != null) sb.append( "\n 146\n"+DIMTFAC )
		if( DIMGAP != null) sb.append( "\n 147\n"+DIMGAP )
		if( DIMALTRND != null) sb.append( "\n 148\n"+DIMALTRND )
		if( DIMTOL != null) sb.append( "\n 71\n"+DIMTOL )
		if( DIMLIM != null) sb.append( "\n 72\n"+DIMLIM )
		if( DIMTIH != null) sb.append( "\n 73\n"+DIMTIH )
		if( DIMTOH != null) sb.append( "\n 74\n"+DIMTOH )
		if( DIMSE1 != null) sb.append( "\n 75\n"+DIMSE1 )
		if( DIMSE2 != null) sb.append( "\n 76\n"+DIMSE2 )
		if( DIMTAD != null) sb.append( "\n 77\n"+DIMTAD )
		if( DIMZIN != null) sb.append( "\n 78\n"+DIMZIN )
		if( DIMAZIN != null) sb.append( "\n 79\n"+DIMAZIN )
		if( DIMALT != null) sb.append( "\n 170\n"+DIMALT )
		if( DIMALTD != null) sb.append( "\n 171\n"+DIMALTD )
		if( DIMTOFL != null) sb.append( "\n 172\n"+DIMTOFL )
		if( DIMSAH != null) sb.append( "\n 173\n"+DIMSAH )
		if( DIMTIX != null) sb.append( "\n 174\n"+DIMTIX )
		if( DIMSOXD != null) sb.append( "\n 175\n"+DIMSOXD )
		if( DIMCLRD != null) sb.append( "\n 176\n"+DIMCLRD )
		if( DIMCLRE != null) sb.append( "\n 177\n"+DIMCLRE )
		if( DIMCLRT != null) sb.append( "\n 178\n"+DIMCLRT )
		if( DIMADEC != null) sb.append( "\n 179\n"+DIMADEC )
		if( DIMUNITNow != null) sb.append( "\n 270\n"+DIMUNITNow )
		if( DIMDEC != null) sb.append( "\n 271\n"+DIMDEC )
		if( DIMTDEC != null) sb.append( "\n 272\n"+DIMTDEC )
		if( DIMALTU != null) sb.append( "\n 273\n"+DIMALTU )
		if( DIMALTTD != null) sb.append( "\n 274\n"+DIMALTTD )
		if( DIMAUNIT != null) sb.append( "\n 275\n"+DIMAUNIT )
		if( DIMFRAC != null) sb.append( "\n 276\n"+DIMFRAC )
		if( DIMLUNIT != null) sb.append( "\n 277\n"+DIMLUNIT )
		if( DIMDSEP != null) sb.append( "\n 278\n"+DIMDSEP )
		if( DIMTMOVE != null) sb.append( "\n 279\n"+DIMTMOVE )
		if( DIMJUST != null) sb.append( "\n 280\n"+DIMJUST )
		if( DIMSD1 != null) sb.append( "\n 281\n"+DIMSD1 )
		if( DIMSD2 != null) sb.append( "\n 282\n"+DIMSD2 )
		if( DIMTOLJ != null) sb.append( "\n 283\n"+DIMTOLJ )
		if( DIMTZIN != null) sb.append( "\n 284\n"+DIMTZIN )
		if( DIMALTZ != null) sb.append( "\n 285\n"+DIMALTZ )
		if( DIMALTTZ != null) sb.append( "\n 286\n"+DIMALTTZ )
		if( DIMFITNow != null) sb.append( "\n 287\n"+DIMFITNow )
		if( DIMUPT != null) sb.append( "\n 288\n"+DIMUPT )
		if( DIMATFIT != null) sb.append( "\n 289\n"+DIMATFIT )
		if( DIMTXSTYReferenced != null) sb.append( "\n 340\n"+DIMTXSTYReferenced )
		if( DIMLDRBLKReferenced != null) sb.append( "\n 341\n"+DIMLDRBLKReferenced )
		if( DIMBLKReferenced != null) sb.append( "\n 342\n"+DIMBLKReferenced )
		if( DIMBLK1Referenced != null) sb.append( "\n 343\n"+DIMBLK1Referenced )
		if( DIMBLK2Referenced != null) sb.append( "\n 344\n"+DIMBLK2Referenced )
		if( DIMLWDEnum != null) sb.append( "\n 371\n"+DIMLWDEnum )
		if( DIMLWEEnum != null) sb.append( "\n 372\n"+DIMLWEEnum )
		if( sb.isNotEmpty() ) {
 			sbX.append( "\n 0\nDIMSTYLE")
			sbX.append( sb)
		}

		 return sbX
	}

	constructor(lastElem: DxfChain, DimensionStyle: String ): this(lastElem, false){
		this.DimensionStyle = DimensionStyle
	}
}