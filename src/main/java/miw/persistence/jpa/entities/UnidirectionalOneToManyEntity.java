package miw.persistence.jpa.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class UnidirectionalOneToManyEntity {

    @Id
    @GeneratedValue
    private int id;

    private String nick;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AnyEntity> anyEntityList;

    public UnidirectionalOneToManyEntity() {
    }

    public UnidirectionalOneToManyEntity(String nick, List<AnyEntity> anyEntityList) {
        this.nick = nick;
        this.anyEntityList = anyEntityList;
    }

    @Override
    public String toString() {
        return "UnidirectionalOneToManyEntity [id=" + id + ", nick=" + nick + ", anyEntityList=" + anyEntityList + "]";
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
        return id == ((UnidirectionalOneToManyEntity) obj).id;
    }

    public int getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public List<AnyEntity> getAnyEntityList() {
        return anyEntityList;
    }

}
