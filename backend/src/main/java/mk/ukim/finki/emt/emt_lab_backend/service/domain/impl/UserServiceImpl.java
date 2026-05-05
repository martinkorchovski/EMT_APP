package mk.ukim.finki.emt.emt_lab_backend.service.domain.impl;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.User;
import mk.ukim.finki.emt.emt_lab_backend.repository.UserRepository;
import mk.ukim.finki.emt.emt_lab_backend.service.domain.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }
}
