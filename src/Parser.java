/******************************************************************
 * @file :                     Parser.java
 * @description:               This class reads commands from an input file and executes operations on a binary search tree (BST) of integers.
 *                             It parses each line into a command (like insert, remove, search, clear, isEmpty, print) along with an optional
 *                             integer argument. It performs the command on the BST, and then logs the results into an output file (result.txt).
 * @author:                    Elham Fayzi
 * @date:                      Sep 17, 2025
 ******************************************************************/

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Scanner;

public class Parser {

    //Create a BST tree of Integer type
    private BST<Player> mybst = new BST<>();

    // Constructor: takes an input filename for test
    public Parser(String filename) throws FileNotFoundException {                   // make sure the naming is less confusing
        File dataSet = new File("./AllTimePremierLeaguePlayerStatistics.csv");
        parsePlayers(dataSet);

        process(new File(filename));
    }

    public void parsePlayers(File dataSet) throws FileNotFoundException {
        FileInputStream fis = new FileInputStream(dataSet);
        Scanner reader = new Scanner(fis);
        reader.nextLine();

        int lineNumber = 0;
        while (reader.hasNextLine()) {
            lineNumber++;

            String[] line = reader.nextLine().split(",");
            Player player;
            try {
                player = new Player(line);
                mybst.insert(player);
            }
            catch (Exception e) {
                System.out.println("Line Number " + lineNumber + " has an invalid format.");
            }
        }
    }

    // Reads commands from an input file and prepares them for execution
    public void process(File input) throws FileNotFoundException {
        FileInputStream fis  = new FileInputStream(input);
        Scanner reader = new Scanner(fis);

        ArrayList<String> arr = new ArrayList<String>();
        while (reader.hasNext()) {
            String line = reader.nextLine();
            if (line.length() == 0) continue;

            StringBuilder str = new StringBuilder();                // stores letters (command)
            StringBuilder digits = new StringBuilder();             // stores digits (arguments for the commands)

            for (int i = 0; i < line.length(); i++) {
                char ch = line.charAt(i);
                if (Character.isLetter(ch)) {
                    str.append(ch);
                }
                else if (Character.isDigit(ch)) {
                    digits.append(ch);
                }
            }

            // Adds command and number to array in this way to ensure that each command is immediately followed by its associated argument if any or an empty element if none
            arr.add(str.toString());
            arr.add(digits.toString());
        }

        // Convert ArrayList to array
        String[] commands = arr.toArray(new String[arr.size()]);
        reader.close();
        operate_BST(commands);
    }

    // Executes a series of BST operations based on commands
    public void operate_BST(String[] command) {
        if (command.length == 0) return;

        Player pl = new Player(command[1]);

        switch (command[0]) {
            case "insert" -> {
                mybst.insert(pl);
                writeToFile("inserted [" + pl.toString() + "]", "./result.txt");
            }

            case "remove" -> {
                Player removed = mybst.remove(pl);
                if (removed == null) {
                    writeToFile("remove failed ", "./result.txt");
                }
                else {
                    writeToFile("removed [" + removed.toString() + "]", "./result.txt");
                }
            }
            case "search" -> {
                Player found = mybst.search(pl);
                if (found == null) {
                    writeToFile("search failed ", "./result.txt");
                }
                else {
                    writeToFile("found [" + found.toString() + "]", "./result.txt");
                }
            }

            case "clear" -> {
                mybst.clear();
                writeToFile("Tree cleared", "./result.txt");
            }

            case "isEmpty" -> {
                if (mybst.size() == 0) {
                    writeToFile("Tree is empty", "./result.txt");
                } else {
                    writeToFile("Tree is not empty", "./result.txt");
                }
            }

            case "print" -> {
                Iterator<Player> iter = mybst.iterator();
                StringBuilder str = new StringBuilder();
                while (iter.hasNext()) {
                    str.append(iter.next());
                    str.append(" ");
                }
                writeToFile(str.toString(), "./result.txt");
            }

            // default case for Invalid Command
            default -> writeToFile("Invalid Command", "./result.txt");
        }

        operate_BST(Arrays.copyOfRange(command, 2, command.length));
    }

    // Writes a line of output to a file (appends if file already exists).
    public void writeToFile (String content, String filePath) {
        try {
            FileOutputStream fos = new FileOutputStream(filePath, true);
            PrintWriter writer = new PrintWriter(fos);

            writer.println(content);
            writer.flush();
        } catch (FileNotFoundException e) {
            System.err.println("File can't be created in the specified directory.");
            System.exit(1);
        }
    }
}
