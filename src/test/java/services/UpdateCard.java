package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static config.TokenKey.TrelloKeyToken.*;
import static org.hamcrest.Matchers.*;

public class UpdateCard {
    @Test
    public void putUpdateCard( String cardId,String name){
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();
        Response response = request.log().all().contentType(ContentType.JSON).header("Accept","application/json").
                when().
                put("/1/cards/"+ cardId + "?key=" +key + "&token=" +token +"&name=" + name );
        response.then().log().all();
        response.then().statusCode(200).
                body("name",equalToObject(name)).
                body("url",endsWith("updated")).
                time(lessThan(3000L));
    }
}
