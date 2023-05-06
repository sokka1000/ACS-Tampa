package tasks.unitTest;

import org.junit.jupiter.api.Test;
import tasks.model.Task;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TaskTest {


    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    @Test
    void constructorTest() throws ParseException {
        String description = "alabala";
        Date dateStart = sdf.parse("2023-09-09 08:00");
        Date dateEnd = sdf.parse("2023-09-09 09:00");
        int interval = 20;

        Task task = new Task(description,dateStart,dateEnd,interval);

        assertEquals (task.getDescription(),description);
        assertEquals (task.getFormattedDateStart(),sdf.format(dateStart));
        assertEquals (task.getFormattedDateEnd(),sdf.format(dateEnd));
        assertEquals (task.getRepeatInterval(),interval);


        String description1 = "newDescription";
        Date time1 = sdf.parse("2022-09-09 08:00");
        Date time2 = sdf.parse("2023-09-09 08:00");
        Date time3 = sdf.parse("1969-09-09 08:00");


        assertThrows(IllegalArgumentException.class, () -> new Task(description1,time1));
        assertThrows(IllegalArgumentException.class, () -> new Task(description1,time3));
        Task task1 =  new Task(description1,time2);

        assertEquals (task1.getDescription(),description1);
        assertEquals (task1.getFormattedDateStart(),sdf.format(time2));
        assertEquals (task1.getFormattedDateEnd(),sdf.format(time2));
        assertEquals (task1.getRepeatInterval(),0);
    }


    @Test
    void nextTimeAfterTest() throws ParseException {

        String description = "alabala";
        Date dateStart = sdf.parse("2023-09-09 08:00");
        Date dateEnd = sdf.parse("2023-09-09 10:00");
        int interval = 25;

        Task task = new Task(description,dateStart,dateEnd,interval);

        task.setActive(true);

        Date currentDate =  sdf.parse("2023-09-09 07:30");
        assertEquals(task.nextTimeAfter(currentDate) , dateStart);
        Date currentDate2 =  sdf.parse("2023-09-09 11:30");
        assertEquals(task.nextTimeAfter(currentDate2) , null);
        Date currentDate3 =  sdf.parse("2023-09-09 08:30");
        assertEquals(task.nextTimeAfter(currentDate3).getTime(), currentDate3.getTime() + 25*1000);

    }



}
