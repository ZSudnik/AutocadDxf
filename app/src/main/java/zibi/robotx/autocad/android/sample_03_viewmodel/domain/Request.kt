package zibi.robotx.autocad.android.sample_03_viewmodel.domain

import androidx.lifecycle.LiveData
import zibi.robotx.autocad.android.sample_03_viewmodel.data.Moment

/**
 * @author Flywith24
 * @date   2020/5/31
 * time   22:10
 * description
 */
internal class Request {
    interface IMomentRequest {

        fun getListMutableLiveData(): LiveData<List<Moment>>

        fun requestList()
    }
}