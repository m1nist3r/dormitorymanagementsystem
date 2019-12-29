package dormitorymanagementsystem.model.resident;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ResidentErasmus extends Resident {

    private StringProperty originUniversity;
    private StringProperty erasmusNumber;

    ResidentErasmus() {
        super();
        this.originUniversity = new SimpleStringProperty();
        this.erasmusNumber = new SimpleStringProperty();
    }

    public String getOriginUniversity() {
        return originUniversity.get();
    }

    void setOriginUniversity(String originUniversity) {
        this.originUniversity.set(originUniversity);
    }

    public String getErasmusNumber() {
        return erasmusNumber.get();
    }

    void setErasmusNumber(String erasmusNumber) {
        this.erasmusNumber.set(erasmusNumber);
    }
}
