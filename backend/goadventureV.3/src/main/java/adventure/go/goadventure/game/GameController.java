package adventure.go.goadventure.game;

import adventure.go.goadventure.user.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/game")
public class GameController {

    private final GameRepository gameRepository;
    public GameController(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }
    // Metoda findAll - Zwraca wszystkie gry
    @GetMapping("/all")
    public List<Game> findAll() {
        return gameRepository.findAll();
    }

    // Metoda findById - Zwraca grę po id_game
    @GetMapping("/{id}")
    public Optional<Game> findById(@PathVariable Integer id) {
        return gameRepository.findById(id);
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Game game) {
        gameRepository.create(game);
    }

    // Metoda update - Aktualizuje powiązanie kroków i wyborów po id_step_choice
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Game game, @PathVariable Integer id) {
        gameRepository.update(game, id);
    }

    // Metoda delete - Usuwa powiązanie kroków i wyborów po id_step_choice
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        gameRepository.delete(id);
    }
}


