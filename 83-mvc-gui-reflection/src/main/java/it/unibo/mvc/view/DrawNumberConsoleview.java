package it.unibo.mvc.view;

import it.unibo.mvc.api.DrawNumberController;
import it.unibo.mvc.api.DrawNumberView;
import it.unibo.mvc.api.DrawResult;


    
public class DrawNumberConsoleView implements DrawNumberView{
    
    @Override
    public void setController(DrawNumberController observer) {

    }

    @Override
    public void start() {
        System.out.println("Send inputs on the other views in order to write on the console");
    }

    @Override
    public void result(DrawResult res) {
        System.out.println(res.getDescription());
    }

}