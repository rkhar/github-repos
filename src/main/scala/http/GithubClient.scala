package http

import akka.actor.typed.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.{HttpMethods, HttpRequest, HttpResponse, Uri}

import scala.concurrent.Future

class GithubClient(url: String)(implicit system: ActorSystem[Nothing]) {
  def zen(): Future[HttpResponse] = Http().singleRequest(
    HttpRequest(HttpMethods.GET, Uri(s"$url/zen"))
  )
}
