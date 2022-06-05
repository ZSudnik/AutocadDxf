package zibi.robotx.autocad.android.modelviewer.ui.activity

import android.content.ContentResolver
import zibi.robotx.autocad.android.common.event.IEvent
import zibi.robotx.autocad.android.modelviewer.ui.event.RotationModeChangeEvent.RotationMode
import zibi.robotx.autocad.android.modelviewer.data.IResourceLocator
import android.os.Bundle
import android.content.Intent
import android.content.SharedPreferences
import android.os.Parcelable
import zibi.robotx.autocad.android.modelviewer.util.URIUtil
import zibi.robotx.autocad.android.modelviewer.data.AssetResourceLocator
import zibi.robotx.autocad.android.modelviewer.data.FileResourceLocator
import android.content.SharedPreferences.OnSharedPreferenceChangeListener
import android.net.Uri
import android.provider.MediaStore
import androidx.core.content.ContentResolverCompat
import androidx.preference.PreferenceManager
import zibi.robotx.autocad.android.modelviewer.ui.loader.*
//import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import zibi.robotx.arplan.R
import zibi.robotx.autocad.android.modelviewer.data.ContentResourceLocator
import zibi.robotx.autocad.android.modelviewer.ui.event.*


class DisplayActivityController(private val activity: DisplayActivity) {

    private var BACKGROUND_KEY: String? = null
    private var BACKGROUND_VALUE_BLACK: String? = null
    private var BACKGROUND_VALUE_BLUE: String? = null
    private var BACKGROUND_VALUE_GRAY: String? = null
    private var BACKGROUND_VALUE_GREEN: String? = null
    private var BACKGROUND_VALUE_RED: String? = null
    private var BACKGROUND_VALUE_WHITE: String? = null
    private var LIGHTING_KEY: String? = null
    private var ROTATION_MODE_KEY: String? = null
    private var ROTATION_MODE_VALUE_FREE: String? = null
    private var ROTATION_MODE_VALUE_XY: String? = null
    private var ROTATION_MODE_VALUE_BLOCK: String? = null
    private var TWO_SIDED_KEY: String? = null

    private val preferencesListener = CustomPreferencesListener(this, null)

    private val mListViewModel = LoaderListener()

    fun onOpenFolder() {
        val intent = Intent(activity, FolderActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP )
        activity.startActivity(intent)
    }

    fun onPreferencesSelected() {
        activity.toggleSettings()
        activity.invalidateOptionsMenu()
    }

    fun onCreate() {
        initializeConstants()
        getPreferences().registerOnSharedPreferenceChangeListener(preferencesListener)
        updateBackground()
        updateTwoSided()
        updateLighting()
        updateRotationMode()
        activity.startLoading()
        val args = Bundle()
        if( activity.intent.data == null ){
            args.putParcelable(BUNDLE_KEY_URI, Uri.parse("asset:///samples/backhoe.dxf"))
        }else {
            args.putParcelable(BUNDLE_KEY_URI, activity.intent.data)
        }
        GlobalScope.launch(Dispatchers.Main) {
            val modelRender = mListViewModel.onCreateLoader( args)
            mListViewModel.onLoadFinished( modelRender)
        }


    }


    fun onNewIntent() {
        updateTwoSided()
        updateLighting()
        resetPositionOffRotation()
        activity.setModelRenderer(null)
        activity.startLoading()
        val args = Bundle()
        args.putParcelable(BUNDLE_KEY_URI, activity.intent.data)
        GlobalScope.launch(Dispatchers.Main) {
            val modelRender = mListViewModel.onCreateLoader( args)
            mListViewModel.onLoadFinished( modelRender)
        }

    }

    fun onErrorDialogCancelled() {
        activity.finish()
    }

    fun onDestroy() {
        getPreferences().unregisterOnSharedPreferenceChangeListener(preferencesListener)
    }

    private fun initializeConstants() {
        BACKGROUND_KEY = getString(R.string.display_preferences_background_key)
        BACKGROUND_VALUE_BLACK = getString(R.string.display_preferences_background_values_black)
        BACKGROUND_VALUE_GRAY = getString(R.string.display_preferences_background_values_gray)
        BACKGROUND_VALUE_RED = getString(R.string.display_preferences_background_values_red)
        BACKGROUND_VALUE_GREEN = getString(R.string.display_preferences_background_values_green)
        BACKGROUND_VALUE_BLUE = getString(R.string.display_preferences_background_values_blue)
        BACKGROUND_VALUE_WHITE = getString(R.string.display_preferences_background_values_white)
        TWO_SIDED_KEY = getString(R.string.display_preferences_twosided_key)
        LIGHTING_KEY = getString(R.string.display_preferences_lighting_key)
        ROTATION_MODE_KEY = getString(R.string.display_preferences_rotation_key)
        ROTATION_MODE_VALUE_XY = getString(R.string.display_preferences_rotation_values_xy)
        ROTATION_MODE_VALUE_FREE = getString(R.string.display_preferences_rotation_values_free)
        ROTATION_MODE_VALUE_BLOCK = getString(R.string.display_preferences_rotation_values_block)
    }

    fun onPreferencesEvent(key: String) {
        when(key) {
            BACKGROUND_KEY  -> updateBackground()
            TWO_SIDED_KEY -> updateTwoSided()
            LIGHTING_KEY  -> updateLighting()
            ROTATION_MODE_KEY -> updateRotationMode()
        }
    }

    private fun updateBackground() {
        val backgroundType = getPreferences().getString(BACKGROUND_KEY, null)
        when(backgroundType) {
            BACKGROUND_VALUE_BLACK -> pushEvent(BackgroundChangeEvent(0.0f, 0.0f, 0.0f))
            BACKGROUND_VALUE_GRAY -> pushEvent(BackgroundChangeEvent(0.75f, 0.75f, 0.75f))
            BACKGROUND_VALUE_RED -> pushEvent(BackgroundChangeEvent(1.0f, 0.0f, 0.0f))
            BACKGROUND_VALUE_GREEN -> pushEvent(BackgroundChangeEvent(0.0f, 1.0f, 0.0f))
            BACKGROUND_VALUE_BLUE -> pushEvent(BackgroundChangeEvent(0.0f, 0.0f, 1.0f))
            BACKGROUND_VALUE_WHITE -> pushEvent(BackgroundChangeEvent(1.0f, 1.0f, 1.0f))
            else -> pushEvent(BackgroundChangeEvent(0.0f, 0.0f, 0.0f))
        }
    }

    private fun updateTwoSided() {
        pushEvent(TwoSidedChangeEvent(getPreferences().getBoolean(TWO_SIDED_KEY, false)))
    }

    private fun updateLighting() {
        pushEvent(LightingChangeEvent(getPreferences().getBoolean(LIGHTING_KEY, true)))
    }

    private fun updateRotationMode() {
        val rotationMode = getPreferences().getString(ROTATION_MODE_KEY, null)
        when(rotationMode) {
            ROTATION_MODE_VALUE_XY -> pushEvent(RotationModeChangeEvent(RotationMode.XY))
            ROTATION_MODE_VALUE_FREE -> pushEvent(RotationModeChangeEvent(RotationMode.Free))
            ROTATION_MODE_VALUE_BLOCK -> pushEvent(RotationModeChangeEvent(RotationMode.Block))
            else ->   pushEvent(RotationModeChangeEvent(RotationMode.XY))
        }
    }

    private fun resetPositionOffRotation(){
        pushEvent(ResetPositionOffRotationEvent( true))
    }

    private fun getPreferences(): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(activity)
    }

    private fun pushEvent(event: IEvent) {
        activity.pushEvent(event)
    }

    private fun getString(id: Int): String {
        return activity.getString(id)
    }


    inner class LoaderListener() {

        suspend fun onCreateLoader( args: Bundle): ModelLoaderResult {
            val uri = args.getParcelable<Parcelable>(BUNDLE_KEY_URI) as Uri?
            val locator = getLocator(uri) ?: return InvalidSchemeModelLoader(activity)
            return getLoader(uri, locator, uri?.scheme) ?: return UnsupportedExtensionModelLoader(activity)
        }

        private fun getLocator(uri: Uri?): IResourceLocator? {
            if (uri?.path != null) {
                return when ( uri.scheme ){
                    URIUtil.SCHEMA_ASSET -> AssetResourceLocator(activity, URIUtil.toRelativePath(uri.path))
                    URIUtil.SCHEMA_FILE  -> FileResourceLocator(uri.path!!)
                    URIUtil.SCHEMA_CONTENT->ContentResourceLocator(activity, uri)
                    else -> null
                }
            }
            return null
        }

        private suspend fun getLoader(uri: Uri?, locator: IResourceLocator, uriScheme: String?): ModelLoaderResult? {
            val filename  = if( URIUtil.SCHEMA_CONTENT == uriScheme ) {
                getFileName( activity.contentResolver, uri) ?: return UnknownErrorModelLoader(activity)
            }else{
                 uri?.lastPathSegment ?: return UnknownErrorModelLoader(activity)
            }
            val extension = URIUtil.getExtension(filename)
            return when(extension){
                OFF_EXTENSION -> OffModelLoader( locator).loadInBackground()
                OBJ_EXTENSION -> ObjModelLoader( locator).loadInBackground()
                DXF_EXTENSION -> DxfModelLoader( locator).loadInBackground()
                else -> null
            }
        }

        fun onLoadFinished( data: ModelLoaderResult) {
            activity.stopLoading()
            if (data.isSuccessful()) {
                activity.setModelRenderer(data.modelRenderer)
            } else {
                activity.showErrorDialog(activity.getString(data.errorMessageId))
            }
        }

    }

    inner class CustomPreferencesListener private constructor() :
        OnSharedPreferenceChangeListener {
        internal constructor(displayActivityController: DisplayActivityController?, customPreferencesListener: CustomPreferencesListener?) : this() {}

        override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
            onPreferencesEvent(key)
        }
    }

    fun onBackPressed() {
        if (activity.isSettingsVisible()) {
            activity.setSettingsVisible( false)
        }
    }

    fun onSaveInstanceState(bundle: Bundle) {
        bundle.putBoolean(BUNDLE_KEY_SETTINGS_VISIBLE, activity.isSettingsVisible())
    }

    fun onRestoreInstanceState(bundle: Bundle) {
        activity.setSettingsVisible(
            bundle.getBoolean(BUNDLE_KEY_SETTINGS_VISIBLE, false)  )
    }

    private fun getFileName(cr: ContentResolver, uri: Uri?): String? {
        if ("content" == uri?.scheme) {
            val projection = arrayOf(MediaStore.MediaColumns.DISPLAY_NAME)
            ContentResolverCompat.query(cr, uri, projection, null, null, null, null)?.use { metaCursor ->
                if (metaCursor.moveToFirst()) {
                    return metaCursor.getString(0)
                }
            }
        }
        return uri?.lastPathSegment
    }


    companion object {
        private const val BUNDLE_KEY_SETTINGS_VISIBLE = "settings.visible"
        private const val BUNDLE_KEY_URI = "uri"
        private const val OBJ_EXTENSION = "obj"
        private const val OFF_EXTENSION = "off"
        private const val DXF_EXTENSION = "dxf"

    }

}