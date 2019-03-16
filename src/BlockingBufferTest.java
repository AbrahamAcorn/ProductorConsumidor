// Creating a synchronized buffer using an ArrayBlockingQueue.
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;


class BlockingBuffer implements Buffer{
	private final ArrayBlockingQueue<Integer> buffer; // shared buffer
	public BlockingBuffer(){
		buffer = new ArrayBlockingQueue<Integer>(1);
	}
// place value into buffer
	public void blockingPut(int value) throws InterruptedException{
		buffer.put(value); // place value in buffer
		System.out.printf("%s%2d\t%s%d%n", "Producer writes ", value,"Buffer cells occupied: ", buffer.size());
		
	}
// return value from buffer
	public int blockingGet() throws InterruptedException{
		int readValue = buffer.take();// remove value from buffer
		System.out.printf("%s %2d\t%s%d%n", "Consumer reads ",
				readValue, "Buffer cells occupied: ", buffer.size());
		return readValue;
	}
	@Override
	public void set(int value) throws InterruptedException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int get() throws InterruptedException {
		// TODO Auto-generated method stub
		return 0;
	}
} // end class BlockingBuffer

public class BlockingBufferTest{
	public static void main(String[] args) throws InterruptedException
	{
		// create new thread pool with two threads
		ExecutorService executorService = Executors.newCachedThreadPool();
		//create BlockingBuffer to store ints
		Buffer sharedLocation = new BlockingBuffer();
		executorService.execute(new Producer(sharedLocation));
		executorService.execute(new Consumer(sharedLocation));
		executorService.shutdown();
		executorService.awaitTermination(1, TimeUnit.MINUTES);
	}
} // end class BlockingBufferTest



