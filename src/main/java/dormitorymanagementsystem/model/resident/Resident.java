package dormitorymanagementsystem.model.resident;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.Date;

public class Resident {

    //region Declare Resident Properties
    private IntegerProperty residentId;
    private IntegerProperty residentTypeId;
    private IntegerProperty residentRoomId;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty pesel;
    private StringProperty gender;
    private SimpleObjectProperty<Date> dobDate;
    private StringProperty motherName;
    private StringProperty fatherName;
    private StringProperty email;
    private StringProperty country;
    private StringProperty address;
    private StringProperty phoneNumber;
    private SimpleObjectProperty<Date> accommodationDate;
    private DoubleProperty payment_fee;
    private SimpleObjectProperty<Date> evictionDate;
    private BooleanProperty isBlocked;
    //Constructor
    public Resident() {
        this.residentId = new SimpleIntegerProperty();
        this.residentTypeId = new SimpleIntegerProperty();
        this.residentRoomId = new SimpleIntegerProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.pesel = new SimpleStringProperty();
        this.gender = new SimpleStringProperty();
        this.dobDate = new SimpleObjectProperty<>();
        this.motherName = new SimpleStringProperty();
        this.fatherName = new SimpleStringProperty();
        this.email = new SimpleStringProperty();
        this.country = new SimpleStringProperty();
        this.address = new SimpleStringProperty();
        this.phoneNumber = new SimpleStringProperty();
        this.accommodationDate = new SimpleObjectProperty<>();
        this.evictionDate = new SimpleObjectProperty<>();
        this.isBlocked = new SimpleBooleanProperty();
        this.payment_fee = new SimpleDoubleProperty();
    }
    //endregion

    @Override
    public String toString() {
        return firstName.getValue() + " " + lastName.getValue() + " " + pesel.getValue() + " " + residentId.getValue();
    }

    //region Getters And Setters For Resident

    //resident_id
    public String getResidentTypeId() {
        return String.valueOf(residentTypeId.get());
    }

    public void setResidentTypeId(int residentTypeId) {
        this.residentTypeId.set(residentTypeId);
    }

    public IntegerProperty residentTypeIdProperty() {
        return residentTypeId;
    }

    //first_name
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    //last_name
    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    //pesel
    public String getPesel() {
        return pesel.get();
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    public StringProperty peselProperty() {
        return pesel;
    }

    //gender
    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public StringProperty genderProperty() {
        return gender;
    }

    //date of birth
    public String getDobDate() {
        return dobDate.get().toString();
    }

    public void setDobDate(Date dobDate) {
        this.dobDate.set(dobDate);
    }

    public SimpleObjectProperty<Date> dobDateProperty() {
        return dobDate;
    }

    //mother_name
    public String getMotherName() {
        return motherName.get();
    }

    public void setMotherName(String motherName) {
        this.motherName.set(motherName);
    }

    public StringProperty motherNameProperty() {
        return motherName;
    }

    //father_name
    public String getFatherName() {
        return fatherName.get();
    }

    public void setFatherName(String fatherName) {
        this.fatherName.set(fatherName);
    }

    public StringProperty fatherNameProperty() {
        return fatherName;
    }

    //email
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty emailProperty() {
        return email;
    }

    //country
    public String getCountry() {
        return country.get();
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    public StringProperty countryProperty() {
        return country;
    }

    //address
    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    public StringProperty addressProperty() {
        return address;
    }

    //phone_number
    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    //accommodation_date
    public String getAccommodationDate() {
        return accommodationDate.get().toString();
    }

    public void setAccommodationDate(Date accommodationDate) {
        this.accommodationDate.set(accommodationDate);
    }

    public SimpleObjectProperty<Date> accommodationDateProperty() {
        return accommodationDate;
    }

    //eviction_date
    public String getEvictionDate() {
        if (evictionDate.get() != null) {
            return evictionDate.get().toString();
        } else return "null";
    }

    public void setEvictionDate(Date evictionDate) {
        this.evictionDate.set(evictionDate);
    }

    public SimpleObjectProperty<Date> evictionDateProperty() {
        return evictionDate;
    }

    //is_blocked
    public String getIsBlocked() {
        return String.valueOf(isBlocked.get());
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked.set(isBlocked);
    }

    public BooleanProperty isBlockedProperty() {
        return isBlocked;
    }

    //id_room
    public String getResidentRoomId() {
        return String.valueOf(residentRoomId.get());
    }

    public void setResidentRoomId(int residentRoomId) {
        this.residentRoomId.set(residentRoomId);
    }

    public IntegerProperty residentRoomIdProperty() {
        return residentRoomId;
    }

    //id
    public String getResidentId() {
        return String.valueOf(residentId.get());
    }

    public void setResidentId(int residentId) {
        this.residentId.set(residentId);
    }

    public IntegerProperty residentIdProperty() {
        return residentId;
    }

    public ObservableList<String> listOfResidentStudent() {
        ObservableList<String> list = FXCollections.observableArrayList();
        list.addAll(this.getResidentId(), this.getResidentTypeId(), this.getResidentRoomId(), this.getFirstName(),
                this.getLastName(), this.getPesel(), this.getGender(), this.getDobDate(), this.getMotherName(),
                this.getFatherName(), this.getEmail(), this.getCountry(), this.getAddress(), this.getPhoneNumber(),
                this.getAccommodationDate(), this.getEvictionDate(), this.getIsBlocked(), this.getPayment_fee());
        return list;
    }

    public String getPayment_fee() {
        return String.valueOf(payment_fee.get());
    }

    public void setPayment_fee(double payment_fee) {
        this.payment_fee.set(payment_fee);
    }

    public DoubleProperty payment_feeProperty() {
        return payment_fee;
    }
    //endregion

}

