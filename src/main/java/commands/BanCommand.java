package commands;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.UserContextInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.concurrent.TimeUnit;

public class BanCommand extends ListenerAdapter {

    public void onSlashCommandInteraction (SlashCommandInteractionEvent event) {
        if (event.getName().equals("ban")) {
            Member member = event.getOption("nutzer").getAsMember();
            String grund = event.getOption("grund").getAsString();
            int delDays = event.getOption("deldays").getAsInt();
            event.reply(banUser(member, grund, delDays)).setEphemeral(true).queue();
        }
    }

    public void onUserContextInteraction (UserContextInteractionEvent event) {
        if (event.getName().equals("Nutzer bannen")) {
            Member member = event.getTargetMember();
            String grund = "Kein Grund angegeben";
            int delDays = 0;
            event.reply(banUser(member, grund, delDays)).setEphemeral(true).queue();
        }
    }

    public String banUser(Member member, String grund, int delDays) {
        String text;
        if (!member.hasPermission(Permission.ADMINISTRATOR)) {
            member.ban(delDays, TimeUnit.DAYS).reason(grund).queue();
            text = "Du hast " + member.getEffectiveName() + " erfolgreich gebannt!";
        } else {
            text = "Du kannst keinen Admin bannen!";
        }
        return text;
    }

}