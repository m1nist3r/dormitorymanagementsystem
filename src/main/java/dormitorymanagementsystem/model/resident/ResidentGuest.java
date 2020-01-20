package dormitorymanagementsystem.model.resident;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ResidentGuest extends Resident {

    private StringProperty isStudent;
    private StringProperty isPartTimeStudent;

    ResidentGuest() {
        super();
        this.isStudent = new SimpleStringProperty();
        this.isPartTimeStudent = new SimpleStringProperty();
    }

    public String getIsStudent() {
        return isStudent.get();
    }

    void setIsStudent(String isStudent) {
        this.isStudent.set(isStudent);
    }

    public String getIsPartTimeStudent() {
        return isPartTimeStudent.get();
    }

    void setIsPartTimeStudent(String isPartTimeStudent) {
        this.isPartTimeStudent.set(isPartTimeStudent);
    }

    public ObservableList<String> listOfResidentStudent() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(super.listOfResidentStudent());
        list.addAll(this.getIsStudent(), this.getIsPartTimeStudent());
        return list;
    }
}
