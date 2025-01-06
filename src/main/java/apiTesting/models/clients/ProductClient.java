package apiTesting.models.clients;

import apiTesting.models.products.ProductResource;
import feign.RequestLine;

public interface ProductClient {
    @RequestLine("GET /productsList")
    ProductResource findAll();
}
