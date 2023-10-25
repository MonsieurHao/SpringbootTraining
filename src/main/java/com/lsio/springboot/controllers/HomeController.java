package com.lsio.springboot.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.lsio.springboot.entities.Game;
import com.lsio.springboot.entities.GameDTO;
import com.lsio.springboot.services.GameMapper;
import com.lsio.springboot.services.GamesService;

@RestController
public class HomeController {
    
    @Autowired 
    GamesService gamesService;

    @GetMapping("/admin")
    public String sayHello(){
        return "Hello Admin";
    }

    @GetMapping("/user")
    public String sayHelloU(){
        return "Hello User";
    }

    @GetMapping("/user/getgames")
    public ResponseEntity<List<Game>> getGames(){
        return gamesService.getGames();
    }

    @PostMapping("/admin/addgame")
    public ResponseEntity<String> SaveGame(@RequestBody Game game){
        return gamesService.saveGame(game);
    }

    @GetMapping("/user/getgame/{gameName}")
    public Game getGame(@PathVariable String gameName){
        return gamesService.getGame(gameName);
    }

    @GetMapping("/getgame/{id}")
    public ResponseEntity<Game> getGameWithID(@PathVariable Long id){
        return gamesService.getGameWithID(id);
    }

    @Autowired
    private GameMapper mapper;

    @GetMapping("/user/map")
    @ResponseBody
    public List<GameDTO> gameDTOs(){
        List<GameDTO> dtos = mapper.getAllGamesDTO();

        return dtos;
    }

    

}
