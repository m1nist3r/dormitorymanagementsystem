package dormitorymanagementsystem.model.room;

import dormitorymanagementsystem.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomDAO {

    @NotNull
    public static ObservableList<Room> searchRooms() throws SQLException {
        //Declare a SELECT statement
        @Language("MySQL") String selectStmt = "SELECT * FROM room";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRoom = DBUtil.dbExecutePreparedQuery(selectStmt, "");

            //Send ResultSet to the getRoomList method and get room object
            //Return room object
            return getRoomList(rsRoom);
        } catch (SQLException e) {
            System.out.println("While searching an residents an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    @NotNull
    public static ObservableList<Room> searchRoomById(int room_id) throws SQLException {
        //Declare a SELECT statement
        @Language("MySQL") String selectStmt = "SELECT * FROM room WHERE idRoom = ?";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRoom = DBUtil.dbExecutePreparedQuery(selectStmt, room_id);

            //Send ResultSet to the getRoomList method and get room object
            //Return room object
            return getRoomList(rsRoom);
        } catch (SQLException e) {
            System.out.println("While searching an residents an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    @NotNull
    public static ObservableList<Room> searchFreeRoom() throws SQLException {
        //Declare a SELECT statement
        @Language("MySQL") String selectStmt = "SELECT idRoom, Room_status FROM room WHERE Resident_amount < Room_type";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRoom = DBUtil.dbExecutePreparedQuery(selectStmt, "");

            ObservableList<Room> resList = FXCollections.observableArrayList();
            while (rsRoom.next()) {
                Room room = new Room();
                room.setRoomId(rsRoom.getInt("IDROOM"));
                room.setRoomStatus(rsRoom.getString("ROOM_STATUS"));
                resList.add(room);
            }
            //Return room objectreturn
            return resList;
        } catch (SQLException e) {
            System.out.println("While searching an residents an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    //Use ResultSet from DB as parameter and set Room Object's attributes and return room object.
    @NotNull
    private static ObservableList<Room> getRoomList(@NotNull ResultSet rs) throws SQLException {
        //Declare a observable List which comprises of Room objects
        ObservableList<Room> resList = FXCollections.observableArrayList();

        while (rs.next()) {
            Room room = new Room();
            //Add room to the ObservableList
            resList.add(addRoomToObservableList(room, rs));
        }
        return resList;
    }

    @NotNull
    private static Room addRoomToObservableList(@NotNull Room room, @NotNull ResultSet rs) throws SQLException {
        room.setRoomId(rs.getInt("IDROOM"));
        room.setRoomType(rs.getInt("ROOM_TYPE"));
        room.setDormitoryNumber(rs.getInt("DORMITORY_NUMBER"));
        room.setRoomStatus(rs.getString("ROOM_STATUS"));
        room.setResidentAmount(rs.getInt("RESIDENT_AMOUNT"));
        room.setFloor(rs.getInt("FLOOR"));
        room.setRemarks(rs.getString("REMARKS"));

        return room;
    }
}
