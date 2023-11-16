import Figures.Bishop;
import Figures.Figure;
import Figures.King;
import Figures.Knight;
import Figures.Pawn;
import Figures.Queen;
import Figures.Rook;

import java.util.ArrayList;

public class Board {
    //TODO: Список фигур и начальное положение всех фигур
    private Figure  fields[][] = new Figure[8][8];
    private ArrayList<String> takeWhite = new ArrayList(16);
    private ArrayList<String> takeBlack = new ArrayList(16);

    public char getColorGaming() {
        return colorGaming;
    }

    public void setColorGaming(char colorGaming) {
        this.colorGaming = colorGaming;
    }

    private char colorGaming;

    public void init(){
        this.fields[0] = new Figure[]{
                new Rook("R", 'w'), new Knight("N", 'w'),
                new Bishop("B", 'w'), new Queen("Q", 'w'),
                new King("K", 'w'), new Bishop("B", 'w'),
                new Knight("N", 'w'),new Rook("R", 'w')
        };
        this.fields[1] = new Figure[]{
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
                new Pawn("P", 'w'),    new Pawn("P", 'w'),
        };

        this.fields[7] = new Figure[]{
                new Rook("R", 'b'), new Knight("N", 'b'),
                new Bishop("B", 'b'), new Queen("Q", 'b'),
                new King("K", 'b'), new Bishop("B", 'b'),
                new Knight("N", 'b'),new Rook("R", 'b')
        };
        this.fields[6] = new Figure[]{
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
                new Pawn("P", 'b'),    new Pawn("P", 'b'),
        };
    }

    public String getCell(int row, int col){
        Figure figure = this.fields[row][col];
        if (figure == null){
            return "    ";
        }
        return " "+figure.getColor()+figure.getName()+" ";
    }

    public ArrayList<String> getTakeWhite() {
        return takeWhite;
    }

    public ArrayList<String> getTakeBlack() {
        return takeBlack;
    }

    // фигуры кроме коня не могут перепрыгивать через другие фигуры, поэтому делаем проверку, свободен ли путь
    // рокировка не реализована, поэтому для ладьи проверяем во всех случаях
    public boolean path_free(int row1, int col1, int row2, int col2) {
        Figure figure = this.fields[row1][col1];
        int startRow = row1, endRow = row2, startCol = col1, endCol = col2;
        if ((row1 > row2) || (col1 > col2)) {
            startRow = row2;
            startCol = col2;
            endRow = row1;
            endCol = col1;
        }
        if ((figure instanceof Bishop) || (figure instanceof Queen)) {
            for (int i = 0; i < endRow - startRow - 1; ++i) {
                if (fields[startRow + i][startCol + i] != null) {
                    return false;
                }
            }
        } else if ((figure instanceof Rook) || (figure instanceof Queen)) {
            if (endRow == startRow) {
                for (int i = 0; i < endCol - startCol - 1; ++i) {
                    if (fields[startCol][startCol + i] != null) {
                        return false;
                    }
                }
            } else if (endCol == startCol) {
                for (int i = 0; i < endRow - startRow - 1; ++i) {
                    if (fields[startRow + i][startCol] != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    public boolean move_figure(int row1, int col1, int row2, int col2){

        Figure figure = this.fields[row1][col1];
        if (this.path_free(row1, col1, row2, col2)) {
            if (figure.canMove(row1, col1, row2, col2) && this.fields[row2][col2] == null) {
                System.out.println("move");
                this.fields[row2][col2] = figure;
                this.fields[row1][col1] = null;
                return true;
            } else if (figure.canAttack(row1, col1, row2, col2) && this.fields[row2][col2] != null && this.fields[row2][col2].getColor() != this.fields[row1][col1].getColor() && !(this.fields[row2][col2] instanceof King)) {
                System.out.println("attack");
                switch (this.fields[row2][col2].getColor()) {
                    case 'w':
                        this.takeWhite.add(this.fields[row2][col2].getColor() + this.fields[row2][col2].getName());
                        break;
                    case 'b':
                        this.takeBlack.add(this.fields[row2][col2].getColor() + this.fields[row2][col2].getName());
                        break;
                }
                this.fields[row2][col2] = figure;
                this.fields[row1][col1] = null;
                return true;
            }
        }
        return false;
    }

    public void print_board(){
        System.out.println(" +----+----+----+----+----+----+----+----+");
        for(int row = 7; row > -1; row--){
            System.out.print(row);
            for(int col = 0; col< 8; col++){
                System.out.print("|"+getCell(row, col));
            }
            System.out.println("|");
            System.out.println(" +----+----+----+----+----+----+----+----+");
        }

        for(int col = 0; col < 8; col++){
            System.out.print("    "+col);
        }
    }

    // вспомогательный метод для checkmate_check
    public String get_king_position(char color){
        int kingRow = -1, kingCol = -1;
        for (int row = 0; row < 8; ++row){
            for (int col = 0; col < 8; ++ col){
                Figure figure = fields[row][col];
                if ((figure instanceof King) && (figure.getColor() == color)){
                    kingRow = row;
                    kingCol = col;
                }
            }
        }
        return Integer.toString(kingRow) + Integer.toString(kingCol);
    }

    public boolean is_under_attack(int target_row, int target_col){
        Figure target = this.fields[target_row][target_col];
        for (int row = 0; row < 8; ++row){
            for (int col = 0; col < 8; ++col){
                Figure attacker = this.fields[row][col];
                if (attacker != null){
                    if (attacker.getColor() != target.getColor()) {
                        if (attacker.canAttack(row, col, target_row, target_col) && path_free(row, col, target_row, target_col)){
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean check_check(){
        char kingColor = this.getColorGaming();
        String position = get_king_position(kingColor);
        int kingRow = Character.getNumericValue(position.charAt(0));
        int kingCol = Character.getNumericValue(position.charAt(1));
        System.out.println(kingRow);
        return is_under_attack(kingRow, kingCol);
    }
    // для проверки мата сначала проверим может ли король уйти от удара
    // и если нет, то могут ли закрыть короля другие фигуры
    public boolean checkmate_check(){
        char kingColor = this.getColorGaming();
        String position = get_king_position(kingColor);
        int kingRow = position.charAt(0);
        int kingCol = position.charAt(1);
        Figure poor_king = fields[kingRow][kingCol];

        for (int row = kingRow - 1; row < kingRow + 1; ++row){
            for (int col = kingCol - 1; col < kingCol + 1; ++col){
                if (poor_king.canMove(kingRow, kingCol, row, col)){
                    return false;
                }
            }
        }

        for (int row = 0; row < 8; ++row){
            for (int col = 0; col < 8; ++col){
                Figure figure = fields[row][col];
                if (figure != null && figure.getColor() == kingColor){
                    for (int defendRow = 0; defendRow < 8; ++defendRow){
                        for (int defendCol = 0; defendCol < 8; ++defendCol){
                            if (this.move_figure(row, col, defendRow, defendCol)){
                                if (!this.is_under_attack(kingRow, kingCol)) {
                                    this.move_figure(defendRow, defendCol, row, col);
                                    return false;
                                }
                                this.move_figure(defendRow, defendCol, row, col);
                            }
                        }
                    }
                }
            }
        }
        return true;
    }
}