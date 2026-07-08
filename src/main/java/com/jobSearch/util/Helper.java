package com.jobSearch.util;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;
import java.util.Base64;

public class Helper {
    public static void main(String[] args) {
        SecretKey key = Jwts.SIG.HS256.key().build();
        String secret  = Base64.getEncoder().encodeToString(key.getEncoded());
        System.out.println(secret);
    }
}
