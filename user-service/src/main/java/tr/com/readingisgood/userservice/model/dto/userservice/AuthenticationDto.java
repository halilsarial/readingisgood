package tr.com.readingisgood.userservice.model.dto.userservice;

import io.swagger.v3.oas.annotations.media.Schema;
import tr.com.readingisgood.userservice.model.dto.AbstractServiceDto;

import javax.validation.constraints.NotBlank;

@Schema(name = "Authantication user model documentation", description = "Signing in is done with this object")
public class AuthenticationDto extends AbstractServiceDto {

    @Schema(name = "Username field of authantication object.")
    @NotBlank(message = "UserName cannot be empty!")
    private String userName;

    @Schema(name = "Password field of authantication object.")
    @NotBlank(message = "Password cannot be empty!")
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
