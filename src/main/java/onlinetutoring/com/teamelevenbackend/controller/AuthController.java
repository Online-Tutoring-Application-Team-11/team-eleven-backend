package onlinetutoring.com.teamelevenbackend.controller;

import onlinetutoring.com.teamelevenbackend.controller.models.auth.LoginRequest;
import onlinetutoring.com.teamelevenbackend.controller.models.auth.UserSignupRequest;
import onlinetutoring.com.teamelevenbackend.entity.tables.pojos.Users;
import onlinetutoring.com.teamelevenbackend.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;
    @Autowired
    public void setAuthService(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping(value = "/signup")
    public ResponseEntity<Users> signup(@RequestBody UserSignupRequest userSignupRequest) {
        try {
            return authService.signup(userSignupRequest);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/login")
    public ResponseEntity<Users> login(@RequestBody LoginRequest loginRequest) {
        try {
            return authService.login(loginRequest);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}