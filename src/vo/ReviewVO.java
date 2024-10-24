package vo;

public class ReviewVO {
    private int reviewID;
    private int hotelID;
    private String userID;
    private String content;
    private int star;

    public ReviewVO() {}

    public ReviewVO(int reviewID, int hotelID, String userID, String content, int star) {
        this.reviewID = reviewID;
        this.hotelID = hotelID;
        this.userID = userID;
        this.content = content;
        this.star = star;
    }

    public int getReviewID() {
        return reviewID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public String getUserID() {
        return userID;
    }

    public String getContent() {
        return content;
    }

    public int getStar() {
        return star;
    }

    public void setReviewID(int reviewID) {
        this.reviewID = reviewID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setStar(int star) {
        this.star = star;
    }
}
