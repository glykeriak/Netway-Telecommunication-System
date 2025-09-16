package core.databases;

import com.sun.tools.xjc.reader.xmlschema.bindinfo.BIConversion.User;
import core.classes.Packet;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Random;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import java.util.UUID;

public class DatabaseManager {

    static final String SQLITE_DRIVER = "org.sqlite.JDBC";
    static final String SQLITE_SUB = "jdbc:sqlite:";
    static final String DB_SERVER = "C:\\Users\\glyke\\Documents";
    static final String DB_NAME = "database.db";
    static final String DB_URL = SQLITE_SUB + DB_SERVER + "\\" + DB_NAME;

    private static String idGenerator(String userType) {
        final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final int ID_LENGTH = 10;
        Random random = new Random();
        StringBuilder id = new StringBuilder(ID_LENGTH);
        for (int i = 0; i < ID_LENGTH; i++) {
            int index = random.nextInt(CHARACTERS.length());
            id.append(CHARACTERS.charAt(index));
        }
        if (userType.equals("CID")) {
            return id.toString() + "C";
        } else if (userType.equals("UID")) {
            return id.toString() + "U";
        } else if (userType.equals("PrID")) {
            return id.toString() + "Pr";
        }
        return null;

    }

    public static Connection getConnection() {
        Connection conn = null;

        try {
            Class.forName(SQLITE_DRIVER);
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Connected...");
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        }

        return conn;
    }

    public static String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return Base64.getEncoder().encodeToString(saltBytes);
    }

    public static String hashPassword(String password, String salt) {
        String saltedPassword = salt + password;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashedBytes = md.digest(saltedPassword.getBytes());
            return Base64.getEncoder().encodeToString(hashedBytes);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 algorithm not found", e);
        }
    }

    public static int addClient(String email, List<String> user_info, List<String> client_info, String phone_number) {
        int status = 0;
        String UID_s = null;
        String sql = "SELECT U_ID FROM User WHERE email=?";
        Connection conn = DatabaseManager.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                UID_s = rs.getString(1);
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        String UID = idGenerator("UID");
        String CID = idGenerator("CID");
        String sql_1 = "INSERT INTO User(U_ID, firstname, lastname, hash, salt, email) VALUES (?, ?, ?, ?, ?, ?)";
        conn = DatabaseManager.getConnection();

        String salt = generateSalt();
        String hashedPassword = hashPassword(user_info.get(2), salt);

        try {
            PreparedStatement ps = conn.prepareStatement(sql_1);
            ps.setString(1, UID);
            ps.setString(2, user_info.get(0));
            ps.setString(3, user_info.get(1));
            ps.setString(4, hashedPassword);
            ps.setString(5, salt);
            ps.setString(6, user_info.get(3));
            status = ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        String sql_2 = "INSERT INTO Client(C_ID, U_ID, afm, total_dept, from_s) VALUES (?, ?, ?, ?, ?)";
        conn = DatabaseManager.getConnection();
        try {
            PreparedStatement ps = conn.prepareStatement(sql_2);
            ps.setString(1, CID);
            ps.setString(2, UID);
            ps.setString(3, client_info.get(0));
            ps.setString(4, "0");
            ps.setString(5, UID_s);
            status = ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        String sql_3 = "INSERT INTO PhoneNumber(P_ID, C_ID, number, saving) VALUES (?, ?, ?, ?)";
        conn = DatabaseManager.getConnection();
        String PID = idGenerator("PID");
        try {
            PreparedStatement ps = conn.prepareStatement(sql_3);
            ps.setString(1, PID);
            ps.setString(2, CID);
            ps.setString(3, phone_number);
            ps.setString(4, "");
            status = ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return status;
    }

    public static int login(String email, String password) {
        int status = 0;
        int error_flag = 0;
        String sql_0 = "SELECT hash, salt FROM User WHERE email=?";
        Connection conn = DatabaseManager.getConnection();
        String salt = null;
        String hash = null;
        try {
            PreparedStatement ps = conn.prepareStatement(sql_0);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                salt = rs.getString("salt");
                hash = rs.getString("hash");
                error_flag = 1;
                System.out.println("Salt found: " + salt);  // Debug message
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        if (error_flag == 0) {
            return 0;
        }

        String hashed = hashPassword(password, salt);

        if (!hashed.equals(hash)) {
            return 0;
        }

        String sql = "SELECT U_ID FROM User WHERE email=? AND hash=?";
        conn = DatabaseManager.getConnection();
        String u_id = null;

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, hash);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                u_id = rs.getString("U_ID");
                System.out.println("User ID found: " + u_id);  // Debug message
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        if (u_id != null) {
            if (checkIfExistsInTable("Client", u_id)) {
                status = 1;
                System.out.println("User is a client");  // Debug message
            } else if (checkIfExistsInTable("Seller", u_id)) {
                status = 2;
                System.out.println("User is a seller");  // Debug message
            } else if (checkIfExistsInTable("Admin", u_id)) {
                status = 3;
                System.out.println("User is an admin");  // Debug message
            }
        }
        System.out.println("Login status: " + status);
        return status;
    }

    private static boolean checkIfExistsInTable(String tableName, String u_id) {
        boolean exists = false;
        String sql = "SELECT 1 FROM " + tableName + " WHERE U_ID=?";
        Connection conn = DatabaseManager.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, u_id);
            ResultSet rs = ps.executeQuery();
            exists = rs.next();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return exists;
    }

    public static int linkPhoneWithPacket(String phonenumber, String packet_name) {
        int status = 1;

        String P_ID = null;
        String sql_3 = "SELECT P_ID FROM PhoneNumber WHERE number=?";
        Connection conn = DatabaseManager.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(sql_3);
            ps.setString(1, phonenumber);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                P_ID = rs.getString(1);
            } else {
                return 0;
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        String Pa_ID = null;
        String duration = null;
        String sql_4 = "SELECT Pa_ID, duration FROM Packet WHERE name=?";
        conn = DatabaseManager.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(sql_4);
            ps.setString(1, packet_name);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Pa_ID = rs.getString(1);
                duration = rs.getString(2);
            } else {
                return 0;
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        String Pr_ID = idGenerator("PrID");
        String sql_5 = "INSERT INTO Program(Pr_ID, P_ID, Pa_ID, date_of_expire) VALUES (?, ?, ?, ?)";
        conn = DatabaseManager.getConnection();

        LocalDate currentDate = LocalDate.now();
        int durationDays = Integer.parseInt(duration);
        LocalDate futureDate = currentDate.plus(durationDays, ChronoUnit.DAYS);

        try {
            PreparedStatement ps = conn.prepareStatement(sql_5);
            ps.setString(1, Pr_ID);
            ps.setString(2, P_ID);
            ps.setString(3, Pa_ID);
            ps.setString(4, futureDate.toString());
            status = ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        return status;
    }

    public static List<Packet> searchAllPackets() {
        List<Packet> list = new ArrayList<>();
        String sql = "SELECT * FROM Packet";
        Connection conn = DatabaseManager.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Packet packet = new Packet();
                packet.setName(rs.getString(2));
                packet.setPrice(rs.getString(3));
                packet.setDescription(rs.getString(4));
                packet.setDuration(rs.getString(5));
                list.add(packet);
            }
            conn.close();
        } catch (NullPointerException | SQLException ex) {
            System.out.println(ex);
        }
        return list;
    }

    public static int updateClientWithSeller(String afm, String sellerId) {
        int status = 0;
        try {
            Connection con = getConnection();
            PreparedStatement ps = con.prepareStatement("UPDATE Client SET sellerId = ? WHERE afm = ?");
            ps.setString(1, sellerId);
            ps.setString(2, afm);

            status = ps.executeUpdate();
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return status;
    }

    public static List<String[]> getInvoiceData(String email) {
        List<String[]> data = new ArrayList<>();
        String sql = "SELECT ph.number, b.price FROM PhoneNumber ph "
                + "JOIN Bill b ON ph.P_ID = b.P_ID "
                + "JOIN Client c ON ph.C_ID = c.C_ID "
                + "JOIN User u ON c.U_ID = u.U_ID "
                + "WHERE u.email = ?";
        Connection conn = getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String[] row = new String[2];
                row[0] = rs.getString("number");
                row[1] = rs.getString("price");
                data.add(row);
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return data;
    }

    public static boolean deleteCustomerSubscription(String email) {
        System.out.println("Attempting to delete subscription for email: " + email); // debug
        String query = "DELETE FROM User WHERE email = ?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String[] getCustomerDetails(String email) {
        System.out.println("Fetching customer details for email: " + email); //Debug
        String query = "SELECT User.firstname, User.lastname, User.email, Client.total_dept, PhoneNumber.number "
                + "FROM User "
                + "JOIN Client ON User.U_ID = Client.U_ID "
                + "LEFT JOIN PhoneNumber ON Client.C_ID = PhoneNumber.C_ID "
                + "WHERE User.email = ?";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                String emailFetched = resultSet.getString("email");
                String totalDept = resultSet.getString("total_dept");
                String phoneNumber = resultSet.getString("number");
                System.out.println("Customer details found: " + firstname + " " + lastname + ", " + emailFetched + ", " + phoneNumber + ", " + totalDept); // Debug
                return new String[]{firstname + " " + lastname, emailFetched, phoneNumber, totalDept};
            } else {
                System.out.println("No customer details found for email: " + email); // Debug
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean addSeller(String firstname, String lastname, String email, String afm, String phone, String code, String emailCookie) {
        String userQuery = "INSERT INTO User (U_ID, firstname, lastname, hash, salt, email) VALUES (?, ?, ?, ?, ?, ?)";
        String phoneQuery = "INSERT INTO PhoneNumber (P_ID, C_ID, number) VALUES (?, ?, ?)";
        String sellerQuery = "INSERT INTO Seller (S_ID, U_ID, sales, from_a) VALUES (?, ?, ?, ?)";
        String aid = null;
        String adminQ = "SELECT A.A_ID FROM Admin A , User u WHERE u.email = ? AND A.U_ID = u.U_ID";

        Connection conn = DatabaseManager.getConnection();

        try {
            PreparedStatement ps = conn.prepareStatement(adminQ);
            ps.setString(1, emailCookie);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                aid = rs.getString(1);
            }
            conn.close();
        } catch (NullPointerException | SQLException ex) {
            System.out.println(ex);
        }

        String salt = generateSalt();
        String hashedPassword = hashPassword(code, salt);
        Connection connection = null;

        try {
            connection = getConnection();
            connection.setAutoCommit(false); // start transaction

            // Generate unique IDs
            String userId = UUID.randomUUID().toString();
            String phoneId = UUID.randomUUID().toString();
            String sellerId = UUID.randomUUID().toString();

            // Insert into User table
            try (PreparedStatement userStatement = connection.prepareStatement(userQuery)) {
                userStatement.setString(1, userId);
                userStatement.setString(2, firstname);
                userStatement.setString(3, lastname);
                userStatement.setString(4, hashedPassword);
                userStatement.setString(5, salt);
                userStatement.setString(6, email);
                userStatement.executeUpdate();
            }

            // Insert into PhoneNumber table
            try (PreparedStatement phoneStatement = connection.prepareStatement(phoneQuery)) {
                phoneStatement.setString(1, phoneId);
                phoneStatement.setString(2, userId); // assuming C_ID refers to User ID
                phoneStatement.setString(3, phone);
                phoneStatement.executeUpdate();
            }

            // Insert into Seller table
            try (PreparedStatement sellerStatement = connection.prepareStatement(sellerQuery)) {
                sellerStatement.setString(1, sellerId);
                sellerStatement.setString(2, userId);
                sellerStatement.setDouble(3, 0.0); // Assuming initial sales value is 0.0
                sellerStatement.setString(4, aid); // Assuming 'from_a' is AFM
                sellerStatement.executeUpdate();
            }

            connection.commit(); // commit transaction
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback(); // rollback transaction on error
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true); // restore default commit behavior
                    connection.close();
                } catch (SQLException closeException) {
                    closeException.printStackTrace();
                }
            }
        }
    }

    public static ResultSet searchSellerByEmail(String email) {
        String query = "SELECT * FROM User WHERE email = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getPhoneNumber(String userId) {
        String query = "SELECT number FROM PhoneNumber WHERE C_ID = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getString("number");
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getAllSellers() {
        String query = "SELECT User.firstname, User.lastname, User.email, Seller.sales, Seller.from_a "
                + "FROM User JOIN Seller ON User.U_ID = Seller.U_ID";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ResultSet getAllCustomers() {
        String query = "SELECT Client.C_ID, User.firstname, User.lastname, User.email, Client.afm, Client.total_dept, Client.from_s "
                + "FROM Client JOIN User ON Client.U_ID = User.U_ID";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized boolean deleteSellerByEmail(String email) {
        String query = "DELETE FROM Seller WHERE U_ID = (SELECT U_ID FROM User WHERE email = ?)";
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, email);
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            if (e.getMessage().contains("database is locked")) {
                System.out.println("Database is locked, retrying...");
                try {
                    Thread.sleep(100);
                    return deleteSellerByEmail(email);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                    return false;
                }
            }
            e.printStackTrace();
            return false;
        }
    }

    public static synchronized boolean createNewPacket(String packetName, String description, double price, int duration) {
        if (duration <= 0 || duration > 90) {
            throw new IllegalArgumentException("Duration must be a positive integer not exceeding 90 days.");
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = getConnection();
            String query = "INSERT INTO Packet (name, description, price, duration) VALUES (?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, packetName);
            preparedStatement.setString(2, description);
            preparedStatement.setDouble(3, price);
            preparedStatement.setInt(4, duration);

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeResources(connection, preparedStatement, null);
        }
    }

    public static void closeResources(Connection connection, PreparedStatement preparedStatement, ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet searchUserByEmail(String email) {
        String query = "SELECT * FROM User WHERE email = ?";
        try {
            Connection connection = getConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            return statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static ArrayList searchCallHistoryByPhoneNumber(String phoneNumber) {
        String sql = "SELECT c.calling_phone , c.date_of_call  FROM Call c, PhoneNumber p WHERE p.number = ? AND p.P_ID = c.P_ID";
        Connection conn = DatabaseManager.getConnection();
        ArrayList<String> calling_phones = new ArrayList<>();

        try {
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, phoneNumber);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                calling_phones.add(rs.getString(1));
                calling_phones.add(rs.getString(2));
            }
            conn.close();
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return calling_phones;
    }

    public static double getDebtByPhoneNumber(String phoneNumber) {
        double debt = 0.0;
        try (Connection connection = getConnection()) {
            String query = "SELECT SUM(price) AS debt FROM Bill WHERE P_ID = (SELECT P_ID FROM PhoneNumber WHERE number = ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, phoneNumber);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                debt = resultSet.getDouble("debt");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return debt;
    }

    public static boolean payBill(String phoneNumber, double amount) {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);

            String selectQuery = "SELECT B_ID, price FROM Bill WHERE P_ID = (SELECT P_ID FROM PhoneNumber WHERE number = ?) ORDER BY date_of_expire";
            PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
            selectStatement.setString(1, phoneNumber);
            ResultSet resultSet = selectStatement.executeQuery();

            while (resultSet.next() && amount > 0) {
                String billId = resultSet.getString("B_ID");
                double price = resultSet.getDouble("price");

                if (amount >= price) {
                    String updateQuery = "DELETE FROM Bill WHERE B_ID = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setString(1, billId);
                    updateStatement.executeUpdate();
                    amount -= price;
                } else {
                    String updateQuery = "UPDATE Bill SET price = price - ? WHERE B_ID = ?";
                    PreparedStatement updateStatement = connection.prepareStatement(updateQuery);
                    updateStatement.setDouble(1, amount);
                    updateStatement.setString(2, billId);
                    updateStatement.executeUpdate();
                    amount = 0;
                }
            }

            connection.commit();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            return false;
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static boolean updatePacket(String packetName, String description, double price, int duration) {
        try (Connection conn = getConnection()) {
            String query = "UPDATE Packet SET description = ?, price = ?, duration = ? WHERE name = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, description);
                stmt.setDouble(2, price);
                stmt.setInt(3, duration);
                stmt.setString(4, packetName);

                int rowsUpdated = stmt.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deletePacket(String packetName) {
        try (Connection conn = getConnection()) {
            String query = "DELETE FROM Packet WHERE name = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, packetName);

                int rowsDeleted = stmt.executeUpdate();
                return rowsDeleted > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static ResultSet getUserDetailsByEmail(String email) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = getConnection();
            String sql = "SELECT * FROM User WHERE email = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rs;
    }

}
