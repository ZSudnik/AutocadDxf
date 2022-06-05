package zibi.robotx.autocad.android.modelviewer.ui.view


import android.app.Activity
import android.view.*
import android.view.View.*
import android.widget.TextView
import zibi.robotx.arplan.R

class ErrorViewWrapper(activity: Activity) {

    private val errorOverlayView: View = activity.findViewById(R.id.error_overlay)
    private val errorTextView: TextView = activity.findViewById<View>(R.id.error_text) as TextView

    fun setText(resourceId: Int) {
        errorTextView.setText(resourceId)
    }

    fun setText(text: String?) {
        errorTextView.text = text
    }

    fun setVisible(visible: Boolean) {
        errorOverlayView.visibility = if (visible) VISIBLE else GONE
    }

}