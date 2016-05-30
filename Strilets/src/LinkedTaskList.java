import com.sun.xml.internal.bind.v2.runtime.reflect.ListIterator;

import java.util.ConcurrentModificationException;
import java.util.Date;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Valera on 26.03.2016.
 */
public class LinkedTaskList {
    private int size;
    private transient Entry header = new Entry(null, null, null);

    public LinkedTaskList() {
        header.next = header.previous = header;
    }

    private static class Entry {
        Task element;
        Entry next;
        Entry previous;
        Entry(){}
        Entry(Task element, Entry next, Entry previous) {
            this.element = element;
            this.next = next;
            this.previous = previous;
        }
    }

    private Entry addBefore(Task e, Entry entry) {
        Entry newEntry = new Entry(e, entry, entry.previous);
        newEntry.previous.next = newEntry;
        newEntry.next.previous = newEntry;
        size++;
        return newEntry;
    }

    public boolean add(Task e) {
        addBefore(e, header);
        return true;
    }

    public boolean remove(Object o) {
        if (o==null) {
            for (Entry e = header.next; e != header; e = e.next) {
                if (e.element==null) {
                    remove(e);
                    return true;
                }
            }
        } else {
            for (Entry e = header.next; e != header; e = e.next) {
                if (o.equals(e.element)) {
                    remove(e);
                    return true;
                }
            }
            size--;
        }
        return false;
    }

    public void size() {
        System.out.println(size);
    }

    public Task get(int index) {
        return entry(index).element;
    }
    private Entry entry(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Index: "+index+ ", Size: "+size);
        Entry e = header;
        if (index < (size >> 1)) {
            for (int i = 0; i <= index; i++)
                e = e.next;
        } else {
            for (int i = size; i > index; i--)
                e = e.previous;
        }
        return e;
    }
/////////////////////////////////////////////////////////////////

    public LinkedTaskList incoming(Date from, Date to){

        Entry newEntry = new Entry();
        LinkedTaskList link = new LinkedTaskList();


        if (newEntry.element.getStartTime().after(from) && newEntry.element.getEndTime().before(to))

            {
                link.add(newEntry.element);
            }
        return link;
    }
//////////////////////////////////////////////////////////////////
    private class ListItr implements ListIterator<Task> {
        private Entry lastReturned = header;
        private Entry next;
        private int nextIndex;
       // private int expectedModCount = modCount;

        ListItr(int index) {
            if (index < 0 || index > size)
                throw new IndexOutOfBoundsException("Index: "+index+
                        ", Size: "+size);
            if (index < (size >> 1)) {
                next = header.next;
                for (nextIndex=0; nextIndex<index; nextIndex++)
                    next = next.next;
            } else {
                next = header;
                for (nextIndex=size; nextIndex>index; nextIndex--)
                    next = next.previous;
            }
        }

        public boolean hasNext() {
            return nextIndex != size;
        }

        public Task next() {
           // checkForComodification();
            if (nextIndex == size)
                throw new NoSuchElementException();

            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.element;
        }

        public boolean hasPrevious() {
            return nextIndex != 0;
        }

        public Task previous() {
            if (nextIndex == 0)
                throw new NoSuchElementException();

            lastReturned = next = next.previous;
            nextIndex--;
           // checkForComodification();
            return lastReturned.element;
        }

        public void remove() {
           // checkForComodification();
            Entry lastNext = lastReturned.next;
            try {
               // LinkedList.this.remove(lastReturned);
            } catch (NoSuchElementException e) {
                throw new IllegalStateException();
            }
            if (next==lastReturned)
                next = lastNext;
            else
                nextIndex--;
            lastReturned = header;
            //expectedModCount++;
        }
        final void checkForComodification() {
            //if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
    }


    public Iterator<Task> descendingIterator() {
        return new DescendingIterator();
    }

    private class DescendingIterator implements Iterator {
        final ListItr itr = new ListItr(size);
        public boolean hasNext() {
            return itr.hasPrevious();
        }
        public Task next() {
            return itr.previous();
        }
        public void remove() {
            itr.remove();
        }
    }


}
