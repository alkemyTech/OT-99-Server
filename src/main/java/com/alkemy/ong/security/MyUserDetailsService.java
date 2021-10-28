
package com.alkemy.ong.security;

import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.UserRepository;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author delam
 */
@Service
public class MyUserDetailsService implements UserDetailsService{
 @Autowired
 UserRepository userRepository;
 
   
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        if (userRepository.findByUsername(username) == null) {
            throw new UsernameNotFoundException("User does not exist: " + username);
        }
        Users user = userRepository.findByUsername(username);

        return new org.springframework.security.core.userdetails.User(
                
                user.getEmail(),
                user.getPassword(),
                true,
                true,
                true,
                true,
                Arrays.asList(new SimpleGrantedAuthority("USER")));

    }
 
}
