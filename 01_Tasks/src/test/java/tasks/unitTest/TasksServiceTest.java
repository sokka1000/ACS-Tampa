package tasks.unitTest;

import javafx.collections.FXCollections;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.model.TaskList;
import tasks.model.TasksOperations;
import tasks.services.TasksService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TasksServiceTest {

    @Mock
    ArrayTaskList arrayTaskList;

    @Mock
    TasksOperations tasksOperations;

    @Mock
    Task task1;
    @Mock
    Task task2;
    @Mock
    Task task3;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");



    @Test
    void getAllTasksTest() throws ParseException {

         String description = "alabala";
        Date dateStart = sdf.parse("2023-09-09 08:00");
        Date dateEnd = sdf.parse("2023-09-09 09:00");
        int interval = 20;
        List<Task> tasks = List.of(new Task(description, dateStart, dateEnd, interval));
        Mockito.when(arrayTaskList.getAll()).thenReturn(tasks);
        TasksService tasksService = new TasksService(arrayTaskList);
        assertEquals(tasksService.getObservableList(), FXCollections.observableArrayList(tasks));
    }

    @Test
    void filterTasksTest() throws ParseException {

        Date dateStartIncoming = sdf.parse("2023-09-09 08:00");
        Date dateEndIncoming = sdf.parse("2023-09-12 09:00");

        List<Task> tasks = List.of(task1,task2,task3);

        Mockito.when(arrayTaskList.getAll()).thenReturn(tasks);

        Iterable<Task> taskIterable = List.of(task1);

        Mockito.when(tasksOperations.incoming(dateStartIncoming,dateEndIncoming)).thenReturn(taskIterable);

        TasksService tasksService = new TasksService(arrayTaskList,tasksOperations);


        Iterable<Task> filteredTasks = tasksService.filterTasks(dateStartIncoming,dateEndIncoming);

        int count=0;
        for (Task task:filteredTasks)
            count++;


        assertEquals(count,1);


    }



}
