package data_entry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExcelReader {

    public static List<String> readExcel() {
        System.out.println("READING DRAFT PROHIBITIONS EXCEL - START");
        String csvFile = "/Users/sojjwal/Documents/DRAFT_PROHIBITIONS/Schools and Buyer-Table 1.csv";
        BufferedReader br = null;
        List<String> lines = new LinkedList<>();
        String line;
        try {

            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                lines.add(line);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("READING DRAFT PROHIBITIONS EXCEL - END");
        return lines;
    }
}
