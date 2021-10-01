package com.example.mytraningapplication;

public class Field {

    private int height;
    private int width;
    private int[][] field;
    public Field(int height){
        this.height = height;
        this.width = height;
        field = new int[height][height];
    }
    public Field(int height, int width){
        this.height = height;
        this.width = width;
        field = new int[height][width];
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }
    public int[][] getValue() {
        return field;
    }

    /**
     * draws dot
     * @param y
     * @param x
     * @param widthOfPencil width of dot
     * @throws IndexOutOfBoundsException
     */
    public void draw(int y, int x, int widthOfPencil) throws IndexOutOfBoundsException{
        for(int drawForY = 0; drawForY < widthOfPencil; drawForY++){
            for(int drawForX = 0; drawForX < widthOfPencil; drawForX++){
                field[drawForY + y][drawForX + x] = 1;
            }
        }
    }
}
