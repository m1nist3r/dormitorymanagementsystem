package dormitorymanagementsystem.model.resident;

import javafx.beans.property.*;

import java.util.Date;

public class Resident {
    //Declare Residents Table Columns
    private IntegerProperty residentTypeId;
    private IntegerProperty residentRoomId;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty passportNumber;
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
    private SimpleObjectProperty<Date> evictionDate;
    private BooleanProperty isBlocked;

    //Constructor
    Resident() {
        this.residentTypeId = new SimpleIntegerProperty();
        this.residentRoomId = new SimpleIntegerProperty();
        this.firstName = new SimpleStringProperty();
        this.lastName = new SimpleStringProperty();
        this.passportNumber = new SimpleStringProperty();
        this.passportNumber = new SimpleStringProperty();
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
    }

    //resident_id
    public int getResidentTypeId() {
        return residentTypeId.get();
    }

    public IntegerProperty residentTypeIdProperty() {
        return residentTypeId;
    }

    public void setResidentTypeId(int residentTypeId) {
        this.residentTypeId.set(residentTypeId);
    }

    //first_name
    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    //last_name
    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    //passport_number
    public String getPassportNumber() {
        return passportNumber.get();
    }

    public StringProperty passportNumberProperty() {
        return passportNumber;
    }

    public void setPassportNumber(String passportNumber) {
        this.passportNumber.set(passportNumber);
    }

    //pesel
    public String getPesel() {
        return pesel.get();
    }

    public StringProperty peselProperty() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    //gender
    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    //date of birth
    public Date getDobDate() {
        return dobDate.get();
    }

    public SimpleObjectProperty<Date> dobDateProperty() {
        return dobDate;
    }

    public void setDobDate(Date dobDate) {
        this.dobDate.set(dobDate);
    }

    //mother_name
    public String getMotherName() {
        return motherName.get();
    }

    public StringProperty motherNameProperty() {
        return motherName;
    }

    public void setMotherName(String motherName) {
        this.motherName.set(motherName);
    }

    //father_name
    public String getFatherName() {
        return fatherName.get();
    }

    public StringProperty fatherNameProperty() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName.set(fatherName);
    }

    //email
    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    //country
    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    public void setCountry(String country) {
        this.country.set(country);
    }

    //address
    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    //phone_number
    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    //accommodation_date
    public Date getAccommodationDate() {
        return accommodationDate.get();
    }

    public SimpleObjectProperty<Date> accommodationDateProperty() {
        return accommodationDate;
    }

    public void setAccommodationDate(Date accommodationDate) {
        this.accommodationDate.set(accommodationDate);
    }

    //eviction_date
    public Date getEvictionDate() {
        return evictionDate.get();
    }

    public SimpleObjectProperty<Date> evictionDateProperty() {
        return evictionDate;
    }

    public void setEvictionDate(Date evictionDate) {
        this.evictionDate.set(evictionDate);
    }

    //is_blocked
    public boolean getIsBlocked() {
        return isBlocked.get();
    }

    public BooleanProperty isBlockedProperty() {
        return isBlocked;
    }

    public void setIsBlocked(boolean isBlocked) {
        this.isBlocked.set(isBlocked);
    }

    //id_room
    public int getResidentRoomId() {
        return residentRoomId.get();
    }

    public IntegerProperty residentRoomIdProperty() {
        return residentRoomId;
    }

    public void setResidentRoomId(int residentRoomId) {
        this.residentRoomId.set(residentRoomId);
    }
}

