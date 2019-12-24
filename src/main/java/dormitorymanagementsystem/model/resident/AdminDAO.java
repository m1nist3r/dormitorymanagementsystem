package dormitorymanagementsystem.model.resident;

import dormitorymanagementsystem.util.DBUtil;

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

    public static Admin searchAdminByPesPas(String pesel, String password) throws SQLException {
        //Execute SELECT statement
        String selectStmt = "SELECT idAdmin, Password, PESEL, idAdmin_type FROM adminstrator WHERE PESEL = ? AND Password = ?";

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
}

