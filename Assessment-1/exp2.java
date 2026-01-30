class Area {
    double area(int side) {
        return side * side;
    }

    double area(int length, int breadth) {
        return length * breadth;
    }

    double area(double radius) {
        return Math.PI * radius * radius;
    }

    public static void main(String[] args) {
        Area obj = new Area();

        System.out.println("Area of Square: " + obj.area(5));
        System.out.println("Area of Rectangle: " + obj.area(4, 6));
        System.out.println("Area of Circle: " + obj.area(7.0));
    }
}