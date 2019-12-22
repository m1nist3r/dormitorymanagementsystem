package dormitorymanagementsystem.model.resident;

public class Admin {
    private String pesel;
    private String password;
    private int idAdminType;
    private int idAdmin;

    public Admin(String pesel, String password, int idAdminType, int idAdmin) {
        this.pesel = pesel;
        this.password = password;
        this.idAdminType = idAdminType;
        this.idAdmin = idAdmin;
    }

    Admin() {
    }

    public String getPesel() {
        return pesel;
    }

    void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public String getPassword() {
        return password;
    }

    void setPassword(String password) {
        this.password = password;
    }

    public int getIdAdmin() {
        return idAdmin;
    }

    void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }

    public int getIdAdminType() {
        return idAdminType;
    }

    void setIdAdminType(int idAdminType) {
        this.idAdminType = idAdminType;
    }

    public void login(int idAdminType) {
        System.out.println(getPesel());
        System.out.println(getPassword());
    }
}
