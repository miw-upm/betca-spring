package es.upm.miw.persistence.jpa.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class CompositionEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String nick;

    @Embedded
    private EmbeddableEntity embeddableEntity;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<AnyEntity> anyEntityList;

    @Lob
    private AnyClass[] anyClassArray;

    public CompositionEntity() {
        //Empty for framework
    }

    public CompositionEntity(String nick, EmbeddableEntity embeddableEntity) {
        this.nick = nick;
        this.embeddableEntity = embeddableEntity;
    }

    public Integer getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public EmbeddableEntity getEmbeddableEntity() {
        return embeddableEntity;
    }

    public void setEmbeddableEntity(EmbeddableEntity embeddableEntity) {
        this.embeddableEntity = embeddableEntity;
    }

    public List<AnyEntity> getAnyEntityList() {
        return anyEntityList;
    }

    public void setAnyEntityList(List<AnyEntity> anyEntityList) {
        this.anyEntityList = anyEntityList;
    }

    public AnyClass[] getAnyClassArray() {
        return anyClassArray;
    }

    public void setAnyClassArray(AnyClass[] anyClassArray) {
        this.anyClassArray = anyClassArray;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((CompositionEntity) obj).id));
    }

}
