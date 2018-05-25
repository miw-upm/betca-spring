package miw.persistence.mongo.documents;

import java.util.Date;

public class Query1Dto {

    private String id;

    private Date bornDate;

    private String large;

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLarge() {
        return large;
    }

    public void setLarge(String large) {
        this.large = large;
    }

    @Override
    public String toString() {
        return "Query1Dto [id=" + id + ", bornDate=" + bornDate + ", large=" + large + "]";
    }

}
