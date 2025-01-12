package adventure.go.goadventure.game;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Optional;

@Repository
public class GameRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public GameRepository(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // Metoda findAll - Zwraca wszystkie gry
    public List<Game> findAll() {
        String sql = "SELECT * FROM public.\"Game\"";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Game(
                rs.getInt("id_game"),
                rs.getInt("id_scen"),
                rs.getDate("date")  // Konwersja z Date na LocalDate
        ));
    }

    // Metoda findById - Zwraca grę na podstawie id_game
    public Optional<Game> findById(Integer id) {
        String sql = "SELECT * FROM public.\"Game\" WHERE id_game = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        List<Game> games = jdbcTemplate.query(sql, params, (rs, rowNum) -> new Game(
                rs.getInt("id_game"),
                rs.getInt("id_scen"),
                rs.getDate("date")  // Konwersja z Date na LocalDate
        ));
        return games.isEmpty() ? Optional.empty() : Optional.of(games.get(0));
    }



    // Metoda create - Tworzy nową grę
    public void create(Game game) {
        String sql = "INSERT INTO public.\"Game\" (id_game, id_scen, date) " +
                "VALUES (:id_game, :id_scen, :date)";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id_game", game.getId_game())
                .addValue("id_scen", game.getId_scen())
                .addValue("date", game.getDate());

        jdbcTemplate.update(sql, params);
    }

    // Metoda update - Aktualizuje grę na podstawie id_game
    public void update(Game game, Integer id) {
        String sql = "UPDATE public.\"Game\" " +
                "SET id_scen = :id_scen, date = :date " +
                "WHERE id_game = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id_scen", game.getId_scen())
                .addValue("date", game.getDate())
                .addValue("id", id);

        int updated = jdbcTemplate.update(sql, params);
        Assert.state(updated == 1, "Game not found with id: " + id);
    }

    // Metoda delete - Usuwa grę po id_game
    public void delete(Integer id) {
        String sql = "DELETE FROM public.\"Game\" WHERE id_game = :id";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("id", id);

        int deleted = jdbcTemplate.update(sql, params);
        Assert.state(deleted == 1, "Game not found with id: " + id);
    }
}