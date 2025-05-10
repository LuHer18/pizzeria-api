package com.luher.luher_pizzeria.web.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {

    private static String SECRET_KEY = "'Luher_prueb4";

    private static Algorithm algorithm = Algorithm.HMAC256(SECRET_KEY);

    public String create(String userName) {
        return JWT.create()
                .withSubject(userName)
                .withIssuer("LuHer")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(System.currentTimeMillis() + TimeUnit.DAYS.toMillis(15)))
                .sign(algorithm);
    }
}
