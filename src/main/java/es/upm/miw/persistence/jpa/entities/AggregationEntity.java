package es.upm.miw.persistence.jpa.entities;

import javax.persistence.*;
import java.util.List;

@Entity
public class AggregationEntity {

    @Id
    @GeneratedValue
    private Integer id;

    private String nick;

    @ManyToOne //@JoinColumn
    private AnotherEntity anotherEntity;

    @ManyToMany(fetch = FetchType.EAGER) //@JoinColumn
    private List<AnotherEntity> anotherEntityList;

    public AggregationEntity() {
        //Empty for framework
    }

    public AggregationEntity(String nick) {
        this.nick = nick;
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

    public AnotherEntity getAnotherEntity() {
        return anotherEntity;
    }

    public void setAnotherEntity(AnotherEntity anotherEntity) {
        this.anotherEntity = anotherEntity;
    }

    public List<AnotherEntity> getAnotherEntityList() {
        return anotherEntityList;
    }

    public void setAnotherEntityList(List<AnotherEntity> anotherEntityList) {
        this.anotherEntityList = anotherEntityList;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((AggregationEntity) obj).id));
    }

    @Override
    public String toString() {
        return "AggregationEntity{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", anotherEntity=" + anotherEntity +
                ", anotherEntityList=" + anotherEntityList +
                '}';
    }
}
