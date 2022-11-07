package com.crud.tasks.scheduler;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailSchedulerTest {

    @Autowired
    EmailScheduler emailScheduler;

    @Test
    void sendInformationEmail() {
        //when then
        assertDoesNotThrow(()-> emailScheduler.sendInformationEmail());
    }
}