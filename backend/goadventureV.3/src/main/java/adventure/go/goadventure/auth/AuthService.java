package adventure.go.goadventure.auth;

import adventure.go.goadventure.jwt.JwtUtil;
import adventure.go.goadventure.user.User;
import adventure.go.goadventure.user.UserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {

    private final UserService userService;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public AuthService(UserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.passwordEncoder = new BCryptPasswordEncoder();
        this.jwtUtil = jwtUtil;
    }

    public String login(String login, String password) {
        Optional<User> userOptional = userService.findByLogin(login);

        if (userOptional.isEmpty()) {
            throw new IllegalArgumentException("Invalid login or password.");
        }

        User user = userOptional.get();

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new IllegalArgumentException("Invalid login or password.");
        }

        // Generowanie tokena JWT
        return jwtUtil.generateToken(user.getLogin());
    }
    /**
     * Rejestruje nowego użytkownika.
     */
    public void register(String login, String email, String password) {
        Optional<User> existingUserByLogin = userService.findByLogin(login);
        Optional<User> existingUserByEmail = userService.findByEmail(email);

        if (existingUserByLogin.isPresent()) {
            throw new IllegalArgumentException("User with this login already exists.");
        }

        if (existingUserByEmail.isPresent()) {
            throw new IllegalArgumentException("User with this email already exists.");
        }

        String hashedPassword = passwordEncoder.encode(password);
        User newUser = new User(null, login, email, hashedPassword); // id_user ustawione na null
        userService.save(newUser);
    }

    /**
     * Autoryzuje użytkownika.
     */
    public boolean authenticate(String login, String password) {
        Optional<User> userOptional = userService.findByLogin(login);

        if (userOptional.isEmpty()) {
            return false; // Użytkownik o podanym loginie nie istnieje
        }

        User user = userOptional.get();
        return passwordEncoder.matches(password, user.getPassword());
    }
}
