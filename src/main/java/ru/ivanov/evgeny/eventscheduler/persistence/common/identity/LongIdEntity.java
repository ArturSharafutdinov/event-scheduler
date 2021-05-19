package ru.ivanov.evgeny.eventscheduler.persistence.common.identity;

import javax.persistence.*;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class LongIdEntity implements Identified {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : System.identityHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof LongIdEntity) && (id != null) && (id.equals(((LongIdEntity) obj).getId()));
    }
}
