package Application.models;


public class Accounts {
    private String accNum;
    private double currBalance;
    private String userID;
    //transaction


    public Accounts(String accNum, double currBalance, String userID){
        this.accNum = accNum;
        this.currBalance = currBalance;
        this.userID = userID;
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

}
