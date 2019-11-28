
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;
public class Part1 {
    public void printNames () {
        FileResource fr = new FileResource();
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            System.out.println("Name " + rec.get(0) + " Genger " + rec.get(1) +
                               " Num Born " + rec.get(2));
        }
    }
    void totalBirths(FileResource fr) {
        int girlBirths = 0;
        int girlNameNum = 0;
        int boyNameNum = 0;
        int boyBirths = 0;
        for (CSVRecord rec : fr.getCSVParser(false)) {
            int numBorn = Integer.parseInt(rec.get(2));
            if (rec.get(1).equals("F")) {
                girlBirths += numBorn;
                girlNameNum ++;
            } else {
                boyBirths += numBorn;
                boyNameNum ++;
            }
        }
        int totalBirths = girlBirths + boyBirths;
        int totalNameNum = girlNameNum + boyNameNum;
        System.out.println("totalBirths = " + totalBirths + ",totalName = " +
                            totalNameNum);
        System.out.println("girlBirths = " + girlBirths + ",girlNameNum = " +
                            girlNameNum);
        System.out.println("boyBirths = " + boyBirths + ",boyNameNum = " + 
                            boyNameNum);
    }
    void testTotalBirths() {
        FileResource fr = new FileResource();
        totalBirths(fr);
    }
    int getRank(int year, String name, String gender) {
        int rk = 1;
        boolean isExist = false;
        String path = "us_babynames/us_babynames_by_year/";
        FileResource fr = new FileResource(path + "yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currentGender = rec.get(1);
            String currentName = rec.get(0);
            if (currentGender.equals(gender)) {
                if (currentName.equals(name)) {
                    isExist = true;
                    break;
                }
                rk ++;
            }
        }
        if (isExist) {
            return rk;
        } else {
            return -1;
        }
    }
    void testGetRank() {
        // int year = 1960;
        // String name = "Emily";
        // String gender = "F";
        int year = 1971;
        String name = "Frank";
        String gender = "M";
        System.out.println(name + " gets rank equals " + getRank(year, name,
                           gender) + " in " + year);
        
    }
    String getName(int year, int rank, String gender) {
        String name = "NO NAME";
        int currentId = 0;
        String path = "us_babynames/us_babynames_by_year/";
        FileResource fr = new FileResource(path + "yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currentGender = rec.get(1);
            String currentName = rec.get(0);
            if (currentGender.equals(gender)) {
                currentId += 1;
                if (currentId == rank) {
                    name = currentName;
                    break;
                }
            }
        }
        return name;
    }
    void testGetName() {
        // int year = 1980;
        // int rank = 350;
        // String gender = "F";
        int year = 1982;
        int rank = 450;
        String gender = "M";
        System.out.println("The rank " + rank + " " + gender + " is " 
                            + getName(year, rank, gender));
    }
    void whatIsNameInYear(String name, int year, int newYear, String gender) {
        int rank = getRank(year, name, gender);
        String newName = getName(newYear, rank, gender);
        if (gender.equals("F")) {
            System.out.println(name + " born in " +year + " would be " + 
                                newName + " if she was born in " + newYear +".");
        } else {
            System.out.println(name + " born in " +year + " would be " + 
                                newName + " if he was born in " + newYear +".");
        }
    }
    void testWhatIsNameInYear() {
        // String name = "Susan"; 
        // int year = 1972;
        // int newYear = 2014;
        // String gender = "F";
        String name = "Owen"; 
        int year = 1974;
        int newYear = 2014;
        String gender = "M";
        whatIsNameInYear(name, year, newYear, gender);
    }
    int yearOfHighestRank(String name, String gender) {
        int highestYear = -1;
        int highestRank = 0xfffffff;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));
            int currentRank = getRank(year, name, gender);
            if (currentRank != -1 && currentRank < highestRank) {
                highestRank = currentRank;
                highestYear = year;
            }
        }
        return highestYear;
    }
    void testYearOfHighestRank() {
        // String name = "Genevieve";
        // String gender = "F";
        String name = "Mich";
        String gender = "M";
        int year = yearOfHighestRank(name, gender);
        System.out.println("year of highest rank is " + year);
    }
    double getAverageRank(String name, String gender) {
        double avgRank = 0;
        int num = 0;
        DirectoryResource dr = new DirectoryResource();
        for (File f : dr.selectedFiles()) {
            String fileName = f.getName();
            int year = Integer.parseInt(fileName.substring(3, 7));
            int currentRank = getRank(year, name, gender);
            if (currentRank != -1) {
                avgRank += (double) currentRank;
                // System.out.println("currentRank = " + currentRank);
                // System.out.println("avgRank = " + avgRank);
                num ++;
            } else {
                avgRank = -1.0;
                break;
            }
        }
        if (avgRank != -1.0) {
            avgRank /= num;
        }
        return avgRank;
    }
    void testGetAverageRank() {
        // String name = "Susan";
        // String gender = "F";
        String name = "Robert";
        String gender = "M";
        double avgRank = getAverageRank(name, gender);
        System.out.println("The average rank of " + gender + " " + name +
                           " is " + avgRank);
    }
    int getTotalBirthsRankedHigher(int year, String name, String gender) {
        int num = 0;
        int rank = getRank(year, name, gender);
        int currentId = 1;
        String path = "us_babynames/us_babynames_by_year/";
        FileResource fr = new FileResource(path + "yob"+year+".csv");
        for (CSVRecord rec : fr.getCSVParser(false)) {
            String currentGender = rec.get(1);
            if (currentGender.equals(gender)) {
                if (currentId < rank) {
                    num += Integer.parseInt(rec.get(2));
                    currentId ++;
                }
            }
        }
        return num;
    }
    void testGetTotalBirthsRankedHigher() {
        // int year = 1990;
        // String name = "Emily";
        // String gender = "F";
        int year = 1990;
        String name = "Drew";
        String gender = "M";
        System.out.println("getTotalBirthsRankedHigher = " + 
                            getTotalBirthsRankedHigher(year, name, gender));
    }
}
