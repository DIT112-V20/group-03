package online.quar.application.model;

import java.util.ArrayList;

public class Route extends CarControlInput{

    ArrayList<CarControlInput> dirAry = new ArrayList<CarControlInput>();
    ArrayList<Long> durAry = new ArrayList<Long>();
    long routStartEpoch;

    public Route(Long routStartEpoch, long carID){
        this.routStartEpoch = routStartEpoch;
    }

    public void setNewMove(CarControlInput newDir, Long newDur){
        dirAry.add(newDir);
        durAry.add(newDur - routStartEpoch);        //converts to ms
        //routStartEpoch = System.currentTimeMillis();
    }

    public CarControlInput getDirAt(int indx){
        return dirAry.get(indx);
    }
    public long getDurAt(int indx) {
        return durAry.get(indx);
    }

    public ArrayList<CarControlInput> getDirAry() {
        return dirAry;
    }

    public ArrayList<Long> getDurAry() {
        return durAry;
    }

    public void clearArray(){
        for(int x=0;x<durAry.size();x++){        //ladida removeAll is better than Clear - screw NULL! (also removeAll is something to look into)
            durAry.remove(x);
            dirAry.remove(x);
        }

    }
}
