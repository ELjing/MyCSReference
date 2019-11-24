
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part1 {
    String findSimpleGene(String dna) {
        String ans = "";
        int startIndex = dna.indexOf("ATG");
        if (startIndex != -1) {
            int stopIndex = dna.indexOf("TAA", startIndex + 3);
            if (stopIndex != -1 ) {
                if ((stopIndex - startIndex) % 3 == 0) {
                    ans = dna.substring(startIndex, stopIndex + 3);
                }
            }
        }
        return ans;
    }
    void testSimpleGene() {
        String dna = "AAATGCCCTAACTAGATTAAGAAACC";
        System.out.println("The DNA is " + dna);
        String gene = findSimpleGene(dna);
        System.out.println("The simple gene of this dna is " + gene);
    }
}
