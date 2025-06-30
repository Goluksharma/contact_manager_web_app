package com.smart.smartcontact.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.smart.smartcontact.entity.User;

public class CustomUserDetails implements UserDetails{
    public User user;
      public CustomUserDetails(User user) {
        this.user = user;
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    SimpleGrantedAuthority simpleauthority = new SimpleGrantedAuthority(user.getRole());
        
        
        
        return List.of(simpleauthority);
    }

    @Override
    public String getPassword() {
       
        return user.getPassword();
    }

    @Override
    public String getUsername() {
      
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    
}
