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

            String[] line = reader.nextLine().toLowerCase().split(",");
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

        ArrayList<String[]> arr = new ArrayList<String[]>();
        while (reader.hasNext()) {
            String line = reader.nextLine().toLowerCase().trim();

            if (line.length() == 0) continue;

            String command;
            String strData = null;

            int firstSpace = line.indexOf(' ');
            if (firstSpace == -1) {
                command = line;
            }
            else {
                command = line.substring(0, firstSpace);
                strData = line.substring(firstSpace + 1).trim();
            }

            // Adds command and number to array in this way to ensure that each command is immediately followed by its associated argument if any or an empty element if none
            if (strData == null) {
                arr.add(new String[] {command});
            }
            else {
                String[] arrData = strData.split(",");
                String[] cmdAndData = new String[arrData.length + 1];
                cmdAndData[0] = command;
                for (int i = 0; i < arrData.length; i++) {
                    cmdAndData[i + 1] = arrData[i].trim();
                }

                arr.add(cmdAndData);
            }
        }

        reader.close();

        for (String[] commandAndData : arr) {
            operate_BST(commandAndData);
        }
    }

    // Executes a series of BST operations based on commands
    public void operate_BST(String[] command) {
        if (command.length == 0) return;

        Player pl = null;
        if (command.length > 1) {
            try {
                pl = new Player(Arrays.copyOfRange(command, 1, command.length));
            } catch (Exception e) {
                String[] badData = Arrays.copyOfRange(command, 1, command.length);
                writeToFile("Invalid player data for " + Arrays.toString(badData),"./result.txt");
                return;
            }
        }

        switch (command[0]) {
            case "insert" -> {
                if (pl == null) {
                    writeToFile("Invalid Command", "./result.txt");
                    return;
                }
                mybst.insert(pl);
                writeToFile("inserted [" + pl.toString() + "]", "./result.txt");
            }

            case "remove" -> {
                if (pl == null) {
                    writeToFile("Invalid Command", "./result.txt");
                    return;
                }
                Player removed = mybst.remove(pl);
                if (removed == null) {
                    writeToFile("remove failed ", "./result.txt");
                }
                else {
                    writeToFile("removed [" + removed.toString() + "]", "./result.txt");
                }
            }
            case "search" -> {
                if (pl == null) {
                    writeToFile("Invalid Command", "./result.txt");
                    return;
                }
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

            case "isempty" -> {
                if (mybst.size() == 0) {
                    writeToFile("Tree is empty", "./result.txt");
                } else {
                    writeToFile("Tree is not empty", "./result.txt");
                }
            }

            case "print" -> {
                Iterator<Player> iter = mybst.iterator();
                while (iter.hasNext()) {;
                    writeToFile(iter.next().toString(), "./printedTree.csv");
                }
                writeToFile("Printed in printedTree.csv", "./result.txt");
            }

            // default case for Invalid Command
            default -> writeToFile("Invalid Command", "./result.txt");
        }
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
