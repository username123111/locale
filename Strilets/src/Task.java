
import java.io.Serializable;
import java.util.Date;

/**
 * Created by Valera on 10.02.2016.
 */
public class Task implements Serializable{
    private boolean active;
    private int repeatInterval;
    private String title;
    private Date timeEnd;
    private int time;
    private int interval;
    private Date timeStart;
    private int timeReturn;
    private boolean repeated;
    private Date current;
    //конструює неактивну задачу, яка виконується у заданий час без повторення із заданою назвою
   public Task(String title, int time){
        this.title = title;
        this.time = time;
    }
// конструює неактивну задачу, яка виконується у заданому проміжку часу (і початок і кінець включно) із заданим інтервалом і має задану назву.
   public Task(String title, Date start, Date end, int interval){
        this.title = title;
        this.timeStart = start;
        this.timeEnd = end;
        this.interval = interval;
    }
//Метод для зчитування та встановлення назви задач
   public void setTitle(String title){

        this.title = title;
    }
    //Методи для зчитування та встановлення стану задач
  public void setActive(boolean active){

        this.active = active;
    }
    //Метод для зчитування та встановлення назви задач
   public String getTitle(){

        return title;
    }
// Методи для зчитування та встановлення стану задач
   public boolean isActive(){

        return active;
    }
    //у разі, якщо задача не повторювалася метод має стати такою, що повторюється
   public void setTime(Date start, Date end, int interval){
        this.timeStart = start;
        this.timeEnd =end;
        this.interval = interval;
    }
//у разі, якщо задача не повторюється метод має повертати 0;
   public int getRepeatInterval(){

        return repeatInterval;
    }
    // у разі, якщо задача не повторюється метод має повертати час виконання задачі;
    public Date getEndTime(){

        return timeEnd;
    }
    //у разі, якщо задача не повторюється метод має повертати час виконання задачі;
   public Date getStartTime(){

        return timeStart;
    }
    //у разі, якщо задача повторювалась, вона має стати такою, що не повторюється.
   public void setTime(int time){
        if (time<0){
            throw new IllegalArgumentException("timeArgument "+time);
        }
        this.time = time;

    }
    //у разі, якщо задача повторюється метод має повертати час початку повторення;
   public int getTime(){

        return timeReturn;
    }

    @Override
    public String toString(){
        return "Имя задачи: " + this.title+"  "+"Время начала: "+this.timeStart +"   " + "Время конца: " + this.getEndTime();
    }
    //Метод для перевірки повторюваності задач
   public boolean isRepeated(){

        return repeated;
    }
    //що повертає час наступного виконання задач
   public Date nextTimeAfter(Date current) {
        this.current = current;
        if (this.active == true) {
            if (interval != 0) {
                Date nextTime = timeStart;
                for (nextTime.equals(timeStart); nextTime.before(timeEnd); nextTime.setHours(interval)) {
                    if (nextTime.after(current)) {
                        return timeStart;
                    }
                    else{
                    return timeStart;
                }
                }
            } else {
                return null;

            }
        }
   return null;
    }

}


