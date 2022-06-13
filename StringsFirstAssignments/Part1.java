import edu.duke.*;
import java.io.File;
/**
 * Write a description of class Part1 here.
 *h
 * @author (Henil Patel)
 * @version (Jan 2022)
 */
public class Part1
{

    public String findSimpleGene(String dna)
    {
        // put your code here
        String gene = "";
        // Get substring of start codon no offset
        int startCodon = dna.indexOf("ATG");
        if (startCodon == -1){
            return gene;
        }
        // Get substring of stop codon offset by 3 indexes from 
        // the index of start codon
        int stopCodon = dna.indexOf("TAA", startCodon + 3);
        if (stopCodon == -1){
            return gene;
        }
        // Get substring between ATG and TAA but not including the codons
        gene = dna.substring(startCodon+3, stopCodon);
        // Test if the gene length is divisible by 3
        if (gene.length() % 3 == 0){
            gene = "ATG" + gene + "TAA";
        }
        else{
            gene = "";
        }
        
        return gene;
    }
    
    public void testSimpleGene()
    {
        // Created a for loop containing all the tests required
        String[] dna_tests = {"ATCGACTAA","ATGCAGTTA","CATCGTACTATT","ATGCGTATAA", "ATGGTATAGTAA", "AAATGCCCTAACTAGATTAAGAAACC"};
        // Loop over each element in dna_tests
        for (String dna: dna_tests){
            // Check if input dna string has a valid gene
            String gene = findSimpleGene(dna);
            // Print gene (should be empty if the gene string does not meet constraints)
            System.out.println("Substring is = " + gene);
        }
    }
    
    public static void main(String[] args)
    {
        Part1 p1 = new Part1();
        p1.testSimpleGene();
    }
}
