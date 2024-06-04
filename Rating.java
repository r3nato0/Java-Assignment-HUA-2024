
public class Rating{
    private String CostumerFirstName;
    private String CostumerLastName;
    private String CostumerRating;
    private Integer Costumerid;
    private String status;


    public Rating(Orders order){
        this.CostumerFirstName =  order.getCostumerFirstName();
        this.CostumerLastName =  order.GetCostumerLastName();
        this.Costumerid = order.getCostumerId();
        this.status = order.getStatus();
        this.CostumerRating = null;
    }

    public String getCostumerFirstName() {
        return this.CostumerFirstName;
    }

    public void setCostumerFirstName(String CostumerFirstName) {
        this.CostumerFirstName = CostumerFirstName;
    }

    public String getCostumerLastName() {
        return this.CostumerLastName;
    }

    public void setCostumerLastName(String CostumerLastName) {
        this.CostumerLastName = CostumerLastName;
    }

    public String getCostumerRating() {
        return this.CostumerRating;
    }

    public void setCostumerRating(String CostumerRating) {
        this.CostumerRating = CostumerRating;
    }

    public Integer getCostumerid() {
        return this.Costumerid;
    }

    public void setCostumerid(Integer Costumerid) {
        this.Costumerid = Costumerid;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}