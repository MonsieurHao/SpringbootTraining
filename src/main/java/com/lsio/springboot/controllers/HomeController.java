package com.lsio.springboot.controllers;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
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
    @PreAuthorize("hasRole('ADMIN')")
    //@RolesAllowed({"ADMIN"})   + add -> (jsr250Enabled = true) with @EnableMethodSecurity in SecuConfig
    public String sayHello(){
        return "Hello Admin";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String sayHelloU(){
        return "Hello User";
    }

    @GetMapping("/getgames")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Game> getGames(){
        return gamesService.getGames();
    }

    @PostMapping("/addgame")
    @PreAuthorize("hasRole('ADMIN')")
    public Game SaveGame(@RequestBody Game game){
        return gamesService.saveGame(game);
    }

    @GetMapping("/getgame")
    public Game getGame(String gameName){
        return gamesService.getGame(gameName);
    }

    @Autowired
    private GameMapper mapper;

    @GetMapping("/map")
    @ResponseBody
    public List<GameDTO> gameDTOs(){
        List<GameDTO> dtos = mapper.getAllGamesDTO();

        return dtos;
    }
}
