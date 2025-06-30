package com.smart.smartcontact.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.smart.smartcontact.dao.UserRepository;
import com.smart.smartcontact.entity.User;

@Service
public class UserDetailsServiceimpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
 
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userRepository.getByUserName(username);
        if (user==null) {
            throw new UsernameNotFoundException("Could Not Find UserName");
            
        }
        CustomUserDetails customUserDetails=new CustomUserDetails(user);
    
        return customUserDetails;
    }

    
}
