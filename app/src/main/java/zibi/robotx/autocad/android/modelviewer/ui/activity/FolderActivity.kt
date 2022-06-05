package zibi.robotx.autocad.android.modelviewer.ui.activity


import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.view.*
import android.widget.AdapterView
import android.widget.ListView
import android.widget.TextView
import androidx.core.view.isVisible
import zibi.robotx.arplan.R
import zibi.robotx.autocad.android.modelviewer.file.StorageEntity
import zibi.robotx.autocad.android.modelviewer.ui.adapter.FileListAdapter
import zibi.robotx.autocad.android.modelviewer.ui.view.ErrorViewWrapper
import android.content.Intent

import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Build.VERSION.SDK_INT
import android.provider.Settings
import androidx.core.app.ActivityCompat
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.annotation.SuppressLint
import android.app.DownloadManager
import android.content.ContentResolver
import android.content.ContentUris
import android.content.Context
import android.content.Intent.FLAG_GRANT_READ_URI_PERMISSION
import android.database.Cursor
import android.os.Environment
import android.provider.DocumentsContract
import android.provider.MediaStore
import android.provider.MediaStore.VOLUME_EXTERNAL
import android.provider.MediaStore.VOLUME_EXTERNAL_PRIMARY
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentResolverCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toFile
import zibi.robotx.autocad.android.modelviewer.file.Storage
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.InputStream
import java.lang.Exception


class FolderActivity : Activity(){
    private var errorView: ErrorViewWrapper? = null
    private var fileListView: ListView? = null
    private val controller = FolderActivityController(this)
    private val fileListAdapter = FileListAdapter(this)
//    private var name_dir_tv: TextView? = null
//    private var exit_btn: View? = null
//    private var back_btn: View? = null
//    private var firstCopySample: Boolean = false

    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createUserInterface()
        controller.onCreate()
    }

    public override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        controller.onSaveInstanceState(outState)
    }

    public override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        controller.onRestoreInstanceState(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()
//        back_btn?.isVisible = controller.isActionUpEnabled()
    }

    override fun onBackPressed() {
//        controller.onBackClicked()
    }

    fun showFiles(files: List<StorageEntity>) {
        fileListAdapter.items = files
    }

    fun showStorageError() {
        fileListView!!.visibility = View.INVISIBLE
        errorView!!.setVisible(true)
    }

    private fun createUserInterface() {
        setContentView(R.layout.activity_folder)
        actionBar?.setHomeButtonEnabled(true)
        actionBar?.setDisplayHomeAsUpEnabled(true)
        actionBar?.hide()
//        findViewById<View>(R.id.exit_btn).setOnClickListener( this)
//        findViewById<View>(R.id.share_btn).setOnClickListener( this)
//        back_btn = findViewById<View>(R.id.back_btn)
//        back_btn?.setOnClickListener( this)
//        name_dir_tv = findViewById<View>(R.id.name_dir_tv) as TextView

        errorView = ErrorViewWrapper(this)
        errorView?.setText(R.string.storage_unavailable)
        fileListView = findViewById<View>(R.id.browse_file_list) as ListView
        fileListView!!.emptyView = findViewById(R.id.browse_empty_list)
        fileListView!!.adapter = fileListAdapter
        fileListView!!.onItemClickListener = AdapterView.OnItemClickListener { parent, view, position, id ->
            controller.onFileClicked(fileListView!!.getItemAtPosition(position) as StorageEntity)
//            back_btn?.visibility = controller.makeActionUpEnabled()
//            name_dir_tv?.text = controller.getCurrentDir()
        }
//        name_dir_tv?.text = controller.getCurrentDir()
    }

//    override fun onClick( view: View?) {
//        when( view?.id){
//            R.id.exit_btn -> {
//                finishAffinity()
//            }
//            R.id.back_btn -> {
//                controller.onUpClicked()
//                name_dir_tv?.text = controller.getCurrentDir()
//                back_btn?.visibility = controller.makeActionUpEnabled()
//            }
////            R.id.share_btn -> {
////                val sharingIntent = Intent(Intent.ACTION_SEND)
////                val screenshotUri: Uri = Uri.parse(path)
////                sharingIntent.type = "image/png"
////                sharingIntent.putExtra(Intent.EXTRA_STREAM, screenshotUri)
////                startActivity(Intent.createChooser(sharingIntent, "Share image using"))
////            }
//        }
//    }


//    private fun checkRWExtStorage(activity: Activity) {
//            if (SDK_INT >= Build.VERSION_CODES.R) {
//                if ( !Environment.isExternalStorageManager() ) {
//                    val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
//                    val uri = Uri.fromParts("package", packageName, null)
//                    intent.data = uri
//                    startActivity(intent)
//                }
//            }else {
//                ActivityCompat.requestPermissions(this, arrayOf(WRITE_EXTERNAL_STORAGE), PERMISSION_REQUEST)
//            }
//    }
//
//    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//        if( grantResults.isEmpty()) return
//        if( requestCode == PERMISSION_REQUEST && grantResults[0] == PackageManager.PERMISSION_GRANTED){
//            copySampleFiles()
//        }
//    }


//    companion object {
//        val FileLocation: String = "Viewer 3D"
//        val ListSampleFiles: List<String> = listOf( "backhoe.dxf","bike.dxf","cnc machine.dxf","drilling_machine.dxf","gekko.dxf","Laurana50k.dxf","Mc Cormik-D3262.dxf","Nikon_D90_Camera.dxf","Laurana50k.dxf","sink.dxf","surface.dxf","Tamiya TT-01.dxf","threeDFaces.dxf",
//            "barrel.obj","crazy_bill.obj","humanoid_tri.obj","zombie.obj","penguin.obj","teapot.obj", "teddy.obj","ToyPlane.obj","zombie.obj",
//            "cube.off","hdodec.off","space_shuttle.off","space_station.off","teapot.off","x29_plane.off",
//            "barrel.mtl","crazy_bill.mtl",                  "zombie.mtl","penguin.mtl",                          "ToyPlane.mtl","zombie.mtl",
//            "barrel.png","crazy_bill.png",                  "zombie.png","penguin.bmp",                          "ToyPlane.bmp","zombie.png"
//            )
//        }

}