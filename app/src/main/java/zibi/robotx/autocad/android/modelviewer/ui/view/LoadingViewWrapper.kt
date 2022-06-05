package zibi.robotx.autocad.android.modelviewer.ui.view


import android.app.Activity
import zibi.robotx.arplan.R
import android.view.animation.Animation
import android.view.*
import android.view.View.GONE
import android.view.animation.AnimationUtils

class LoadingViewWrapper(activity: Activity) {

    private val animation: Animation = AnimationUtils.loadAnimation(activity, R.anim.loading)
    private val loadingImage: View = activity.findViewById(R.id.loading_image)
    private val overlayView: View = activity.findViewById(R.id.loading_overlay)

    fun setVisible(visible: Boolean) {
        overlayView.visibility = if (visible) View.VISIBLE else GONE
        if (visible) {
            loadingImage.startAnimation(animation)
        } else {
            loadingImage.clearAnimation()
        }
    }

}