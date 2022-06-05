package zibi.robotx.autocad.data.dxf.protocol

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*
import javax.vecmath.Vector2f
import javax.vecmath.Vector3f

class SecHeader(lastElem: DxfChain) : DxfChain() {

    //	 Maintenance version number (should be ignored)
    //	70
    var ACADMAINTVER: Int? = null

    //	 The AutoCAD drawing database version number:, AC1006 = R10, AC1009 = R11 and R12, AC1012 = R13, AC1014 = R14, AC1015 = AutoCAD 2000, AC1018 = AutoCAD 2004, AC1021 = AutoCAD 2007, AC1024 = AutoCAD 2010, AC1027 = AutoCAD 2013, AC1032 = AutoCAD 2018
    //	1
    var ACADVER: String? = null

    //	 Angle 0 direction
    //	50
    var ANGBASE: Float? = null

    //	 1 = Clockwise angles, 0 = Counterclockwise angles
    //	70
    var ANGDIR: Int? = null

    //	 Attribute visibility:, 0 = None, 1 = Normal, 2 = All
    //	70
    var ATTMODE: Int? = null

    //	 Units format for angles
    //	70
    var AUNITS: Int? = null

    //	 Units precision for angles
    //	70
    var AUPREC: Int? = null

    //	 Current entity color number:, "0 = BYBLOCK 256 = BYLAYER"
    //	62
    var CECOLOR: Int? = null

    //	 Current entity linetype scale
    //	40
    var CELTSCALE: Float? = null

    //	 Entity linetype name, or BYBLOCK or BYLAYER
    //	6
    var CELTYPE: String? = null

    //	 Lineweight of new objects
    //	370
    var CELWEIGHT: Int? = null

    //	 "Plotstyle handle of new objects if CEPSNTYPE is 3, then this value indicates the handle"
    //	390
    var CEPSNID: String? = null

    //	 Plot style type of new objects:, 0 = Plot style by layer, 1 = Plot style by block, 2 = Plot style by dictionary default, 3 = Plot style by object ID/handle
    //	380
    var CEPSNTYPE: Int? = null

    //	 First chamfer distance
    //	40
    var CHAMFERA: Float? = null

    //	 Second chamfer distance
    //	40
    var CHAMFERB: Float? = null

    //	 Chamfer length
    //	40
    var CHAMFERC: Float? = null

    //	 Chamfer angle
    //	40
    var CHAMFERD: Float? = null

    //	 Current layer name
    //	8
    var CLAYER: String? = null

    //	 Current multiline justification:, "0 = Top
    //	70
    var CMLJUST: Int? = null

    //	 Current multiline scale
    //	40
    var CMLSCALE: Float? = null

    //	 Current multiline style name
    //	2
    var CMLSTYLE: String? = null

    //	 Shadow mode for a 3D object:, 0 = Casts and receives shadows, 1 = Casts shadows, 2 = Receives shadows, 3 = Ignores shadows, Note: Starting with AutoCAD 2016-based products, this variable is obsolete but still supported for backwards compatibility.
    //	280
    var CSHADOW: Int? = null

    //	 Number of precision places displayed in angular dimensions
    //	70
    var DIMADEC: Int? = null

    //	 Alternate unit dimensioning performed if nonzero
    //	70
    var DIMALT: Int? = null

    //	 Alternate unit decimal places
    //	70
    var DIMALTD: Int? = null

    //	 Alternate unit scale factor
    //	40
    var DIMALTF: Float? = null

    //	 Determines rounding of alternate units
    //	40
    var DIMALTRND: Float? = null

    //	 Number of decimal places for tolerance values of an alternate units dimension
    //	70
    var DIMALTTD: Int? = null

    //	 Controls suppression of zeros for alternate tolerance values:, 0 = Suppresses zero feet and precisely zero inches, 1 = Includes zero feet and precisely zero inches, 2 = Includes zero feet and suppresses zero inches, 3 = Includes zero inches and suppresses zero feet, To suppress leading or trailing zeros, add the following values to one of the preceding values:, 4 = Suppresses leading zeros, 8 = Suppresses trailing zeros
    //	70
    var DIMALTTZ: Int? = null

    //	 Units format for alternate units of all dimension style family members except angular:, 1 = Scientific, 2 = Decimal, 3 = Engineering, 4 = Architectural (stacked), 5 = Fractional (stacked), 6 = Architectural, 7 = Fractional, 8 = Operating system defines the decimal separator and number grouping symbols
    //	70
    var DIMALTU: Int? = null

    //	 Controls suppression of zeros for alternate unit dimension values:, 0 = Suppresses zero feet and precisely zero inches, 1 = Includes zero feet and precisely zero inches, 2 = Includes zero feet and suppresses zero inches, 3 = Includes zero inches and suppresses zero feet, 4 = Suppresses leading zeros in decimal dimensions, 8 = Suppresses trailing zeros in decimal dimensions, 12 = Suppresses both leading and trailing zeros
    //	70
    var DIMALTZ: Int? = null

    //	 Alternate dimensioning suffix
    //	1
    var DIMAPOST: String? = null

    //	 1 = Create associative dimensioning, 0 = Draw individual entities, "Note: Obsolete see $DIMASSOC."
    //	70
    var DIMASO: Int? = null

    //	 Controls the associativity of dimension objects, "0 = Creates exploded dimensions
    //	280
    var DIMASSOC: Int? = null

    //	 Dimensioning arrow size
    //	40
    var DIMASZ: Float? = null

    //	 Controls dimension text and arrow placement when space is not sufficient to place both within the extension lines:, 0 = Places both text and arrows outside extension lines, 1 = Moves arrows first, then text, 2 = Moves text first, then arrows, 3 = Moves either text or arrows, whichever fits best, AutoCAD adds a leader to moved dimension text when DIMTMOVE is set to 1
    //	70
    var DIMATFIT: Int? = null

    //	 Angle format for angular dimensions:, 0 = Decimal degrees, "1 = Degrees/minutes/seconds", 2 = Gradians, 3 = Radians, 4 = Surveyor's units
    //	70
    var DIMAUNIT: Int? = null

    //	 Controls suppression of zeros for angular dimensions:, 0 = Displays all leading and trailing zeros, 1 = Suppresses leading zeros in decimal dimensions, 2 = Suppresses trailing zeros in decimal dimensions, 3 = Suppresses leading and trailing zeros
    //	70
    var DIMAZIN: Int? = null

    //	 Arrow block name
    //	1
    var DIMBLK: String? = null

    //	 First arrow block name
    //	1
    var DIMBLK1: String? = null

    //	 Second arrow block name
    //	1
    var DIMBLK2: String? = null

    //	 Size of center mark/lines
    //	40
    var DIMCEN: Float? = null

    //	 Dimension line color:, "range is 0 = BYBLOCK 256 = BYLAYER"
    //	70
    var DIMCLRD: Int? = null

    //	 Dimension extension line color:, "range is 0 = BYBLOCK 256 = BYLAYER"
    //	70
    var DIMCLRE: Int? = null

    //	 Dimension text color:, "range is 0 = BYBLOCK 256 = BYLAYER"
    //	70
    var DIMCLRT: Int? = null

    //	 Number of decimal places for the tolerance values of a primary units dimension
    //	70
    var DIMDEC: Int? = null

    //	 Dimension line extension
    //	40
    var DIMDLE: Float? = null

    //	 Dimension line increment
    //	40
    var DIMDLI: Float? = null

    //	 Single-character decimal separator used when creating dimensions whose unit format is decimal
    //	70
    var DIMDSEP: Int? = null

    //	 Extension line extension
    //	40
    var DIMEXE: Float? = null

    //	 Extension line offset
    //	40
    var DIMEXO: Float? = null

    //	 Scale factor used to calculate the height of text for dimension fractions and tolerances. AutoCAD multiplies DIMTXT by DIMTFAC to set the fractional or tolerance text height.
    //	40
    var DIMFAC: Float? = null

    //	 Dimension line gap
    //	40
    var DIMGAP: Float? = null

    //	 Horizontal dimension text position:, 0 = Above dimension line and center-justified between extension lines, 1 = Above dimension line and next to first extension line, 2 = Above dimension line and next to second extension line, 3 = Above and center-justified to first extension line, 4 = Above and center-justified to second extension line
    //	70
    var DIMJUST: Int? = null

    //	 Arrow block name for leaders
    //	1
    var DIMLDRBLK: String? = null

    //	 Linear measurements scale factor
    //	40
    var DIMLFAC: Float? = null

    //	 Dimension limits generated if nonzero
    //	70
    var DIMLIM: Int? = null

    //	 Sets units for all dimension types except Angular:, 1 = Scientific, 2 = Decimal, 3 = Engineering, 4 = Architectural, 5 = Fractional, 6 = Operating system
    //	70
    var DIMLUNIT: Int? = null

    //	 Dimension line lineweight:, -3 = Standard, -2 = ByLayer, -1 = ByBlock, 0-211 = an integer representing 100th of mm
    //	70
    var DIMLWD: Int? = null

    //	 Extension line lineweight:, -3 = Standard, -2 = ByLayer, -1 = ByBlock, 0-211 = an integer representing 100th of mm
    //	70
    var DIMLWE: Int? = null

    //	 General dimensioning suffix
    //	1
    var DIMPOST: String? = null

    //	 Rounding value for dimension distances
    //	40
    var DIMRND: Float? = null

    //	 Use separate arrow blocks if nonzero
    //	70
    var DIMSAH: Int? = null

    //	 Overall dimensioning scale factor
    //	40
    var DIMSCALE: Float? = null

    //	 Suppression of first extension line:, 0 = Not suppressed, 1 = Suppressed
    //	70
    var DIMSD1: Int? = null

    //	 Suppression of second extension line:, 0 = Not suppressed, 1 = Suppressed
    //	70
    var DIMSD2: Int? = null

    //	 First extension line suppressed if nonzero
    //	70
    var DIMSE1: Int? = null

    //	 Second extension line suppressed if nonzero
    //	70
    var DIMSE2: Int? = null

    //	 1 = Recompute dimensions while dragging, 0 = Drag original image
    //	70
    var DIMSHO: Int? = null

    //	 Suppress outside-extensions dimension lines if nonzero
    //	70
    var DIMSOXD: Int? = null

    //	 Dimension style name
    //	2
    var DIMSTYLE: String? = null

    //	 Text above dimension line if nonzero
    //	70
    var DIMTAD: Int? = null

    //	 Number of decimal places to display the tolerance values
    //	70
    var DIMTDEC: Int? = null

    //	 Dimension tolerance display scale factor
    //	40
    var DIMTFAC: Float? = null

    //	 Text inside horizontal if nonzero
    //	70
    var DIMTIH: Int? = null

    //	 Force text inside extensions if nonzero
    //	70
    var DIMTIX: Int? = null

    //	 Minus tolerance
    //	40
    var DIMTM: Float? = null

    //	 Dimension text movement rules:, 0 = Moves the dimension line with dimension text, 1 = Adds a leader when dimension text is moved, 2 = Allows text to be moved freely without a leader
    //	70
    var DIMTMOVE: Int? = null

    //	 If text is outside the extension lines, dimension lines are forced between the extension lines if nonzero
    //	70
    var DIMTOFL: Int? = null

    //	 Text outside horizontal if nonzero
    //	70
    var DIMTOH: Int? = null

    //	 Dimension tolerances generated if nonzero
    //	70
    var DIMTOL: Int? = null

    //	 Vertical justification for tolerance values:, 0 = Top, 1 = Middle, 2 = Bottom
    //	70
    var DIMTOLJ: Int? = null

    //	 Plus tolerance
    //	40
    var DIMTP: Float? = null

    //	 Dimensioning tick size:, 0 = Draws arrowheads, >0 = Draws oblique strokes instead of arrowheads
    //	40
    var DIMTSZ: Float? = null

    //	 Text vertical position
    //	40
    var DIMTVP: Float? = null

    //	 Dimension text style
    //	7
    var DIMTXSTY: String? = null

    //	 Dimensioning text height
    //	40
    var DIMTXT: Float? = null

    //	 Controls suppression of zeros for tolerance values:, 0 = Suppresses zero feet and precisely zero inches, 1 = Includes zero feet and precisely zero inches, 2 = Includes zero feet and suppresses zero inches, 3 = Includes zero inches and suppresses zero feet, 4 = Suppresses leading zeros in decimal dimensions, 8 = Suppresses trailing zeros in decimal dimensions, 12 = Suppresses both leading and trailing zeros
    //	70
    var DIMTZIN: Int? = null

    //	 Cursor functionality for user-positioned text:, 0 = Controls only the dimension line location, 1 = Controls the text position as well as the dimension line location
    //	70
    var DIMUPT: Int? = null

    //	 Controls suppression of zeros for primary unit values:, 0 = Suppresses zero feet and precisely zero inches, 1 = Includes zero feet and precisely zero inches, 2 = Includes zero feet and suppresses zero inches, 3 = Includes zero inches and suppresses zero feet, 4 = Suppresses leading zeros in decimal dimensions, 8 = Suppresses trailing zeros in decimal dimensions, 12 = Suppresses both leading and trailing zeros
    //	70
    var DIMZIN: Int? = null

    //	 Controls the display of silhouette curves of body objects in Wireframe mode:, 0 = Off, 1 = On
    //	70
    var DISPSILH: Int? = null

    //	 Hard-pointer ID to visual style while creating 3D solid primitives. The default value is NULL
    //	349
    var DRAGVS: String? = null

    //	 "Drawing code page set to the system code page when a new drawing is created, but not otherwise maintained by AutoCAD"
    //	3
    var DWGCODEPAGE: String? = null

    //	 Current elevation set by ELEV command
    //	40
    var ELEVATION: Float? = null

    //	 Lineweight endcaps setting for new objects:, 0 = None, 1 = Round, 2 = Angle, 3 = Square
    //	280
    var ENDCAPS: Int? = null

    //	 X, Y, and Z drawing extents upper-right corner (in WCS)
    //	10	20	30
    var EXTMAX: Vector3f? = null

    //	 X, Y, and Z drawing extents lower-left corner (in WCS)
    //	10	20	30
    var EXTMIN: Vector3f? = null

    //	 Controls symbol table naming:, 0 = AutoCAD Release 14 compatibility. Limits names to 31 characters in length. Names can include the letters A to Z, the numerals 0 to 9, and the special characters dollar sign ($), underscore (_), and hyphen (-)., 1 = AutoCAD 2000. Names can be up to 255 characters in length, and can include the letters A to Z, the numerals 0 to 9, spaces, and any special characters not used for other purposes by Microsoft Windows and AutoCAD
    //	290
    var EXTNAMES: Boolean? = null

    //	 Fillet radius
    //	40
    var FILLETRAD: Float? = null

    //	 Fill mode on if nonzero
    //	70
    var FILLMODE: Int? = null

    //	 Set at creation time, uniquely identifies a particular drawing
    //	2
    var FINGERPRINTGUID: String? = null

    //	 "Specifies a gap to be displayed where an object is hidden by another object the value is specified as a percent of one unit and is independent of the zoom level. A haloed line is shortened at the point where it is hidden when HIDE or the Hidden option of SHADEMODE is used"
    //	280
    var HALOGAP: Int? = null

    //	 Next available handle
    //	5
    var HANDSEED: Int? = null

    //	 Specifies HIDETEXT system variable:, 0 = HIDE ignores text objects when producing the hidden view, 1 = HIDE does not ignore text objects
    //	290
    var HIDETEXT: Boolean? = null

    //	 Path for all relative hyperlinks in the drawing. If null, the drawing path is used
    //	1
    var HYPERLINKBASE: String? = null

    //	 Controls whether layer and spatial indexes are created and saved in drawing files:, 0 = No indexes are created, 1 = Layer index is created, 2 = Spatial index is created, 3 = Layer and spatial indexes are created
    //	280
    var INDEXCTL: Int? = null

    //	 Insertion base set by BASE command (in WCS)
    //	10	20	30
    var INSBASE: Vector3f? = null

    //	 Default drawing units for AutoCAD DesignCenter blocks:, 0 = Unitless, 1 = Inches, 2 = Feet, 3 = Miles, 4 = Millimeters, 5 = Centimeters, 6 = Meters, 7 = Kilometers, 8 = Microinches, 9 = Mils, 10 = Yards, 11 = Angstroms, 12 = Nanometers, 13 = Microns, 14 = Decimeters, 15 = Decameters, 16 = Hectometers, 17 = Gigameters, 18 = Astronomical units, 19 = Light years, 20 = Parsecs, 21 = US Survey Feet, 22 = US Survey Inch, 23 = US Survey Yard, 24 = US Survey Mile
    //	70
    var INSUNITS: Int? = null

    //	 "Represents the ACI color index of the ""interference objects"" created during the INTERFERE command. Default value is 1"
    //	62
    var INTERFERECOLOR: Int? = null

    //	 Hard-pointer ID to the visual style for interference objects. Default visual style is Conceptual.
    //	345
    var INTERFEREOBJVS: String? = null

    //	 Hard-pointer ID to the visual style for the viewport during interference checking. Default visual style is 3d Wireframe.
    //	346
    var INTERFEREVPVS: String? = null

    //	 Specifies the entity color of intersection polylines:, Values 1-255 designate an AutoCAD color index (ACI), 0 = Color BYBLOCK, 256 = Color BYLAYER, 257 = Color BYENTITY
    //	70
    var INTERSECTIONCOLOR: Int? = null

    //	 Specifies the display of intersection polylines:, 0 = Turns off the display of intersection polylines, 1 = Turns on the display of intersection polylines
    //	290
    var INTERSECTIONDISPLAY: Boolean? = null

    //	 Lineweight joint setting for new objects:, 0=None, 1= Round, 2 = Angle, 3 = Flat
    //	280
    var JOINSTYLE: Int? = null

    //	 Nonzero if limits checking is on
    //	70
    var LIMCHECK: Int? = null

    //	 XY drawing limits upper-right corner (in WCS)
    //	10	20
    var LIMMAX: Vector2f? = null

    //	 XY drawing limits lower-left corner (in WCS)
    //	10	20
    var LIMMIN: Vector2f? = null

    //	 Global linetype scale
    //	40
    var LTSCALE: Float? = null

    //	 Units format for coordinates and distances
    //	70
    var LUNITS: Int? = null

    //	 Units precision for coordinates and distances
    //	70
    var LUPREC: Int? = null

    //	 Controls the display of lineweights on the Model or Layout tab:, 0 = Lineweight is not displayed, 1 = Lineweight is displayed
    //	290
    var LWDISPLAY: Boolean? = null

    //	 Sets maximum number of viewports to be regenerated
    //	70
    var MAXACTVP: Int? = null

    //	 Sets drawing units:, 0 = English, 1 = Metric
    //	70
    var MEASUREMENT: Int? = null

    //	 Name of menu file
    //	1
    var MENU: String? = null

    //	 Mirror text if nonzero
    //	70
    var MIRRTEXT: Int? = null

    //	 Specifies the color of obscured lines. An obscured line is a hidden line made visible by changing its color and linetype and is visible only when the HIDE or SHADEMODE command is used. The OBSCUREDCOLOR setting is visible only if the OBSCUREDLTYPE is turned ON by setting it to a value other than 0., 0 and 256 = Entity color, 1-255 = An AutoCAD color index (ACI)
    //	70
    var OBSCOLOR: Int? = null

    //	 Specifies the linetype of obscured lines. Obscured linetypes are independent of zoom level, unlike standard object linetypes. Value 0 turns off display of obscured lines and is the default. Linetype values are defined as follows:, 0 = Off, 1 = Solid, 2 = Dashed, 3 = Dotted, 4 = Short Dash, 5 = Medium Dash, 6 = Long Dash, 7 = Double Short Dash, 8 = Double Medium Dash, 9 = Double Long Dash, 10 = Medium Long Dash, 11 = Sparse Dot
    //	280
    var OBSLTYPE: Int? = null

    //	 Ortho mode on if nonzero
    //	70
    var ORTHOMODE: Int? = null

    //	 Point display mode
    //	70
    var PDMODE: Int? = null

    //	 Point display size
    //	40
    var PDSIZE: Float? = null

    //	 Current paper space elevation
    //	40
    var PELEVATION: Float? = null

    //	 Maximum X, Y, and Z extents for paper space
    //	10	20	30
    var PEXTMAX: Vector3f? = null

    //	 Minimum X, Y, and Z extents for paper space
    //	10	20	30
    var PEXTMIN: Vector3f? = null

    //	 Paper space insertion base point
    //	10	20	30
    var PINSBASE: Vector3f? = null

    //	 Limits checking in paper space when nonzero
    //	70
    var PLIMCHECK: Int? = null

    //	 Maximum X and Y limits in paper space
    //	10	20
    var PLIMMAX: Vector2f? = null

    //	 Minimum X and Y limits in paper space
    //	10	20
    var PLIMMIN: Vector2f? = null

    //	 Governs the generation of linetype patterns around the vertices of a 2D polyline:, 1 = Linetype is generated in a continuous pattern around vertices of the polyline, 0 = Each segment of the polyline starts and ends with a dash
    //	70
    var PLINEGEN: Int? = null

    //	 Default polyline width
    //	40
    var PLINEWID: Float? = null

    //	 Assigns a project name to the current drawing. Used when an external reference or image is not found on its original path. The project name points to a section in the registry that can contain one or more search paths for each project name defined. Project names and their search directories are created from the Files tab of the Options dialog box
    //	1
    var PROJECTNAME: String? = null

    //	 Controls the saving of proxy object images
    //	70
    var PROXYGRAPHICS: Int? = null

    //	 Controls paper space linetype scaling:, 1 = No special linetype scaling, 0 = Viewport scaling governs linetype scaling
    //	70
    var PSLTSCALE: Int? = null

    //	 Indicates whether the current drawing is in a Color-Dependent or Named Plot Style mode:, 0 = Uses named plot style tables in the current drawing, 1 = Uses color-dependent plot style tables in the current drawing
    //	290
    var PSTYLEMODE: Boolean? = null

    //	 View scale factor for new viewports:, 0 = Scaled to fit, >0 = Scale factor (a positive real value)
    //	40
    var PSVPSCALE: Float? = null

    //	 Name of the UCS that defines the origin and orientation of orthographic UCS settings (paper space only)
    //	2
    var PUCSBASE: String? = null

    //	 Current paper space UCS name
    //	2
    var PUCSNAME: String? = null

    //	 Current paper space UCS origin
    //	10	20	30
    var PUCSORG: Vector3f? = null

    //	 Point which becomes the new UCS origin after changing paper space UCS to BACK when PUCSBASE is set to WORLD
    //	10	20	30
    var PUCSORGBACK: Vector3f? = null

    //	 Point which becomes the new UCS origin after changing paper space UCS to BOTTOM when PUCSBASE is set to WORLD
    //	10	20	30
    var PUCSORGBOTTOM: Vector3f? = null

    //	 Point which becomes the new UCS origin after changing paper space UCS to FRONT when PUCSBASE is set to WORLD
    //	10	20	30
    var PUCSORGFRONT: Vector3f? = null

    //	 Point which becomes the new UCS origin after changing paper space UCS to LEFT when PUCSBASE is set to WORLD
    //	10	20	30
    var PUCSORGLEFT: Vector3f? = null

    //	 Point which becomes the new UCS origin after changing paper space UCS to RIGHT when PUCSBASE is set to WORLD
    //	10	20	30
    var PUCSORGRIGHT: Vector3f? = null

    //	 Point which becomes the new UCS origin after changing paper space UCS to TOP when PUCSBASE is set to WORLD
    //	10	20	30
    var PUCSORGTOP: Vector3f? = null

    //	 If paper space UCS is orthographic (PUCSORTHOVIEW not equal to 0), this is the name of the UCS that the orthographic UCS is relative to. If blank, UCS is relative to WORLD
    //	2
    var PUCSORTHOREF: String? = null

    //	 Orthographic view type of paper space UCS:, 0 = UCS is not orthographic, 1 = Top, 2 = Bottom, 3 = Front, 4 = Back, 5 = Left, 6 = Right
    //	70
    var PUCSORTHOVIEW: Int? = null

    //	 Current paper space UCS X axis
    //	10	20	30
    var PUCSXDIR: Vector3f? = null

    //	 Current paper space UCS Y axis
    //	10	20	30
    var PUCSYDIR: Vector3f? = null

    //	 Quick Text mode on if nonzero
    //	70
    var QTEXTMODE: Int? = null

    //	 REGENAUTO mode on if nonzero
    //	70
    var REGENMODE: Int? = null

    //	 Controls the shading of edges:, 0 = Faces shaded, edges not highlighted, 1 = Faces shaded, edges highlighted in black, 2 = Faces not filled, edges in entity color, 3 = Faces in entity color, edges in black
    //	70
    var SHADEDGE: Int? = null

    //	 "Percent ambient/diffuse light
    //	70
    var SHADEDIF: Int? = null

    //	 Location of the ground shadow plane. This is a Z axis ordinate.
    //	40
    var SHADOWPLANELOCATION: Float? = null

    //	 Sketch record increment
    //	40
    var SKETCHINC: Float? = null

    //	 Determines the object type created by the SKETCH command:, 0 = Generates lines, 1 = Generates polylines, 2 = Generates splines
    //	70
    var SKPOLY: Int? = null

    //	 "Controls the object sorting methods
    //	280
    var SORTENTS: Int? = null

    //	 Number of line segments per spline patch
    //	70
    var SPLINESEGS: Int? = null

    //	 Spline curve type for PEDIT Spline
    //	70
    var SPLINETYPE: Int? = null

    //	 Number of mesh tabulations in first direction
    //	70
    var SURFTAB1: Int? = null

    //	 Number of mesh tabulations in second direction
    //	70
    var SURFTAB2: Int? = null

    //	 Surface type for PEDIT Smooth
    //	70
    var SURFTYPE: Int? = null

    //	 Surface density (for PEDIT Smooth) in M direction
    //	70
    var SURFU: Int? = null

    //	 Surface density (for PEDIT Smooth) in N direction
    //	70
    var SURFV: Int? = null

    //	 Local date/time of drawing creation (see Special Handling of Date/Time Variables)
    //	40
    var TDCREATE: Float? = null

    //	 Cumulative editing time for this drawing (see Special Handling of Date/Time Variables)
    //	40
    var TDINDWG: Float? = null

    //	 Universal date/time the drawing was created (see Special Handling of Date/Time Variables)
    //	40
    var TDUCREATE: Float? = null

    //	 Local date/time of last drawing update (see Special Handling of Date/Time Variables)
    //	40
    var TDUPDATE: Float? = null

    //	 User-elapsed timer
    //	40
    var TDUSRTIMER: Float? = null

    //	 Universal date/time of the last update/save (see Special Handling of Date/Time Variables)
    //	40
    var TDUUPDATE: Float? = null

    //	 Default text height
    //	40
    var TEXTSIZE: Float? = null

    //	 Current text style name
    //	7
    var TEXTSTYLE: String? = null

    //	 Current thickness set by ELEV command
    //	40
    var THICKNESS: Float? = null

    //	 "1 for previous release compatibility mode 0 otherwise"
    //	70
    var TILEMODE: Int? = null

    //	 Default trace width
    //	40
    var TRACEWID: Float? = null

    //	 Specifies the maximum depth of the spatial index
    //	70
    var TREEDEPTH: Int? = null

    //	 Name of the UCS that defines the origin and orientation of orthographic UCS settings
    //	2
    var UCSBASE: String? = null

    //	 Name of current UCS
    //	2
    var UCSNAME: String? = null

    //	 Origin of current UCS (in WCS)
    //	10	20	30
    var UCSORG: Vector3f? = null

    //	 Point which becomes the new UCS origin after changing model space UCS to BACK when UCSBASE is set to WORLD
    //	10	20	30
    var UCSORGBACK: Vector3f? = null

    //	 Point which becomes the new UCS origin after changing model space UCS to BOTTOM when UCSBASE is set to WORLD
    //	10	20	30
    var UCSORGBOTTOM: Vector3f? = null

    //	 Point which becomes the new UCS origin after changing model space UCS to FRONT when UCSBASE is set to WORLD
    //	10	20	30
    var UCSORGFRONT: Vector3f? = null

    //	 Point which becomes the new UCS origin after changing model space UCS to LEFT when UCSBASE is set to WORLD
    //	10	20	30
    var UCSORGLEFT: Vector3f? = null

    //	 Point which becomes the new UCS origin after changing model space UCS to RIGHT when UCSBASE is set to WORLD
    //	10	20	30
    var UCSORGRIGHT: Vector3f? = null

    //	 Point which becomes the new UCS origin after changing model space UCS to TOP when UCSBASE is set to WORLD
    //	10	20	30
    var UCSORGTOP: Vector3f? = null

    //	 If model space UCS is orthographic (UCSORTHOVIEW not equal to 0), this is the name of the UCS that the orthographic UCS is relative to. If blank, UCS is relative to WORLD
    //	2
    var UCSORTHOREF: String? = null

    //	 Orthographic view type of model space UCS:, 0 = UCS is not orthographic, 1 = Top, 2 = Bottom, 3 = Front, 4 = Back, 5 = Left, 6 = Right
    //	70
    var UCSORTHOVIEW: Int? = null

    //	 Direction of the current UCS X axis (in WCS)
    //	10	20	30
    var UCSXDIR: Vector3f? = null

    //	 Direction of the current UCS Y axis (in WCS)
    //	10	20	30
    var UCSYDIR: Vector3f? = null

    //	 Low bit set = Display fractions, feet-and-inches, and surveyor's angles in input format
    //	70
    var UNITED: Int? = null

    //	 Five integer variables intended for use by third-party developers
    //	70
    var USERI1: Int? = null

    //	 Five real variables intended for use by third-party developers
    //	40
    var USERR1: Float? = null

    //	 Controls the user timer for the drawing:, 0 = Timer off, 1 = Timer on
    //	70
    var USRTIMER: Int? = null

    //	 Uniquely identifies a particular version of a drawing. Updated when the drawing is modified
    //	2
    var VERSIONGUID: String? = null

    //	 Controls the properties of xref-dependent layers:, 0 = Don't retain xref-dependent visibility settings, 1 = Retain xref-dependent visibility settings
    //	70
    var VISRETAIN: Int? = null

    //	 Determines whether input for the DVIEW and VPOINT command evaluated as relative to the WCS or current UCS:, 0 = Don't change UCS, 1 = Set UCS to WCS during DVIEW/VPOINT
    //	70
    var WORLDVIEW: Int? = null

    //	 Controls the visibility of xref clipping boundaries:, 0 = Clipping boundary is not visible, 1 = Clipping boundary is visible
    //	290
    var XCLIPFRAME: Boolean? = null

    //	 Controls whether the current drawing can be edited in-place when being referenced by another drawing:, 0 = Can't use in-place reference editing, 1 = Can use in-place reference editing
    //	290
    var XEDIT: Boolean? = null



    override fun read(dxfContext: DxfLoaderContext) {
//        this.dxfContext = dxfContext
        var code = dxfContext.get()
        loop@ while (true) {
            if (code == "0") {
                do{ code = dxfContext.get() }while(code == "0")
                if (code == "ENDSEC") {
                    return
                } else {
                    dxfContext.get()
                    continue
                }
            }

            if ( code == "9" ) {
                val VarCode = dxfContext.get()
                code = dxfContext.get()
                when (VarCode) {
                    "\$ACADMAINTVER" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    ACADMAINTVER = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$ACADVER" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "1" -> {
                                    ACADVER = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$ANGBASE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "50" -> {
                                    ANGBASE = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$ANGDIR" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    ANGDIR = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$ATTMODE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    ATTMODE = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$AUNITS" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    AUNITS = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$AUPREC" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    AUPREC = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$CECOLOR" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "62" -> {
                                    CECOLOR = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$CELTSCALE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    CELTSCALE = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$CELTYPE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "6" -> {
                                    CELTYPE = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$CELWEIGHT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "370" -> {
                                    CELWEIGHT = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$CEPSNID" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "390" -> {
                                    CEPSNID = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$CEPSNTYPE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "380" -> {
                                    CEPSNTYPE = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$CHAMFERA" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    CHAMFERA = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$CHAMFERB" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    CHAMFERB = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$CHAMFERC" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    CHAMFERC = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$CHAMFERD" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    CHAMFERD = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$CLAYER" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "8" -> {
                                    CLAYER = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$CMLJUST" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    CMLJUST = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$CMLSCALE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    CMLSCALE = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$CMLSTYLE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "2" -> {
                                    CMLSTYLE = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$CSHADOW" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "280" -> {
                                    CSHADOW = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMADEC" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMADEC = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMALT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMALT = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMALTD" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMALTD = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMALTF" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMALTF = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMALTRND" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMALTRND = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMALTTD" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMALTTD = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMALTTZ" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMALTTZ = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMALTU" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMALTU = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMALTZ" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMALTZ = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMAPOST" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "1" -> {
                                    DIMAPOST = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMASO" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMASO = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMASSOC" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "280" -> {
                                    DIMASSOC = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMASZ" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMASZ = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMATFIT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMATFIT = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMAUNIT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMAUNIT = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMAZIN" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMAZIN = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMBLK" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "1" -> {
                                    DIMBLK = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMBLK1" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "1" -> {
                                    DIMBLK1 = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMBLK2" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "1" -> {
                                    DIMBLK2 = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMCEN" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMCEN = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMCLRD" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMCLRD = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMCLRE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMCLRE = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMCLRT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMCLRT = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMDEC" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMDEC = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMDLE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMDLE = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMDLI" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMDLI = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMDSEP" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMDSEP = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMEXE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMEXE = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMEXO" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMEXO = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMFAC" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMFAC = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMGAP" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMGAP = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMJUST" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMJUST = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMLDRBLK" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "1" -> {
                                    DIMLDRBLK = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMLFAC" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMLFAC = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMLIM" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMLIM = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMLUNIT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMLUNIT = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMLWD" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMLWD = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMLWE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMLWE = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMPOST" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "1" -> {
                                    DIMPOST = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMRND" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMRND = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMSAH" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMSAH = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMSCALE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMSCALE = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMSD1" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMSD1 = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMSD2" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMSD2 = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMSE1" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMSE1 = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMSE2" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMSE2 = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMSHO" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMSHO = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMSOXD" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMSOXD = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMSTYLE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "2" -> {
                                    DIMSTYLE = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMTAD" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMTAD = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMTDEC" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMTDEC = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMTFAC" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMTFAC = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMTIH" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMTIH = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMTIX" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMTIX = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMTM" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMTM = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMTMOVE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMTMOVE = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMTOFL" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMTOFL = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMTOH" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMTOH = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMTOL" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMTOL = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMTOLJ" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMTOLJ = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMTP" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMTP = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMTSZ" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMTSZ = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMTVP" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMTVP = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMTXSTY" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "7" -> {
                                    DIMTXSTY = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMTXT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    DIMTXT = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMTZIN" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMTZIN = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMUPT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMUPT = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DIMZIN" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DIMZIN = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DISPSILH" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    DISPSILH = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DRAGVS" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "349" -> {
                                    DRAGVS = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$DWGCODEPAGE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "3" -> {
                                    DWGCODEPAGE = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$ELEVATION" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    ELEVATION = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$ENDCAPS" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "280" -> {
                                    ENDCAPS = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$EXTMAX" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( EXTMAX == null ) EXTMAX = Vector3f()
                                    EXTMAX?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( EXTMAX == null ) EXTMAX = Vector3f()
                                    EXTMAX?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( EXTMAX == null ) EXTMAX = Vector3f()
                                    EXTMAX?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$EXTMIN" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( EXTMIN == null ) EXTMIN = Vector3f()
                                    EXTMIN?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( EXTMIN == null ) EXTMIN = Vector3f()
                                    EXTMIN?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( EXTMIN == null ) EXTMIN = Vector3f()
                                    EXTMIN?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$EXTNAMES" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "290" -> {
                                    EXTNAMES = dxfContext.booleanValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$FILLETRAD" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    FILLETRAD = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$FILLMODE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    FILLMODE = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$FINGERPRINTGUID" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "2" -> {
                                    FINGERPRINTGUID = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$HALOGAP" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "280" -> {
                                    HALOGAP = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$HANDSEED" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "5" -> {
                                    HANDSEED = dxfContext.intHexValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$HIDETEXT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "290" -> {
                                    HIDETEXT = dxfContext.booleanValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$HYPERLINKBASE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "1" -> {
                                    HYPERLINKBASE = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$INDEXCTL" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "280" -> {
                                    INDEXCTL = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$INSBASE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( INSBASE == null ) INSBASE = Vector3f()
                                    INSBASE?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( INSBASE == null ) INSBASE = Vector3f()
                                    INSBASE?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( INSBASE == null ) INSBASE = Vector3f()
                                    INSBASE?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$INSUNITS" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    INSUNITS = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$INTERFERECOLOR" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "62" -> {
                                    INTERFERECOLOR = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$INTERFEREOBJVS" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "345" -> {
                                    INTERFEREOBJVS = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$INTERFEREVPVS" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "346" -> {
                                    INTERFEREVPVS = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$INTERSECTIONCOLOR" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    INTERSECTIONCOLOR = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$INTERSECTIONDISPLAY" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "290" -> {
                                    INTERSECTIONDISPLAY = dxfContext.booleanValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$JOINSTYLE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "280" -> {
                                    JOINSTYLE = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$LIMCHECK" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    LIMCHECK = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$LIMMAX" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( LIMMAX == null ) LIMMAX = Vector2f()
                                    LIMMAX?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( LIMMAX == null ) LIMMAX = Vector2f()
                                    LIMMAX?.y = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$LIMMIN" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( LIMMIN == null ) LIMMIN = Vector2f()
                                    LIMMIN?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( LIMMIN == null ) LIMMIN = Vector2f()
                                    LIMMIN?.y = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$LTSCALE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    LTSCALE = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$LUNITS" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    LUNITS = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$LUPREC" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    LUPREC = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$LWDISPLAY" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "290" -> {
                                    LWDISPLAY = dxfContext.booleanValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$MAXACTVP" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    MAXACTVP = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$MEASUREMENT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    MEASUREMENT = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$MENU" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "1" -> {
                                    MENU = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$MIRRTEXT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    MIRRTEXT = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$OBSCOLOR" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    OBSCOLOR = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$OBSLTYPE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "280" -> {
                                    OBSLTYPE = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$ORTHOMODE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    ORTHOMODE = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PDMODE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    PDMODE = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PDSIZE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    PDSIZE = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PELEVATION" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    PELEVATION = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PEXTMAX" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( PEXTMAX == null ) PEXTMAX = Vector3f()
                                    PEXTMAX?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( PEXTMAX == null ) PEXTMAX = Vector3f()
                                    PEXTMAX?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( PEXTMAX == null ) PEXTMAX = Vector3f()
                                    PEXTMAX?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PEXTMIN" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( PEXTMIN == null ) PEXTMIN = Vector3f()
                                    PEXTMIN?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( PEXTMIN == null ) PEXTMIN = Vector3f()
                                    PEXTMIN?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( PEXTMIN == null ) PEXTMIN = Vector3f()
                                    PEXTMIN?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PINSBASE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( PINSBASE == null ) PINSBASE = Vector3f()
                                    PINSBASE?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( PINSBASE == null ) PINSBASE = Vector3f()
                                    PINSBASE?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( PINSBASE == null ) PINSBASE = Vector3f()
                                    PINSBASE?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PLIMCHECK" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    PLIMCHECK = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PLIMMAX" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( PLIMMAX == null ) PLIMMAX = Vector2f()
                                    PLIMMAX?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( PLIMMAX == null ) PLIMMAX = Vector2f()
                                    PLIMMAX?.y = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PLIMMIN" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( PLIMMIN == null ) PLIMMIN = Vector2f()
                                    PLIMMIN?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( PLIMMIN == null ) PLIMMIN = Vector2f()
                                    PLIMMIN?.y = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PLINEGEN" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    PLINEGEN = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PLINEWID" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    PLINEWID = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PROJECTNAME" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "1" -> {
                                    PROJECTNAME = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PROXYGRAPHICS" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    PROXYGRAPHICS = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PSLTSCALE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    PSLTSCALE = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PSTYLEMODE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "290" -> {
                                    PSTYLEMODE = dxfContext.booleanValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PSVPSCALE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    PSVPSCALE = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PUCSBASE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "2" -> {
                                    PUCSBASE = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PUCSNAME" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "2" -> {
                                    PUCSNAME = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PUCSORG" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( PUCSORG == null ) PUCSORG = Vector3f()
                                    PUCSORG?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( PUCSORG == null ) PUCSORG = Vector3f()
                                    PUCSORG?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( PUCSORG == null ) PUCSORG = Vector3f()
                                    PUCSORG?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PUCSORGBACK" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( PUCSORGBACK == null ) PUCSORGBACK = Vector3f()
                                    PUCSORGBACK?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( PUCSORGBACK == null ) PUCSORGBACK = Vector3f()
                                    PUCSORGBACK?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( PUCSORGBACK == null ) PUCSORGBACK = Vector3f()
                                    PUCSORGBACK?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PUCSORGBOTTOM" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( PUCSORGBOTTOM == null ) PUCSORGBOTTOM = Vector3f()
                                    PUCSORGBOTTOM?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( PUCSORGBOTTOM == null ) PUCSORGBOTTOM = Vector3f()
                                    PUCSORGBOTTOM?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( PUCSORGBOTTOM == null ) PUCSORGBOTTOM = Vector3f()
                                    PUCSORGBOTTOM?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PUCSORGFRONT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( PUCSORGFRONT == null ) PUCSORGFRONT = Vector3f()
                                    PUCSORGFRONT?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( PUCSORGFRONT == null ) PUCSORGFRONT = Vector3f()
                                    PUCSORGFRONT?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( PUCSORGFRONT == null ) PUCSORGFRONT = Vector3f()
                                    PUCSORGFRONT?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PUCSORGLEFT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( PUCSORGLEFT == null ) PUCSORGLEFT = Vector3f()
                                    PUCSORGLEFT?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( PUCSORGLEFT == null ) PUCSORGLEFT = Vector3f()
                                    PUCSORGLEFT?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( PUCSORGLEFT == null ) PUCSORGLEFT = Vector3f()
                                    PUCSORGLEFT?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PUCSORGRIGHT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( PUCSORGRIGHT == null ) PUCSORGRIGHT = Vector3f()
                                    PUCSORGRIGHT?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( PUCSORGRIGHT == null ) PUCSORGRIGHT = Vector3f()
                                    PUCSORGRIGHT?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( PUCSORGRIGHT == null ) PUCSORGRIGHT = Vector3f()
                                    PUCSORGRIGHT?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PUCSORGTOP" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( PUCSORGTOP == null ) PUCSORGTOP = Vector3f()
                                    PUCSORGTOP?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( PUCSORGTOP == null ) PUCSORGTOP = Vector3f()
                                    PUCSORGTOP?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( PUCSORGTOP == null ) PUCSORGTOP = Vector3f()
                                    PUCSORGTOP?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PUCSORTHOREF" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "2" -> {
                                    PUCSORTHOREF = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PUCSORTHOVIEW" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    PUCSORTHOVIEW = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PUCSXDIR" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( PUCSXDIR == null ) PUCSXDIR = Vector3f()
                                    PUCSXDIR?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( PUCSXDIR == null ) PUCSXDIR = Vector3f()
                                    PUCSXDIR?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( PUCSXDIR == null ) PUCSXDIR = Vector3f()
                                    PUCSXDIR?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$PUCSYDIR" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( PUCSYDIR == null ) PUCSYDIR = Vector3f()
                                    PUCSYDIR?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( PUCSYDIR == null ) PUCSYDIR = Vector3f()
                                    PUCSYDIR?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( PUCSYDIR == null ) PUCSYDIR = Vector3f()
                                    PUCSYDIR?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$QTEXTMODE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    QTEXTMODE = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$REGENMODE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    REGENMODE = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$SHADEDGE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    SHADEDGE = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$SHADEDIF" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    SHADEDIF = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$SHADOWPLANELOCATION" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    SHADOWPLANELOCATION = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$SKETCHINC" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    SKETCHINC = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$SKPOLY" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    SKPOLY = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$SORTENTS" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "280" -> {
                                    SORTENTS = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$SPLINESEGS" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    SPLINESEGS = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$SPLINETYPE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    SPLINETYPE = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$SURFTAB1" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    SURFTAB1 = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$SURFTAB2" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    SURFTAB2 = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$SURFTYPE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    SURFTYPE = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$SURFU" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    SURFU = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$SURFV" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    SURFV = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$TDCREATE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    TDCREATE = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$TDINDWG" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    TDINDWG = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$TDUCREATE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    TDUCREATE = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$TDUPDATE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    TDUPDATE = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$TDUSRTIMER" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    TDUSRTIMER = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$TDUUPDATE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    TDUUPDATE = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$TEXTSIZE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    TEXTSIZE = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$TEXTSTYLE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "7" -> {
                                    TEXTSTYLE = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$THICKNESS" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    THICKNESS = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$TILEMODE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    TILEMODE = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$TRACEWID" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    TRACEWID = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$TREEDEPTH" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    TREEDEPTH = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$UCSBASE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "2" -> {
                                    UCSBASE = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$UCSNAME" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "2" -> {
                                    UCSNAME = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$UCSORG" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( UCSORG == null ) UCSORG = Vector3f()
                                    UCSORG?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( UCSORG == null ) UCSORG = Vector3f()
                                    UCSORG?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( UCSORG == null ) UCSORG = Vector3f()
                                    UCSORG?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$UCSORGBACK" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( UCSORGBACK == null ) UCSORGBACK = Vector3f()
                                    UCSORGBACK?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( UCSORGBACK == null ) UCSORGBACK = Vector3f()
                                    UCSORGBACK?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( UCSORGBACK == null ) UCSORGBACK = Vector3f()
                                    UCSORGBACK?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$UCSORGBOTTOM" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( UCSORGBOTTOM == null ) UCSORGBOTTOM = Vector3f()
                                    UCSORGBOTTOM?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( UCSORGBOTTOM == null ) UCSORGBOTTOM = Vector3f()
                                    UCSORGBOTTOM?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( UCSORGBOTTOM == null ) UCSORGBOTTOM = Vector3f()
                                    UCSORGBOTTOM?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$UCSORGFRONT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( UCSORGFRONT == null ) UCSORGFRONT = Vector3f()
                                    UCSORGFRONT?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( UCSORGFRONT == null ) UCSORGFRONT = Vector3f()
                                    UCSORGFRONT?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( UCSORGFRONT == null ) UCSORGFRONT = Vector3f()
                                    UCSORGFRONT?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$UCSORGLEFT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( UCSORGLEFT == null ) UCSORGLEFT = Vector3f()
                                    UCSORGLEFT?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( UCSORGLEFT == null ) UCSORGLEFT = Vector3f()
                                    UCSORGLEFT?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( UCSORGLEFT == null ) UCSORGLEFT = Vector3f()
                                    UCSORGLEFT?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$UCSORGRIGHT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( UCSORGRIGHT == null ) UCSORGRIGHT = Vector3f()
                                    UCSORGRIGHT?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( UCSORGRIGHT == null ) UCSORGRIGHT = Vector3f()
                                    UCSORGRIGHT?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( UCSORGRIGHT == null ) UCSORGRIGHT = Vector3f()
                                    UCSORGRIGHT?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$UCSORGTOP" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( UCSORGTOP == null ) UCSORGTOP = Vector3f()
                                    UCSORGTOP?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( UCSORGTOP == null ) UCSORGTOP = Vector3f()
                                    UCSORGTOP?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( UCSORGTOP == null ) UCSORGTOP = Vector3f()
                                    UCSORGTOP?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$UCSORTHOREF" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "2" -> {
                                    UCSORTHOREF = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$UCSORTHOVIEW" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    UCSORTHOVIEW = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$UCSXDIR" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( UCSXDIR == null ) UCSXDIR = Vector3f()
                                    UCSXDIR?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( UCSXDIR == null ) UCSXDIR = Vector3f()
                                    UCSXDIR?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( UCSXDIR == null ) UCSXDIR = Vector3f()
                                    UCSXDIR?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$UCSYDIR" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "10" -> {
                                    if( UCSYDIR == null ) UCSYDIR = Vector3f()
                                    UCSYDIR?.x = dxfContext.floatValue()
                                }
                                "20" -> {
                                    if( UCSYDIR == null ) UCSYDIR = Vector3f()
                                    UCSYDIR?.y = dxfContext.floatValue()
                                }
                                "30" -> {
                                    if( UCSYDIR == null ) UCSYDIR = Vector3f()
                                    UCSYDIR?.z = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$UNITMODE" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    UNITED = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$USERI1 - 5" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    USERI1 = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$USERR1 - 5" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "40" -> {
                                    USERR1 = dxfContext.floatValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$USRTIMER" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    USRTIMER = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$VERSIONGUID" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "2" -> {
                                    VERSIONGUID = dxfContext.stringValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$VISRETAIN" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    VISRETAIN = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$WORLDVIEW" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "70" -> {
                                    WORLDVIEW = dxfContext.intValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$XCLIPFRAME" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "290" -> {
                                    XCLIPFRAME = dxfContext.booleanValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }
                    "\$XEDIT" -> {
                        while (code !="0" && code != "9") {
                            when (code){
                                "290" -> {
                                    XEDIT = dxfContext.booleanValue()
                                }
                            }
                            code = dxfContext.get()
                        }
                        continue@loop
                    }

                }
            }
            code = dxfContext.get()
        }
    }


    override fun write( sbX: StringBuilder): StringBuilder {
        val sb = StringBuilder()
        if( ACADMAINTVER != null) sb.append("\n 9\n\$ACADMAINTVER\n 70\n$ACADMAINTVER")
        if( ACADVER != null) sb.append("\n 9\n\$ACADVER\n 1\n$ACADVER")
        if( ANGBASE != null) sb.append("\n 9\n\$ANGBASE\n 50\n$ANGBASE")
        if( ANGDIR != null) sb.append("\n 9\n\$ANGDIR\n 70\n$ANGDIR")
        if( ATTMODE != null) sb.append("\n 9\n\$ATTMODE\n 70\n$ATTMODE")
        if( AUNITS != null) sb.append("\n 9\n\$AUNITS\n 70\n$AUNITS")
        if( AUPREC != null) sb.append("\n 9\n\$AUPREC\n 70\n$AUPREC")
        if( CECOLOR != null) sb.append("\n 9\n\$CECOLOR\n 62\n$CECOLOR")
        if( CELTSCALE != null) sb.append("\n 9\n\$CELTSCALE\n 40\n$CELTSCALE")
        if( CELTYPE != null) sb.append("\n 9\n\$CELTYPE\n 6\n$CELTYPE")
        if( CELWEIGHT != null) sb.append("\n 9\n\$CELWEIGHT\n 370\n$CELWEIGHT")
        if( CEPSNID != null) sb.append("\n 9\n\$CEPSNID\n 390\n$CEPSNID")
        if( CEPSNTYPE != null) sb.append("\n 9\n\$CEPSNTYPE\n 380\n$CEPSNTYPE")
        if( CHAMFERA != null) sb.append("\n 9\n\$CHAMFERA\n 40\n$CHAMFERA")
        if( CHAMFERB != null) sb.append("\n 9\n\$CHAMFERB\n 40\n$CHAMFERB")
        if( CHAMFERC != null) sb.append("\n 9\n\$CHAMFERC\n 40\n$CHAMFERC")
        if( CHAMFERD != null) sb.append("\n 9\n\$CHAMFERD\n 40\n$CHAMFERD")
        if( CLAYER != null) sb.append("\n 9\n\$CLAYER\n 8\n$CLAYER")
        if( CMLJUST != null) sb.append("\n 9\n\$CMLJUST\n 70\n$CMLJUST")
        if( CMLSCALE != null) sb.append("\n 9\n\$CMLSCALE\n 40\n$CMLSCALE")
        if( CMLSTYLE != null) sb.append("\n 9\n\$CMLSTYLE\n 2\n$CMLSTYLE")
        if( CSHADOW != null) sb.append("\n 9\n\$CSHADOW\n 280\n$CSHADOW")
        if( DIMADEC != null) sb.append("\n 9\n\$DIMADEC\n 70\n$DIMADEC")
        if( DIMALT != null) sb.append("\n 9\n\$DIMALT\n 70\n$DIMALT")
        if( DIMALTD != null) sb.append("\n 9\n\$DIMALTD\n 70\n$DIMALTD")
        if( DIMALTF != null) sb.append("\n 9\n\$DIMALTF\n 40\n$DIMALTF")
        if( DIMALTRND != null) sb.append("\n 9\n\$DIMALTRND\n 40\n$DIMALTRND")
        if( DIMALTTD != null) sb.append("\n 9\n\$DIMALTTD\n 70\n$DIMALTTD")
        if( DIMALTTZ != null) sb.append("\n 9\n\$DIMALTTZ\n 70\n$DIMALTTZ")
        if( DIMALTU != null) sb.append("\n 9\n\$DIMALTU\n 70\n$DIMALTU")
        if( DIMALTZ != null) sb.append("\n 9\n\$DIMALTZ\n 70\n$DIMALTZ")
        if( DIMAPOST != null) sb.append("\n 9\n\$DIMAPOST\n 1\n$DIMAPOST")
        if( DIMASO != null) sb.append("\n 9\n\$DIMASO\n 70\n$DIMASO")
        if( DIMASSOC != null) sb.append("\n 9\n\$DIMASSOC\n 280\n$DIMASSOC")
        if( DIMASZ != null) sb.append("\n 9\n\$DIMASZ\n 40\n$DIMASZ")
        if( DIMATFIT != null) sb.append("\n 9\n\$DIMATFIT\n 70\n$DIMATFIT")
        if( DIMAUNIT != null) sb.append("\n 9\n\$DIMAUNIT\n 70\n$DIMAUNIT")
        if( DIMAZIN != null) sb.append("\n 9\n\$DIMAZIN\n 70\n$DIMAZIN")
        if( DIMBLK != null) sb.append("\n 9\n\$DIMBLK\n 1\n$DIMBLK")
        if( DIMBLK1 != null) sb.append("\n 9\n\$DIMBLK1\n 1\n$DIMBLK1")
        if( DIMBLK2 != null) sb.append( "\n 9\n\$DIMBLK2\n 1\n"+DIMBLK2 )
        if( DIMCEN != null) sb.append("\n 9\n\$DIMCEN\n 40\n$DIMCEN")
        if( DIMCLRD != null) sb.append("\n 9\n\$DIMCLRD\n 70\n$DIMCLRD")
        if( DIMCLRE != null) sb.append( "\n 9\n\$DIMCLRE\n 70\n"+DIMCLRE )
        if( DIMCLRT != null) sb.append( "\n 9\n\$DIMCLRT\n 70\n"+DIMCLRT )
        if( DIMDEC != null) sb.append( "\n 9\n\$DIMDEC\n 70\n"+DIMDEC )
        if( DIMDLE != null) sb.append( "\n 9\n\$DIMDLE\n 40\n"+DIMDLE )
        if( DIMDLI != null) sb.append( "\n 9\n\$DIMDLI\n 40\n"+DIMDLI )
        if( DIMDSEP != null) sb.append( "\n 9\n\$DIMDSEP\n 70\n"+DIMDSEP )
        if( DIMEXE != null) sb.append( "\n 9\n\$DIMEXE\n 40\n"+DIMEXE )
        if( DIMEXO != null) sb.append( "\n 9\n\$DIMEXO\n 40\n"+DIMEXO )
        if( DIMFAC != null) sb.append( "\n 9\n\$DIMFAC\n 40\n"+DIMFAC )
        if( DIMGAP != null) sb.append( "\n 9\n\$DIMGAP\n 40\n"+DIMGAP )
        if( DIMJUST != null) sb.append( "\n 9\n\$DIMJUST\n 70\n"+DIMJUST )
        if( DIMLDRBLK != null) sb.append( "\n 9\n\$DIMLDRBLK\n 1\n"+DIMLDRBLK )
        if( DIMLFAC != null) sb.append( "\n 9\n\$DIMLFAC\n 40\n"+DIMLFAC )
        if( DIMLIM != null) sb.append( "\n 9\n\$DIMLIM\n 70\n"+DIMLIM )
        if( DIMLUNIT != null) sb.append( "\n 9\n\$DIMLUNIT\n 70\n"+DIMLUNIT )
        if( DIMLWD != null) sb.append( "\n 9\n\$DIMLWD\n 70\n"+DIMLWD )
        if( DIMLWE != null) sb.append( "\n 9\n\$DIMLWE\n 70\n"+DIMLWE )
        if( DIMPOST != null) sb.append( "\n 9\n\$DIMPOST\n 1\n"+DIMPOST )
        if( DIMRND != null) sb.append( "\n 9\n\$DIMRND\n 40\n"+DIMRND )
        if( DIMSAH != null) sb.append( "\n 9\n\$DIMSAH\n 70\n"+DIMSAH )
        if( DIMSCALE != null) sb.append( "\n 9\n\$DIMSCALE\n 40\n"+DIMSCALE )
        if( DIMSD1 != null) sb.append( "\n 9\n\$DIMSD1\n 70\n"+DIMSD1 )
        if( DIMSD2 != null) sb.append( "\n 9\n\$DIMSD2\n 70\n"+DIMSD2 )
        if( DIMSE1 != null) sb.append( "\n 9\n\$DIMSE1\n 70\n"+DIMSE1 )
        if( DIMSE2 != null) sb.append( "\n 9\n\$DIMSE2\n 70\n"+DIMSE2 )
        if( DIMSHO != null) sb.append( "\n 9\n\$DIMSHO\n 70\n"+DIMSHO )
        if( DIMSOXD != null) sb.append( "\n 9\n\$DIMSOXD\n 70\n"+DIMSOXD )
        if( DIMSTYLE != null) sb.append( "\n 9\n\$DIMSTYLE\n 2\n"+DIMSTYLE )
        if( DIMTAD != null) sb.append( "\n 9\n\$DIMTAD\n 70\n"+DIMTAD )
        if( DIMTDEC != null) sb.append( "\n 9\n\$DIMTDEC\n 70\n"+DIMTDEC )
        if( DIMTFAC != null) sb.append( "\n 9\n\$DIMTFAC\n 40\n"+DIMTFAC )
        if( DIMTIH != null) sb.append( "\n 9\n\$DIMTIH\n 70\n"+DIMTIH )
        if( DIMTIX != null) sb.append( "\n 9\n\$DIMTIX\n 70\n"+DIMTIX )
        if( DIMTM != null) sb.append( "\n 9\n\$DIMTM\n 40\n"+DIMTM )
        if( DIMTMOVE != null) sb.append( "\n 9\n\$DIMTMOVE\n 70\n"+DIMTMOVE )
        if( DIMTOFL != null) sb.append( "\n 9\n\$DIMTOFL\n 70\n"+DIMTOFL )
        if( DIMTOH != null) sb.append( "\n 9\n\$DIMTOH\n 70\n"+DIMTOH )
        if( DIMTOL != null) sb.append( "\n 9\n\$DIMTOL\n 70\n"+DIMTOL )
        if( DIMTOLJ != null) sb.append( "\n 9\n\$DIMTOLJ\n 70\n"+DIMTOLJ )
        if( DIMTP != null) sb.append( "\n 9\n\$DIMTP\n 40\n"+DIMTP )
        if( DIMTSZ != null) sb.append( "\n 9\n\$DIMTSZ\n 40\n"+DIMTSZ )
        if( DIMTVP != null) sb.append( "\n 9\n\$DIMTVP\n 40\n"+DIMTVP )
        if( DIMTXSTY != null) sb.append( "\n 9\n\$DIMTXSTY\n 7\n"+DIMTXSTY )
        if( DIMTXT != null) sb.append( "\n 9\n\$DIMTXT\n 40\n"+DIMTXT )
        if( DIMTZIN != null) sb.append( "\n 9\n\$DIMTZIN\n 70\n"+DIMTZIN )
        if( DIMUPT != null) sb.append( "\n 9\n\$DIMUPT\n 70\n"+DIMUPT )
        if( DIMZIN != null) sb.append( "\n 9\n\$DIMZIN\n 70\n"+DIMZIN )
        if( DISPSILH != null) sb.append( "\n 9\n\$DISPSILH\n 70\n"+DISPSILH )
        if( DRAGVS != null) sb.append( "\n 9\n\$DRAGVS\n 349\n"+DRAGVS )
        if( DWGCODEPAGE != null) sb.append( "\n 9\n\$DWGCODEPAGE\n 3\n"+DWGCODEPAGE )
        if( ELEVATION != null) sb.append( "\n 9\n\$ELEVATION\n 40\n"+ELEVATION )
        if( ENDCAPS != null) sb.append( "\n 9\n\$ENDCAPS\n 280\n"+ENDCAPS )
        if( EXTMAX != null) sb.append( "\n 9\n\$EXTMAX\n 10\n"+EXTMAX?.x+"\n 20\n"+EXTMAX?.y+"\n 30\n"+EXTMAX?.z )
        if( EXTMIN != null) sb.append( "\n 9\n\$EXTMIN\n 10\n"+EXTMIN?.x+"\n 20\n"+EXTMIN?.y+"\n 30\n"+EXTMIN?.z )
        if( EXTNAMES != null) sb.append( "\n 9\n\$EXTNAMES\n 290\n"+EXTNAMES.to01() )
        if( FILLETRAD != null) sb.append( "\n 9\n\$FILLETRAD\n 40\n"+FILLETRAD )
        if( FILLMODE != null) sb.append( "\n 9\n\$FILLMODE\n 70\n"+FILLMODE )
        if( FINGERPRINTGUID != null) sb.append( "\n 9\n\$FINGERPRINTGUID\n 2\n"+FINGERPRINTGUID )
        if( HALOGAP != null) sb.append( "\n 9\n\$HALOGAP\n 280\n"+HALOGAP )
        if( HANDSEED != null) sb.append( "\n 9\n\$HANDSEED\n 5\n"+Integer.toHexString(HANDSEED!!)  )
        if( HIDETEXT != null) sb.append( "\n 9\n\$HIDETEXT\n 290\n"+HIDETEXT.to01() )
        if( HYPERLINKBASE != null) sb.append( "\n 9\n\$HYPERLINKBASE\n 1\n"+HYPERLINKBASE )
        if( INDEXCTL != null) sb.append( "\n 9\n\$INDEXCTL\n 280\n"+INDEXCTL )
        if( INSBASE != null) sb.append( "\n 9\n\$INSBASE\n 10\n"+INSBASE?.x+"\n 20\n"+INSBASE?.y+"\n 30\n"+INSBASE?.z )
        if( INSUNITS != null) sb.append( "\n 9\n\$INSUNITS\n 70\n"+INSUNITS )
        if( INTERFERECOLOR != null) sb.append( "\n 9\n\$INTERFERECOLOR\n 62\n"+INTERFERECOLOR )
        if( INTERFEREOBJVS != null) sb.append( "\n 9\n\$INTERFEREOBJVS\n 345\n"+INTERFEREOBJVS )
        if( INTERFEREVPVS != null) sb.append( "\n 9\n\$INTERFEREVPVS\n 346\n"+INTERFEREVPVS )
        if( INTERSECTIONCOLOR != null) sb.append( "\n 9\n\$INTERSECTIONCOLOR\n 70\n"+INTERSECTIONCOLOR )
        if( INTERSECTIONDISPLAY != null) sb.append( "\n 9\n\$INTERSECTIONDISPLAY\n 290\n"+INTERSECTIONDISPLAY.to01() )
        if( JOINSTYLE != null) sb.append( "\n 9\n\$JOINSTYLE\n 280\n"+JOINSTYLE )
        if( LIMCHECK != null) sb.append( "\n 9\n\$LIMCHECK\n 70\n"+LIMCHECK )
        if( LIMMAX != null) sb.append( "\n 9\n\$LIMMAX\n 10\n"+LIMMAX?.x+"\n 20\n"+LIMMAX?.y )
        if( LIMMIN != null) sb.append( "\n 9\n\$LIMMIN\n 10\n"+LIMMIN?.x+"\n 20\n"+LIMMIN?.y )
        if( LTSCALE != null) sb.append( "\n 9\n\$LTSCALE\n 40\n"+LTSCALE )
        if( LUNITS != null) sb.append( "\n 9\n\$LUNITS\n 70\n"+LUNITS )
        if( LUPREC != null) sb.append( "\n 9\n\$LUPREC\n 70\n"+LUPREC )
        if( LWDISPLAY != null) sb.append( "\n 9\n\$LWDISPLAY\n 290\n"+LWDISPLAY.to01() )
        if( MAXACTVP != null) sb.append( "\n 9\n\$MAXACTVP\n 70\n"+MAXACTVP )
        if( MEASUREMENT != null) sb.append( "\n 9\n\$MEASUREMENT\n 70\n"+MEASUREMENT )
        if( MENU != null) sb.append( "\n 9\n\$MENU\n 1\n"+MENU )
        if( MIRRTEXT != null) sb.append( "\n 9\n\$MIRRTEXT\n 70\n"+MIRRTEXT )
        if( OBSCOLOR != null) sb.append( "\n 9\n\$OBSCOLOR\n 70\n"+OBSCOLOR )
        if( OBSLTYPE != null) sb.append( "\n 9\n\$OBSLTYPE\n 280\n"+OBSLTYPE )
        if( ORTHOMODE != null) sb.append( "\n 9\n\$ORTHOMODE\n 70\n"+ORTHOMODE )
        if( PDMODE != null) sb.append( "\n 9\n\$PDMODE\n 70\n"+PDMODE )
        if( PDSIZE != null) sb.append( "\n 9\n\$PDSIZE\n 40\n"+PDSIZE )
        if( PELEVATION != null) sb.append( "\n 9\n\$PELEVATION\n 40\n"+PELEVATION )
        if( PEXTMAX != null) sb.append( "\n 9\n\$PEXTMAX\n 10\n"+PEXTMAX?.x+"\n 20\n"+PEXTMAX?.y+"\n 30\n"+PEXTMAX?.z )
        if( PEXTMIN != null) sb.append( "\n 9\n\$PEXTMIN\n 10\n"+PEXTMIN?.x+"\n 20\n"+PEXTMIN?.y+"\n 30\n"+PEXTMIN?.z )
        if( PINSBASE != null) sb.append( "\n 9\n\$PINSBASE\n 10\n"+PINSBASE?.x+"\n 20\n"+PINSBASE?.y+"\n 30\n"+PINSBASE?.z )
        if( PLIMCHECK != null) sb.append( "\n 9\n\$PLIMCHECK\n 70\n"+PLIMCHECK )
        if( PLIMMAX != null) sb.append( "\n 9\n\$PLIMMAX\n 10\n"+PLIMMAX?.x+"\n 20\n"+PLIMMAX?.y )
        if( PLIMMIN != null) sb.append( "\n 9\n\$PLIMMIN\n 10\n"+PLIMMIN?.x+"\n 20\n"+PLIMMIN?.y )
        if( PLINEGEN != null) sb.append("\n 9\n\$PLINEGEN\n 70\n$PLINEGEN")
        if( PLINEWID != null) sb.append( "\n 9\n\$PLINEWID\n 40\n"+PLINEWID )
        if( PROJECTNAME != null) sb.append( "\n 9\n\$PROJECTNAME\n 1\n"+PROJECTNAME )
        if( PROXYGRAPHICS != null) sb.append( "\n 9\n\$PROXYGRAPHICS\n 70\n"+PROXYGRAPHICS )
        if( PSLTSCALE != null) sb.append( "\n 9\n\$PSLTSCALE\n 70\n"+PSLTSCALE )
        if( PSTYLEMODE != null) sb.append( "\n 9\n\$PSTYLEMODE\n 290\n"+PSTYLEMODE.to01() )
        if( PSVPSCALE != null) sb.append( "\n 9\n\$PSVPSCALE\n 40\n"+PSVPSCALE )
        if( PUCSBASE != null) sb.append( "\n 9\n\$PUCSBASE\n 2\n"+PUCSBASE )
        if( PUCSNAME != null) sb.append( "\n 9\n\$PUCSNAME\n 2\n"+PUCSNAME )
        if( PUCSORG != null) sb.append( "\n 9\n\$PUCSORG\n 10\n"+PUCSORG?.x+"\n 20\n"+PUCSORG?.y+"\n 30\n"+PUCSORG?.z )
        if( PUCSORGBACK != null) sb.append( "\n 9\n\$PUCSORGBACK\n 10\n"+PUCSORGBACK?.x+"\n 20\n"+PUCSORGBACK?.y+"\n 30\n"+PUCSORGBACK?.z )
        if( PUCSORGBOTTOM != null) sb.append( "\n 9\n\$PUCSORGBOTTOM\n 10\n"+PUCSORGBOTTOM?.x+"\n 20\n"+PUCSORGBOTTOM?.y+"\n 30\n"+PUCSORGBOTTOM?.z )
        if( PUCSORGFRONT != null) sb.append( "\n 9\n\$PUCSORGFRONT\n 10\n"+PUCSORGFRONT?.x+"\n 20\n"+PUCSORGFRONT?.y+"\n 30\n"+PUCSORGFRONT?.z )
        if( PUCSORGLEFT != null) sb.append( "\n 9\n\$PUCSORGLEFT\n 10\n"+PUCSORGLEFT?.x+"\n 20\n"+PUCSORGLEFT?.y+"\n 30\n"+PUCSORGLEFT?.z )
        if( PUCSORGRIGHT != null) sb.append( "\n 9\n\$PUCSORGRIGHT\n 10\n"+PUCSORGRIGHT?.x+"\n 20\n"+PUCSORGRIGHT?.y+"\n 30\n"+PUCSORGRIGHT?.z )
        if( PUCSORGTOP != null) sb.append( "\n 9\n\$PUCSORGTOP\n 10\n"+PUCSORGTOP?.x+"\n 20\n"+PUCSORGTOP?.y+"\n 30\n"+PUCSORGTOP?.z )
        if( PUCSORTHOREF != null) sb.append( "\n 9\n\$PUCSORTHOREF\n 2\n"+PUCSORTHOREF )
        if( PUCSORTHOVIEW != null) sb.append( "\n 9\n\$PUCSORTHOVIEW\n 70\n"+PUCSORTHOVIEW )
        if( PUCSXDIR != null) sb.append( "\n 9\n\$PUCSXDIR\n 10\n"+PUCSXDIR?.x+"\n 20\n"+PUCSXDIR?.y+"\n 30\n"+PUCSXDIR?.z )
        if( PUCSYDIR != null) sb.append( "\n 9\n\$PUCSYDIR\n 10\n"+PUCSYDIR?.x+"\n 20\n"+PUCSYDIR?.y+"\n 30\n"+PUCSYDIR?.z )
        if( QTEXTMODE != null) sb.append( "\n 9\n\$QTEXTMODE\n 70\n"+QTEXTMODE )
        if( REGENMODE != null) sb.append( "\n 9\n\$REGENMODE\n 70\n"+REGENMODE )
        if( SHADEDGE != null) sb.append( "\n 9\n\$SHADEDGE\n 70\n"+SHADEDGE )
        if( SHADEDIF != null) sb.append( "\n 9\n\$SHADEDIF\n 70\n"+SHADEDIF )
        if( SHADOWPLANELOCATION != null) sb.append( "\n 9\n\$SHADOWPLANELOCATION\n 40\n"+SHADOWPLANELOCATION )
        if( SKETCHINC != null) sb.append( "\n 9\n\$SKETCHINC\n 40\n"+SKETCHINC )
        if( SKPOLY != null) sb.append( "\n 9\n\$SKPOLY\n 70\n"+SKPOLY )
        if( SORTENTS != null) sb.append( "\n 9\n\$SORTENTS\n 280\n"+SORTENTS )
        if( SPLINESEGS != null) sb.append( "\n 9\n\$SPLINESEGS\n 70\n"+SPLINESEGS )
        if( SPLINETYPE != null) sb.append( "\n 9\n\$SPLINETYPE\n 70\n"+SPLINETYPE )
        if( SURFTAB1 != null) sb.append( "\n 9\n\$SURFTAB1\n 70\n"+SURFTAB1 )
        if( SURFTAB2 != null) sb.append( "\n 9\n\$SURFTAB2\n 70\n"+SURFTAB2 )
        if( SURFTYPE != null) sb.append( "\n 9\n\$SURFTYPE\n 70\n"+SURFTYPE )
        if( SURFU != null) sb.append( "\n 9\n\$SURFU\n 70\n"+SURFU )
        if( SURFV != null) sb.append( "\n 9\n\$SURFV\n 70\n"+SURFV )
        if( TDCREATE != null) sb.append( "\n 9\n\$TDCREATE\n 40\n"+TDCREATE )
        if( TDINDWG != null) sb.append( "\n 9\n\$TDINDWG\n 40\n"+TDINDWG )
        if( TDUCREATE != null) sb.append( "\n 9\n\$TDUCREATE\n 40\n"+TDUCREATE )
        if( TDUPDATE != null) sb.append( "\n 9\n\$TDUPDATE\n 40\n"+TDUPDATE )
        if( TDUSRTIMER != null) sb.append( "\n 9\n\$TDUSRTIMER\n 40\n"+TDUSRTIMER )
        if( TDUUPDATE != null) sb.append( "\n 9\n\$TDUUPDATE\n 40\n"+TDUUPDATE )
        if( TEXTSIZE != null) sb.append( "\n 9\n\$TEXTSIZE\n 40\n"+TEXTSIZE )
        if( TEXTSTYLE != null) sb.append( "\n 9\n\$TEXTSTYLE\n 7\n"+TEXTSTYLE )
        if( THICKNESS != null) sb.append( "\n 9\n\$THICKNESS\n 40\n"+THICKNESS )
        if( TILEMODE != null) sb.append( "\n 9\n\$TILEMODE\n 70\n"+TILEMODE )
        if( TRACEWID != null) sb.append( "\n 9\n\$TRACEWID\n 40\n"+TRACEWID )
        if( TREEDEPTH != null) sb.append( "\n 9\n\$TREEDEPTH\n 70\n"+TREEDEPTH )
        if( UCSBASE != null) sb.append( "\n 9\n\$UCSBASE\n 2\n"+UCSBASE )
        if( UCSNAME != null) sb.append( "\n 9\n\$UCSNAME\n 2\n"+UCSNAME )
        if( UCSORG != null) sb.append( "\n 9\n\$UCSORG\n 10\n"+UCSORG?.x+"\n 20\n"+UCSORG?.y+"\n 30\n"+UCSORG?.z )
        if( UCSORGBACK != null) sb.append( "\n 9\n\$UCSORGBACK\n 10\n"+UCSORGBACK?.x+"\n 20\n"+UCSORGBACK?.y+"\n 30\n"+UCSORGBACK?.z )
        if( UCSORGBOTTOM != null) sb.append( "\n 9\n\$UCSORGBOTTOM\n 10\n"+UCSORGBOTTOM?.x+"\n 20\n"+UCSORGBOTTOM?.y+"\n 30\n"+UCSORGBOTTOM?.z )
        if( UCSORGFRONT != null) sb.append( "\n 9\n\$UCSORGFRONT\n 10\n"+UCSORGFRONT?.x+"\n 20\n"+UCSORGFRONT?.y+"\n 30\n"+UCSORGFRONT?.z )
        if( UCSORGLEFT != null) sb.append( "\n 9\n\$UCSORGLEFT\n 10\n"+UCSORGLEFT?.x+"\n 20\n"+UCSORGLEFT?.y+"\n 30\n"+UCSORGLEFT?.z )
        if( UCSORGRIGHT != null) sb.append( "\n 9\n\$UCSORGRIGHT\n 10\n"+UCSORGRIGHT?.x+"\n 20\n"+UCSORGRIGHT?.y+"\n 30\n"+UCSORGRIGHT?.z )
        if( UCSORGTOP != null) sb.append( "\n 9\n\$UCSORGTOP\n 10\n"+UCSORGTOP?.x+"\n 20\n"+UCSORGTOP?.y+"\n 30\n"+UCSORGTOP?.z )
        if( UCSORTHOREF != null) sb.append( "\n 9\n\$UCSORTHOREF\n 2\n"+UCSORTHOREF )
        if( UCSORTHOVIEW != null) sb.append( "\n 9\n\$UCSORTHOVIEW\n 70\n"+UCSORTHOVIEW )
        if( UCSXDIR != null) sb.append( "\n 9\n\$UCSXDIR\n 10\n"+UCSXDIR?.x+"\n 20\n"+UCSXDIR?.y+"\n 30\n"+UCSXDIR?.z )
        if( UCSYDIR != null) sb.append( "\n 9\n\$UCSYDIR\n 10\n"+UCSYDIR?.x+"\n 20\n"+UCSYDIR?.y+"\n 30\n"+UCSYDIR?.z )
        if( UNITED != null) sb.append("\n 9\n\$UNITED\n 70\n$UNITED")
        if( USERI1 != null) sb.append("\n 9\n\$USERI1 - 5\n 70\n$USERI1")
        if( USERR1 != null) sb.append("\n 9\n\$USERR1 - 5\n 40\n$USERR1")
        if( USRTIMER != null) sb.append("\n 9\n\$USRTIMER\n 70\n$USRTIMER")
        if( VERSIONGUID != null) sb.append("\n 9\n\$VERSIONGUID\n 2\n$VERSIONGUID")
        if( VISRETAIN != null) sb.append("\n 9\n\$VISRETAIN\n 70\n$VISRETAIN")
        if( WORLDVIEW != null) sb.append("\n 9\n\$WORLDVIEW\n 70\n$WORLDVIEW")
        if( XCLIPFRAME != null) sb.append("\n 9\n\$XCLIPFRAME\n 290\n${XCLIPFRAME.to01()}")
        if( XEDIT != null) sb.append("\n 9\n\$XEDIT\n 290\n${XEDIT.to01()}")
        if( sb.isNotEmpty() ) {
            if( sbX.isNotEmpty()) sbX.append( "\n ")
            sbX.append( " 0\nSECTION\n 2\nHEADER" )
            sbX.append( sb)
            sbX.append( "\n 0\nENDSEC")
        }

        return sbX
    }


    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? { return sizeMMParent }
    override fun scaleObjectToFit(maxRadiusSqr: Float): Float { return maxRadiusSqr }
    override fun collectionConnect(collectionPoint: CollectionPoint): Unit {}
    override fun xdef(): Int { return 0 }


    init {
        last(lastElem)
    }
}