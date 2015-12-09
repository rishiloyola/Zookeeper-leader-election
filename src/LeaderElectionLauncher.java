import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.apache.log4j.Logger;

public class LeaderElectionLauncher {
		
	public static void main(String[] args) throws IOException {
		
		final int id = 1;
		final String zkURL = "localhost";
		
		final ExecutorService service = Executors.newSingleThreadExecutor();
		
		final Future<?> status = service.submit(new ProcessNode(id, zkURL));
		
		try {
			status.get();
		} catch (InterruptedException | ExecutionException e) {
			service.shutdown();
		}
	}
}
