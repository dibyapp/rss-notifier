import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Notifier {

	public static void main(String[] args) {
		try{
            String cmd = "";
            if(System.getProperty("os.name").startsWith("Windows")) {   
                    cmd = "ping -n 1 ";
            } else {
                    cmd = "/System/Library/PrivateFrameworks/Apple80211.framework/Versions/Current/Resources/airport -I | grep CtlRSSI;";
            }

            Process myProcess = Runtime.getRuntime().exec(cmd);
            myProcess.waitFor();

            if(myProcess.exitValue() == 0) {
            	BufferedReader output = new BufferedReader(new InputStreamReader(myProcess.getInputStream()));
            	System.err.println(output.readLine());
            } else {
            	System.err.println(" not ok");
            }

    } catch( Exception e ) {
            e.printStackTrace();
            System.err.println(" not ok");
    }
	}
	
}
