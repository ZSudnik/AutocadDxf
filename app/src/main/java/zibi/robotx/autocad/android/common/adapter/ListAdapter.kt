package zibi.robotx.autocad.android.common.adapter

import android.widget.BaseAdapter

abstract class ListAdapter<T> : BaseAdapter {

    var items: List<T>
        set(items) {
            assertItemsNotNull(items)
            field = items
            notifyDataSetChanged()
        }

    constructor() {
        items = ArrayList<T>()
    }

    constructor(items: List<T>) {
        assertItemsNotNull(items)
        this.items = items
    }



    override fun getCount(): Int {
        return items.size
    }

    // android.widget.Adapter
    override fun getItem(position: Int): T {
        return items[position]
    }

    // android.widget.Adapter
    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    private fun assertItemsNotNull(items: List<T>?) {
        requireNotNull(items) { "Items cannot be null." }
    }
}