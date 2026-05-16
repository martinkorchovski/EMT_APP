package mk.ukim.finki.emt.emt_lab_backend.service.domain.impl;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Country;
import mk.ukim.finki.emt.emt_lab_backend.repository.CountryRepository;
import mk.ukim.finki.emt.emt_lab_backend.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CountryServiceImpl implements CountryService {

    private final CountryRepository countryRepository;

    public CountryServiceImpl(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @Override
    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    @Override
    public Optional<Country> findById(Long id) {
        return countryRepository.findById(id);
    }

    @Override
    public Country save(Country country) {
        return countryRepository.save(country);
    }

    @Override
    public Optional<Country> update(Long id, Country country) {
        return countryRepository.findById(id)
                .map(existing -> {
                    existing.setName(country.getName());
                    existing.setContinent(country.getContinent());
                    return countryRepository.save(existing);
                });
    }

    @Override
    public Optional<Country> deleteById(Long id) {
        return countryRepository.findById(id)
                .map(country -> {
                    countryRepository.deleteById(id);
                    return country;
                });
    }
}
