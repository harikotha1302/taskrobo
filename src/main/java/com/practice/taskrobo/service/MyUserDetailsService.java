package com.practice.taskrobo.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {
	
	@Autowired
	BCryptPasswordEncoder passwordEncrypter;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User("harikrishna", passwordEncrypter.encode("admin123"),getAuthorities(Collections.singletonList("ADMIN")));
	}
	
	private Collection<? extends GrantedAuthority> getAuthorities(
		      List<String> roles) {
		  
		        return getGrantedAuthorities(roles);
		    }
	
	private List<GrantedAuthority> getGrantedAuthorities(List<String> privileges) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String privilege : privileges) {
            authorities.add(new SimpleGrantedAuthority(privilege));
        }
        return authorities;
    }

}
