package com.nashtech.credibility

import java.nio.file.Paths
import org.apache.pekko.actor.ActorSystem
import org.apache.pekko.cluster.sharding.{ClusterSharding, ClusterShardingSettings}
import org.apache.pekko.http.scaladsl.Http
import org.apache.pekko.management.scaladsl.PekkoManagement
import com.typesafe.config.ConfigFactory
import org.slf4j.LoggerFactory

object Main extends App {
  val log = LoggerFactory.getLogger(this.getClass)

  val Opt = """-D(\S+)=(\S+)""".r
  args.toList.foreach {
    case Opt(key, value) =>
      log.info(s"Config Override: $key = $value")
      System.setProperty(key, value)
  }

  implicit val system: ActorSystem = ActorSystem("Credibility")

  PekkoManagement(system).start()

  val rootPath = Paths.get("tmp")
  val credibilityRepository: CredibilityRepository = new FileBasedCredibilityRepository(rootPath)(system.dispatcher)

  val credibilityActorSupervisor = ClusterSharding(system).start(
    "credibility",
    CredibilityActor.props(credibilityRepository),
    ClusterShardingSettings(system),
    CredibilityActorSupervisor.idExtractor,
    CredibilityActorSupervisor.shardIdExtractor
  )

  val credibilityRoutes = new CredibilityRoutes(credibilityActorSupervisor)(system.dispatcher)

  private val port: Int = ConfigFactory.load().getInt("pekko.http.server.default-http-port")
  Http().newServerAt("localhost", port).bindFlow(credibilityRoutes.routes)
}
