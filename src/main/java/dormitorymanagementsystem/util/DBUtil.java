package dormitorymanagementsystem.util;

import dormitorymanagementsystem.model.admin.Admin;
import dormitorymanagementsystem.model.archive.Archive;
import dormitorymanagementsystem.model.payment.Payment;
import dormitorymanagementsystem.model.resident.*;
import org.jetbrains.annotations.NotNull;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class DBUtil {

    //Connection String
    private static final String connStr = "jdbc:mysql://localhost:3306/dormitory";
    private static final String connUser = "root";
    private static final String connPass = "";
    //Connection
    private static Connection conn = null;

    //Connect to DB
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connStr, connUser, connPass);
    }

    //DB Execute Query Operation
    public static ResultSet dbExecutePreparedQuery(String queryStmt, @NotNull String... searchedCriteria) throws SQLException {
        //Declare statement, resultSet and CachedResultSet as null
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CachedRowSet crs;
        RowSetFactory factory;

        try {

            //Connect to DB (Establish Mysql Connection)
            conn = getConnection();
            System.out.println("Select statement: " + queryStmt + "\n");

            //Create prepared statement
            preparedStatement = conn.prepareStatement(queryStmt);

            //Setting preparedStatement with searchedCriteria
            if (!searchedCriteria[0].equals("")) {
                for (int i = 0; i < searchedCriteria.length; i++) {
                    preparedStatement.setString(i + 1, searchedCriteria[i]);
                }
            }

            //Execute select (query) operation
            resultSet = preparedStatement.executeQuery();
            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
            factory = RowSetProvider.newFactory();
            crs = factory.createCachedRowSet();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e.getMessage());

            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (preparedStatement != null) {
                //Close Statement
                preparedStatement.close();
            }
            //Close connection
            conn.close();
        }
        //Return CachedRowSet
        return crs;
    }

    public static ResultSet dbExecutePreparedQuery(String queryStmt, @NotNull int... searchedCriteria) throws SQLException {
        //Declare statement, resultSet and CachedResultSet as null
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CachedRowSet crs;
        RowSetFactory factory;

        try {

            //Connect to DB (Establish Mysql Connection)
            conn = getConnection();
            System.out.println("Select statement: " + queryStmt + "\n");
            //Create prepared statement
            preparedStatement = conn.prepareStatement(queryStmt);

            //Setting preparedStatement with searchedCriteria
            if (searchedCriteria.length > 1) {
                for (int i = 0; i < searchedCriteria.length; i++) {
                    preparedStatement.setInt(i + 1, searchedCriteria[i]);
                }
            } else preparedStatement.setInt(1, searchedCriteria[0]);

            //Execute select (query) operation
            resultSet = preparedStatement.executeQuery();

            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
            factory = RowSetProvider.newFactory();
            crs = factory.createCachedRowSet();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e.getMessage());
            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (preparedStatement != null) {
                //Close Statement
                preparedStatement.close();
            }
            //Close connection
            conn.close();
        }
        //Return CachedRowSet
        return crs;
    }

    public static ResultSet dbExecutePreparedQueryFind(String queryStmt, @NotNull String... searchedCriteria) throws SQLException {
        //Declare statement, resultSet and CachedResultSet as null
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        CachedRowSet crs;
        RowSetFactory factory;

        try {

            //Connect to DB (Establish Mysql Connection)
            conn = getConnection();
            System.out.println("Select statement: " + queryStmt + "\n");

            //Create prepared statement
            preparedStatement = conn.prepareStatement(queryStmt);

            //Setting preparedStatement with searchedCriteria
            if (!searchedCriteria[0].equals("")) {
                for (int i = 0; i < searchedCriteria.length; i++) {
                    preparedStatement.setString(i + 1, searchedCriteria[i] + "%");
                }
            }

            //Execute select (query) operation
            resultSet = preparedStatement.executeQuery();
            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
            factory = RowSetProvider.newFactory();
            crs = factory.createCachedRowSet();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e.getMessage());

            throw e;
        } finally {
            if (resultSet != null) {
                //Close resultSet
                resultSet.close();
            }
            if (preparedStatement != null) {
                //Close Statement
                preparedStatement.close();
            }
            //Close connection
            conn.close();
        }
        //Return CachedRowSet
        return crs;
    }

    //DB Execute Update Operation
    public static void dbExecutePreparedUpdate(String sqlStmt, int id, String columnName) throws SQLException {
        //Declare statement as null
        PreparedStatement preparedStatement = null;
        try {
            //Connect to DB (Establish Mysql Connection)
            conn = getConnection();
            System.out.println("Update statement: " + sqlStmt + "\n");
            //Create Statement
            preparedStatement = conn.prepareStatement(sqlStmt);

            //Populate with Resident Id
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, columnName);

            //Run executeUpdate operation with given sql statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (preparedStatement != null) {
                //Close Statement
                preparedStatement.close();
            }
            //Close connection
            conn.close();
        }
    }

    public static void dbExecutePreparedUpdate(String sqlStmt, int resId) throws SQLException {
        //Declare statement as null
        PreparedStatement preparedStatement = null;
        try {
            //Connect to DB (Establish Mysql Connection)
            conn = getConnection();
            System.out.println("Select statement: " + sqlStmt + "\n");
            //Create Statement
            preparedStatement = conn.prepareStatement(sqlStmt);

            //Populate with Resident Id
            preparedStatement.setInt(1, resId);

            //Run executeUpdate operation with given sql statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (preparedStatement != null) {
                //Close Statement
                preparedStatement.close();
            }
            //Close connection
            conn.close();
        }
    }

    public static void dbExecutePreparedArchiveInsert(String sqlStmt, Archive archive) throws SQLException {
        //Declare statement as null
        PreparedStatement preparedStatement = null;
        try {
            //Connect to DB (Establish Mysql Connection)
            conn = getConnection();
            System.out.println("Select statement: " + sqlStmt + "\n");
            //Create Statement
            preparedStatement = conn.prepareStatement(sqlStmt);

            //Populate with Resident Id
            preparedStatement.setDate(1, (Date) archive.getYearBackUp());
            preparedStatement.setString(2, archive.getPathToArchive());
            preparedStatement.setString(3, archive.getNameArchive());
            //Run executeUpdate operation with given sql statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (preparedStatement != null) {
                //Close Statement
                preparedStatement.close();
            }
            //Close connection
            conn.close();
        }
    }

    public static void dbExecutePreparedAdminInsert(String sqlStmt, Admin admin) throws SQLException {
        //Declare statement as null
        PreparedStatement preparedStatement = null;
        try {
            //Connect to DB (Establish Mysql Connection)
            conn = getConnection();
            System.out.println("Select statement: " + sqlStmt + "\n");
            //Create Statement
            preparedStatement = conn.prepareStatement(sqlStmt);

            //Populate with Resident Id
            preparedStatement.setInt(1, admin.getIdAdminType());
            preparedStatement.setString(2, admin.getAdminName());
            preparedStatement.setString(3, admin.getAdminLastName());
            preparedStatement.setString(4, admin.getPesel());
            preparedStatement.setString(5, admin.getPassword());
            preparedStatement.setString(6, admin.getAdminEmail());
            preparedStatement.setDate(7, (Date) admin.getAdminDOB());
            preparedStatement.setString(8, admin.getAdminPhone());
            //Run executeUpdate operation with given sql statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (preparedStatement != null) {
                //Close Statement
                preparedStatement.close();
            }
            //Close connection
            conn.close();
        }
    }

    //DB Execute Insert Operation
    public static void dbExecutePreparedResidentInsert(String sqlStmt, String sqlStmtExt, @NotNull Resident res) throws SQLException {
        //Declare statement as null
        PreparedStatement insertResident = null;
        PreparedStatement insertResidentExt = null;
        try {
            //Connect to DB (Establish Mysql Connection)
            conn = getConnection();

            //Create Statement
            insertResident = conn.prepareStatement(sqlStmt);
            insertResidentExt = conn.prepareStatement(sqlStmtExt);

            insertResident.setInt(1, Integer.parseInt(res.getResidentTypeId()));
            insertResident.setInt(2, Integer.parseInt(res.getResidentRoomId()));
            insertResident.setString(3, res.getFirstName());
            insertResident.setString(4, res.getLastName());
            insertResident.setString(5, res.getPesel());
            insertResident.setString(6, res.getGender());
            insertResident.setDate(7, Date.valueOf(res.getDobDate()));
            insertResident.setString(8, res.getMotherName());
            insertResident.setString(9, res.getFatherName());
            insertResident.setString(10, res.getEmail());
            insertResident.setString(11, res.getCountry());
            insertResident.setString(12, res.getAddress());
            insertResident.setString(13, res.getPhoneNumber());
            insertResident.setDate(14, Date.valueOf(res.getEvictionDate()));
            insertResident.execute();

            if (res instanceof ResidentStudent) {
                insertResidentExt.setString(1, ((ResidentStudent) res).getStudentNumber());
                insertResidentExt.setString(2, ((ResidentStudent) res).getDepartment());
                insertResidentExt.setString(3, ((ResidentStudent) res).getYearOfStudy());
                insertResidentExt.setString(4, ((ResidentStudent) res).getAcademicYear());
                insertResidentExt.setString(5, ((ResidentStudent) res).getStudentPaymentAccount());
            } else if (res instanceof ResidentErasmus) {
                insertResidentExt.setString(1, ((ResidentErasmus) res).getOriginUniversity());
                insertResidentExt.setString(2, ((ResidentErasmus) res).getErasmusNumber());
            } else if (res instanceof ResidentForeignStudent) {
                insertResidentExt.setString(1, ((ResidentForeignStudent) res).getOriginUniversity());
            } else if (res instanceof ResidentGuest) {
                insertResidentExt.setString(1, ((ResidentGuest) res).getIsStudent());
                insertResidentExt.setString(2, ((ResidentGuest) res).getIsPartTimeStudent());
            }
            insertResidentExt.execute();
            //Run executeUpdate operation with given sql statement
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (insertResident != null) {
                //Close Statement
                insertResident.close();
            }
            if (insertResidentExt != null) {
                insertResidentExt.close();
            }
            //Close connection
            conn.close();
        }
    }

    public static void dbExecutePreparedPaymentInsert(String sqlInsertStmt, String sqlUpdateStmt, @NotNull Payment pay)
            throws SQLException {

        PreparedStatement insertPayment = null;
        PreparedStatement updateResident = null;
        try {
            conn = getConnection();

            //Create Statement
            insertPayment = conn.prepareStatement(sqlInsertStmt);
            updateResident = conn.prepareStatement(sqlUpdateStmt);

            insertPayment.setInt(1, pay.getIdResident());
            insertPayment.setInt(2, pay.getIdAdmin());
            insertPayment.setDouble(3, pay.getPaymentAmount());
            insertPayment.setDouble(4, pay.getPaymentBalance());
            insertPayment.setString(5, pay.getPaymentType());

            insertPayment.execute();

            updateResident.setDouble(1, pay.getPaymentBalance());
            updateResident.setInt(2, pay.getIdResident());

            updateResident.execute();
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if (insertPayment != null) {
                //Close Statement
                insertPayment.close();
            }
            if (updateResident != null) {
                updateResident.close();
            }
            //Close connection
            conn.close();
        }
    }
}

