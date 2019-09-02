package com.example.synd;

public class Crowd_Count {

    String cashcounter,cheque,loan_dept,passbook;

    public Crowd_Count(String cashcounter, String cheque, String loan_dept, String passbook) {
        this.cashcounter = cashcounter;
        this.cheque = cheque;
        this.loan_dept = loan_dept;
        this.passbook = passbook;
    }

    public Crowd_Count() {
    }

    public String getCashcounter() {
        return cashcounter;
    }

    public void setCashcounter(String cashcounter) {
        this.cashcounter = cashcounter;
    }

    public String getCheque() {
        return cheque;
    }

    public void setCheque(String cheque) {
        this.cheque = cheque;
    }

    public String getLoan_dept() {
        return loan_dept;
    }

    public void setLoan_dept(String loan_dept) {
        this.loan_dept = loan_dept;
    }

    public String getPassbook() {
        return passbook;
    }

    public void setPassbook(String passbook) {
        this.passbook = passbook;
    }
}
