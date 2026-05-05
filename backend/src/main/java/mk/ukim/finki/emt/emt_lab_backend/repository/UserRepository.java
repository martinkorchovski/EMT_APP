package mk.ukim.finki.emt.emt_lab_backend.repository;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
