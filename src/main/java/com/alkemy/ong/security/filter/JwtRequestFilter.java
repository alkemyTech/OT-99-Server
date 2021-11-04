package com.alkemy.ong.security.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alkemy.ong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import com.alkemy.ong.model.Users;


@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authorizationHeader = request.getHeader("Authorization");

        String email = null;
        String jwt = null;

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            jwt = authorizationHeader.substring(7);

            email = jwtTokenUtil.getUsernameFromToken(jwt);

        }

        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            Users user = userService.findByEmail(email);

            if (jwtTokenUtil.validateToken(jwt, user)) {

                Collection<GrantedAuthority> roles = new ArrayList<>();
                roles.add(new SimpleGrantedAuthority(user.getRole().getName()));

                UsernamePasswordAuthenticationToken usernamePasswordAuthToken
                        = new UsernamePasswordAuthenticationToken(user, null, roles);

                usernamePasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().
                        buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthToken);

            }
        }

        filterChain.doFilter(request, response);

    }

}
