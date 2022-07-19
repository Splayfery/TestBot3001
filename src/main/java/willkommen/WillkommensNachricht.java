package willkommen;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

public class WillkommensNachricht extends ListenerAdapter {

    public void onGuildMemberJoin (GuildMemberJoinEvent ereignis) {

        User nutzer = ereignis.getUser();

        EmbedBuilder bauplan = new EmbedBuilder();

        bauplan.setTitle("Willkommen auf dem TestServer3001!");
        bauplan.setDescription("Danke " + nutzer.getAsMention() + ", dass du dich dazu entschlossen hast, unserem Server beizutreten!");

        bauplan.setThumbnail("https://cdn.discordapp.com/attachments/964153588827963415/972454494329778196/willkommen.gif");

        nutzer.openPrivateChannel().complete().sendMessageEmbeds(bauplan.build()).queue();

    }

}
