package xyz.mrsherobrine.lishogibot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import xyz.mrsherobrine.lishogibot.commands.HelpCommand;
import xyz.mrsherobrine.lishogibot.commands.ProfileCommand;

import java.io.IOException;
import java.util.Map;

public class CommandHandler extends ListenerAdapter {

    public Logger logger;
    public Map conf;

    public ProfileCommand profile;
    public HelpCommand help;

    public CommandHandler(Logger logger, Map conf) {
        this.logger = logger;
        this.conf = conf;
        this.help = new HelpCommand();
        this.profile = new ProfileCommand();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        User author = event.getAuthor();
        Message messageData = event.getMessage();
        String message = messageData.getContentStripped().toLowerCase();
        MessageChannel channel = event.getChannel();

        String prefix = (String) conf.get("prefix");
        if (message.startsWith(prefix) && !author.isBot()) {
            //logger.info("Message: "+message);
            String[] command = message.substring(prefix.length()).split(" ");
            //logger.info("Command received: "+command[0]);
            switch(command[0]) {

                case "profile":
                    if (command.length != 2) {
                        //TODO: implement this
                        channel.sendMessage("not implemented at the moment").queue();
                    } else {
                        try {
                            profile.getProfileFromUsername(channel, command[1]);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                //if command's unknown, continue into help.
                default:
                    channel.sendMessage("Unknown command!").queue();
                    //no break here.
                case "help":
                    help.helpCommand(channel, author);
                    break;
            }
        }

    }

}
