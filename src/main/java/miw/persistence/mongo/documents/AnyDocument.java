package miw.persistence.mongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class AnyDocument {

    @Id
    private String id;

    private String value;

    public AnyDocument() {
    }

    public AnyDocument(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "AnyEntity [id=" + id + ", value=" + value + "]";
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof AnyDocument && id.equals(((AnyDocument) obj).id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
