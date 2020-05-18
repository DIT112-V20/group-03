package online.quar.application.model;

import org.springframework.security.task.DelegatingSecurityContextAsyncTaskExecutor;

import java.util.ArrayList;

public class Route extends CarControlInput{

    /*  potato prototype outline ----TEMPORARY NOTES ---- IGNORE ---------------------------------------------

        notes:      TODO create a "Record this rout" button in UI
                        TODO have a failsafe to make sure user cant record something like driving against a wall - simple MSG that outputs to user: "your driving is silly - rout recording canceled"   - see line 11 for prototype suggestion
                    TODO implement a method that records the movments of the joystick (inside the class that controlls the car somewhere)
                    AND  bundels them with a time stamp - whatever our refresh rate on the joystick is - is probobly good enough (wouldnt want a rout to have milions of miniture ajustments - but wouldnt want it to only go in 30° turns - looking 'challenged')
                        TODO create a new rout when clicked (save somewhere? we can have a single "record rout" "play rout" save slot inside this class - to keep it simple or to prototype
                        TODO have the integrated direction/speed and time stamp added to the 2d array

            TODO do we have a direction/speed object? should we have one? wouldnt that help?

        init class
            2D array [direction/speed][duration in ms?]
            timer


        class methods


                        getMovmentDirAt(int index)
                            return array[x][0]

                        getMovmentTimeAt(int index)
                            return array[x][1]

                        public void clearArray()
                            for(DELETE EVERYTHING AHHHHH

            TODO i got confused :D

            ((INSIDE CALLER METHOD))
            getAllMovement()
                while(done == false)
                    call car movments array[x][0]
                    sleep for array[x][1]
                    if(x==array.size())
                        done = true


----------------------------IGNORE!!-------------------------IGNORE!!----------------------------
*/


    ArrayList<CarControlInput> dirAry = new ArrayList<>();
    ArrayList<Long> durAry = new ArrayList<>();
    long routStartEpoch;

    public Route(Long routStartEpoch){
        this.routStartEpoch = routStartEpoch;
    }

    public void setNewMove(CarControlInput newDir, Long newDur){
        dirAry.add(newDir);
        durAry.add(newDur - routStartEpoch);        //converts to ms
    }

    public CarControlInput getDirAt(int indx){
        return dirAry.get(indx);
    }
    public long getDurAt(int indx) {
        return durAry.get(indx);
    }

    public void clearArray(){
        for(int x=0;x<durAry.size();x++){        //ladida removeAll is better than Clear - screw NULL! (also removeAll is something to look into)
            durAry.remove(x);
            dirAry.remove(x);
        }

    }
}
