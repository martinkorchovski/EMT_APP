package mk.ukim.finki.emt.emt_lab_backend.service.domain;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Country;
import java.util.List;
import java.util.Optional;

public interface CountryService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
}
