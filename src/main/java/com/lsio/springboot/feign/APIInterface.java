package com.lsio.springboot.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.lsio.springboot.entities.Game;
import com.lsio.springboot.entities.GameDTO;

@FeignClient(name="SPRINGBOOTMETIER", configuration = FeignClientConfiguration.class)
public interface APIInterface {
    
    @GetMapping("/user/getgames")
    public ResponseEntity<List<Game>> getGames();

    @PostMapping("/admin/addgame")
    public ResponseEntity<String> saveGame(@RequestBody Game game);

    //to be modified
    @GetMapping("/user/getgame/{gameName}")
    public Game getGame(@PathVariable String gameName);

    @GetMapping("/user/getgame/{id}")
    public ResponseEntity<Game> getGameWithID(@PathVariable Long id);
    
    @GetMapping("/user/map")
    public List<GameDTO> getAllGamesDTO();

}
