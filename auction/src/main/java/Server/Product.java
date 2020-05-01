package Server;

import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;

public class Product {
    //image?

    private String productName;
    private Double currentBid;
    private Double buyItNow;
    private int bids;
    private String condition;
    private String auctionEndDate;
    private String shippingIncluded;
    private String returns;
    private String description;
    private String currentHighestBidder; //user id
    private String owner;    //user id
    private Boolean expired;


    public String timeLeft(){
        if(expired){return null;}
        DateTimeFormatter fmt = DateTimeFormatter.ISO_ZONED_DATE_TIME;
        Instant event = fmt.parse(this.auctionEndDate, Instant::from);
        Instant now = Instant.now().minus(5, ChronoUnit.HOURS);
        System.out.println(now);
        Duration diff = Duration.between(now,event);
        long days = diff.toDays();
        long hours = diff.toHours()-24*diff.toDays();
        long minutes = diff.toMinutes()-60*diff.toHours();
        long seconds = diff.getSeconds()-60*diff.toMinutes();
        return Long.toString(days) + "days " + Long.toString(hours) + "hours " + Long.toString(minutes) + "minutes " + Long.toString(seconds) + "seconds";
    }

    public Product(String productName, Double minBid, Double buyItNow, boolean condition, String auctionEndDate, Double shippingIncluded, int returns, String description, String owner){
        this.productName = productName;
        this.currentBid = minBid;
        this.buyItNow = buyItNow;
        this.bids = 0;
        if(condition){this.condition = "New";}else{this.condition = "Used";}
        this.auctionEndDate = auctionEndDate;  //date string format
        if(shippingIncluded == null){this.shippingIncluded = "FREE";}
            else{this.shippingIncluded = "$ " + shippingIncluded.toString();};
        if(returns == 0){this.returns = "No Returns";} else{this.returns = returns + "-day returns";}
        this.description = description;
        this.owner = owner;
        this.expired = false;
    }

    synchronized public String bid(double bidValue, String Bidder){
        if(expired){return "Sorry this item has expired";}
        if(bidValue > currentBid){
            currentBid = bidValue;
            currentHighestBidder = Bidder;
            bids++;
            if(bidValue >=buyItNow){
                if(shippingIncluded != null){
                    return "Your bid has exceeded the BuyItNow Price, Your item has been purchased for: $" + buyItNow + " + $" + shippingIncluded;
                }
                else{return "Your bid has exceeded the BuyItNow Price, Your item has been purchased for: $" + buyItNow;}
            }
            return "Your bid has been placed - bid number: " + bids;
        }
        return "Sorry, your bid is too small";
    }

    public void debug(){
        System.out.println("product name: " + productName);
        System.out.println("currentBid: " + currentBid);
        System.out.println("buyItNow: " + buyItNow);
        System.out.println("bids: " + bids);
        System.out.println("condition: " + condition);
        System.out.println("auctionEndDate: " + auctionEndDate);
        System.out.println("shippingIncluded: " + shippingIncluded);
        System.out.println("returns: " + returns);
        System.out.println("description: " + description);
        System.out.println("currentHighestBidder: " + currentHighestBidder);
        System.out.println("owner: " + owner);
        System.out.println("expired: " + expired);
    }

}
