package tasks.model;

import javafx.util.converter.LocalDateStringConverter;
import org.apache.log4j.Logger;
import tasks.services.TaskIO;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

public class Task implements Serializable, Cloneable {
    private String description;
    private Date start;
    private Date end;
    private int interval;
    private boolean active;

    private static final Logger log = Logger.getLogger(Task.class.getName());
    private  final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public  SimpleDateFormat getDateFormat(){
        return sdf;
    }

    public Task(String description, Date time){
        if (time.getTime() < 0) {
            log.error("time below bound");
            throw new IllegalArgumentException("Time cannot be negative");
        }
        if (time.before(new Date()))
        {
            log.error("start date is in the past");
            throw new IllegalArgumentException("start date should be from current date on");
        }
        this.description = description;
        this.start = time;
        this.end = time;
    }
    public Task(String description, Date start, Date end, int interval){
        if (start.getTime() < 0 || end.getTime() < 0) {
            log.error("time below bound");
            throw new IllegalArgumentException("Time cannot be negative");
        }
        if (start.before(new Date()))
        {
            log.error("start date is in the past");
            throw new IllegalArgumentException("start date should be from current date on");
        }
        if (interval < 1) {
            log.error("interval < than 1");
            throw new IllegalArgumentException("interval should me > 1");
        }
        this.description = description;
        this.start = start;
        this.end = end;
        this.interval = interval;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public boolean isActive(){
        return this.active;
    }

    public void setActive(boolean active){
        this.active = active;
    }


    public Date getStartTime() {
        return start;
    }

    public Date getEndTime() {
        return end;
    }
    public int getRepeatInterval(){
        return interval > 0 ? interval : 0;
    }

    public boolean isRepeated(){
        return this.interval != 0;

    }

    private Date f2(Date current,Date timeAfter,Date timeBefore){
        for (long i = start.getTime(); i <= end.getTime(); i += interval*1000){

            if (current.equals(timeAfter))
                return new Date(timeAfter.getTime()+interval*1000);
            if (current.after(timeBefore) && current.before(timeAfter))
                return timeBefore;//return timeAfter


            timeBefore = timeAfter;
            timeAfter = new Date(timeAfter.getTime()+ interval*1000);
        }
        return null;
    }

    private Date f(Date current){
        if (isRepeated() && isActive()){
            Date timeBefore  = start;
            Date timeAfter = start;
            if (current.before(start)){
                return start;
            }
            if ( !current.before(end) && (!current.after(end)) && f2(current,timeAfter,timeBefore)!=null )
            {
                    return f2(current,timeAfter,timeBefore);

            }
        }
        return null;
    }

    public Date nextTimeAfter(Date current){
        if (!current.before(end))
            return null;

        if(f(current)!=null)
            return f(current);

        if (!isRepeated() && current.before(start) && isActive()){
            return start;
        }
        return null;
    }

    public Date getTime(){
        return start;
    }
    //duplicate methods for TableView which sets column
    // value by single method and doesn't allow passing parameters
    public String getFormattedDateStart(){
        return sdf.format(start);
    }
    public String getFormattedDateEnd(){
        return sdf.format(end);
    }
    public String getFormattedRepeated(){
        if (isRepeated()){
            String formattedInterval = TaskIO.getFormattedInterval(interval);
            return "Every " + formattedInterval;
        }
        else {
            return "No";
        }
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;
        if (!start.equals(task.start)) return false;
        if (!end.equals(task.end)) return false;
        if (interval != task.interval) return false;
        if (active != task.active) return false;
        return description.equals(task.description);
    }

    @Override
    public int hashCode() {
        int result = description.hashCode();
        result = 31 * result + start.hashCode();
        result = 31 * result + end.hashCode();
        result = 31 * result + interval;
        result = 31 * result + (active ? 1 : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Task{" +
                "description='" + description + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", interval=" + interval +
                ", active=" + active +
                '}';
    }


    @Override
    protected Task clone() throws CloneNotSupportedException {
        Task task  = (Task)super.clone();
        task.start = (Date)this.start.clone();
        task.end = (Date)this.end.clone();
        return task;
    }
}

