package com.lsio.springboot.entities;
import java.sql.Date;

import jakarta.persistence.Column;
//import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "games")
@Getter @Setter @NoArgsConstructor
public class Game {
    
    @Id
    @Column(name="game_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="game_name")
    private String gameName;

    @Column(name="editor")
    private String gameEditor;
    
    @Column(name="release_date")
    private Date relDate;


}
