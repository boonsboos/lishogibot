package xyz.mrsherobrine.lishogibot.util;

import org.slf4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ConfigGenerator {

    public Logger logger;
    public ConfigGenerator(Logger logger) {
        this.logger = logger;
    }

    public void generateConfig() throws IOException, InterruptedException {
        String a = System.getProperty("user.dir") + System.getProperty("file.separator") + "config.json";
        logger.info(System.getProperty("user.dir")+", " +a);
        if (new File(a).createNewFile()) {
            logger.info("New config generated. Please configure it.");
            FileWriter w = new FileWriter(a);
            w.write("""
                {
                  "token": "",
                  "prefix": ""
                }
                """);
            w.close();
            Thread.sleep(1000);
            System.exit(0);
        }
    }
}