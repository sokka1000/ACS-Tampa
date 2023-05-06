package tasks.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;

class TaskTest {

    private Task task;

    @BeforeEach
    void setUp() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            task=new Task("new task",sdf.parse("2024-02-12 10:10"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Test
    void testTaskCreation() throws ParseException {
       assert task.getDescription() == "new task";
        System.out.println(task.getFormattedDateStart());
        System.out.println(task.getDateFormat().format(task.getDateFormat().parse("2024-02-12 10:10")));
       assert task.getFormattedDateStart().equals(task.getDateFormat().format(task.getDateFormat().parse("2024-02-12 10:10")));

    }

    @AfterEach
    void tearDown() {
    }
}
