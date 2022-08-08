package http

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.Route

trait HttpRoutes extends GithubRoutes {
  val routes: Route = concat(
    githubRoutes,
    healthcheck()
  )

  def healthcheck(): Route = path("healthcheck") {
    get {
      complete("sup")
    }
  }
}
