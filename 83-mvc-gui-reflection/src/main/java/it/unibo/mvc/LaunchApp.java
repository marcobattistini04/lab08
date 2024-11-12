package it.unibo.mvc;

import java.lang.reflect.InvocationTargetException;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.controller.DrawNumberControllerImpl;
import it.unibo.mvc.model.DrawNumberImpl;


/**
 * Application entry-point.
 */
public final class LaunchApp {
    private final static String PACKAGE_NAME = "it.unibo.mvc.view";
    private LaunchApp() { }
    /**
     * Runs the application.
     *
     * @param args ignored
     * @throws ClassNotFoundException if the fetches class does not exist
     * @throws NoSuchMethodException if the 0-ary constructor do not exist
     * @throws InvocationTargetException if the constructor throws exceptions
     * @throws InstantiationException if the constructor throws exceptions
     * @throws IllegalAccessException in case of reflection issues
     * @throws IllegalArgumentException in case of reflection issues
     */
    /*
     * defined two generic variable containing packages of the views.
     * Added 3 views of each tipe with methods getConstructor().newInstance().
     * Both of these action throws several exception, specified at the beginning 
     * of the method main
     */
    public static void main(final String... args) 
            throws 
            ClassNotFoundException, 
            NoSuchMethodException,
            InstantiationException, 
            IllegalAccessException, 
            InvocationTargetException{
                final var model = new DrawNumberImpl();
                final DrawNumberController app = new DrawNumberControllerImpl(model);
               
                final var classCV = Class.forName( PACKAGE_NAME + ".DrawNumberConsoleView");
                final var classSv = Class.forName(PACKAGE_NAME + ".DrawNumberSwingView");
            
                for(int i=0; i < 3; i++) {
                    app.addView((DrawNumberView)classCV.getConstructor().newInstance());
                    app.addView((DrawNumberView)classSv.getConstructor().newInstance());
                }
            }
            
    
}
        

