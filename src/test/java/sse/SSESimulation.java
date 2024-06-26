package sse;

import io.gatling.javaapi.core.*;
import io.gatling.javaapi.http.*;

import java.time.Duration;

import static io.gatling.javaapi.core.CoreDsl.*;
import static io.gatling.javaapi.http.HttpDsl.*;



public class SSESimulation extends Simulation {

    private HttpProtocolBuilder httpProtocol = http.baseUrl("http://localhost:8080");
    private ScenarioBuilder scn = scenario("SSE SERVER 부하 테스트")
            .exec(sse("Connect").connect("/sub"));


    //Load Simulation
    {
        setUp(
                /**
                scn.injectOpen(
                        nothingFor(4), // 1
                        atOnceUsers(500),
                        rampUsers(1000).during(10)
                       // rampUsers(10).during(5), // 3
                       // constantUsersPerSec(20).during(15), // 4
                       // constantUsersPerSec(20).during(15).randomized(), // 5
                       // rampUsersPerSec(10).to(20).during(10), // 6
                       // rampUsersPerSec(10).to(20).during(10).randomized(), // 7
                       // stressPeakUsers(10000).during(20) // 8
                ).protocols(httpProtocol));
                 **/

                        scn.injectOpen(
                                rampUsers(10000).during(Duration.ofSeconds(30))
                        )
                ).protocols(httpProtocol).maxDuration(Duration.ofMinutes(1));
    }
}
