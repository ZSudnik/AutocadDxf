package zibi.robotx.autocad.android.modelviewer.ui.adapter

import android.app.Activity
import android.widget.TextView
import zibi.robotx.arplan.R
import zibi.robotx.autocad.android.common.adapter.TypedListAdapter
import zibi.robotx.autocad.android.modelviewer.file.StorageEntity
import zibi.robotx.autocad.android.modelviewer.file.StorageFile
import zibi.robotx.autocad.android.modelviewer.file.StorageFolder
import android.view.*


class FileListAdapter(private val activity: Activity) : TypedListAdapter<StorageEntity>() {

    override fun getViewTypeCount(): Int {
        return 1
    }

    override fun getItemViewType(position: Int): Int {
        return 0
    }

    override fun createView(item: StorageEntity): View {
        return activity.layoutInflater.inflate(R.layout.item_file, null as ViewGroup?)
    }

    override fun createViewHolder(view: View, item: StorageEntity): Any {
        return FileHolder(view)
    }

    override fun populateViewHolder(holder: Any, item: StorageEntity) {
        (holder as FileHolder).populate(item)
    }

    class FileHolder(itemView: View) {
        private val nameView: TextView = itemView.findViewById<View>(R.id.item_file_name) as TextView

        fun populate(item: StorageEntity) {
            nameView.text = item.name
            if (item is StorageFile) {
                nameView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_file, 0, 0, 0)
            }
//            if (item is StorageFolder) {
//                nameView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_folder, 0, 0, 0)
//            }
        }

    }
}