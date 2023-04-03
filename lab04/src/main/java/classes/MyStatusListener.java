package classes;

import processing.Status;
import processing.StatusListener;

public class MyStatusListener implements StatusListener {
    @Override
    public void statusChanged(Status s) {
        System.out.println("Progress:"+s.getProgress()+" TaskId:" +s.getTaskId());
    }

}
