package Server;

import java.util.ArrayList;

public class Account {
    String username;
    String password;
    String email;
    Double rating;
    ArrayList<String> reviews;
    //possibly can do email server? to message seller
    public Account(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.rating = 0.00;
        this.email = email;
        reviews = new ArrayList<String>();
    }



    public void addReview(String review, Double rating){
        this.rating = (this.rating*reviews.size() + rating)/(reviews.size()+1);
        reviews.add(review);
    }



    public String toString(){
        return this.username;
    }
}
