package Server;

import java.util.ArrayList;

public class Account {
    String username;
    String password;
    String email;
    Double rating;
    ArrayList<String> reviews;
    ArrayList<Product> soldExpired;
    ArrayList<Product> currentListings;
    //possibly can do email server? to message seller
    public Account(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.rating = 0.00;
        this.email = email;
        reviews = new ArrayList<String>();
        soldExpired = new ArrayList<Product>();
        currentListings = new ArrayList<Product>();
    }

    public void newProduct(String productName, Double minBid, Double buyItNow, boolean condition, String auctionEndDate, Double shippingIncluded, int returns, String description){
        Product temp = new Product(productName, minBid, buyItNow, condition, auctionEndDate, shippingIncluded, returns, description, this.username);
        currentListings.add(temp);
    }

    public void addReview(String review, Double rating){
        this.rating = (this.rating*reviews.size() + rating)/(reviews.size()+1);
        reviews.add(review);
    }

    public void updateSoldExpired(Product temp){
        currentListings.remove(temp);
        soldExpired.add(temp);
    }

    public String toString(){
        return this.username;
    }
}
