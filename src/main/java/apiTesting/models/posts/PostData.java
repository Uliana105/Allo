package apiTesting.models.posts;

import Common.RandomGenerator;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostData {
    String title;
    String body;
    int userId;

    public PostData generateRandomPostData() {
        this.title = RandomGenerator.generateRandomString(10);
        this.body = RandomGenerator.generateRandomString(15);
        this.userId = RandomGenerator.generateRandomInteger(2);
        return this;
    }

    @Override
    public String toString() {
        return "PostData{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                '}';
    }
}
