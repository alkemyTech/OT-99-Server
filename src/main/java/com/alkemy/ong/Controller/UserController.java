package com.alkemy.ong.Controller;

import com.alkemy.ong.Service.AppUserService;
import com.alkemy.ong.Service.UserService;
import com.alkemy.ong.filter.JwtResponse;
import com.alkemy.ong.filter.JwtTokenUtil;
import com.alkemy.ong.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author delam
 */
@RestController
@CrossOrigin
@RequestMapping("/auth")
public class UserController {

    @Autowired
    AppUserService appUserService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping
    @RequestMapping("/sign_up")
    public ResponseEntity<?> registerUser(@RequestBody Users appUser) {

        appUserService.createAppUser(appUser);
        return ResponseEntity.ok("User created");
    }

    @PostMapping
    @RequestMapping("/login")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody Users authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authenticate(String mail, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(mail, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
