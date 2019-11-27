package Application.models;


import java.util.ArrayList;

public class Accounts {
    private String accNum;
    private String accType;
    private double currBalance;
    private String userID;
    //transaction array for transaction object
    private ArrayList<Transaction> transactions;

    public Accounts(String accNum, String accType, double currBalance, String userID){
        this.accNum = accNum;
        this.accType = accType;
        this.currBalance = currBalance;
        this.userID = userID;
        this.transactions = new ArrayList<Transaction>();
    }
    public String getAccNum() {
        return accNum;
    }
    public void setAccNum(String accNum) {
        this.accNum = accNum;
    }
    public double getCurrBalance() {
        return currBalance;
    }
    public void setCurrBalance(double currBalance) {
        this.currBalance = currBalance;
    }
    public String getUserID() {
        return userID;
    }
    public void setUserID(String userID) {
        this.userID = userID;
    }
    public String getAccType() {
        return accType;
    }
    public void setAccType(String accType) {
        this.accType = accType;
    }
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    public void setTransactions(ArrayList<Transaction> transactions) {
        this.transactions = transactions;
    }

}
