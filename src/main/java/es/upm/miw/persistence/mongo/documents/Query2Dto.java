package es.upm.miw.persistence.mongo.documents;

import java.util.Date;

public class Query2Dto {

    private Date bornDate;

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }

    @Override
    public String toString() {
        return "Query2Dto [bornDate=" + bornDate + "]";
    }

}
