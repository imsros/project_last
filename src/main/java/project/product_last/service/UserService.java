package project.product_last.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import project.product_last.model.entities.User;
import project.product_last.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {
    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public void Register(User user){
        try{
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodedPassword);
            userRepository.save(user);
        }catch (Exception e){
            System.out.println("S : "+e.getMessage());
            e.printStackTrace();
        }
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }
}
