package classloader;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import app.App;
import classes.MyStatusListener;
import processing.StatusListener;

public class ClassHandler {

    public static void load(String loadedClass, String task) {

        MyClassLoader loader = new MyClassLoader();
        Class<?> p = loader.findClass(loadedClass);

        try {
            Constructor<?> cp = p.getConstructor();

            Object o = cp.newInstance();

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

        } catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
                | IllegalArgumentException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
