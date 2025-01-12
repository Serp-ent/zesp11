package adventure.go.goadventure.scenario;

import adventure.go.goadventure.scenario.Scenario;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class ScenarioRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public ScenarioRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Metoda findAll - Pobiera wszystkie scenariusze
    public List<Scenario> findAll() {
        String sql = "SELECT * FROM public.\"Scenario\"";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Scenario(
                rs.getInt("id_scen"),
                rs.getString("name"),
                rs.getInt("limit_players"),
                rs.getInt("id_first_step"),
                rs.getInt("id_user")
        ));
    }

    // Metoda findById - Pobiera scenariusz po id_scen
    public Optional<Scenario> findById(Integer id) {
        String sql = "SELECT * FROM public.\"Scenario\" WHERE id_scen = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        List<Scenario> scenarios = jdbcTemplate.query(sql, params, (rs, rowNum) -> new Scenario(
                rs.getInt("id_scen"),
                rs.getString("name"),
                rs.getInt("limit_players"),
                rs.getInt("id_first_step"),
                rs.getInt("id_user")
        ));
        return scenarios.isEmpty() ? Optional.empty() : Optional.of(scenarios.get(0));
    }

    public Optional<Scenario> findByName(String name) {
        String sql = "SELECT * FROM public.\"Scenario\" WHERE name = :name";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", name);

        List<Scenario> scenarios = jdbcTemplate.query(sql, params, (rs, rowNum) -> new Scenario(
                rs.getInt("id_scen"),
                rs.getString("name"),
                rs.getInt("limit_players"),
                rs.getInt("id_first_step"),
                rs.getInt("id_user")
        ));
        return scenarios.isEmpty() ? Optional.empty() : Optional.of(scenarios.get(0));
    }

    // Metoda create - Tworzy nowy scenariusz
    public void create(Scenario scenario) {
        String sql = "INSERT INTO public.\"Scenario\" (id_scen, name, limit_players, id_first_step, id_user) " +
                "VALUES (:id_scen, :name, :limit_players, :id_first_step, :id_user)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id_scen", scenario.getId_scen())
                .addValue("name", scenario.getName())
                .addValue("limit_players", scenario.getLimit_players())
                .addValue("id_first_step", scenario.getId_first_step())
                .addValue("id_user", scenario.getId_user());

        jdbcTemplate.update(sql, params);
    }

    // Metoda update - Aktualizuje scenariusz po id_scen
    public void update(Scenario scenario, Integer id) {
        String sql = "UPDATE public.\"Scenario\" " +
                "SET name = :name, limit_players = :limit_players, id_first_step = :id_first_step, id_user = :id_user " +
                "WHERE id_scen = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", scenario.getName())
                .addValue("limit_players", scenario.getLimit_players())
                .addValue("id_first_step", scenario.getId_first_step())
                .addValue("id_user", scenario.getId_user())
                .addValue("id", id);

        int updated = jdbcTemplate.update(sql, params);
        Assert.state(updated == 1, "Scenario not found");
    }

    // Metoda delete - Usuwa scenariusz po id_scen
    public void delete(Integer id) {
        String sql = "DELETE FROM public.\"Scenario\" WHERE id_scen = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        int deleted = jdbcTemplate.update(sql, params);
        Assert.state(deleted == 1, "Scenario not found");
    }
}
