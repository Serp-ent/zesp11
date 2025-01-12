package adventure.go.goadventure.auth;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthRequest authRequest) {
        authService.register(authRequest.getLogin(), authRequest.getEmail(), authRequest.getPassword());
        return ResponseEntity.ok("User registered successfully.");
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest) {
        try {
            String token = authService.login(authRequest.getLogin(), authRequest.getPassword());
            return ResponseEntity.ok(new AuthResponse(token)); // Zwracamy token w odpowiedzi.
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(401).body(ex.getMessage()); // HTTP 401 - Unauthorized
        }
    }
}
