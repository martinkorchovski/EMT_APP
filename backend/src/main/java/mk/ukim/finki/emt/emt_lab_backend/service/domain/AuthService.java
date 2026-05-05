package mk.ukim.finki.emt.emt_lab_backend.service.domain;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.User;

public interface AuthService {
    User register(String username, String password);
    String login(String username, String password);
}
