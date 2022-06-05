package zibi.robotx.autocad.data.dxf.protocol

import zibi.robotx.autocad.data.dxf.DxfChain
import zibi.robotx.autocad.data.dxf.DxfLoaderContext
import zibi.robotx.autocad.data.dxf.util.*

class SecBlocks(lastElem: DxfChain) : DxfChain() {

    /**
     * A collection of Drawing Blocks.
     */
    val mapBlocks: MutableMap<String, BlkBlock> = mutableMapOf()


    override fun read(dxfContext: DxfLoaderContext) {
        var code: String = dxfContext.get()
        loop@ while (true) {
            if (code == "0") {
                do{ code = dxfContext.get() }while(code == "0")
                if (code == "ENDSEC") {
                    return
                } else if (code == "BLOCK") {
                    val newBlock = BlkBlock(this, true)
                    if (newBlock.BlockName != null) mapBlocks[newBlock.BlockName!!] = newBlock
                }
            }
            code = dxfContext.get()
        }
    }

    override fun write(sbX: StringBuilder): StringBuilder {
        val sb = StringBuilder()
        if (mapBlocks.isNotEmpty()) {
            for ((k, tblBLOCK_RECORD) in mapBlocks) {
                tblBLOCK_RECORD.write(sb)
            }
        }
        if (sb.isNotEmpty()) {
            if (sbX.isNotEmpty()) sbX.append("\n ")
            sbX.append(" 0\nSECTION\n 2\nBLOCKS")
            sbX.append(sb)
            sbX.append("\n 0\nENDSEC")
        }
        return sbX
    }


    override fun centerObject(sizeMMParent: SizeMinMax?): SizeMinMax? { return sizeMMParent }
    override fun scaleObjectToFit(maxRadiusSqr: Float): Float { return maxRadiusSqr }
    override fun collectionConnect(collectionPoint: CollectionPoint) { }
    override fun xdef(): Int { return 0 }

    fun isNoBlock(BlockName: String): Boolean{
        return ! mapBlocks.containsKey( BlockName)
    }

    fun getBlkBlock(BlockName: String): BlkBlock {
        return when {
            mapBlocks.containsKey( BlockName) -> mapBlocks[BlockName]!!
            else                                  -> {
                val locBlock = BlkBlock(this, BlockName)
                mapBlocks[ BlockName] = locBlock
                locBlock
            }
        }
    }

    init {
        last(lastElem)
        lastElem.dxfContext.secBlocks = this
    }

}