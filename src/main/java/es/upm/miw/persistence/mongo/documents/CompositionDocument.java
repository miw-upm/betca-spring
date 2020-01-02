package es.upm.miw.persistence.mongo.documents;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class CompositionDocument {

    @Id
    private String id;

    private String nick;

    private EmbeddableDocument embeddableDocument;

    private List<EmbeddableDocument> embeddableDocumentList;

    public CompositionDocument() {
        embeddableDocumentList = new ArrayList<>();
    }

    public CompositionDocument(String nick, EmbeddableDocument embeddableDocument) {
        this();
        this.nick = nick;
        this.embeddableDocument = embeddableDocument;
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

    public EmbeddableDocument getEmbeddableDocument() {
        return embeddableDocument;
    }

    public void setEmbeddableDocument(EmbeddableDocument embeddableDocument) {
        this.embeddableDocument = embeddableDocument;
    }

    public List<EmbeddableDocument> getEmbeddableDocumentList() {
        return embeddableDocumentList;
    }

    public void setEmbeddableDocumentList(List<EmbeddableDocument> embeddableDocumentList) {
        this.embeddableDocumentList = embeddableDocumentList;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj != null && getClass() == obj.getClass() && (id.equals(((CompositionDocument) obj).id));
    }

    @Override
    public String toString() {
        return "CompositionDocument{" +
                "id='" + id + '\'' +
                ", nick='" + nick + '\'' +
                ", embeddableDocument=" + embeddableDocument +
                ", embeddableDocumentList=" + embeddableDocumentList +
                '}';
    }
}
