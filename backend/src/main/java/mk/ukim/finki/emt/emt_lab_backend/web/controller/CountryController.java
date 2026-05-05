package mk.ukim.finki.emt.emt_lab_backend.web.controller;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Country;
import mk.ukim.finki.emt.emt_lab_backend.service.domain.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/countries")
public class CountryController {

    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<Country>> getAllCountries() {
        return ResponseEntity.ok(countryService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Country> getCountryById(@PathVariable Long id) {
        return countryService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
