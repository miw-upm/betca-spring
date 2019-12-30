package es.upm.miw.persistence.jpa.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Entity//(name = "un_related_entity")
public class UnRelatedEntity {

    public static final String TRANSIENT = "no persistent";

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "KCIN", unique = true, nullable = false, length = 30)
    private String nick;

    @Enumerated(EnumType.STRING) //@Column(length = 20)
    private Gender gender;

    //@Temporal(TemporalType.TIMESTAMP) //Or TemporalType.DATE: only year, month & day)
    @Column
    private LocalDateTime bornDate;

    @Lob
    private String large;

    private String[] strings; // Or ArrayList

    @ElementCollection(fetch = FetchType.EAGER) // @CollectionTable(name = "unrelated_list")
    private List<String> list;

    @Transient
    private String noPersistent;

    public UnRelatedEntity() {
        //Empty for framework
    }

    public UnRelatedEntity(String nick, Gender gender, LocalDateTime bornDate, String large, String[] strings, List<String> list,
                           String noPersistent) {
        this.nick = nick;
        this.gender = gender;
        this.bornDate = bornDate;
        this.large = large;
        this.strings = strings;
        this.list = list;
        this.noPersistent = noPersistent;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public LocalDateTime getBornDate() {
        return bornDate;
    }

    public void setBornDate(LocalDateTime bornDate) {
        this.bornDate = bornDate;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String[] getStrings() {
        return strings;
    }

    public void setStrings(String[] strings) {
        this.strings = strings;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public String getNoPersistent() {
        return noPersistent;
    }

    public void setNoPersistent(String noPersistent) {
        this.noPersistent = noPersistent;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((UnRelatedEntity) obj).id));
    }

    @Override
    public String toString() {
        return "UnRelatedEntity{" +
                "id=" + id +
                ", nick='" + nick + '\'' +
                ", gender=" + gender +
                ", bornDate=" + bornDate +
                ", large='" + large + '\'' +
                ", strings=" + Arrays.toString(strings) +
                ", list=" + list +
                ", noPersistent='" + noPersistent + '\'' +
                '}';
    }
}
