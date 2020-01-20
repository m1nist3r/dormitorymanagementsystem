package dormitorymanagementsystem.model.resident;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    public ObservableList<String> listOfResidentStudent() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(super.listOfResidentStudent());
        list.add(this.getOriginUniversity());
        return list;
    }
}
