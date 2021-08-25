package xyz.mrsherobrine.lishogibot;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageChannel;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.slf4j.Logger;
import xyz.mrsherobrine.lishogibot.commands.HelpCommand;

import java.util.Map;

public class CommandHandler extends ListenerAdapter {

    public Logger logger;
    public Map conf;

    public HelpCommand help;
    public CommandHandler(Logger logger, Map conf) {
        this.logger = logger;
        this.conf = conf;
        this.help = new HelpCommand();
    }

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        User author = event.getAuthor();
        Message messageData = event.getMessage();
        String message = messageData.getContentStripped().toLowerCase();
        MessageChannel channel = event.getChannel();

        String prefix = (String) conf.get("prefix");
        if (message.startsWith(prefix) && !author.isBot()) {
            String[] command = message.substring(prefix.length()).split(" ");
            switch(command[0]) {

                case "profile":
                    if (command.length != 2) {
                        channel.sendMessage("not implemented at the moment");
                    } else {

                    }
                    break;
                case "help":
                default:
                    help.helpCommand(channel, author);
                    break;
            }
        }

    }

}
