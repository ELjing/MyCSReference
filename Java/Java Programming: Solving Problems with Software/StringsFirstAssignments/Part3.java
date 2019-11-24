
/**
 * 在这里给出对类 Part3 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part3 {
    boolean twoOccurrences(String stringa, String stringb) {
        boolean flag = false;
        //int lenA = stringa.length();
        int firstOccurrence = stringb.indexOf(stringa);
        if (firstOccurrence != -1) {
            if (stringb.indexOf(stringa, firstOccurrence + 1) != -1) {
                flag = true;
            }
        }
        return flag;
    }
    void testing() {
        //String a = "an", b = "banana";
        String a = "zoo", b = "forest";
        //String a = "atg", b = "ctgtatgta";
        //System.out.println("1. "+ twoOccurrences(a, b));
        System.out.println("2. "+ lastPart(a, b));
    }
    String lastPart(String stringa, String stringb) {
        String ans = stringb;
        int index = stringb.indexOf(stringa);
        if (index != -1) {
            int lenA = stringa.length();
            ans = stringb.substring(index + lenA); 
        }
        return ans;
    }
 }
