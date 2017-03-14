import java.sql.SQLException;

/**
 * Created by VADYM on 14.03.2017.
 */
public interface AdressDAO {
    void getFlat(Address address) throws SQLException;
    void create(Address address) throws SQLException;
    void update(Address address);
    void delete(Address address);
}
