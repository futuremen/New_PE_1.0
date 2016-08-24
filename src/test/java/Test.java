import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {

	public static void main(String[] args) {
		Date date = new Date();
		
		String x = new SimpleDateFormat("yyyyMMdd").format(date);
		System.out.println(x);
		
	
	}


}
