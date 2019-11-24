
/**
 * 在这里给出对类 Part3 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;

public class Part3 {
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
        int startIndex = dna.indexOf("ATG", where);
        if (startIndex == -1) {
            return "";
        }
        int taaIndex = findStopCodon(dna, startIndex, "TAA");
        int tagIndex = findStopCodon(dna, startIndex, "TAG");
        int tgaIndex = findStopCodon(dna, startIndex, "TGA");
        //note that if taaIndex does exit, it will return the maxn(the
        //length of dna, so we only need to take care of the minimun of 
        //those stopcodons
        int minIndex = Math.min(taaIndex, Math.min(tagIndex, tgaIndex));
        if (minIndex == dna.length()) {
            return "";
        }
        return dna.substring(startIndex, minIndex + 3);
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
        int startIndex = 0;
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene.isEmpty()) {
                break;
            }
            System.out.println(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) +
                         currentGene.length();
        }
    }
    void testPrintAllGenes() {
        String dna = "ATGTTTACGTAGTGAGATGCGTTAGGATGTTTTTAATGTACGGGCTAA";
        printAllGenes(dna);
    }
    public StorageResource getAllGenes(String dna) {
        int startIndex = 0;
        StorageResource sr = new StorageResource();
        while (true) {
            String currentGene = findGene(dna, startIndex);
            if (currentGene == "") {
                break;
            }
            sr.add(currentGene);
            startIndex = dna.indexOf(currentGene, startIndex) +
                         currentGene.length();
                         //take care of we should start from the new gene
                         //instead of old startIndex
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
    void processGenes(StorageResource sr) {
        int numOfLengthLongerThan60 = 0;
        int numOfCGRatioHigherThanDot35 = 0;
        for (String tmp : sr.data()) {
            if (tmp.length() > 60) {
                System.out.println(tmp);
                numOfLengthLongerThan60 += 1;
            }
        }
        System.out.println("the number of Strings in sr that are longer " +
        "than 60 characters is " + numOfLengthLongerThan60);
        for (String tmp : sr.data()) {
            if (cgRatio(tmp) > 0.35) {
                System.out.println(tmp);
                numOfCGRatioHigherThanDot35 += 1;
            }
        }
        System.out.println("the number of strings in sr whose C-G-ratio" +
        " is higher than 0.35 is " + numOfCGRatioHigherThanDot35);
        int longestLength = 0;
        for (String tmp : sr.data()) {
            if (tmp.length() > longestLength) {
                longestLength = tmp.length();
            }
        }
        System.out.println("The longest length of DNA is " + longestLength);
    }
    void testProcessGenes() {
        FileResource fr = new FileResource("dna/GRch38dnapart.fa");
        String dna = fr.asString();
        dna = dna.toUpperCase();
        StorageResource sr = getAllGenes(dna);
        System.out.println("The size of sr is :" + sr.size());
        // System.out.println(dna);
        processGenes(sr);
        int num = 0;
        int currentIndex = 0, startIndex = 0;
        while (true) {
            currentIndex = dna.indexOf("CTG", startIndex);
            if (currentIndex == -1) {
                break;
            }
            num += 1;
            startIndex = currentIndex + 3;
        }
        System.out.println("CTG appear in this strand of DNA " + num +
                            " times");
    }
    void findYouTubeLinks() {
        URLResource ur = new URLResource(
        "http://www.dukelearntoprogram.com/course2/data/manylinks.html");
        String target = "youtube.com";
   	for (String item : ur.words()) {
       	   String itemLower = item.toLowerCase();
       	   int pos = itemLower.indexOf("youtube.com");
       	   if (pos != -1) {
               int beg = item.lastIndexOf("\"",pos);
               int end = item.indexOf("\"", pos+1);
               System.out.println(item.substring(beg+1,end));
           }
   	}
    }
}
