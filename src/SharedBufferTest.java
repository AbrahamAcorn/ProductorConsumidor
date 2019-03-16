import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Fig. 23.12: UnsynchronizedBuffer.java
// UnsynchronizedBuffer maintains the shared integer that is accessed by
// a producer thread and a consumer thread.
class UnsynchronizedBuffer implements Buffer{
	private int buffer = -1; // shared by producer and consumer threads
//place value into buffer
	public void set(int value) throws InterruptedException{
		System.out.printf("Producer writes\t%2d", value);
		buffer = value;
 }

// return value from buffer
public int get() throws InterruptedException{
		System.out.printf("Consumer reads\t%2d", buffer);
		return buffer;
	}
} // end class UnsynchronizedBuffer


public class SharedBufferTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ExecutorService application = Executors.newCachedThreadPool();
        Buffer sharedLocation = new UnsynchronizedBuffer();
        System.out.println("Action\t\tValue\tSum of Produced\tSum of Consumed" );
        System.out.println("------\t\t-----\t---------------\t---------------\n" );
        application.execute( new Producer( sharedLocation ) );
        application.execute( new Consumer( sharedLocation ) );
        application.shutdown();
    }

}
