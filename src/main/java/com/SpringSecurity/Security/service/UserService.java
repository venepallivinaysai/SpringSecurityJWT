package com.SpringSecurity.Security.service;

import com.SpringSecurity.Security.entity.User;
import com.SpringSecurity.Security.exception.CustomizedException;
import com.SpringSecurity.Security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private BCryptPasswordEncoder bCryptPasswordEncoder= new BCryptPasswordEncoder(12);

    public User registerUser(User user){
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
       return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public String deleteUser(Integer id) throws CustomizedException {
            Optional<User> user = userRepository.findById(id);
            if (user.isEmpty()) {
                throw new CustomizedException("User Not Found!!");
            }
            else {
                userRepository.delete(user.get());
                return "User deleted Successfully!";
            }
    }

    public String verify(User user) throws CustomizedException {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if(authentication.isAuthenticated()){
            return jwtService.generateToken(user.getUsername());
        }
        else throw new CustomizedException("Verification Failed");

    }
}
