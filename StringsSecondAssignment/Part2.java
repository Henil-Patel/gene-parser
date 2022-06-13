
/**
 * Write a description of class Part2 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part2
{
    public int howMany(String stringa, String stringb){
        // Returns how many times stringa appeared in stringb
        // ex. stringa could be a substring, stringb is dna
        int count = 0;
        int currIndex = 0;
        
        // First instance
        currIndex = stringb.indexOf(stringa, currIndex);
        while(true){
            if(currIndex == -1){
                System.out.println("Cannot find anymore. Exiting.");
                break;
            }
            else{
                count = count + 1;
            }
            // Update currIndex 
            currIndex = currIndex + stringa.length(); 
            currIndex = stringb.indexOf(stringa, currIndex);
            
        }
        return count;
    }
    
    public void testHowMany(){
        String e1a = "GAA";
        String e1b = "ATGAACGAATTGAATC"; 
        String e2a = "AA";
        String e2b = "ATAAAA";
        
        int ct1 = howMany(e1a, e1b);
        System.out.println("Example 1: " + ct1);
        int ct2 = howMany(e2a, e2b);
        System.out.println("Example 2: " + ct2);
    }
    
    
    
    public static void main(String[] args)
    {
        Part2 p2 = new Part2();
        p2.testHowMany();
    }
}
