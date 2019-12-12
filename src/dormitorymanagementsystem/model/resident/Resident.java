package dormitorymanagementsystem.model.resident;

import javafx.beans.property.*;

import java.util.Date;

public class Resident {
    //Declare Residents Table Columns
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
    private SimpleObjectProperty<Date> evictionDate;
    private BooleanProperty isBlocked;

    //Constructor
    Resident() {
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
    }

    //resident_id
    public int getResidentTypeId() {
        return residentTypeId.get();
    }

    public IntegerProperty residentTypeIdProperty() {
        return residentTypeId;
    }

    void setResidentTypeId(int residentTypeId) {
        this.residentTypeId.set(residentTypeId);
    }

    //first_name
    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    //last_name
    public String getLastName() {
        return lastName.get();
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }

    void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    //pesel
    public String getPesel() {
        return pesel.get();
    }

    public StringProperty peselProperty() {
        return pesel;
    }

    void setPesel(String pesel) {
        this.pesel.set(pesel);
    }

    //gender
    public String getGender() {
        return gender.get();
    }

    public StringProperty genderProperty() {
        return gender;
    }

    void setGender(String gender) {
        this.gender.set(gender);
    }

    //date of birth
    public Date getDobDate() {
        return dobDate.get();
    }

    public SimpleObjectProperty<Date> dobDateProperty() {
        return dobDate;
    }

    void setDobDate(Date dobDate) {
        this.dobDate.set(dobDate);
    }

    //mother_name
    public String getMotherName() {
        return motherName.get();
    }

    public StringProperty motherNameProperty() {
        return motherName;
    }

    void setMotherName(String motherName) {
        this.motherName.set(motherName);
    }

    //father_name
    public String getFatherName() {
        return fatherName.get();
    }

    public StringProperty fatherNameProperty() {
        return fatherName;
    }

    void setFatherName(String fatherName) {
        this.fatherName.set(fatherName);
    }

    //email
    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    void setEmail(String email) {
        this.email.set(email);
    }

    //country
    public String getCountry() {
        return country.get();
    }

    public StringProperty countryProperty() {
        return country;
    }

    void setCountry(String country) {
        this.country.set(country);
    }

    //address
    public String getAddress() {
        return address.get();
    }

    public StringProperty addressProperty() {
        return address;
    }

    void setAddress(String address) {
        this.address.set(address);
    }

    //phone_number
    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public StringProperty phoneNumberProperty() {
        return phoneNumber;
    }

    void setPhoneNumber(String phoneNumber) {
        this.phoneNumber.set(phoneNumber);
    }

    //accommodation_date
    public Date getAccommodationDate() {
        return accommodationDate.get();
    }

    public SimpleObjectProperty<Date> accommodationDateProperty() {
        return accommodationDate;
    }

    void setAccommodationDate(Date accommodationDate) {
        this.accommodationDate.set(accommodationDate);
    }

    //eviction_date
    public Date getEvictionDate() {
        return evictionDate.get();
    }

    public SimpleObjectProperty<Date> evictionDateProperty() {
        return evictionDate;
    }

    void setEvictionDate(Date evictionDate) {
        this.evictionDate.set(evictionDate);
    }

    //is_blocked
    public boolean getIsBlocked() {
        return isBlocked.get();
    }

    public BooleanProperty isBlockedProperty() {
        return isBlocked;
    }

    void setIsBlocked(boolean isBlocked) {
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

    public int getResidentId() {
        return residentId.get();
    }

    public void setResidentId(int residentId) {
        this.residentId.set(residentId);
    }

    public IntegerProperty residentIdProperty() {
        return residentId;
    }
}

