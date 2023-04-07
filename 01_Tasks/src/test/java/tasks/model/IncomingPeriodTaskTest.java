package tasks.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class IncomingPeriodTaskTest {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void incoming_WBT_Valid() throws ParseException {
        ArrayTaskList tasks = new ArrayTaskList();
        tasks.add(new Task("alabala", sdf.parse("2023-09-09 08:00"), sdf.parse("2022-09-09 09:00"), 20));
        tasks.add(new Task("alabala", sdf.parse("2023-08-09 08:00"), sdf.parse("2022-08-09 09:00"), 20));
        tasks.add(new Task("alabala", sdf.parse("2023-08-15 08:00"), sdf.parse("2022-08-15 09:00"), 20));
        ObservableList<Task> observableList = FXCollections.observableArrayList(tasks.getAll());
        TasksOperations tasksOperations = new TasksOperations(observableList);
        Iterable<Task> tasks1 = tasksOperations.incoming(sdf.parse("2023-08-10 08:00"),sdf.parse("2023-10-01 08:00"));
        int count = 0;
        for (Task task : tasks1)
        {
            count++;
        }
        System.out.println(count);
        assert ( count == 2);
        assert (tasksOperations.getAllTasks().size() == 3);
    }


    @Test
    void incoming_WBT_Invalid(){

    }
}