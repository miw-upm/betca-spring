package miw.persistence.mongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Arrays;
import java.util.Date;

@Document(collection = "un_related") // Nombre de la colección, por defecto el nombre de la clase
public class UnRelatedDocument {

    public static final String TRANSIENT = "no persistent";

    @Id
    private String id;

    @Field(value = "kcin") // Nombre del campo, por defecto el nombre del atributo
    private String nick;

    private Gender gender; // Se guarda el String asociado

    // @DateTimeFormat(iso=ISO.DATE) // ISO.DATE_TIME, ISO.TIME
    // ISODate: YYYY-MM-DDThh:mm:ss.sTZD, TZD = time zone designator (Z or +hh:mm or -hh:mm)
    // (1997-07-16T19:20:30.45+01:00) (2018-02-22T22:20Z)
    private Date bornDate;

    private String[] strings;

    private String large;

    private boolean booleano;

    private int integer;

    private long loger;

    private double decimal;

    @Transient
    private String noPersistent;

    public UnRelatedDocument() {
    }

    public UnRelatedDocument(String nick) {
        this.nick = nick;
        this.gender = Gender.FEMALE;
        this.bornDate = new Date();
        this.strings = new String[]{"uno", "dos"};
        this.large = "Large...";
        this.noPersistent = "noPersistent";
        this.booleano = true;
        this.integer = 666;
        this.loger = this.bornDate.getTime();
        this.decimal = 666.666e30;
    }

    @Override
    public String toString() {
        return "UnRelatedDocument [id=" + id + ", nick=" + nick + ", gender=" + gender + ", bornDate=" + bornDate + ", strings="
                + Arrays.toString(strings) + ", large=" + large + ", booleano=" + booleano + ", integer=" + integer + ", loger=" + loger
                + ", decimal=" + decimal + ", noPersistent=" + noPersistent + "]";
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof UnRelatedDocument && nick.equals(((UnRelatedDocument) obj).nick);
    }

    public String getId() {
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

    public Date getBornDate() {
        return bornDate;
    }

    public String[] getStrings() {
        return strings;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    public String getNoPersistent() {
        return noPersistent;
    }

    public boolean isBooleano() {
        return booleano;
    }

    public void setBooleano(boolean booleano) {
        this.booleano = booleano;
    }

    public long getLoger() {
        return loger;
    }

    public void setLoger(long loger) {
        this.loger = loger;
    }

}
