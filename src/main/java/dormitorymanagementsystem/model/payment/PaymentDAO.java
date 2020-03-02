package dormitorymanagementsystem.model.payment;

import dormitorymanagementsystem.util.DBUtil;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.intellij.lang.annotations.Language;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDAO {

    @NotNull
    public static ObservableList<Payment> searchPayments(String payFLName) throws SQLException {
        //Declare a SELECT statement
        @Language("MySQL") String selectStmt = "" +
                "SELECT p.idPayment, p.idResident, p.idAdmin, p.Amount, p.Balance, p.Date, p.PaymentType," +
                "r.first_name, r.last_name" +
                " FROM payment as p" +
                " INNER JOIN resident as r " +
                "ON p.idResident = r.id_resident " +
                "WHERE r.first_name LIKE ? OR r.last_name LIKE ?";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRes = DBUtil.dbExecutePreparedQueryFind(selectStmt, payFLName, payFLName);

            //Send ResultSet to the getResidentFromResultSet method and get payident object
            //Return payident object
            return getPaymentList(rsRes);
        } catch (SQLException e) {
            System.out.println("While searching an pay an error occurred: " + e.getMessage());
            //Return exception
            throw e;
        }
    }

    @NotNull
    public static ObservableList<Payment> searchPayments() throws SQLException {
        //Declare a SELECT statement
        @Language("MySQL") String selectStmt = "" +
                "SELECT p.idPayment, p.idResident, p.idAdmin, p.Amount, p.Balance, p.Date, p.PaymentType," +
                "r.first_name, r.last_name" +
                " FROM payment as p" +
                " INNER JOIN resident as r " +
                "ON p.idResident = r.id_resident " +
                "ORDER BY p.Date DESC";

        //Execute SELECT statement
        try {
            //Get ResultSet from dbExecuteQuery method
            ResultSet rsRes = DBUtil.dbExecutePreparedQuery(selectStmt, "");

            //Send ResultSet to the getResidentFromResultSet method and get resident object
            //Return resident object
            return getPaymentList(rsRes);
        } catch (SQLException e) {
            System.out.println("While searching an residents an error occurred: " + e);
            //Return exception
            throw e;
        }
    }

    public static boolean insertPayment(Payment payment) {

        @Language("MySQL") String insertPaymentStmt = "" +
                "INSERT INTO payment(idPayment, idResident, idAdmin, Amount, Balance, Date, PaymentType) " +
                "VALUE (null, ?, ?, ?, ?, current_timestamp, ?);";
        @Language("MySQL") String updateResidentPaymentFee = "UPDATE resident SET" +
                " resident.payment_fee = ? WHERE id_resident = ?";
        try {
            DBUtil.dbExecutePreparedPaymentInsert(insertPaymentStmt, updateResidentPaymentFee, payment);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @NotNull
    private static ObservableList<Payment> getPaymentList(@NotNull ResultSet rs) throws SQLException {
        //Declare a observable List which comprises of Resident objects
        ObservableList<Payment> payList = FXCollections.observableArrayList();

        while (rs.next()) {
            Payment pay = new Payment();
            //Add payident to the ObservableList
            payList.add(addPaymentToObservableList(pay, rs));
        }
        return payList;
    }

    @NotNull
    private static Payment addPaymentToObservableList(@NotNull Payment pay, @NotNull ResultSet rs) throws SQLException {
        pay.setIdAdmin(rs.getInt("IDADMIN"));
        pay.setIdPayment(rs.getInt("IDPAYMENT"));
        pay.setIdResident(rs.getInt("IDRESIDENT"));
        pay.setResidentName(rs.getString("FIRST_NAME"));
        pay.setResidentLastName(rs.getString("LAST_NAME"));
        pay.setPaymentAmount(rs.getDouble("AMOUNT"));
        pay.setPaymentBalance(rs.getDouble("BALANCE"));
        pay.setPaymentDate(rs.getDate("DATE"));
        pay.setPaymentType(rs.getString("PAYMENTTYPE"));

        return pay;
    }
}
