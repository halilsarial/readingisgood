package tr.com.readingisgood.userservice.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import tr.com.readingisgood.userservice.authconfig.JwtTokenUtil;
import tr.com.readingisgood.userservice.model.domain.User;
import tr.com.readingisgood.userservice.model.dto.userservice.AuthenticationDto;
import tr.com.readingisgood.userservice.model.dto.userservice.UserDto;
import tr.com.readingisgood.userservice.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("/readingisgood/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    private Logger logger = LogManager.getLogger(this.getClass());

    @PostMapping("/auth/sign-up")
    public ResponseEntity<String> signUp(@Valid @RequestBody UserDto userDto) {
        userService.createUser(userService.transformUserFromDto(userDto));
        logger.info("The user created successfully!");
        return ResponseEntity.ok().body("The user created successfully!");
    }

    @GetMapping(value = "/auth/sign-in")
    public ResponseEntity<?> signInWithUserInformation(@Valid @RequestBody AuthenticationDto authenticationDto) throws Exception {
        authenticate(authenticationDto.getUserName(), authenticationDto.getPassword());
        HttpHeaders headers = new HttpHeaders();
        headers.add("jwt-access-token", jwtTokenUtil.generateToken(userDetailsService.loadUserByUsername(authenticationDto.getUserName())));
        User user = userService.getUserByUserName(authenticationDto.getUserName());
        logger.info("The user login!" + user.getName() + " " + user.getSurname());
        return new ResponseEntity<>("Login Successfully! Welcome " + user.getName() + " " + user.getSurname(), headers, HttpStatus.OK);
    }

    @GetMapping(value = "/verify-token")
    public ResponseEntity<?> verifyToken() {
        logger.info("The jwt token excepted");
        return new ResponseEntity<>("You can use this JWT Token succefully!", HttpStatus.ACCEPTED);
    }

    private void authenticate(String username, String password) throws BadCredentialsException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            logger.error("Username or password is wrong!", e);
            throw new BadCredentialsException("Username or password is wrong!", e);
        }
    }
}
