package zibi.robotx.autocad.android.sample_03_viewmodel.ui.state

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import zibi.robotx.autocad.android.sample_03_viewmodel.data.Moment
import zibi.robotx.autocad.android.sample_03_viewmodel.domain.MomentRequest
import zibi.robotx.autocad.android.sample_03_viewmodel.domain.Request

/**
 * @author Flywith24
 * @date   2020/5/31
 * time   22:03
 * description
 */
internal class ListViewModel : ViewModel(), Request.IMomentRequest {

    private val mMomentRequest by lazy { MomentRequest() }

    override fun getListMutableLiveData(): LiveData<List<Moment>> = mMomentRequest.getListMutableLiveData()

    override fun requestList() {
        mMomentRequest.requestList()
    }
}