package mk.ukim.finki.emt.emt_lab_backend.service.application.impl;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Country;
import mk.ukim.finki.emt.emt_lab_backend.service.application.CountryApplicationService;
import mk.ukim.finki.emt.emt_lab_backend.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    public CountryApplicationServiceImpl(CountryService countryService) {
        this.countryService = countryService;
    }

    @Override
    public List<Country> findAll() {
        return countryService.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryService.findById(id);
    }

    @Override
    public Country save(Country country) {
        return countryService.save(country);
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return countryService.update(id, country);
    }

    @Override
    public Optional<Country> deleteById(Long id) {
        return countryService.deleteById(id);
    }
}