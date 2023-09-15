package com.lsio.springboot.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lsio.springboot.entities.Game;
import com.lsio.springboot.repositories.GameRepository;

@Service
public class GamesService {
    
    @Autowired
    GameRepository gameRepository;

    public GamesService(){

    }

    public List<Game> getGames(){
        return gameRepository.findAll();
    }

    public Game saveGame(Game game){

        return gameRepository.save(game);
    }

    public Game getGame(String gameName) {
        return gameRepository.findByGameName(gameName);
    }
}
