package com.gmail.kulacholeg.task_01;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XMLParser {
    private static final String FS = File.separator;
    private static final String PATH = "src" + FS + "main" + FS + "resources" + FS + "task_01" + FS;
    private final Pattern namePattern = Pattern.compile("\\s*[^\\S*]name\\s*=\\s*\"(\\S*)\"");
    private final Pattern surnamePattern = Pattern.compile("surname\\s*=\\s*\"(\\S*)\"\\u0020*");
    private final Pattern delimiterPattern = Pattern.compile("/\\S*>");

    public void parseFile(){
        try(BufferedReader reader = new BufferedReader(new FileReader(PATH + "data_input.xml"));
                BufferedWriter writer = new BufferedWriter(new FileWriter(PATH + "data_output.xml"))){
            String s = "";
            String temp = "";
            String name = "";
            String surname = "";
            //Read lines and add them to StringBuilder
            StringBuilder sb = new StringBuilder();
            while ((s=reader.readLine()) != null){
                sb.append(s);
                sb.append("\n");
                //Check if matcher founds delimiter. If "true" - create string
                Matcher delimiterMatcher = delimiterPattern.matcher(s);
                if(delimiterMatcher.find()){
                    temp = sb.toString();
                    sb.setLength(0);
                    //Find name and surname in created String
                    Matcher nameMatcher = namePattern.matcher(temp);
                    Matcher surnameMatcher = surnamePattern.matcher(temp);
                    if(nameMatcher.find() && surnameMatcher.find()){
                        name = nameMatcher.group(1);
                        surname = surnameMatcher.group(1);
                        //Create new 'name' value and delete 'surname' pattern
                        temp = temp.replace(nameMatcher.group(1), name + " " + surname);
                        temp = temp.replace(surnameMatcher.group(0), "");
                    }
                    //Write data to output file
                    writer.write(temp);
                    writer.flush();
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
