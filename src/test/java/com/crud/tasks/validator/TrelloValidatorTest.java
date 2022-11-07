package com.crud.tasks.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.trello.validator.TrelloValidator;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloValidatorTest {


    TrelloValidator validator = new TrelloValidator();

    @Test
    void validateCard() {
        //Given
        TrelloCard card = new TrelloCard("test", "dsc", "1","1");

        //when & then
        assertFalse(validator.validateCard(card));
    }

    @Test
    void validateTrelloBoards() {
        //given
        List<TrelloList> trelloLists =
                List.of(new TrelloList("1", "test_list", false));

        List<TrelloBoard> trelloBoards =
                List.of(
                        new TrelloBoard("1", "test", trelloLists),
                        new TrelloBoard("1", "normal board", trelloLists)
                );

        //when
        List<TrelloBoard> validatedList = validator.validateTrelloBoards(trelloBoards);

        //then
        assertEquals(1, validatedList.size());

    }
}