package adventure.go.goadventure.session;
import java.sql.Date;
public class Session {
    private Integer id_ses;
    private Integer id_user;
    private Integer id_game;
    private Integer last_step;
    private Date start_date;
    private Date end_date;
    public Session() {
    }
    public Session(Integer id_ses, Integer id_user, Integer id_game, Integer last_step, Date start_date, Date end_date) {
        this.id_ses = id_ses;
        this.id_user = id_user;
        this.id_game = id_game;
        this.last_step = last_step;
        this.start_date = start_date;
        this.end_date = end_date;
    }
    // Gettery i Settery

    public Integer getId_ses() {
        return id_ses;
    }

    public void setId_ses(Integer id_ses) {
        this.id_ses = id_ses;
    }

    public Integer getId_user() {
        return id_user;
    }

    public void setId_user(Integer id_user) {
        this.id_user = id_user;
    }

    public Integer getId_game() {
        return id_game;
    }

    public void setId_game(Integer id_game) {
        this.id_game = id_game;
    }

    public Integer getLast_step() {
        return last_step;
    }

    public void setLast_step(Integer last_step) {
        this.last_step = last_step;
    }

    public Date getStart_date() {
        return start_date;
    }

    public void setStart_date(Date start_date) {
        this.start_date = start_date;
    }

    public Date getEnd_date() {
        return end_date;
    }

    public void setEnd_date(Date end_date) {
        this.end_date = end_date;
    }
}
