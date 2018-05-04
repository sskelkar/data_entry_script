package data_entry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class MMSCodeReader {

    public static List<String> readMMSCodes() {
        System.out.println("READING MMS CODES - START");
        List<String> codes = new LinkedList<>();
        for (int i = 1; i <= 28; i++) {
            String csvFile = "/Users/sojjwal/Documents/MMS_Codes/" + i + ".csv";
            codes.addAll(getCodesForFile(csvFile));
        }
        System.out.println(codes);
        System.out.println("READING MMS CODES - START");
        return codes;
    }

    private static List<String> getCodesForFile(String csvFile) {
        BufferedReader br = null;
        List<String> codes = new LinkedList<>();
        String line;
        try {

            br = new BufferedReader(new FileReader(csvFile));
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(",");
                if(tokens.length > 0) {
                    addCode(codes, tokens[0]);
                    addCode(codes, tokens[2]);
                    addCode(codes, tokens[4]);
                }
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
        return codes;
    }

    private static void addCode(List<String> codes, String token) {
        int length = token.trim().length();
        if (length > 0) {
            if (isOdd(length)) {
                token = "0" + token;
            }
            codes.add(token);
        }
    }

    private static boolean isOdd(int length) {
        return length % 2 == 1;
    }
}
