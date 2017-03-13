import java.sql.SQLException;

/**
 * Created by VADYM on 13.03.2017.
 */
public interface FlatDAO {

    void getFlat(Flat flat) throws SQLException;
    void create(Flat flat) throws SQLException;
    void update(Flat flat);
    void delete(Flat flat);

}
