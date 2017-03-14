import java.sql.*;

/**
 * Created by VADYM on 13.03.2017.
 */
public class ManagerFlat implements FlatDAO {


    protected Connection conn = null;

    public ManagerFlat() {

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

    public void getFlat() throws SQLException {

        PreparedStatement ps = conn.prepareStatement("SELECT * FROM Flats");
        printResult(ps);

    }

    public void getFlatByPrice(int pr) throws SQLException {

       // PreparedStatement ps = conn.prepareStatement("SELECT * FROM Flats WHERE price = ?");
        PreparedStatement ps = conn.prepareStatement("SELECT\n" +
                "id_flat,\n" +
                "price,\n" +
                "area,\n" +
                "rooms,\n" +
                "Flats.id_region,\n" +
                "Region.name AS Region_name,\n" +
                "Flats.id_adress,\n" +
                "Address.name AS Address_name\n" +
                "FROM Flats\n" +
                "JOIN Address ON Flats.id_adress = Address.id_address\n" +
                "JOIN Region ON Flats.id_region = Region.id_region\n" +
                "WHERE price = ?");


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
        } finally {
            st.close();
        }
    }
}
