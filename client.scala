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
    val pool: JedisPool = new JedisPool(new JedisPoolConfig(), "10.20.112.103", 6379)

    val jedis: Jedis = pool.getResource

    val listener = new MyListener

    jedis.subscribe(listener, "future")
  }
}
