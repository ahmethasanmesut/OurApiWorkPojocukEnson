
package apitests.hrStudy2.hure;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Item {

    @SerializedName("employee_id")
    @Expose
    private Integer employee_id;
    @SerializedName("first_name")
    @Expose
    private String first_name;
    @SerializedName("last_name")
    @Expose
    private String last_name;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("phone_number")
    @Expose
    private String phone_number;
    @SerializedName("hire_date")
    @Expose
    private String hire_date;
    @SerializedName("job_id")
    @Expose
    private String job_id;
    @SerializedName("salary")
    @Expose
    private Integer salary;
    @SerializedName("commission_pct")
    @Expose
    private Object commission_pct;
    @SerializedName("manager_id")
    @Expose
    private Object manager_id;
    @SerializedName("department_id")
    @Expose
    private Integer department_id;
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
     * @param first_name
     * @param last_name
     * @param job_id
     * @param hire_date
     * @param phone_number
     * @param department_id
     * @param employee_id
     * @param links
     * @param manager_id
     * @param salary
     * @param email
     * @param commission_pct
     */
    public Item(Integer employee_id, String first_name, String last_name, String email, String phone_number, String hire_date, String job_id, Integer salary, Object commission_pct, Object manager_id, Integer department_id, List<Link> links) {
        super();
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.hire_date = hire_date;
        this.job_id = job_id;
        this.salary = salary;
        this.commission_pct = commission_pct;
        this.manager_id = manager_id;
        this.department_id = department_id;
        this.links = links;
    }

    public Integer getemployee_id() {
        return employee_id;
    }

    public void setemployee_id(Integer employee_id) {
        this.employee_id = employee_id;
    }

    public String getfirst_name() {
        return first_name;
    }

    public void setfirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getlast_name() {
        return last_name;
    }

    public void setlast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getphone_number() {
        return phone_number;
    }

    public void setphone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public String gethire_date() {
        return hire_date;
    }

    public void sethire_date(String hire_date) {
        this.hire_date = hire_date;
    }

    public String getjob_id() {
        return job_id;
    }

    public void setjob_id(String job_id) {
        this.job_id = job_id;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Object getcommission_pct() {
        return commission_pct;
    }

    public void setcommission_pct(Object commission_pct) {
        this.commission_pct = commission_pct;
    }

    public Object getmanager_id() {
        return manager_id;
    }

    public void setmanager_id(Object manager_id) {
        this.manager_id = manager_id;
    }

    public Integer getdepartment_id() {
        return department_id;
    }

    public void setdepartment_id(Integer department_id) {
        this.department_id = department_id;
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
        sb.append("employee_id");
        sb.append('=');
        sb.append(((this.employee_id == null)?"<null>":this.employee_id));
        sb.append(',');
        sb.append("first_name");
        sb.append('=');
        sb.append(((this.first_name == null)?"<null>":this.first_name));
        sb.append(',');
        sb.append("last_name");
        sb.append('=');
        sb.append(((this.last_name == null)?"<null>":this.last_name));
        sb.append(',');
        sb.append("email");
        sb.append('=');
        sb.append(((this.email == null)?"<null>":this.email));
        sb.append(',');
        sb.append("phone_number");
        sb.append('=');
        sb.append(((this.phone_number == null)?"<null>":this.phone_number));
        sb.append(',');
        sb.append("hire_date");
        sb.append('=');
        sb.append(((this.hire_date == null)?"<null>":this.hire_date));
        sb.append(',');
        sb.append("job_id");
        sb.append('=');
        sb.append(((this.job_id == null)?"<null>":this.job_id));
        sb.append(',');
        sb.append("salary");
        sb.append('=');
        sb.append(((this.salary == null)?"<null>":this.salary));
        sb.append(',');
        sb.append("commission_pct");
        sb.append('=');
        sb.append(((this.commission_pct == null)?"<null>":this.commission_pct));
        sb.append(',');
        sb.append("manager_id");
        sb.append('=');
        sb.append(((this.manager_id == null)?"<null>":this.manager_id));
        sb.append(',');
        sb.append("department_id");
        sb.append('=');
        sb.append(((this.department_id == null)?"<null>":this.department_id));
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
