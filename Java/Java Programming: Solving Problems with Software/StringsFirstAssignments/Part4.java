
/**
 * 在这里给出对类 Part4 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
import edu.duke.*;
public class Part4 {
    URLResource ur = new URLResource(
    "http://www.dukelearntoprogram.com/course2/data/manylinks.html");
    String target = "youtube.com";
    void findYouTubeLinks() {
   	for (String item : ur.words()) {
       	   String itemLower = item.toLowerCase();
       	   int pos = itemLower.indexOf("youtube.com");
       	   if (pos != -1) {
               int beg = item.lastIndexOf("\"",pos);
               int end = item.indexOf("\"", pos+1);
               System.out.println(item.substring(beg+1,end));
           }
   	}
        // return ans;
    }
    // void testing() {
        // String ans = findYouTubeLinks();
        // if (ans != "") {
            // System.out.println(ans);
        // }
    // }
}
