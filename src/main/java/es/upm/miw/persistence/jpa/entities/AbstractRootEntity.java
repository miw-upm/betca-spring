package es.upm.miw.persistence.jpa.entities;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//se mapea en una tabla, InheritanceType.JOINED: Una tabla por clase
public abstract class AbstractRootEntity {

    @Id
    @GeneratedValue
    private Integer id;

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
        return (this == obj) || ((obj != null) && (getClass().equals(obj.getClass())) && (id.equals(((AbstractRootEntity) obj).id)));
    }

    public Integer getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

}
