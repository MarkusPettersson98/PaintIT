package com.PaintIT.app;

public class GuiStarter {

    /*
    * Main entry point
    * This is a necessary circumvention since JavaFX 11
    * See https://stackoverflow.com/questions/52653836/maven-shade-javafx-runtime-components-are-missing
    * */

    public static void main( String[] args ) {
        App.main(args);
    }
}
