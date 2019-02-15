package cn.net.leading.myapparchitecturetest


/**
 * @package cn.net.leading.myapparchitecturetest
 * @fileName BaseActionEvent
 * @date 2019/2/15 12:31
 * @author Zj
 * @describe BaseActionEvent 即用于向View层传递 action 的 model，
 * 在ViewModel通过向view层传递不同的消息类型，从而出发相应的操作
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
class BaseActionEvent(action: Int) : BaseEvent(action) {

    companion object {
        const val SHOW_LOADING_DIALOG = 1
        const val DISMISS_LOADING_DIALOG = 2
        const val SHOW_TOAST = 3
        const val FINISH = 4
        const val FINISH_WITH_RESULT_OK = 5
    }

    private var message = ""

    fun getMessage(): String {
        return this.message
    }

    fun setMessage(message: String) {
        this.message = message
    }
}