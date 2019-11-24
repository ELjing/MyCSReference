
/**
 * 在这里给出对类 Debugging_Part1 的描述。
 * 
 * @作者（你的名字）
 * @版本（一个版本号或者一个日期）
 */
public class Debugging_Part1 {
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            if (index == -1) {
                break;
            }
            System.out.println((index + 1) +  "," + (index + 4));
            String found = input.substring(index+1, index+4);
            System.out.println(found);
            index = input.indexOf("abc", index+4);
        }
    }
   public void test() {
        //findAbc("abcd");
        findAbc("abcdabc");
   }
   void myTest() {
       String s = "0123456789";
       System.out.println(s.substring(1,4));
       //substring也是左闭右开的
   }
}
