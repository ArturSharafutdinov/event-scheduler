package ru.ivanov.evgeny.eventscheduler.persistence.common;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.UUID;

@MappedSuperclass
@Access(AccessType.FIELD)
public abstract class UUIDEntity implements Identified{

    @Id
    private UUID uuid;

    public UUIDEntity() {
        this.uuid=UUID.randomUUID();
    }

    @Override
    public Serializable getId() {
        return this.uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public int hashCode() {
        return uuid != null ? uuid.hashCode() : System.identityHashCode(this);
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof LongIdEntity) && (uuid != null) && (uuid.equals(((UUIDEntity) obj).getId()));
    }


}
