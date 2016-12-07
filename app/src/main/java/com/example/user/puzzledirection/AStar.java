package com.example.user.puzzledirection;

/**
 * Created by USER on 12/6/2016.
 */
import android.util.Log;
import android.widget.Toast;

import java.io.*;
import java.util.*;

public class AStar {
    ArrayList<Pair> ans;
    coordinate co;
    int m=100000;
    int ROW=5;
    int COL=5;
    public String s="";
    AStar(){
        ans=new ArrayList<>();
        co=new coordinate();
    }
    boolean isValid(int row, int col)
    {
        return (row >= 0) && (row < ROW) &&
                (col >= 0) && (col < COL);
    }
    boolean isUnBlocked(int grid[][], int row, int col)
    {
        if (grid[row][col] == 1)
            return (true);
        else
            return (false);
    }
    boolean isDestination(int row, int col, Pair dest)
    {
        if (row == dest.first && col == dest.second)
            return (true);
        else
            return (false);
    }
    double calculateHValue(int row, int col, Pair dest)
    {
        return (Math.sqrt((row-dest.first)*(row-dest.first)
                + (col-dest.second)*(col-dest.second)));
    }
    void tracePath(cell cellDetails[][], Pair dest)
    {
        int row = dest.first;
        int col = dest.second;
        Pair ppp;
        s="";
        Stack<Pair> Path=new Stack<>();
        while (!(cellDetails[row][col].parent_i == row
                && cellDetails[row][col].parent_j == col ))
        {
            ppp=new Pair(row,col);
            Path.push (ppp);
            int temp_row = cellDetails[row][col].parent_i;
            int temp_col = cellDetails[row][col].parent_j;
            row = temp_row;
            col = temp_col;
        }
        ppp=new Pair(row,col);
        Path.push (ppp);
        while (!Path.empty())
        {
            Pair p = Path.pop();
            s+=" -> "+(p.first)+","+(p.second);
            ans.add(p);
        }
        return;
    }
    void aStarSearch(int grid[][], int qq,int qqq, int ww,int www) {
        Pair src=new Pair(qq,qqq);
        Pair dest=new Pair(ww,www);
        if (isValid(src.first, src.second) == false) {
            //invalid source;
            return;
        }
        if (isValid (dest.first, dest.second) == false)
        {
            //Invalid destination
            return;
        }
        if (isUnBlocked(grid, src.first, src.second) == false ||
                isUnBlocked(grid, dest.first, dest.second) == false)
        {
            //Blocked source or destination
            return;
        }
        if (isDestination(src.first, src.second, dest) == true)
        {
            //You reached your destination
            return;
        }
        boolean closedList[][]=new boolean[ROW][COL];
        cell cellDetails[][]=new cell[ROW][COL];
        for(int x=0;x<ROW;x++){
            for(int y=0;y<COL;y++){
                cellDetails[x][y]=new cell();
            }
        }
        int i, j;
        for (i=0; i<ROW; i++)
        {
            for (j=0; j<COL; j++)
            {
                cellDetails[i][j].f = m;
                cellDetails[i][j].g = m;
                cellDetails[i][j].h = m;
                cellDetails[i][j].parent_i = -1;
                cellDetails[i][j].parent_j = -1;
            }
        }
        i = src.first;
        j = src.second;
        cellDetails[i][j].f = 0.0;
        cellDetails[i][j].g = 0.0;
        cellDetails[i][j].h = 0.0;
        cellDetails[i][j].parent_i = i;
        cellDetails[i][j].parent_j = j;
        ArrayList<pPair> openList = new ArrayList<>();
        Pair ppp=new Pair(i,j);
        pPair pp=new pPair(0.0,ppp);
        openList.add(pp);
        boolean foundDest = false;
        while (!openList.isEmpty()) {
            pPair p = openList.get(0);
            openList.remove(0);
            i = p.second.first;
            j = p.second.second;
            closedList[i][j] = true;
            double gNew, hNew, fNew;

            //----------- 1st Successor (North) ------------
            if (isValid(i-1, j) == true)
            {
                if (isDestination(i-1, j, dest) == true)
                {
                    cellDetails[i-1][j].parent_i = i;
                    cellDetails[i-1][j].parent_j = j;
                    //The destination cell is found
                    tracePath (cellDetails, dest);
                    foundDest = true;
                    return;
                }
                else if (closedList[i-1][j] == false &&
                        isUnBlocked(grid, i-1, j) == true)
                {
                    gNew = cellDetails[i][j].g + 1.0;
                    hNew = calculateHValue (i-1, j, dest);
                    fNew = gNew + hNew;
                    if (cellDetails[i-1][j].f == m ||
                            cellDetails[i-1][j].f > fNew)
                    {
                        ppp=new Pair(i-1,j);
                        pp=new pPair(0.0,ppp);
                        openList.add(pp);
                        cellDetails[i-1][j].f = fNew;
                        cellDetails[i-1][j].g = gNew;
                        cellDetails[i-1][j].h = hNew;
                        cellDetails[i-1][j].parent_i = i;
                        cellDetails[i-1][j].parent_j = j;
                    }
                }
            }

            //----------- 2nd Successor (South) ------------
            if (isValid(i+1, j) == true)
            {
                if (isDestination(i+1, j, dest) == true)
                {
                    cellDetails[i+1][j].parent_i = i;
                    cellDetails[i+1][j].parent_j = j;
                    //The destination cell is found
                    tracePath(cellDetails, dest);
                    foundDest = true;
                    return;
                }
                else if (closedList[i+1][j] == false &&
                        isUnBlocked(grid, i+1, j) == true)
                {
                    gNew = cellDetails[i][j].g + 1.0;
                    hNew = calculateHValue(i+1, j, dest);
                    fNew = gNew + hNew;
                    if (cellDetails[i+1][j].f == m ||
                            cellDetails[i+1][j].f > fNew)
                    {
                        ppp=new Pair(i+1,j);
                        pp=new pPair(0.0,ppp);
                        openList.add(pp);
                        cellDetails[i+1][j].f = fNew;
                        cellDetails[i+1][j].g = gNew;
                        cellDetails[i+1][j].h = hNew;
                        cellDetails[i+1][j].parent_i = i;
                        cellDetails[i+1][j].parent_j = j;
                    }
                }
            }

            //----------- 3rd Successor (East) ------------
            if (isValid (i, j+1) == true)
            {
                if (isDestination(i, j+1, dest) == true)
                {
                    cellDetails[i][j+1].parent_i = i;
                    cellDetails[i][j+1].parent_j = j;
                    //The destination cell is found
                    tracePath(cellDetails, dest);
                    foundDest = true;
                    return;
                }
                else if (closedList[i][j+1] == false &&
                        isUnBlocked (grid, i, j+1) == true)
                {
                    gNew = cellDetails[i][j].g + 1.0;
                    hNew = calculateHValue (i, j+1, dest);
                    fNew = gNew + hNew;
                    if (cellDetails[i][j+1].f == m ||
                            cellDetails[i][j+1].f > fNew)
                    {
                        ppp=new Pair(i,j+1);
                        pp=new pPair(0.0,ppp);
                        openList.add(pp);
                        cellDetails[i][j+1].f = fNew;
                        cellDetails[i][j+1].g = gNew;
                        cellDetails[i][j+1].h = hNew;
                        cellDetails[i][j+1].parent_i = i;
                        cellDetails[i][j+1].parent_j = j;
                    }
                }
            }

            //----------- 4th Successor (West) ------------
            if (isValid(i, j-1) == true)
            {
                if (isDestination(i, j-1, dest) == true)
                {
                    cellDetails[i][j-1].parent_i = i;
                    cellDetails[i][j-1].parent_j = j;
                    //The destination cell is found
                    tracePath(cellDetails, dest);
                    foundDest = true;
                    return;
                }
                else if (closedList[i][j-1] == false &&
                        isUnBlocked(grid, i, j-1) == true)
                {
                    gNew = cellDetails[i][j].g + 1.0;
                    hNew = calculateHValue(i, j-1, dest);
                    fNew = gNew + hNew;
                    if (cellDetails[i][j-1].f == m ||
                            cellDetails[i][j-1].f > fNew)
                    {
                        ppp=new Pair(i,j-1);
                        pp=new pPair(0.0,ppp);
                        openList.add(pp);
                        cellDetails[i][j-1].f = fNew;
                        cellDetails[i][j-1].g = gNew;
                        cellDetails[i][j-1].h = hNew;
                        cellDetails[i][j-1].parent_i = i;
                        cellDetails[i][j-1].parent_j = j;
                    }
                }
            }
        }
        if (foundDest == false)
            //Failed to find the Destination Cell
        return;
        }
}
class cell{
    int parent_i, parent_j;
    // f = g + h
    double f, g, h;
    cell(){
        parent_i=parent_j=0;
        f=g=h=0.0;
    }
}
class coordinate{
    int c,d;
    coordinate(){
        c=d=0;
    }
}
class Pair{
    int first,second;
    Pair(int a,int b){
        first=a;
        second=b;
    }
}
class pPair{
    double first;
    Pair second;
    pPair(double a,Pair b){
        first=a;
        second=b;
    }
}