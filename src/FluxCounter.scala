/**
 * Created with IntelliJ IDEA.
 * System: Ubuntu
 * User: baoan @datayes
 * Date: 9/27/13
 * Time: 1:35 PM
 */
class FluxCounter extends Runnable {

  override def run() = {
    while(true) {
      Thread.sleep(1000)
      //println(client.count)
      client.count = 0
    }
  }
}
