package es.upm.miw.persistence.mongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class OneToManyEmbeddedDocument {

    @Id
    private String id;

    private String nick;

    private List<EmbeddableDocument> embeddableDocumentList;

    public OneToManyEmbeddedDocument() {
    }

    public OneToManyEmbeddedDocument(String nick) {
        this.nick = nick;
        this.embeddableDocumentList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return "OneToManyEmbeddedDocument [id=" + id + ", nick=" + nick + ", embeddableDocumentList=" + embeddableDocumentList + "]";
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof OneAndManyToOneDocument && id.equals(((OneToManyEmbeddedDocument) obj).id);
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

    public List<EmbeddableDocument> getEmbeddableDocumentList() {
        return embeddableDocumentList;
    }

    public void setEmbeddableDocumentList(List<EmbeddableDocument> embeddableDocumentList) {
        this.embeddableDocumentList = embeddableDocumentList;
    }

}
