
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
import org.apache.commons.csv.*;
public class Part1 {
    public void listExporters(CSVParser parser, String exportOfInterest) {
        for(CSVRecord record : parser) {
            String export = record.get("Exports");
            if (export.contains(exportOfInterest)) {
                String country = record.get("Country");
                System.out.println(country);
            }
        }
    }
    public void tester() {
        FileResource fr = new FileResource();
        CSVParser parser = fr.getCSVParser();
        //listExporters(parser, "coffee");
        
        // String ans = countryInfo(parser, "Nauru");
        // System.out.println(ans);
        
        //listExportersTwoProducts(parser, "cotton", "flowers");
        
        // int ans = numberOfExporters(parser, "cocoa");
        // System.out.println("The number of Country who exports is " + ans);
        
        bigExporters(parser, "$999,999,999,999");
    }
    String countryInfo(CSVParser parser, String country) {
        for (CSVRecord record : parser) {
            if (record.get("Country").contains(country)) {
                //must use contains instead of using ==
                return country + ": " + record.get(1) +
                        ": "+record.get(2);
            }
        }
        return "NOT FOUND";
    }
    void listExportersTwoProducts(CSVParser parser, String exportItem1,
                                   String exportItem2) {
        for (CSVRecord record : parser) {
            String exports = record.get("Exports");
            if (exports.contains(exportItem1) && exports.contains(exportItem2)) {
                System.out.println(record.get(0));
            }
        }
    }
    public int numberOfExporters(CSVParser parser, String exportItem) {
        int ans = 0;
        for (CSVRecord record : parser) {
            if (record.get("Exports").contains(exportItem)) {
                ans += 1;
            }
        }
        return ans;
    }
    void bigExporters(CSVParser parser, String amount) {
        for (CSVRecord record: parser) {
            String value = record.get(2);
            if (value.length() > amount.length()) {
                System.out.println(record.get(0) + " " + record.get(2));
            }
        }
    }
}
