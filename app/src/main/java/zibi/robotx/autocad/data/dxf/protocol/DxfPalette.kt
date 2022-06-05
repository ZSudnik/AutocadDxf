package zibi.robotx.autocad.data.dxf.protocol

import android.graphics.Color

class DxfPalette {
    /**
     * Get palettetype.
     */
    /**
     * Palette type
     */
    protected var paletteType = PALETTETYPE_UNKNOWN

    /**
     * Default Color palette.
     */
    private var jcolorarr_aci: Array<Color?>? = null

    /**
     * Default Color palette.
     */
    private var jcolorarr_acihighinverse: Array<Color>? = null
    /**
     * Get jcolor_singlecolor.
     */
    /**
     * Single Color value.
     */
    protected var singleColor: Color? = null
        private set

    /**
     * Constructor.
     */
    protected constructor() {
//        setPalette_aci()
    }

    /**
     * Constructor.
     * @param arg_palettetype type of pallette to create.
     * @param arg_obj optional passed Object depends on type.
     */
//    protected constructor(arg_palettetype: Int, arg_obj: Any?) {
//        if (arg_palettetype == PALETTETYPE_ACI) setPalette_aci() else if (arg_palettetype == PALETTETYPE_ACIHIGHINVERSE) setPalette_acihighinverse() else if (arg_palettetype == PALETTETYPE_SINGLECOLOR) {
//            if (arg_obj is String) setPalette_singlecolor(arg_obj as String?) else setPalette_singlecolor(arg_obj as Color?) // Error if not Color
//        } else setPalette_unknown()
//    }

    /**
     * Set palette to default.
     */
//    protected fun setPalette_aci() {
//        if (paletteType == PALETTETYPE_ACI) return
//        if (jcolorarr_aci == null) {
//            jcolorarr_aci = arrayOfNulls(256)
//            fillPaletteArray_aci(jcolorarr_aci)
//        }
//        for (i in 0..255) jcolorarr.get(i) = jcolorarr_aci!![i]
//        paletteType = PALETTETYPE_ACI
//    }

    /**
     * Set palette to default with high inverse (for printing).
     */
//    protected fun setPalette_acihighinverse() {
//        if (paletteType == PALETTETYPE_ACIHIGHINVERSE) return
//        if (jcolorarr_acihighinverse == null) {
//            jcolorarr_acihighinverse = arrayOfNulls(256)
//            fillPaletteArray_aci(jcolorarr_acihighinverse)
//            var col: Color
//            var r: Int
//            var g: Int
//            var b: Int
//            for (i in 0..255) {
//                col = jcolorarr_acihighinverse.get(i)
//                if (col == null) continue
//                r = col.getRed()
//                g = col.getGreen()
//                b = col.getBlue()
//                if (r >= 192 && g >= 192 && b >= 192) jcolorarr_acihighinverse.get(i) = Color(255 - r, 255 - g, 255 - b)
//            }
//        }
//        for (i in 0..255) jcolorarr.get(i) = jcolorarr_acihighinverse!![i]
//        paletteType = PALETTETYPE_ACIHIGHINVERSE
//    }

    /**
     * Set palette to all the same Color from Color value.
     * @param arg_singlecolor_col Color value.
     */
//    protected fun setPalette_singlecolor(arg_singlecolor_col: Color?) {
//        singleColor = arg_singlecolor_col
//        jcolorarr.get(0) = null
//        for (i in 1..255) jcolorarr.get(i) = singleColor
//        paletteType = PALETTETYPE_SINGLECOLOR
//    }

//    /**
//     * Set palette to all the same Color from String value.
//     * @param arg_singlecolor_str Color value.
//     */
//    protected fun setPalette_singlecolor(arg_singlecolor_str: String?) {
//        val col: Color = YutilProperties.convertStringToColor(arg_singlecolor_str)
//        setPalette_singlecolor(col)
//    }

    /**
     * Set palette to unknown (cleared) state.
     */
    protected fun setPalette_unknown() {
//        for (i in 0..255) jcolorarr.get(i) = null
        jcolorarr_aci = null
        jcolorarr_acihighinverse = null
        singleColor = null
        paletteType = PALETTETYPE_UNKNOWN
    }

    /**
     * Fill palette array with aci values.
     * @param parr Palette array to fill.
     */
//    protected fun fillPaletteArray_aci(parr: Array<Color?>) {
//        // ToDo: verify colors
//        parr[0] = null
//        parr[1] = Color.RED
//        parr[2] = Color.yellow
//        parr[3] = Color.green
//        parr[4] = Color.cyan
//        parr[5] = Color.blue
//        parr[6] = Color.magenta
//        parr[7] = Color.white
//        parr[8] = Color(0, 76, 76)
//        parr[9] = Color(192, 192, 192)
//        parr[10] = Color(255, 0, 0)
//        parr[11] = Color(255, 127, 127)
//        parr[12] = Color(165, 0, 0)
//        parr[13] = Color(165, 82, 82)
//        parr[14] = Color(127, 0, 0)
//        parr[15] = Color(127, 63, 63)
//        parr[16] = Color(76, 0, 0)
//        parr[17] = Color(76, 38, 38)
//        parr[18] = Color(38, 0, 0)
//        parr[19] = Color(38, 19, 19)
//        parr[20] = Color(255, 63, 0)
//        parr[21] = Color(255, 159, 127)
//        parr[22] = Color(165, 41, 0)
//        parr[23] = Color(165, 103, 82)
//        parr[24] = Color(127, 31, 0)
//        parr[25] = Color(127, 79, 63)
//        parr[26] = Color(76, 19, 0)
//        parr[27] = Color(76, 48, 38)
//        parr[28] = Color(38, 9, 0)
//        parr[29] = Color(38, 23, 19)
//        parr[30] = Color(255, 127, 0)
//        parr[31] = Color(255, 191, 127)
//        parr[32] = Color(165, 82, 0)
//        parr[33] = Color(165, 124, 82)
//        parr[34] = Color(127, 63, 0)
//        parr[35] = Color(127, 95, 63)
//        parr[36] = Color(76, 38, 0)
//        parr[37] = Color(76, 57, 38)
//        parr[38] = Color(38, 19, 0)
//        parr[39] = Color(38, 28, 19)
//        parr[40] = Color(255, 191, 0)
//        parr[41] = Color(255, 223, 125)
//        parr[42] = Color(165, 124, 0)
//        parr[43] = Color(165, 145, 82)
//        parr[44] = Color(127, 95, 0)
//        parr[45] = Color(127, 111, 63)
//        parr[46] = Color(76, 57, 0)
//        parr[47] = Color(76, 66, 38)
//        parr[48] = Color(38, 0, 0)
//        parr[49] = Color(38, 28, 0)
//        parr[50] = Color(255, 255, 0)
//        parr[51] = Color(255, 255, 127)
//        parr[52] = Color(165, 165, 0)
//        parr[53] = Color(165, 165, 82)
//        parr[54] = Color(127, 127, 0)
//        parr[55] = Color(127, 127, 63)
//        parr[56] = Color(76, 76, 0)
//        parr[57] = Color(76, 76, 38)
//        parr[58] = Color(38, 38, 0)
//        parr[59] = Color(38, 38, 19)
//        parr[60] = Color(191, 255, 0)
//        parr[61] = Color(223, 255, 127)
//        parr[62] = Color(124, 165, 0)
//        parr[63] = Color(145, 165, 82)
//        parr[64] = Color(95, 127, 0)
//        parr[65] = Color(111, 127, 63)
//        parr[66] = Color(57, 76, 0)
//        parr[67] = Color(66, 76, 38)
//        parr[68] = Color(28, 38, 0)
//        parr[69] = Color(33, 38, 19)
//        parr[70] = Color(127, 255, 0)
//        parr[71] = Color(191, 255, 127)
//        parr[72] = Color(82, 165, 0)
//        parr[73] = Color(124, 165, 82)
//        parr[74] = Color(63, 127, 0)
//        parr[75] = Color(95, 127, 63)
//        parr[76] = Color(38, 76, 0)
//        parr[77] = Color(57, 76, 38)
//        parr[78] = Color(19, 38, 0)
//        parr[79] = Color(28, 38, 19)
//        parr[80] = Color(63, 255, 0)
//        parr[81] = Color(159, 255, 127)
//        parr[82] = Color(41, 165, 0)
//        parr[83] = Color(103, 165, 82)
//        parr[84] = Color(31, 127, 0)
//        parr[85] = Color(79, 127, 63)
//        parr[86] = Color(19, 76, 0)
//        parr[87] = Color(47, 76, 38)
//        parr[88] = Color(9, 38, 0)
//        parr[89] = Color(23, 38, 19)
//        parr[90] = Color(0, 255, 0)
//        parr[91] = Color(127, 255, 127)
//        parr[92] = Color(0, 165, 0)
//        parr[93] = Color(82, 165, 82)
//        parr[94] = Color(0, 127, 0)
//        parr[95] = Color(63, 127, 63)
//        parr[96] = Color(0, 76, 0)
//        parr[97] = Color(38, 76, 38)
//        parr[98] = Color(0, 38, 0)
//        parr[99] = Color(19, 38, 19)
//        parr[100] = Color(0, 255, 63)
//        parr[101] = Color(127, 255, 159)
//        parr[102] = Color(0, 165, 41)
//        parr[103] = Color(82, 165, 103)
//        parr[104] = Color(0, 127, 31)
//        parr[105] = Color(63, 127, 79)
//        parr[106] = Color(0, 76, 19)
//        parr[107] = Color(38, 76, 47)
//        parr[108] = Color(0, 38, 9)
//        parr[109] = Color(19, 38, 23)
//        parr[110] = Color(0, 255, 127)
//        parr[111] = Color(127, 255, 191)
//        parr[112] = Color(0, 165, 82)
//        parr[113] = Color(82, 165, 124)
//        parr[114] = Color(0, 127, 63)
//        parr[115] = Color(63, 127, 95)
//        parr[116] = Color(0, 76, 38)
//        parr[117] = Color(38, 76, 57)
//        parr[118] = Color(0, 38, 19)
//        parr[119] = Color(19, 38, 28)
//        parr[120] = Color(0, 255, 191)
//        parr[121] = Color(127, 255, 223)
//        parr[122] = Color(0, 165, 124)
//        parr[123] = Color(82, 165, 145)
//        parr[124] = Color(0, 127, 95)
//        parr[125] = Color(63, 127, 111)
//        parr[126] = Color(0, 76, 57)
//        parr[127] = Color(38, 76, 66)
//        parr[128] = Color(0, 38, 28)
//        parr[129] = Color(19, 38, 33)
//        parr[130] = Color(0, 255, 191)
//        parr[131] = Color(127, 255, 255)
//        parr[132] = Color(0, 165, 165)
//        parr[133] = Color(82, 165, 165)
//        parr[134] = Color(0, 127, 127)
//        parr[135] = Color(63, 127, 127)
//        parr[136] = Color(0, 76, 76)
//        parr[137] = Color(38, 76, 76)
//        parr[138] = Color(0, 38, 38)
//        parr[139] = Color(19, 38, 38)
//        parr[140] = Color(0, 191, 255)
//        parr[141] = Color(127, 223, 255)
//        parr[142] = Color(0, 124, 165)
//        parr[143] = Color(82, 145, 165)
//        parr[144] = Color(0, 95, 127)
//        parr[145] = Color(63, 111, 127)
//        parr[146] = Color(0, 57, 76)
//        parr[147] = Color(38, 66, 76)
//        parr[148] = Color(0, 28, 38)
//        parr[149] = Color(19, 33, 38)
//        parr[150] = Color(0, 127, 255)
//        parr[151] = Color(127, 191, 255)
//        parr[152] = Color(0, 82, 165)
//        parr[153] = Color(82, 124, 165)
//        parr[154] = Color(0, 63, 127)
//        parr[155] = Color(63, 95, 127)
//        parr[156] = Color(0, 38, 76)
//        parr[157] = Color(38, 57, 76)
//        parr[158] = Color(0, 19, 38)
//        parr[159] = Color(19, 28, 38)
//        parr[160] = Color(0, 63, 255)
//        parr[161] = Color(127, 159, 255)
//        parr[162] = Color(0, 41, 165)
//        parr[163] = Color(82, 103, 165)
//        parr[164] = Color(0, 31, 127)
//        parr[165] = Color(63, 79, 127)
//        parr[166] = Color(0, 19, 76)
//        parr[167] = Color(38, 47, 76)
//        parr[168] = Color(0, 9, 38)
//        parr[169] = Color(19, 23, 38)
//        parr[170] = Color(0, 0, 255)
//        parr[171] = Color(127, 127, 255)
//        parr[172] = Color(0, 0, 165)
//        parr[173] = Color(82, 82, 165)
//        parr[174] = Color(0, 0, 127)
//        parr[175] = Color(63, 63, 127)
//        parr[176] = Color(0, 0, 76)
//        parr[177] = Color(38, 38, 76)
//        parr[178] = Color(0, 0, 38)
//        parr[179] = Color(19, 19, 38)
//        parr[180] = Color(63, 0, 255)
//        parr[181] = Color(159, 127, 255)
//        parr[182] = Color(41, 0, 165)
//        parr[183] = Color(103, 82, 165)
//        parr[184] = Color(31, 0, 127)
//        parr[185] = Color(79, 63, 127)
//        parr[186] = Color(19, 0, 76)
//        parr[187] = Color(47, 38, 76)
//        parr[188] = Color(9, 0, 38)
//        parr[189] = Color(23, 19, 38)
//        parr[190] = Color(127, 0, 255)
//        parr[191] = Color(191, 127, 255)
//        parr[192] = Color(82, 0, 165)
//        parr[193] = Color(124, 82, 165)
//        parr[194] = Color(63, 0, 127)
//        parr[195] = Color(95, 63, 127)
//        parr[196] = Color(38, 0, 76)
//        parr[197] = Color(57, 38, 76)
//        parr[198] = Color(19, 0, 38)
//        parr[199] = Color(28, 19, 38)
//        parr[200] = Color(191, 0, 255)
//        parr[201] = Color(223, 127, 255)
//        parr[202] = Color(124, 0, 165)
//        parr[203] = Color(145, 82, 165)
//        parr[204] = Color(95, 0, 127)
//        parr[205] = Color(111, 63, 127)
//        parr[206] = Color(57, 0, 76)
//        parr[207] = Color(66, 38, 76)
//        parr[208] = Color(28, 0, 38)
//        parr[209] = Color(33, 19, 38)
//        parr[210] = Color(255, 0, 255)
//        parr[211] = Color(255, 127, 255)
//        parr[212] = Color(165, 0, 165)
//        parr[213] = Color(165, 82, 165)
//        parr[214] = Color(127, 0, 127)
//        parr[215] = Color(127, 63, 127)
//        parr[216] = Color(76, 0, 76)
//        parr[217] = Color(76, 38, 76)
//        parr[218] = Color(38, 0, 38)
//        parr[219] = Color(38, 19, 38)
//        parr[220] = Color(255, 0, 191)
//        parr[221] = Color(255, 127, 223)
//        parr[222] = Color(165, 0, 124)
//        parr[223] = Color(165, 82, 145)
//        parr[224] = Color(127, 0, 95)
//        parr[225] = Color(127, 63, 111)
//        parr[226] = Color(76, 0, 57)
//        parr[227] = Color(76, 38, 66)
//        parr[228] = Color(38, 0, 28)
//        parr[229] = Color(38, 19, 33)
//        parr[230] = Color(255, 0, 127)
//        parr[231] = Color(255, 127, 191)
//        parr[232] = Color(165, 0, 82)
//        parr[233] = Color(165, 82, 124)
//        parr[234] = Color(127, 0, 63)
//        parr[235] = Color(127, 63, 95)
//        parr[236] = Color(76, 0, 38)
//        parr[237] = Color(76, 38, 57)
//        parr[238] = Color(38, 0, 19)
//        parr[239] = Color(38, 19, 28)
//        parr[240] = Color(255, 0, 63)
//        parr[241] = Color(255, 127, 159)
//        parr[242] = Color(165, 0, 41)
//        parr[243] = Color(165, 82, 103)
//        parr[244] = Color(127, 0, 31)
//        parr[245] = Color(127, 63, 79)
//        parr[246] = Color(76, 0, 19)
//        parr[247] = Color(76, 38, 47)
//        parr[248] = Color(38, 0, 9)
//        parr[249] = Color(38, 19, 23)
//        parr[250] = Color(84, 84, 84)
//        parr[251] = Color(118, 118, 118)
//        parr[252] = Color(160, 160, 160)
//        parr[253] = Color(192, 192, 192)
//        parr[254] = Color(222, 200, 222)
//        parr[255] = Color(255, 255, 255)
//    }

    /**
     * Get jcolorarr.
     */
//    protected val paletteArray: Array<Color>
//        protected get() = jcolorarr

    companion object {
        /**
         * AutoCAD Color Index by block.
         */
        const val ACI_BYBLOCK = 0

        /**
         * AutoCAD Color Index by layer.
         */
        const val ACI_BYLAYER = 256

        /**
         * AutoCAD Color Index red.
         */
        const val ACI_RED = 1

        /**
         * AutoCAD Color Index yellow.
         */
        const val ACI_YELLOW = 2

        /**
         * AutoCAD Color Index green.
         */
        const val ACI_GREEN = 3

        /**
         * AutoCAD Color Index cyan.
         */
        const val ACI_CYAN = 4

        /**
         * AutoCAD Color Index blue.
         */
        const val ACI_BLUE = 5

        /**
         * AutoCAD Color Index magenta.
         */
        const val ACI_MAGENTA = 6

        /**
         * AutoCAD Color Index white.
         */
        const val ACI_WHITE = 7
        /**
         * Color palette.
         */
        //    public Color    jcolorarr[] = new Color[256];
        /**
         * Palette type EVENTREQUESTCODE_UNKNOWN
         */
        const val PALETTETYPE_UNKNOWN = 0

        /**
         * Palette type DEFAULT
         */
        const val PALETTETYPE_ACI = 1

        /**
         * Palette type DEFAULT_HIGHINV
         */
        const val PALETTETYPE_ACIHIGHINVERSE = 2

        /**
         * Palette type SINGLECOLOR
         */
        const val PALETTETYPE_SINGLECOLOR = 3
    }
}