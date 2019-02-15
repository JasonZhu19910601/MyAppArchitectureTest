package cn.net.leading.myapparchitecturetest

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel

/**
 * @package cn.net.leading.myapparchitecturetest
 * @fileName BaseViewModel
 * @date 2019/2/15 13:56
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
open class BaseViewModel : ViewModel(), IViewModelAction {

    private var actionLiveData: MutableLiveData<BaseActionEvent> = MutableLiveData()

    private lateinit var lifecycleOwner: LifecycleOwner


    override fun startLoading() {
        startLoading("")
    }


    override fun startLoading(message: String) {
        val baseActionEvent = BaseActionEvent(BaseActionEvent.SHOW_LOADING_DIALOG)
        baseActionEvent.setMessage(message)
        actionLiveData.value = baseActionEvent
    }

    override fun dismissLoading() {
        actionLiveData.value = BaseActionEvent(BaseActionEvent.DISMISS_LOADING_DIALOG)
    }

    override fun showToast(message: String) {
        val baseActionEvent = BaseActionEvent(BaseActionEvent.SHOW_TOAST)
        baseActionEvent.setMessage(message)
        actionLiveData.value = baseActionEvent
    }

    override fun finish() {
        actionLiveData.value = BaseActionEvent(BaseActionEvent.FINISH)
    }

    override fun finishWithResultOk() {
        actionLiveData.value = BaseActionEvent(BaseActionEvent.FINISH_WITH_RESULT_OK)
    }

    override fun getActionLiveData(): MutableLiveData<BaseActionEvent> = actionLiveData

    fun setLifecycleOwner(lifecycleOwner: LifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner
    }

}