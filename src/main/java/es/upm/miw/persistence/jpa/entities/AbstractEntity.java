package es.upm.miw.persistence.jpa.entities;

import javax.persistence.MappedSuperclass;

//@Entity: se mapea en tabla independiente
//Se indica que no se mapea en tabla, sino que se realiza en la tabla de la clases hija
@MappedSuperclass
public abstract class AbstractEntity extends AbstractRootEntity {

    private String description;

    public AbstractEntity() {
    }

    public AbstractEntity(String nick, String description) {
        super(nick);
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object obj) {
        return (this == obj) || ((obj != null) && (getClass().equals(obj.getClass())) && (this.getId().equals(((AbstractEntity) obj).getId())));
    }


}
