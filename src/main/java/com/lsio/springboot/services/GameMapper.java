package com.lsio.springboot.services;

import java.util.stream.Collectors;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsio.springboot.entities.Game;
import com.lsio.springboot.entities.GameDTO;
import com.lsio.springboot.repositories.GameRepository;

@Service
public class GameMapper {
    @Autowired
    private GameRepository gameRepo;

    
    public List<GameDTO> getAllGamesDTO(){
        return ((List<Game>)gameRepo
                .findAll())
                .stream()
                .map(this::convertGameIntoDTO)
                    .collect(Collectors.toList());
    }


    private GameDTO convertGameIntoDTO(Game game){
        GameDTO dto = new GameDTO();
        dto.setId(game.getId());
        dto.setName(game.getGamename());
        dto.setEditor(game.getGameeditor());
        return dto;
    }
}
