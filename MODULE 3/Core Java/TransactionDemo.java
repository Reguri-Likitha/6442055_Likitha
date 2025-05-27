import java.sql.*;

public class TransactionDemo {
    public static void main(String[] args) {
        String url = "jdbc:sqlite:bank.db";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("DROP TABLE IF EXISTS accounts");
            stmt.executeUpdate("CREATE TABLE accounts (id INTEGER PRIMARY KEY, name TEXT, balance REAL)");
            stmt.executeUpdate("INSERT INTO accounts (id, name, balance) VALUES (1, 'Alice', 1000.0)");
            stmt.executeUpdate("INSERT INTO accounts (id, name, balance) VALUES (2, 'Bob', 500.0)");

            transfer(conn, 1, 2, 200.0);

            ResultSet rs = stmt.executeQuery("SELECT * FROM accounts");
            while (rs.next()) {
                System.out.println(rs.getInt("id") + " " +
                                   rs.getString("name") + " " +
                                   rs.getDouble("balance"));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void transfer(Connection conn, int fromId, int toId, double amount) {
        String debitSQL = "UPDATE accounts SET balance = balance - ? WHERE id = ?";
        String creditSQL = "UPDATE accounts SET balance = balance + ? WHERE id = ?";

        try (PreparedStatement debitStmt = conn.prepareStatement(debitSQL);
             PreparedStatement creditStmt = conn.prepareStatement(creditSQL)) {

            conn.setAutoCommit(false);

            debitStmt.setDouble(1, amount);
            debitStmt.setInt(2, fromId);
            debitStmt.executeUpdate();

            creditStmt.setDouble(1, amount);
            creditStmt.setInt(2, toId);
            creditStmt.executeUpdate();

            conn.commit();
            System.out.println("Transfer successful.");

        } catch (Exception e) {
            try {
                conn.rollback();
                System.out.println("Transfer failed. Rolled back.");
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
