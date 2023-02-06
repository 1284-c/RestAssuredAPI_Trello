package services;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

import static config.TokenKey.TrelloKeyToken.*;
import static org.hamcrest.Matchers.lessThan;

public class GetList {
    @Test
    public String getaListonCreatedBoard(String boardId){
        RestAssured.baseURI = baseUrl;
        RequestSpecification request = RestAssured.given();
        Response response = request.log().all().contentType(ContentType.JSON).header("Accept","application/json").
                when().
                get("/1/boards/"+ boardId + "/lists" +"?key=" +key + "&token=" +token);
        response.then().log().all();
        response.then().statusCode(200).
                time(lessThan(3000L));
        JsonPath path = response.jsonPath();
        List<String> listids =path.from(response.asString()).getList("id");
        // Get the first list on board
        System.out.println("ListId: " +  listids.get(0));
        return listids.get(0) ;



    }
}
