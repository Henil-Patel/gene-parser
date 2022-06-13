import edu.duke.*;
import java.io.File;

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
    public StorageResource getAllGenes(String dna){
        int currIndex = 0;
        StorageResource geneList = new StorageResource();
        while(true){
            String gene = findGene(dna, currIndex);
            if (gene.isEmpty()){
                break;
            }
            geneList.add(gene);
            // Update currIndex to point to the next start codon
            currIndex = dna.indexOf(gene, currIndex) + 
                        gene.length();
            
        }
        return geneList;
    }
    
    public void testStorageRes(String dna){
        System.out.println("Testing method on " + dna);
        StorageResource genes = getAllGenes(dna);
        for (String g: genes.data()){
            System.out.println(g);
        }
    }
    
    public float cgRatio(String dna){
        float ratio = 0.0f;
        int CCount = 0;
        int GCount = 0;
        for (int i = 0; i < dna.length(); i++){
            // System.out.println(dna.charAt(i));
            if (dna.charAt(i) == 'G'){
                GCount = GCount + 1;
            }
            if (dna.charAt(i) == 'C'){
                CCount = CCount + 1;
            }
        }
        ratio = (float) (CCount + GCount)/dna.length();
        System.out.println(ratio);
        return ratio;
    }
    
    public int countCTG(String dna){
        int count = 0;
        int currIndex = dna.indexOf("CTG");
        
        while (currIndex != -1){
            // System.out.println("CTG Found At: " + currIndex);
            count = count + 1;
            currIndex = dna.indexOf("CTG", currIndex + 1);
        }
        System.out.println(count);
        return count;
    }
    
    public void processGenes(StorageResource sr){
        int gr9 = 0;
        int countCtoG = 0;
        int maxLength = 0;
        int totCount = 0;
        for (String g: sr.data()){
            // System.out.println(g);
            float CtoG = 0.0f;
            CtoG = cgRatio(g);
            
            if (g.length() > maxLength){
                maxLength = g.length();   
            }
            
            if (g.length() > 60){
                //System.out.println("Length greater than 9");
                //System.out.println(g);
                gr9 = gr9 + 1;
            }
            if (CtoG > 0.35){
                //System.out.println("C-to-G ratio greater than 0.35");
                //System.out.println(g);
                countCtoG = countCtoG + 1;
            }
            totCount = totCount + 1;
        }
        System.out.println("\n===================================\n");
        System.out.println("Count C-to-G > 0.35: " + countCtoG);
        System.out.println("Len > 60: " + gr9);
        System.out.println("Max len: " + maxLength);
        System.out.println("Total Count: " + totCount);
    }
    
    public void testProcessGenes(){
        FileResource fr = new FileResource("GRch38dnapart.fa");
        String contents = fr.asString();
        int count = countCTG(contents);
        
        StorageResource sr = getAllGenes(contents);
        processGenes(sr);
        System.out.println("CTG Count: " + count);
    }

    public static void main(String[] args)
    {
        Part1 p1 = new Part1();
        // VALID CODONS TAA, TAG, TGA
        // String dna = "TAAGTCTACTGATGCATCGATACTAGCATATGCAGTAAGTAATGATGATC";
        // p1.testStorageRes(dna);
        //String dna = "TAACTGGTAATCGATGTACTGACTGCTGGTAGAATCCGTTCATTGACTGATCG";
        //float ratio = p1.cgRatio(dna);
        //int count = p1.countCTG(dna);
        p1.testProcessGenes();
    }
}
