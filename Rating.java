
public class Rating{
    private String CostumerFullName;
    private String CostumerRating;
    private Integer Costumerid;
    private String status;


    public Rating(Orders order){
        this.CostumerFullName =  order.getCostumerFullName();
        this.Costumerid = order.getCostumerId();
        this.status = order.getStatus();
        this.CostumerRating = null;
    }


    public String getCostumerFullName(){
        return this.CostumerFullName;
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