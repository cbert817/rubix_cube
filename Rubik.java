
public class Rubik {

    public static void main(String[] args) {

        //rubiks cube faces
        //face 1 red front R
        char f1[][] = { {'r', 'r', 'r'},
                        {'r', 'r', 'r'},
                        {'r', 'r', 'r'} };

        //face 2 blue front L
        char f2[][] = { {'b', 'b', 'b'},
                        {'b', 'b', 'b'},
                        {'b', 'b', 'b'} };

        //face 3 orange rear L
        char f3[][] = { {'o', 'o', 'o'},
                        {'o', 'o', 'o'},
                        {'o', 'o', 'o'} };

        //face 4 green rear R
        char f4[][] = { {'g', 'g', 'g'},
                        {'g', 'g', 'g'},
                        {'g', 'g', 'g'} };
        
        //face 5 yellow top
        char f5[][] = { {'y', 'y', 'y'},
                        {'y', 'y', 'y'},
                        {'y', 'y', 'y'} };

        //face 6 white bottom
        char f6[][] = { {'w', 'w', 'w'},
                        {'w', 'w', 'w'},
                        {'w', 'w', 'w'} };

        //rubiks cube array
        //note: format: FR, FL, RL, RR, T, B
        char cube[][][] = {f1, f2, f3, f4, f5, f6};


        
        for(String case_input : args){
            cube = move(cube, case_input);
        }

        printCube(cube);
        
        unscramble(args);

    }

    //Moves to Unscrambles Cube
    public static unscramble(String[] scramble){
        String[] solver = new String[scramble.length];

        for(int i = 0, j = (scramble.length - 1); i < scramble.length; i++, j--){
            switch(scramble[j]){
                case "U":
                    solver[i] = "U\'";
                    break;
                case "D":
                solver[i] = "D\'";
                    break;
                case "R":
                solver[i] = "R\'";
                    break;
                case "L":
                solver[i] = "L\'";
                    break;
                case "F":
                solver[i] = "F\'";
                    break;
                case "B":
                solver[i] = "B\'";
                    break;
                case "U\'":
                    solver[i] = "U";
                    break;
                case "D\'":
                solver[i] = "D";
                    break;
                case "R\'":
                solver[i] = "R";
                    break;
                case "L\'":
                solver[i] = "L";
                    break;
                case "F\'":
                solver[i] = "F";
                    break;
                case "B\'":
                solver[i] = "B";
                    break;
            }
        }

        System.out.print("Moves to Unscramble Cube: ");
        for(String move : solver){
            System.out.print(move + " ");
        }
    }

    //printout cube
    public static void printCube(char[][][] cube) {
        for(int f = 0; f < 6; f++){
            for(int r = 0; r < 3; r ++) {
                for(int c = 0; c < 3; c++) {
                    if(j < 2){
                        System.out.print(cube[f][r][c] + "|");
                    } else {
                        System.out.println(cube[f][r][c]);
                    }
                }
            }
            System.out.println("");
        }
    }

    //Method to rotate face
    public static char[][] rotateFace(char[][] face, boolean clockwise) {
        
        char copy[][] = face.clone();

        //clockwise 
        if(clockwise) {

            //top to right (i goes up)
            for(int i = 0; i < 3; i++) {
                face[i][2] = copy[0][i];
            }

            //right to bottom (i goes up j goes down)
            for(int i = 0, j = 2; i < 3; i++, j--) {
                face[2][i] = copy[j][2];
            }

            //bottom to left (i goes up)
            for(int i = 0; i < 3; i++) {
                face[i][0] = copy[2][i];
            }

            //left to top (i goes up j goes down)
            for(int i = 0, j = 2; i < 3; i++, j--) {
                face[0][i] = copy[j][0];
            }
        } 

        //counter clockwise
        else {

            //top to left (i goes up)
            for(int i = 0; i < 3; i++) {
                face[i][2] = copy[0][i];
            }

            //left to bottom (i goes up j goes down)
            for(int i = 0, j = 2; i < 3; i++, j--) {
                face[2][i] = copy[j][2];
            }

            //bottom to right (i goes up)
            for(int i = 0; i < 3; i++) {
                face[i][0] = copy[2][i];
            }

            //right to top (i goes up j goes down)
            for(int i = 0, j = 2; i < 3; i++, j--) {
                face[0][i] = copy[j][0];
            }
        }
        
        //return rotated face
        return(face);
        
    }

    //Note: make top row of white connected to blue side, make top row of yellow connected to green side

    //Performs moves
    public static char[][][] move(char[][][] cube, String move) {
        char copy[][][] = cube.clone();
        switch(move.toLowerCase()){

            //move U(top yellow)
            case "U":
                //rotating top yellow clockwise
                cube[4] = rotateFace(cube[4], true);

                //moving tops of f1 to f2, f2 to f3, f3 to f4, f4 to f1
                    cube[1][0] = copy[0][0].clone();
                    cube[2][0] = copy[1][0].clone();
                    cube[3][0] = copy[2][0].clone();
                    cube[0][0] = copy[3][0].clone();
                break;

            //move D(bottom white)
            case "D":
                //rotating bottom white clockwise
                cube[5] = rotateFace(cube[5], true);

                //moving bottom of f1 to f4, f4 to f3, f3 to f2, f2 to f1
                cube[3][2] = copy[0][2].clone();
                cube[2][2] = copy[3][2].clone();
                cube[1][2] = copy[2][2].clone();
                cube[0][2] = copy[1][2].clone();
                break;
            
            //move R(Front R red)
            case "R":
                //rotating front R red clockwise
                cube[0] = rotateFace(cube[0], true);

                //moving side of f6 to f2, f2 to f5, f5 to f4, f4 to f6
                for(int i = 0; i < 3;i++){
                cube[1][i][2] = copy[5][i][2];
                }
                for(int i = 0; i < 3;i++){
                    cube[4][i][2] = copy[1][i][2];
                }
                for(int i = 0; i < 3;i++){
                    cube[3][i][0] = copy[4][i][2];
                }
                for(int i = 0, r = 2; i < 3;i++, r--){
                    cube[5][i][2] = copy[3][r][0];
                }
                break;
               
            //move L(Rear L orange)
            case "L":
                //rotating Rear L orange clockwise
                cube[2] = rotateFace(cube[2], true);

                //moving side of f5 to f2, f2 to f6, f6 to f4, f4 to f5
                for(int i = 0; i < 3;i++){
                    cube[1][i][0] = copy[4][i][0];
                }
                for(int i = 0; i < 3;i++){
                    cube[5][i][0] = copy[1][i][0];
                }
                for(int i = 0, r = 2; i < 3;i++, r--){
                    cube[3][i][2] = copy[5][r][0];
                }
                for(int i = 0, r = 2; i < 3;i++, r--){
                    cube[4][i][0] = copy[3][r][2];
                }
                break;

            //move F(Front L blue)
            case "F":
                //rotating Front L blue clockwise
                cube[1] = rotateFace(cube[1], true);

                //moving side of f5 to f1, f1 to f6, f6 to f3, f3 to f5
                for(int i = 0; i < 3;i++){
                    cube[0][i][0] = copy[4][2][i];
                }
                for(int i = 0, r = 2; i < 3;i++, r--){
                    cube[5][0][i] = copy[0][r][0];
                }
                for(int i = 0; i < 3;i++){
                    cube[3][i][2] = copy[5][0][i];
                }
                for(int i = 0, r = 2; i < 3;i++, r--){
                    cube[4][2][i] = copy[3][r][2];
                }
                break;

            //move B(Rear R green)
            case "B":
                //rotating Rear R green clockwise
                cube[3] = rotateFace(cube[3], true);

                //moving side of f1 to f5, f5 to f3, f3 to f6, f6 to f1
                for(int i = 0; i < 3;i++){
                    cube[4][0][i] = copy[0][i][2];
                }
                for(int i = 0, r = 2; i < 3;i++, r--){
                    cube[2][i][0] = copy[4][0][r];
                }
                for(int i = 0; i < 3;i++){
                    cube[5][2][i] = copy[2][i][0];
                }
                for(int i = 0, r = 2; i < 3;r++, r--){
                    cube[0][i][2] = copy[5][2][r];
                }
                break;

            //OPPOSITE MOVES *****************************************************************************************

            //move U'(top yellow) *done
            case "U\'":
                //rotating top yellow counter clockwise
                cube[4] = rotateFace(cube[4], false);

                //moving tops of f1 to f4, f4 to f3, f3 to f2, f2 to f1
                    cube[3][0] = copy[0][0].clone();
                    cube[2][0] = copy[3][0].clone();
                    cube[1][0] = copy[2][0].clone();
                    cube[0][0] = copy[1][0].clone();
            break;

            //move D'(bottom white) *done
            case "D\'":
                //rotating bottom white counter clockwise
                cube[5] = rotateFace(cube[5], false);

                //moving bottoms of f1 to f2, f2 to f3, f3 to f4, f4 to f1
                cube[1][2] = copy[0][2].clone();
                cube[2][2] = copy[1][2].clone();
                cube[3][2] = copy[2][2].clone();
                cube[0][2] = copy[3][2].clone();
            break;
        
            //move R'(Front R red) *done
            case "R\'":
                //rotating front R red counter clockwise
                cube[0] = rotateFace(cube[0], false);

                //moving side of f5 to f2, f2 to f6, f6 to f4, f4 to f5
                for(int i = 0; i < 3;i++){
                    cube[1][i][2] = copy[4][i][2];
                }
                for(int i = 0; i < 3;i++){
                    cube[5][i][2] = copy[1][i][2];
                }
                for(int i = 0, r = 2; i < 3;i++, r--){
                    cube[3][i][0] = copy[5][r][2];
                }
                for(int i = 0, r = 2; i < 3;i++, r--){
                    cube[4][i][2] = copy[3][r][0];
                }
            break;
           
            //move L'(Rear L orange) *done
            case "L\'":
                //rotating Rear L orange counter clockwise
                cube[2] = rotateFace(cube[2], false);

                //moving side of f6 to f2, f2 to f5, f5 to f4, f4 to f6
                for(int i = 0; i < 3;i++){
                    cube[1][i][0] = copy[5][i][0];
                    }
                    for(int i = 0; i < 3;i++){
                        cube[4][i][0] = copy[1][i][0];
                    }
                    for(int i = 0, r = 2; i < 3;i++, r--){
                        cube[3][i][2] = copy[4][r][0];
                    }
                    for(int i = 0, r = 2; i < 3;i++, r--){
                        cube[5][i][2] = copy[3][r][0];
                    }
            break;

            //move F'(Front L blue) *done
            case "F\'":
                //rotating Front L blue counter clockwise
                cube[1] = rotateFace(cube[1], false);

                //moving side of f1 to f5, f5 to f3, f3 to f6, f6 to f1
                for(int i = 0; i < 3;i++){
                    cube[4][2][i] = copy[0][i][0];
                }
                for(int i = 0, r = 2; i < 3;i++, r--){
                    cube[2][i][2] = copy[4][2][r];
                }
                for(int i = 0; i < 3;i++){
                    cube[5][0][i] = copy[2][i][2];
                }
                for(int i = 0, r = 2; i < 3;r++, r--){
                    cube[0][i][0] = copy[5][0][r];
                }
            break;

            //move B'(Rear R green) *done
            case "B\'":
                //rotating Rear R green counter clockwise
                cube[3] = rotateFace(cube[3], false);

                //moving side of f5 to f1, f1 to f6, f6 to f3, f3 to f5
                for(int i = 0; i < 3;i++){
                    cube[0][i][2] = copy[4][0][i];
                }
                for(int i = 0, r = 2; i < 3;i++, r--){
                    cube[5][2][i] = copy[0][r][2];
                }
                for(int i = 0; i < 3;i++){
                    cube[3][i][0] = copy[5][2][i];
                }
                for(int i = 0, r = 2; i < 3;i++, r--){
                    cube[4][0][i] = copy[3][r][0];
                }
            break;
        }
        return(cube);

    }

    //makes copy of a face array
    public static char[][] copy(char[][] face) {
        char copy[][] = new char[3][3];

        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                copy[i][j] = face[i][j];
            }
        }
        return(copy);
    }
}