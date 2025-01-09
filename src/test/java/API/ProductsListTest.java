package API;

import Common.Constants;
import apiTesting.models.clients.ProductClient;
import apiTesting.models.products.ProductResource;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.testng.Assert;
import org.testng.annotations.*;

public class ProductsListTest {

    ProductClient productClient;
    ProductResource productResource;

    @BeforeClass
    public void setUp() {
        productClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .logger(new Slf4jLogger(ProductClient.class))
                .logLevel(Logger.Level.FULL)
                .target(ProductClient.class, Constants.API_AUTOMATIONEXERCISE_URL);

        productResource = productClient.findAll();

        System.out.println(productResource.getProducts());
    }

    @Test
    public void verifyProductsListIsNotEmpty() {
        Assert.assertTrue(productResource.getProducts().size() >= 1, "Product list is empty");
    }

    @Test
    public void verifyResponseCode() {
        Assert.assertEquals(productResource.getResponseCode(), 200, "Product list is empty");
    }
}
