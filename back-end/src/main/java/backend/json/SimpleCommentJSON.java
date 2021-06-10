package backend.json;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.sql.Timestamp;

public class SimpleCommentJSON {
    private String body;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private Timestamp createdAt;

    private int idUser;

    public String getBody() {
        return body;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public int getIdUser() {
        return idUser;
    }

    public SimpleCommentJSON(String body, Timestamp createdAt, int idUser) {
        this.body = body;
        this.createdAt = createdAt;
        this.idUser = idUser;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}
