package zibi.robotx.autocad.android.modelviewer.util


import android.content.Context
import android.content.res.Resources
import androidx.annotation.RawRes

fun Resources.getRawTextFile(@RawRes resource: Int): String =
    openRawResource(resource).bufferedReader().use { it.readText() }

fun getTextFile(context: Context, @RawRes resource: Int): String =
    context.resources.openRawResource(resource).bufferedReader().use { it.readText() }
