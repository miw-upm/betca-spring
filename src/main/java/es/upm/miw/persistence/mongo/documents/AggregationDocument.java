package es.upm.miw.persistence.mongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class AggregationDocument {

    @Id
    private String id;

    private String nick;

    @DBRef
    private AnyDocument anyDocument;

    @DBRef
    private List<AnyDocument> anyDocumentList;

    public AggregationDocument() {
        //Empty for the framework
    }

    public AggregationDocument(String nick, AnyDocument anyDocument, List<AnyDocument> anyDocumentList) {
        this.nick = nick;
        this.anyDocument = anyDocument;
        this.anyDocumentList = anyDocumentList;
    }

    public String getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public AnyDocument getAnyDocument() {
        return anyDocument;
    }

    public void setAnyDocument(AnyDocument anyDocument) {
        this.anyDocument = anyDocument;
    }

    public List<AnyDocument> getAnyDocumentList() {
        return anyDocumentList;
    }

    public void setAnyDocumentList(List<AnyDocument> anyDocumentList) {
        this.anyDocumentList = anyDocumentList;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AggregationDocument && id.equals(((AggregationDocument) obj).id);
    }

    @Override
    public String toString() {
        return "AggregationDocument{" +
                "id='" + id + '\'' +
                ", nick='" + nick + '\'' +
                ", anyDocument=" + anyDocument +
                ", anyDocumentList=" + anyDocumentList +
                '}';
    }
}
