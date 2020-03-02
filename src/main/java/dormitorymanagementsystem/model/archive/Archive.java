package dormitorymanagementsystem.model.archive;

import javafx.beans.property.*;

import java.util.Date;

public class Archive {

    private IntegerProperty idBackup;
    private SimpleObjectProperty<Date> yearBackUp;
    private StringProperty pathToArchive;
    private StringProperty nameArchive;

    public Archive() {
        this.idBackup = new SimpleIntegerProperty();
        this.yearBackUp = new SimpleObjectProperty<>();
        this.pathToArchive = new SimpleStringProperty();
        this.nameArchive = new SimpleStringProperty();
    }

    public int getIdBackup() {
        return idBackup.get();
    }

    public void setIdBackup(int idBackup) {
        this.idBackup.set(idBackup);
    }

    public IntegerProperty idBackupProperty() {
        return idBackup;
    }

    public Date getYearBackUp() {
        return yearBackUp.get();
    }

    public void setYearBackUp(Date yearBackUp) {
        this.yearBackUp.set(yearBackUp);
    }

    public SimpleObjectProperty<Date> yearBackUpProperty() {
        return yearBackUp;
    }

    public String getPathToArchive() {
        return pathToArchive.get();
    }

    public void setPathToArchive(String pathToArchive) {
        this.pathToArchive.set(pathToArchive);
    }

    public StringProperty pathToArchiveProperty() {
        return pathToArchive;
    }

    public String getNameArchive() {
        return nameArchive.get();
    }

    public void setNameArchive(String nameArchive) {
        this.nameArchive.set(nameArchive);
    }

    public StringProperty nameArchiveProperty() {
        return nameArchive;
    }
}
