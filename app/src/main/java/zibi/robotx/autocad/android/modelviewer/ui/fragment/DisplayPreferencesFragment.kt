package zibi.robotx.autocad.android.modelviewer.ui.fragment

import zibi.robotx.arplan.R
import android.os.Bundle
import android.preference.PreferenceFragment
import android.view.*

class DisplayPreferencesFragment : PreferenceFragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        addPreferencesFromResource(R.xml.display_preferences)
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return super.onCreateView(
            inflater.cloneInContext(ContextThemeWrapper(activity, R.style.Theme_Dark)), container, savedInstanceState
        )
    }
}