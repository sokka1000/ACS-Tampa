package tasks.model;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class ArrayTaskListTest {

    private Task validTask;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }


    @RepeatedTest(value = 3, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    @DisplayName("Add a valid task for ECP")
    void addValidTaskECP() throws ParseException {
        // Arrange
        ArrayTaskList taskList = new ArrayTaskList();
        validTask = new Task("alabala",sdf.parse("2024-01-01 08:00"), sdf.parse("2024-01-01 09:00"), 50);

        // Act
        taskList.add(validTask);

        // Assert
        assertEquals(1, taskList.size());
        assertTrue(taskList.getAll().contains(validTask));
    }

    @Test
    @DisplayName("Add a task with an invalid interval ECP")
    @Timeout(value = 100,unit = TimeUnit.MILLISECONDS)
    void addTaskWithInvalidIntervalECP() {
        // Arrange
        ArrayTaskList taskList = new ArrayTaskList();

        // Act & Assert
        assertThrows(NumberFormatException.class, () -> taskList.add(new Task("alabala", sdf.parse("2024-01-01 08:00"), sdf.parse("2024-01-01 09:00"), Integer.parseInt("abc"))));
    }

    @Test
    @DisplayName("Add a task with an invalid start time ECP")
    @Tag("invalid")
    void addTaskWithInvalidStartTimeECP() {
        // Arrange
        ArrayTaskList taskList = new ArrayTaskList();
        //Task taskWithNegativeStartTime = new Task("Task with negative start time", new Date(-100000), new Date(200000), 100);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> taskList.add(new Task("alabala", sdf.parse("2022-01-01 08:00"), sdf.parse("2022-01-01 09:00"), 50)));
    }

    @Test
    @DisplayName("Add a task with an invalid interval and start date ECP")
    //@Disabled
    void addTaskWithInvalidStartTimeAndInvalidIntervalTimeECP() {
        // Arrange
        ArrayTaskList taskList = new ArrayTaskList();
        //Task taskWithNegativeEndTime = new Task("Task with negative end time", new Date(100000), new Date(-200000), 100);

        // Act & Assert
        assertThrows(NumberFormatException.class, () -> taskList.add(new Task("alabala", sdf.parse("2022-01-01 08:00"), sdf.parse("2022-01-01 09:00"), Integer.parseInt("abc"))));
    }


    @Test
    @DisplayName("Add a valid task for BVA")
    @Tag("valid")
    void addValidTaskBVA() {
        // Arrange
        ArrayTaskList taskList = new ArrayTaskList();
        var start = new Date();
        start.setDate(start.getDate()+1);
        var end = new Date();
        end.setDate(start.getDate()+1);
        Task taskWithStartTimeGreaterThanEndTime = new Task("alabala",start,end, 20);

        // Act
        taskList.add(taskWithStartTimeGreaterThanEndTime);

        // Assert
        assertEquals(1, taskList.size());
        assertTrue(taskList.getAll().contains(taskWithStartTimeGreaterThanEndTime));
    }

    @Test
    @DisplayName("Add a task with an invalid start date BVA")
    @Timeout(value = 100,unit = TimeUnit.MILLISECONDS)
    void addTaskWithInvalidStartTimeBVA(){
        // Arrange
        ArrayTaskList taskList = new ArrayTaskList();
        var date = new Date();
        date.setDate(date.getDate()+1);
        var date1 = new Date();
        date1.setDate(date.getDate()-1);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> taskList.add(new Task("alabala", date1, date, 20)));
    }


    @DisplayName("Add a valid task for BVA v2")
    @RepeatedTest(value = 3, name = "{displayName} {currentRepetition}/{totalRepetitions}")
    void addValidTaskBVA_2() throws ParseException {
        // Arrange
        ArrayTaskList taskList = new ArrayTaskList();
        var date = new Date();
        date.setDate(date.getDate()+1);
        Task taskWithStartTimeGreaterThanEndTime = new Task("alabala", sdf.parse("2024-01-01 08:00"), sdf.parse("2024-01-01 09:00"), 1);

        // Act
        taskList.add(taskWithStartTimeGreaterThanEndTime);

        // Assert
        assertEquals(1, taskList.size());
        assertTrue(taskList.getAll().contains(taskWithStartTimeGreaterThanEndTime));
    }


    @Test
    @DisplayName("Add a task with an invalid interval BVA")
    //@Disabled
    void addTaskWithInvalidIntervalTimeBVA() {
        // Arrange
        ArrayTaskList taskList = new ArrayTaskList();
        //Task taskWithNegativeEndTime = new Task("Task with negative end time", new Date(100000), new Date(-200000), 100);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> taskList.add(new Task("alabala", sdf.parse("2024-01-01 08:00"), sdf.parse("2024-01-01 09:00"), 0)));
    }
}