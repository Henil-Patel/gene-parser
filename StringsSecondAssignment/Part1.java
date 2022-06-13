
/**
 * Write a description of class Part1 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part1
{
    public int findStopCodon(String dna, int startIndex, String stopCodon)
    {
        int currIndex = dna.indexOf(stopCodon, startIndex + 3);
        while (currIndex != -1){
            if ((currIndex - startIndex) % 3 == 0){
                    return currIndex;
                }
            else{
                    currIndex = dna.indexOf(stopCodon, currIndex + 1);
            }
        }
        return -1; 
    }
    
    public String findGene(String dna, int currIndex)
    {
        String gene = "";
        int startIndex = dna.indexOf("ATG", currIndex);
        if (startIndex == -1){
            return "";
        }
        int firstTAA = findStopCodon(dna, startIndex, "TAA");
        int firstTAG = findStopCodon(dna, startIndex, "TAG");
        int firstTGA = findStopCodon(dna, startIndex, "TGA");
        int minIndex = 0;
        if (firstTAA == -1 || (firstTAG != -1 && firstTAG < firstTAA)){
            minIndex = firstTAG;
        }
        else{
            minIndex = firstTAA;
        }
        
        if (minIndex == -1 || (firstTGA != -1 && firstTGA < minIndex)){
            minIndex = firstTGA;
        }
        if (minIndex == -1){
            return "";
        }
        
        return dna.substring(startIndex, minIndex + 3);
    }
    
    public void testFindStopCodon()
    {
        String dna1 = "ATGTAATAACGATG";
        String dna2 = "TAATGACGAGCTAG";
        String dna3 = "CATGCAGTACGCAT";
        int out1 = findStopCodon(dna1, 0, "TAA");
        assert out1 == 3: "Stop codon index mismatch";
        int out2 = findStopCodon(dna2, 2, "TAG");
        assert out2 == 11: "Stop codon index mismatch";
        int out3 = findStopCodon(dna3, 1, "TGA");
        assert out3 == dna3.length(): "Invalid stop codon";
    }
    
    public void testFindGene(){
        // MULTIPLE VALID STOP CODONS
        String dna1 = "ATGTAATAACGATG";
        // NO ATG
        String dna2 = "TAATGACGAGCTAG";
        // NO STOP CODON
        String dna3 = "CATGCAGTACGCAT";
        String gene1 = findGene(dna1, 0);
        System.out.println("Gene1: " + gene1);
        String gene2 = findGene(dna2, 0);
        System.out.println("Gene2: " + gene2);
        String gene3 = findGene(dna3, 0);
        System.out.println("Gene3: " + gene3);
    }
    
    public void printAllGenes(String dna){
        int currIndex = 0;
        while(true){
            String gene = findGene(dna, currIndex);
            System.out.println("GENE: " + gene);
            if (gene.isEmpty()){
                break;
            }
            
            // Update currIndex to point to the next start codon
            currIndex = dna.indexOf(gene, currIndex) + 
                        gene.length();
            
        }
    }
    
    public static void main(String[] args)
    {
        Part1 p1 = new Part1();
        // VALID CODONS TAA, TAG, TGA
        String dna = "TAAGTCTACTGATGCATCGATACTAGCATATGCAGTAAGTAATGATGATC";
        p1.printAllGenes(dna);
    }
}
