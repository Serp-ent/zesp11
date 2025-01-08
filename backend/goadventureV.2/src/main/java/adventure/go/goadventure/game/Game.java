package adventure.go.goadventure.game;

import java.sql.Date;

public class Game {
    private Integer id_game;
    private Integer id_scen;
    private Date date;
    public Game() {
    }
    public Game(Integer id_game, Integer id_scen, Date date) {
        this.id_game = id_game;
        this.id_scen = id_scen;
        this.date = date;
    }
    // Gettery i Settery

    public Integer getId_game() {
        return id_game;
    }

    public void setId_game(Integer id_game) {
        this.id_game = id_game;
    }

    public Integer getId_scen() {
        return id_scen;
    }

    public void setId_scen(Integer id_scen) {
        this.id_scen = id_scen;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
