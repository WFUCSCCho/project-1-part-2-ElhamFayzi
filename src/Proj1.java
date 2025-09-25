/******************************************************************
 * @file :                     Proj1.java
 * @description:               The Proj1 class contains the main method that serves as the starting point of the program. It expects
 *                             exactly two command-line argument: the first one must the path to the test input file, while the second one
 *                             must be the path to the data set.
 * @author:                    Elham Fayzi
 * @date:                      Sep 17, 2025
 ******************************************************************/

import java.io.FileNotFoundException;

public class Proj1 {
    public static void main(String[] args) throws FileNotFoundException{
        if(args.length != 2){
            System.err.println("Argument count is invalid: " + args.length);
            System.exit(0);
        }
        new Parser(args[0], args[1]);               // First argument must be the path to the test input file, and the second argument must be the path to the data set
    }
}