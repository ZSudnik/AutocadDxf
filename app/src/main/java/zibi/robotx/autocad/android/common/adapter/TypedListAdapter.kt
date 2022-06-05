package zibi.robotx.autocad.android.common.adapter

import android.view.*

abstract class TypedListAdapter<T> : ListAdapter<T> {

    abstract fun createView(item: T): View
    abstract fun createViewHolder(view: View, item: T): Any

    abstract override fun getItemViewType(position: Int): Int

    abstract override fun getViewTypeCount(): Int
    abstract fun populateViewHolder(holder: Any, item: T)

    constructor() {}
    constructor(items: List<T>) : super(items) {}

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertViewX = convertView
        val item = getItem(position)
        if (convertViewX == null) {
            convertViewX = createView(item)
            convertViewX.tag = createViewHolder(convertViewX, item)
        }
        populateViewHolder(convertViewX.tag, item)
        return convertViewX
    }
}