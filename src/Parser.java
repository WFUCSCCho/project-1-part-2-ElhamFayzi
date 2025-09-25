/******************************************************************
 * @file :                     Parser.java
 * @description:               This class loads player records from a CSV dataset into a binary search tree (BST) of Player objects. It then reads commands
 *                             from an input file and performs operations on the BST. Each command may include a full player record (as comma-separated values)
 *                             or no arguments, depending on the operation. The results of each command are written to an output file (result.txt), and the print
 *                             command writes the tree contents to a CSV file (printedTree.csv).
 * @author:                    Elham Fayzi
 * @date:                      Sep 25, 2025
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
    public Parser(String filename) throws FileNotFoundException {
        File dataSet = new File("./AllTimePremierLeaguePlayerStatistics.csv");
        parsePlayers(dataSet);              // Load dataset into BST

        process(new File(filename));        // Process test commands from input file
    }

    // Parses the dataset CSV file and inserts Player objects into the BST
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
                // Skip invalid lines and notify
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

            // Prepare command array: first element = command, rest = data
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

        // If there is player data, try to create a Player object
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
