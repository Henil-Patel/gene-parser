
/**
 * Write a description of class Part3 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part3
{
    
    // Trying out empty constructors ;)
    public Part3()
    {
        ;
    }

    //Method 1 - twoOccurences - return true if a occurs 'at least twice' in b (i.e # occurences of a >= 2)
    /**
     * Method 1 - twoOccurences
     * Purpose of this method is to check if String a occurs 'at least twice' in String b
     * Return true if it occurs >= 2 times
     * Return false otherwise
    */
    public boolean twoOccurences(String a, String b){
        int countOccurence = 0;
        boolean outcome = false;
        int index = b.indexOf(a);
        if(index == -1){
            outcome = false;
        }
        else{
            countOccurence = countOccurence + 1;
            
            for(int i = 0; i < b.length(); i++){
                index = b.indexOf(a, index+1);
                if (index == -1){
                    break;
                }
                else{
                    countOccurence = countOccurence + 1;
                    if(countOccurence >= 2){
                        outcome = true;
                    }
                }
            }
        }
        
        System.out.println(a + " occurred " + countOccurence + " times");
        return outcome;
    }

    
    public String lastPart(String a, String b){
        int index = b.indexOf(a);
        if (index == -1){
            return b;
        }
        else{ // index >= 0
            return b.substring(index + a.length(), b.length());
        }
    }
    /**
     * Tester method to check if twoOccurences actually works correctly
    */
    public void testlastPart(){
        String a = "zoo";
        String b = "forest";
        String result = lastPart(a, b);
        System.out.println("Last part " + result);
    }
    
    public void testtwoOccurences(){
        String a = "atg";
        String b = "tatggctatg";
        boolean result = twoOccurences(a, b);
        System.out.println("Two occurences? " + result);
    }
    
    
    
    
    public static void main(String[] args)
    {
        Part3 p3 = new Part3();
        p3.testtwoOccurences();
        p3.testlastPart();
    }
    
    
}
