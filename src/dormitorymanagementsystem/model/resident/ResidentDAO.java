package dormitorymanagementsystem.model.resident;

import dormitorymanagementsystem.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ResidentDAO {

    //*******************************
    //SELECT specific Residents (Search by First + Last name)
    //*******************************
    public static ObservableList<Resident> searchResidents(String resFLName) throws SQLException {
        return searchResidents();
    }

    //*******************************
    //SELECT all Residents
    //*******************************
    private static ObservableList<Resident> searchResidents() throws SQLException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM resident";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRes = DBUtil.dbExecuteQuery(selectStmt);

            //Send ResultSet to the getResidentFromResultSet method and get resident object
            //Return resident object
            return getResidentList(rsRes);
        } catch (SQLException e) {
            System.out.println("While searching an residents an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    //Use ResultSet from DB as parameter and set Resident Object's attributes and return resident object.
    private static ObservableList<Resident> getResidentList(ResultSet rs) throws SQLException {
    //Declare a observable List which comprises of Resident objects
        ObservableList<Resident> resList = FXCollections.observableArrayList();

        while (rs.next()) {
            Resident res = new Resident();
            res.setResidentTypeId(rs.getInt("ID_TYPE"));
            res.setResidentTypeId(rs.getInt("ID_ROOM"));
            res.setFatherName(rs.getString("FIRST_NAME"));
            res.setLastName(rs.getString("LAST_NAME"));
            res.setPassportNumber(rs.getString("PASSPORT_NUMBER"));
            res.setPesel(rs.getString("PESEL"));
            res.setGender(rs.getString("GENDER"));
            res.setDobDate(rs.getDate("DOB"));
            res.setMotherName(rs.getString("MOTHER_NAME"));
            res.setFatherName(rs.getString("FATHER_NAME"));
            res.setEmail(rs.getString("EMAIL"));
            res.setCountry(rs.getString("COUNTRY"));
            res.setAddress(rs.getString("ADDRESS"));
            res.setPhoneNumber(rs.getString("PHONE_NUMBER"));
            res.setAccommodationDate(rs.getDate("ACCOMMODATION_DATE"));
            res.setEvictionDate(rs.getDate("EVICTION_DATE"));
            res.setIsBlocked(rs.getBoolean("IS_BLOCKED"));
            //Add resident to the ObservableList
            resList.add(res);
        }
        return resList;
    }

    //*************************************
    //UPDATE a resident's details
    //*************************************
    public static void updateResident (String resId, String columnName, String newValue) throws SQLException  {
        //Declare a UPDATE statement
        String updateStmt = "UPDATE resident SET " + columnName + " = " +newValue+ "  WHERE " + resId + " = id_resident";

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while UPDATE Operation: " + e);
            throw e;
        }
    }

    //*************************************
    //DELETE a resident
    //*************************************
    public static void deleteResWithId (String resId) throws SQLException {
        //Declare a DELETE statement
        String updateStmt = "DELETE FROM resident WHERE id_resident = " + resId + "";

        //Execute UPDATE operation
        try {
            DBUtil.dbExecuteUpdate(updateStmt);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}
