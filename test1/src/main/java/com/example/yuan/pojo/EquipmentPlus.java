package com.example.yuan.pojo;

public class EquipmentPlus {

    private Integer Eid;
    public String Ename;
    private Integer Cid;
    private Integer Lid;
    public  String Cname;
    public String Lname;
    private Integer Flag;
    private String Flagmsg;
    public EquipmentPlus setEquipment(Equipment e,Category c,Laboratory l,String msg){
        EquipmentPlus p=new EquipmentPlus();
        p.Eid=e.getEid();
        p.Ename=e.getEname();
        p.Cid=e.getCid();
        p.Lid=c.getLid();
        p.Cname=c.getCname();
        p.Lname=l.getLname();
        p.Flag=e.getFlag();
        p.Flagmsg=msg;
        return p;
    }

    public String getFlagmsg() {
        return Flagmsg;
    }

    @Override
    public String toString() {
        return "EquipmentPlus{" +
                "Eid=" + Eid +
                ", Ename='" + Ename + '\'' +
                ", Cid=" + Cid +
                ", Lid=" + Lid +
                ", Cname='" + Cname + '\'' +
                ", Lname='" + Lname + '\'' +
                ", Flag=" + Flag +
                '}';
    }

    public String getLname() {
        return Lname;
    }

    public void setEname(String ename) {
        Ename = ename;
    }

    public void setEid(Integer eid) {
        Eid = eid;
    }
    public String getEname() {
        return Ename;
    }

    public Integer getEid() {
        return Eid;
    }

    public void setLname(String lname) {
        Lname = lname;
    }


    public void setCid(Integer cid) {
        Cid = cid;
    }

    public void setCname(String cname) {
        Cname = cname;
    }

    public void setLid(Integer lid) {
        Lid = lid;
    }

    public Integer getCid() {
        return Cid;
    }

    public Integer getLid() {
        return Lid;
    }

    public String getCname() {
        return Cname;
    }

}
