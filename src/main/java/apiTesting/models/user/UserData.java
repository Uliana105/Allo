package apiTesting.models.user;

import Common.RandomGenerator;
import feign.Param;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class UserData {
    String name;
    String email;
    String password;
    String title;
    String birth_date;
    String birth_month;
    String birth_year;
    String firstname;
    String lastname;
    String company;
    String address1;
    String address2;
    String country;
    String zipcode;
    String state;
    String city;
    String mobile_number;

    public UserData generateRandomUserData() {
        LocalDate birthDate = RandomGenerator.generateRandomDate(LocalDate.of(1980, 1, 1),
                LocalDate.of(1990, 1, 1));

        this.name = RandomGenerator.generateRandomString(10);
        this.email = "testuser+" + RandomGenerator.generateRandomInteger(5) + "@gmail.com";
        this.password = RandomGenerator.generateRandomString(10);
        this.title = "Mr";
        this.birth_date = String.valueOf(birthDate.getDayOfMonth());
        this.birth_month = String.valueOf(birthDate.getMonthValue());
        this.birth_year = String.valueOf(birthDate.getYear());
        this.firstname = RandomGenerator.generateRandomStringWithoutNumbers(10);
        this.lastname = RandomGenerator.generateRandomStringWithoutNumbers(10);
        this.company = RandomGenerator.generateRandomStringWithoutNumbers(5);
        this.address1 = RandomGenerator.generateRandomStringWithoutNumbers(5);
        this.address2 = RandomGenerator.generateRandomStringWithoutNumbers(5);
        this.country = RandomGenerator.generateRandomStringWithoutNumbers(5);
        this.zipcode = String.valueOf(RandomGenerator.generateRandomInteger(5));
        this.state = RandomGenerator.generateRandomStringWithoutNumbers(5);
        this.city = RandomGenerator.generateRandomStringWithoutNumbers(5);
        this.mobile_number = String.valueOf(RandomGenerator.generateRandomInteger(10));
        return this;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", title='" + title + '\'' +
                ", birth_date='" + birth_date + '\'' +
                ", birth_month='" + birth_month + '\'' +
                ", birth_year='" + birth_year + '\'' +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", company='" + company + '\'' +
                ", address1='" + address1 + '\'' +
                ", address2='" + address2 + '\'' +
                ", country='" + country + '\'' +
                ", zipcode='" + zipcode + '\'' +
                ", state='" + state + '\'' +
                ", city='" + city + '\'' +
                ", mobile_number='" + mobile_number + '\'' +
                '}';
    }
}
