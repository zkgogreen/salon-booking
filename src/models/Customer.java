package models;

public class Customer extends Person{

    private double wallet;
    private Membership member;

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public Membership getMember() {
        return member;
    }

    public void setMember(Membership member) {
        this.member = member;
    }
}
