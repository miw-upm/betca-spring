package miw.persistence.mongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document
public class OneToOneEmbeddedDocument {

    @Id
    private String id;

    private String nick;

    private EmbeddableDocument embeddableDocument;

    public OneToOneEmbeddedDocument() {
    }

    public OneToOneEmbeddedDocument(String nick, EmbeddableDocument embeddableDocument) {
        this.nick = nick;
        this.embeddableDocument = embeddableDocument;
    }

    @Override
    public String toString() {
        return "OneToOneEmbeddedDocument [id=" + id + ", nick=" + nick + ", embeddableDocument=" + embeddableDocument + "]";
    }

    @Override
    public int hashCode() {
        return this.id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return !(obj instanceof OneToOneEmbeddedDocument) ? false : Objects.equals(id, ((OneToOneEmbeddedDocument) obj).id);
    }

    public String getId() {
        return id;
    }

    public String getNick() {
        return nick;
    }

    public EmbeddableDocument getEmbeddableDocument() {
        return embeddableDocument;
    }

}
