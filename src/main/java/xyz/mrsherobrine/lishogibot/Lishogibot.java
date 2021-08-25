package xyz.mrsherobrine.lishogibot;

import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import xyz.mrsherobrine.lishogibot.util.ConfigGenerator;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Lishogibot {

    public static void main(String[] args) throws LoginException, InterruptedException, IOException {

        Logger logger = LoggerFactory.getLogger(Lishogibot.class);
        ObjectMapper objectMapper = new ObjectMapper();
        Map conf = new HashMap<>();
        try {
            new ConfigGenerator(logger).generateConfig();
            conf = objectMapper.readValue(new File("config.json"), Map.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        JDA jda = JDABuilder.createLight((String)conf.get("token"))
                .addEventListeners(new CommandHandler(logger, conf))
                .setMemberCachePolicy(MemberCachePolicy.ONLINE)
                .build()
                .awaitReady();

        logger.info("Welcome to lishogibot!");
    }
}
