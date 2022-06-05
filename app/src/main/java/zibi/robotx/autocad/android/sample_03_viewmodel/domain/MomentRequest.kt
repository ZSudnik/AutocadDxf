package zibi.robotx.autocad.android.sample_03_viewmodel.domain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import zibi.robotx.autocad.android.sample_03_viewmodel.data.Moment
import zibi.robotx.autocad.android.sample_03_viewmodel.data.DataRepository

/**
 * @author Flywith24
 * @date   2020/5/31
 * time   22:09
 * description
 */
internal class MomentRequest : Request.IMomentRequest {
    private val mListMutableLiveData = MutableLiveData<List<Moment>>()

    override fun getListMutableLiveData(): LiveData<List<Moment>> = mListMutableLiveData

    override fun requestList() {
        DataRepository.getInstance().requestList(mListMutableLiveData)
    }
}