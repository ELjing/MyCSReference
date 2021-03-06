
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Part1 {
    int findStopCodon(String dna, int startIndex, String stopCodon) {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1) {
            if ((currIndex - startIndex) % 3 == 0) {
                return currIndex;
            } else {
                currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return dna.length();
         // returns the index of the first occurrence of stopCodon 
         // that appears past startIndex and is a multiple of 3 away
         // from startIndex. If there is no such stopCodon, this method 
         //returns the length of the dna strand.
    }
    void testFindStopCodon() {
        
        //String dna = "ATGGGTTAA"; //6
        //String dna = "GATGTTT"; //no TAA, return the dna length
        String dna = "ATGTACGGGCTAA"; //not multiple of 3
        //System.out.println(dna.indexOf("TAA"));
        int ans = findStopCodon(dna, 0, "TAA");
        System.out.println(ans);
    }
    String findGene(String dna, int where) {
        int atgIndex = dna.indexOf("ATG", where);
        if(atgIndex != -1) {
            int taaIndex = findStopCodon(dna, atgIndex, "TAA");
            // Find the index of the first occurrence of the stop codon “TAG” 
            // after the first occurrence of “ATG” that is a multiple of three
            // away from the “ATG”. Find the index of the first occurrence of
            // the stop codon “TGA” after the first occurrence of “ATG” that
            // is a multiple of three away from the “ATG”.
            int tagIndex = findStopCodon(dna, atgIndex, "TAG");
            int tgaIndex = findStopCodon(dna, atgIndex, "TGA");
            //note that if taaIndex does exit, it will return the maxn(the
            //length of dna, so we only need to take care of the minimun of 
            //those stopcodons
            int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
            if (minIndex == dna.length()) {
                return "";
            } else {
                return dna.substring(atgIndex, minIndex + 3);
            }
            // int MAXN = 10000;
            // if (taaIndex == -1) {
                // taaIndex = MAXN;
            // }
            // if (tagIndex == -1) {
                // tagIndex = MAXN;
            // } 
            // if (tgaIndex == -1) {
                // tgaIndex = MAXN;
            // }
            // int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
            // if (minIndex == MAXN) return "";
            // else {
                // return dna.substring(atgIndex, minIndex + 3);
            // }
        }
        // Find the index of the first occurrence of the start codon “ATG”. 
        // If there is no “ATG”, return the empty string.
        return "";
    }
    void testFindGene() {
        // String dna = "GATGCGTTAGTTA";
        // System.out.println("The DNA strand is " + dna);
        // System.out.println("The Gene is " + findGene(dna));
        //String dna = "TTA";
        String dna = "AATGCTAACTAGCTGACTAAT";
        System.out.println("The DNA strand is " + dna);
        System.out.println("The Gene is " + findGene(dna, 0));
    }
    void printAllGenes(String dna) {
        int start = 0;
        while (true) {
            String currGene = findGene(dna, start);
            if (currGene == "") {
                break;
            }
            System.out.println(currGene);
            start = start + currGene.length();
        }
    }
    void testPrintAllGenes() {
        String dna = "ATGTTTACGTAGTGAGATGCGTTAGGATGTTTTTAATGTACGGGCTAA";
        printAllGenes(dna);
    }
}
