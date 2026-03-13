package repository;
import model.Entity;
import java.sql.SQLException;
import java.util.List;

/**
 * Generic CRUD repository interface.
 * @param <ID> primary key type
 * @param <E>  entity type
 */
public interface Repository<ID, E extends Entity<ID>> {
    List<E> findAll()        throws SQLException;
    E       findById(ID id)  throws SQLException;
    ID      save(E entity)   throws SQLException;
    void    update(E entity) throws SQLException;
    void    delete(ID id)    throws SQLException;
}
