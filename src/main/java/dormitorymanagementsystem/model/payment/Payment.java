package dormitorymanagementsystem.model.payment;

import javafx.beans.property.*;

import java.util.Date;

public class Payment {

    //Declare Payment Properties
    private IntegerProperty idPayment;
    private IntegerProperty idResident;
    private IntegerProperty idAdmin;
    private DoubleProperty paymentAmount;
    private DoubleProperty paymentBalance;
    private SimpleObjectProperty<Date> paymentDate;
    private StringProperty paymentType;
    private StringProperty residentName;
    private StringProperty residentLastName;

    public Payment() {
        this.idPayment = new SimpleIntegerProperty();
        this.idResident = new SimpleIntegerProperty();
        this.idAdmin = new SimpleIntegerProperty();
        this.paymentAmount = new SimpleDoubleProperty();
        this.paymentBalance = new SimpleDoubleProperty();
        this.paymentDate = new SimpleObjectProperty<>();
        this.paymentType = new SimpleStringProperty();
        this.residentName = new SimpleStringProperty();
        this.residentLastName = new SimpleStringProperty();
    }

    public int getIdPayment() {
        return idPayment.get();
    }

    public void setIdPayment(int idPayment) {
        this.idPayment.set(idPayment);
    }

    public IntegerProperty idPaymentProperty() {
        return idPayment;
    }

    public int getIdResident() {
        return idResident.get();
    }

    public void setIdResident(int idResident) {
        this.idResident.set(idResident);
    }

    public IntegerProperty idResidentProperty() {
        return idResident;
    }

    public int getIdAdmin() {
        return idAdmin.get();
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin.set(idAdmin);
    }

    public IntegerProperty idAdminProperty() {
        return idAdmin;
    }

    public double getPaymentAmount() {
        return paymentAmount.get();
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount.set(paymentAmount);
    }

    public DoubleProperty paymentAmountProperty() {
        return paymentAmount;
    }

    public double getPaymentBalance() {
        return paymentBalance.get();
    }

    public void setPaymentBalance(double paymentBalance) {
        this.paymentBalance.set(paymentBalance);
    }

    public DoubleProperty paymentBalanceProperty() {
        return paymentBalance;
    }

    public Date getPaymentDate() {
        return paymentDate.get();
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate.set(paymentDate);
    }

    public SimpleObjectProperty<Date> paymentDateProperty() {
        return paymentDate;
    }

    public String getPaymentType() {
        return paymentType.get();
    }

    public void setPaymentType(String paymentType) {
        this.paymentType.set(paymentType);
    }

    public StringProperty paymentTypeProperty() {
        return paymentType;
    }

    public String getResidentName() {
        return residentName.get();
    }

    public void setResidentName(String residentName) {
        this.residentName.set(residentName);
    }

    public StringProperty residentNameProperty() {
        return residentName;
    }

    public String getResidentLastName() {
        return residentLastName.get();
    }

    public void setResidentLastName(String residentLastName) {
        this.residentLastName.set(residentLastName);
    }

    public StringProperty residentLastNameProperty() {
        return residentLastName;
    }
}
