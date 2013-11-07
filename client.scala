import redis.clients.jedis.{Jedis, JedisPoolConfig, JedisPool}

/**
 * Created with IntelliJ IDEA.
 * System: Ubuntu
 * User: baoan @datayes
 * Date: 9/25/13
 * Time: 1:23 PM
 */
object client {

  def main(args: Array[String]) {

    val myThread = new MyThread
    val t = new Thread(myThread)
    t.start()

    while(true) {
      println("main thread")
      Thread.sleep(3000)
    }
  }
}
