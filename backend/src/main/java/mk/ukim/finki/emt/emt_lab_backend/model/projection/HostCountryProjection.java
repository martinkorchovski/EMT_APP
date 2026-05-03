package mk.ukim.finki.emt.emt_lab_backend.model.projection;

public interface HostCountryProjection {
    Long getCountryId();

    String getCountryName();

    Long getHostCount();
}
