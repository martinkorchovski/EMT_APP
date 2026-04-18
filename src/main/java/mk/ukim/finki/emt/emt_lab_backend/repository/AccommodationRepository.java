package mk.ukim.finki.emt.emt_lab_backend.repository;

import mk.ukim.finki.emt.emt_lab_backend.model.domain.Accommodation;
import mk.ukim.finki.emt.emt_lab_backend.model.projection.AccommodationLongProjection;
import mk.ukim.finki.emt.emt_lab_backend.model.projection.AccommodationShortProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {
    List<Accommodation> findAll();

    Page<Accommodation> findAll(Pageable pageable);

    List<Accommodation> findByIsRentedTrue();

    List<Accommodation> findByIsRentedFalse();

    @Query("""
            SELECT a FROM Accommodation a
            JOIN a.host h
            JOIN h.country c
            WHERE (:name IS NULL OR LOWER(a.name) LIKE LOWER(CONCAT('%', CAST(:name AS string), '%')))
            AND (:categoryId IS NULL OR a.category.id = :categoryId)
            AND (:hostId IS NULL OR a.host.id = :hostId)
            AND (:countryName IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', CAST(:countryName AS string), '%')))
            AND (:numRooms IS NULL OR a.numRooms = :numRooms)
            ORDER BY a.name DESC
            """)
    Page<Accommodation> findAllFiltered(
            @Param("name") String name,
            @Param("categoryId") Long categoryId,
            @Param("hostId") Long hostId,
            @Param("countryName") String countryName,
            @Param("numRooms") Integer numRooms,
            Pageable pageable
    );

    @Query("""
            SELECT 
                a.id AS id,
                a.name AS name,
                c.name AS category,
                a.numRooms AS numRooms
            FROM Accommodation a
            JOIN a.category c
            """)
    List<AccommodationShortProjection> findAllShort();

    @Query("""
            SELECT 
                   a.id AS id,
                   a.name AS name,
                   c.name AS category,
                   a.numRooms AS numRooms,
                   h.name AS hostName,
                   h.surname AS hostSurname,
                   co.name AS countryName
               FROM Accommodation a
               JOIN a.category c
               JOIN a.host h
               JOIN h.country co
            """)
    List<AccommodationLongProjection> findAllDetailed();

    @Query("SELECT a FROM Accommodation a JOIN FETCH a.host h JOIN FETCH h.country")
    List<Accommodation> findAllWithHostAndCountry();

    @Query("SELECT a FROM Accommodation a JOIN FETCH a.host h JOIN FETCH h.country WHERE a.id = :id")
    Optional<Accommodation> findWithHostAndCountryById(@Param("id") Long id);
}
