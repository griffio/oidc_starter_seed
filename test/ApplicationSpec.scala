import org.junit.runner._
import org.specs2.runner._
import play.api.test.{FakeRequest, PlaySpecification, WithApplication}

@RunWith(classOf[JUnitRunner])
class ApplicationSpec extends PlaySpecification {

  "Application" should {

    "handle routes" in new WithApplication {
      val Some(bogus) = route(app, FakeRequest(GET, "/bogus"))
      status(bogus) must equalTo(NOT_FOUND)

      val Some(index) = route(app, FakeRequest(GET, "/"))
      status(index) must equalTo(OK)
      contentType(index) must beSome.which(_ == "text/html")
    }
  }
}
