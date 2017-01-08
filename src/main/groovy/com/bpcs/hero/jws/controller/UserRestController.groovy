package com.bpcs.hero.jws.controller

import com.bpcs.hero.jws.JwtTokenUtil
import com.bpcs.hero.jws.JwtUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import javax.servlet.http.HttpServletRequest

@RestController
public class UserRestController {

    @Value('${jwt.header}')
    private String tokenHeader

    @Value('${jwt.prefix}')
    private String tokenPrefix

    @Autowired
    private JwtTokenUtil jwtTokenUtil

    @Autowired
    private UserDetailsService userDetailsService

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String authToken = request.getHeader(this.tokenHeader)
        if (tokenPrefix && authToken && authToken.startsWith("$tokenPrefix "))
        {
            authToken = authToken.substring(tokenPrefix.length() + 1)
        }
        String username = jwtTokenUtil.getUsernameFromToken(authToken)
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username)
        return user
    }

}
