//carOffer could be a subclass of offerList?
public class carOffer //extends offerList{
    private int quota;
    private String price;

    public carOffer(int quota, String price) {
        this.quota = quota;
        this.price = price;
    }

    public int getQuota() {
        return quota;
    }
    
    public void setQuota(int quota) {
        this.quota = quota;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
    
}