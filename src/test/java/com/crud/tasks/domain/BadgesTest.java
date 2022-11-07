package com.crud.tasks.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BadgesTest {

    @Test
    void getAndSetFieldsTest() {

        //Given
        BadgesDto badges = new BadgesDto();
        //When
        badges.setVotes(10);
        AttachmentsByTypeDto attachments = new AttachmentsByTypeDto();
        badges.setAttachmentsByTypeDto(attachments);
        int gotVotes = badges.getVotes();
        AttachmentsByTypeDto gotAttachmentsByType = badges.getAttachmentsByTypeDto();
        //Then
        Assertions.assertEquals(10, badges.getVotes());
        Assertions.assertEquals(attachments, gotAttachmentsByType);
    }
}