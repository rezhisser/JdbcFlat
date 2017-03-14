import java.sql.*;

/**
 * Created by VADYM on 14.03.2017.
 */
public class ManagerAddress implements AddressDAO {

    protected Connection conn = null;

    public ManagerAddress() {
        DbProperties props = new DbProperties();
        try {
            this.conn = DriverManager.getConnection(props.getUrl(),props.getUser(),props.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            initDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void getAddress() throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Address");
        printResult(ps);

    }


    public void getAddress(Address address) throws SQLException {

    }

    public void create(Address address) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Address (name) VALUES(?)");
        try {
            ps.setString(1, address.getAdressName());
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }

    }

    public void update(Address address) {

    }

    public void delete(Address address) {

    }
    private void printResult(PreparedStatement ps) throws SQLException {
        try {
            // table of data representing a database result set,
            ResultSet rs = ps.executeQuery();
            try {
                // can be used to get information about the types and properties of the columns in a ResultSet object
                ResultSetMetaData md = rs.getMetaData();

                for (int i = 1; i <= md.getColumnCount(); i++)
                    System.out.print(md.getColumnName(i) + "\t\t");
                System.out.println();

                while (rs.next()) {
                    for (int i = 1; i <= md.getColumnCount(); i++) {
                        System.out.print(rs.getString(i) + "\t\t\t");
                    }
                    System.out.println();
                }
            } finally {
                rs.close(); // rs can't be null according to the docs
            }
        } finally {
            ps.close();
        }
    }

    public void initDB() throws SQLException {
        Statement st = conn.createStatement();
        try {
            st.execute("DROP TABLE IF EXISTS Address");
            st.execute("CREATE TABLE Address (id_address INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL)");
        } finally {
            st.close();
        }
    }
}
