package com.nic.aishe.master.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JwtTokenAuthenticationFilter extends OncePerRequestFilter {

    private final JwtConfig jwtConfig;
    private final RestTemplate restTemplate;
    private final Environment environment;
    public static final String POLICY = "default-src 'self'";

    public JwtTokenAuthenticationFilter(JwtConfig jwtConfig, RestTemplate restTemplate, Environment environment) {
        this.jwtConfig = jwtConfig;
        this.restTemplate = restTemplate;
        this.environment = environment;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
//        response = new HttpServletResponseWrapper(response) {
//            @Override
//            public void setHeader(String name, String value) {
//                if (!"X-Forwarded-Host".equalsIgnoreCase(name) && !"X-Host-Forwarded-Server".equalsIgnoreCase(name)) {
//                    super.setHeader(name, value);
//                }
//            }
//        };
//        if (request.getMethod().equals("OPTIONS")) {
//            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
//        }
//        if (response instanceof HttpServletResponse) {
//            (response).setHeader("Content-Security-Policy", JwtTokenAuthenticationFilter.POLICY);
//            response.setHeader("Strict-Transport-Security", "max-age=31536000 ; includeSubDomains; preload");
//            response.setHeader("X-Content-Type-Options", "nosniff");
//            response.setHeader("X-Frame-Options", "SAMEORIGIN");
//            response.setHeader("X-XSS-Protection", "1; mode=block");
//            (response).setHeader("Referrer-Policy", "strict-origin-when-cross-origin");
//            (response).setHeader("Permissions-Policy", "microphone 'none'; geolocation 'none'; camera 'none'");
//            (response).setHeader("Server", "myServer");
//            (response).setHeader("unset", "myServer");
//            (response).addHeader("Set-Cookie (.*)", "$1; SameSite=strict");
//            (response).addHeader(" Set-Cookie (.*)", "$1; HttpOnly");
//            (response).addHeader("Allow", "");
//            //(response).addHeader("Access-Control-Allow-Headers", "");
//        }

// 1. get the authentication header. Tokens are supposed to be passed in the authentication header
        String header = request.getHeader(jwtConfig.getHeader());

        if (header == null || !header.startsWith(jwtConfig.getPrefix())) {
            chain.doFilter(request, response);        // If not valid, go to the next filter.
            return;
        }

        // If there is no token provided and hence the user won't be authenticated.
        // It's Ok. Maybe the user accessing a public path or asking for a token.

        // All secured paths that needs a token are already defined and secured in config class.
        // And If user tried to access without access token, then he won't be authenticated and an exception will be thrown.

        // 3. Get the token
        String token = header.replace(jwtConfig.getPrefix(), "");

        try {    // exceptions might be thrown in creating the claims if for example the token is expired

            // 4. Validate the token
            Claims claims = Jwts.parser()
                    .setSigningKey(jwtConfig.getSecret().getBytes())
                    .parseClaimsJws(token)
                    .getBody();

            String username = claims.getSubject();
            System.out.println(6);
            System.out.println(username);
            if (username != null) {
                List<String> l = new ArrayList<>();
                l.add("msheservice");
                l.add("aisheCollegePostApi");
                l.add("collegeServiceApi");
                l.add("users");
                l.add("user");
                l.add("auth");
                l.add("aishemasterservice");
                l.add("aishehibernategetapi");
                l.add("aishehibernatepostapi");
                l.add("aishejpagetapi");
                l.add("aishejpapostapi");
                l.add("aisheinstitutemanagement");
                l.add("aishepdf");
                l.add("usermanagement");
                l.add("vacancyTracker");
                l.add("pmusha");
                boolean anyMatch = l.stream().anyMatch(s -> request.getRequestURI().contains(s));
                if (!anyMatch) {
//					;
                    Boolean hasacess = restTemplate.getForObject(environment.getProperty("accesscheck.url") + username + "?url=" + request.getRequestURI(), Boolean.class);
                    System.out.println(hasacess);
                    if (!hasacess) {
                        throw new Exception();
                    }
                }
                l.add("/msheservice/**");
                l.add("/user/**");
                l.add("/aisheCollegePostApi/**");
                l.add("/collegeServiceApi/**");
                l.add("/users/**");
                l.add("/user/**");
                l.add("/auth/**");
                l.add("/aishemasterservice/**");
                l.add("/aishehibernategetapi/**");
                l.add("/aishehibernatepostapi/**");
                l.add("/aishejpagetapi/**");
                l.add("/aishejpapostapi/**");
                l.add("/aisheinstitutemanagement/**");
                l.add("/aishepdf/**");
                l.add("/usermanagement/**");
                l.add("/vacancyTracker/**");
                l.add("/pmusha/**");
                @SuppressWarnings("unchecked")
                List<String> authorities = (List<String>) claims.get("authorities");
                System.out.println(authorities);


                // 5. Create auth object
                // UsernamePasswordAuthenticationToken: A built-in object, used by spring to represent the current authenticated / being authenticated user.
                // It needs a list of authorities, which has type of GrantedAuthority interface, where SimpleGrantedAuthority is an implementation of that interface
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                        username, null, authorities.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList()));


                // 6. Authenticate the user
                // Now, user is authenticated
                SecurityContextHolder.getContext().setAuthentication(auth);
            }

        } catch (Exception e) {
            // In case of failure. Make sure it's clear; so guarantee user won't be authenticated
            SecurityContextHolder.clearContext();
        }

        // go to the next filter in the filter chain
        chain.doFilter(request, response);

    }
}
