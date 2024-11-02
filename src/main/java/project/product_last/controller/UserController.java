package project.product_last.controller;

import jdk.jfr.Registered;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import project.product_last.model.entities.User;
import project.product_last.model.request.LoginRequest;
import project.product_last.model.request.RegisterRequest;
import project.product_last.model.request.UserRequest;
import project.product_last.model.response.ResponseObject;
import project.product_last.service.JwtService;
import project.product_last.service.UserService;

@RestController
@AllArgsConstructor
@RequestMapping("/api/auth/")
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<ResponseObject<Object>> register(@ModelAttribute UserRequest userRequest){
        try{
            User user = new User();
            user.setName(userRequest.getName());
            user.setEmail(userRequest.getEmail());
            user.setPassword(userRequest.getPassword());
            user.setGender(userRequest.getGender());
            userService.Register(user);
        }catch (Exception e){
            e.printStackTrace();
        }
        ResponseObject<Object> responseObject = ResponseObject.builder().build();
        return new ResponseEntity<>(responseObject, HttpStatusCode.valueOf(200))
    }
    @PostMapping("/login")
    public ResponseEntity<ResponseObject<Object>> login(@ModelAttribute LoginRequest loginRequest){
        User user = userService.findByEmail(loginRequest.getEmail());
        if(user == null && passwordEncoder.matches(loginRequest.getPassword(),user.getPassword())){
            
        }
    }

}
