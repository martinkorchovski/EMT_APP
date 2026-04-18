package mk.ukim.finki.emt.emt_lab_backend.model.dto;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Accommodation;
import java.util.List;

public record DisplayAccommodationDTO(
        Long id,
        String name,
        Integer numRooms,
        Long categoryId,
        Long stateId,
        Long hostId,
        Boolean rented
) {
    public static DisplayAccommodationDTO from(Accommodation accommodation) {
        return new DisplayAccommodationDTO(
                accommodation.getId(),
                accommodation.getName(),
                accommodation.getNumRooms(),
                accommodation.getCategory().getId(),
                accommodation.getState().getId(),
                accommodation.getHost().getId(),
                accommodation.getRented()
        );
    }

    public static List<DisplayAccommodationDTO> from(List<Accommodation> accommodations) {
        return accommodations
                .stream()
                .map(DisplayAccommodationDTO::from)
                .toList();
    }
}
