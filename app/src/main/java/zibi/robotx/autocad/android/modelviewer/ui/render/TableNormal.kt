package zibi.robotx.autocad.android.modelviewer.ui.render

object TableNormal
{
    // constants for colours and coordinates
    private val one : Float = 1.0f
    private val zero : Float = 0.0f
    private val z8 : Float = 0.7f
    private val z7 : Float = 0.8f

val normals = floatArrayOf(
        1.0f, 1.0f, -1.0f,  /* Back. */
        -1.0f, 1.0f, -1.0f,
        1.0f, -1.0f, -1.0f,
        -1.0f, -1.0f, -1.0f,
        0.0f, 0.0f, -1.0f,
        -1.0f, 1.0f, 1.0f,  /* Front. */
        1.0f, 1.0f, 1.0f,
        -1.0f, -1.0f, 1.0f,
        1.0f, -1.0f, 1.0f,
        0.0f, 0.0f, 1.0f,
        -1.0f, 1.0f, -1.0f,  /* Left. */
        -1.0f, 1.0f, 1.0f,
        -1.0f, -1.0f, -1.0f,
        -1.0f, -1.0f, 1.0f,
        -1.0f, 0.0f, 0.0f,
        1.0f, 1.0f, 1.0f,  /* Right. */
        1.0f, 1.0f, -1.0f,
        1.0f, -1.0f, 1.0f,
        1.0f, -1.0f, -1.0f,
        1.0f, 0.0f, 0.0f,
        -1.0f, -1.0f, 1.0f,  /* Bottom. */
        1.0f, -1.0f, 1.0f,
        -1.0f, -1.0f, -1.0f,
        1.0f, -1.0f, -1.0f,
        0.0f, -1.0f, 0.0f,
        -1.0f, 1.0f, -1.0f,  /* Top. */
        1.0f, 1.0f, -1.0f,
        -1.0f, 1.0f, 1.0f,
        1.0f, 1.0f, 1.0f,
        0.0f, 1.0f, 0.0f
)


val tangents =    floatArrayOf(-1.0f, 0.0f, 0.0f,            /* Back */
        -1.0f, 0.0f, 0.0f,
        -1.0f, 0.0f, 0.0f,
        -1.0f, 0.0f, 0.0f,
        1.0f, 0.0f, 0.0f,                /* Front */
        1.0f, 0.0f, 0.0f,
        1.0f, 0.0f, 0.0f,
        1.0f, 0.0f, 0.0f,
        0.0f, 0.0f, 1.0f,                /* Left */
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, -1.0f,                /* Right */
        0.0f, 0.0f, -1.0f,
        0.0f, 0.0f, -1.0f,
        0.0f, 0.0f, -1.0f,
        1.0f, 0.0f, 0.0f,                /* Top */
        1.0f, 0.0f, 0.0f,
        1.0f, 0.0f, 0.0f,
        1.0f, 0.0f, 0.0f,
        1.0f, 0.0f, 0.0f,                /* Bottom */
        1.0f, 0.0f, 0.0f,
        1.0f, 0.0f, 0.0f,
        1.0f, 0.0f, 0.0f )
val biNormals = floatArrayOf(    0.0f, 1.0f, 0.0f,                /* Back */
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,                /* Front */
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,                /* Left */
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,                 /* Right */
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 1.0f, 0.0f,
        0.0f, 0.0f, -1.0f,                /* Top */
        0.0f, 0.0f, -1.0f,
        0.0f, 0.0f, -1.0f,
        0.0f, 0.0f, -1.0f,
        0.0f, 0.0f, 1.0f,                /* Bottom */
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f,
        0.0f, 0.0f, 1.0f )


    // X, Y, Z of hexahedron vertices
    val hexahedronPosition = floatArrayOf(
            // Front
            -one, one, one,
            -one, -one, one,
            one, one, one,
            -one, -one, one,
            one, -one, one,
            one, one, one,

            // Right
            one, one, one,
            one, -one, one,
            one, one, -one,
            one, -one, one,
            one, -one, -one,
            one, one, -one,

            // Back
            one, one, -one,
            one, -one, -one,
            -one, one, -one,
            one, -one, -one,
            -one, -one, -one,
            -one, one, -one,

            // Left
            -one, one, -one,
            -one, -one, -one,
            -one, one, one,
            -one, -one, -one,
            -one, -one, one,
            -one, one, one,

            // Top
            -one, one, -one,
            -one, one, one,
            one, one, -one,
            -one, one, one,
            one, one, one,
            one, one, -one,

            // Bottom
            one, -one, -one,
            one, -one, one,
            -one, -one, -one,
            one, -one, one,
            -one, -one, one,
            -one, -one, -one)

    // R, G, B, A of hexahedron vertices
    val hexahedronColor = floatArrayOf(
            // Front color
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,

            // Right color
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,

            // Back color
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,

            // Left color
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,

            // Top color
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,

            // Bottom color
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one,
            z8, z8, z8, one)

    // X, Y, Z
    val hexahedronNormal = floatArrayOf(
            // Front face
            zero, zero, one,
            zero, zero, one,
            zero, zero, one,
            zero, zero, one,
            zero, zero, one,
            zero, zero, one,

            // Right face
            one, zero, zero,
            one, zero, zero,
            one, zero, zero,
            one, zero, zero,
            one, zero, zero,
            one, zero, zero,

            // Back face
            zero, zero, -one,
            zero, zero, -one,
            zero, zero, -one,
            zero, zero, -one,
            zero, zero, -one,
            zero, zero, -one,

            // Left face
            -one, zero, zero,
            -one, zero, zero,
            -one, zero, zero,
            -one, zero, zero,
            -one, zero, zero,
            -one, zero, zero,

            // Top face
            zero, one, zero,
            zero, one, zero,
            zero, one, zero,
            zero, one, zero,
            zero, one, zero,
            zero, one, zero,

            // Bottom face
            zero, -one, zero,
            zero, -one, zero,
            zero, -one, zero,
            zero, -one, zero,
            zero, -one, zero,
            zero, -one, zero)

    val hexahedronTextureCoordinateData = floatArrayOf(
            // Front face
            zero, zero, zero,
            one, one, zero,
            zero, one, one,
            one, one, zero,

            // Right face
            zero, zero, zero,
            one, one, zero,
            zero, one, one,
            one, one, zero,

            // Back face
            zero, zero, zero,
            one, one, zero,
            zero, one, one,
            one, one, zero,

            // Left face
            zero, zero, zero,
            one, one, zero,
            zero, one, one,
            one, one, zero,

            // Top face
            zero, zero, zero,
            one, one, zero,
            zero, one, one,
            one, one, zero,

            // Bottom face
            zero, zero, zero,
            one, one, zero,
            zero, one, one,
            one, one, zero)

    // tetrahedron data
    val tetrahedronCoordinates = floatArrayOf(
            -one, one, one,
            -one, -one, -one,
            one, -one, one,

            one, -one, one,
            -one, -one, -one,
            one, one, -one,

            one, one, -one,
            -one, -one, -one,
            -one, one, one,

            -one, one, one,
            one, -one, one,
            one, one, -one)

    val tetrahedronColors = floatArrayOf(
            z7, z7, z7, one,
            z7, z7, z7, one,
            z7, z7, z7, one,

            z7, z7, z7, one,
            z7, z7, z7, one,
            z7, z7, z7, one,

            z7, z7, z7, one,
            z7, z7, z7, one,
            z7, z7, z7, one,

            z7, z7, z7, one,
            z7, z7, z7, one,
            z7, z7, z7, one)

    private val tetrahedronNormalVectors = floatArrayOf(
            -one, -one, one,
            -one, -one, one,
            -one, -one, one,

            one, -one, -one,
            one, -one, -one,
            one, -one, -one,

            -one, one, -one,
            -one, one, -one,
            -one, one, -one,

            one, one, one,
            one, one, one,
            one, one, one)




}