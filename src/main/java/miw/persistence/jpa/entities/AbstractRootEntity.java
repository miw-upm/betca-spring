package miw.persistence.jpa.entities;

import javax.persistence.*;

//InheritanceType.JOINED: Una tabla por clase
//InheritanceType.SINGLE_TABLE: En una tabla se mapea todo
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class AbstractRootEntity {

    @Id
    @GeneratedValue
    private int id;

    private String nick;

    public AbstractRootEntity() {
    }

    public AbstractRootEntity(String nick) {
        this.nick = nick;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else {
            return id == obj.hashCode();
        }
    }

    public int getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

}
