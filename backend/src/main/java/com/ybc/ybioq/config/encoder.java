package com.ybc.ybioq.config;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 *
 * @author MatiasRuizHolgado
 */
public class encoder {
    
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String claveEncriptada = encoder.encode("admin");
        System.out.println(claveEncriptada);
    }
    
}
