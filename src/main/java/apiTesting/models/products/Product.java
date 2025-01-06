package apiTesting.models.products;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private String id;
    private String name;
    private String price;
    private String brand;
    private Category category;

    public Product(String id, String name, String price, String brand, Category category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.brand = brand;
        this.category = category;
    }

    @Getter
    @Setter
    public class Category {
        private UserType usertype;
        private String category;

        public Category(UserType usertype, String category) {
            this.usertype = usertype;
            this.category = category;
        }

        @Getter
        @Setter
        public class UserType {
            private String usertype;

            public UserType(String usertype) {
                this.usertype = usertype;
            }

            @Override
            public String toString() {
                return "UserType{" +
                        "usertype=" + usertype +
                        '}';
            }
        }

        @Override
        public String toString() {
            return "Category{" +
                    "usertype=" + usertype + ", " +
                    "category=" + category +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "\n" + "Product{" +
                "id=" + id + ", " +
                "name=" + name + ", " +
                "price=" + price + ", " +
                "brand=" + brand + ", " +
                "category=" + category +
                '}';
    }
}
