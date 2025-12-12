package com.example.saleswebsite;

import com.example.saleswebsite.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtUtilSingletonTest {

    @Autowired
    private JwtUtil jwtUtilBean;

    @Test
    public void jwtUtilSingletonInstanceIsSet() {
        JwtUtil instance = JwtUtil.getInstance();
        assertNotNull(instance);
        assertSame(jwtUtilBean, instance);
    }
}
