package apiTesting.models.products;

import apiTesting.models.products.Product;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProductResource {
    private int responseCode;
    private List<Product> products;

    public ProductResource(int responseCode, List<Product> products) {
        this.responseCode = responseCode;
        this.products = products;
    }

    @Override
    public String toString() {
        return "ProductResource{" + "\n" +
                "responseCode=" + responseCode + "\n" +
                "product=" + products +
                '}';
    }
}
