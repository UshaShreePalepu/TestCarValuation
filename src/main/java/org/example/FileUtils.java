package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtils {
        private static final String regex = "\\b[A-Z]{2}[0-9]{2}\\s?[A-Z]{3}\\b";
        private static final String inputFileName = "src/test/resources/car_input.txt";
        private static final String expectedOutputFileName = "src/test/resources/car_input.txt";
    public static String[] readCarNumbers() throws IOException {
        // Regex pattern for car numbers in UK
        Pattern pattern = Pattern.compile(regex);
        List<String> numbers = new ArrayList<>();

        //Load the file
        FileReader fileReader = new FileReader(inputFileName);

        // Use try catch
        BufferedReader reader = new BufferedReader(fileReader);

        String line;
        //read and store each line and loop through each line skipping hte empty lines
        while ((line = reader.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);

            //Store the number if it matches the regex
            while (matcher.find()) {
                numbers.add(matcher.group().replace(" ", ""));
            }
        }
        System.out.println("Car Numbers are: \n" + numbers);
        return numbers.toArray(new String[0]);
    }

    public static HashMap<String, String[]> retrieveExpectedOutput() throws IOException {
        //Load the file
        FileReader fileReader = new FileReader(expectedOutputFileName);
        BufferedReader reader = new BufferedReader(fileReader);

        boolean firstLine = true;
        String line;
        HashMap<String, String[]> carDetailsMap = new HashMap<>();

        while ((line = reader.readLine()) != null) {
            if (firstLine) {
                firstLine = false;
            }

            String[] details = line.split(",");
            String reg = details[0];
            String[] carDetails = new String[3];

            carDetails[0] = details[1];
            carDetails[1] = details[2];
            carDetails[2] = details[3];

            carDetailsMap.put(reg, carDetails);
        }
        return carDetailsMap;
    }
}
