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
	 * Metod za dodavanje lemenata na kraj niza
	 * 
	 */

	public void add(T element) {
		// System.out.println("Size je: " + size + " stv duzina: " +
		// ((int)(myElements.length * 0.8)));
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

		MyList<Integer> mojaLista = new MyList<>();

		System.out.println(mojaLista.size());

		mojaLista.add(14);
		mojaLista.add(10);
		mojaLista.add(54);
		mojaLista.add(24);
		// mojaLista.add(null);
		mojaLista.add(4);

		System.out.println(mojaLista.size());

		for (int i = 0; i < mojaLista.size(); i++) {
			System.out.print(mojaLista.get(i) + " ");
		}
		System.out.println();

		mojaLista.remove(2);
		System.out.println(mojaLista.size());

		for (Integer it : mojaLista) {
			System.out.print(it + " ");
		}
		System.out.println();

		System.out.println(mojaLista.toString());

		for (int i = 0; i < 15; i++) {
			mojaLista.add(i + 1);
		}

		for (Integer it : mojaLista) {
			System.out.print(it + " ");
		}
		System.out.println();

		mojaLista.add(8, 84);
		
		for (int i = 0; i < mojaLista.size(); i++) {
			System.out.print(mojaLista.get(i) + " ");
		}
	}

}
