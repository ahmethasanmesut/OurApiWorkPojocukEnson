package apitests.bookitStudying.PetStoreCalisma;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;
import java.util.Map;

public class PetStore {
    @SerializedName("id")
    @Expose
    private Long id;
    @SerializedName("category")
    @Expose
    private  Category category;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("photoUrls")
    @Expose
    private List<String> photoUrls = null;
    @SerializedName("tags")
    @Expose
    private List<Tags> tags = null;
    @SerializedName("status")
    @Expose
    private  String status;

    public PetStore(){

    }

    public PetStore(Long id, Category category, String name, List<String> photoUrls, List<Tags> tags, String status) {
        super();
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Category getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public List<String> getPhotoUrls() {
        return photoUrls;
    }

    public List<Tags> getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhotoUrls(List<String> photoUrls) {
        this.photoUrls = photoUrls;
    }

    public void setTags(List<Tags> tags) {
        this.tags = tags;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PetStore{" +
                "id=" + id +
                ", category=" + category +
                ", name='" + name + '\'' +
                ", photoUrls=" + photoUrls +
                ", tags=" + tags +
                ", status='" + status + '\'' +
                '}';
    }
}
