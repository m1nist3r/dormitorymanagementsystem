package dormitorymanagementsystem.model.admin;

public class Admin {
    private String pesel;

    public String getPesel() {
        return pesel;
    }

    public int getIdAdminType() {
        return idAdminType;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    private String password;
    private int idAdminType;
    private int idAdmin;

    Admin() {
    }

    void setPesel(String pesel) {
        this.pesel = pesel;
    }

    void setPassword(String password) {
        this.password = password;
    }

    void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    void setIdAdminType(int idAdminType) {
        this.idAdminType = idAdminType;
    }
}
