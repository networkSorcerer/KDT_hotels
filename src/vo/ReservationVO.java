package vo;

import java.sql.Date;

public class ReservationVO {
    private int reserveID;
    private String userID;
    private int hotelID;
    private String hotelName;
    private Date startDate;
    private Date endDate;
    private String price;
    private String roomNumber;
    private int roomID;


    public ReservationVO(int roomID, int hotelID, String roomTypeStr, int price, int roomNumber) {
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    private String roomType;
    public ReservationVO() {}

    public ReservationVO(int reserveID, String userID, int hotelID, String hotelName, Date startDate, Date endDate, int roomID) {
        this.reserveID = reserveID;
        this.userID = userID;
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomID = roomID;
    }

    public int getReserveID() {
        return reserveID;
    }

    public String getUserID() {
        return userID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public String getHotelName() {
        return hotelName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setReserveID(int reserveID) {
        this.reserveID = reserveID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }
}
