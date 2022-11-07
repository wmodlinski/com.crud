package com.crud.tasks.controller;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.trello.facade.TrelloFacade;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TrelloControllerTest {

    @Test
    void shouldFetchEmptyTrelloBoards() throws Exception{
        //given
        TrelloFacade trelloFacade = mock(TrelloFacade.class);
        when(trelloFacade.fetchTrelloBoards()).thenReturn(List.of());
        TrelloController trelloController = new TrelloController(trelloFacade);

        //when
        ResponseEntity<List<TrelloBoardDto>> response = trelloController.getTrelloBoards();

        //then
        assertEquals(200, response.getStatusCodeValue());
        assertEquals(List.of(), response.getBody());

    }
}