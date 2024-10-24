package vo;

import java.sql.Date;

public class ReservationVO {
    private int reserveID;
    private String userID;
    private int hotelID;
    private String hotelName;
    private Date startDate;
    private Date endDate;
    private String roomID;

    public ReservationVO() {}

    public ReservationVO(int reserveID, String userID, int hotelID, String hotelName, Date startDate, Date endDate, String roomID) {
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

    public String getRoomID() {
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

    public void setRoomID(String roomID) {
        this.roomID = roomID;
    }
}
