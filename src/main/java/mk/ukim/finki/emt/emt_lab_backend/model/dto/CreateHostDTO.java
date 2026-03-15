package mk.ukim.finki.emt.emt_lab_backend.model.dto;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Country;
import mk.ukim.finki.emt.emt_lab_backend.model.domain.Host;

public record CreateHostDTO(
        String name,
        String surname,
        Long countryId
) {
    public Host toAuthor(Country country) {
        return new Host(name, surname, country);
    }
}
