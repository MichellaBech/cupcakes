package app.entities;

import java.util.List;

public class User
    {
        private int userId;
        private int userNumber;
        private String userMail;
        private String username;
        private String password;
        private String role;
        private double balance;


        public User(int userId, int userNumber, String userMail, String username, String password, String role, double balance)
        {
            this.userId = userId;
            this.userNumber = userNumber;
            this.userMail = userMail;
            this.username = username;
            this.password = password;
            this.role = role;
            this.balance = balance;
        }

        public User(){}

        public User(String username, double balance)
        {
            this.username = username;
            this.balance = balance;
        }


        public int getUserId()
        {
            return userId;
        }

        public int getUserNumber()
        {
            return userNumber;
        }

        public String getUserMail()
        {
            return userMail;
        }

        public String getUsername()
        {
            return username;
        }

        public String getPassword()
        {
            return password;
        }

        public String getRole()
        {
            return role;
        }

        public double getBalance()
        {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public String getUsernameAndBalanceString(List<User> users) {
            StringBuilder stringBuilder = new StringBuilder();
            for (User user : users) {
                stringBuilder.append("User: ").append(user.getUsername())
                        .append(", balance: ").append(user.getBalance())
                        .append("\n");
            }
            return stringBuilder.toString();
        }

        @Override
        public String toString()
        {
            return "User{" +
                    "userId=" + userId +
                    ", userNumber=" + userNumber + '\'' +
                    ", userMail=" + userMail + '\'' +
                    ", username='" + username + '\'' +
                    ", password='" + password + '\'' +
                    ", role='" + role + '\'' +
                    ", balance='" + balance + '\'' +
                    '}';
        }
    }
