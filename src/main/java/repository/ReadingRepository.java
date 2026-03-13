package repository;
import model.RadiationReading;
import java.sql.SQLException;
import java.util.List;

/** Repository interface for RadiationReading. */
public interface ReadingRepository extends Repository<Integer, RadiationReading> {
    List<RadiationReading> findByStationId(int stationId) throws SQLException;
    List<RadiationReading> searchByStationId(int stationId, String keyword) throws SQLException;
}
