import edu.duke.*;
import java.io.File;
/**
 * Write a description of class Part1 here.
 *h
 * @author (Henil Patel)
 * @version (Jan 2022)
 */
public class Part2
{

    public String findSimpleGene(String dna, String startCodon, String stopCodon)
    {
        // put your code here
        String gene = "";
        boolean capsLock = false;
        // Check if DNA is upper case or lower case
        if (dna == dna.toUpperCase()){
            // This case covers upper case DNA
            // Check if there is a mismatch between Codons and DNA case & update settings to sync both
            if (startCodon != startCodon.toUpperCase() || stopCodon != stopCodon.toUpperCase()){
                startCodon = startCodon.toUpperCase();
                stopCodon = stopCodon.toUpperCase();
            }
            capsLock = true;
        }
        else{
            // This case covers lower case DNA
            // Check if there is a mismatch between Codons and DNA case & update settings to sync both
            if (startCodon != startCodon.toLowerCase() || stopCodon != stopCodon.toLowerCase()){
                startCodon = startCodon.toLowerCase();
                stopCodon = stopCodon.toLowerCase();
            }
            capsLock = false;
        }
        // Get substring of start codon no offset
        int startCodonIndex = dna.indexOf(startCodon);
        if (startCodonIndex == -1){
            return gene;
        }
        // Get substring of stop codon offset by 3 indexes from 
        // the index of start codon
        int stopCodonIndex = dna.indexOf(stopCodon, startCodonIndex + 3);
        if (stopCodonIndex == -1){
            return gene;
        }
        // Get substring between ATG and TAA but not including the codons
        gene = dna.substring(startCodonIndex+3, stopCodonIndex);
        // Test if the gene length is divisible by 3
        if (gene.length() % 3 == 0){
            gene = startCodon + gene + stopCodon;
        }
        else{
            gene = "";
        }
        
        return gene;
    }
    
    public void testSimpleGene()
    {
        // Created a for loop containing all the tests required
        String[] dna_tests = {"ATCGACTAA","ATGCAGTTA","CATCGTACTATT","ATGCGTATAA", "ATGGTATAGTAA".toLowerCase()};
        String start = "ATG";
        String stop = "TAA";
        
        // Loop over each element in dna_tests
        for (String dna: dna_tests){
            // Check if input dna string has a valid gene
            String gene = findSimpleGene(dna, start, stop);
            // Print gene (should be empty if the gene string does not meet constraints)
            System.out.println("Substring is = " + gene);
        }
    }
    
    public static void main(String[] args)
    {
        Part2 p2 = new Part2();
        p2.testSimpleGene();
    }
}
