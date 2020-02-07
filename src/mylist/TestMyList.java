package mylist;

import java.util.Arrays;
import java.util.Iterator;

class MyList<T> implements Iterable<T> {

	/**
	 * 
	 * Genericka klasa MyList
	 * 
	 * @author Daniel M
	 * 
	 */

	/** Inicijalna kolicina elemenata, inicijalni kapacitet */

	private int size = 0;

	private int initialCapacity = 10;

	private int oldCapacity = initialCapacity;

	private int newCapacity;

	/**
	 * 
	 * Niz elemenata kolekcije
	 * 
	 */

	private Object[] myElements;

	/**
	 * 
	 * Bazni konstruktor koji inicijalizira niz elemanata sa inicijalnim kapacitetom
	 * 
	 */

	public MyList() {

		myElements = new Object[initialCapacity];

	}

	/**
	 * 
	 * Konstruktor za inicijalizaciju Liste sa elementima niza
	 * 
	 */

	public MyList(T[] array) {

		myElements = array;		
		size = array.length;
	}

	/**
	 * 
	 * Metod za dodavanje lemenata na kraj niza
	 * 
	 */

	public void add(T element) {

		if (size >= (int) (myElements.length * 0.8)) {
			increaseCapacity();
		}

		myElements[size++] = element;

	}

	public void add(int index, T element) {

		if (index < 0 || index > myElements.length) {
			IndexOutOfBoundsException indOutOfBoEx = new IndexOutOfBoundsException(
					"Izasli ste iz okvira kolekcije! -> size : " + size + " -> index " + index + " (?)");
			throw indOutOfBoEx;
		} else if (size >= (int) (myElements.length * 0.8)) {
			increaseCapacity();
		}

		Object[] tempArray_to = Arrays.copyOfRange(myElements, 0, index);
		Object[] tempArray_from = Arrays.copyOfRange(myElements, index, size);

		myElements = Arrays.copyOf(tempArray_to, size + 1);
		myElements[index] = element;

		System.arraycopy(tempArray_from, 0, myElements, index + 1, tempArray_from.length);
		size++;

	}

	/**
	 * 
	 * Metod za dobijanje elementa na poziciji indeks parametra
	 * 
	 * @param index
	 * @return E
	 * 
	 */

	public T get(int index) {

		if (index < 0 || index > myElements.length) {
			IndexOutOfBoundsException indOutOfBoEx = new IndexOutOfBoundsException(
					"Izasli ste iz okvira kolekcije! -> size : " + size + " -> index " + index + " (?)");
			throw indOutOfBoEx;
		}

		return (T) myElements[index];
	}

	/**
	 * 
	 * Metod za uklanjanje elementa sa pozicije indeks parametra
	 * 
	 * @param index
	 * 
	 */

	public void remove(int index) {

		if (index < 0) {

			IndexOutOfBoundsException indOutOfBoEx = new IndexOutOfBoundsException(
					"Izasli ste iz okvira kolekcije! -> size : " + size + " -> index " + index + " (?)");
			throw indOutOfBoEx;

		}

		Object[] tempArray_to = Arrays.copyOfRange(myElements, 0, index);
		Object[] tempArray_from = Arrays.copyOfRange(myElements, index + 1, size);

		myElements = Arrays.copyOf(tempArray_to, size);

		System.arraycopy(tempArray_from, 0, myElements, index, tempArray_from.length);
		size--;

	}

	/**
	 * 
	 * Metod za vracanje velicine niza
	 * 
	 */

	public int size() {

		return size;

	}

	public boolean isEmpty() {

		return size == 0 ? true : false;

	}

	private void increaseCapacity() {

		newCapacity = (oldCapacity * 3) / 2 + 1;

		oldCapacity = newCapacity;

		myElements = Arrays.copyOf(myElements, newCapacity);

	}

	/**
	 * 
	 * Implementacija Iterable metode za dobijanje iteratora za elemente niza
	 * 
	 */

	@Override
	public Iterator<T> iterator() {

		return (Iterator<T>) Arrays.asList(Arrays.copyOf(myElements, size)).iterator();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(myElements);
		result = prime * result + size;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MyList other = (MyList) obj;
		if (!Arrays.deepEquals(myElements, other.myElements))
			return false;
		if (size != other.size)
			return false;
		return true;
	}

	@Override
	public String toString() {

		String toStringOutput = Arrays.toString(Arrays.copyOf(myElements, size));
		toStringOutput = toStringOutput.replaceAll("\\[", "< ");
		toStringOutput = toStringOutput.replaceAll("\\]", " >");

		return toStringOutput;
	}

}

public class TestMyList {

	public static void main(String[] args) {

		MyList<Integer> myList = new MyList<>();

		System.out.println("Velicina kolekcije: " + myList.size());

		myList.add(10);
		myList.add(54);
		myList.add(24);
		myList.add(null);
		myList.add(4);

		System.out.println("Velicina kolekcije: " + myList.size());

		for (int i = 0; i < myList.size(); i++) {
			System.out.print(myList.get(i) + " ");
		}
		System.out.println();

		myList.remove(2);
		System.out.println("Velicina kolekcije: " + myList.size());

		for (Integer it : myList) {
			System.out.print(it + " ");
		}
		System.out.println();

		System.out.println(myList.toString());

		for (int i = 0; i < 15; i++) {
			myList.add(i + 1);
		}

		for (Integer it : myList) {
			System.out.print(it + " ");
		}
		System.out.println();

		myList.add(8, 84);

		for (Integer it : myList) {
			System.out.print(it + " ");
		}
		System.out.println();
		
		// Inicijalizacija pomocu niza
		
		MyList<String> myList2 = new MyList<String>(new String[] {"prvo","drugo","trece", "cetvrto"});
		
		System.out.println("Velicina kolekcije: " + myList2.size());
		
		for (String str : myList2) {
			System.out.print(str + " ");
		}
		System.out.println();
		
		for (int i = 5; i < 15; i++) {
			myList2.add(i + 1 + "");
		}
		
		for (String str : myList2) {
			System.out.print(str + " ");
		}
		System.out.println();
		
		System.out.println("Velicina kolekcije: " + myList2.size());

		System.out.println(myList2.toString());

		
	}

}
