package com.example.synd;

public class cslot {

    String slotid, slotdept, slottime, slotcomm;

    public cslot(){

    }


    public void setSlotid(String slotid) {
        this.slotid = slotid;
    }

    public void setSlotdept(String slotdept) {
        this.slotdept = slotdept;
    }

    public void setSlottime(String slottime) {
        this.slottime = slottime;
    }

    public void setSlotcomm(String slotcomm) {
        this.slotcomm = slotcomm;
    }

    public cslot(String slotid, String slotdept, String slottime, String slotcomm){
        this.slotid = slotid;
        this.slottime = slottime;
        this.slotcomm = slotcomm;
        this.slotdept = slotdept;

    }


    public String getSlotid() {
        return slotid;
    }

    public String getSlotdept() {
        return slotdept;
    }
    public String getSlottime() {
        return slottime;
    }

    public String getSlotcomm() {
        return slotcomm;
    }
}
