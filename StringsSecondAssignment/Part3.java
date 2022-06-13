
/**
 * Write a description of class Part3 here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Part3
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
    public void printAllGenes(String dna){
        int currIndex = 0;
        while(true){
            String gene = findGene(dna, currIndex);
            System.out.println("GENE: " + gene);
            if (gene.isEmpty()){
                System.out.println("Cannot find anymore. Exiting.");
                break;
            }
            
            // Update currIndex to point to the next start codon
            currIndex = dna.indexOf(gene, currIndex) + 
                        gene.length();
            
        }
    }
    
    public int countGenes(String dna){
        int currIndex = 0;
        int counter = 0;
        while(true){
            String gene = findGene(dna, currIndex);
            System.out.println("Gene: " + gene);
            if (gene.isEmpty()){
                System.out.println("Cannot find anymore. Exiting.");
                break;
            }
            else{
                counter = counter + 1;
            }
            // Update currIndex to point to the next start codon
            currIndex = dna.indexOf(gene, currIndex) + gene.length();
        }
        return counter;
    }
    public static void main(String[] args)
    {
        Part3 p3 = new Part3();
        // VALID CODONS TAA, TAG, TGA
        // String dna = "TAAGTCTACTGATGCATCGATACTAGCATATGCAGTAAGTAATGATGATC";
        String dna = "AATGCTAACTAGCTGACTAAT";
        int count = p3.countGenes(dna);
        System.out.println("# of Genes: " + count);
    }
}
