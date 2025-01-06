package apiTesting.models.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserCreationResource {
    private int responseCode;
    private String message;

    public UserCreationResource(int responseCode, String message) {
        this.responseCode = responseCode;
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserCreationResource{" +
                "responseCode=" + responseCode +
                ", message='" + message + '\'' +
                '}';
    }
}
