package joc.rejsekortjoc.HelpClasses;

/**
 * Created by justinas on 4/22/17.
 */

public class Station {

    private Integer startStation;
    private Integer endStation;
    private Double price;

    public Station(Integer start, Integer end,double price){

        this.price = price;
        this.startStation=start;
        this.endStation = end;
    }
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }



    public Integer getEndStation() {
        return endStation;
    }

    public void setEndStation(Integer endStation) {
        this.endStation = endStation;
    }

    public Integer getStartStation() {
        return startStation;
    }

    public void setStartStation(Integer startStation) {
        this.startStation = startStation;
    }


}
