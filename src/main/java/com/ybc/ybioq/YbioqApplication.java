package com.ybc.ybioq;

import com.ybc.ybioq.fx.YbioqFxApplication;
import javafx.application.Application;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class YbioqApplication {

    public static void main(String[] args) {
        Application.launch(YbioqFxApplication.class, args);
    }
}
