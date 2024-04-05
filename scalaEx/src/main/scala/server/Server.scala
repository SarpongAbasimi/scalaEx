package server
import cats.effect.{IO, Resource}
import com.comcast.ip4s._
import http.Endpoints
import org.http4s.{Request, Uri}
import org.http4s.ember.server.EmberServerBuilder
import org.http4s.metrics.prometheus.{Prometheus, PrometheusExportService}
import org.http4s.server.middleware.Metrics
import org.http4s.server.{Router, Server}

object Server {

  def create: Resource[IO, Server] = {
    def classifier(request: Request[IO]): Option[String] = {
      val urlMatcher: PartialFunction[Uri, Option[String]] = {
        case uri if uri.path.renderString.contains("greet") => Some("greet_label")
        case uri if uri.path.renderString.contains("hello") => Some("hello_label")
      }
      urlMatcher(request.uri)
    }


    for {
      metricsSvc <- PrometheusExportService.build[IO]
      metrics <- Prometheus.metricsOps[IO](metricsSvc.collectorRegistry, "server")
      router = Router[IO](
        "/api" -> Metrics[IO](metrics, classifierF = classifier)(Endpoints.route),
        "/" -> metricsSvc.routes
      )
      server <- EmberServerBuilder.default[IO]
        .withHost(ipv4"0.0.0.0")
        .withPort(port"8080")
        .withHttpApp(router.orNotFound)
        .build
    } yield server
  }
}
