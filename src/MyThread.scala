import java.io.File
import javax.xml.parsers.{DocumentBuilder, DocumentBuilderFactory}
import org.w3c.dom.{NodeList, Element, Document}
import redis.clients.jedis.{Jedis, JedisPoolConfig, JedisPool}

/**
 * Created with IntelliJ IDEA.
 * System: Ubuntu
 * User: baoan @datayes
 * Date: 9/26/13
 * Time: 3:45 PM
 */
class MyThread extends Runnable {

  private var HOST = ""
  private var PORT = ""

  override def run() = {

    readConfig()

    //val pool: JedisPool = new JedisPool(new JedisPoolConfig(), "10.20.112.103", 6379)
    val pool: JedisPool = new JedisPool(new JedisPoolConfig(), HOST, PORT.toInt)

    val jedis: Jedis = pool.getResource

    val listener = new MyListener

    jedis.subscribe(listener, "future2")
  }

  private def readConfig(): Unit = {
    val file: File = new File("")
    val strCurrentPath: String = file.getAbsolutePath

    val strXmlpath: String = strCurrentPath + System.getProperty("file.separator") + "config.xml"
    System.out.println("Read " + strXmlpath)

    val factory: DocumentBuilderFactory = DocumentBuilderFactory.newInstance
    val builder: DocumentBuilder = factory.newDocumentBuilder
    val document: Document = builder.parse(new File(strXmlpath))
    val rootElement: Element = document.getDocumentElement

    val list: NodeList = rootElement.getElementsByTagName("Redis")
    if (null == list || list.getLength <= 0) {
      throw new RuntimeException("No \"Redis\" tag exists")
    }

    val configElement: Element = list.item(0).asInstanceOf[Element]
    val hostId = configElement.getAttribute("value")

    val hostList: NodeList = configElement.getElementsByTagName("HostList")
    if(null == hostList || hostList.getLength <= 0) {
      throw new RuntimeException("No \"HostList\" tag exists")
    }

    val hosts: NodeList = hostList.item(0).asInstanceOf[Element].getElementsByTagName("Host")
    if(null == hosts || hosts.getLength <= 0) {
      throw new RuntimeException("No \"Host\" tag exists")
    }

    var i = 0
    while(i < hosts.getLength) {
      val element: Element = hosts.item(i).asInstanceOf[Element]
      if(element.getAttribute("id") == hostId) {
        HOST = element.getAttribute("host")
        PORT = element.getAttribute("port")
        return
      }
      i += 1
    }

    throw new RuntimeException("No host id \"" + hostId + "\" exists")
  }

}
