/**
 * Created by VADYM on 13.03.2017.
 */


public class Flat {
    private int idFlat;
    private int priceFlat;
    private int areFlat;
    private int roomsFlat;
    private int idRegionFlat;
    private int idAdressFlat;


    public Flat() {
    }

    public Flat(int priceFlat, int areFlat, int roomsFlat, int idRegionFlat, int idAdressFlat) {

        this.priceFlat = priceFlat;
        this.areFlat = areFlat;
        this.roomsFlat = roomsFlat;
        this.idRegionFlat = idRegionFlat;
        this.idAdressFlat = idAdressFlat;
    }

    public int getIdFlat() {
        return idFlat;
    }


    public int getPriceFlat() {
        return priceFlat;
    }

    public void setPriceFlat(int priceFlat) {
        this.priceFlat = priceFlat;
    }

    public int getAreFlat() {
        return areFlat;
    }

    public void setAreFlat(int areFlat) {
        this.areFlat = areFlat;
    }

    public int getRoomsFlat() {
        return roomsFlat;
    }

    public void setRoomsFlat(int roomsFlat) {
        this.roomsFlat = roomsFlat;
    }

    public int getIdRegionFlat() {
        return idRegionFlat;
    }

    public void setIdRegionFlat(int idRegionFlat) {
        this.idRegionFlat = idRegionFlat;
    }

    public int getIdAdressFlat() {
        return idAdressFlat;
    }

    public void setIdAdressFlat(int idAdressFlat) {
        this.idAdressFlat = idAdressFlat;
    }
}


