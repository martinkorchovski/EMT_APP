package mk.ukim.finki.emt.emt_lab_backend.model.dto;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Accommodation;

public record AccommodationResponseDTO(
        Long id,
        String name,
        Integer numRooms,
        String category,
        String state,
        String host,
        String country
) {
    private AccommodationResponseDTO toDTO(Accommodation a) {
        return new AccommodationResponseDTO(
                a.getId(),
                a.getName(),
                a.getNumRooms(),
                a.getCategory().getName(),
                a.getState().getName(),
                a.getHost().getName() + " " + a.getHost().getSurname(),
                a.getHost().getCountry().getName()
        );
    }
}
