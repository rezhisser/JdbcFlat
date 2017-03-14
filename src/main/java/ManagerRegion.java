import java.sql.*;

/**
 * Created by VADYM on 14.03.2017.
 */
public class ManagerRegion implements RegionDAO {

    protected Connection conn = null;

    public ManagerRegion() {
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

    public void getRegion() throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Region");
        printResult(ps);

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

    public void getRegion(Region region) throws SQLException {

    }

    public void create(Region region) throws SQLException {
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Region (name) VALUES(?)");
        try {
            ps.setString(1, region.getRegionName());
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }

    }

    public void update(Region region) {

    }

    public void delete(Region region) {

    }

    public void initDB() throws SQLException {
        Statement st = conn.createStatement();
        try {
            st.execute("DROP TABLE IF EXISTS Region");
            st.execute("CREATE TABLE Region (id_region INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL)");
        } finally {
            st.close();
        }
    }
}
