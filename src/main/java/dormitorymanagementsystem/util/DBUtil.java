package dormitorymanagementsystem.util;

import dormitorymanagementsystem.model.resident.Resident;
import org.jetbrains.annotations.NotNull;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class DBUtil {

    //Connection String
    private static final String connStr = "jdbc:mysql://localhost:3306/dormitory";
    //Connection
    private static Connection conn = null;

    //Connect to DB
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(connStr, "root", "");
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

    //DB Execute Update (For Update/Insert/Delete) Operation
    public static void dbExecutePreparedUpdate(String sqlStmt, int resId, String columnName) throws SQLException {
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

    //DB Execute Update (For Update/Insert/Delete) Operation
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

    public static void dbExecutePreparedInsert(String sqlStmt, Resident res) throws SQLException {
        //Declare statement as null
        PreparedStatement preparedStatement = null;
        try {
            //Connect to DB (Establish Mysql Connection)
            conn = getConnection();

            //Create Statement
            preparedStatement = conn.prepareStatement(sqlStmt);

            preparedStatement.setInt(1, Integer.parseInt(res.getResidentTypeId()));
            preparedStatement.setInt(2, Integer.parseInt(res.getResidentRoomId()));
            preparedStatement.setString(3, res.getFirstName());
            preparedStatement.setString(4, res.getLastName());
            preparedStatement.setString(5, res.getPesel());
            preparedStatement.setString(6, res.getGender());
            preparedStatement.setDate(7, Date.valueOf(res.getDobDate()));
            preparedStatement.setString(8, res.getMotherName());
            preparedStatement.setString(9, res.getFatherName());
            preparedStatement.setString(10, res.getEmail());
            preparedStatement.setString(11, res.getCountry());
            preparedStatement.setString(12, res.getAddress());
            preparedStatement.setString(13, res.getPhoneNumber());
            preparedStatement.setDate(14, Date.valueOf(res.getEvictionDate()));


            //Run executeUpdate operation with given sql statement
            preparedStatement.execute();
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
}
