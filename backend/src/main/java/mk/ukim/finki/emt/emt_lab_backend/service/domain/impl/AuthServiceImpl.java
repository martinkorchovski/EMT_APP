package mk.ukim.finki.emt.emt_lab_backend.service.domain.impl;

import mk.ukim.finki.emt.emt_lab_backend.config.JwtUtil;
import mk.ukim.finki.emt.emt_lab_backend.model.domain.User;
import mk.ukim.finki.emt.emt_lab_backend.repository.UserRepository;
import mk.ukim.finki.emt.emt_lab_backend.service.domain.AuthService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder passwordEncoder;

    public AuthServiceImpl(UserRepository userRepository, JwtUtil jwtUtil, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password) {
        User user = new User(username, passwordEncoder.encode(password));
        return userRepository.save(user);
    }

    @Override
    public String login(String username, String password) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }
        return jwtUtil.generateToken(username);
    }
}
