package apitests.day6_POJO;

public class RegionPost { //day7

    private int region_id;
    private String region_name;

    public RegionPost() {
    }

    public int getRegion_id() {
        return region_id;
    }

    public void setRegion_id(int region_id) {
        this.region_id = region_id;
    }

    public String getRegion_name() {
        return region_name;
    }

    public void setRegion_name(String region_name) {
        this.region_name = region_name;
    }

    public RegionPost(int region_id, String region_name) {
        this.region_id = region_id;
        this.region_name = region_name;
    }
}
