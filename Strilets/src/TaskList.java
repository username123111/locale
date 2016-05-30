import java.io.Serializable;

/**
 * Created by Valera on 26.03.2016.
 */
public abstract class TaskList implements Serializable{


    // метод, що повертає задачу, яка знаходиться на вказаному місці у списку, перша задача має індекс 0.
    public abstract Task getTask(int index);

    //метод, що додає до списку вказану задачу
    public abstract void add(Task task);

    public abstract boolean remove(Task task);

    //метод, що повертає кількість задач у списку
    abstract int size();
}
