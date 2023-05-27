package model;

public class UserInformation {
    public String getName() {
        return name;
    }

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    private String password;
    private int winTimes;
    private int totalTimes;

    public UserInformation(String name,int winTimes,int totalTimes, String password) {
        this.winTimes=winTimes;
        this.totalTimes=totalTimes;
        this.name=name;
        this.password=password;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public int getWinTimes() {
        return winTimes;
    }

    public void setWinTimes(int winTimes) {
        this.winTimes = winTimes;
    }
    public int getTotalTimes() {
        return totalTimes;
    }

    public void setTotalTimes(int totalTimes) {
        this.totalTimes = totalTimes;
    }

    @Override
    public String toString() {
        return getWinTimes()+" "+getTotalTimes()+" "+getName()+" "+getPassword();
    }
}