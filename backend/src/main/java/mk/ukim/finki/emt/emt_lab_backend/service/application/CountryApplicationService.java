package mk.ukim.finki.emt.emt_lab_backend.service.application;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Country;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {
    List<Country> findAll();
    Optional<Country> findById(Long id);
    Country save(Country country);
    Optional<Country> update(Long id, Country country);
    Optional<Country> deleteById(Long id);
}