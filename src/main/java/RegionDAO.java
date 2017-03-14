import java.sql.SQLException;

/**
 * Created by VADYM on 14.03.2017.
 */
public interface RegionDAO {
    void getRegion(Region region) throws SQLException;
    void create(Region region) throws SQLException;
    void update(Region region);
    void delete(Region region);
}
