package com.lsio.springboot.services;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lsio.springboot.entities.Game;
import com.lsio.springboot.repositories.GameRepository;

@Service
public class GamesService {
    
    @Autowired
    GameRepository gameRepository;
    public GamesService(){

    }

    public ResponseEntity<List<Game>> getGames(){
        try{
            return new ResponseEntity<List<Game>>(gameRepository.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<List<Game>>(new ArrayList<>(), HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<String> saveGame(Game game){
        gameRepository.save(game);
        return new ResponseEntity<>("success", HttpStatus.CREATED);
    }

    public Game getGame(String gameName) {
        return gameRepository.findByGamename(gameName);
    }

    public ResponseEntity<Game> getGameWithID(Long id) {
        
        return new ResponseEntity<Game>(HttpStatus.OK);
    }
}
