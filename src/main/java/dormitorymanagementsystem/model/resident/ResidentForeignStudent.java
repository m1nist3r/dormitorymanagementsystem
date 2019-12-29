package dormitorymanagementsystem.model.resident;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ResidentForeignStudent extends Resident {

    private StringProperty originUniversity;

    ResidentForeignStudent() {
        super();
        originUniversity = new SimpleStringProperty();
    }

    public String getOriginUniversity() {
        return originUniversity.get();
    }

    void setOriginUniversity(String originUniversity) {
        this.originUniversity.set(originUniversity);
    }
}
