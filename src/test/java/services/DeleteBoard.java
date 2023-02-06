package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static config.TokenKey.TrelloKeyToken.*;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.nullValue;

public class DeleteBoard {
    @Test
    public void deleteCreatedBoard( String boardId){
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();
        Response response = request.log().all().contentType(ContentType.JSON).header("Accept","application/json").
                when().
                delete("/1/boards/"+ boardId + "?key=" +key + "&token=" +token );
        response.then().log().all();
        response.then().statusCode(200).
                body("id", nullValue()).
                time(lessThan(3000L));


    }
}
