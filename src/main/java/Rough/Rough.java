package Rough;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Rough {

	public static void main(String[] args) throws InterruptedException, ExecutionException, TimeoutException {
		
		// ExecutorService is a complete solution for asynchronous processing.
		// It manages an in-memory queue and schedules submitted tasks based on thread availability.
		Executor servic = Executors.newFixedThreadPool(2);
		
		// Execute
		servic.execute(()->System.out.println("Krishna:: "+Thread.currentThread().getName()));
		servic.execute(()->System.out.println("Kumar :: "+Thread.currentThread().getName()));
		System.out.println("Singh :: "+Thread.currentThread().getName());
		
		
		// Submit - Asynchronous
		ExecutorService service = Executors.newFixedThreadPool(1); // Thread Pool Size 1
		
		Runnable r = () -> System.out.println("I Am Runnable :: "+Thread.currentThread().getName());
		service.submit(r);
		
		Callable<String> c = () -> { return "Krishna"; };
		Future<?> future = service.submit(c); // Future is used to represent the result of an asynchronous operation.
		System.out.println("Is Future Done! "+future.isDone());
		System.out.println("Future Value Is :: "+future.get(10, TimeUnit.SECONDS)); // If the task takes more than this time, a TimeoutException is thrown:
		
		// List Of Thread.
		List<Callable<String>> callables = Arrays.asList(
		        () -> "task1",
		        () -> "task2",
		        () -> "task3");

		service.invokeAll(callables)
		    .stream()
		    .map(futur -> {
		        try {
		            return futur.get();
		        }
		        catch (Exception e) {
		            throw new IllegalStateException(e);
		        }
		    }).forEach(System.out::println);
		
		service.shutdown();
		
		// Scheduled Executor
	    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
	 
	    Future<String> fut = executorService.schedule(() -> {
	        return "Hello world";
	    }, 10, TimeUnit.SECONDS); // Enables After 10 Seconds
	 
	    ScheduledFuture<?> scheduledFuture = executorService.schedule(() -> {
	    	 return "Hello world Again";
	    }, 10, TimeUnit.SECONDS); // Enables After 10 Seconds
	    
	    System.out.println("Running scheduleFuture ::: "+fut.get()+" ::: "+scheduledFuture.get());
	    
	    // Periodic : Runnable Will Run Till The Service Shutdown. With Delay 10 Seconds. Initial Delay 1.
	    executorService.scheduleWithFixedDelay(() -> { // Runs At The Interval Of 10 Seconds Until ScheduledExecuterService Closes.
	        System.out.println("Krishnaaaaaaaaaaaaa");
	    }, 1, 10, TimeUnit.SECONDS); 
	   
	    executorService.scheduleWithFixedDelay(() -> { // Runs repeatedly with the given delay between the termination of the executing one and the invocation of the next one.
	    	System.out.println("Krishnaaaaaaaaaaaaa Again");
	    }, 1, 10, TimeUnit.SECONDS); 
	    
	    Thread.sleep(50000);
	    executorService.shutdown();
		
	    // Read About Locks	
	    
	}

}
