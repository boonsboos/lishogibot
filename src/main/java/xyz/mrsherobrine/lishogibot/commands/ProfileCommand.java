package xyz.mrsherobrine.lishogibot.commands;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;

import java.io.IOException;
import java.net.URL;

public class ProfileCommand {

    public void getProfileFromUsername(MessageChannel channel, String username) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        JsonNode profile = mapper.readTree(new URL("https://lishogi.org/api/user/" + username));
        channel.sendMessage(profile.get("username").toString().replace("\"", "") + " has " + " puzzle games").queue();
        JsonNode perfs = profile.get("perfs");
        EmbedBuilder profileEmbed = new EmbedBuilder()
                .setTitle("4")
                .addField("Puzzle", perfs.get("puzzle").get("rating").toString(), false)
                .addField("Bullet", perfs.get("bullet").get("rating").toString(), false)
                .addField("Blitz", perfs.get("blitz").get("rating").toString(), false)
                .addField("Rapid", perfs.get("rapid").get("rating").toString(), false)
                .addField("Classical", perfs.get("classical").get("rating").toString(), false)
                .addField("Correspondence", perfs.get("correspondence").get("rating").toString(), false);

        if (profile.get("online").toString().equals("false")) {
            profileEmbed.addField("","Player offline.",false);
        } else {
            profileEmbed.addField("", "Player online", false);
        }
        channel.sendMessageEmbeds(profileEmbed.build()).queue();
    }

}