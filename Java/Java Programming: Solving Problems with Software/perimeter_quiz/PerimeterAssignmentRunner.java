import edu.duke.*;
import java.io.File;

public class PerimeterAssignmentRunner {
    public double getPerimeter (Shape s) {
        // Start with totalPerim = 0
        double totalPerim = 0.0;
        // Start wth prevPt = the last point 
        Point prevPt = s.getLastPoint();
        // For each point currPt in the shape,
        for (Point currPt : s.getPoints()) {
            // Find distance from prevPt point to currPt 
            double currDist = prevPt.distance(currPt);
            // Update totalPerim by currDist
            totalPerim = totalPerim + currDist;
            // Update prevPt to be currPt
            prevPt = currPt;
        }
        // totalPerim is the answer
        return totalPerim;
    }

    public int getNumPoints (Shape s) {
        // Put code here
        int num = 0;
        for (Point tmp : s.getPoints()) {
            num = num + 1;
        }
        return num;
    }

    public double getAverageLength(Shape s) {
        // Put code here
        double totalDist = 0.0;
        Point prePt = s.getLastPoint();
        for (Point tmpPt : s.getPoints()) {
            totalDist = totalDist + tmpPt.distance(prePt);
            prePt = tmpPt;
        }
        return totalDist / getNumPoints(s);
    }

    public double getLargestSide(Shape s) {
        // Put code here
        Point prePt = s.getLastPoint();
        double LargestSide = 0.0;
        for (Point tmpPt : s.getPoints()) {
            double tmpSide = tmpPt.distance(prePt);
            if (tmpSide > LargestSide) {
                LargestSide = tmpSide;
            }
            prePt = tmpPt;
        }
        return LargestSide;
    }

    public double getLargestX(Shape s) {
        // Put code here
        double largestX = s.getLastPoint().getX();
        for (Point tmpPt : s.getPoints()) {
            if (tmpPt.getX() > largestX) {
                largestX = tmpPt.getX();
            }
        }
        return largestX;
    }

    public double getLargestPerimeterMultipleFiles() {
        // Put code here
        DirectoryResource dr = new DirectoryResource();
        double largestPrim = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double tmpPrim = getPerimeter(s);
            if (tmpPrim > largestPrim) {
                largestPrim = tmpPrim;
            }
        }
        return largestPrim;
    }

    public String getFileWithLargestPerimeter() {
        // Put code here
        File temp = null;    // replace this code
        DirectoryResource dr = new DirectoryResource();
        double largestPrim = 0.0;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            Shape s = new Shape(fr);
            double tmpPrim = getPerimeter(s);
            if (tmpPrim > largestPrim) {
                largestPrim = tmpPrim;
                temp = f;
            }
        }
        return temp.getName();
    }

    public void testPerimeter () {
        FileResource fr = new FileResource();
        Shape s = new Shape(fr);
        double length = getPerimeter(s);
        System.out.println("perimeter = " + length);
        System.out.println("The number of s = " + getNumPoints(s));
        System.out.println("The average length = " + getAverageLength(s));
        System.out.println("The largest side = "+ getLargestSide(s));
        System.out.println("The largest x is " + getLargestX(s));
    }
    
    public void testPerimeterMultipleFiles() {
        // Put code here
        double largestPrim = getLargestPerimeterMultipleFiles();
        System.out.println("The largest Primeter is " + largestPrim);
    }

    public void testFileWithLargestPerimeter() {
        // Put code here
        System.out.println(getFileWithLargestPerimeter());
    }

    // This method creates a triangle that you can use to test your other methods
    public void triangle(){
        Shape triangle = new Shape();
        triangle.addPoint(new Point(0,0));
        triangle.addPoint(new Point(6,0));
        triangle.addPoint(new Point(3,6));
        for (Point p : triangle.getPoints()){
            System.out.println(p);
        }
        double peri = getPerimeter(triangle);
        System.out.println("perimeter = "+peri);
    }

    // This method prints names of all files in a chosen folder that you can use to test your other methods
    public void printFileNames() {
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            System.out.println(f);
        }
    }

    public static void main (String[] args) {
        PerimeterAssignmentRunner pr = new PerimeterAssignmentRunner();
        //pr.testPerimeter();
        //pr.testPerimeterMultipleFiles();
        pr.testFileWithLargestPerimeter();
    }
}
