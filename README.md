# Project 1 @ CSC 201 Fall 2025: Binary Search Tree (Part 2)

## Pledged Work Policy

This is a ___Pledged Work___ assignment.  This means that the work you submit for grading ___must___ be your work product.  
You may not submit the work of others outside of your team, or the modification of work of others outside of your team.
You are encouraged to talk with each other about general problems.  For example, you may talk to someone about "What does it mean when the compiler says there is a semicolon missing on line 20", or "I can not get my assignment template to download from GitHub, what did you do?".  However, you may not engage in "Could you send me a copy of your work so I can see how to get started?".  You may get full and detailed assistance from me, the Teaching Assistant (TA), and the TAs in the Computer Science Center.  If you have any question about the appropriateness of assistance, do not hesitate to consult with me.

If I believe you have violated our ___Pledge Work___ agreement, I will pursue this matter through the college Honor Council.

## Overview

Database software typically comprises two essential components: an interface for user interaction and a data structure for storing data and supporting various functions. This project involves the creation of a program that manages a Binary Search Tree (BST).

In this project, you will implement:

1. A generic BST with iterator interface.
2. A parser capable of reading and processing input commands.
3. _A BST that can accommodate user-defined data types._

## Invocation and I/O Files:

Just like in the previous part, the name of the program is `Proj1` ( provided with a `main` method in`Proj1.java` ).

You are encouraged to run and debug code in __IntelliJ IDEA__. Also, the program can be invoked from the command-line as:

```shell
java Proj1 {command-file}
```

For Section3, you will create your own input file `input.txt` and output file `output.txt`, similar to the one you created for Sections 1 and 2, which will be used for grading.

## 3. **BST for user-defined data types**

For this part of the lab, you copy over your program from Part 1 and adapt it to work more generically. You will have to find your own data set from an online repository such as [https://www.kaggle.com/](https://www.kaggle.com/). The data set you choose has only three requirements: 1) it must be in a text file in CSV format, 2) it must have between 100 and 100,000 entries, and 3) it must be meaningful in some way to you. You will create a class for the data in your dataset. You will read from the dataset and fill a BST with objects of your custom class.

1. Download your own dataset.
2. Create a well written class to store each record from your dataset. The members of this class must include all attributes in your dataset. This class must contain the following methods beside the default, parametrized, and copy constructors:
    - `public String toString()`: This method should return a string representation of the object in the format of your choosing.
    - `public boolean equals(Object obj)`: This method should return true if the object is equal to the object passed as a parameter.
    - `public int compareTo(T obj)`: This method should return a negative number if the object is less than the object passed as a parameter, a positive number if the object is greater than the object passed as a parameter, and 0 if the objects are equal.
3. Create a parser that reads from your dataset and fills a BST with objects of your custom class.   

## Submission:

Your project will be developed and graded via GitHub. Your final "push" is your final submission, and it must occur before it is due. On Canvas, enter the url to your Github repository. Your project will not be graded without it.

## Recommendations:

I ___strongly suggest___ that you carefully think through your strategy before just jumping into the code.  Once that is working, start adding in new features individually.  A good place to start is building your class.

*In order to get full points of Commenting and Code Style, you need to add comments to every methods and head comments for each file (providing file description, author, date, and acknowledgement).

```
/∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗*
∗ @file: filename.java
∗ @description: This program implements . . .
∗ @author: Your Name
∗ @date: September 26, 2025
∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗∗/
```



## Note for the Grader regarding Running the Program:
* The program must be invoked from the command-line as:

```shell
java Proj1.java {testInputFile} {dataset}
```

*  Command Input Format: When adding commands that involve a player (like `insert`, `remove`, or `search`) in the testInputFile, each successful command must be followed by the full player data as comma-separated values in the following order:
```
[Name, JerseyNumber, Club, Position, Nationality, Age, Appearances, Wins, Goals, Assists]
```

