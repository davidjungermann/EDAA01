package testqueue;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.Queue;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import queue_singlelinkedlist.FifoQueue;

public class TestAppendFifoQueue {

	FifoQueue<Integer> myIntQueue1;
	FifoQueue<Integer> myIntQueue2;

	@Before
	public void setUp() throws Exception {
		myIntQueue1 = new FifoQueue<Integer>();
		myIntQueue2 = new FifoQueue<Integer>();
	}

	@After
	public void tearDown() throws Exception {
		myIntQueue1 = null;
		myIntQueue2 = null;
	}

	@Test
	public final void testAppendTwoEmptyQueues() {
		myIntQueue1.append(myIntQueue2);
		assertTrue(myIntQueue1.size() == 0);
		assertTrue(myIntQueue2.size() == 0);

	}

	@Test
	public final void testAppendEmptyToNonEmpty() {
		myIntQueue1.offer(1);
		myIntQueue1.offer(2);
		myIntQueue1.offer(3);
		myIntQueue1.append(myIntQueue2);
		assertTrue(myIntQueue1.size() == 3);
		assertTrue(myIntQueue2.size() == 0);
		
		for(int i = 1; i <= 3; i++) {
			int k = myIntQueue1.poll(); 
			assertEquals("Incorrect order in joined queue", i, k);
		}
	}

	@Test
	public final void testAppendNonEmptyToEmpty() {
		myIntQueue2.offer(1);
		myIntQueue2.offer(2);
		myIntQueue2.offer(3);
		myIntQueue1.append(myIntQueue2);
		assertTrue(myIntQueue1.size() == 3);
		assertTrue(myIntQueue2.size() == 0);
		
		for(int i = 1; i <= 3; i++) {
			int k = myIntQueue1.poll(); 
			assertEquals("Incorrect order in joined queue", i, k);
		}
	}

	@Test
	public final void testAppendNonEmptyQueues() {
		myIntQueue1.offer(1);
		myIntQueue1.offer(2);
		myIntQueue1.offer(3);
		myIntQueue2.offer(4);
		myIntQueue2.offer(5);
		myIntQueue2.offer(6);
		myIntQueue1.append(myIntQueue2);
		assertTrue(myIntQueue1.size() == 6);
		assertTrue(myIntQueue2.size() == 0);
		
		for(int i = 1; i <= 6; i++) {
			int k = myIntQueue1.poll(); 
			assertEquals("Incorrect order in joined queue", i, k);
		}
	}

	@Test(expected = IllegalArgumentException.class)
	public final void testAppendSameQueue() {
		myIntQueue1.append(myIntQueue1);
		assertTrue(myIntQueue1.size() == 0);

	}

}
