package myqueue;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

class MyQueue<T> implements Iterable<T>{

    private int size;

    private int head;

    private int teil;

    private int initialCapacity = 10;

    private int oldCapacity = initialCapacity;

    private int newCapacity;

    private Object[] myArrayList;

    /**
     *
     *  Konstruktor bez argumenata
     *
     * */

    public MyQueue() {

        this.head = -1;
        this.teil = -1;
        myArrayList = new Object[initialCapacity];

    }

    /**
     *
     *  Konstruktor sa zadatim kapacitetom reda
     *
     * */

    public MyQueue(final int Capacity) {

        this.head = -1;
        this.teil = -1;
        myArrayList = new Object[Capacity];

    }

    /**
     *
     *  Ciscenje reda
     *
     * */

    public void clear() {
        myArrayList = new Object[newCapacity];
        size = 0;
        head = -1;
        teil = -1;
    }

    /**
     *
     *  Provjeravanje da li je red prazan
     *
     * */

    public boolean isEmpty() {

        return head == -1;
    }

    /**
     *
     *  Vracanje velicine reda
     *
     * */

    public int size() {
        return size;
    }

    /**
     *
     *  Funkcija za dodavanje u red, ako je red pun moze baciti Exception
     *
     * */

    public boolean add(T element) throws Exception {

        if (teil == size) {
            throw new Exception("Red je pun!");
        }

        if(head == -1 && teil == -1) {
            head = 0;
            teil = 0;
            size++;
            myArrayList[teil] = element;
            return true;
        } else if(head == 0 && size > 0) {
            myArrayList[++teil] = element;
            size++;
            return true;
        }
        return false;
    }

    /**
     *
     *  Funkcija za dodavanje u red, ako je red pun dolazi do sirenja reda
     *
     * */

    public boolean offer(T element) {

        if (size >= (int) (myArrayList.length * 0.9)) {
            increaseCapacity();
        }

        if(head == -1 && teil == -1) {
            head = 0;
            teil = 0;
            size++;
            myArrayList[teil] = element;
            return true;
        } else if(head == 0 && size > 0) {
            myArrayList[++teil] = element;
            size++;
            return true;
        }
        return false;
    }

    /**
     *
     *  Funkcija za dohvatanje head elementa iz reda, baca Exception ako je red prazan
     *
     * */

    public T element() {

        if(isEmpty()) {
            throw new NoSuchElementException("Red je prazan! ");
        }
        return (T) myArrayList[head];
    }

    /**
     *
     *  Funkcija za dohvatanje head elementa iz reda, vraca null ako je red prazan
     *
     * */

    public T peek() {

        if(isEmpty()) {
            return null;
        }
        return (T) myArrayList[head];
    }

    /**
     *
     *  Funkcija za uklanjanje elementa iz reda, baca Exception ako je red prazan
     *
     * */

    public T remove() {

        if(isEmpty()) {
            throw new NoSuchElementException("Red je prazan! ");
        }

        T returnElement = (T) myArrayList[head];

        myArrayList = Arrays.copyOfRange(myArrayList,1, myArrayList.length);

        size--;
        teil--;

        if(size == 0) {
            head = -1;
        }

        return returnElement;
    }

    /**
     *
     *  Funkcija za uklanjanje elementa iz reda, vraca null ako je red prazan
     *
     * */

    public T poll() {

        if(isEmpty()) {
            return null;
        }

        T returnElement = (T) myArrayList[head];

        myArrayList = Arrays.copyOfRange(myArrayList,1, myArrayList.length);

        size--;
        teil--;

        if(size == 0) {
            head = -1;
        }

        return returnElement;
    }

    private void increaseCapacity() {

        newCapacity = (oldCapacity * 3) / 2 + 1;

        oldCapacity = newCapacity;

        myArrayList = Arrays.copyOf(myArrayList, newCapacity);

    }

    /**
     *
     *  Implementacija Iterable interfejsa za dobijanje iteratora za elemente reda
     *
     */

    @Override
    public Iterator<T> iterator() {

        return (Iterator<T>) Arrays.asList(Arrays.copyOf(myArrayList, size)).iterator();
    }


}
