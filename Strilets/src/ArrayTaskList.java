import javax.xml.crypto.Data;
import java.util.*;

/**
 * Created by Valera on 10.02.2016.
 */
public class ArrayTaskList extends TaskList {
    private Task[] arrayList;
    private int size;

    // метод, що повертає задачу, яка знаходиться на вказаному місці у списку, перша задача має індекс 0.
    @Override
    public Task getTask(int index){
         if (index>=size||index<0){
             throw new IndexOutOfBoundsException("Неверный индекс"+index);
         }
             return arrayList[index];
    }
 public ArrayTaskList()
    {
        arrayList = new Task[10];//construct a zero length array.
        size=0;
    }
    //метод, що додає до списку вказану задачу
    @Override
   public void add(Task task){
        int oldCapacity = arrayList.length;
        if(size+1>oldCapacity) {
            int newCapacity = (oldCapacity * 3) / 2 + 1;
            if (newCapacity<size+1)
            newCapacity = size+1;
            arrayList = Arrays.copyOf(arrayList, newCapacity);
        }
        arrayList[size++]=task;
    }

   public void show(){
        System.out.println(arrayList[3].getTitle());
    }

    @Override
   public boolean remove(Task task){
        for (int i = 0; i < arrayList.length; i++)
            if (task.equals(arrayList[i])) {
                int numMoved = size - i - 1;
                System.arraycopy(arrayList, i+1, arrayList, i, numMoved);
                arrayList[--size]=null;
                return true;
            }
        return false;
    }
    //метод, що повертає кількість задач у списку
    @Override
    int size(){
        return arrayList.length;
    }
    void sizes(){
        System.out.println(arrayList.length);
    }

    //метод, що повертає підмножину задач, які заплановані на виконання хоча б раз після часу from і не пізніше ніж to
    /////////////////////////////////////////////////////////////////////////
   public ArrayTaskList incoming(Date from, Date to){

        ArrayTaskList ob = new ArrayTaskList();

        for(int i=0;i<arrayList.length;i++ ) {
            if (arrayList[i].getStartTime().after(from) && arrayList[i].getEndTime().before(to))
            {
                ob.add(arrayList[i]);
            }
        }

        return ob;
    }
    ////////////////////////////////////////////////////////////////

    void sort(){
        for(int i = arrayList.length ; i > 0 ; i--){
            for(int j = 0 ; j < i ; j++){
            /*Сравниваем элементы попарно,
              если они имеют неправильный порядок,
              то меняем местами*/
                if( arrayList[j].getStartTime().compareTo(arrayList[j+1].getStartTime()) > arrayList[j+1].getTime() ){
                    Task tmp = arrayList[j];
                    arrayList[j] = arrayList[j+1];
                    arrayList[j+1] = tmp;
                }
            }
        }
    }

    public Iterator<Task> iterator() {
        return new Itr();
             }

    private class Itr implements Iterator<Task> {
        int cursor;       // index of next element to return
        int lastRet = -1; // index of last element returned; -1 if no such

        public boolean hasNext() {
            return cursor != size;
        }

        @SuppressWarnings("unchecked")
        public Task next() {
            //checkForComodification();
            int i = cursor;
            if (i >= size)
                throw new NoSuchElementException();
            Task[] elementData = ArrayTaskList.this.arrayList;
            if (i >= elementData.length)
                throw new ConcurrentModificationException();
            cursor = i + 1;
            return (Task) elementData[lastRet = i];
        }

        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            checkForComodification();

            try {
               // ArrayTaskList.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
                //expectedModCount = modCount;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }

        final void checkForComodification() {
           // if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }


}
