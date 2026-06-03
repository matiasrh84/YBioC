package com.ybc.ybioq.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class HealthApiController {

    private static final String VERSION = "1.0.0";

    @GetMapping("/health")
    public Map<String, Object> health() {
        return Map.of(
                "status",          "ok",
                "version",         VERSION,
                "licenciaValida",  true
        );
    }
}
