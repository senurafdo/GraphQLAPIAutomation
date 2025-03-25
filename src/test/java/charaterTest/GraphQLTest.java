package charaterTest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import modal.response.GraphQLGetResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.github.jsonSnapshot.SnapshotMatcher.expect;
import static io.github.jsonSnapshot.SnapshotMatcher.start;

public class GraphQLTest {

    @BeforeClass
    public static void beforeAll() {
        start();
        // Set the base URI and base path for the GraphQL endpoint
        RestAssured.baseURI = "https://rickandmortyapi.com";
        RestAssured.basePath = "/graphql";
    }

    @Test
    public void testGraphQLGetRequestWithValidDetails() {
        String body = "{\"query\":\"query Query {\\r\\n " +
                " characters(page: 2, filter: { name: \\\"Morty\\\" }) {\\r\\n   " +
                " info {\\r\\n      count\\r\\n    }\\r\\n    results {\\r\\n    " +
                "  name\\r\\n    }\\r\\n  }\\r\\n  " +
                "location(id: 1) {\\r\\n    id\\r\\n  }\\r\\n  " +
                "episodesByIds(ids: [1, 2]) {\\r\\n    id\\r\\n  }\\r\\n}\",\"variables\":{}}";

        RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
        reqSpecBuilder.setContentType("application/json");
        reqSpecBuilder.setBody(body);

        RequestSpecification requestSpecification = RestAssured.given(reqSpecBuilder.build());
        GraphQLGetResponse graphResponse = requestSpecification.expect().when().post().as(GraphQLGetResponse.class);

        expect(graphResponse).toMatchSnapshot();
    }
}
