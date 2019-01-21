package queue_singlelinkedlist;

import java.util.*;

public class FifoQueue<E> extends AbstractQueue<E> implements Queue<E> {
	private QueueNode<E> last;
	private int size;

	public FifoQueue() {
		super();
		last = null; // inget sistaelement, storlek 0 -> tom kö
		size = 0;
	}

	/**
	 * Inserts the specified element into this queue, if possible post: The
	 * specified element is added to the rear of this queue
	 * 
	 * @param e the element to insert
	 * @return true if it was possible to add the element to this queue, else false
	 */
	public boolean offer(E e) {
		QueueNode node = new QueueNode<E>(e);
		if (size == 0) { // varför fungerar inte (last == null)?
			last = node; // läggs till sist i listan, gör dock ingen skillnad eftersom den är ensam i kön
			last.next = last; // här sätts även pekaren på sig själv enligt Figur 1
		} else {
			node.next = last.next; // pekaren sätts på last.next
			last.next = node; // pekaren sätts på node eftersom det är en cirkulär lista, och den ska peka på
								// det första elementet i kön

			last = node; // läggs till som sista-element
		}
		size++;
		return true;
	}

	/**
	 * Retrieves, but does not remove, the head of this queue, returning null if
	 * this queue is empty
	 * 
	 * @return the head element of this queue, or null if this queue is empty
	 */
	public E peek() {
		if (size == 0) { // kan även ha (last == null) eller (isEmpty()), vad är bäst?
			return null;
		} else {
			return last.next.element; // last.next hänvisar till den äldsta könoden
		}
	}

	/**
	 * Retrieves and removes the head of this queue, or null if this queue is empty.
	 * post: the head of the queue is removed if it was not empty
	 * 
	 * @return the head of this queue, or null if the queue is empty
	 */
	public E poll() {
		if (size == 0) {
			return null;
		}
		QueueNode<E> node = last.next; // den äldsta könoden
		last.next = node.next; // sätter att nästföljande nod blir först i kön
		size--;
		return node.element;
	} 
	
	public void append(FifoQueue<E> q) { // q läggs till bakom vår befintliga kö
		 
		if (this == q) { // funkar detta?
			throw new IllegalArgumentException();

		} else if (size == 0) { // om vår befintliga kö är tom sätts vår kös sistaelement till elementet
								// för den givna kön
			last = q.last;
			size = size + q.size; // storleken ökas till (q1 + q2) - element

		} else if (q.size != 0) {
			QueueNode<E> temp = last.next; // skapar en temporär nod
			last.next = q.last.next; // förstaelementet i vår kö sätts till förstaelementet för den givna kön
			q.last.next = temp; // förstaelementet för den givna kön sätts till temp som är last.next
			last = q.last; // sistaelementet sätts till sistaelementet för den givna kön
			size = size + q.size;

		}

		q.last = null; // den givna kön töms
		q.size = 0;

	}
	
	/**
	 * Returns the number of elements in this queue
	 * 
	 * @return the number of elements in this queue
	 */
	public int size() {
		return size;
	}
	
	public Iterator<E> iterator() {
		return new QueueIterator();
	}

	private static class QueueNode<E> {
		E element;
		QueueNode<E> next;

		private QueueNode(E x) {
			element = x;
			next = null;
		}
	}

	
	private class QueueIterator implements Iterator<E> {

		private QueueNode<E> pos; // håller koll positionen för den gällande könoden i iterationen
		private int n; // håller koll på hur många könoder vi passerar i iterationen

		private QueueIterator() {
			if (size == 0) { // om listan är tom sätts den gällande positionen till null
				pos = null;
			} else {
				pos = last.next; // annars till den första könoden
			}
			n = 0; // börjar på det nollte elementet
		}

		public boolean hasNext() {
			if (pos == null || n >= size) { // om det inte finns några fler könoder eller om vi itererat över listan
				return false;
			}

			return true;
		}

		public E next() {
			if (hasNext()) {
				QueueNode<E> temp = pos; // könoden sätts till pos, första iterationen -> första könoden
				pos = temp.next; // pos flyttas "bakåt" i kön, alltså den får värdet för könoden näst på tur
				n++;
				return temp.element;
			} else {
				throw new NoSuchElementException();
			}

		}

	}
}
