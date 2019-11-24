
/**
 * 在这里给出对类 Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
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
    StorageResource getAllGenes(String dna) {
        int start = 0;
        StorageResource sr = new StorageResource();
        while (true) {
            String currGene = findGene(dna, start);
            if (currGene == "") {
                break;
            }
            sr.add(currGene);
            //System.out.println(currGene);
            start = start + currGene.length();
        }
        return sr;
    }
        void testGetAllGenes() {
        String dna = "ATGTTTACGTAGTGAGATGCGTTAGGATGTTTTTAATGTACGGGCTAA";
        StorageResource sr = getAllGenes(dna);
        System.out.println("testGetAllGenes :");
        for (String g : sr.data()) {
            System.out.println(g);
        }
        System.out.println("printAllGenes :");
        printAllGenes(dna);
    }
}
