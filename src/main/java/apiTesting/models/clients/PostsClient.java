package apiTesting.models.clients;

import apiTesting.models.posts.PostData;
import apiTesting.models.posts.PostResource;
import feign.Headers;
import feign.RequestLine;

public interface PostsClient {
    @RequestLine("POST /posts")
    @Headers("Content-Type: application/json")
    PostResource create(PostData data);
}
