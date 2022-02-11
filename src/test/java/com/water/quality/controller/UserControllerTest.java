package com.water.quality.controller;


import com.water.quality.r.R;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
class UserControllerTest {

    @Autowired
    private UserController userController;

    @Test
    void searchUser() {
        R admin = userController.searchUser("admin");

        log.info("admin = " + admin);
    }
}
