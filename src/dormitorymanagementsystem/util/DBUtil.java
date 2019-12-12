package dormitorymanagementsystem.util;

import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;
import java.sql.*;

public class DBUtil {

    //Connection
    private static Connection conn = null;

    //Connection String
    private static final String connStr = "jdbc:mysql://localhost:3306/dormitory";

    //Connect to DB
    private static Connection getConnection() throws SQLException {
        return  DriverManager.getConnection(connStr, "root", "");
    }

    //DB Execute Query Operation
    public static ResultSet dbExecuteQuery(String queryStmt) throws SQLException {
        return dbExecuteQuery(queryStmt, "");
    }

    //DB Execute Query Operation
    private static ResultSet dbExecuteQuery(String queryStmt, String... searchedCriteria) throws SQLException {
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
            if(searchedCriteria.length > 1) {
                for (int i = 1; i <= searchedCriteria.length; i++) {
                    preparedStatement.setString(i, searchedCriteria[i - 1]);
                }
            } else preparedStatement.setString(1, searchedCriteria[0]);

            //Execute select (query) operation
            resultSet = preparedStatement.executeQuery(queryStmt);

            //CachedRowSet Implementation
            //In order to prevent "java.sql.SQLRecoverableException: Closed Connection: next" error
            //We are using CachedRowSet
            factory = RowSetProvider.newFactory();
            crs = factory.createCachedRowSet();
            crs.populate(resultSet);
        } catch (SQLException e) {
            System.out.println("Problem occurred at executeQuery operation : " + e);
            throw e;
        } finally {
            if(resultSet != null){
                //Close resultSet
                resultSet.close();
            }
            if(preparedStatement != null){
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
    public static void dbExecuteUpdate(String sqlStmt) throws SQLException {
        //Declare statement as null
        Statement stmt = null;
        try {
            //Connect to DB (Establish Mysql Connection)
            conn = getConnection();

            //Create Statement
            stmt = conn.createStatement();

            //Run executeUpdate operation with given sql statement
            stmt.executeUpdate(sqlStmt);
        } catch (SQLException e){
            System.out.println("Problem occurred at executeUpdate operation : " + e);
            throw e;
        } finally {
            if(stmt != null) {
                //Close Statement
                stmt.close();
            }
            //Close connection
            conn.close();
        }
    }
}
