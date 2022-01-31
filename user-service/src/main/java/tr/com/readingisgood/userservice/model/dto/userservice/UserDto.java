package tr.com.readingisgood.userservice.model.dto.userservice;

import io.swagger.v3.oas.annotations.media.Schema;
import tr.com.readingisgood.userservice.model.dto.AbstractServiceDto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Schema(name = "API user model documentation", description = "Signing up is done with this object")
public class UserDto extends AbstractServiceDto {

    @Schema(name = "Name field of user object")
    private String name;

    @Schema(name = "Surname field of user object")
    private String surname;

    @Schema(name = "Unique username field of user object")
    @NotBlank(message = "UserName cannot be empty!")
    private String userName;

    @Schema(name = "Unique email field of user object")
    @Email(message = "Email must conform to the format!")
    private String email;

    @Schema(name = "Password field of user object")
    @NotBlank(message = "Password cannot be empty!")
    private String password;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
