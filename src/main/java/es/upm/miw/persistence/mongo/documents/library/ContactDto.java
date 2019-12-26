package es.upm.miw.persistence.mongo.documents.library;

public class ContactDto {

    private Contact contact;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "ContactDto{" +
                "contact=" + contact +
                '}';
    }
}
