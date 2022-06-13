import edu.duke.*;
import java.io.File;

/**
 * Write a description of class Part4 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part4
{
    
    
    public String YoutubeLink(String link){
        String outcome = "";
        if (link.toLowerCase().indexOf("youtube") != -1){
            outcome = extractLink(link);
            return outcome;
        }
        return null;
    }
    
    public String extractLink(String link){
        int startQuote = link.indexOf('\"');
        int endQuote = link.indexOf('\"', startQuote + 1);
        if (link.lastIndexOf("href") > endQuote){
            startQuote = link.indexOf('\"', link.lastIndexOf("href"));
            endQuote = link.indexOf('\"', startQuote + 1);
        }
        String extractYoutube = link.substring(startQuote, endQuote + 1);
        return extractYoutube;
    }

    
    public void testYoutubeLink(){
        String manyLinks = "https://www.dukelearntoprogram.com//course2/data/manylinks.html"; 
        URLResource ur = new URLResource(manyLinks);
        String extractedLink = "";
        for (String link: ur.lines()){
            extractedLink = YoutubeLink(link);
            if (extractedLink != null){
                System.out.println(extractedLink);
            }
        }
        
        
    }
    
    public static void main(String[] args)
    {
        Part4 p4 = new Part4();
        p4.testYoutubeLink();
    }
}
