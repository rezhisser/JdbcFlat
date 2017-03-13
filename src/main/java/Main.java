import java.sql.*;

/**
 * Created by VADYM on 10.03.2017.
 */

public class Main {

/*
Спроектировать базу «Квартиры». Каждая запись
в базе содержит данные о квартире (район,
адрес, площадь, кол. комнат, цена). Сделать
возможность выборки квартир из списка по
параметрам.

*/

    //Разобраться, как правильно добавлять AUTO_INCREMENT (если такая запись существует - не добавлять)
    //можно ли параметром задать имя поля

    public static void main(String[] args) throws SQLException {
        DbProperties props = new DbProperties();
        ManagerFlat mf = new ManagerFlat(props.getUrl(),props.getUser(),props.getPassword());

        try {
            mf.initDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        Flat fl1 = new Flat(70000, 120, 4, 1, 1);
        Flat fl2 = new Flat(70000, 120, 2, 2, 2);
        Flat fl3 = new Flat(60000, 140, 3, 3, 3);
        Flat fl4 = new Flat(50000, 130, 1, 1, 2);
        Flat fl5 = new Flat(40000, 120, 4, 4, 1);
        Flat fl6 = new Flat(713000, 124, 3, 2, 4);
        Flat fl7 = new Flat(706000, 150, 3, 3, 1);
        Flat fl8 = new Flat(700700, 120, 4, 1, 2);
        Flat fl9 = new Flat(34550, 125, 4, 1, 3);
        Flat fl10 = new Flat(35400, 123, 1, 4, 4);

        try {
            mf.create(fl1);
            mf.create(fl2);
            mf.create(fl3);
            mf.create(fl4);
            mf.create(fl5);
            mf.create(fl6);
            mf.create(fl7);
            mf.create(fl8);
            mf.create(fl9);
            mf.create(fl10);
            mf.create(fl10);
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            mf.getFlat();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println();
        mf.getFlatByPrice(70000);


    }


}
