package dormitorymanagementsystem.model.room;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Room {

    //Declare Room Properties
    private IntegerProperty roomId;
    private IntegerProperty roomType;
    private IntegerProperty dormitoryNumber;
    private StringProperty roomStatus;
    private IntegerProperty residentAmount;
    private IntegerProperty floor;
    private StringProperty remarks;

    Room() {
        this.roomId = new SimpleIntegerProperty();
        this.roomType = new SimpleIntegerProperty();
        this.dormitoryNumber = new SimpleIntegerProperty();
        this.roomStatus = new SimpleStringProperty();
        this.residentAmount = new SimpleIntegerProperty();
        this.floor = new SimpleIntegerProperty();
        this.remarks = new SimpleStringProperty();
    }

    public int getRoomId() {
        return roomId.get();
    }

    public void setRoomId(int roomId) {
        this.roomId.set(roomId);
    }

    public IntegerProperty roomIdProperty() {
        return roomId;
    }

    public int getRoomType() {
        return roomType.get();
    }

    public void setRoomType(int roomType) {
        this.roomType.set(roomType);
    }

    public IntegerProperty roomTypeProperty() {
        return roomType;
    }

    public int getDormitoryNumber() {
        return dormitoryNumber.get();
    }

    public void setDormitoryNumber(int dormitoryNumber) {
        this.dormitoryNumber.set(dormitoryNumber);
    }

    public IntegerProperty dormitoryNumberProperty() {
        return dormitoryNumber;
    }

    public String getRoomStatus() {
        return roomStatus.get();
    }

    public void setRoomStatus(String roomStatus) {
        this.roomStatus.set(roomStatus);
    }

    public StringProperty roomStatusProperty() {
        return roomStatus;
    }

    public int getResidentAmount() {
        return residentAmount.get();
    }

    public void setResidentAmount(int residentAmount) {
        this.residentAmount.set(residentAmount);
    }

    public IntegerProperty residentAmountProperty() {
        return residentAmount;
    }

    public int getFloor() {
        return floor.get();
    }

    public void setFloor(int floor) {
        this.floor.set(floor);
    }

    public IntegerProperty floorProperty() {
        return floor;
    }

    public String getRemarks() {
        return remarks.get();
    }

    public void setRemarks(String remarks) {
        this.remarks.set(remarks);
    }

    public StringProperty remarksProperty() {
        return remarks;
    }
}
