package es.upm.miw.persistence.jpa.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UnidirectionalManyToOneJoinColumnEntity {

    @Id
    @GeneratedValue
    private int id;

    private String nick;

    @ManyToOne
    // @JoinColumn
    private AnyEntity anyEntity;

    public UnidirectionalManyToOneJoinColumnEntity() {
    }

    public UnidirectionalManyToOneJoinColumnEntity(String nick, AnyEntity anyEntity) {
        this.nick = nick;
        this.anyEntity = anyEntity;
    }

    @Override
    public String toString() {
        return "UnidirectionalManyToOneJoinColumnEntity [id=" + id + ", nick=" + nick + ", anyEntity=" + anyEntity + "]";
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UnidirectionalManyToOneJoinColumnEntity)) {
            return false;
        }
        return id == ((UnidirectionalManyToOneJoinColumnEntity) obj).id;
    }

    public int getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public AnyEntity getAnyEntity() {
        return anyEntity;
    }

}
