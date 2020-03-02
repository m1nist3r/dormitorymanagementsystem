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
        @Language("MySQL") String selectStmt = "SELECT * FROM room ORDER BY Resident_amount ";
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
        @Language("MySQL") String selectStmt = "SELECT idRoom, Room_status, Room_type, Resident_amount FROM room WHERE Resident_amount < Room_type";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRoom = DBUtil.dbExecutePreparedQuery(selectStmt, "");

            ObservableList<Room> resList = FXCollections.observableArrayList();
            while (rsRoom.next()) {
                Room room = new Room();
                room.setRoomId(rsRoom.getInt("IDROOM"));
                room.setRoomStatus(rsRoom.getString("ROOM_STATUS"));
                room.setRoomType(rsRoom.getInt("ROOM_TYPE"));
                room.setResidentAmount(rsRoom.getInt("RESIDENT_AMOUNT"));
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

    public static void updateRoom(int idRoom, int numRes, int room_type)
            throws IndexOutOfBoundsException, SQLException {
        String room_status;
        switch (numRes) {
            case 0:
                room_status = "1 / " + room_type;
                break;
            case 1:
                if (numRes > room_type)
                    throw new IndexOutOfBoundsException("Wrong amount of resident for that type of room");
                room_status = "2 /" + room_type;
                break;
            case 2:
                if (numRes > room_type)
                    throw new IndexOutOfBoundsException("Wrong amount of resident for that type of room");
                room_status = "Full";
                break;
            default:
                throw new IndexOutOfBoundsException("Max amount of residents");
        }
        @Language("MySQL") String updateStmt = "UPDATE room" +
                " SET Resident_amount = Resident_amount + 1, Room_status ='" + room_status + "'" +
                " WHERE idRoom = ?";

        try {
            DBUtil.dbExecutePreparedUpdate(updateStmt, idRoom);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }
}
