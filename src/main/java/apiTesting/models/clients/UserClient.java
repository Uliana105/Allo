package apiTesting.models.clients;

import apiTesting.models.user.UserCreationResource;
import apiTesting.models.user.UserData;
import feign.Headers;
import feign.Param;
import feign.RequestLine;
public interface UserClient {

    @RequestLine("POST /createAccount")
    @Headers("Content-Type: application/x-www-form-urlencoded")
    UserCreationResource create(UserData data);

    @RequestLine("GET /getUserDetailByEmail?email={email}")
    UserCreationResource getUserByEmail(@Param("email") String email);
}
