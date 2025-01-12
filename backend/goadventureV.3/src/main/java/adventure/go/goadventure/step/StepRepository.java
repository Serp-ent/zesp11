package adventure.go.goadventure.step;

import adventure.go.goadventure.step.Step;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class StepRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public StepRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Metoda findAll - Pobiera wszystkie kroki
    public List<Step> findAll() {
        String sql = "SELECT * FROM public.\"Step\"";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Step(
                rs.getInt("id_step"),
                rs.getString("title"),
                rs.getString("text"),
                rs.getInt("longitude"),
                rs.getInt("latitude")
        ));
    }

    // Metoda findById - Pobiera krok po id_step
    public Optional<Step> findById(Integer id) {
        String sql = "SELECT * FROM public.\"Step\" WHERE id_step = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        List<Step> steps = jdbcTemplate.query(sql, params, (rs, rowNum) -> new Step(
                rs.getInt("id_step"),
                rs.getString("title"),
                rs.getString("text"),
                rs.getInt("longitude"),
                rs.getInt("latitude")
        ));
        return steps.isEmpty() ? Optional.empty() : Optional.of(steps.get(0));
    }



    // Metoda create - Tworzy nowy krok
    public void create(Step step) {
        String sql = "INSERT INTO public.\"Step\" (id_step, title, text, longitude, latitude) " +
                "VALUES (:id_step, :title, :text, :longitude, :latitude)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id_step", step.getId_step())
                .addValue("title", step.getTitle())
                .addValue("text", step.getText())
                .addValue("longitude", step.getLongitude())
                .addValue("latitude", step.getLatitude());

        jdbcTemplate.update(sql, params);
    }

    // Metoda update - Aktualizuje krok po id_step
    public void update(Step step, Integer id) {
        String sql = "UPDATE public.\"Step\" " +
                "SET title = :title, text = :text, longitude = :longitude, latitude = :latitude " +
                "WHERE id_step = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("title", step.getTitle())
                .addValue("text", step.getText())
                .addValue("longitude", step.getLongitude())
                .addValue("latitude", step.getLatitude())
                .addValue("id", id);

        int updated = jdbcTemplate.update(sql, params);
        Assert.state(updated == 1, "Step not found");
    }

    // Metoda delete - Usuwa krok po id_step
    public void delete(Integer id) {
        String sql = "DELETE FROM public.\"Step\" WHERE id_step = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        int deleted = jdbcTemplate.update(sql, params);
        Assert.state(deleted == 1, "Step not found");
    }
}
