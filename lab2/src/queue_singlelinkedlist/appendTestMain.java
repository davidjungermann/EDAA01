package queue_singlelinkedlist;

public class appendTestMain {

	public static void main(String[] args) {
		FifoQueue<Integer> myIntQueue1 =  new FifoQueue<Integer>();
		FifoQueue<Integer> myIntQueue2 =  new FifoQueue<Integer>();
		myIntQueue1.offer(1);
		myIntQueue1.offer(2);
		myIntQueue1.offer(3); 
		myIntQueue2.offer(1); 
		myIntQueue2.offer(2);
		myIntQueue2.offer(3);
		
		myIntQueue1.append(myIntQueue2);
		System.out.println(myIntQueue1);
		System.out.println(myIntQueue2);
		
	}

}
