package miw.persistence.jpa.entities;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.*;

// Parámetro name opcional: se indica el nombre de la tabla
// Nombre por defecto de la tabla: nombre de la clase (UnRelatedEntity)
@Entity(name = "un_related")
public class UnRelatedEntity {

    public static final String TRANSIENT = "no persistent";

    @Id
    @GeneratedValue
    private int id;

    // Nombre por defecto de la columna el nombre del atributo en minusculas
    @Column(name = "KCIN", unique = true, nullable = false, length = 30)
    private String nick;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private Gender gender;

    // TemporalType.TIMESTAMP: Fecha y hora, TemporalType.DATE Solo fecha
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar bornDate;

    @Lob
    private String large;

    private String[] strings; // Clase ArrayList funciona igual

    // Se Almacena en otra tabla
    @ElementCollection(fetch = FetchType.EAGER)
    // opcional el nombre de la tabla a utilizar
    @CollectionTable(name = "unrelatedentity_list")
    private List<String> list;

    @Transient
    private String noPersistent;

    public UnRelatedEntity() {
    }

    public UnRelatedEntity(String nick, Gender gender, Calendar bornDate, String large, String[] strings, List<String> list,
                           String noPersistent) {
        this.nick = nick;
        this.gender = gender;
        this.bornDate = bornDate;
        this.large = large;
        this.strings = strings;
        this.list = list;
        this.noPersistent = noPersistent;
    }

    public UnRelatedEntity(String nick) {
        this(nick, Gender.FEMALE, new GregorianCalendar(), "", null, new ArrayList<String>(), "");
    }

    @Override
    public String toString() {
        String time = new SimpleDateFormat("dd-MMM-yyyy ").format(bornDate.getTime());
        return "UnRelatedEntity [id=" + id + ", nick=" + nick + ", gender=" + gender + ", bornDate=" + time + ", large=" + large
                + ", strings=" + Arrays.toString(strings) + ", list=" + list + ", noPersistent=" + noPersistent + "]";
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof UnRelatedEntity)) {
            return false;
        }
        return nick.equals(((UnRelatedEntity) obj).nick);
    }

    public int getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Calendar getBornDate() {
        return bornDate;
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

    public List<String> getList() {
        return list;
    }

    public String getNoPersistent() {
        return noPersistent;
    }

}
