package com.alkemy.ong.mapper;

import com.alkemy.ong.dto.UsersDto;
import com.alkemy.ong.model.Users;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {

    @Autowired
    ModelMapper modelMapper;

    public UsersDto convertToDto(Users user) {
        UsersDto userDto = modelMapper.map(user, UsersDto.class);
        return userDto;
    }
}
