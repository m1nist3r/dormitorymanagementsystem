package dormitorymanagementsystem.model.resident;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ResidentStudent {

    private StringProperty studentNumber;
    private StringProperty department;
    private StringProperty yearOfStudy;
    private StringProperty academicYear;
    private StringProperty studentPaymentAccount;


    ResidentStudent() {
        this.studentNumber = new SimpleStringProperty();
        this.department = new SimpleStringProperty();
        this.yearOfStudy = new SimpleStringProperty();
        this.academicYear = new SimpleStringProperty();
        this.studentPaymentAccount = new SimpleStringProperty();
    }


    public String getStudentNumber() {
        return studentNumber.get();
    }

    void setStudentNumber(String studentNumber) {
        this.studentNumber.set(studentNumber);
    }

    public StringProperty studentNumberProperty() {
        return studentNumber;
    }

    public String getDepartment() {
        return department.get();
    }

    void setDepartment(String department) {
        this.department.set(department);
    }

    public StringProperty departmentProperty() {
        return department;
    }

    public String getYearOfStudy() {
        return yearOfStudy.get();
    }

    void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy.set(yearOfStudy);
    }

    public StringProperty yearOfStudyProperty() {
        return yearOfStudy;
    }

    public String getAcademicYear() {
        return academicYear.get();
    }

    void setAcademicYear(String academicYear) {
        this.academicYear.set(academicYear);
    }

    public StringProperty academicYearProperty() {
        return academicYear;
    }

    public String getStudentPaymentAccount() {
        return studentPaymentAccount.get();
    }

    void setStudentPaymentAccount(String studentPaymentAccount) {
        this.studentPaymentAccount.set(studentPaymentAccount);
    }

    public StringProperty studentPaymentAccountProperty() {
        return studentPaymentAccount;
    }

}
