package me.shubhamjain.logging.slf4jsimple;

import lombok.extern.slf4j.Slf4j;

// https://stackoverflow.com/questions/29076981/how-to-intercept-slf4j-with-logback-logging-via-a-junit-test
@Slf4j
public class StudentOperations {

    int totalMarks;

    public int addMarks(int marks) {
        totalMarks += marks;
        log.info("Total Marks {} ", totalMarks);
        return totalMarks;
    }
}
