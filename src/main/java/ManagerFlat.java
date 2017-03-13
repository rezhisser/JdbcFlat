import java.sql.*;

/**
 * Created by VADYM on 13.03.2017.
 */
public class ManagerFlat implements FlatDAO {

    protected  String dbConnection;
    protected  String dbUser;
    protected  String dbPassword;
    protected Connection conn = null;

    public ManagerFlat() {
    }

    public ManagerFlat(String dbConnection, String dbUser, String dbPassword) {
        this.dbConnection = dbConnection;
        this.dbUser = dbUser;
        this.dbPassword = dbPassword;
        DbProperties props = new DbProperties();
        try {
          //  this.conn = DriverManager.getConnection(dbConnection,dbUser,dbPassword);
            this.conn = DriverManager.getConnection(props.getUrl(),props.getUser(),props.getPassword());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getDbConnection() {
        return dbConnection;
    }

    public void setDbConnection(String dbConnection) {
        this.dbConnection = dbConnection;
    }

    public String getDbUser() {
        return dbUser;
    }

    public void setDbUser(String dbUser) {
        this.dbUser = dbUser;
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public void setDbPassword(String dbPassword) {
        this.dbPassword = dbPassword;
    }

    public void getFlat() throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Flats");
        printResult(ps);

    }

    public void getFlatByPrice(int pr) throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Flats WHERE price = ?");

            ps.setInt(1, pr);
            ps.executeQuery();
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

    public void getFlat(Flat flat) throws SQLException {

    }

    public void create(Flat flat) throws SQLException{
        PreparedStatement ps = conn.prepareStatement("INSERT INTO Flats (id_flat, price, area, rooms, id_region, id_adress) VALUES(?, ?, ?, ?, ?, ?)");
        try {
            ps.setInt(1, flat.getIdFlat());
            ps.setInt(2, flat.getPriceFlat());
            ps.setInt(3, flat.getAreFlat());
            ps.setInt(4, flat.getRoomsFlat());
            ps.setInt(5, flat.getIdRegionFlat());
            ps.setInt(6, flat.getIdAdressFlat());
            ps.executeUpdate(); // for INSERT, UPDATE & DELETE
        } finally {
            ps.close();
        }

    }


    public void update(Flat flat) {

    }

    public void delete(Flat flat) {

    }

    public void initDB() throws SQLException {
        Statement st = conn.createStatement();
        try {
            st.execute("DROP TABLE IF EXISTS Flats");
            st.execute("CREATE TABLE Flats (id_flat INT NOT NULL AUTO_INCREMENT PRIMARY KEY, price INT, area INT, rooms INT, id_region INT, id_adress INT)");
            st.execute("CREATE TABLE Region (id_region INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL)");
            st.execute("CREATE TABLE Adress (id_adress INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(20) NOT NULL)");
        } finally {
            st.close();
        }
    }
}
