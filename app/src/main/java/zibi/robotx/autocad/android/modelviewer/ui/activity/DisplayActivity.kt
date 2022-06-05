package zibi.robotx.autocad.android.modelviewer.ui.activity

import android.Manifest
import android.annotation.SuppressLint
import zibi.robotx.autocad.android.common.event.IEvent
import android.app.Activity
import android.content.ContentResolver
import zibi.robotx.arplan.R
import zibi.robotx.autocad.android.modelviewer.ui.render.ModelRenderer
import android.opengl.GLSurfaceView
import zibi.robotx.autocad.android.modelviewer.ui.render.DisplayRenderer
import zibi.robotx.autocad.android.modelviewer.ui.event.RotationEvent
import zibi.robotx.autocad.android.modelviewer.ui.event.ZoomEvent
import android.os.Bundle
import zibi.robotx.autocad.android.modelviewer.ui.view.ErrorViewWrapper
import zibi.robotx.autocad.android.modelviewer.ui.view.LoadingViewWrapper
import android.content.Intent
import android.opengl.GLSurfaceView.RENDERMODE_WHEN_DIRTY
import zibi.robotx.autocad.android.common.listener.OnViewScaleGestureAdapter
import zibi.robotx.autocad.android.common.listener.OnViewGestureAdapter
import android.view.*
import android.view.View.*
import com.android.billingclient.api.*
import zibi.robotx.autocad.android.modelviewer.ui.event.ClickPictureEvent


class DisplayActivity : Activity(), View.OnClickListener {
    private val controller = DisplayActivityController(this)
    private var errorView: ErrorViewWrapper? = null
    private var loadingView: LoadingViewWrapper? = null
    private var renderer: DisplayRenderer? = null
    private var rotationDetector: GestureDetector? = null
    private var scaleDetector: ScaleGestureDetector? = null
    private var settingsView: View? = null
    private var surface: GLSurfaceView? = null
    private var buttonExit: View? = null
    private var buttonOpen: View? = null
    private var buttonSetup: View? = null
    private var buttonSelect: View? = null
    private var buttonDeSelect: View? = null


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(1024)
        createUserInterface()
        controller.onCreate()
//        copySampleFiles()
    }


    override fun onPause() {
        super.onPause()
        surface!!.onPause()
    }

    override fun onResume() {
        super.onResume()
        surface!!.onResume()
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        controller.onNewIntent()
    }

    override fun onSaveInstanceState(bundle: Bundle) {
        super.onSaveInstanceState(bundle)
        renderer!!.save(bundle)
        controller.onSaveInstanceState(bundle)
    }

    override fun onRestoreInstanceState(bundle: Bundle) {
        super.onRestoreInstanceState(bundle)
        renderer!!.restore(bundle)
        controller.onRestoreInstanceState(bundle)
    }

    override fun onDestroy() {
        controller.onDestroy()
        super.onDestroy()
    }

    override fun onBackPressed() {
        controller.onBackPressed()
        super.onBackPressed()
    }

    fun isSettingsVisible(): Boolean {
        return settingsView!!.visibility == VISIBLE
    }

    fun toggleSettings() {
        setSettingsVisible(!isSettingsVisible())
    }

    fun setSettingsVisible(visible: Boolean) {
        if (visible) {
            settingsView!!.visibility = VISIBLE
        } else {
            settingsView!!.visibility = GONE
        }
    }

    fun showErrorDialog(errorMessage: String?) {
        surface!!.visibility = INVISIBLE
        errorView!!.setText(errorMessage)
        errorView!!.setVisible(true)
    }

    fun startLoading() {
        loadingView!!.setVisible(true)
    }

    fun stopLoading() {
        loadingView!!.setVisible(false)
    }

    @SuppressLint("ClickableViewAccessibility")
    private fun createUserInterface() {
        setContentView(R.layout.activity_display)
        actionBar?.setHomeButtonEnabled(false)
        actionBar?.setDisplayHomeAsUpEnabled(false)
        actionBar?.hide()
        errorView = ErrorViewWrapper(this)
        loadingView = LoadingViewWrapper(this)
        settingsView = findViewById(R.id.display_settings_holder)
        scaleDetector = ScaleGestureDetector(this, CustomScaleGestureListener(this, null))
        rotationDetector = GestureDetector(this, CustomOnGestureListener(this, null))
        rotationDetector?.setIsLongpressEnabled(false)
        findViewById<View>(R.id.exit_btn).setOnClickListener( this)
        findViewById<View>(R.id.open_btn).setOnClickListener( this)
        findViewById<View>(R.id.sample_btn).setOnClickListener( this)
        findViewById<View>(R.id.setup_btn).setOnClickListener( this)
        findViewById<View>(R.id.select_all_btn).setOnClickListener( this)
        findViewById<View>(R.id.deselect_all_btn).setOnClickListener( this)

        surface = getSurfaceView()
        surface?.setEGLContextClientVersion(2)
        renderer = DisplayRenderer( surface!!.context)
        surface?.setRenderer(renderer)

        surface?.renderMode = RENDERMODE_WHEN_DIRTY
        surface?.setOnTouchListener { v, event ->
            val result = handleSurfaceTouched(event)
            surface!!.requestRender()
            result
        }

    }

    fun handleSurfaceTouched(event: MotionEvent?): Boolean {
        setSettingsVisible(false)
        return rotationDetector!!.onTouchEvent(event) || scaleDetector!!.onTouchEvent(event)
    }

    private fun getSurfaceView(): GLSurfaceView {
        return findViewById<View>(R.id.display_activity_surface) as GLSurfaceView
    }

    inner class CustomScaleGestureListener private constructor() :
        OnViewScaleGestureAdapter() {
        internal constructor(
            displayActivity: DisplayActivity?,
            customScaleGestureListener: CustomScaleGestureListener?
        ) : this() {
        }

        override fun onScale(detector: ScaleGestureDetector): Boolean {
            renderer?.eventQueue?.push(ZoomEvent((detector.currentSpan - detector.previousSpan) / 10.0f))
            return true
        }

        override fun onScaleBegin(detector: ScaleGestureDetector): Boolean {
            return true
        }
    }

    inner class CustomOnGestureListener private constructor() : OnViewGestureAdapter() {

        internal constructor(
            displayActivity: DisplayActivity?,
            customOnGestureListener: CustomOnGestureListener?
        ) : this()

        override fun onScroll(e1: MotionEvent, e2: MotionEvent, distanceX: Float, distanceY: Float): Boolean {
            renderer?.eventQueue?.push(RotationEvent(distanceX, distanceY))
            return true
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            renderer?.eventQueue?.push( ClickPictureEvent(e.x, e.y) )
            return true
        }
    }


    fun setModelRenderer(modelRenderer: ModelRenderer?) {
        renderer?.modelRenderer =  modelRenderer
        surface?.requestRender()
    }

    fun pushEvent(event: IEvent?) {
        renderer?.eventQueue?.push(event)
        surface?.requestRender()
    }

    override fun onClick(v: View?) {
        when( v?.id ){
            R.id.exit_btn -> finishAffinity()
            R.id.open_btn -> actionOpenDocument()
            R.id.setup_btn -> controller.onPreferencesSelected()
            R.id.sample_btn -> controller.onOpenFolder()
//            R.id.select_all_btn ->
//            R.id.deselect_all_btn ->
        }
    }


    private fun actionOpenDocument() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "*/*"
        }
        startActivityForResult(intent, OPEN_DOCUMENT_REQUEST)
    }

    public override fun onActivityResult(requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
            if(resultData?.data != null){
                val uri = resultData.data
                val intent = Intent(this, DisplayActivity::class.java)
                intent.data = uri
                this.startActivity(intent)
        }
    }

    ///////////////// Billing
    private val purchasesUpdatedListener =
        PurchasesUpdatedListener { billingResult, purchases ->
            // To be implemented in a later section.
        }

    private var billingClient = BillingClient.newBuilder( this)
        .setListener(purchasesUpdatedListener)
        .enablePendingPurchases()
        .build()

    billingClient.startConnection(object : BillingClientStateListener {
        override fun onBillingSetupFinished(billingResult: BillingResult) {
            if (billingResult.responseCode ==  BillingClient.BillingResponseCode.OK) {
                // The BillingClient is ready. You can query purchases here.
            }
        }
        override fun onBillingServiceDisconnected() {
            // Try to restart the connection on the next request to
            // Google Play by calling the startConnection() method.
        }
    })

    override fun onPurchasesUpdated(billingResult: BillingResult, purchases: List<Purchase>?) {
        if (billingResult.responseCode == BillingClient.BillingResponseCode.OK && purchases != null) {
            for (purchase in purchases) {
                handlePurchase(purchase)
            }
        } else if (billingResult.responseCode == BillingClient.BillingResponseCode.USER_CANCELED) {
            // Handle an error caused by a user cancelling the purchase flow.
        } else {
            // Handle any other error codes.
        }
    }



    companion object{
        private const val OPEN_DOCUMENT_REQUEST = 101
val ListSampleFiles: List<String> = listOf( "backhoe.dxf","bike.dxf","cnc machine.dxf","drilling_machine.dxf","gekko.dxf","Laurana50k.dxf","Mc Cormik-D3262.dxf","Nikon_D90_Camera.dxf","Laurana50k.dxf","sink.dxf","surface.dxf","Tamiya TT-01.dxf","threeDFaces.dxf",
            "barrel.obj","crazy_bill.obj","humanoid_tri.obj","zombie.obj","penguin.obj","teapot.obj", "teddy.obj","ToyPlane.obj","zombie.obj",
            "cube.off","hdodec.off","space_shuttle.off","space_station.off","teapot.off","x29_plane.off",
            "barrel.mtl","crazy_bill.mtl",                  "zombie.mtl","penguin.mtl",                          "ToyPlane.mtl","zombie.mtl",
            "barrel.png","crazy_bill.png",                  "zombie.png","penguin.bmp",                          "ToyPlane.bmp","zombie.png"
        )

    }


}


