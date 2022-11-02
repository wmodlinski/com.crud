package com.crud.tasks.trello.controller;

import com.crud.tasks.trello.domain.CreatedTrelloCard;
import com.crud.tasks.trello.domain.TrelloBoardDto;
import com.crud.tasks.trello.domain.TrelloCardDto;
import com.crud.tasks.trello.client.TrelloClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/trello")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TrelloController {
    private final TrelloClient trelloClient;

    @GetMapping("boards")
    public ResponseEntity<List<TrelloBoardDto>> getTrelloBoards() {

        return ResponseEntity.ok(trelloClient.getTrelloBoardsWithLists());

    }

    @PostMapping("cards")
    public ResponseEntity<CreatedTrelloCard> createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {

        return ResponseEntity.ok(trelloClient.createNewCard(trelloCardDto));

    }

}