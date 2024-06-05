
public class Rating{
    private Integer nextid=1;
    private Integer RatingID;
    private String CostumerFullName;
    private Integer CostumerRating;
    private Integer Costumerid;
    private String status;


    public Rating(Orders order){
        this.RatingID = nextid++;
        this.CostumerFullName =  order.getCostumerFullName();
        this.Costumerid = order.getCostumerId();
        this.status = order.getStatus();
        this.CostumerRating = null;
    }

    public Integer getRatingId(){
        return this.RatingID;
    }
    public void setRatingId(Integer RatingID){
        this.RatingID=RatingID;
    }

    public String getCostumerFullName(){
        return this.CostumerFullName;
    }
    public Integer getCostumerRating() {
        return this.CostumerRating;
    }

    public void setCostumerRating(Integer CostumerRating) {
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