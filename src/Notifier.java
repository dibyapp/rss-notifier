import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Notifier {

	public static void main(String[] args) {
		try {
			String cmd = "";
			if (System.getProperty("os.name").startsWith("Windows")) {
				StringBuffer sb = new StringBuffer();
				cmd = "netsh wlan show interfaces";
				Process process = Runtime.getRuntime().exec(cmd);
				BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
				String line;
				while ((line = reader.readLine()) != null) {
					sb.append(line);
				}
				int per = Integer.parseInt(sb.substring(sb.indexOf("Signal")+25, sb.indexOf("Signal")+33).trim().replace("%", ""));
				int dBm = (per/2)-100;
				System.err.println(dBm);
			} else {
				cmd = "/System/Library/PrivateFrameworks/Apple80211.framework/Versions/Current/Resources/airport -I | grep CtlRSSI;";
				Process myProcess = Runtime.getRuntime().exec(cmd);
				myProcess.waitFor();
				if (myProcess.exitValue() == 0) {
					BufferedReader output = new BufferedReader(new InputStreamReader(myProcess.getInputStream()));
					System.err.println("ok");
					System.err.println(output.readLine());
				} else {
					System.err.println(" not ok");
				}
			}

		} catch (Exception e) {
			System.err.println("err-askdib-code broken");
		}
	}

}
