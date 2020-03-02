package dormitorymanagementsystem.model.resident;

import dormitorymanagementsystem.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Pair;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResidentDAO {
    @NotNull
    public static ObservableList<Resident> searchResidents(String resFLName)
            throws SQLException {
        //Declare a SELECT statement
        @Language("MySQL") String selectStmt = "SELECT * FROM resident WHERE first_name LIKE ? OR last_name LIKE ?";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRes = DBUtil.dbExecutePreparedQueryFind(selectStmt, resFLName, resFLName);

            //Send ResultSet to the getResidentFromResultSet method and get resident object
            //Return resident object
            return getResidentList(rsRes);
        } catch (SQLException e) {
            System.out.println("While searching an residents an error occurred: " + e.getMessage());
            //Return exception
            throw e;
        }
    }

    @NotNull
    public static <T extends Resident> ObservableList<T> searchResidentById(int id, int resident_type)
            throws SQLException {
        //Declare a SELECT statement
        @Language("MySQL") String selectStmt = "SELECT r.*, t.Type, \n" +
                "        GROUP_CONCAT(rv.value SEPARATOR ', ') as resident_detail_value\n" +
                "                FROM resident r \n" +
                "        INNER JOIN type_of_resident t ON r.id_type = t.idType\n" +
                "        INNER JOIN resident_type_fields rt ON r.id_type = rt.idType\n" +
                "        INNER JOIN resident_values_fields rv ON rt.idField = rv.idField" +
                "        WHERE rv.idResident = ? AND r.id_resident = ?";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRes = DBUtil.dbExecutePreparedQuery(selectStmt, id, id);
            //Send ResultSet to the getResidentFromResultSet method and get resident object
            //Return resident object
            return getResidentById(rsRes, resident_type);
        } catch (SQLException e) {
            System.out.println("While searching an residents an error occurred: " + e.getMessage());
            //Return exception
            throw e;
        }
    }

    @NotNull
    public static ObservableList<Resident> searchResidents()
            throws SQLException {
        //Declare a SELECT statement
        @Language("MySQL") String selectStmt = "SELECT * FROM resident";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRes = DBUtil.dbExecutePreparedQuery(selectStmt, "");

            //Send ResultSet to the getResidentFromResultSet method and get resident object
            //Return resident object
            return getResidentList(rsRes);
        } catch (SQLException e) {
            System.out.println("While searching an residents an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    @NotNull
    private static <T extends Resident> T addResidentToObservableList(@NotNull Resident res, @NotNull ResultSet rs)
            throws SQLException {
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

        return ((T) res);
    }

    @NotNull
    private static ObservableList<Resident> getResidentList(@NotNull ResultSet rs)
            throws SQLException {
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
    public static Resident searchResidentById(int id)
            throws SQLException {
        //Declare a observable List which comprises of Resident objects
        @Language("MySQL") String selectStmt = "SELECT * FROM resident WHERE id_resident = ?";
        ObservableList<Resident> resList = FXCollections.observableArrayList();
        Resident res = new Resident();
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRes = DBUtil.dbExecutePreparedQuery(selectStmt, id);
            //Send ResultSet to the getResidentFromResultSet method and get resident object
            //Return resident object
            Resident resident = new Resident();
            rsRes.next();
            addResidentToObservableList(resident, rsRes);
            resident.setPayment_fee(rsRes.getDouble("PAYMENT_FEE"));
            return resident;

        } catch (SQLException e) {
            System.out.println("While searching an residents an error occurred: " + e.getMessage());
            //Return exception
            throw e;
        }
    }

    @NotNull
    private static <T> ObservableList<T> getResidentById(@NotNull ResultSet rs, int resident_type)
            throws SQLException {
        //Declare a observable List which comprises of Resident objects
        ObservableList<T> resList = FXCollections.observableArrayList();
        switch (resident_type) {
            case 1: {
                while (rs.next()) {
                    ResidentStudent res = new ResidentStudent();
                    String[] subStr = (rs.getString("RESIDENT_DETAIL_VALUE")).split(", ");
                    res.setStudentNumber(subStr[0]);
                    res.setDepartment(subStr[1]);
                    res.setYearOfStudy(subStr[2]);
                    res.setAcademicYear(subStr[3]);
                    res.setStudentPaymentAccount(subStr[4]);
                    res.setPayment_fee(rs.getDouble("PAYMENT_FEE"));
                    //Add resident to the ObservableList
                    resList.add((T) addResidentToObservableList(res, rs));
                }
                break;
            }
            case 2: {
                while (rs.next()) {
                    ResidentErasmus res = new ResidentErasmus();
                    String[] subStr = (rs.getString("RESIDENT_DETAIL_VALUE")).split(", ");
                    res.setOriginUniversity(subStr[0]);
                    res.setErasmusNumber(subStr[1]);

                    //Add resident to the ObservableList
                    resList.add((T) addResidentToObservableList(res, rs));
                }
                break;
            }
            case 3: {
                while (rs.next()) {
                    ResidentForeignStudent res = new ResidentForeignStudent();
                    String[] subStr = (rs.getString("RESIDENT_DETAIL_VALUE")).split(", ");
                    res.setOriginUniversity(subStr[0]);

                    //Add resident to the ObservableList
                    resList.add((T) addResidentToObservableList(res, rs));
                }
                break;
            }
            case 4: {
                while (rs.next()) {
                    ResidentGuest res = new ResidentGuest();
                    String[] subStr = (rs.getString("RESIDENT_DETAIL_VALUE")).split(", ");
                    res.setIsStudent(subStr[0]);
                    res.setIsPartTimeStudent(subStr[1]);

                    //Add resident to the ObservableList
                    resList.add((T) addResidentToObservableList(res, rs));
                }
                break;
            }
        }
        return resList;
    }

    @NotNull
    public static ObservableList<Pair<Integer, String>> getResidentTypes()
            throws SQLException {
        //Declare a SELECT statement
        @Language("MySQL") String selectStmt = "SELECT idType, Type FROM type_of_resident";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRes = DBUtil.dbExecutePreparedQuery(selectStmt, "");

            ObservableList<Pair<Integer, String>> pairs = FXCollections.observableArrayList();

            while (rsRes.next()) {
                pairs.add(new Pair<>(rsRes.getInt("IDTYPE"), rsRes.getString("TYPE")));
            }
            //Return resident object
            return pairs;
        } catch (SQLException e) {
            System.out.println("While searching an residents an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    public static boolean updateResident(int resId, int residentTypeId, int columnNameId, String newValue)
            throws SQLException {
        ArrayList<String> arrayList;
        String columnName;
        @Language("MySQL") String updateStmt;
        @Language("MySQL") String getColumnNamesStmt =
                "SELECT `COLUMN_NAME` FROM `INFORMATION_SCHEMA`.`COLUMNS` " +
                        "WHERE `TABLE_SCHEMA`= DATABASE() AND `TABLE_NAME`= 'resident' " +
                        "UNION " +
                        "SELECT `field_name` FROM `resident_type_fields` " +
                        "WHERE idType = ?";
        try {
            System.out.println(residentTypeId);
            ResultSet resultSet = DBUtil.dbExecutePreparedQuery(getColumnNamesStmt, residentTypeId);
            arrayList = new ArrayList<>();
            while (resultSet.next()) {
                arrayList.add(resultSet.getString("COLUMN_NAME"));
            }
        } catch (SQLException e) {
            System.out.print("Error occurred while SELECT Operation: " + e);
            throw e;
        }
        columnName = arrayList.get(columnNameId);
        if (columnNameId < 17) {
            //Declare a UPDATE statement
            updateStmt = "UPDATE resident SET " + columnName + " = '" + newValue + "'  WHERE id_resident = ? ";
            //Execute UPDATE operation
            try {
                DBUtil.dbExecutePreparedUpdate(updateStmt, resId);
                return true;
            } catch (SQLException e) {
                System.out.print("Error occurred while UPDATE Operation: " + e);
                return false;
            }
        } else {

            updateStmt =
                    "UPDATE resident_values_fields AS rvf " +
                            "INNER JOIN resident_type_fields rtf ON rvf.idField = rtf.idField " +
                            "SET rvf.value = 91956" +
                            " WHERE rvf.idResident = ? AND rtf.field_name = ?";
            //Execute UPDATE operation
            try {
                DBUtil.dbExecutePreparedUpdate(updateStmt, resId, columnName);
                return true;
            } catch (SQLException e) {
                System.out.print("Error occurred while UPDATE Operation: " + e);
                return false;
            }
        }
    }

    public static void insertResident(Resident res)
            throws SQLException {
        //Declare a INSERT statement
        @Language("MySQL") String insertResAddStmt = "";
        @Language("MySQL") String insertResidentStmt = "INSERT INTO `resident`(`id_resident`, `id_type`, `id_room`, `first_name`," +
                " `last_name`, `pesel`, `gender`, `dob`, `mothers_name`, `fathers_name`, `email`, `country`, `address`," +
                " `phone_number`, `accommodation_date`, `eviction_date`, `is_blocked`)" +
                " VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, current_timestamp, ?, 0);";
        if (res instanceof ResidentStudent) {
            insertResAddStmt = " INSERT INTO `resident_values_fields`(`idValue`, `idField`, `idResident`, `value`)" +
                    " VALUES (null, 1, (SELECT id_resident FROM resident ORDER BY id_resident DESC LIMIT 1), ?)," +
                    " (null, 2, (SELECT id_resident FROM resident ORDER BY id_resident DESC LIMIT 1), ?)," +
                    " (null, 3, (SELECT id_resident FROM resident ORDER BY id_resident DESC LIMIT 1), ?)," +
                    " (null, 4, (SELECT id_resident FROM resident ORDER BY id_resident DESC LIMIT 1), ?)," +
                    " (null, 5, (SELECT id_resident FROM resident ORDER BY id_resident DESC LIMIT 1), ?);";
        } else if (res instanceof ResidentErasmus) {
            insertResAddStmt = " INSERT INTO `resident_values_fields`(`idValue`, `idField`, `idResident`, `value`)" +
                    " VALUES (null, 6, (SELECT id_resident FROM resident ORDER BY id_resident DESC LIMIT 1), ?)," +
                    " (null, 7, (SELECT id_resident FROM resident ORDER BY id_resident DESC LIMIT 1), ?);";
        } else if (res instanceof ResidentForeignStudent) {
            insertResAddStmt = " INSERT INTO `resident_values_fields`(`idValue`, `idField`, `idResident`, `value`)" +
                    " VALUES (null, 8, (SELECT id_resident FROM resident ORDER BY id_resident DESC LIMIT 1), ?);";
        } else if (res instanceof ResidentGuest) {
            insertResAddStmt = "INSERT INTO `resident_values_fields`(`idValue`, `idField`, `idResident`, `value`)" +
                    " VALUES (null, 9, (SELECT id_resident FROM resident ORDER BY id_resident DESC LIMIT 1), ?)," +
                    " (null, 10, (SELECT id_resident FROM resident ORDER BY id_resident DESC LIMIT 1), ?);";
        }
        //Execute INSERT operation
        try {
            DBUtil.dbExecutePreparedResidentInsert(insertResidentStmt, insertResAddStmt, res);
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }


    @NotNull
    public static ObservableList<String> getResidentFields(int resident_type) throws SQLException {
        //Declare a SELECT statement
        @Language("MySQL") String selectStmt = "SELECT field_name FROM resident_type_fields WHERE  idType = ?";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRes = DBUtil.dbExecutePreparedQuery(selectStmt, resident_type);

            ObservableList<String> fieldsName = FXCollections.observableArrayList();

            while (rsRes.next()) {
                fieldsName.add(rsRes.getString("FIELD_NAME"));
            }
            //Return resident object
            return fieldsName;
        } catch (SQLException e) {
            System.out.println("While searching an residents an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    public static void deleteResWithId(String resId) throws SQLException {
        //Declare a DELETE statement
        @Language("MySQL") String updateStmt = "DELETE FROM resident WHERE id_resident = ?";
        //Execute UPDATE operation
        try {
            DBUtil.dbExecutePreparedQuery(updateStmt, resId);
        } catch (SQLException e) {
            System.out.print("Error occurred while DELETE Operation: " + e);
            throw e;
        }
    }
}
