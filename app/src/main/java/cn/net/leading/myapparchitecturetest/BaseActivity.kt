package cn.net.leading.myapparchitecturetest

import android.app.Activity
import android.app.ProgressDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast

/**
 * @package cn.net.leading.myapparchitecturetest
 * @fileName BaseActivity
 * @date 2019/2/15 14:51
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
abstract class BaseActivity : AppCompatActivity() {
    private lateinit var loadingDialog: ProgressDialog
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initViewModelEvent()
    }

    private fun initViewModelEvent() {
        val viewModelList: List<ViewModel> = initViewModelList()
        if (!viewModelList.isNotEmpty()) {
            observeEvent(viewModelList)
        } else {

        }
    }

    private fun observeEvent(viewModelList: List<ViewModel>) {
        viewModelList.forEach { viewModel: ViewModel ->
            if (viewModel is IViewModelAction) {
                val viewModelAction = viewModel as IViewModelAction
                viewModelAction.getActionLiveData().observe(this, Observer<BaseActionEvent> {
                    if (it != null) {
                        when (it.getAction()) {
                            BaseActionEvent.SHOW_LOADING_DIALOG -> {
                                startLoading(it.getMessage())
                            }

                            BaseActionEvent.DISMISS_LOADING_DIALOG -> {
                                dismissLoading()
                            }

                            BaseActionEvent.SHOW_TOAST -> {
                                showToast(it.getMessage())
                            }

                            BaseActionEvent.FINISH -> {
                                finish()
                            }

                            BaseActionEvent.FINISH_WITH_RESULT_OK -> {
                                setResult(Activity.RESULT_OK)
                                finish()
                            }
                        }
                    }
                })
            }
        }
    }

    protected fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    protected fun dismissLoading() {
        if (loadingDialog != null && loadingDialog.isShowing) {
            loadingDialog.dismiss()
        }
    }

    protected fun startLoading(message: String) {
        if (loadingDialog == null) {
            loadingDialog = ProgressDialog(this)
            loadingDialog.setCancelable(false)
            loadingDialog.setCanceledOnTouchOutside(false)
        }
        loadingDialog.setTitle(message)
        loadingDialog.show()
    }

    protected abstract fun initViewModel()

    protected fun initViewModelList(): List<ViewModel> {
        return emptyList()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissLoading()
    }

    protected fun finishWithResultOk() {
        setResult(Activity.RESULT_OK)
        finish()
    }

    protected fun getContext(): BaseActivity {
        return this
    }

    protected fun startActivity(cl: Class<Any>) {
        startActivity(Intent(this, cl))
    }

    fun startActivityForResult(cl: Class<Any>, requestCode: Int) {
        startActivityForResult(Intent(this, cl), requestCode)
    }

    protected fun isFinishingOrDestroyed(): Boolean {
        return isFinishing || isDestroyed
    }
}