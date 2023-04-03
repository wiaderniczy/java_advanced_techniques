package classloader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import app.App;
import classes.MyStatusListener;
import processing.StatusListener;

public class ClassHandler {

    private static ArrayList<Object> classes = new ArrayList<Object>();

    public static void load(String loadedClass, String task) {

        MyClassLoader loader = new MyClassLoader();
        Class<?> p = loader.findClass(loadedClass);

        try {
            Constructor<?> cp = p.getConstructor();

            Object o = cp.newInstance();

            classes.add(o);


        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            e.printStackTrace();
        }
    }

    public static void execute(Class<?> p, Object o, String task){
        try {
            Method getInfoMethod = p.getDeclaredMethod("getInfo");
            System.out.println((String)getInfoMethod.invoke(o));

            Method submitTaskMethod = p.getDeclaredMethod("submitTask", String.class, StatusListener.class);

            boolean b = (boolean) submitTaskMethod.invoke(o, task, new MyStatusListener());


            if (b)
                System.out.println("Processing started correctly");
            else
                System.out.println("Processing ended with an error");


            Method getResultMethod = p.getDeclaredMethod("getResult");

            ExecutorService executor = Executors.newSingleThreadExecutor();

            executor.submit(() -> {
                String result = null;
                while (true) {

                    try {
                        Thread.sleep(800);
                        result = (String) getResultMethod.invoke(o);
                    } catch (InterruptedException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
                        e.printStackTrace();
                    }
                    if (result != null) {
                        System.out.println("Result: " + result);
                        executor.shutdown();
                        break;
                    }
                }
            });

        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Object> getList(){
        return classes;
    }
}
