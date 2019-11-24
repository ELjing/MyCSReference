
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part2 {
    double cgRatio(String dna) {
        double ans = 0.0;
        int len = dna.length();
        for (int i = 0; i < len; ++ i) {
            char tmp = dna.charAt(i);
            if (tmp == 'C' || tmp == 'G') {
                ans = ans + 1.0;
            }
        }
        return ans / len;
    }
    void testCGRatio() {
        String dna = "ATGCCATAG";
        System.out.println(cgRatio(dna));
    }
    int countCTG(String dna) {
        int ans = 0;
        int where = 0;
        while (true) {
            int tmp = dna.indexOf("CTG", where);
            if (tmp == -1) {
                break;
            }
            ans = ans + 1;
            where = tmp + 1;
        }
        return ans;
    }
    void testCountCTG() {
        String dna = "ATGCTGGTACGTACTGCTG";
        int ans = countCTG(dna);
        System.out.println("The number of CTG is: " + ans);
    }
}
