package cn.net.leading.myapparchitecturetest

import android.arch.lifecycle.MutableLiveData

/**
 * @package cn.net.leading.myapparchitecturetest
 * @fileName IViewModelAction
 * @date 2019/2/15 13:48
 * @author Zj
 * @describe BaseViewModel需要向子类提供默认的实现
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
interface IViewModelAction {
    fun startLoading()
    fun startLoading(message: String)
    fun dismissLoading()
    fun showToast(message: String)
    fun finish()
    fun finishWithResultOk()
    fun getActionLiveData(): MutableLiveData<BaseActionEvent>
}