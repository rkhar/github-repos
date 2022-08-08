import akka.actor.typed.ActorSystem
import akka.actor.typed.scaladsl.Behaviors
import akka.http.scaladsl.model.{ContentTypes, HttpEntity}
import akka.http.scaladsl.server.Directives._
import http.{GithubClient, HttpServer}

import scala.concurrent.ExecutionContextExecutor

object Boot {

  def main(args: Array[String]): Unit = {

    implicit val system: ActorSystem[Nothing] = ActorSystem(Behaviors.empty, "my-system")
    // needed for the future flatMap/onComplete in the end
    implicit val executionContext: ExecutionContextExecutor = system.executionContext

    val githubClient = new GithubClient("https://api.github.com")

    new HttpServer("localhost", 8080, githubClient)
  }
}
