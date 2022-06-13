
/**
 * Write a description of class D1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class D1
{
    public void findAbc(String input) {
        int index = input.indexOf("abc");
        while (true) {
            System.out.println("index " + index);
            if (index == -1 || index >= input.length() - 3) {
                break;
            }
            int lower = index + 1;
            int upper = index + 4;
            String found = input.substring(lower, upper);
            System.out.println(found);
            index = input.indexOf("abc", index+3);
            System.out.println("index after updating " + index);
        }
    }
   public void test() {
        //findAbc("abcd");
        //findAbc("abcdabc");
        //findAbc("woiehabchi");
        //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
        //findAbc("abcdkfjsksioehgjfhsdjfhksdfhuwabcabcajfieowj");
        findAbc("abcabcabcabca");
    }
    public static void main(String[] args)
    {
        D1 debug1 = new D1();
        debug1.test();
    }
    
}
