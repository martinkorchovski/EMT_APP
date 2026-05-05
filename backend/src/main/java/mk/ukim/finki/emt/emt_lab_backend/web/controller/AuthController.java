package mk.ukim.finki.emt.emt_lab_backend.web.controller;

import mk.ukim.finki.emt.emt_lab_backend.model.dto.AuthResponse;
import mk.ukim.finki.emt.emt_lab_backend.model.dto.LoginRequest;
import mk.ukim.finki.emt.emt_lab_backend.model.dto.RegisterRequest;
import mk.ukim.finki.emt.emt_lab_backend.service.domain.AuthService;
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
    public ResponseEntity<?> register(@RequestBody RegisterRequest request) {
        authService.register(request.username(), request.password());
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        String token = authService.login(request.username(), request.password());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}
