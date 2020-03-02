package dormitorymanagementsystem.model.archive;

import dormitorymanagementsystem.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ArchiveDAO {

    public static ObservableList<Archive> searchArchives() throws SQLException {
        //Declare a SELECT statement
        @Language("MySQL") String selectStmt = "SELECT * FROM archive ORDER BY Year_backup DESC";
        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsArchive = DBUtil.dbExecutePreparedQuery(selectStmt, "");

            //Send ResultSet to the getRoomList method and get room object
            //Return room object
            return getArchiveList(rsArchive);
        } catch (SQLException e) {
            System.out.println("While searching an residents an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    @NotNull
    private static ObservableList<Archive> getArchiveList(@NotNull ResultSet rs) throws SQLException {
        //Declare a observable List which comprises of Room objects
        ObservableList<Archive> archList = FXCollections.observableArrayList();

        while (rs.next()) {
            Archive archive = new Archive();
            //Add room to the ObservableList
            archList.add(addArchiveToObservableList(archive, rs));
        }
        return archList;
    }

    @NotNull
    private static Archive addArchiveToObservableList(@NotNull Archive archive, @NotNull ResultSet rs) throws SQLException {
        archive.setIdBackup(rs.getInt("IDBACKUP"));
        archive.setYearBackUp(rs.getDate("YEAR_BACKUP"));
        archive.setPathToArchive(rs.getString("PATH_TO_ARCHIVE"));
        archive.setNameArchive(rs.getString("ARCHIVE_NAME"));

        return archive;
    }

    public static void insertArchive(Archive archive) throws SQLException {

        @Language("MySQL") String insertStmt = "" +
                "INSERT INTO archive(idBackup, Year_backup, Path_to_archive, Archive_name)" +
                " VALUE (null, ?, ?, ?);";
        try {
            DBUtil.dbExecutePreparedArchiveInsert(insertStmt, archive);
        } catch (SQLException e) {
            System.out.println("While searching an residents an error occurred: " + e);
            //Return exception
            throw e;
        }
    }
}
