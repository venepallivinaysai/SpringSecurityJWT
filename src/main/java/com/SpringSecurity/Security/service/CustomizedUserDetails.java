package com.SpringSecurity.Security.service;

import com.SpringSecurity.Security.entity.User;
import com.SpringSecurity.Security.model.UserDetailsModel;
import com.SpringSecurity.Security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomizedUserDetails implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.findByUsername(username);

        if(user == null){
            System.out.println("User Not Found");
            throw new UsernameNotFoundException("User Not Found");
        }

        return new UserDetailsModel(user);
    }
}
