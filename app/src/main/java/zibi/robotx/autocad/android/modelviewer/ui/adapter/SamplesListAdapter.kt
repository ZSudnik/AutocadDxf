package zibi.robotx.autocad.android.modelviewer.ui.adapter

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import zibi.robotx.arplan.R
import zibi.robotx.autocad.android.common.adapter.TypedListAdapter
import zibi.robotx.autocad.android.modelviewer.ui.model.CategoryPresetItem
import zibi.robotx.autocad.android.modelviewer.ui.model.LinkPresetItem

class SamplesListAdapter(private val activity: Activity) : TypedListAdapter<Any?>() {
    override fun getViewTypeCount(): Int {
        return 2
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        if (item is CategoryPresetItem) {
            return 0
        }
        if (item is LinkPresetItem) {
            return 1
        }
        throw IllegalStateException(UNKNOWN_TYPE_ERROR)
    }

    override fun createView(item: Any?): View {
        val inflater = activity.layoutInflater
        if (item is CategoryPresetItem) {
            return inflater.inflate(R.layout.item_category, null as ViewGroup?)
        }
        if (item is LinkPresetItem) {
            return inflater.inflate(R.layout.item_sample, null as ViewGroup?)
        }
        throw IllegalStateException(UNKNOWN_TYPE_ERROR)
    }

    override fun createViewHolder(view: View, item: Any?): Any {
        if (item is CategoryPresetItem) {
            return CategoryViewHolder(view)
        }
        if (item is LinkPresetItem) {
            return LinkViewHolder(view)
        }
        throw IllegalStateException(UNKNOWN_TYPE_ERROR)
    }

    override fun populateViewHolder(holder: Any, item: Any?) {
        if (item is CategoryPresetItem) {
            (holder as CategoryViewHolder).populate(item)
        } else if (item is LinkPresetItem) {
            (holder as LinkViewHolder).populate(item)
        }
    }

    override fun isEnabled(position: Int): Boolean {
        return getItem(position) is LinkPresetItem
    }

    class CategoryViewHolder(itemView: View) {
        private val titleView: TextView = itemView.findViewById<View>(R.id.category_title) as TextView
        fun populate(item: CategoryPresetItem) {
            titleView.setText(item.titleId)
        }

    }

    class LinkViewHolder(itemView: View) {
        private val descriptionView: TextView = itemView.findViewById<View>(R.id.sample_description) as TextView
        private val iconView: ImageView = itemView.findViewById<View>(R.id.sample_icon) as ImageView
        private val titleView: TextView = itemView.findViewById<View>(R.id.sample_title) as TextView
        fun populate(item: LinkPresetItem) {
            iconView.setImageResource(item.iconId)
            titleView.setText(item.titleId)
            descriptionView.setText(item.descriptionId)
        }

    }

    companion object {
        private const val ITEM_TYPE_CATEGORY = 0
        private const val ITEM_TYPE_LINK = 1
        private const val UNKNOWN_TYPE_ERROR = "Cannot determine item type."
    }
}