package joc.rejsekortjoc.HelpClasses;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import io.realm.RealmObject;

/**
 * Created by justinas on 4/25/17.
 */

public class History  extends RealmObject{

    private String uniqueID = null;
    private Integer startStation = null;
    private Integer endStation =null;
    private Double paidPrice = null;
    private String user = null;
    private String startDate = null;
    private String endDate = null;

    public History(Integer mStartStation, Integer mEndStation, double mPaidPrice, String username, String mStartDate, String mEndDate){
        uniqueID = UUID.randomUUID().toString();
        startStation = mStartStation;
        endStation = mEndStation;
        paidPrice = mPaidPrice;
        user = username;
        startDate = mStartDate;
        endDate = mEndDate;

    }
    public History(){
        uniqueID = "";
        startStation = -1;
        endStation = -1;
        paidPrice = 0.0;
        user = "";
        startDate = "";
        endDate = "";

    }
    private String toString(History history) {

        return "Start Station : "+history.getStartStation()+" End Station: "+history.getEndStation()+" Price: "+history.getPaidPrice()
                + " Start Date: " +history.getStartDate() + " End Date: " + history.getEndDate();
    }
    public List<String> toList(List<History> list){
        List<String> newList = new ArrayList<>();
        for (History hist: list){

            newList.add(toString(hist));

        }
        return newList;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getStartStation() {
        return startStation;
    }

    public void setStartStation(Integer startStation) {
        this.startStation = startStation;
    }

    public Integer getEndStation() {
        return endStation;
    }

    public void setEndStation(Integer endStation) {
        this.endStation = endStation;
    }

    public Double getPaidPrice() {
        return paidPrice;
    }

    public void setPaidPrice(Double paidPrice) {
        this.paidPrice = paidPrice;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
