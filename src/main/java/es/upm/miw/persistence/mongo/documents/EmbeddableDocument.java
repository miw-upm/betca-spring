package es.upm.miw.persistence.mongo.documents;


public class EmbeddableDocument {

    private int number;

    private String value;

    public EmbeddableDocument(int number, String value) {
        this.number = number;
        this.value = value;
    }

    @Override
    public String toString() {
        return "EmbeddableEntity [number=" + number + ", value=" + value + "]";
    }

    public int getNumber() {
        return number;
    }

    public String getValue() {
        return value;
    }

}
