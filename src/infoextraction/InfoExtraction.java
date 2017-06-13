/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package infoextraction;

import infoextraction.InfoExtract.InformationRecognition;
import java.io.IOException;

/**
 *
 * @author Kaihua Liu
 */
public class InfoExtraction {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        String training_file_address = "C:\\Users\\Kaihua\\Desktop\\Assignment\\Training.txt";
        String testing_file_address = "";
        run(training_file_address, testing_file_address);
    }
    
    public static void run(String training_file_address, String testing_file_address) throws IOException{
        
        InformationRecognition ir = new InformationRecognition(training_file_address);
        ir.extract();
        
        
    }
    
}
