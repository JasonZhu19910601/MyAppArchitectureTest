package cn.net.leading.myapparchitecturetest

/**
 * @package cn.net.leading.myapparchitecturetest
 * @fileName BaseEvent
 * @date 2019/2/15 12:23
 * @author Zj
 * @describe TODO
 * @org Leading.com(北京理正软件)
 * @email 2856211755@qq.com
 * @computer Administrator
 */
open class BaseEvent(private var action: Int) {

    fun getAction(): Int {
        return action
    }
}