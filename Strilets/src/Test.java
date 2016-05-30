import javax.xml.crypto.Data;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by Valera on 16.02.2016.
 */
public class Test {

    public static void main(String args[]) throws ParseException, IOException {
        ///////////////////////////////////////////
        String s="05.09.2013";
        SimpleDateFormat format = new SimpleDateFormat();
        format.applyPattern("dd.MM.yyyy");
        Date docDate= format.parse(s);
        ///////////////////////////////////////
        Date data = new Date(113,5,20,12,30,0);
        Date data2 = new Date(110,10,20);
        Date data1 = new Date(116,10,20);
        Task tasks = new Task("true",data,data,4);
        tasks.getTitle();
        //System.out.println(tasks.getTitle());
        Task task1 = new Task("Пробежка",data,docDate,2);
        Task task2 = new Task("Переговоры",data,docDate,6);
        Task task3 = new Task("Встреча",data,docDate,6);
        Task task4 = new Task("Армия",data,docDate,3);
        ArrayTaskList aList = new ArrayTaskList();
        LinkedTaskList lList = new LinkedTaskList();
        tasks.setTime(data,data1,4);
        tasks.setActive(true);
        //////////////////////////
        aList.add(task1);
        aList.add(tasks);
        aList.add(task2);
        aList.add(task3);
        aList.add(task4);
        aList.add(task4);
        aList.add(task4);
        aList.add(task4);
        aList.add(task4);
        aList.add(task2);
        aList.add(task3);
        aList.add(task2);
        aList.add(task3);
        FileOutputStream fos = new FileOutputStream("test.txt");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(task1);
        /////////////////////////////
        aList.size();
        aList.show();
        aList.getTask(1);
        aList.sizes();
        ///////////////////////////////
        lList.add(task1);
        lList.add(task2);
        lList.add(task3);
        lList.size();
        lList.add(task1);
        lList.size();
        //lList.remove(task1);
        lList.size();
        lList.get(1);
       // lList.incoming(data2,data1);
       // aList.incoming(data2,data1);
       // lList.show();
        /*Iterator<Task> iterator = aList.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next().toString());
        }*/


        Iterator<Task> iter = lList.descendingIterator();

        while (iter.hasNext()) {
            System.out.println(iter.next().toString());
        }

       // lList.incoming(1,6);
        //aList.getTask(1);
        //System.out.println(aList.size());
        //aList.show();
        //aList.remove(task1);
       // System.out.println(aList.size());
       // aList.show();
       // aList.incoming(4,5);

    }
}
