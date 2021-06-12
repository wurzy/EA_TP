package backend.json;

public class RateResourceJSON {
    private int idUser;
    private int rating;

    public RateResourceJSON() {
    }

    public RateResourceJSON(int idUser, int rating) {
        this.idUser = idUser;
        this.rating = rating;
    }

    public int getIdUser() {
        return idUser;
    }

    public int getRating() {
        return rating;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
