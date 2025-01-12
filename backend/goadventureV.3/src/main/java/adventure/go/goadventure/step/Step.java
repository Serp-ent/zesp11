package adventure.go.goadventure.step;

public class Step {
    private Integer id_step;
    private String title;
    private String text;
    private Integer longitude;
    private Integer latitude;

    public Step() {
    }

    public Step(Integer id_step, String title, String text, Integer longitude, Integer latitude) {
        this.id_step = id_step;
        this.title = title;
        this.text = text;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    // Gettery i Settery

    public Integer getId_step() {
        return id_step;
    }

    public void setId_step(Integer id_step) {
        this.id_step = id_step;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getLongitude() {
        return longitude;
    }

    public void setLongitude(Integer longitude) {
        this.longitude = longitude;
    }

    public Integer getLatitude() {
        return latitude;
    }

    public void setLatitude(Integer latitude) {
        this.latitude = latitude;
    }
}
