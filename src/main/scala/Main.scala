import com.typesafe.config.ConfigFactory
import zio._
import zio.config.magnolia._
import zio.config.typesafe.TypesafeConfigProvider

case class HttpServerConfig(
  host: String,
  port: Int,
  source: List[String],
  path: List[String]
)

object HttpServerConfig {
  implicit val config: Config[HttpServerConfig] = deriveConfig[HttpServerConfig].nested("HttpServerConfig")
}

object MainA extends ZIOAppDefault {
  override val bootstrap = Runtime.setConfigProvider(TypesafeConfigProvider.fromResourcePath())

  val workflow = ZIO
    .config[HttpServerConfig](HttpServerConfig.config)
    .flatMap { config =>
      Console.printLine(
        "Application started with following configuration:\n" +
          s"\thost: ${config.host}\n" +
          s"\tport: ${config.port}\n" +
          s"\tsource: ${config.source}\n" +
          s"\tpath: ${config.path}\n"
      )
    }

  val typeSafeConf = Console.printLine(ConfigFactory.load().getConfig("HttpServerConfig").getStringList("path"))

  def run = typeSafeConf *> workflow
}
