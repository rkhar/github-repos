package http

import akka.Done
import akka.actor.CoordinatedShutdown
import akka.actor.typed.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.server.Route
import org.slf4j.{Logger, LoggerFactory}

import scala.concurrent.ExecutionContext
import scala.concurrent.duration.DurationInt
import scala.util.{Failure, Success}

class HttpServer(host: String, port: Int, override val githubClient: GithubClient)(implicit val system: ActorSystem[Nothing], executionContext: ExecutionContext)
    extends HttpRoutes {
  val log: Logger = LoggerFactory.getLogger("http-server")

  Http().newServerAt(host, port).bind(routes).onComplete {
    case Success(binding) =>
      val address = binding.localAddress
      log.info(s"Server is running at http://{}:{}", address.getHostString, address.getPort)

      CoordinatedShutdown(system).addTask(CoordinatedShutdown.PhaseServiceRequestsDone, "http-graceful-terminate") { () =>
        binding.terminate(10.seconds).map { _ =>
          log.info("Server at http://{}:{}/ graceful shutdown completed", address.getHostString, address.getPort)

          Done
        }
      }
    case Failure(exception) =>
      log.error("Failed to bind HTTP endpoint, terminating system", exception)
      system.terminate()
  }
}
