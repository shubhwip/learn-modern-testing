package me.shubhamjain.logging.slf4jsimple;

import me.shubhamjain.logging.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class StudentRankCalculatorTest {
    @InjectMocks
    StudentRankCalculator systemUnderTest;
    @Mock
    Logger logger;
    @BeforeEach
    void setUp() {
        systemUnderTest = new StudentRankCalculator();
    }
    @Test
    void givenStudents_whenRankCalculatorIsCalled_thenReturnsRanksOfStudent() {
        Student s1 = new Student(1, 89);
        Student s2 = new Student(2, 98);
        Student s3 = new Student(3, 99);

        systemUnderTest.rankCalculator(List.of(s1, s2, s3));

        Mockito.verify(logger).info("First rank {}", new Student(3, 99));
        Mockito.verify(logger).info("Second rank {}", new Student(2, 98));
        Mockito.verify(logger).info("Third rank {}", new Student(1, 89));
    }

    @Test
    void givenLessThan3Students_whenRankCalculatorIsCalled_thenReturnsError() {
        Student s1 = new Student(1, 89);
        Student s2 = new Student(2, 98);

        systemUnderTest.rankCalculator(List.of(s1, s2));

        Mockito.verify(logger).error("Please provide more than 3 students details and current students size is {}", 2);
    }

}
