/**
 * program that models an employee. An employee has an employee number, a name,
 * an address, and a hire date. This class holds the employee
 * information. Used in EmployeeDBInheritance
 * @author Emanuel
 */
public class PacMans
{ 
    private String pacmanName;
    private char direction;
    private char finalDirectionPacman;
    private int yPacman;
    private int xPacman;
    private int maxY;
    private int maxX;
    private int countCookie;
    private int countMove = 0;
    
    public PacMans()// 
    {
/*        this.pacmanName = pacmanName;
        this.xPacman = xPacman;
        this.yPacman = yPacman;
        this.maxX = maxX;
        this.maxY = maxY;*/
    }
    public String toString()
    {
        return yPacman + "\n" + xPacman  + "\n" + direction + "\n" + pacmanName
                + "\n" + countMove + "\n" + countCookie;
    }
    public char[][] rotateCW(int i, int j, int x, int y, char grid[][]) 
    {                
                //below switch identifies current position of pacman and
                //rotates it CCW
                if(grid[xPacman][yPacman] == '>') 
                {
                    grid[xPacman][yPacman] = 'V';
                    direction = grid[xPacman][yPacman];
                }
                else if(grid[xPacman][yPacman] == 'V') 
                {
                    grid[xPacman][yPacman] = '<';
                    direction = grid[xPacman][yPacman];
                }
                else if(grid[xPacman][yPacman] == '<') 
                {
                    grid[xPacman][yPacman] = '^';
                    direction = grid[xPacman][yPacman];
                }
                else if(grid[xPacman][yPacman] == '^') 
                {
                    grid[xPacman][yPacman] = '>';
                    direction = grid[xPacman][yPacman];
                }
        return grid;
        
    }
    public char[][] rotateCCW(int i, int j, int x, int y, char grid[][]) 
    {
                //below switch identifies current position of pacman and
                //rotates it CCW
                if(grid[xPacman][yPacman] == '>') 
                {
                    grid[xPacman][yPacman] = '^';
                    direction = grid[xPacman][yPacman];
                }
                else if(grid[xPacman][yPacman] == 'V') 
                {
                    grid[xPacman][yPacman] = '>';
                    direction = grid[xPacman][yPacman];
                }
                else if(grid[xPacman][yPacman] == '<') 
                {
                    grid[xPacman][yPacman] = 'V';
                    direction = grid[xPacman][yPacman];
                }
                else if(grid[xPacman][yPacman] == '^') 
                {
                    grid[xPacman][yPacman] = '<';
                    direction = grid[xPacman][yPacman];
                }

        return grid;
        
    }
    public String getPacmanName() 
    {
        return pacmanName;
    }
    public int getXPacman() 
    {
        return xPacman;
    }
    public int getYPacman() 
    {
        return yPacman;
    }
    public int getCountCookie() 
    {
        return countCookie;
    }
    public int getCountMove() 
    {
        return countMove;
    }
    public char getDirection() 
    {
        return direction;
    }
    public void setXPacman(int xPacman) 
    {
        this.xPacman = xPacman;
    }
    public void setYPacman(int yPacman) 
    {
        this.yPacman = yPacman;
    }
    public void setCountCookie(int countCookie) 
    {
        this.countCookie = countCookie;
    }
    public void setPacmanName(String pacmanName) 
    {
        this.pacmanName = pacmanName;
    }
    public void setCountMove(int countMove) 
    {
        this.countMove = countMove;
    }
    public void setDirection(char direction) 
    {
        this.direction = direction;
    }
}
