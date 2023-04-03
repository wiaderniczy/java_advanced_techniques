package processing;

public interface Processor {

    boolean submitTask(String task, StatusListener sl);

    String getInfo();

    String getResult();
}
