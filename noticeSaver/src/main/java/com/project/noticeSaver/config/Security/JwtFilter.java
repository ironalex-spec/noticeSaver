package com.project.noticeSaver.config.Security;


import com.project.noticeSaver.service.CustomUserDetails;
import com.project.noticeSaver.service.CustomUserDetailsService;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

import static io.jsonwebtoken.lang.Strings.hasText;

@Component
@Log
public class JwtFilter extends GenericFilterBean {

    public static final String AUTHORIZATION = "Authorization";
    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        String tokenValidation = null;
        String tokenFromCoockie = null;
        String tokenFromHTTPHeader = getTokenFromRequest((HttpServletRequest) servletRequest);


        Cookie coockie = getCookie((HttpServletRequest) servletRequest, "token");
        if (coockie != null) {
            tokenFromCoockie = coockie.getValue();
        }


        System.out.println("token from header " + tokenFromHTTPHeader);
        System.out.println(tokenFromCoockie);


        if (tokenFromHTTPHeader == null){
            tokenValidation = tokenFromCoockie;
        } else {
            tokenValidation = tokenFromHTTPHeader;
        }


        if (tokenValidation != null && jwtProvider.validateToken(tokenValidation)) {
            String userLogin = jwtProvider.getLoginFromToken(tokenValidation);
            CustomUserDetails customUserDetails = customUserDetailsService.loadUserByUsername(userLogin);
            UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(customUserDetails, null, customUserDetails.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(auth);
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader(AUTHORIZATION);
        if (hasText(bearer) && bearer.startsWith("Bearer ")) {
            return bearer.substring(7);
        }
        return null;
    }

    public static Cookie getCookie(HttpServletRequest req, String name) {
        Cookie[] cookies = req.getCookies();
        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                if(cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}

