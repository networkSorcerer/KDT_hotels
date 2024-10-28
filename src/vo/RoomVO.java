package vo;

public class RoomVO {
    private int roomID;
    private int hotelID;
    private String roomType;
    private int price;
    private int roomNumber;

    public RoomVO() {}

    public RoomVO(int roomID, int hotelID, String roomType, int price, int roomNumber) {
        this.roomID = roomID;
        this.hotelID = hotelID;
        this.roomType = roomType;
        this.price = price;
        this.roomNumber = roomNumber;
    }

    public int getRoomID() {
        return roomID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public String getRoomType() {
        return roomType;
    }

    public int getPrice() {
        return price;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomID(int roomID) {
        this.roomID = roomID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }
}
