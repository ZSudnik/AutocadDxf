package zibi.robotx.autocad.common.collision

import java.text.DecimalFormat
import java.util.*

class Dimensions {
    // edge coordinates
    private var leftPt = Float.MAX_VALUE
    private var rightPt = -Float.MAX_VALUE // on x-axis
    private var topPt = -Float.MAX_VALUE
    private var bottomPt = Float.MAX_VALUE // on y-axis
    private var farPt = Float.MAX_VALUE
    private var nearPt = -Float.MAX_VALUE // on z-axis

    /**
     * @return the center of the bounding box
     */
    // min max center
    val center = floatArrayOf(0f, 0f, 0f)
    val min = floatArrayOf(0f, 0f, 0f)
    val max = floatArrayOf(0f, 0f, 0f)

    // whether at least 1 vertex was processed
    private var initialized = false

    constructor() {
        //();
    }

    constructor(
        leftPt: Float,
        rightPt: Float,
        topPt: Float,
        bottomPt: Float,
        nearPt: Float,
        farPt: Float
    ) {
        this.leftPt = leftPt
        this.rightPt = rightPt
        this.topPt = topPt
        this.bottomPt = bottomPt
        this.nearPt = nearPt
        this.farPt = farPt
        refresh()
    }

    fun update(x: Float, y: Float, z: Float) {
        if (x > rightPt) rightPt = x
        if (x < leftPt) leftPt = x
        if (y > topPt) topPt = y
        if (y < bottomPt) bottomPt = y
        if (z > nearPt) nearPt = z
        if (z < farPt) farPt = z
        refresh()
    }

    private fun refresh() {
        min[0] = getLeftPt()
        min[1] = getBottomPt()
        min[2] = getFarPt()
        max[0] = getRightPt()
        max[1] = getTopPt()
        max[2] = getNearPt()
        center[0] = (getRightPt() + getLeftPt()) / 2.0f
        center[1] = (getTopPt() + getBottomPt()) / 2.0f
        center[2] = (getNearPt() + getFarPt()) / 2.0f
        initialized = true
    }

    // ------------- use the edge coordinates ----------------------------
    val width: Float
        get() = Math.abs(getRightPt() - getLeftPt())
    val height: Float
        get() = Math.abs(getTopPt() - getBottomPt())
    val depth: Float
        get() = Math.abs(getNearPt() - getFarPt())
    val largest: Float
        get() {
            val height = height
            val depth = depth
            var largest = width
            if (height > largest) largest = height
            if (depth > largest) largest = depth
            return largest
        }

    private fun getRightPt(): Float {
        return if (!initialized) 0f else rightPt
    }

    private fun getLeftPt(): Float {
        return if (!initialized) 0f else leftPt
    }

    private fun getTopPt(): Float {
        return if (!initialized) 0f else topPt
    }

    private fun getBottomPt(): Float {
        return if (!initialized) 0f else bottomPt
    }

    private fun getNearPt(): Float {
        return if (!initialized) 0f else nearPt
    }

    private fun getFarPt(): Float {
        return if (!initialized) 0f else farPt
    }

    val cornerLeftTopNearVector: FloatArray
        get() = floatArrayOf(getLeftPt(), getTopPt(), getNearPt(), 1f)
    val cornerRightBottomFar: FloatArray
        get() = floatArrayOf(getRightPt(), getBottomPt(), getFarPt(), 1f)

    fun translate(diff: FloatArray): Dimensions {
        return Dimensions(
            leftPt + diff[0], rightPt + diff[0],
            topPt + diff[1], bottomPt + diff[1],
            nearPt + diff[2], farPt + diff[2]
        )
    }

    fun scale(scale: Float): Dimensions {
        return Dimensions(
            leftPt * scale, rightPt * scale,
            topPt * scale, bottomPt * scale,
            nearPt * scale, farPt * scale
        )
    }

    override fun toString(): String {
        return "Dimensions{" +
                "min=" + Arrays.toString(min) +
                ", max=" + Arrays.toString(max) +
                ", center=" + Arrays.toString(center) +
                ", width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                '}'
    }

    fun getRelationTo(other: Dimensions): Float {
        return largest / other.largest
    }

    companion object {
        // for reporting
        private val df = DecimalFormat("0.##") // 2 dp
    }
}