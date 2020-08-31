package ru.RestApi;

import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

public class RestAssuredTest {
    @Test
    @Description(value = "Получение списка пользователей")
    @Owner(value = "Фёдоров Андрей Евгеньевич")
    @Step("Проверка получения списка всех пользователей")
    public void GetUsers()
    {
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification request = RestAssured.given();
        Response response = request.request(Method.GET, "/api/users");
        String responseBody = response.getBody().asString();
        System.out.println("Ответ от сервера =>  " + responseBody);
    }
    @Test
    @Description(value = "Создание пользователя в системе")
    @Owner(value = "Фёдоров Андрей Евгеньевич")
    @Step("Проверка функции создания пользователя")
    public void CreateUser() {
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification request = RestAssured.given();
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", "Fedorov Andrey"); // Cast
        requestParams.put("job", "Qa");
        request.body(requestParams.toString());
        Response response = request.post("/api/users");
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
        System.out.println("Тело ответа на запрос" + response.getBody().asString());
    }
    @Test
    @Description(value = "Удаление пользователя в системе")
    @Owner(value = "Фёдоров Андрей Евгеньевич")
    @Step("Проверка функции удадения пользователя")
    public void DeleteUser()
    {
       int userid = 345;
       RestAssured.baseURI = "https://reqres.in";
       RequestSpecification request = RestAssured.given();
       request.header("Content-Type", "application/json");
       Response response = request.delete("/api/user"+ userid);
       int statusCode = response.getStatusCode();
       System.out.println(response.asString());
       Assert.assertEquals(statusCode, 204);


    }

}