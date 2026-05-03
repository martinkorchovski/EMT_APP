package mk.ukim.finki.emt.emt_lab_backend.model.scheduling;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class AccommodationStatsRefreshScheduler {

    @PersistenceContext
    private EntityManager entityManager;

    @Scheduled(fixedRateString = "${app.materialized-view.refresh-rate:60000}")
    @Transactional
    public void refreshMaterializedView() {
        entityManager.createNativeQuery("REFRESH MATERIALIZED VIEW accommodation_stats_view")
                .executeUpdate();
        System.out.println("Materialized view refreshed at: " + java.time.LocalDateTime.now());
    }
}