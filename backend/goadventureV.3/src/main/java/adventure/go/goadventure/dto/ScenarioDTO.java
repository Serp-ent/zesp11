package adventure.go.goadventure.dto;

public class ScenarioDTO {
    private Integer id_scen;
    private String name;
    private Integer limit_players;
    private Integer id_first_step;
    private Integer id_user;

    public ScenarioDTO() {
    }

    public ScenarioDTO(Integer id_scen, String name, Integer limit_players, Integer id_first_step, Integer id_user) {
        this.id_scen = id_scen;
        this.name = name;
        this.limit_players = limit_players;
        this.id_first_step = id_first_step;
        this.id_user = id_user;
    }

    public Integer getId_scen() {
        return id_scen;
    }

    public void setId_scen(Integer id_scen) {
        this.id_scen = id_scen;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLimit_players() {
        return limit_players;
    }

    public void setLimit_players(Integer limit_players) {
        this.limit_players = limit_players;
    }

    public Integer getId_first_step() {
        return id_first_step;
    }

    public void setId_first_step(Integer id_first_step) {
        this.id_first_step = id_first_step;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }
}
