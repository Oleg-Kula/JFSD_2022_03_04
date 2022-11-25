package com.gmail.kulacholeg.task_02;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.gmail.kulacholeg.task_02.Entity.TrafficViolation;
import com.gmail.kulacholeg.task_02.Entity.TrafficViolationAmounts;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;


public class TrafficViolationsConverter {
    private static final String FS = File.separator;
    private static final String PATH = "src" + FS + "main" + FS + "resources" + FS + "task_02" + FS;
    private final File inputDirectory = new File(PATH + "data_input");
    private final File outputFile = new File(PATH + "data_output.xml");
    private Map<String, Double> result = new HashMap<>();

    public void createAmounts() {
        //Read files from folder
        File[] files = inputDirectory.listFiles((pathname) -> pathname.getName().endsWith(".json"));
        if (files == null) {
            System.out.println("No files");
            return;
        }
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<TrafficViolation> violations = new ArrayList<>();
            for (File file : files) {
                violations.addAll(mapper.readValue(file, new TypeReference<List<TrafficViolation>>() {
                }));
            }
            //Write needed data to Map
            for (TrafficViolation tv : violations) {
                result.merge(tv.getType(), tv.getFineAmount(), Double::sum);
            }
            //Sort map from max to min amounts
            result = result.entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, Double>comparingByValue().reversed())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldVal, newVal) -> oldVal, LinkedHashMap::new
                    ));
            //Write output xml-file
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_DECLARATION, true);
            xmlMapper.enable(SerializationFeature.INDENT_OUTPUT);
            xmlMapper.writeValue(outputFile, new TrafficViolationAmounts(result));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
