package model;

/**
 * Abstract base class for all entities.
 * Holds the primary key so the generic repository can work with any entity type.
 * @param <ID> type of the primary key (e.g. Integer)
 */
public abstract class Entity<ID> {
    protected ID id;
    public ID getId() { return id; }
    public void setId(ID id) { this.id = id; }
}
