package repository;
import model.MonitoringStation;
import java.sql.SQLException;
import java.util.List;

/** Repository interface for MonitoringStation. */
public interface StationRepository extends Repository<Integer, MonitoringStation> {
    List<MonitoringStation> search(String keyword) throws SQLException;
}
