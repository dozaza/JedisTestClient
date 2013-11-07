import redis.clients.jedis.JedisPubSub

/**
 * Created with IntelliJ IDEA.
 * System: Ubuntu
 * User: baoan @datayes
 * Date: 9/25/13
 * Time: 2:57 PM
 */
class MyListener extends JedisPubSub {

  // 取得订阅的消息后的处理
  override def onMessage(channel: String, message: String) {
    println("OnMessage " + channel + "=" + message)
  }

  // 初始化订阅时候的处理
  override def onSubscribe(channel: String , subscribedChannels: Int) {
    println("OnSubscribe " + channel + "=" + subscribedChannels)
  }

  // 取消订阅时候的处理
  override def onUnsubscribe(channel: String , subscribedChannels: Int) {
    println(channel + "=" + subscribedChannels)
  }

  // 初始化按表达式的方式订阅时候的处理
  override def onPSubscribe(pattern: String , subscribedChannels: Int) {
    println(pattern + "=" + subscribedChannels)
  }

  // 取消按表达式的方式订阅时候的处理
  override def onPUnsubscribe(pattern: String , subscribedChannels: Int) {
    println(pattern + "=" + subscribedChannels)
  }

  // 取得按表达式的方式订阅的消息后的处理
  override def onPMessage(pattern: String, channel: String , message: String) {
    println(pattern + "=" + channel + "=" + message)
  }

}
