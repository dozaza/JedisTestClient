import redis.clients.jedis.{Jedis, JedisPoolConfig, JedisPool}

/**
 * Created with IntelliJ IDEA.
 * System: Ubuntu
 * User: baoan @datayes
 * Date: 9/26/13
 * Time: 3:45 PM
 */
class MyThread extends Runnable {

  override def run() = {
    val pool: JedisPool = new JedisPool(new JedisPoolConfig(), "10.20.112.103", 6379)

    val jedis: Jedis = pool.getResource

    val listener = new MyListener

    jedis.subscribe(listener, "future")
  }

}
