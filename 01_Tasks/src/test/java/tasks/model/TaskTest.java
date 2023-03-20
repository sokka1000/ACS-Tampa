package tasks.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

class TaskTest {

    private Task task;

    @BeforeEach
    void setUp() {
        try {
            task=new Task("new task",task.getDateFormat().parse("2023-02-12 10:10"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testTaskCreation() throws ParseException {
       assert task.getDescription() == "new task";
        System.out.println(task.getFormattedDateStart());
        System.out.println(task.getDateFormat().format(task.getDateFormat().parse("2023-02-12 10:10")));
       assert task.getFormattedDateStart().equals(task.getDateFormat().format(task.getDateFormat().parse("2023-02-12 10:10")));

    }

    @AfterEach
    void tearDown() {
    }
}