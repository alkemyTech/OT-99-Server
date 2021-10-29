package com.alkemy.ong.filter;

import com.alkemy.ong.repository.UserRepository;
import java.io.Serializable;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author delam
 */
@Data
public class JwtResponse implements Serializable {


    private final String jwttoken;

}
