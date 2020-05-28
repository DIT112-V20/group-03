package online.quar.application.model;

public class Route {

    /*  potato prototype outline

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
            setNewMovement(direction/speed, timeStamp)
                calculate time spent after last command ( depending on how we pass the time either: [from epoch] or [ms since creation of timer] )
                add new arrayList item to 2D array[dir/spd][ms]

            getAllMovement()
                while(done == false)
                    call car movments array[x][0]
                    sleep for array[x][1]
                    if(x==array.size())
                        done = true

            getMovmentDirAt(int index)
                return array[x][0]

            getMovmentTimeAt(int index)
                return array[x][1]

            clearArray()
                for(DELETE EVERYTHING AHHHHH

            cleanArray() //takes out repetition
                create duplicateArray
                for(array.size() -1)
                    if not the same as next in direction/speed (time doesnt matter)
                        copy over to duplicateArray + addup time
                        addUpTime = 0;
                    else
                        addUpTime =+ next items time (? basicly compile the array so we dont have 2milion neutral commands....)

            TODO i got confused :D




     */







}
