package dormitorymanagementsystem.model.resident;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ResidentErasmus extends Resident {

    private StringProperty originUniversity;
    private StringProperty erasmusNumber;

    public ResidentErasmus() {
        super();
        this.originUniversity = new SimpleStringProperty();
        this.erasmusNumber = new SimpleStringProperty();
    }

    public String getOriginUniversity() {
        return originUniversity.get();
    }

    public void setOriginUniversity(String originUniversity) {
        this.originUniversity.set(originUniversity);
    }

    public String getErasmusNumber() {
        return erasmusNumber.get();
    }

    public void setErasmusNumber(String erasmusNumber) {
        this.erasmusNumber.set(erasmusNumber);
    }

    public ObservableList<String> listOfResidentStudent() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(super.listOfResidentStudent());
        list.addAll(this.getOriginUniversity(), this.getErasmusNumber());
        return list;
    }
}
