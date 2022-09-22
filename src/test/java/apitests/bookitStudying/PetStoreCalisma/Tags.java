package apitests.bookitStudying.PetStoreCalisma;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Tags {
    @SerializedName("id")
    @Expose
   private int id;
    @SerializedName("name")
    @Expose
    private String name;

    public Tags(){

    }

    public Tags(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Tags{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
