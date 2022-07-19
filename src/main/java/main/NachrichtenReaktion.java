package main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class NachrichtenReaktion extends ListenerAdapter {

    public void onMessageReceived (MessageReceivedEvent ereignis) {

        if (ereignis.isFromGuild()) {

            if (ereignis.getMessage().getContentStripped().equals("hallo")) {

                String username = ereignis.getAuthor().getName();

                //Reaktion hinzufügen

                ereignis.getMessage().addReaction(Emoji.fromFormatted("✅")).queue();

                ereignis.getChannel().sendTyping().queue();
                ereignis.getChannel().sendMessage("Hallo " + username + "!").queue();

                Role rolle = ereignis.getGuild().getRoleById("964173165108547624");

                ereignis.getGuild().addRoleToMember(ereignis.getMember(), rolle).queue();

            } else if (ereignis.getMessage().getContentStripped().equals("!hilfe")) {

                EmbedBuilder bauplan = new EmbedBuilder();

                bauplan.setTitle("Ich bin ein Embed!", "https://youtube.com/splayfer");
                bauplan.setDescription("Hier ist die Beschreibung des Embeds.");
                bauplan.setColor(0x3c5ab5);

                bauplan.setThumbnail("https://cdn.discordapp.com/attachments/964153588827963415/972451980062638080/thumbnail.png");
                bauplan.setImage("https://cdn.discordapp.com/attachments/964153588827963415/972452187806523413/image.png");

                bauplan.setAuthor("TestBot3001", "https://youtube.com/splayfer", "https://cdn.discordapp.com/attachments/964153588827963415/972452510272987166/bot_PB.jpg");

                bauplan.addField("Überschrift", "Das ist ein Text", true);

                ereignis.getChannel().sendMessageEmbeds(bauplan.build()).queue();

            }

        }

    }

}
