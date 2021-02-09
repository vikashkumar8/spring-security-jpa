package io.javabrains.springsecurityjpa;

import java.util.Arrays;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import io.javabrains.springsecurityjpa.models.User;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        if(user==null)
        {
        throw new UsernameNotFoundException(userName);
        }

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(), true, true, true, true,Arrays.asList(new SimpleGrantedAuthority("user")));
    }
}
