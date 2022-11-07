package com.crud.tasks.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AttachmentsByTypeTest {

    @Test
    void setAndGetFieldsTest() {

        //Given
        AttachmentsByTypeDto attachments = new AttachmentsByTypeDto();
        TrelloDto trello = new TrelloDto();
        trello.setBoard(0);
        trello.setCard(0);
        //When
        attachments.setTrelloDto(trello);
        TrelloDto gotTrello = attachments.getTrelloDto();
        //Then
        Assertions.assertEquals(0, gotTrello.getBoard());
        Assertions.assertEquals(0, gotTrello.getCard());
    }
}