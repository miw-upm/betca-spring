package miw.restControllers;

import miw.persistence.jpa.entities.Gender;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

public class Dto {

    private Integer id;

    private String name;

    private Gender gender;

    private LocalDateTime bornDate;

    public Dto() {
    }

    public Dto(int id, String name, Gender gender, LocalDateTime bornDate) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.bornDate = bornDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && id == ((Dto) obj).id;
    }

    @Override
    public String toString() {
        return "Dto [id=" + id + ", name=" + name + ", gender=" + gender + ", bornDate=" + bornDate + "]";
    }

}
