package tasks.integration;

import org.junit.jupiter.api.Test;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.model.TasksOperations;
import tasks.services.TasksService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTaskTest {

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @Test
    void getAllTasksTest() throws ParseException {
        String description = "alabala";
        Date dateStart = sdf.parse("2023-09-10 08:00");
        Date dateEnd = sdf.parse("2023-09-11 09:00");
        Date dateStart2 = sdf.parse("2024-09-09 08:00");
        Date dateEnd2 = sdf.parse("2024-09-09 09:00");
        Date dateStart3 = sdf.parse("2023-09-14 08:00");
        Date dateEnd3 = sdf.parse("2023-09-14 09:00");
        int interval = 20;

        Task task1 = new Task(description, dateStart, dateEnd, interval);
        Task task2 = new Task(description, dateStart2, dateEnd2, interval);
        Task task3 = new Task(description, dateStart3, dateEnd3, interval);

        ArrayTaskList arrayTaskList = new ArrayTaskList();
        arrayTaskList.add(task1);
        arrayTaskList.add(task2);
        arrayTaskList.add(task3);
        TasksService tasksService = new TasksService(arrayTaskList);
        assertEquals(tasksService.getObservableList().size(), 3);
    }

    @Test
    void filterTasksTest() throws ParseException {
        String description = "alabala";
        Date dateStart = sdf.parse("2023-09-10 08:00");
        Date dateEnd = sdf.parse("2023-09-11 09:00");
        Date dateStart2 = sdf.parse("2024-09-09 08:00");
        Date dateEnd2 = sdf.parse("2024-09-09 09:00");
        Date dateStart3 = sdf.parse("2023-09-14 08:00");
        Date dateEnd3 = sdf.parse("2023-09-14 09:00");
        Date dateStartIncoming = sdf.parse("2023-09-09 08:00");
        Date dateEndIncoming = sdf.parse("2023-09-12 09:00");
        int interval = 20;
        Task task1 = new Task(description, dateStart, dateEnd, interval);
        Task task2 = new Task(description, dateStart2, dateEnd2, interval);
        Task task3 = new Task(description, dateStart3, dateEnd3, interval);
        task1.setActive(true);
        task2.setActive(true);
        task3.setActive(true);

        ArrayTaskList arrayTaskList = new ArrayTaskList();
        arrayTaskList.add(task1);
        arrayTaskList.add(task2);
        arrayTaskList.add(task3);
        TasksService tasksService = new TasksService(arrayTaskList);
        TasksOperations tasksOperations = new TasksOperations(tasksService.getObservableList());
        tasksService.setTasksOperations(tasksOperations);
        assertEquals(tasksOperations.getAllTasks().size(),3);


        Iterable<Task> taskIterable = tasksService.filterTasks(dateStartIncoming, dateEndIncoming);

        int count = 0;
        for (Task task : taskIterable) {
            count++;
                assertEquals(task,task1);
        }

        assertEquals(count, 1);

    }


}
