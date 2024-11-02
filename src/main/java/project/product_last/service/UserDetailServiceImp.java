package project.product_last.service;

import project.product_last.model.entities.User;
import project.product_last.repository.UserRepository;

import lombok.AllArgsConstructor;
public class UserDetailServiceImp {
    private UserRepository userRepository;
@AllArgsConstructor
    @Override
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        User user = userRepository.findById(Integer.valueOf(id));
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getId()+"")
                .password(user.getPassword())
                .authorities("USER")
                .build();
    }
}
