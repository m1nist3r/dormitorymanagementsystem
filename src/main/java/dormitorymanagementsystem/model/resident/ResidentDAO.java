package dormitorymanagementsystem.model.resident;

import dormitorymanagementsystem.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ResidentDAO {

    //*******************************
    //SELECT specific Residents (Search by First + Last name)
    //*******************************
    @NotNull
    public static ObservableList<Resident> searchResidents(String resFLName) throws SQLException {
        //Declare a SELECT statement
        String selectStmt = "SELECT * FROM resident WHERE first_name LIKE ? OR last_name LIKE ?";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRes = DBUtil.dbExecutePreparedQuery(selectStmt, resFLName, resFLName);

            //Send ResultSet to the getResidentFromResultSet method and get resident object
            //Return resident object
            return getResidentList(rsRes);
        } catch (SQLException e) {
            System.out.println("While searching an residents an error occurred: " + e.getMessage());
            //Return exception
            throw e;
        }
    }

    //                "        GROUP_CONCAT(rt.field_name SEPARATOR ', ') as resident_detail_field,\n" +
    @NotNull
    public static ObservableList<Resident> searchResidentById(int id, int resident_type) throws SQLException {
        //Declare a SELECT statement
        String selectStmt = "SELECT r.*, t.Type, \n" +
                "        GROUP_CONCAT(rv.value SEPARATOR ', ') as resident_detail_value\n" +
                "                FROM resident r \n" +
                "        INNER JOIN type_of_resident t ON r.id_type = t.idType\n" +
                "        INNER JOIN resident_type_fields rt ON r.id_type = rt.idType\n" +
                "        INNER JOIN resident_values_fields rv ON rt.idField = rv.idField WHERE r.id_resident = ?";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRes = DBUtil.dbExecutePreparedQuery(selectStmt, id);

            //Send ResultSet to the getResidentFromResultSet method and get resident object
            //Return resident object
            return getResidentById(rsRes, resident_type);
        } catch (SQLException e) {
            System.out.println("While searching an residents an error occurred: " + e.getMessage());
            //Return exception
            throw e;
        }
    }

    //*******************************
    //SELECT all Residents
    //*******************************
    @NotNull
    public static ObservableList<Resident> searchResidents() throws SQLException {
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

    private static Resident addResidentToObservableList(Resident res, ResultSet rs) throws SQLException {
        res.setResidentId(rs.getInt("ID_RESIDENT"));
        res.setResidentTypeId(rs.getInt("ID_TYPE"));
        res.setResidentRoomId(rs.getInt("ID_ROOM"));
        res.setFirstName(rs.getString("FIRST_NAME"));
        res.setLastName(rs.getString("LAST_NAME"));
        res.setPesel(rs.getString("PESEL"));
        res.setGender(rs.getString("GENDER"));
        res.setDobDate(rs.getDate("DOB"));
        res.setMotherName(rs.getString("MOTHERS_NAME"));
        res.setFatherName(rs.getString("FATHERS_NAME"));
        res.setEmail(rs.getString("EMAIL"));
        res.setCountry(rs.getString("COUNTRY"));
        res.setAddress(rs.getString("ADDRESS"));
        res.setPhoneNumber(rs.getString("PHONE_NUMBER"));
        res.setAccommodationDate(rs.getDate("ACCOMMODATION_DATE"));
        res.setEvictionDate(rs.getDate("EVICTION_DATE"));
        res.setIsBlocked(rs.getBoolean("IS_BLOCKED"));

        return res;
    }

    //Use ResultSet from DB as parameter and set Resident Object's attributes and return resident object.
    @NotNull
    private static ObservableList<Resident> getResidentList(@NotNull ResultSet rs) throws SQLException {
        //Declare a observable List which comprises of Resident objects
        ObservableList<Resident> resList = FXCollections.observableArrayList();

        while (rs.next()) {
            Resident res = new Resident();
            //Add resident to the ObservableList
            resList.add(addResidentToObservableList(res, rs));
        }
        return resList;
    }

    @NotNull
    private static ObservableList<Resident> getResidentById(@NotNull ResultSet rs, int resident_type) throws SQLException {
        //Declare a observable List which comprises of Resident objects
        ObservableList<Resident> resList = FXCollections.observableArrayList();
        switch (resident_type) {
            case 1: {
                while (rs.next()) {
                    Resident res = new Resident();
                    String[] subStr = (rs.getString("RESIDENT_DETAIL_VALUE")).split(", ");
                    res.setStudentNumber(subStr[0]);
                    res.setDepartment(subStr[1]);
                    res.setYearOfStudy(subStr[2]);
                    res.setAcademicYear(subStr[3]);
                    res.setStudentPaymentAccount(subStr[4]);

                    //Add resident to the ObservableList
                    resList.add(addResidentToObservableList(res, rs));
                }
                break;
            }
        }
        return resList;
    }


    //*************************************
    //UPDATE a resident's details
    //*************************************
    public static void updateResident(String resId, String columnName, String newValue) throws SQLException {
        //Declare a UPDATE statement
        String updateStmt = "UPDATE resident SET " + columnName + " = " + newValue + "  WHERE " + resId + " = id_resident";

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
    public static void deleteResWithId(String resId) throws SQLException {
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
