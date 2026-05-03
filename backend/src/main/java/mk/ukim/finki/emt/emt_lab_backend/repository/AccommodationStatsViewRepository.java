package mk.ukim.finki.emt.emt_lab_backend.repository;

import mk.ukim.finki.emt.emt_lab_backend.model.views.AccommodationStatsView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationStatsViewRepository extends JpaRepository<AccommodationStatsView, String> {
}