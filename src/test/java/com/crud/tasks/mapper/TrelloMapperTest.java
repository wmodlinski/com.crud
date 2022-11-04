package com.crud.tasks.mapper;

import com.crud.tasks.domain.*;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrelloMapperTest {

    TrelloMapper trelloMapper = new TrelloMapper();

    @Test
    void mapToBoards() {

        //Given
        List<TrelloListDto> trelloList1 = List.of(
                new TrelloListDto("1","Test trello list 1", true),
                new TrelloListDto("2","Test trello list 2", false)
        );
        List<TrelloListDto> trelloList2 = List.of(
                new TrelloListDto("3","Test trello list 3", true),
                new TrelloListDto("4","Test trello list 4", false)
        );
        List<TrelloBoardDto> trelloBoardsDto = List.of(
                new TrelloBoardDto("1","First Board",trelloList1),
                new TrelloBoardDto("2","Second board",trelloList2)
        );

        //when
        List<TrelloBoard> mappedBoards = trelloMapper.mapToBoards(trelloBoardsDto);

        //Then
        assertEquals(2, mappedBoards.size());
        assertEquals("First Board", mappedBoards.get(0).getName());
        assertTrue(mappedBoards.get(1).getLists().get(0).isClosed());
    }

    @Test
    void mapToBoardsDto() {
        //Given
        List<TrelloList> trelloList1 = List.of(
                new TrelloList("1","Test trello list 1", true),
                new TrelloList("2","Test trello list 2", false)
        );
        List<TrelloList> trelloList2 = List.of(
                new TrelloList("3","Test trello list 3", true),
                new TrelloList("4","Test trello list 4", false)
        );
        List<TrelloBoard> trelloBoards = List.of(
                new TrelloBoard("1","First Board",trelloList1),
                new TrelloBoard("2","Second board",trelloList2)
        );

        //when
        List<TrelloBoardDto> mappedBoardsDto = trelloMapper.mapToBoardsDto(trelloBoards);

        //Then
        assertEquals(2, mappedBoardsDto.size());
        assertEquals("First Board", mappedBoardsDto.get(0).getName());
        assertTrue(mappedBoardsDto.get(1).getLists().get(0).isClosed());
    }

    @Test
    void mapToList() {
        //Given
        List<TrelloListDto> trelloListDto = List.of(
                new TrelloListDto("1","Test trello list 1", true),
                new TrelloListDto("2","Test trello list 2", false)
        );

        //when
        List<TrelloList> mappedList = trelloMapper.mapToList(trelloListDto);

        assertEquals("Test trello list 1", mappedList.get(0).getName());
        assertFalse(mappedList.get(1).isClosed());
    }

    @Test
    void mapToListDto() {
        //Given
        List<TrelloList> trelloList = List.of(
                new TrelloList("1","Test trello list 1", true),
                new TrelloList("2","Test trello list 2", false)
        );

        //when
        List<TrelloListDto> mappedListDto = trelloMapper.mapToListDto(trelloList);

        assertEquals("Test trello list 1", mappedListDto.get(0).getName());
        assertFalse(mappedListDto.get(1).isClosed());
    }

    @Test
    void mapToCardDto() {
        //Given
        TrelloCard trelloCard = new TrelloCard("Test", "Test card", "1", "T1");

        //when
        TrelloCardDto mappedDto = trelloMapper.mapToCardDto(trelloCard);

        //then
        assertEquals("Test",mappedDto.getName());
        assertEquals("1", mappedDto.getPos());
    }

    @Test
    void mapToCard() {
        //Given
        TrelloCardDto trelloCardDto = new TrelloCardDto("Test", "Test card", "1", "T1");

        //when
        TrelloCard mappedCard = trelloMapper.mapToCard(trelloCardDto);

        //then
        assertEquals("Test",mappedCard.getName());
        assertEquals("1", mappedCard.getPos());
    }
}
