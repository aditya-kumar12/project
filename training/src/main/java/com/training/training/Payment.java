package com.training.training;

public class Payment {

    String id;
    String uid;
    String mid;
    String tid;
    String amount;
    String remarks;

    public Payment(String id,String uid,String mid,String tid,String amount,String rem){
        this.id=id;
        this.uid=uid;
        this.mid=mid;
        this.tid=tid;
        this.amount=amount;
        this.remarks=rem;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getUid() {

        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
