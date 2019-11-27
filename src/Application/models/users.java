package Application.models;
import java.util.ArrayList;


public class users {
        private String namFirst;
        private String namLast;
        private String userID;
        private String email;
        private ArrayList<Accounts> accounts;
        private String password;

    public users(String namFirst, String namLast, String email, String password){
        this.namFirst = namFirst;
        this.namLast = namLast;
        this.email = email;
        this.accounts = new ArrayList<Accounts>();
        this.password = password;
    }


        public String getNamFirst() { return namFirst; }
        public void setNamFirst(String namFirst) {
            this.namFirst = namFirst;
        }
        public String getNamLast() {
            return namLast;
        }
        public void setNamLast(String namLast) {
            this.namLast = namLast;
        }
        public String getUserID() {
            return userID;
        }
        public void setUserID(String userID) {
            this.userID = userID;
        }
        public String getEmail() {
            return email;
        }
        public void setEmail(String email) {
            this.email = email;
        }
        public ArrayList<Accounts> getAccounts() {
        return accounts;
     }
        public void setAccounts(ArrayList<Accounts> accounts) {
        this.accounts = accounts;
    }
        public String getPassword() {
        return password;
    }
        public void setPassword(String password) {
        this.password = password;
    }




}
