
/**
 * 在这里给出对类 Part2 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part2 {
    String findSimpleGene(String dna, String startCodon, String stopCodon) {
        String ans = "";
        int startIndex = dna.indexOf(startCodon);
        if (startIndex != -1) {
            int stopIndex = dna.indexOf(stopCodon, startIndex + 3);
            if (stopIndex != -1 ) {
                if ((stopIndex - startIndex) % 3 == 0) {
                    ans = dna.substring(startIndex, stopIndex + 3);
                }
            }
        }
        return ans;
    }
    void testSimpleGene() {
        //String dna = "ATGGGTTAAGTC";
        String dna = "gatgctataat";
        String startCodon = "ATG";
        String stopCodon = "TAA";
        System.out.println("The DNA is " + dna);
        String gene = findSimpleGene(dna.toUpperCase(), startCodon, stopCodon);
        System.out.println("The simple gene of this dna is " + gene.toLowerCase());
    }
}
