package apiTesting.models.posts;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostResource {
    String title;
    String body;
    int userId;
    int id;

    public PostResource(String title, String body, int userId, int id) {
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.id = id;
    }

    @Override
    public String toString() {
        return "PostResource{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                ", id=" + id +
                '}';
    }
}
