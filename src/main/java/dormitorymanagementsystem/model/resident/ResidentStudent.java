package dormitorymanagementsystem.model.resident;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ResidentStudent extends Resident {

    private StringProperty studentNumber;
    private StringProperty department;
    private StringProperty yearOfStudy;
    private StringProperty academicYear;
    private StringProperty studentPaymentAccount;


    ResidentStudent() {
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

    void setStudentNumber(String studentNumber) {
        this.studentNumber.set(studentNumber);
    }

    public String getDepartment() {
        return department.get();
    }

    void setDepartment(String department) {
        this.department.set(department);
    }


    public String getYearOfStudy() {
        return yearOfStudy.get();
    }

    void setYearOfStudy(String yearOfStudy) {
        this.yearOfStudy.set(yearOfStudy);
    }

    public String getAcademicYear() {
        return academicYear.get();
    }

    void setAcademicYear(String academicYear) {
        this.academicYear.set(academicYear);
    }

    public String getStudentPaymentAccount() {
        return studentPaymentAccount.get();
    }

    void setStudentPaymentAccount(String studentPaymentAccount) {
        this.studentPaymentAccount.set(studentPaymentAccount);
    }
}
