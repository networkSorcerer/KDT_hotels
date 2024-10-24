package vo;

public class HotelVO {
    private int hotelID;
    private String hotelName;
    private String region;
    private String phone;
    private String hotelExpl ;

    public HotelVO() {}

    public HotelVO(int hotelID, String hotelName, String region, String phone, String hotelExpl) {
        super();
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.region = region;
        this.phone = phone;
        this.hotelExpl = hotelExpl;
    }
    public HotelVO(String hotelName, String region, String phone, String hotelExpl) {
        this.hotelName = hotelName;
        this.region = region;
        this.phone = phone;
        this.hotelExpl=hotelExpl;

    }


    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHotelExpl() {
        return hotelExpl;
    }

    public void setHotelExpl(String hotelExpl) {
        this.hotelExpl = hotelExpl;
    }
}
