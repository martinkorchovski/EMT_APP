package mk.ukim.finki.emt.emt_lab_backend.service.domain;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    Optional<User> findById(Long id);
}
