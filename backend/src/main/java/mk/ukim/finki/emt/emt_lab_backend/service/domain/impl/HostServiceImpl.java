package mk.ukim.finki.emt.emt_lab_backend.service.domain.impl;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Host;
import mk.ukim.finki.emt.emt_lab_backend.repository.HostRepository;
import mk.ukim.finki.emt.emt_lab_backend.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HostServiceImpl implements HostService {

    private final HostRepository hostRepository;

    public HostServiceImpl(HostRepository hostRepository) {
        this.hostRepository = hostRepository;
    }

    @Override
    public List<Host> findAll() {
        return hostRepository.findAll();
    }

    @Override
    public Optional<Host> findById(Long id) {
        return hostRepository.findById(id);
    }
}
