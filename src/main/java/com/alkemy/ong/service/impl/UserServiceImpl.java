package com.alkemy.ong.service.impl;

import java.util.Date;

import com.alkemy.ong.dto.JwtTokenDto;
import com.alkemy.ong.dto.UserLoginRequest;
import com.alkemy.ong.dto.UserRegisterRequest;
import com.alkemy.ong.dto.UserRegisterResponse;
import com.alkemy.ong.exception.EmailAlreadyExistException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.security.filter.JwtTokenUtil;
import com.alkemy.ong.service.RoleService;
import com.alkemy.ong.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleService roleService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Users findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest userReq) throws EmailAlreadyExistException {

        if (this.findByEmail(userReq.getEmail()) != null) {
            throw new EmailAlreadyExistException();
        }

        Users user = UserRegisterRequest.mapToEntity(userReq);
        Role role = new Role();
        role = roleService.findByName("USER");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreationDate(new Date());

        return UserRegisterResponse.mapToResponse(userRepo.save(user));
    }

    @Override
    public JwtTokenDto authenticate(UserLoginRequest userReq) throws NotFoundException {
        Users user = findByEmail(userReq.getEmail());
        UserDetails userDetails;
        if(user == null){
            throw new NotFoundException("The user is not registered.");
        }
        else if(!passwordEncoder.matches(userReq.getPassword(),user.getPassword())){
            throw new NotFoundException("The data entered are invalid.");
        }
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(),userReq.getPassword())
        );
        userDetails = (UserDetails) auth.getPrincipal();
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return new JwtTokenDto(jwt);
    }

}
