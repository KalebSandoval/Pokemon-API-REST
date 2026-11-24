package sandoval.brian.pokeApi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sandoval.brian.pokeApi.dto.LoginRequest;
import sandoval.brian.pokeApi.dto.LoginResponse;
import sandoval.brian.pokeApi.model.User;
import sandoval.brian.pokeApi.repository.UserRepository;
import sandoval.brian.pokeApi.service.JwtService;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail());

        if (user == null) {
            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "credenciales invalidas"));
        }

        String token = jwtService.generateToken(user.getEmail(), user.getRole());

        return ResponseEntity.ok(new LoginResponse(token));

    }


}
