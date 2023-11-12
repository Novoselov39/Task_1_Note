package data;
import java.util.Date;

public class Note {
    private int id;
    private String nameNote;
    private String body;
    private Date time;
    


    
    public Note(int id, String nameNote, String body, Date time) {
        this.id = id;
        this.nameNote = nameNote;
        this.body = body;
        this.time = time;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNameNote() {
        return nameNote;
    }
    public void setNameNote(String nameNote) {
        this.nameNote = nameNote;
    }
    public String getBody() {
        return body;
    }
    public void setBody(String body) {
        this.body = body;
    }
    public Date getTime() {
        return time;
    }
    public void setTime(Date time) {
        this.time = time;
    }


    @Override
    public String toString() {
        return "Note [id=" + id + ", nameNote=" + nameNote + ", body=" + body + ", time=" + time + "]";
    }

    
    
    
}
