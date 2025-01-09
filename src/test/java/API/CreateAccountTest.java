package API;

import Common.Constants;
import apiTesting.models.clients.UserClient;
import apiTesting.models.user.UserCreationResource;
import apiTesting.models.user.UserData;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateAccountTest {

    UserClient userClient;
    UserData userData;

    @BeforeMethod
    public void setUp() {
        userClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(UserClient.class))
                .logLevel(Logger.Level.FULL)
                .target(UserClient.class, Constants.API_AUTOMATIONEXERCISE_URL);

        userData = new UserData().generateRandomUserData();
        System.out.println(userData);
    }

    @Test
    public void createAccount() {
        UserCreationResource userCreationResource = userClient.create(userData);

        Assert.assertEquals(userCreationResource.getResponseCode(), 201, "Response code isn't correct");
        Assert.assertEquals(userCreationResource.getMessage(), "User created!", "Response message isn't correct");
    }

    @Test
    public void verifyCreatedClient() {
        UserCreationResource userCreationResource = userClient.getUserByEmail(userData.getEmail());

        Assert.assertEquals(userCreationResource.getResponseCode(), 200, "Response code isn't correct");
    }
}
