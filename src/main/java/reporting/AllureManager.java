package reporting;

import java.io.IOException;
import java.util.Map;

public class AllureManager {

    public static void generateAndOpenReport() {
        //temporary solution
        //TODO refactor method to get path for user allure folder depends on platform,etc

        String userDir = System.getProperty("user.dir");
        ProcessBuilder builder = new ProcessBuilder(
                "cmd.exe", "/c", "allure serve " + userDir + "\\target\\allure-results");
        Map<String, String> envs = builder.environment();
        envs.put("PATH", "C:\\Users\\natallia.zayko\\scoop\\apps\\allure\\current\\bin");
        builder.redirectErrorStream(true);
        Process p = null;
        try {
            p = builder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
