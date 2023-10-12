package com.lsio.springboot.entities;
import java.sql.Date;

//import jakarta.persistence.Column;
//import java.util.Objects;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "games")
@Data
public class Game {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String gamename;

    private String gameeditor;
    
    private Date reldate;


}
