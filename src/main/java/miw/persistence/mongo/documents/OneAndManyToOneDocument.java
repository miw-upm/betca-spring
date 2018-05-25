package miw.persistence.mongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class OneAndManyToOneDocument {

    @Id
    private String id;

    private String nick;

    @DBRef
    private AnyDocument anyDocument;

    public OneAndManyToOneDocument() {
    }

    public OneAndManyToOneDocument(String nick, AnyDocument anyDocument) {
        this.nick = nick;
        this.anyDocument = anyDocument;
    }

    @Override
    public String toString() {
        return "OneToOneDocument [id=" + id + ", nick=" + nick + ", anyDocument=" + anyDocument + "]";
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return !(obj instanceof OneAndManyToOneDocument) ? false : id.equals(((OneAndManyToOneDocument) obj).id);
    }

    public String getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }


}
