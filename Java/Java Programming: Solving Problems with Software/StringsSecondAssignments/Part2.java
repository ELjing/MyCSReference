
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part2 {
    int howMany(String stringa, String stringb) {
        int ans = 0;
        int where = 0;
        while (stringb.indexOf(stringa, where) != -1) {
            ans = ans + 1;
            where = stringb.indexOf(stringa, where) + stringa.length();
        }
        return ans;
    }
    void testHowMany() {
        // String stringa = "GAA";
        // String stringb = "ATGAACGAATTGAATC";
        String stringa = "AA";
        String stringb = "ATAAAA";
        System.out.println("Stringa appear in Stringb " + 
                            howMany(stringa, stringb) + " time(s)");
    }
}
