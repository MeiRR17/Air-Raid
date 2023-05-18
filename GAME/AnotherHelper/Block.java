//package AnotherHelper;
//public class Block {
//    private int x;
//    private int y;
//    private int r;
//    private int g;
//    private int b;
//    private int endY;
//
//public Block(int x, int y, Map<String, Integer> colour) {
//        this.x = x;
//        this.y = y;
//        this.r = colour.get("r");
//        this.g = colour.get("g");
//        this.b = colour.get("b");
//        this.endY = y;
//        }
//
//public void draw() {
//        if (this.y < this.endY) {
//        this.y += 2;
//        }
//        noStroke();
//        fill(this.r, this.g, this.b);
//        rect(this.x, this.y, 20, 20);
//        }
//
//public void moveDown() {
//        if (this.y == this.endY) {
//        this.endY += 20;
//        }
//        }
//
//public void setRgb(Map<String, Integer> rgb) {
//        this.r = rgb.get("r");
//        this.g = rgb.get("g");
//        this.b = rgb.get("b");
//        }
//
