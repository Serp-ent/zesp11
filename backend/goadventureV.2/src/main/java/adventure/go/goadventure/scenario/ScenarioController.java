package adventure.go.goadventure.scenario;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/scenario")
public class ScenarioController {

    private final ScenarioRepository scenarioRepository;

    public ScenarioController(ScenarioRepository scenarioRepository) {
        this.scenarioRepository = scenarioRepository;
    }

    // Metoda findAll - Zwraca wszystkie scenariusze
    @GetMapping("/all")
    public List<Scenario> findAll() {
        return scenarioRepository.findAll();
    }

    // Metoda findById - Zwraca scenariusz na podstawie id_scen
    @GetMapping("/{id}")
    public Optional<Scenario> findById(@PathVariable Integer id) {
        return scenarioRepository.findById(id);
    }

    @GetMapping("/name/{name}")
    public Optional<Scenario> findByName(@PathVariable String name) {
        return scenarioRepository.findByName(name);
    }

    // Metoda create - Tworzy nowy scenariusz
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("")
    public void create(@RequestBody Scenario scenario) {
        scenarioRepository.create(scenario);
    }

    // Metoda update - Aktualizuje scenariusz po id_scen
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("/{id}")
    public void update(@RequestBody Scenario scenario, @PathVariable Integer id) {
        scenarioRepository.update(scenario, id);
    }

    // Metoda delete - Usuwa scenariusz po id_scen
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        scenarioRepository.delete(id);
    }
}
