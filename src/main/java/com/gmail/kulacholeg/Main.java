package com.gmail.kulacholeg;

import com.gmail.kulacholeg.task_01.XMLParser;
import com.gmail.kulacholeg.task_02.TrafficViolationsConverter;

public class Main {
    public static void main(String[] args) {
        //task_01
        XMLParser xmlParser = new XMLParser();
        xmlParser.parseFile();

        //task_02
        TrafficViolationsConverter trafficViolationsConverter = new TrafficViolationsConverter();
        trafficViolationsConverter.createAmounts();
    }
}
