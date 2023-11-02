package com.lsio.springboot.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lsio.springboot.entities.Game;
import com.lsio.springboot.entities.GameDTO;
import com.lsio.springboot.feign.APIInterface;

@RestController
public class HomeController {
    
    @Autowired
    APIInterface gamesAPI;

    @GetMapping("/user/getgames")
    public ResponseEntity<List<Game>> getGames(){
        return gamesAPI.getGames();
    }

    @PostMapping("/admin/addgame")
    public ResponseEntity<String> SaveGame(@RequestBody Game game){
        return gamesAPI.saveGame(game);
    }

    @GetMapping("/user/getgame/{gameName}")
    public Game getGame(@PathVariable String gameName){
        return gamesAPI.getGame(gameName);
    }

    @GetMapping("/getgame/{id}")
    public ResponseEntity<Game> getGameWithID(@PathVariable Long id){
        return gamesAPI.getGameWithID(id);
    }

    @GetMapping("/user/map")
    @ResponseBody
    public List<GameDTO> gameDTOs(){
        List<GameDTO> dtos = gamesAPI.getAllGamesDTO();
        return dtos;
    }

    

}
