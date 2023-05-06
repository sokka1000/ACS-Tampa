package tasks.unitTest;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import tasks.model.ArrayTaskList;
import tasks.model.Task;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;


class ArrayTaskListTest {

    @Mock
    Task taskMock;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void addTest()  {
        ArrayTaskList arrayTaskList = new ArrayTaskList();

        assertEquals(arrayTaskList.size(),0);
        arrayTaskList.add(taskMock);
        assertEquals(arrayTaskList.size(),1);

        Mockito.when(taskMock.getDescription()).thenReturn("n-am descriere");
        assertEquals(taskMock.getDescription(),"n-am descriere");
    }

    @Test
    void removeTest() {

        ArrayTaskList arrayTaskList = new ArrayTaskList();

        assertEquals(arrayTaskList.size(),0);
        arrayTaskList.add(taskMock);
        assertEquals(arrayTaskList.size(),1);
        arrayTaskList.remove(taskMock);
        assertEquals(arrayTaskList.size(),0);
    }


}
