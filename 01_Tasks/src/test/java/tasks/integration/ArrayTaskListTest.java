package tasks.integration;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;
import tasks.services.TasksService;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTaskListTest {

    @Mock
    Task task1;
    @Mock
    Task task2;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addTest(){
        ArrayTaskList arrayTaskList=new ArrayTaskList();
        arrayTaskList.add(task1);
        arrayTaskList.add(task2);
        TasksService tasksService = new TasksService(arrayTaskList);

        assertEquals(tasksService.getObservableList().size(),2);
    }
    @Test
    void removeTest(){
        ArrayTaskList arrayTaskList=new ArrayTaskList();
        arrayTaskList.add(task1);
        arrayTaskList.add(task2);
        TasksService tasksService = new TasksService(arrayTaskList);
        assertEquals(tasksService.getObservableList().size(),2);
        arrayTaskList.remove(task1);
        assertEquals(tasksService.getObservableList().size(),1);
        assertEquals(tasksService.getObservableList().get(0),task2);
        arrayTaskList.remove(task2);
        assertEquals(tasksService.getObservableList().size(),0);
    }



}
