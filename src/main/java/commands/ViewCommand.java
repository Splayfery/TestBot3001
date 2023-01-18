package commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

import java.util.concurrent.TimeUnit;

public class ViewCommand extends ListenerAdapter {

    public void onMessageReceived (MessageReceivedEvent ereignis) {

        if (ereignis.getMessage().getContentStripped().startsWith("!view")) {

            Member mitglied = ereignis.getMessage().getMentions().getMembers().get(0);

            EmbedBuilder embedBuilder = new EmbedBuilder();
            embedBuilder.setThumbnail(mitglied.getEffectiveAvatarUrl());
            embedBuilder.setTitle("Nutzerinfos zu " + mitglied.getEffectiveName());
            embedBuilder.setDescription("adhgiadhgadhfhgijfad jajfdu hjadujfhj uadh");
            embedBuilder.addField("ID des users", mitglied.getId(), true);

            Button kickButton = Button.secondary("kick" + mitglied.getId(), "Mitglied kicken");
            Button banButton = Button.danger("ban" + mitglied.getId(), "Mitglied bannen");
            Button profileButton = Button.link("https://youtube.com/splayfer", "Profilbild abrufen");

            ereignis.getChannel().sendMessageEmbeds(embedBuilder.build()).setActionRow(kickButton, banButton, profileButton).queue();


        }

    }

    public void onButtonInteraction (ButtonInteractionEvent ereignis) {

        if (ereignis.getButton().getId().startsWith("kick")) {

            if (ereignis.getMember().getPermissions().contains(Permission.KICK_MEMBERS)) {

                String nutzerID = ereignis.getButton().getId().substring(4);

                ereignis.getGuild().kick(User.fromId(nutzerID)).queue();

                ereignis.reply("Bestrafung erfolgreich!").queue();

            } else {

                ereignis.reply("Dir fehlen die Rechte hierzu!").queue();

            }

        } else if (ereignis.getButton().getId().startsWith("ban")) {

            if (ereignis.getMember().getPermissions().contains(Permission.BAN_MEMBERS)) {

                String nutzerID = ereignis.getButton().getId().substring(3);

                ereignis.getGuild().ban(User.fromId(nutzerID), 7, TimeUnit.DAYS).reason("Bann eines Admins").queue();

                ereignis.reply("Bestrafung erfolgreich!").queue();

            } else {

                ereignis.reply("Dir fehlen die Rechte hierzu!").queue();

            }

        }

    }

}
