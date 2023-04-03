package classes;

import processing.Processor;
import processing.Status;
import processing.StatusListener;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Subtractor implements Processor {
    private static int taskId=0;
    private String result = null;

    @Override
    public boolean submitTask(String task, StatusListener sl) {
        taskId++;
        AtomicInteger ai = new AtomicInteger(0);

        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        executorService.scheduleAtFixedRate(
                ()->{
                    ai.incrementAndGet();
                    sl.statusChanged(new Status(taskId,ai.get()));
                },
                1, 10, TimeUnit.MILLISECONDS);



        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            while (true) {

                if (ai.get() >= 100) {
                    if (task.contains("-")) {
                        String [] split = task.split("-");
                        int s1 = Integer.parseInt(split[0]);
                        int s2 = Integer.parseInt(split[1]);
                        int r = s1 - s2;
                        result = Integer.toString(r);
                    }
                    else result = "Not an allowed formula";
                    System.out.println("Finished");
                    executorService.shutdown();
                    executor.shutdown();
                    break;
                }
            }
        });

        return true;
    }

    @Override
    public String getInfo() {
        return "Subtracting two arguments";
    }

    @Override
    public String getResult() {
        return result;
    }

}
