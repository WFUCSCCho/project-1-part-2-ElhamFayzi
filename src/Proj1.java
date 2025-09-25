/******************************************************************
 * @file :                     Proj1.java
 * @description:               The Proj1 class contains the main method that serves as the starting point of the program. It expects
 *                             exactly one command-line argument, which should be the path to an input file.
 * @author:                    Elham Fayzi
 * @date:                      Sep 17, 2025
 ******************************************************************/

import java.io.FileNotFoundException;

public class Proj1 {
    public static void main(String[] args) throws FileNotFoundException{
        if(args.length != 1){
            System.err.println("Argument count is invalid: " + args.length);
            System.exit(0);
        }
        new Parser(args[0]);
    }
}