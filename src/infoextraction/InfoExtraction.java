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
        String testing_file_address = "C:\\Users\\Kaihua\\Desktop\\Assignment\\Testing.txt";
        run(training_file_address, testing_file_address);
    }
    
    public static void run(String training_file_address, String testing_file_address) throws IOException{
        
        InformationRecognition ir = new InformationRecognition(training_file_address, testing_file_address);
        
        //Test
        String text = "agent : Hello Mr. Liu , how can I help you ? customer : Yes, I will have a trip to Suzhou next Tuesday . And I am in Beijing now . agent : When will you come back ? customer : Next weekend , Saturday or Sunday .";
        ir.extract();     
    }
    
}
