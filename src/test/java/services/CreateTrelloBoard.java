package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static config.TokenKey.TrelloKeyToken.baseUrl;
import static config.TokenKey.TrelloKeyToken.token;
import static config.TokenKey.TrelloKeyToken.key;
import static org.hamcrest.Matchers.*;

public class CreateTrelloBoard {
    @Test
    public String postCreateBoard( String name){
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();
        Response response = request.log().all().contentType(ContentType.JSON).
                 when().
                 post("/1/boards/?name=" + name + "&key=" + key +"&token=" +token);
        response.then().log().all();
        response.then().statusCode(200).
                 time(lessThan(4000L)).
                 body("name",equalTo(name)).
                 body("url", startsWith("https://trello.com/")).
                 body("prefs.backgroundColor", equalTo("#0079BF")).
                 time(lessThan(3000L));
        String jsonString = response.getBody().asString();
        String BoardId = JsonPath.from(jsonString).getString("id");
        System.out.println("Board id: " + BoardId);
        return BoardId;
    }
}
