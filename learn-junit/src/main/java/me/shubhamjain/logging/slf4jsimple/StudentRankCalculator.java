package me.shubhamjain.logging.slf4jsimple;

import lombok.extern.slf4j.Slf4j;
import me.shubhamjain.logging.Student;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class StudentRankCalculator {
    public void rankCalculator(List<Student> students) {
        Objects.requireNonNull(students, "students should be non null");
        if(students.size() < 3) {
            log.error("Please provide more than 3 students details and current students size is {}", students.size());
            return;
        }
        List<Student> rankedStudents = students
                .stream()
                .sorted((o1, o2) -> o1.totalMarks() > o2.totalMarks() ? -1 : 1)
                .limit(students.size() > 3 ? 3 : students.size())
                .collect(Collectors.toList());
        log.info("First rank {}", rankedStudents.get(0));
        log.info("Second rank {}", rankedStudents.get(1));
        log.info("Third rank {}", rankedStudents.get(2));
    }
}
