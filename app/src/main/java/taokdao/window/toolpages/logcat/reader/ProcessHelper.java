package taokdao.window.toolpages.logcat.reader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ProcessHelper {
    public static String getPackageName(String pid) {
        String pkgName = "";
        File f = new File("/proc/" + pid + "/cmdline");
        if (!pid.equals("0") && f.exists()) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("/proc/" + pid + "/cmdline"), 256);
                String line;
                if ((line = br.readLine()) != null) {
                    pkgName = line.trim();
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
        return pkgName;
    }
}
