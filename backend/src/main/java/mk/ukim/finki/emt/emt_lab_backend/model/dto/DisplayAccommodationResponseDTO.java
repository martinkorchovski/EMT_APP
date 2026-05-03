package mk.ukim.finki.emt.emt_lab_backend.model.dto;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Accommodation;

public record DisplayAccommodationResponseDTO(
        Long id,
        String name,
        int numRooms,
        String category,
        String state,
        Long hostId,
        boolean rented
) {
    public static DisplayAccommodationResponseDTO from(Accommodation a) {
        return new DisplayAccommodationResponseDTO(
                a.getId(),
                a.getName(),
                a.getNumRooms(),
                a.getCategory().getName(),
                a.getState().getName(),
                a.getHost().getId(),
                a.getRented()
        );
    }
}
