//package Server;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//public class tester {
//    private static ArrayList<String> userIDs;
//    private static HashMap<String,Account> Accounts;
//
//    public static void main(String[] args){
//        userIDs = new ArrayList<String>();
//        Accounts = new HashMap<String,Account>();
//        //make account;
//        String username = "Reto"; //need to test if in userIDs
//        String password = "password";
//        String email = "rteruya@utexas.edu";
//        Account account1 = new Account(username,password,email);
//        userIDs.add(username);
//        Accounts.put(username, account1);
//
//        String tiName = "TI-84 C Silver Edition";
//        Double timinBid = 40.00;
//        //only 2 zero's
//        Double tibuyItNow = 100.00;
//        boolean tiCondtiion = false;
//        String tiAuctionEndDate = "2020-05-19T19:45:00Z";
//        Double tishippingIncluded = 5.00;
//        int tireturns = 30;
//        String tidescription =  "hello up for this auction is a used calculator comes with cover and usb cable please see the pictures for the  actual conditions    it have some scratches";
//        String tiowner = account1.username;
//
//        account1.newProduct(tiName, timinBid, tibuyItNow, tiCondtiion, tiAuctionEndDate, tishippingIncluded, tireturns, tidescription);
//        account1.currentListings.get(0).debug();
//        System.out.println(account1.currentListings.get(0).timeLeft());
//    }
//}
//product, TI-84 C Silver Edition,40,100,false,2020-05-19T19:45:00Z,5,30,hello up for this auction is a used calculator comes with cover and usb cable please see the pictures for the  actual conditions    it have some scratches,reto
