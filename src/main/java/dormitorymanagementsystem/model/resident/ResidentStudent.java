package dormitorymanagementsystem.model.resident;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ResidentStudent extends Resident {

    private StringProperty studentNumber;
    private StringProperty department;
    private StringProperty yearOfStudy;
    private StringProperty academicYear;
    private StringProperty studentPaymentAccount;

    public ResidentStudent() {
        super();
        this.studentNumber = new SimpleStringProperty();
        this.department = new SimpleStringProperty();
        this.yearOfStudy = new SimpleStringProperty();
        this.academicYear = new SimpleStringProperty();
        this.studentPaymentAccount = new SimpleStringProperty();
    }


    public String getStudentNumber() {
        return studentNumber.get();
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber.set(studentNumber);
    }

    public String getDepartment() {
        return department.get();
    }

    public void setDepartment(String department) {
        this.department.set(department);
    }


    public String getYearOfStudy() {
        return yearOfStudy.get();
    }

    public void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy.set(yearOfStudy);
    }

    public String getAcademicYear() {
        return academicYear.get();
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear.set(academicYear);
    }

    public String getStudentPaymentAccount() {
        return studentPaymentAccount.get();
    }

    public void setStudentPaymentAccount(String studentPaymentAccount) {
        this.studentPaymentAccount.set(studentPaymentAccount);
    }

    public ObservableList<String> listOfResidentStudent() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(super.listOfResidentStudent());
        list.addAll(this.getStudentNumber(), this.getDepartment(), this.getYearOfStudy(), this.getAcademicYear(), this.getStudentPaymentAccount());
        return list;
    }
}
