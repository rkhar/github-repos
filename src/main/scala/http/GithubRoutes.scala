package http

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.server.Route
import akka.http.scaladsl.server.Directives._
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport._
import io.circe.generic.auto._

trait GithubRoutes {

  implicit val system: ActorSystem[Nothing]
  val githubClient: GithubClient

  val githubRoutes: Route = concat(zen())

  def zen(): Route = path("zen") {
    get(complete(githubClient.zen()))
  }

}
