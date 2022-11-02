package com.crud.tasks.trello.client;

import com.crud.tasks.trello.domain.CreatedTrelloCard;
import com.crud.tasks.trello.domain.TrelloBoardDto;
import com.crud.tasks.trello.domain.TrelloCardDto;
import com.crud.tasks.trello.domain.TrelloListDto;
import com.crud.tasks.trello.config.TrelloConfig;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TrelloClient {

    private final RestTemplate restTemplate;
    private final TrelloConfig trelloConfig;

    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloClient.class);

    public List<TrelloBoardDto> getTrelloBoardsWithLists(){
        try{
            List<TrelloBoardDto> trelloBoards = getTrelloBoards();

            trelloBoards.forEach(trelloBoardDto -> {
                String boardId = trelloBoardDto.getId();
                trelloBoardDto.setLists(getListsFromBoards(boardId));
            });
            return  trelloBoards;

        }catch (RestClientException e){
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    public List<TrelloBoardDto> getTrelloBoards() {

        URI url = generateGetBoardsRequestPath();
        TrelloBoardDto[] boardsResponse = restTemplate.getForObject(url, TrelloBoardDto[].class);
        return Optional.ofNullable(boardsResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }
    public List<TrelloListDto> getListsFromBoards(String boardId){
        URI url = generateGetListsRequestPatch(boardId);
        TrelloListDto[] listsResponse = restTemplate.getForObject(url, TrelloListDto[].class);
        return Optional.ofNullable(listsResponse)
                .map(Arrays::asList)
                .orElse(Collections.emptyList());
    }

    public CreatedTrelloCard createNewCard(TrelloCardDto trelloCardDto){

        URI url = UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/cards")
                .queryParam("key", trelloConfig.getTrelloAppKey() )
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("name", trelloCardDto.getName())
                .queryParam("desc", trelloCardDto.getDescription())
                .queryParam("pos", trelloCardDto.getPos())
                .queryParam("idList", trelloCardDto.getListId())
                .build()
                .encode()
                .toUri();
        return restTemplate.postForObject(url,null,CreatedTrelloCard.class);
    }

    private URI generateGetBoardsRequestPath(){

        return UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/members/" + trelloConfig.getUsername() + "/boards")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .queryParam("fields", "name,id")
                .build()
                .encode()
                .toUri();
    }

    private URI generateGetListsRequestPatch(String boardId){

        return UriComponentsBuilder.fromHttpUrl(trelloConfig.getTrelloApiEndpoint() + "/boards/" + boardId + "/lists")
                .queryParam("key", trelloConfig.getTrelloAppKey())
                .queryParam("token", trelloConfig.getTrelloToken())
                .build()
                .encode()
                .toUri();
    }
}