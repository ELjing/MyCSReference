
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
    CSVRecord coldestHourInFile(CSVParser parser) {
        CSVRecord coldestRecord = null;
        for (CSVRecord currentRecord : parser) {
            String currentStrTemper = currentRecord.get("TemperatureF");
            
            double currentDoubleTemper = Double.parseDouble(currentStrTemper);
            if (currentDoubleTemper != -9999) {
                if (coldestRecord == null) {
                    coldestRecord = currentRecord;
                } 
                else {
                    String coldestStrTemper = coldestRecord.get("TemperatureF");
                    double coldestDoubleTemper = Double.parseDouble(coldestStrTemper);
                    //System.out.println(currentDoubleTemper + ":" + coldestDoubleTemper);
                    if (currentDoubleTemper < coldestDoubleTemper) {
                        coldestRecord = currentRecord;
                    }
                }
            }
        }
        return coldestRecord;
    }
    void testColdestHourInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println("The coldest temperature was " +
                            record.get("TemperatureF") + " at " +
                            record.get('N' - 'A'));
    }
    public String fileWithColdestTemperature() {
        String coldestfile = "";
        DirectoryResource dr = new DirectoryResource();
        double coldestTemper = 9999;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            if (coldestfile == "") {
                coldestfile = f.getName();
                coldestTemper = Double.parseDouble(coldestHourInFile(parser).get("TemperatureF"));
            } else {
                double currentTemper = Double.parseDouble(coldestHourInFile(parser).get("TemperatureF"));
                if (currentTemper < coldestTemper) {
                    coldestTemper = currentTemper;
                    coldestfile = f.getName();
                }
            }
        }
        return coldestfile;
    }
    void testFileWithColdestTemperature() {
        String fileName = fileWithColdestTemperature();
        System.out.println("Coldest day was in file " + fileName);
        FileResource fr = new FileResource("nc_weather/2013/" + fileName);
        // FileResource fr = new FileResource("nc_weather/2014/" + fileName);
        CSVParser parser = fr.getCSVParser();
        CSVRecord record = coldestHourInFile(parser);
        System.out.println("Coldest temperature on that day was " 
                            + record.get("TemperatureF"));
        System.out.println("All the Temperatures on the coldest day were:");
        parser = fr.getCSVParser();
        for (CSVRecord tmp : parser) {
            System.out.println(tmp.get("DateUTC") + ": "
                                + tmp.get("TemperatureF"));
        }
    }
    public CSVRecord lowestHumidityInFile(CSVParser parser) {
        CSVRecord lowestRecord = null;
        for (CSVRecord currentRecord : parser) {
            // there is not a number in the Humidity 
            //column but instead there is the string “N/A”
            if (!currentRecord.get("Humidity").equals("N/A")) {    
                if (lowestRecord == null) {
                    lowestRecord = currentRecord;
                } else {
                    double lowestHumid = Double.parseDouble(lowestRecord.get("Humidity"));
                    double currentHumid = Double.parseDouble(currentRecord.get("Humidity"));
                    if (currentHumid < lowestHumid) {
                        lowestRecord = currentRecord;
                    }
                }
            }
        }
        return lowestRecord;
    }
    void testLowestHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        CSVRecord csv = lowestHumidityInFile(parser);
        System.out.println("Lowest Humidity was " + csv.get("Humidity") 
                            + " at " + csv.get('N' - 'A'));
    }
    void lowestHumidityInManyFiles() {
        DirectoryResource dr = new DirectoryResource();
        CSVRecord lowestRecord = null;
        for (File f : dr.selectedFiles()) {
            FileResource fr = new FileResource(f);
            CSVParser parser = fr.getCSVParser();
            CSVRecord currentRecord = lowestHumidityInFile(parser);
            if (lowestRecord == null) {
                lowestRecord = currentRecord;
            }
            else {
                if (Double.parseDouble(currentRecord.get("Humidity")) <
                    Double.parseDouble(lowestRecord.get("Humidity")))
                        lowestRecord = currentRecord;
            }
        }
        System.out.println("Lowest Humidity was " + lowestRecord.get("Humidity") 
                            + " at " + lowestRecord.get('N' - 'A'));
    }
    public void testLowestHumidityInManyFiles() {
        lowestHumidityInManyFiles();
    }
    double averageTemperatureInFile(CSVParser parser) { 
        double sum = 0.0;
        int num = 0;
        for (CSVRecord record : parser) {
            num += 1;
            sum += Double.parseDouble(record.get("TemperatureF"));
        }
        if (num != 0) {
            return sum / num;
        } else {
            return 0;
        }
    }
    void testAverageTemperatureInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        System.out.println("Average temperature in file is " + 
                            averageTemperatureInFile(parser));
    }
    double averageTemperatureWithHighHumidityInFile(CSVParser parser,
                                                    int value) {
        double sum = 0.0;
        int num = 0;
        for (CSVRecord record : parser) {
            if (!record.get("Humidity").equals("N/A")) {
                if(Double.parseDouble(record.get("Humidity")) >= value) {
                    num += 1;
                    sum += Double.parseDouble(record.get("TemperatureF"));
                }
            }
        }
        if (num != 0) {
            return sum / num;
        } else {
            return 0;
        }
    }
    void testAverageTemperatureWithHighHumidityInFile() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        int value = 80;
        double ans = averageTemperatureWithHighHumidityInFile(parser, value);
        if(ans == 0) {
            System.out.println("No temperatures with that humidity");
        } else {
            System.out.println("Average Temp when high Humidity is " + 
                                ans);
        }
    }
}
