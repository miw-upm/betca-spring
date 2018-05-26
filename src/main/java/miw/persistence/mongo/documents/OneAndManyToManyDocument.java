package miw.persistence.mongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class OneAndManyToManyDocument {

    @Id
    private String id;

    private String nick;

    @DBRef
    private List<AnyDocument> anyDocumentList;

    public OneAndManyToManyDocument() {
    }

    public OneAndManyToManyDocument(String nick, List<AnyDocument> anyDocumentList) {
        this.nick = nick;
        this.anyDocumentList = anyDocumentList;
    }

    @Override
    public String toString() {
        return "OneToManyDocument [id=" + id + ", nick=" + nick + ", anyDocumentList=" + anyDocumentList + "]";
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof OneAndManyToManyDocument && id.equals(((OneAndManyToManyDocument) obj).id);
    }

    public String getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public List<AnyDocument> getAnyDocumentList() {
        return anyDocumentList;
    }

}
