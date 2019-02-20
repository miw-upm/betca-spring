package miw.persistence.mongo.documents.library;

public class NameDto {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "NameDto{" +
                "name='" + name + '\'' +
                '}';
    }
}
