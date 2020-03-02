package dormitorymanagementsystem.model.admin;

import dormitorymanagementsystem.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {

    private static Admin getAdminById(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        while (rs.next()) {
            admin.setIdAdmin(rs.getInt("IDADMIN"));
            admin.setPassword(rs.getString("PASSWORD"));
            admin.setPesel(rs.getString("PESEL"));
            admin.setIdAdminType(rs.getInt("IDADMIN_TYPE"));
        }
        return admin;
    }

    @NotNull
    public static Admin searchAdminByPesPas(String pesel, String password) throws SQLException {
        //Execute SELECT statement
        @Language("MySQL") String selectStmt = "SELECT idAdmin, Password, PESEL, idAdmin_type" +
                " FROM adminstrator" +
                " WHERE PESEL = ? AND Password = ?";

        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRes = DBUtil.dbExecutePreparedQuery(selectStmt, pesel, password);
            //Send ResultSet to the getResidentFromResultSet method and get resident object
            //Return resident object

            return getAdminById(rsRes);
        } catch (SQLException e) {
            System.out.println("While searching an residents an error occurred: " + e.getMessage());
            //Return exception
            throw e;
        }
    }

    public static ObservableList<Admin> searchAdminTypes() throws SQLException {
        //Execute SELECT statement
        @Language("MySQL") String selectStmt = "SELECT idAdmin_type, Description FROM admin_type";

        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRes = DBUtil.dbExecutePreparedQuery(selectStmt, "");
            //Send ResultSet to the getResidentFromResultSet method and get resident object
            //Return resident object

            return getAdminTypes(rsRes);
        } catch (SQLException e) {
            System.out.println("While searching an residents an error occurred: " + e.getMessage());
            //Return exception
            throw e;
        }
    }

    private static ObservableList<Admin> getAdminTypes(ResultSet rs) throws SQLException {
        ObservableList<Admin> adminObservableList = FXCollections.observableArrayList();
        while (rs.next()) {
            Admin admin = new Admin();
            admin.setIdAdminType(rs.getInt("IDADMIN_TYPE"));
            admin.setIdTypeName(rs.getString("DESCRIPTION"));
            adminObservableList.add(admin);
        }
        return adminObservableList;
    }

    public static boolean insertAdmin(Admin admin) throws SQLException {

        @Language("MySQL") String insertStmt = "" +
                "INSERT INTO adminstrator(idAdmin, idAdmin_type, Name, Surname, PESEL, Password, Email, DOB, Phone_number, Last_login, Last_logout, Date_of_creation)" +
                " VALUE (null, ?, ?, ?, ?, ?, ?, ?, ?, null, null, current_timestamp)";
        try {
            DBUtil.dbExecutePreparedAdminInsert(insertStmt, admin);
            return true;
        } catch (SQLException e) {
            System.out.print("Error occurred while INSERT Operation: " + e);
            throw e;
        }
    }
}

