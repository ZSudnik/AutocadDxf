package zibi.robotx.autocad.common.collision.old

import android.opengl.GLES20
import android.opengl.Matrix
import android.util.Log
import zibi.robotx.autocad.common.collision.createShortBuffer
import java.net.URI
import java.nio.FloatBuffer
import java.nio.IntBuffer
import java.nio.ShortBuffer
import java.util.*
/**
 * This is the basic 3D data necessary to build the 3D object
 *
 * @author andresoviedo
 */
class Object3DData {
    protected class ChangeEvent internal constructor(source: Any?) : EventObject(source)


    /**
     * Parent object if hierarchy of objects
     */
    var parent: Object3DData? = null

    /**
     * model resource locator
     */
    var uri: URI? = null

    /**
     * model id
     */
    var id: String? = null
        private set

    /**
     * model friendly name or joint name
     */
    var name: String? = null

    /**
     * Whether to draw object using indices or not
     */
    var isDrawUsingArrays = false
        private set

    /**
     * Whether the object is to be drawn
     */
    var isVisible = true
        private set
    var isSolid = true
        private set

    /**
     * The minimum thing we can draw in space is a vertex (or point).
     * This drawing mode uses the vertexBuffer
     */
    var drawMode = GLES20.GL_POINTS
        private set

    // mesh vertex data
//    var meshData: MeshData? = null

    // -------------------- Buffers ---------------------- //
    // Model data
    var vertexBuffer: FloatBuffer? = null
        protected set
    var triangleIndexPoint: MutableList<IntArray>? = null
    var normalsBuffer: FloatBuffer? = null
        private set
    var colorsBuffer: FloatBuffer? = null
        private set
    var textureBuffer: FloatBuffer? = null
        private set
    protected var elements: List<Element>? = null
    get() {
        if (field == null && drawOrder != null) {
            val element = Element(
                id,
                drawOrder,
                null
            )
//            element.setMaterial(material)
            field = listOf<Element>(element)
        }
        return field
    }

    /**
     * Object materials
     */
//    private var materials: Materials? = null

    // simple object variables for drawing using arrays
//    var material: Material = Material("default")
    var drawOrder: IntBuffer? = null
        private set
    private var indexShortBuffer: ShortBuffer? = null // in case system doesn't support ints

    // Processed arrays
    var drawModeList: List<IntArray>? = null
        private set

    // derived data
    val boundingBox: BoundingBox?
        get() = BoundingBox.create(id + "_BoundingBox1", dimensions, newModelMatrix)

    // Transformation data
    protected var scale = floatArrayOf(1f, 1f, 1f)
    protected var rotation = floatArrayOf(0f, 0f, 0f)
    var location: FloatArray = floatArrayOf(0f, 0f, 0f)
        protected set

    // extra transforms
    private var rotation1: FloatArray? = null
    var rotation2: FloatArray? = null
        private set
    var rotation2Location: FloatArray? = null
        private set

    /**
     * This is the local transformation
     */
    private val modelMatrix = FloatArray(16)

    /**
     * This is the local transformation when we have node hierarchy (ie. `<visual_scene><node><transform></transform></node></visual_scene>`
     */
    var bindTransform: FloatArray? = null

    /**
     * This is the final model transformation
     */
    val newModelMatrix = FloatArray(16)

    // whether the object has changed
    var isChanged = false

    // Async Loader
    // current dimensions
    var dimensions: Dimensions? = null
        set(dimensions)  {
            field = dimensions
            currentDimensions =dimensions
//        Log.d("Object3DData", "New fixed dimensions for " + id + ": " + this.dimensions)
        }
        get() {
            if (field == null) {
                val dimensions = Dimensions()
                if (elements == null || elements!!.isEmpty()) {
                    var i = 0
                    while (i < vertexBuffer!!.capacity()) {
                        dimensions.update(
                            vertexBuffer!![i],
                            vertexBuffer!![i + 1],
                            vertexBuffer!![i + 2]
                        )
                        i += 3
                    }
                } else {
                    for (element in elements!!) {
                        val indexBuffer: IntBuffer = element.getIndexBuffer()
                        for (i in 0 until indexBuffer.capacity()) {
                            val idx = indexBuffer[i]
                            dimensions.update(
                                vertexBuffer!![idx * 3],
                                vertexBuffer!![idx * 3 + 1],
                                vertexBuffer!![idx * 3 + 2]
                            )
                        }
                    }
                }
                field = dimensions
    //            Log.d("Object3DData", "New dimensions for '" + id + "': " + this.dimensions)
            }
            return field
        }

    protected var currentDimensions: Dimensions? = null
        get() {
            if (field == null) {
                val location = FloatArray(4)
                val ret = FloatArray(4)
                val newDimensions = Dimensions()
//                Log.i("Object3DData", "id:$id, elements:$elements")
                if (elements == null || elements!!.isEmpty()) {
                    var i = 0
                    while (i < vertexBuffer!!.capacity()) {
                        location[0] = vertexBuffer!![i]
                        location[1] = vertexBuffer!![i + 1]
                        location[2] = vertexBuffer!![i + 2]
                        location[3] = 1f
                        Matrix.multiplyMV(ret, 0, newModelMatrix, 0, location, 0)
                        newDimensions.update(ret[0], ret[1], ret[2])
                        i += 3
                    }
                } else {
                    for (element in elements!!) {
                        val indexBuffer: IntBuffer = element.getIndexBuffer()
                        for (i in 0 until indexBuffer.capacity()) {
                            val idx = indexBuffer[i]
                            location[0] = vertexBuffer!![idx * 3]
                            location[1] = vertexBuffer!![idx * 3 + 1]
                            location[2] = vertexBuffer!![idx * 3 + 2]
                            location[3] = 1f
                            Matrix.multiplyMV(ret, 0, newModelMatrix, 0, location, 0)
                            newDimensions.update(ret[0], ret[1], ret[2])
                        }
                    }
                }
                field = newDimensions
        //            Log.d("Object3DData", "Calculated current dimensions for '" + id + "': " + currentDimensions)
            }
            return field
        }

    // collision detection
    var octree: Octree? = null

    // errors detected
    private val errors: MutableList<String> = ArrayList()

    // event listeners
    private val listeners: MutableList<EventListener> = ArrayList<EventListener>()

    constructor() {}
    constructor(vertexBuffer: FloatBuffer?) {
        this.vertexBuffer = vertexBuffer
        setDrawUsingArrays(true)
        updateModelDimensions()
    }

    constructor(vertexBuffer: FloatBuffer?, triangleIndexPoint: MutableList<IntArray>?) {
        this.vertexBuffer = vertexBuffer
        this.triangleIndexPoint = triangleIndexPoint
        setDrawUsingArrays(true)
        updateModelDimensions()
    }

    constructor(vertexBuffer: FloatBuffer?, drawOrder: IntBuffer?) {
        this.vertexBuffer = vertexBuffer
        this.drawOrder = drawOrder
        setDrawUsingArrays(false)
        updateModelDimensions()
    }

    constructor(vertexBuffer: FloatBuffer?, textureBuffer: FloatBuffer?, texData: ByteArray?) {
        this.vertexBuffer = vertexBuffer
        this.textureBuffer = textureBuffer
//        material.setTextureData(texData)
        setDrawUsingArrays(true)
        updateModelDimensions()
    }

    constructor(vertexBuffer: FloatBuffer?, colorsBuffer: FloatBuffer?,
                textureBuffer: FloatBuffer?, texData: ByteArray?) {
        this.vertexBuffer = vertexBuffer
        this.colorsBuffer = colorsBuffer
        this.textureBuffer = textureBuffer
//        material.setTextureData(texData)
        setDrawUsingArrays(true)
        updateModelDimensions()
    }

    constructor(
        verts: FloatBuffer?, normals: FloatBuffer?//,
//        materials: Materials?
    ) : super() {
        vertexBuffer = verts
        normalsBuffer = normals
//        this.materials = materials
        setDrawUsingArrays(false)
        updateModelDimensions()
    }

    fun setId(id: String?): Object3DData {
        this.id = id
        return this
    }

    fun setDrawUsingArrays(drawUsingArrays: Boolean): Object3DData {
        isDrawUsingArrays = drawUsingArrays
        return this
    }

//    fun getMaterials(): Materials? {
//        return materials
//    }

    fun setSolid(solid: Boolean): Object3DData {
        isSolid = solid
        return this
    }

    // ---------------------------- dimensions ----------------------------- //
    val width: Float
        get() = currentDimensions!!.width * scaleX
    val height: Float
        get() = currentDimensions!!.height * scaleY
    val depth: Float
        get() = currentDimensions!!.depth * scaleZ


    fun addListener(listener: EventListener) {
        Log.d("Object3DData", "Listener for $id --> $listener")
        listeners.add(listener)
    }

//    protected fun fireEvent(event: EventObject?) {
//        AndroidUtils.fireEvent(listeners, event)
//    }

//    fun render(
//        drawer: RendererFactory?,
//        lightPosInWorldSpace: FloatArray?,
//        colorMask: FloatArray?
//    ) {}

//    fun hide(): Object3DData {
//        return setVisible(false)
//    }

//    fun show(): Object3DData {
//        return setVisible(true)
//    }

//    fun setVisible(isVisible: Boolean): Object3DData {
//        this.isVisible = isVisible
//        fireEvent(ChangeEvent(this))
//        return this
//    }

//    fun toggleVisible() {
//        setVisible(!isVisible)
//    }

//    val color: FloatArray
//        get() = material.getColor()

//    fun setColor(color: FloatArray?): Object3DData {
        // set color only if valid data
//        if (color != null) {
            // assert
//            require(color.size == 4) { "color should be RGBA" }
            // color variable when using single color
//            material.setDiffuse(color)
//            material.setAlpha(color[3])
//        }
//        return this
//    }

    fun setDrawMode(drawMode: Int): Object3DData {
        this.drawMode = drawMode
        return this
    }

    val drawSize: Int
        get() = 0

    // TODO: let user pick object and/or element to update texture
    // as for now, let's just update 1st element
    // -----------
//    var textureData: ByteArray
//        get() = material.getTextureData()
//        set(textureData) {
//            Log.i("Object3DData", "New texture: " + textureData.size + " (bytes)")
//            material.setTextureData(textureData)
//            if (getElements() != null && getElements()!!.size == 1) {
//                // TODO: let user pick object and/or element to update texture
//                // as for now, let's just update 1st element
//                for (i in 0..0) {
//                    if (getElements()!![i].getMaterial() == null) continue
//                    if (getElements()!![i].getMaterial().getTextureData() == null) continue
//                    getElements()!![i].getMaterial().setTextureData(textureData)
//                    Log.i(
//                        "Object3DData",
//                        "New texture for element (" + i + "): " + getElements()!![i].getMaterial()
//                    )
//                }
//            }
//        }

    fun setLocation(location: FloatArray): Object3DData {
        this.location = location
        updateModelMatrix()
        updateModelDimensions()
        isChanged = true
        return this
    }

    fun translate(translation: FloatArray) {
        location[0] += translation[0]
        location[1] += translation[1]
        location[2] += translation[2]
        updateModelMatrix()
        updateModelDimensions()
        isChanged = true
    }

    val locationX: Float
        get() = if (location != null) location[0] else 0f
    val locationY: Float
        get() = if (location != null) location[1] else 0f
    val locationZ: Float
        get() = if (location != null) location[2] else 0f


    val rotationZ: Float
        get() = rotation[2]

    fun setScale(scale: FloatArray): Object3DData {
        this.scale = scale
        updateModelMatrix()
        updateModelDimensions()
        isChanged = true
        return this
    }

//    fun setScale(x: Float, y: Float, z: Float): Object3DData {
//        return this.setScale(floatArrayOf(x, y, z))
//    }

//    @JvmName("getScale1")
//    fun getScale(): FloatArray? {
//        return scale
//    }

    private val scaleX: Float
        get() = scale[0]
    private val scaleY: Float
        get() = scale[1]
    private val scaleZ: Float
        get() = scale[2]

    fun setRotation(rotation: FloatArray): Object3DData {
        this.rotation = rotation
        updateModelMatrix()
        return this
    }

    fun setRotation1(rotation: FloatArray?): Object3DData {
        rotation1 = rotation
        updateModelMatrix()
        return this
    }

    fun setRotation2(rotation2: FloatArray?, rotation2Location: FloatArray?): Object3DData {
        this.rotation2 = rotation2
        this.rotation2Location = rotation2Location
        updateModelMatrix()
        return this
    }

    // binding coming from skeleton
    fun setBindTransform(matrix: FloatArray?): Object3DData {
        bindTransform = matrix
        updateModelMatrix()
        updateModelDimensions()
        return this
    }

    /**
     * This is the bind shape transform found in sking (ie. `<library_controllers><skin><bind_shape_matrix>`
     */
    fun setBindShapeMatrix(matrix: FloatArray?) {
        if (matrix == null) return
        val vertex = floatArrayOf(0f, 0f, 0f, 1f)
        val shaped = floatArrayOf(0f, 0f, 0f, 1f)
        var i = 0
        while (i < vertexBuffer!!.capacity()) {
            vertex[0] = vertexBuffer!![i]
            vertex[1] = vertexBuffer!![i + 1]
            vertex[2] = vertexBuffer!![i + 2]
            Matrix.multiplyMV(shaped, 0, matrix, 0, vertex, 0)
            vertexBuffer!!.put(i, shaped[0])
            vertexBuffer!!.put(i + 1, shaped[1])
            vertexBuffer!!.put(i + 2, shaped[2])
            i += 3
        }
        updateModelDimensions()
    }

    private fun updateModelMatrix() {
        Matrix.setIdentityM(modelMatrix, 0)
        if (rotation1 != null) {
            //Matrix.rotateM(modelMatrix, 0, rotation1[0], 1f, 0f, 0f);
            Matrix.rotateM(modelMatrix, 0, rotation1!![1], 0f, 1f, 0f)
            //Matrix.rotateM(modelMatrix, 0, rotation1[2], 0, 0, 1f);
        }
        if (location != null) {
            Matrix.translateM(modelMatrix, 0, locationX, locationY, locationZ)
        }
        if (rotation2 != null && rotation2Location != null) {
            Matrix.translateM(
                modelMatrix,
                0,
                rotation2Location!![0],
                rotation2Location!![1],
                rotation2Location!![2]
            )
            Matrix.rotateM(modelMatrix, 0, rotation2!![0], 1f, 0f, 0f)
            Matrix.rotateM(modelMatrix, 0, rotation2!![1], 0f, 1f, 0f)
            Matrix.rotateM(modelMatrix, 0, rotation2!![2], 0f, 0f, 1f)
            Matrix.translateM(
                modelMatrix,
                0,
                -rotation2Location!![0],
                -rotation2Location!![1],
                -rotation2Location!![2]
            )
        }
        if (rotation != null) {
            Matrix.rotateM(modelMatrix, 0, rotation[0], 1f, 0f, 0f)
            Matrix.rotateM(modelMatrix, 0, rotation[1], 0f, 1f, 0f)
            Matrix.rotateM(modelMatrix, 0, rotationZ, 0f, 0f, 1f)
        }
        if (scale != null) {
            Matrix.scaleM(modelMatrix, 0, scaleX, scaleY, scaleZ)
        }
        if (bindTransform == null) {
            // geometries not linked to any joint does not have bind transform
            System.arraycopy(modelMatrix, 0, newModelMatrix, 0, 16)
        } else {
            Matrix.multiplyMM(newModelMatrix, 0, modelMatrix, 0, bindTransform, 0)
        }
//        fireEvent(ChangeEvent(this))
    }

//    fun getModelMatrix(): FloatArray {
//        return newModelMatrix
//    }

    val transform: Transform
        get() = Transform(scale, rotation, location)

    /**
     * In case OpenGL doesn't support using GL_UNSIGNED_INT for glDrawElements(), then use this buffer
     *
     * @return the draw buffer as short
     */
    val drawOrderAsShort: ShortBuffer?
        get() {
            if (indexShortBuffer == null && drawOrder != null) {
                indexShortBuffer = createShortBuffer(drawOrder!!.capacity())
                for (i in 0 until drawOrder!!.capacity()) {
                    indexShortBuffer!!.put(drawOrder!![i].toShort())
                }
            }
            return indexShortBuffer
        }

    fun setDrawOrder(drawBuffer: IntBuffer?): Object3DData {
        drawOrder = drawBuffer
        return this
    }

    fun setVertexBuffer(vertexBuffer: FloatBuffer?): Object3DData {
        this.vertexBuffer = vertexBuffer
        updateModelDimensions()
        return this
    }

    fun setNormalsBuffer(normalsBuffer: FloatBuffer?): Object3DData {
        this.normalsBuffer = normalsBuffer
        return this
    }

    fun setTextureBuffer(textureBuffer: FloatBuffer?): Object3DData {
        this.textureBuffer = textureBuffer
        return this
    }

    fun setDrawModeList(drawModeList: List<IntArray>?): Object3DData {
        this.drawModeList = drawModeList
        return this
    }

    fun setColorsBuffer(colorsBuffer: FloatBuffer?): Object3DData {
        require(!(colorsBuffer != null && colorsBuffer.capacity() % 4 != 0)) { "Color buffer not multiple of 4 floats" }
        this.colorsBuffer = colorsBuffer
        return this
    }

    protected fun updateModelDimensions() {
        // FIXME: this breaks GUI
        //this.currentDimensions = null;

        /*final float[] location = new float[4];
        final float[] ret = new float[4];

        final Dimensions dimensions = new Dimensions();
        final Dimensions currentDimensions = new Dimensions();

        if (this.elements == null || this.elements.isEmpty()){
            for (int i = 0; i < vertexBuffer.capacity(); i += 3) {
                if (getBindTransform() != null) {
                    location[0] = vertexBuffer.get(i);
                    location[1] = vertexBuffer.get(i + 1);
                    location[2] = vertexBuffer.get(i + 2);
                    location[3] = 1;
                    Matrix.multiplyMV(ret, 0, this.getModelMatrix(), 0, location, 0);
                    currentDimensions.update(ret[0], ret[1], ret[2]);
                } else {
                    currentDimensions.update(vertexBuffer.get(i), vertexBuffer.get(i + 1), vertexBuffer.get(i + 2));
                }
                dimensions.update(vertexBuffer.get(i), vertexBuffer.get(i + 1), vertexBuffer.get(i + 2));
            }
        }
        else {
            for (Element element : getElements()) {
                final IntBuffer indexBuffer = element.getIndexBuffer();
                for (int i = 0; i < indexBuffer.capacity(); i++) {
                    final int idx = indexBuffer.get(i);
                    location[0] = vertexBuffer.get(idx * 3);
                    location[1] = vertexBuffer.get(idx * 3 + 1);
                    location[2] = vertexBuffer.get(idx * 3 + 2);
                    location[3] = 1;
                    Matrix.multiplyMV(ret, 0, this.getModelMatrix(), 0, location, 0);
                    currentDimensions.update(ret[0], ret[1], ret[2]);

                    dimensions.update(location[0], location[1], location[2]);
                }
            }
        }
        this.dimensions = dimensions;
        this.currentDimensions = currentDimensions;

        Log.d("Object3DData","New dimensions for '"+getId()+"': "+this.dimensions+", real: "+this.currentDimensions);*/
    }

    fun addError(error: String) {
        errors.add(error)
    }

    fun getErrors(): List<String> {
        return errors
    }

    fun setElements(elements: List<Element>?): Object3DData {
        this.elements = elements
        updateModelDimensions()
        return this
    }


    fun clone(): Object3DData {
        val ret = Object3DData()
        copy(ret)
        return ret
    }

    fun copy(ret: Object3DData) {
        ret.setId(id)
        ret.setLocation(location.clone())
        ret.setScale(scale.clone())
//        ret.setRotation(getRotation()!!.clone())
        ret.currentDimensions = currentDimensions
        ret.setVertexBuffer(vertexBuffer)
        ret.setNormalsBuffer(normalsBuffer)
        ret.setColorsBuffer(colorsBuffer)
        ret.setTextureBuffer(textureBuffer)
//        ret.material = material
        ret.setElements( elements)
        ret.setDrawMode(drawMode)
        ret.setDrawUsingArrays(isDrawUsingArrays)
    }

//    override fun toString(): String {
//        return "Object3DData{" +
//                "id='" + id + "'" +
//                ", name=" + name +
//                ", isVisible=" + isVisible +
//                ", color=" + Arrays.toString(material.getColor()) +
//                ", position=" + Arrays.toString(location) +
//                ", scale=" + Arrays.toString(scale) +
//                ", indexed=" + !isDrawUsingArrays +
//                ", vertices: " + (if (vertexBuffer != null) vertexBuffer!!.capacity() / 3 else 0) +
//                ", normals: " + (if (normalsBuffer != null) normalsBuffer!!.capacity() / 3 else 0) +
//                ", dimensions: " + dimensions +
//                ", current dimensions: " + currentDimensions +
//                ", material=" + material +
//                ", elements=" + getElements() +
//                '}'
//    }

    init {
        //
        Matrix.setIdentityM(modelMatrix, 0)
        Matrix.setIdentityM(newModelMatrix, 0)
    }
}