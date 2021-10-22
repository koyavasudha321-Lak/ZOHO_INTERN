package day1;

import java.util.*;

public class GridSearch{

    static final int ROW = 4;
    static final int COL = 6;

    /*possible directions for row and column */
    static int rowPOS[] = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int colPOS[] = {-1, 0, 1, -1, 1, -1, 0, 1};


    /* function to check corner cases*/
    static boolean isValid(int row, int col, int preROW, int preCOL){
        return (  row >= 0 && row < ROW && col >= 0 && col < COL && !(row == preROW && col == preCOL) );

    }

    /* function to traverse through charcaters of in all directions og Grid*/
    static void DFS(char GRID[][], int r, int c, int prevROW, int prevCOL, char[] word, String path, int index, int n){
        if(index > n || GRID[r][c] != word[index])
            return;

        path += word[index] + "(" + String.valueOf(r) + "," + String.valueOf(c) + ") ";
        if(index == n){
            System.out.println(path);
            return;
        }

        for(int l = 0;l < 8;l++){
            if(isValid(r + rowPOS[l], c + colPOS[l], prevROW, prevCOL)){
                DFS(GRID, r + rowPOS[l], c + colPOS[l], r, c,word, path, index + 1, n );
            }
        }
    }

    static void searchWord(char GRID[][], char word[], int n){
        for(int i = 0;i < ROW;i++) {
            for(int j = 0;j < COL;j++){
                if(GRID[i][j] == word[0]){
                    DFS(GRID, i, j, -1, -1, word, "", 0, n );
                }
            }
        }


    }



    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);

        char GRID[][]= { {'K', 'I', 'E', 'Y', 'S', 'V'},
                {'H', 'V', 'D', 'K', 'K', 'A'},
                {'V', 'E', 'V', 'D', 'E', 'V'},
                {'V', 'A', 'V', 'S', 'V', 'K'}
        };

        char[] word = "VVKS".toCharArray();/* converts string to characters */

        searchWord(GRID, word, word.length - 1);

    }

}
/*OUTPUT

V(1, 1) V(2, 2) K(1, 3) S(0, 4)
V(3, 2) V(2, 2) K(1, 3) S(0, 4)
V(3, 4) V(2, 5) K(1, 4) S(0, 4)

*/
