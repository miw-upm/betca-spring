package miw.persistence.jpa.entities;

import javax.persistence.*;

@Entity
public class UnidirectionalOneToOneJoinColumnEntity {

    @Id
    @GeneratedValue
    private int id;

    private String nick;

    @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn
    private AnyEntity anyEntity;

    public UnidirectionalOneToOneJoinColumnEntity() {
    }

    public UnidirectionalOneToOneJoinColumnEntity(String nick, AnyEntity anyEntity) {
        this.nick = nick;
        this.anyEntity = anyEntity;
    }

    @Override
    public String toString() {
        return "UnidirectionalOneToOneJoinColumnEntity [id=" + id + ", nick=" + nick + ", anyEntity=" + anyEntity + "]";
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UnidirectionalOneToManyEntity)) {
            return false;
        }
        return id == ((UnidirectionalOneToOneJoinColumnEntity) obj).id;
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
