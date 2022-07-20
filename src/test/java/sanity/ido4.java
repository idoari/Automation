package sanity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ido4 {
    public static void main(String[] args) throws IOException {
        killAppium(8206);
    }

    public static void killAppium(int port) throws IOException {
        String cmd;
        String line;

        if (System.getProperty("os.name").contains("Mac")) { //if it's MAC
            //cmd = "netstat -vanp tcp | grep " + port; //get the running apps path and PID
            cmd="ps -ax";
            List<String> str = new ArrayList<>();

            Process process = Runtime.getRuntime().exec(cmd);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            //extract pid
            while ((line = reader.readLine()) != null) {
                if (line.contains("port "+ port) ) {
                    str.add(line.split(" ")[0]); //get the relevant PID
                }
            }
            //kill processes
            for (String proc : str) {
                System.out.println("Killing Appium...");
                Runtime.getRuntime().exec("kill -9 " + proc.trim()); //kill the appium processes by PID
            }
            reader.close();


        } else if (System.getProperty("os.name").contains("Windows")) {
            cmd ="taskkill /F /IM node.exe";
            Runtime.getRuntime().exec(cmd);
        }
    }
}
