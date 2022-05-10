
package apitests.hrStudying.hr;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Item {

    @SerializedName("region_id")
    @Expose
    private Integer region_id;
    @SerializedName("region_name")
    @Expose
    private String region_name;
    @SerializedName("links")
    @Expose
    private List<Link> links = null;

    /**
     * No args constructor for use in serialization
     * 
     */
    public Item() {
    }

    /**
     * 
     * @param region_id
     * @param region_name
     * @param links
     */
    public Item(Integer region_id, String region_name, List<Link> links) {
        super();
        this.region_id = region_id;
        this.region_name = region_name;
        this.links = links;
    }

    public Integer getregion_id() {
        return region_id;
    }

    public void setregion_id(Integer region_id) {
        this.region_id = region_id;
    }

    public String getregion_name() {
        return region_name;
    }

    public void setregion_name(String region_name) {
        this.region_name = region_name;
    }

    public List<Link> getLinks() {
        return links;
    }

    public void setLinks(List<Link> links) {
        this.links = links;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Item.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("region_id");
        sb.append('=');
        sb.append(((this.region_id == null)?"<null>":this.region_id));
        sb.append(',');
        sb.append("region_name");
        sb.append('=');
        sb.append(((this.region_name == null)?"<null>":this.region_name));
        sb.append(',');
        sb.append("links");
        sb.append('=');
        sb.append(((this.links == null)?"<null>":this.links));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

}
