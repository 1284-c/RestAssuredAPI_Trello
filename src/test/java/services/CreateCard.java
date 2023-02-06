package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static config.TokenKey.TrelloKeyToken.*;
import static org.hamcrest.Matchers.*;

public class CreateCard {

    @Test
        public String postCreateCard(String name, String listId, String boardId){
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();
        Response response = request.log().all().contentType(ContentType.JSON).header("Accept","application/json").
                when().
                post("/1/cards?idList="+ listId + "&key=" +key + "&token=" +token +"&name=" + name );
        response.then().log().all();
        response.then().statusCode(200).
                body("idBoard", equalTo(boardId)).
                body("idList",equalTo(listId)).
                body("url", startsWith("https://trello.com/"));
        String jsonString = response.getBody().asString();
        String CardId = JsonPath.from(jsonString).getString("id");
        System.out.println("Card id: " + CardId);
        return CardId;
    }
}
