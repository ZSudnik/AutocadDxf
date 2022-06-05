package zibi.robotx.autocad.data.dxf.util

import zibi.robotx.autocad.data.dxf.protocol.*
import javax.vecmath.Vector2f
import javax.vecmath.Vector3f

fun contextHeader( header: SecHeader){
    header.ACADVER= "AC1027"
    header.ACADMAINTVER = 81
    header.DWGCODEPAGE="ANSI_1252"
//    header.LASTSAVEDBY="Viewer3D"
//    header.REQUIREDVERSIONS=0
    header.INSBASE = Vector3f( 0.0f, 0.0f, 0.0f)
    header.EXTMIN = Vector3f( 74.4996639211449f, 52.10826176515015f, -14.6159509252741f)
    header.EXTMAX = Vector3f( 146.7502179028872f, 84.62875251634384f, 8.750168454515511f)
    header.LIMMIN = Vector2f( 0.0f, 0.0f)
    header.LIMMAX = Vector2f( 12.0f, 0.0f)
    header.ORTHOMODE = 0
    header.REGENMODE = 1
    header.FILLMODE = 1
    header.QTEXTMODE = 0
    header.MIRRTEXT = 0
    header.LTSCALE = 1.0f
    header.ATTMODE = 1
    header.TEXTSIZE = 0.2f
    header.TRACEWID = 0.05f
    header.TEXTSTYLE = "Standard"
    header.CLAYER = "TR2"
    header.CELTYPE = "ByLayer"
    header.CECOLOR = 256
    header.CELTSCALE = 1.0f
    header.DISPSILH = 0
    header.DIMSCALE = 1.0f
    header.DIMASZ = 0.18f
    header.DIMEXO = 0.0625f
    header.DIMDLI = 0.38f
    header.DIMRND = 0.0f
    header.DIMDLE = 0.0f
    header.DIMEXE = 0.18f
    header.DIMTP = 0.0f
    header.DIMTM = 0.0f
    header.DIMTXT = 0.18f
    header.DIMCEN = 0.09f
    header.DIMTSZ = 0.0f
    header.DIMTOL = 0
    header.DIMLIM = 0
    header.DIMTIH = 1
    header.DIMTOH = 1
    header.DIMSE1 = 0
    header.DIMSE2 = 0
    header.DIMTAD = 0
    header.DIMZIN = 0
    header.DIMBLK = ""
    header.DIMASO = 1
    header.DIMSHO = 1
    header.DIMPOST = ""
    header.DIMAPOST = ""
    header.DIMALT = 0
    header.DIMALTD = 2
    header.DIMALTF = 25.4f
    header.DIMLFAC = 1.0f
    header.DIMTOFL = 0
    header.DIMTVP = 0.0f
    header.DIMTIX = 0
    header.DIMSOXD = 0
    header.DIMSAH = 0
    header.DIMBLK1 = ""
    header.DIMBLK2 = ""
    header.DIMSTYLE = "Standard"
    header.DIMCLRD = 0
    header.DIMCLRE = 0
    header.DIMCLRT = 0
    header.DIMTFAC = 1.0f
    header.DIMGAP = 0.09f
    header.DIMJUST = 0
    header.DIMSD1 = 0
    header.DIMSD2 = 0
    header.DIMTOLJ = 0
    header.DIMTZIN = 0
    header.DIMALTZ = 0
    header.DIMALTTZ = 0
    header.DIMUPT = 0
    header.DIMDEC = 4
    header.DIMTDEC = 4
    header.DIMALTU = 2
    header.DIMALTTD = 2
    header.DIMTXSTY = "Standard"
    header.DIMAUNIT = 0
    header.DIMADEC = 0
    header.DIMALTRND = 0.0f
    header.DIMAZIN = 0
    header.DIMDSEP = 46
    header.DIMATFIT = 3
//    header.DIMFRAC = 0
    header.DIMLDRBLK = ""
    header.DIMLUNIT = 2
    header.DIMLWD = -2
    header.DIMLWE = -2
    header.DIMTMOVE = 0
//    header.DIMFXL = 1.0f
//    header.DIMFXLON = 0
//    header.DIMJOGANG = 0.7853981633974483
//    header.DIMTFILL = 0
//    header.DIMTFILLCLR = 0
//    header.DIMARCSYM = 0
//    header.DIMLTYPE = ""
//    header.DIMLTEX1 = ""
//    header.DIMLTEX2 = ""
//    header.DIMTXTDIRECTION = 0
    header.LUNITS = 2
    header.LUPREC = 4
    header.SKETCHINC = 0.1f
    header.FILLETRAD = 0.0f
    header.AUNITS = 0
    header.AUPREC = 0
    header.MENU = "."
    header.ELEVATION = 0.0f
    header.PELEVATION = 0.0f
    header.THICKNESS = 0.0f
    header.LIMCHECK = 0
    header.CHAMFERA = 0.0f
    header.CHAMFERB = 0.0f
    header.CHAMFERC = 0.0f
    header.CHAMFERD = 0.0f
    header.SKPOLY = 0
    header.TDCREATE = 2459531.246407685f
    header.TDUCREATE = 2459531.204741018f
    header.TDUPDATE = 2459531.403892188f
    header.TDUUPDATE = 2459531.362225521f
    header.TDINDWG = 0.1514944213f
    header.TDUSRTIMER = 0.1514943981f
    header.USRTIMER = 1
    header.ANGBASE = 0.0f
    header.ANGDIR = 0
    header.PDMODE = 0
    header.PDSIZE = 0.0f
    header.PLINEWID = 0.0f
//    header.SPLFRAME = 0
    header.SPLINETYPE = 6
    header.SPLINESEGS = 8
    header.HANDSEED = 240
    header.SURFTAB1 = 6
    header.SURFTAB2 = 6
    header.SURFTYPE = 6
    header.SURFU = 6
    header.SURFV = 6
    header.UCSBASE = ""
    header.UCSNAME = ""
    header.UCSORG = Vector3f( 0.0f, 0.0f, 0.0f)
    header.UCSXDIR = Vector3f( 1.0f, 0.0f, 0.0f)
    header.UCSYDIR = Vector3f( 0.0f, 0.0f, 0.0f)
    header.UCSORTHOREF = ""
    header.UCSORTHOVIEW = 0
    header.UCSORGTOP = Vector3f( 0.0f, 0.0f, 0.0f)
    header.UCSORGBOTTOM = Vector3f( 0.0f, 0.0f, 0.0f)
    header.UCSORGLEFT = Vector3f( 0.0f, 0.0f, 0.0f)
    header.UCSORGRIGHT = Vector3f( 0.0f, 0.0f, 0.0f)
    header.UCSORGFRONT = Vector3f( 0.0f, 0.0f, 0.0f)
    header.UCSORGBACK = Vector3f( 0.0f, 0.0f, 0.0f)
    header.PUCSBASE = ""
    header.PUCSNAME = ""
    header.PUCSORG = Vector3f( 0.0f, 0.0f, 0.0f)
    header.PUCSXDIR = Vector3f( 1.0f, 0.0f, 0.0f)
    header.PUCSYDIR = Vector3f( 0.0f, 0.0f, 0.0f)
    header.PUCSORTHOREF = ""
    header.PUCSORTHOVIEW = 0
    header.PUCSORGTOP = Vector3f( 0.0f, 0.0f, 0.0f)
    header.PUCSORGBOTTOM = Vector3f( 0.0f, 0.0f, 0.0f)
    header.PUCSORGLEFT = Vector3f( 0.0f, 0.0f, 0.0f)
    header.PUCSORGRIGHT = Vector3f( 0.0f, 0.0f, 0.0f)
    header.PUCSORGFRONT = Vector3f( 0.0f, 0.0f, 0.0f)
    header.PUCSORGBACK = Vector3f( 0.0f, 0.0f, 0.0f)
    header.USERI1 = 0
//    header.USERI2 = 0
//    header.USERI3 = 0
//    header.USERI4 = 0
//    header.USERI5 = 0
    header.USERR1 = 0.0f
//    header.USERR2 = 0.0
//    header.USERR3 = 0.0
//    header.USERR4 = 0.0
//    header.USERR5 = 0.0
    header.WORLDVIEW = 1
    header.SHADEDGE = 3
    header.SHADEDIF = 70
    header.TILEMODE = 1
    header.MAXACTVP = 64
    header.PINSBASE = Vector3f( 0.0f, 0.0f, 0.0f)
    header.PLIMCHECK = 0
    header.PEXTMIN = Vector3f( 0.0f, 0.0f, 0.0f)
    header.PEXTMAX  = Vector3f( 0.0f, 0.0f, 0.0f)
    header.PLIMMIN = Vector2f( 0.0f, 0.0f)
    header.PLIMMAX = Vector2f( 12.0f, 9.0f)
//    header.UNITMODE = 0
    header.VISRETAIN = 1
    header.PLINEGEN = 0
    header.PSLTSCALE = 1
    header.TREEDEPTH = 3020
    header.CMLSTYLE = "Standard"
    header.CMLJUST = 0
    header.CMLSCALE = 1.0f
    header.PROXYGRAPHICS = 1
    header.MEASUREMENT = 0
    header.CELWEIGHT = -1
    header.ENDCAPS = 0
    header.JOINSTYLE = 0
    header.LWDISPLAY = false
    header.INSUNITS = 1
    header.HYPERLINKBASE = ""
//    header.STYLESHEET = ""
    header.XEDIT = true
    header.CEPSNTYPE = 0
    header.PSTYLEMODE = false
    header.FINGERPRINTGUID = "{5361B790-0021-477E-B9F9-0002FCE2CC1B}"
    header.VERSIONGUID = "{FF306DAF-DC58-4DC4-97CD-000CBCA9222B}"
    header.EXTNAMES = true
    header.PSVPSCALE = 0.0f
//    header.OLESTARTUP = 0
    header.SORTENTS = 127
    header.INDEXCTL = 0
    header.HIDETEXT = true
    header.XCLIPFRAME = true//2f check type
    header.HALOGAP = 0
    header.OBSCOLOR = 257
    header.OBSLTYPE = 0
    header.INTERSECTIONDISPLAY = false
    header.INTERSECTIONCOLOR = 257
    header.DIMASSOC = 2
    header.PROJECTNAME = ""
//    header.CAMERADISPLAY = 0
//    header.LENSLENGTH = 50.0
//    header.CAMERAHEIGHT = 60.0
//    header.STEPSPERSEC = 2.0
//    header.STEPSIZE = 6.0
//    header.3DDWFPREC = 2.0f
//    header.PSOLWIDTH =0.25
//    header.PSOLHEIGHT = 4.0
//    header.LOFTANG1 = 1.570796326794896
//    header.LOFTANG2 = 1.570796326794896
//    header.LOFTMAG1 = 0.0
//    header.LOFTMAG2 = 0.0
//    header.LOFTPARAM = 7
//    header.LOFTNORMALS = 1
//    header.LATITUDE = 37.795
//    header.LONGITUDE = -122.394
//    header.NORTHDIRECTION = 0.0
//    header.TIMEZONE = -8000
//    header.LIGHTGLYPHDISPLAY = 1
//    header.TILEMODELIGHTSYNCH = 1
//    header.CMATERIAL = 92
//    header.SOLIDHIST = 0
//    header.SHOWHIST = 1
//    header.DWFFRAME = 2
//    header.DGNFRAME = 0
//    header.REALWORLDSCALE = 1
    header.INTERFERECOLOR = 1
    header.INTERFEREOBJVS = "9F"
    header.INTERFEREVPVS = "9C"
    header.CSHADOW = 0
    header.SHADOWPLANELOCATION = 0.0f

}

fun contextClasses( classes: SecClasses, overwrite: Boolean){

    var mClass: ClClass?
    var nameDXFClass = "ACDBDICTIONARYWDFLT"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass( nameDXFClass )
        mClass.NameCClass = "AcDbDictionaryWithDefault"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 0
        mClass.InstanceCount = 1
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "DICTIONARYVAR"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbDictionaryVar"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 0
        mClass.InstanceCount = 11
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "MATERIAL"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbMaterial"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 1153
        mClass.InstanceCount = 3
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "VISUALSTYLE"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbVisualStyle"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 4095
        mClass.InstanceCount = 24
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "TABLESTYLE"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbTableStyle"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 4095
        mClass.InstanceCount = 1
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "SCALE"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbScale"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 1153
        mClass.InstanceCount = 33
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "MLEADERSTYLE"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbMLeaderStyle"
        mClass.AppName = "ACDB_MLEADERSTYLE_CLASS"
        mClass.ProxyFlag = 4095
        mClass.InstanceCount = 2
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "CELLSTYLEMAP"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbCellStyleMap"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 1152
        mClass.InstanceCount = 5
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "EXACXREFPANELOBJECT"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "ExAcXREFPanelObject"
        mClass.AppName = "EXAC_ESW"
        mClass.ProxyFlag = 1025
        mClass.InstanceCount = 0
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "NPOCOLLECTION"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbImpNonPersistentObjectsCollection"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 1153
        mClass.InstanceCount = 2
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "LAYER_INDEX"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbLayerIndex"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 0
        mClass.InstanceCount = 0
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "SPATIAL_INDEX"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbSpatialIndex"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 0
        mClass.InstanceCount = 0
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "IDBUFFER"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbIdBuffer"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 0
        mClass.InstanceCount = 0
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "ACDBSECTIONVIEWSTYLE"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbSectionViewStyle"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 1025
        mClass.InstanceCount = 1
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "ACDBDETAILVIEWSTYLE"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbDetailViewStyle"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 1025
        mClass.InstanceCount = 1
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "SORTENTSTABLE"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbSortentsTable"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 0
        mClass.InstanceCount = 1
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "SOLID_BACKGROUND"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbSolidBackground"
        mClass.AppName = "SCENEOE"
        mClass.ProxyFlag = 4095
        mClass.InstanceCount = 2
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "ACDBASSOCPERSSUBENTMANAGER"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbAssocPersSubentManager"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 1024
        mClass.InstanceCount = 1
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "ACDBPERSSUBENTMANAGER"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbPersSubentManager"
        mClass.AppName = "AcDbPersSubentManager"
        mClass.ProxyFlag = 1024
        mClass.InstanceCount = 1
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "ACSH_BOX_CLASS"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbShBox"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 1153
        mClass.InstanceCount = 1
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "ACAD_EVALUATION_GRAPH"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbEvalGraph"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 1153
        mClass.InstanceCount = 1
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }
////
    nameDXFClass = "ACSH_HISTORY_CLASS"
    if( classes.isNoClass(nameDXFClass )  || overwrite) {
        mClass = classes.getClClass(nameDXFClass)
        mClass.NameCClass = "AcDbShHistory"
        mClass.AppName = "ObjectDBX Classes"
        mClass.ProxyFlag = 1153
        mClass.InstanceCount = 1
        mClass.WasProxyFlag = 0
        mClass.EntityFlaf = 0
    }

}

fun contextBlocks( blocks: SecBlocks, overwrite: Boolean) {

    var mBlock: BlkBlock?
    var BlockName = "*Model_Space"
    if (blocks.isNoBlock(BlockName) || overwrite) {
        mBlock = blocks.getBlkBlock(BlockName)
        mBlock.Flag = 0
        mBlock.BasePoint = Vector3f(0f, 0f, 0f)
        mBlock.BlockName2 = "*Model_Space"
        mBlock.PathNameXref = ""
    }
    ///
    BlockName = "*Paper_Space"
    if (blocks.isNoBlock(BlockName) || overwrite) {
        mBlock = blocks.getBlkBlock(BlockName)
        mBlock.Flag = 0
        mBlock.BasePoint = Vector3f(0f, 0f, 0f)
        mBlock.BlockName2 = "*Paper_Space"
        mBlock.PathNameXref = ""
    }
////
    BlockName = "*Paper_Space0"
    if (blocks.isNoBlock(BlockName) || overwrite) {
        mBlock = blocks.getBlkBlock(BlockName)
        mBlock.Flag = 0
        mBlock.BasePoint = Vector3f(0f, 0f, 0f)
        mBlock.BlockName2 = "*Paper_Space0"
        mBlock.PathNameXref = ""
    }
}

fun contextTables( tables: SecTables, overwrite: Boolean) {

    val ViewportName = "*Active"
    if (tables.isNoVPORT(ViewportName) || overwrite) {
        val mTable = tables.getVPORT(ViewportName)
        mTable.StandardFlag = 0
        mTable.LowerleftCorner = Vector2f(0.0f, 0.0f)
        mTable.UpperrightCorner = Vector2f(1.0f, 1.0f)
        mTable.ViewCenter = Vector2f(0.0f, 0.0f)
        mTable.SnapBase = Vector2f(0.0f, 0.0f)
        mTable.SnapSpacing = Vector2f(0.5f, 0.5f)
        mTable.GridSpacing = Vector2f(0.5f, 0.5f)
        mTable.ViewDirection = Vector3f(287.0899049460878f, -191.3932699640585f, 133.975288974841f)
        mTable.ViewTarget = Vector3f(4.195187165775401f, 5.703208556149733f, -0.8422459893048126f)
        mTable.LensLength = 50.00000000000003f
        mTable.FrontClipping = 0.0f
        mTable.BackClipping = 0.0f
        mTable.SnapRotation = 0.0f
        mTable.ViewTwist = 0.0000000000000387f
        mTable.ViewMode = 1
        mTable.CircleSides = 1000
        mTable.UCSICONSetting = 3
        mTable.RenderMode0 = 4
        mTable.UCSOriginDXF = Vector3f(0.0f, 0.0f, 0.0f)
        mTable.UCSXaxisDXF = Vector3f(1.0f, 0.0f, 0.0f)
        mTable.UCSYaxisDXF = Vector3f(0.0f, 1.0f, 0.0f)
        mTable.OrthographicType = 0
        mTable.Elevation = 0.0f
        mTable.HardpointerIDhandle = "A3"
        mTable.MajorGrid = 5
        mTable.DefaultLighting = 1
        mTable.DefaultLighting1 = 1
        mTable.Brightness = 0.0f
        mTable.Contrast = 0.0f
        mTable.AmbientColor = 250
    }

    var LinetypeName = "ByBlock"
    if (tables.isNoLTYPE(LinetypeName) || overwrite) {
        val mTable = tables.getLTYPE(LinetypeName)
        mTable.StandardFlag = 0
        mTable.DescriptiveText = ""
        mTable.AlignmentCode = 65
        mTable.TheNumber = 0
        mTable.TotalPattern = 0.0f
    }
    LinetypeName = "ByLayer"
    if (tables.isNoLTYPE(LinetypeName) || overwrite) {
        val mTable = tables.getLTYPE(LinetypeName)
        mTable.StandardFlag = 0
        mTable.DescriptiveText = ""
        mTable.AlignmentCode = 65
        mTable.TheNumber = 0
        mTable.TotalPattern = 0.0f
    }
    LinetypeName = "Continuous"
    if (tables.isNoLTYPE(LinetypeName) || overwrite) {
        val mTable = tables.getLTYPE(LinetypeName)
        mTable.StandardFlag = 0
        mTable.DescriptiveText = "Solid line"
        mTable.AlignmentCode = 65
        mTable.TheNumber = 0
        mTable.TotalPattern = 0.0f
    }

    val LayerName = "0"
    if (tables.isNoLAYER(LayerName) || overwrite) {
        val mTable = tables.getLAYER(LayerName)
        mTable.StandardFlags = 0
        mTable.ColorNumber = 254
        mTable.LinetypeName = "Continuous"
        mTable.LineweightEnum = -3
        mTable.HardpointerIDhandle = "F"
        mTable.HardpointerIDhandle1 = "98"
    }

    var StyleName = "Standard"
    if (tables.isNoSTYLE(StyleName) || overwrite) {
        val mTable = tables.getSTYLE(StyleName)
        mTable.StandardFlag = 0
        mTable.FixedText = 0.0f
        mTable.WidthFactor = 1.0f
        mTable.ObliqueAngle = 0.0f
        mTable.TextGeneration = 0
        mTable.LastHeight = 0.2f
        mTable.PrimaryFont = "arial.ttf"
        mTable.BigfontFile = ""
    }
    StyleName = "Annotative"
    if (tables.isNoSTYLE(StyleName) || overwrite) {
        val mTable = tables.getSTYLE(StyleName)
        mTable.StandardFlag = 0
        mTable.FixedText = 0.0f
        mTable.WidthFactor = 1.0f
        mTable.ObliqueAngle = 0.0f
        mTable.TextGeneration = 0
        mTable.LastHeight = 0.2f
        mTable.PrimaryFont = "arial.ttf"
        mTable.BigfontFile = ""
    }

    var UsersuppliedApplicationsupplied = "ACAD"
    if (tables.isNoAPPID(UsersuppliedApplicationsupplied) || overwrite) {
        val mTable = tables.getAPPID(UsersuppliedApplicationsupplied)
        mTable.StandardFlag = 0
    }
    UsersuppliedApplicationsupplied = "ACAD_EXEMPT_FROM_CAD_STANDARDS"
    if (tables.isNoAPPID(UsersuppliedApplicationsupplied) || overwrite) {
        val mTable = tables.getAPPID(UsersuppliedApplicationsupplied)
        mTable.StandardFlag = 0
    }
    UsersuppliedApplicationsupplied = "AcadAnnoPO"
    if (tables.isNoAPPID(UsersuppliedApplicationsupplied) || overwrite) {
        val mTable = tables.getAPPID(UsersuppliedApplicationsupplied)
        mTable.StandardFlag = 0
    }
    UsersuppliedApplicationsupplied = "AcadAnnotative"
    if (tables.isNoAPPID(UsersuppliedApplicationsupplied) || overwrite) {
        val mTable = tables.getAPPID(UsersuppliedApplicationsupplied)
        mTable.StandardFlag = 0
    }
    UsersuppliedApplicationsupplied = "ACAD_DSTYLE_DIMJAG"
    if (tables.isNoAPPID(UsersuppliedApplicationsupplied) || overwrite) {
        val mTable = tables.getAPPID(UsersuppliedApplicationsupplied)
        mTable.StandardFlag = 0
    }
    UsersuppliedApplicationsupplied = "ACAD_DSTYLE_DIMTALN"
    if (tables.isNoAPPID(UsersuppliedApplicationsupplied) || overwrite) {
        val mTable = tables.getAPPID(UsersuppliedApplicationsupplied)
        mTable.StandardFlag = 0
    }
    UsersuppliedApplicationsupplied = "ACAD_MLEADERVER"
    if (tables.isNoAPPID(UsersuppliedApplicationsupplied) || overwrite) {
        val mTable = tables.getAPPID(UsersuppliedApplicationsupplied)
        mTable.StandardFlag = 0
    }
    UsersuppliedApplicationsupplied = "ACAD_NAV_VCDISPLAY"
    if (tables.isNoAPPID(UsersuppliedApplicationsupplied) || overwrite) {
        val mTable = tables.getAPPID(UsersuppliedApplicationsupplied)
        mTable.StandardFlag = 0
    }

    var DimensionStyle = "Standard"
    if (tables.isNoDIMSTYLE(DimensionStyle) || overwrite) {
        val mTable = tables.getDIMSTYLE(DimensionStyle)
        mTable.HandleDIMSTYLE = "27"
        mTable.IDhandleDictionary = "A"
        mTable.StandardFlag = 0
        mTable.DIMTXSTYReferenced = "11"
    }
    DimensionStyle = "Annotative"
    if (tables.isNoDIMSTYLE(DimensionStyle) || overwrite) {
        val mTable = tables.getDIMSTYLE(DimensionStyle)
        mTable.HandleDIMSTYLE = "153"
        mTable.IDhandleDictionary = "A"
        mTable.StandardFlag = 0
        mTable.DIMSCALE = 0.0f
        mTable.DIMTXSTYReferenced = "11"
    }

    var BlockName = "*Model_Space"
    if (tables.isNoBLOCK_RECORD(BlockName) || overwrite) {
        val mTable = tables.getBLOCK_RECORD(BlockName)
        mTable.HardpointerIDhandle = "22"
        mTable.BlockInsertion = 0
        mTable.BlockExplodability = 1
        mTable.BlockScalability = 0
    }
    BlockName = "*Paper_Space"
    if (tables.isNoBLOCK_RECORD(BlockName) || overwrite) {
        val mTable = tables.getBLOCK_RECORD(BlockName)
        mTable.HardpointerIDhandle = "59"
        mTable.BlockInsertion = 0
        mTable.BlockExplodability = 1
        mTable.BlockScalability = 0
    }
    BlockName = "*Paper_Space0"
    if (tables.isNoBLOCK_RECORD(BlockName) || overwrite) {
        val mTable = tables.getBLOCK_RECORD(BlockName)
        mTable.HardpointerIDhandle = "5E"
        mTable.BlockInsertion = 0
        mTable.BlockExplodability = 1
        mTable.BlockScalability = 0
    }

}