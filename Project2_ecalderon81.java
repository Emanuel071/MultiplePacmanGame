/**This program is to create a pacman game using grid/arrays 
 * and controllers to control the pacman movement 
 * @author Emanuel
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.util.Random;
import java.util.Scanner;
public class Project2_ecalderon81 
{
 /*   Private String path;
    Private boolean append_to_file = false;
    
    public Project2_ecalderon81(String append_to_file)*/
            
/*"/Users/Emanuel/Documents/"
                       + "NetBeansProjects/Project2_ecalderon81/"
                       + "src/" + textInput;*/
    public static void main(String[] args) throws FileNotFoundException, 
            IOException 
    {
        //sets up all the variables used
        //x & y are user inputs
        //i,j row and col are used for the loops to set grid and sift through
        //userinput are for commands
        //random is for cookie randomization
        Scanner input = new Scanner( System.in );
        Scanner text = new Scanner( System.in );
        String restore, textInput;
        int x,y = 0,i = 0,j = 0,userInput, direction, randomCookie;
        int random, position, row, col, numberPacman = 0, countPacman;
        //keeps track and sets up the random cookies 
        int countMove=0,countCookie=0, minCookie, maxCookie, newCookies; 
        double stat,percent;                   //finds statistic
        double cookie, cookieRound;    //helps set up cookies on grid
        char finalDirection = 0, load, character;
        
        //initial screen display and user inputs
        System.out.println("Restore previous game? (y/n)");
        restore = input.nextLine();
        load = restore.charAt(0);
        
        //question to load files 
        if(load == 'y')
        {   //below was meant to read the file but it was stated not to use I/O
            //was too late to change. Professor you are going to hate me 
            //for the messyness of this code....
            System.out.println("Enter file name to load ");
            System.out.println("(ex; filname.txt): ");
            textInput = input.nextLine();
            System.out.println();
            
            //reads in the file 
            FileReader fr = new FileReader("C:\\Users\\Emanuel\\Documents\\"
                + "NetBeansProjects\\Project2_ecalderon81\\src\\" + textInput);
            
            //sets the variables equal to the inputs of the 
            //characters 
            Scanner sc = new Scanner(fr);
            x = Integer.parseInt(sc.nextLine());
            System.out.println(x);
            y = Integer.parseInt(sc.nextLine());
            System.out.println(y);
            numberPacman = Integer.parseInt(sc.nextLine());
            
            //object pacman to set up for further data input
            PacMans[] PacMan = new PacMans[numberPacman]; 
                    
            for(countPacman=0;countPacman<numberPacman;countPacman++)
            {
                PacMan[countPacman] = new PacMans();
                PacMan[countPacman].setXPacman(Integer.parseInt(sc.nextLine()));
                System.out.println(PacMan[countPacman].getXPacman());
                
                PacMan[countPacman].setYPacman(Integer.parseInt(sc.nextLine()));
                System.out.println(PacMan[countPacman].getYPacman());
                        
                character = sc.nextLine().charAt(0);
                PacMan[countPacman].setDirection(character);
                System.out.println(PacMan[countPacman].getDirection());
                        
                PacMan[countPacman].setPacmanName(sc.nextLine());
                System.out.println(PacMan[countPacman].getPacmanName());
                        
                PacMan[countPacman].setCountMove(
                        Integer.parseInt(sc.nextLine()));
                System.out.println(PacMan[countPacman].getCountMove());
                        
                PacMan[countPacman].setCountCookie(
                        Integer.parseInt(sc.nextLine()));
                System.out.println(PacMan[countPacman].getCountCookie());
                        
            }
            char grid[][] = new char [x][y];
                    
            //data input to the variable 
            for (i = 0; i<x; i++)           //first grid setup
            {
                for (j = 0; j<y; j++)
                {
                    character = sc.nextLine().charAt(0);
                    grid[i][j] = character;       //dots on grid                                 
                }            
            } 
            
            printGameIntroduction();        //method: game intro print
            printCommandInstructions();     //method: print command instructions        
            printGrid(i, j, x, y, grid);    //method: prints grid
            //now you are running the program below 
            for (countPacman = 0;countPacman<numberPacman;countPacman++)
            {
                System.out.println();
                System.out.print("Command for PacMan:" 
                        + PacMan[countPacman].getPacmanName()); //switches name 
                System.out.println();
                System.out.println("#cookies currently eaten "
                        + PacMan[countPacman].getCountCookie());//cookie tracker       
            
                System.out.print("Command:");     //command word is seen
                userInput = input.nextInt();
                System.out.println();
                
                if(userInput ==4)
                {
                    break;
                }
                //this one ie long, i am sure there is a better way to do this
                //might be one thing to go back and make easier 
                else if (userInput == 3)          //choice 3 (moving)
                {
                    //counts moves for each pacman
                    countMove = PacMan[countPacman].getCountMove();
                    PacMan[countPacman].setCountMove(countMove + 1);
                    int newJ = PacMan[countPacman].getYPacman();
                    int newI = PacMan[countPacman].getXPacman();
                    //switch case to identify where pacman is looking
                    switch (grid[newI][newJ]) 
                    {
                        case '>':
                            grid[newI][newJ] = ' ';  //puts blank array
                            //checks to see if out of bounds on left grid
                            if (newJ==0)          
                            {       
                                grid[newI][newJ] = '>';
                                System.out.println("Out of bounds!");
                            }
                            //checks to see if in way of pacman
                            else if(grid[newI][newJ-1] == '>' || 
                                    grid[newI][newJ-1] == 'V' || 
                                    grid[newI][newJ-1] == '<' || 
                                    grid[newI][newJ-1] == '^')
                            {
                                grid[newI][newJ] = '>';
                                System.out.println("Watch out other Pacman!");
                            }
                            else            //perform the move
                            {                                       
                                newJ=newJ-1; //logic for actual move
                                //checks if cookie
                                if (grid[newI][newJ] != '.' && 
                                        grid[newI][newJ] != ' ') 
                                {
                                    //gets cookie number 
                                    newCookies = Character.getNumericValue(
                                            grid[newI][newJ]);
                                    countCookie = newCookies + countCookie;
                                    //sets new cookie number 
                                    PacMan[countPacman].setCountCookie(
                                            countCookie);
                                    System.out.println("Yum Yum. you ate ");
                                    System.out.println(newCookies + 
                                            " Cookies. That was good!");
                                }                      //for cookie
                                grid[newI][newJ] = '>'; 
                                //puts pacman in new location
                                PacMan[countPacman].setYPacman(newJ); 
                            } 
                            break;
                            //does same see above comments
                        case 'V':                  
                            grid[newI][newJ] = ' ';
                            //checks to see if out of bounds on top grid  
                            if (newI==0)
                            {       
                                grid[newI][newJ] = 'V';
                                System.out.println("Out of bounds!");
                            }
                            else if(grid[newI-1][newJ] == '>' || 
                                    grid[newI-1][newJ] == 'V' || 
                                    grid[newI-1][newJ] == '<' ||
                                    grid[newI-1][newJ] == '^')
                            {
                                grid[newI][newJ] = 'V';
                                System.out.println("Watch out other Pacman!");
                            }
                            else
                            {
                                newI=newI-1;
                                //checks if cookie
                                if (grid[newI][newJ] != '.' && 
                                        grid[newI][newJ] != ' ') 
                                {
                                    newCookies=
                                            Character.getNumericValue(
                                                    grid[newI][newJ]);
                                    countCookie = newCookies + countCookie;
                                    PacMan[countPacman].setCountCookie(
                                            countCookie);
                                    System.out.println("Yum Yum. you ate ");
                                    System.out.println(countCookie + 
                                            " Cookies. That was good!");
                                }    
                                grid[newI][newJ] = 'V';
                                PacMan[countPacman].setXPacman(newI); 
                            }                                     
                            break;
                            //does same see above comments
                        case '<':
                            grid[newI][newJ] = ' ';
                            if (newJ==(y-1))
                            {       
                                grid[newI][newJ] = '<';
                                System.out.println("Out of bounds!");
                            }
                            else if(grid[newI][newJ+1] == '>' || 
                                    grid[newI][newJ+1] == 'V' || 
                                    grid[newI][newJ+1] == '<' || 
                                    grid[newI][newJ+1] == '^')
                            {
                                grid[newI][newJ] = '<';
                                System.out.println("Watch out other Pacman!");
                            }
                            else
                            {
                            newJ=newJ+1;
                                if (grid[newI][newJ] != '.' && 
                                        grid[newI][newJ] != ' ') 
                                {
                                    newCookies = 
                                            Character.getNumericValue(
                                                    grid[newI][newJ]);
                                    countCookie = newCookies + countCookie;
                                    PacMan[countPacman].setCountCookie(
                                            countCookie);
                                    System.out.println("Yum Yum. you ate ");
                                    System.out.println(countCookie + 
                                            " Cookies. That was good!");
                                }    
                                grid[newI][newJ] = '<';
                                PacMan[countPacman].setYPacman(newJ);
                            }                                      
                            break;
                            //does same see above comments
                        case '^':
                            grid[newI][newJ] = ' ';
                            //checks to see if out of bounds below grid
                            if (newI==(x-1))
                            {       
                                grid[newI][newJ] = '^';
                                System.out.println("Out of bounds!");
                            }
                            else if(grid[newI+1][newJ] == '>' || 
                                    grid[newI+1][newJ] == 'V' || 
                                    grid[newI+1][newJ] == '<' || 
                                    grid[newI+1][newJ] == '^')
                            {
                                grid[newI][newJ] = '^';
                                System.out.println("Watch out other Pacman!");
                            }
                            else
                            {
                                newI=newI+1;
                                if (grid[newI][newJ] != '.' && 
                                        grid[newI][newJ] != ' ') 
                                {
                                    newCookies = 
                                            Character.getNumericValue(
                                                    grid[newI][newJ]);
                                    countCookie = newCookies + countCookie;
                                    PacMan[countPacman].setCountCookie(
                                            countCookie);
                                    System.out.println("Yum Yum. you ate ");
                                    System.out.println(countCookie + 
                                            " Cookies. That was good!");
                                }    
                                grid[newI][newJ] = '^';
                                PacMan[countPacman].setXPacman(newI);
                            }
                            break;
                        default:            //just incase something goes
                            break;          //wrong
                    }
                    printGrid(i, j, x, y, grid);    //finally print grid 
                } 
                else if (userInput == 2)          //choice 3
                {
                    countMove = PacMan[countPacman].getCountMove();
                    PacMan[countPacman].setCountMove(countMove + 1);//tracks 
                    //class: CW rotation
                    PacMan[countPacman].rotateCW(i, j, x, y, grid); 
                    printGrid(i, j, x, y, grid);             
                }  
                else if (userInput == 1)          //choice 1
                {
                    countMove = PacMan[countPacman].getCountMove();
                    PacMan[countPacman].setCountMove(countMove + 1); //tracks 
                    //class: CCW rotation
                    PacMan[countPacman].rotateCCW(i, j, x, y, grid);
                    printGrid(i, j, x, y, grid);
                }
                else if (userInput == 0)               //choice 0
                {
                    printCommandInstructions();  
                }
                else if (userInput == 5)               //choice 5
                {
                    input.nextLine(); 
                    System.out.println("Enter file name to save ");
                    System.out.println("(put file type, ex; filname.txt): ");
                    textInput = input.nextLine();
                    System.out.println();
                
                    String save = "/Users/Emanuel/Documents/"
                                + "NetBeansProjects/Project2_ecalderon81/"
                                + "src/" + textInput;
          
                    if(!save.isEmpty())
                    {
                        System.out.println("Do you want to overrite file?");
                        System.out.println("yes = 1, no = 2");
                        userInput = input.nextInt();
                        if(userInput == 1)
                        {
                            try(PrintWriter out = new PrintWriter(save))
                            {                               
                                out.println(String.valueOf(x));
                                out.println(String.valueOf(y));
                                out.println(String.valueOf(numberPacman));
                                for (countPacman = 0;countPacman<numberPacman;
                                        countPacman++)
                                {   
                                    out.println(PacMan[countPacman].toString()); 
                                } 
                                for (i = 0; i<x; i++)       //first grid setup
                                {
                                    for (j = 0; j<y; j++)
                                    {
                                        out.println(grid[i][j]); //dots on grid 
                                    }
                                }
                            }
                        }   
                        else
                        printGrid(i, j, x, y, grid);
                    } 
                    else if(save.isEmpty())
                    {
                        try(PrintWriter out = new PrintWriter(save))
                        {                               
                            out.println(String.valueOf(x));
                            out.println(String.valueOf(y));
                            out.println(String.valueOf(numberPacman));
                            for (countPacman = 0;countPacman<numberPacman;
                                    countPacman++)
                            {   
                                out.println(PacMan[countPacman].toString()); 
                            } 
                            for (i = 0; i<x; i++)       //first grid setup
                            {
                                for (j = 0; j<y; j++)
                                {
                                    out.println(grid[i][j]); //dots on grid 
                                }
                            }
                        }
                    
                    }
                        printGrid(i, j, x, y, grid);
                }
                if(countPacman == (numberPacman-1))
                {
                    countPacman = -1;
                }
        
            }
            System.out.println("Thanks for playing!");
            //end of game output with counted cookies and moves
            for (countPacman = 0;countPacman<numberPacman;countPacman++)
            {
                System.out.println("*********************************"); 
                System.out.println("Statistic for " + 
                        PacMan[countPacman].getPacmanName()); 
                System.out.println("# of Move: " + 
                        PacMan[countPacman].getCountMove()); 
                System.out.println("# Cookies eaten : " + 
                        PacMan[countPacman].getCountCookie());  
            
                //logic below to make sure not to divide by zero
                if (PacMan[countPacman].getCountCookie() == 0)  
                {
                System.out.println("#Moves per #Cookies: N/A");
                }
                else
                {
                    //quick float input for math 
                    float cM, cC;       
                    cM = PacMan[countPacman].getCountMove(); 
                    cC = PacMan[countPacman].getCountCookie();  
                    //actual values instead of just two numbers
                    stat = cM/cC;                       
                    System.out.println("#Moves per #Cookies: " + stat);      
                }
           }
                    
        }
        else if (load == 'n')//here is where the program begins 
        {
            System.out.println("Choose an x (row) value for grid");
            x = input.nextInt();
            System.out.println("Choose a y (column) value for grid");
            y = input.nextInt();
            System.out.println();
            System.out.println("Number of PacMans: "); //added this 
            numberPacman = input.nextInt();
            System.out.println();
        
            //main grid set up
            char grid[][] = new char [x][y];        
            int[] positions = new int[x*y]; //cookie randomization setup
        
            //you will be seeing alot of these for loops, we are going through
            //each of the array grid values to put in the dots
            for (i = 0; i<x; i++)           //first grid setup
            {
                for (j = 0; j<y; j++)
                {
                    grid[i][j] = '.';       //dots on grid                                 
                }            
            } 
        
            PacMans[] PacMan = new PacMans[numberPacman];   //pacmen set up
        
            //below begins the setup for the pacman depending on how many chosen
            for(countPacman=0;countPacman<numberPacman;countPacman++)
            {
                PacMan[countPacman] = new PacMans();

                int holderInt;
                String holderString;
                //alot of inputs below 
                System.out.print("Choose a Y location for PacMan " 
                    + countPacman + " : ");
                holderInt = input.nextInt();
                PacMan[countPacman].setYPacman(holderInt);
                System.out.println(); 
                System.out.print("Choose an X location for PacMan " 
                        + countPacman + " : ");
                holderInt = input.nextInt();
                PacMan[countPacman].setXPacman(holderInt);
                System.out.println();  
                
                //below i could have simplified but was nearing the end 
                System.out.print("Direction(Left: 0 Right: 2 Up: 1 Down: 3): ");                
                direction = input.nextInt();
                System.out.println(); 
                input.nextLine();
                System.out.print("Enter PacMan name : "); 
                holderString = text.nextLine();
                PacMan[countPacman].setPacmanName(holderString);
                System.out.println(); 
                
                //as mentioned before, this could have been done better 
                if(direction == 0) 
                {
                finalDirection = '>';
                }
                else if(direction == 1)
                {
                    finalDirection = 'V';
                }
                else if(direction == 2)
                {
                    finalDirection = '<';
                }
            
                else if(direction == 3)
                {
                    finalDirection= '^';
                }
                grid[PacMan[countPacman].getYPacman()]
                        [PacMan[countPacman].getXPacman()] = finalDirection;
                PacMan[countPacman].setDirection(finalDirection);
                //end of the long logic 
            }
        
            for (i = 0; i < x*y; i++)   //initializer for cookie randomization
            {
                positions[i] = i;
            }
            
            //had to update this alot 
            System.out.println("Enter percent of cookies for " + x*y + " grid");
            System.out.println("(Ex: 14 is 14% of " + x*y + 
                    "meaning 3.5 cookies");
            System.out.println("or 3 cookies... user cookie value is : ");
            percent = input.nextInt();
            System.out.println();
            //added 
            System.out.println("Min Cookie Value : ");
            minCookie = input.nextInt();
            System.out.println();
            System.out.println("Max Cookie Value : ");
            maxCookie = input.nextInt();
            System.out.println();
            
            //had to incorporate this in order to get full max cookie
            Random rnGenerator = new Random();
        
            //below is just the input of how many cookies 
            cookie = (x*y)*(percent/100);         
            cookieRound = Math.floor(cookie);
        
            //below loop uses random and initializer to sift through 
            //and place the cookies randomyl on the grid 
            for (i = 0; i < (cookieRound); i++) 
            {
                random = (int)(Math.random() * ((x*y) - i - 1)); //logic
                position = positions[random];                    
                positions[random] = positions[(x*y) - i - 1];    //second logic
                row = position / x;
                col = position % y;
                //below search to avoid and dont take to account pacman in grid 
                while (grid[row][col] == '>' || grid[row][col] == 'V' || 
                        grid[row][col] == '<' || grid[row][col] == '^')
                {
                    random = (int)(Math.random() * ((x*y) - i - 1));
                    position = positions[random];
                    positions[random] = positions[(x*y) - i - 1];
                    row = position / x;
                    col = position % y;
                }
                //belo gets me my values in the grid
                randomCookie =(int)(rnGenerator.nextInt(maxCookie)+minCookie);
                grid[row][col] = (char)(randomCookie+'0');
            }
            printGameIntroduction();        //method: game intro print
            printCommandInstructions();     //method: print command instructions        
            printGrid(i, j, x, y, grid);    //method: prints grid in several areas
            //now you are running the program below 
            for (countPacman = 0;countPacman<numberPacman;countPacman++)
            {
            System.out.println();
            System.out.print("Command for PacMan:" 
                    + PacMan[countPacman].getPacmanName()); //switches name 
            System.out.println();
            System.out.println("#cookies currently eaten "
                    + PacMan[countPacman].getCountCookie());  //cookie tracker       
            
            System.out.print("Command:");     //command word is seen
            userInput = input.nextInt();
            System.out.println();
                
            if(userInput ==4)
            {
                break;
            }
            //this one ie long, i am sure there is a better way to do this
            //might be one thing to go back and make easier 
            else if (userInput == 3)          //choice 3 (moving)
            {
                //counts moves for each pacman
                countMove = PacMan[countPacman].getCountMove();
                PacMan[countPacman].setCountMove(countMove + 1);
                int newJ = PacMan[countPacman].getYPacman();
                int newI = PacMan[countPacman].getXPacman();
                //switch case to identify where pacman is looking
                switch (grid[newI][newJ]) 
                {
                    case '>':
                        grid[newI][newJ] = ' ';  //puts blank array
                        //checks to see if out of bounds on left grid
                        if (newJ==0)          
                        {       
                            grid[newI][newJ] = '>';
                            System.out.println("Out of bounds!");
                        }
                        //checks to see if in way of pacman
                        else if(grid[newI][newJ-1] == '>' || 
                                grid[newI][newJ-1] == 'V' || 
                                grid[newI][newJ-1] == '<' || 
                                grid[newI][newJ-1] == '^')
                        {
                            grid[newI][newJ] = '>';
                            System.out.println("Watch out other Pacman!");
                        }
                        else            //perform the move
                        {                                       
                            newJ=newJ-1; //logic for actual move
                            //checks if cookie
                            if (grid[newI][newJ] != '.' && 
                                    grid[newI][newJ] != ' ') 
                            {
                                //gets cookie number 
                                newCookies = Character.getNumericValue(
                                        grid[newI][newJ]);
                                countCookie = newCookies + countCookie;
                                //sets new cookie number 
                                PacMan[countPacman].setCountCookie(countCookie);
                                System.out.println("Yum Yum. you ate ");
                                System.out.println(newCookies + 
                                        " Cookies. That was good!");
                            }                      //for cookie
                            grid[newI][newJ] = '>'; 
                            //puts pacman in new location
                            PacMan[countPacman].setYPacman(newJ); 
                        } 
                        break;
                        //does same see above comments
                    case 'V':                  
                        grid[newI][newJ] = ' ';
                        //checks to see if out of bounds on top grid  
                        if (newI==0)
                        {       
                            grid[newI][newJ] = 'V';
                            System.out.println("Out of bounds!");
                                    }
                        else if(grid[newI-1][newJ] == '>' || 
                                grid[newI-1][newJ] == 'V' || 
                                grid[newI-1][newJ] == '<' ||
                                grid[newI-1][newJ] == '^')
                        {
                            grid[newI][newJ] = 'V';
                            System.out.println("Watch out other Pacman!");
                        }
                        else
                        {
                            newI=newI-1;
                            //checks if cookie
                            if (grid[newI][newJ] != '.' && 
                                    grid[newI][newJ] != ' ') 
                            {
                                newCookies=
                                        Character.getNumericValue(
                                                grid[newI][newJ]);
                                countCookie = newCookies + countCookie;
                                PacMan[countPacman].setCountCookie(countCookie);
                                System.out.println("Yum Yum. you ate ");
                                System.out.println(countCookie + 
                                        " Cookies. That was good!");
                            }    
                            grid[newI][newJ] = 'V';
                            PacMan[countPacman].setXPacman(newI); 
                        }                                     
                        break;
                        //does same see above comments
                    case '<':
                        grid[newI][newJ] = ' ';
                        if (newJ==(y-1))
                        {       
                            grid[newI][newJ] = '<';
                            System.out.println("Out of bounds!");
                        }
                        else if(grid[newI][newJ+1] == '>' || 
                                grid[newI][newJ+1] == 'V' || 
                                grid[newI][newJ+1] == '<' || 
                                grid[newI][newJ+1] == '^')
                        {
                            grid[newI][newJ] = '<';
                            System.out.println("Watch out other Pacman!");
                        }
                        else
                        {
                            newJ=newJ+1;
                            if (grid[newI][newJ] != '.' && 
                                    grid[newI][newJ] != ' ') 
                            {
                                newCookies = 
                                        Character.getNumericValue(
                                                grid[newI][newJ]);
                                countCookie = newCookies + countCookie;
                                PacMan[countPacman].setCountCookie(countCookie);
                                System.out.println("Yum Yum. you ate ");
                                System.out.println(countCookie + 
                                        " Cookies. That was good!");
                            }    
                            grid[newI][newJ] = '<';
                            PacMan[countPacman].setYPacman(newJ);
                        }                                      
                        break;
                        //does same see above comments
                    case '^':
                        grid[newI][newJ] = ' ';
                        //checks to see if out of bounds below grid
                        if (newI==(x-1))
                        {       
                            grid[newI][newJ] = '^';
                            System.out.println("Out of bounds!");
                        }
                        else if(grid[newI+1][newJ] == '>' || 
                                grid[newI+1][newJ] == 'V' || 
                                grid[newI+1][newJ] == '<' || 
                                grid[newI+1][newJ] == '^')
                        {
                            grid[newI][newJ] = '^';
                            System.out.println("Watch out other Pacman!");
                        }
                        else
                        {
                            newI=newI+1;
                            if (grid[newI][newJ] != '.' && 
                                    grid[newI][newJ] != ' ') 
                            {
                                newCookies = 
                                        Character.getNumericValue(
                                                grid[newI][newJ]);
                                countCookie = newCookies + countCookie;
                                PacMan[countPacman].setCountCookie(countCookie);
                                System.out.println("Yum Yum. you ate ");
                                System.out.println(countCookie + 
                                        " Cookies. That was good!");
                            }    
                            grid[newI][newJ] = '^';
                            PacMan[countPacman].setXPacman(newI);
                        }
                        break;
                    default:            //just incase something goes
                        break;          //wrong
                }
                printGrid(i, j, x, y, grid);    //finally print grid 
            } 
            else if (userInput == 2)          //choice 3
            {
                countMove = PacMan[countPacman].getCountMove();
                PacMan[countPacman].setCountMove(countMove + 1);//tracks moves
                //method: CW rotation
                PacMan[countPacman].rotateCW(i, j, x, y, grid); 
                printGrid(i, j, x, y, grid);             
            }  
            else if (userInput == 1)          //choice 1
            {
                countMove = PacMan[countPacman].getCountMove();
                PacMan[countPacman].setCountMove(countMove + 1); //tracks moves
                //method: CCW rotation
                PacMan[countPacman].rotateCCW(i, j, x, y, grid);
                printGrid(i, j, x, y, grid);
            }
            else if (userInput == 0)               //choice 0
            {
                printCommandInstructions();  
            }
            else if (userInput == 5)               //choice 5
            {
                input.nextLine(); 
                System.out.println("Enter file name to save ");
                System.out.println("(put file type, ex; filname.txt): ");
                textInput = input.nextLine();
                System.out.println();
                
                String save = "/Users/Emanuel/Documents/"
                                + "NetBeansProjects/Project2_ecalderon81/"
                                + "src/" + textInput;
                if(!save.isEmpty())
                    {
                        System.out.println("Do you want to overrite file?");
                        System.out.println("yes = 1, no = 2");
                        userInput = input.nextInt();
                        if(userInput == 1)
                        {
                            try(PrintWriter out = new PrintWriter(save))
                            {                               
                                out.println(String.valueOf(x));
                                out.println(String.valueOf(y));
                                out.println(String.valueOf(numberPacman));
                                for (countPacman = 0;countPacman<numberPacman;
                                        countPacman++)
                                {   
                                    out.println(PacMan[countPacman].toString()); 
                                } 
                                for (i = 0; i<x; i++)       //first grid setup
                                {
                                    for (j = 0; j<y; j++)
                                    {
                                        out.println(grid[i][j]); //dots on grid 
                                    }
                                }
                            }
                        }
                        else
                        printGrid(i, j, x, y, grid);
                    } 
                    else if(save.isEmpty())
                    {
                        try(PrintWriter out = new PrintWriter(save))
                        {                               
                            out.println(String.valueOf(x));
                            out.println(String.valueOf(y));
                            out.println(String.valueOf(numberPacman));
                            for (countPacman = 0;countPacman<numberPacman;
                                    countPacman++)
                            {   
                                out.println(PacMan[countPacman].toString()); 
                            } 
                            for (i = 0; i<x; i++)       //first grid setup
                            {
                                for (j = 0; j<y; j++)
                                {
                                    out.println(grid[i][j]); //dots on grid 
                                }
                            }
                        }
                    
                    }
                    printGrid(i, j, x, y, grid);
                }
            if(countPacman == (numberPacman-1))
            {
                countPacman = -1;
            }
        
        }
        System.out.println("Thanks for playing!");
        //end of game output with counted cookies and moves
        for (countPacman = 0;countPacman<numberPacman;countPacman++)
        {
            System.out.println("*********************************"); 
            System.out.println("Statistic for " + 
                    PacMan[countPacman].getPacmanName()); 
            System.out.println("# of Move: " + 
                    PacMan[countPacman].getCountMove()); 
            System.out.println("# Cookies eaten : " + 
                    PacMan[countPacman].getCountCookie());  
            
            //logic below to make sure not to divide by zero
            if (PacMan[countPacman].getCountCookie() == 0)  
            {
                System.out.println("#Moves per #Cookies: N/A");
            }
            else
            {
                //quick float input for math 
                float cM, cC;       
                cM = PacMan[countPacman].getCountMove(); 
                cC = PacMan[countPacman].getCountCookie();  
                //actual values instead of just two numbers
                stat = cM/cC;                       
                System.out.println("#Moves per #Cookies: " + stat);      
            }
        }
        }
          
    }
    public static void printGameIntroduction()     //method: print intro
    {
        System.out.println("Welcome to Pacman!");
        System.out.println("Enter the number of the command desired.");
    }
    public static void printCommandInstructions()     //method: print commands
    {
        System.out.println("Display Commands (0):");
        System.out.println("Turn Left        (1):");
        System.out.println("Turn Right       (2):");
        System.out.println("Move             (3):");
        System.out.println("Exit             (4):");
        System.out.println("Save Game        (5):");
        System.out.println();
    }
    
    //Method: below method to print pacman grid
    public static void printGrid(int i, int j, int x, int y, char grid[][]) 
    {
        for (i = 0; i<x; i++)
        {
            for (j = 0; j<y; j++)
            {
                System.out.print(grid[i][j]);      
            }
            System.out.println();
        }
    }
    


}
 
