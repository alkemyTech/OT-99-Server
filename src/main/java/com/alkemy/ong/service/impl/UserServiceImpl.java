package com.alkemy.ong.service.impl;

import java.io.IOException;
import java.util.Date;
import com.alkemy.ong.dto.JwtTokenDto;
import com.alkemy.ong.dto.UserLoginRequest;
import com.alkemy.ong.dto.UserRegisterRequest;
import com.alkemy.ong.dto.UserRegisterResponse;
import com.alkemy.ong.exception.EmailAlreadyExistException;
import com.alkemy.ong.exception.InvalidCredentialsException;
import com.alkemy.ong.exception.NotFoundException;
import com.alkemy.ong.model.Role;
import com.alkemy.ong.model.Users;
import com.alkemy.ong.repository.UserRepository;
import com.alkemy.ong.security.filter.JwtTokenUtil;
import com.alkemy.ong.service.EmailService;
import com.alkemy.ong.service.RoleService;
import com.alkemy.ong.service.UserService;
import java.util.List;

import javax.transaction.Transactional;

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
    EmailService emailService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public Users findByEmail(String email) {
        Users user=userRepo.findByEmail(email);
        
        if(user!=null) user.getRole().getName();
        
        return user;
    }

    @Override
    public void delete(Long id) throws NotFoundException {
        if(userRepo.existsById(id)){
            userRepo.deleteById(id);
        }else{
            throw new NotFoundException("The user is not registered.");
        }
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest userReq) throws EmailAlreadyExistException, IOException {
        if (this.findByEmail(userReq.getEmail()) != null) {
            throw new EmailAlreadyExistException();
        }

        Users user = UserRegisterRequest.mapToEntity(userReq);
        Role role = new Role();
        role = roleService.findByName("USER");
        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setCreationDate(new Date());

        emailService.sendWelcomeEmail(userReq);

        return UserRegisterResponse.mapToResponse(userRepo.save(user));
    }

    @Override
    public JwtTokenDto authenticate(UserLoginRequest userReq) throws NotFoundException, InvalidCredentialsException {
        Users user = findByEmail(userReq.getEmail());
        UserDetails userDetails;
        if(user == null){
            throw new NotFoundException("The user is not registered.");
        }
        else if(!passwordEncoder.matches(userReq.getPassword(),user.getPassword())){
            throw new InvalidCredentialsException("The data entered are invalid.");
        }
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(),userReq.getPassword())
        );
        userDetails = (UserDetails) auth.getPrincipal();
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return new JwtTokenDto(jwt);
    }

    @Override
    public List<Users> getAllUsers() {
        return userRepo.findAll();
    }

    @Override
    public UserRegisterResponse upgradeUser(Long id, UserRegisterRequest userJpa) throws NotFoundException {
        Users userBd = userRepo.findById(id).orElseThrow( ()-> new NotFoundException("The user is not registered.") );
        userBd.setLastUpdate(new Date());
        userBd.setPassword(userJpa.getPassword());
        userBd.setFirstName(userJpa.getFirstName());
        userBd.setLastName(userJpa.getLastName());
        userBd.setPhoto(userJpa.getPhoto());
        userBd.setEmail(userJpa.getEmail());
        userBd.setPassword(passwordEncoder.encode(userBd.getPassword()));
        return UserRegisterResponse.mapToResponse(userRepo.save(userBd));
    }

}
