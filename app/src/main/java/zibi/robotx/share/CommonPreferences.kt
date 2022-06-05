package zibi.robotx.share

import android.content.Context
import android.content.SharedPreferences
import android.util.Base64


open class CommonPreferences {

    protected lateinit var mSharePre: SharedPreferences
//    protected var mContext: Context? = null

//    fun init(context: Context) {
//        mContext = context
//    }

    protected fun init(context: Context, name: String?) {
//        if( mContext == null ) mContext = context
//        mSharePre = mContext?.getSharedPreferences(name, 0)!!
        mSharePre = context.getSharedPreferences(name, 0)
    }

    fun remove(key: String?): Boolean {
        return mSharePre.edit().remove(key).commit()
    }

    fun getValue(key: String?, defValue: Float): Float {
        return mSharePre.getFloat(key, defValue)
    }

    fun getValue(key: String?, defValue: Int): Int {
        return mSharePre.getInt(key, defValue)
    }

    fun getValue(key: String?, defValue: Long): Long {
        return mSharePre.getLong(key, defValue)
    }

    fun getValue(key: String, defValue: String): String? {
        return mSharePre.getString(key, defValue)
    }

    fun getValue(key: String?, defValue: Set<String?>?): Set<String>? {
        return mSharePre.getStringSet(key, defValue)
    }

    fun getValue(key: String?, defValue: Boolean): Boolean {
        return mSharePre.getBoolean(key, defValue)
    }

    fun getValue(key: String, defValue: ByteArray): ByteArray {
        val size = mSharePre.getInt(key + "_size", -1)
        if( size <= 0 ) return defValue

        val base64String: String = mSharePre.getString(key + "_", "") ?: ""
        val array: ByteArray = Base64.decode(base64String, Base64.NO_WRAP)
//        val array: ByteArray = ByteArray(size)
//        for (i in 0 until size)
//            array[i] = mSharePre.getInt(key + "_" + i, 0).toByte()
        return array.copyOfRange( 0, size)
    }

    fun setValue(key: String?, defValue: Float) {
        mSharePre.edit().putFloat(key, defValue).apply()
    }

    fun setValue(key: String?, defValue: Int) {
        mSharePre.edit().putInt(key, defValue).apply()
    }

    fun setValue(key: String?, defValue: Long) {
        mSharePre.edit().putLong(key, defValue).apply()
    }

    fun setValue(key: String?, defValue: String?) {
        mSharePre.edit().putString(key, defValue).apply()
    }

    fun setValue(key: String?, defValue: Set<String?>?) {
        mSharePre.edit().putStringSet(key, defValue).apply()
    }

    fun setValue(key: String?, defValue: Boolean) {
        mSharePre.edit().putBoolean(key, defValue).apply()
    }


    fun setValue(key: String?, defValue: ByteArray) {
        mSharePre.edit().putInt(key + "_size", defValue.size).apply()
        val base64String: String = Base64.encodeToString(defValue, Base64.NO_WRAP)
        mSharePre.edit().putString(key + "_", base64String).apply()
//        for (i in defValue.indices)
//            mSharePre.edit().putInt(key + "_" + i, defValue[i].toInt()).apply()
    }


}