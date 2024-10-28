package vo;

import java.sql.Date;

public class ReservationVO {
    private int reserveID;
    private String userID;
    private int hotelID;
    private String hotelName;
    private Date startDate;
    private Date endDate;
    private int price;
    private int roomNumber;
    private int roomID;
    private String roomType;

    public ReservationVO(int reserveID, String userID, int hotelID, String hotelName, Date startDate, Date endDate, int roomID) {
        this.reserveID = reserveID;    // 예약 ID 초기화
        this.userID = userID;          // 유저 ID 초기화
        this.hotelID = hotelID;        // 호텔 ID 초기화
        this.hotelName = hotelName;    // 호텔 이름 초기화
        this.startDate = startDate;    // 체크인 날짜 초기화
        this.endDate = endDate;        // 체크아웃 날짜 초기화
        this.roomID = roomID;          // 방 ID 초기화
    }

    public String getRoomType() {
        return roomType;
    }

    public ReservationVO(int reserveID, Date startDate, Date endDate, int roomID) {
        this.reserveID = reserveID;
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomID = roomID;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public ReservationVO(int roomID, int hotelID, String roomType, int price, int roomNumber) {
        this.roomID = roomID;
        this.hotelID = hotelID;
        this.roomType = roomType;
        this.price = price;
        this.roomNumber = roomNumber;
    }

    public int getReserveID() {
        return reserveID;
    }

    public void setReserveID(int reserveID) {
        this.reserveID = reserveID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getRoomID() {
        return roomID;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public ReservationVO(int reserveID, String userID, int hotelID, String hotelName, Date startDate, Date endDate, int price, int roomNumber, int roomID, String roomType) {
        this.reserveID = reserveID;
        this.userID = userID;
        this.hotelID = hotelID;
        this.hotelName = hotelName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.roomNumber = roomNumber;
        this.roomID = roomID;
        this.roomType = roomType;
    }
}

