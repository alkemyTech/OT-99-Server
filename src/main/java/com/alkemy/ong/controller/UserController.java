
package com.alkemy.ong.controller;

import com.alkemy.ong.repository.UserRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping("/a/users")
public class UserController {
    
    @Autowired
    UserRepository userRepository;
    
    @GetMapping
    public List listUsers (){
        return List<String> hola;
        
    }
    
    

}
