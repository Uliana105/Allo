package APITests;

import Common.Constants;
import apiTesting.models.clients.PostsClient;
import apiTesting.models.posts.PostData;
import apiTesting.models.posts.PostResource;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreatePostTest {
    PostsClient postsClient;
    PostData postData;

    @BeforeMethod
    public void setUp() {
        postsClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(PostsClient.class))
                .logLevel(Logger.Level.FULL)
                .target(PostsClient.class, Constants.API_JSONPLACEHOLDER_URL);

        postData = new PostData().generateRandomPostData();
        System.out.println(postData);
    }

    @Test
    public void createAccount() {
        PostResource postResource = postsClient.create(postData);

        System.out.println(postResource);

        Assert.assertEquals(postResource.getTitle(), postData.getTitle(), "Title isn't correct");
        Assert.assertEquals(postResource.getBody(), postData.getBody(), "Body isn't correct");
        Assert.assertEquals(postResource.getUserId(), postData.getUserId(), "User id isn't correct");
        Assert.assertNotEquals(postResource.getId(), null, "Id isn't correct");
    }
}
