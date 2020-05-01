package Server;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import com.google.gson.Gson;

class Server extends Observable {
    private static ArrayList<String> userIDs;
    private static HashMap<String,Account> Accounts;
    private static ArrayList<Product> soldExpired;
    private static ArrayList<Product> currentListings;
    private static ArrayList<String> emails;



    public static void main(String[] args) {
        soldExpired = new ArrayList<Product>();
        currentListings = new ArrayList<Product>();
        userIDs = new ArrayList<String>();
        Accounts = new HashMap<String,Account>();
        emails = new ArrayList<String>();
        new Server().runServer();
    }

    private void runServer() {
        try {
            setUpNetworking();
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private void setUpNetworking() throws Exception {
        @SuppressWarnings("resource")
        ServerSocket serverSock = new ServerSocket(4445);
        while (true) {
            Socket clientSocket = serverSock.accept();
            System.out.println("Connecting to... " + clientSocket);

            ClientHandler handler = new ClientHandler(this, clientSocket);
            this.addObserver(handler);

            Thread t = new Thread(handler);
            t.start();
        }
    }

    protected void processRequest(String input) {
        String output = "Error";
        Gson gson = new Gson();
        String[] variables = gson.fromJson(input, String[].class);
        try{
            System.out.println(variables[0]);
            if(variables[0].equals("create")){
                processAccount(variables);
            }
            else if(variables[0].equals("log-in")){
                processLogIn(variables);
            }
            else if(variables[0].equals("product")){
                processProduct(variables);
            }
            else if(variables[0].equals("see")){
                processSee(variables);
            }
            else System.out.println("failed to read");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected void processAccount(String[] variables){
        //TODO observers change to handler???
        if(userIDs.contains(variables[1])){
            this.setChanged();
            this.notifyObservers("username already taken");         //TODO: change to individual user
            return;
        }
        //TODO: or if invalid email
        else if(emails.contains(variables[3])){
            this.setChanged();
            this.notifyObservers("An account has already been made with this email");
            return;
        }
        Account temp = new Account(variables[1], variables[2],variables[3]);
        userIDs.add(variables[1]);
        emails.add(variables[3]);
        Accounts.put(variables[1],temp);
        this.setChanged();
        this.notifyObservers("Account successfully created");
    }

    protected void processLogIn(String[] variables){
        //TODO observers change to handler
        if(Accounts.containsKey(variables[1])) {
            if (Accounts.get(variables[1]).password.equals(variables[2])) {
                this.setChanged();
                this.notifyObservers("successful log-in");
                return;
            } else {
                this.setChanged();
                this.notifyObservers("Invalid Username or Password");
                return;
            }
        }
        this.setChanged();
        this.notifyObservers("Account not Found");
    }

    protected void processProduct(String[] variables){
        //productName, minBid, buyItNow, condition, auctionEndDate, shippingIncluded, returns, description, userId
        Product temp = new Product(variables[1], Double.valueOf(variables[2]), Double.valueOf(variables[3]), Boolean.parseBoolean(variables[4]), variables[5], Double.valueOf(variables[6]), Integer.parseInt(variables[7]), variables[8], variables[9]);
        currentListings.add(temp);
    }

    protected void processSee(String[] variables){
        if(currentListings.size() <= Integer.parseInt(variables[1])){
            this.setChanged();
            this.notifyObservers("Invalid Product");
        }
        Product temp = currentListings.get(Integer.parseInt(variables[1]));
        temp.toString();
        this.setChanged();
        this.notifyObservers(temp.toString() + "\n" + temp.timeLeft());
    }

    protected void bid(String[] variables){
        //TODO cant bid without account need to assign account to each individual client
    }

}