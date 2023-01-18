package main;

import commands.BanCommand;
import commands.ViewCommand;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.ChunkingFilter;
import net.dv8tion.jda.api.utils.MemberCachePolicy;
import net.dv8tion.jda.api.utils.cache.CacheFlag;
import willkommen.WillkommensNachricht;

import javax.security.auth.login.LoginException;
import java.util.EnumSet;

public class Hauptklasse {

    public static void main(String[] args) throws LoginException, InterruptedException {

        //Schreib irgendwas
        String prefix = "!";

        String status;
        status = "test!";

        int zahl = 12;
        double kommazahl = 1.45;

        String token = "OTY0MTUxMzE5ODg1NjUxOTk5.GgHF1J.he-33iq8v8KQnQr7ZwPNGsejSgPj4uiyI8GSLc";

        JDABuilder bauplan = JDABuilder.createDefault(token);

        bauplan.setStatus(OnlineStatus.ONLINE);
        bauplan.setActivity(Activity.playing(status));

        bauplan.setChunkingFilter(ChunkingFilter.ALL);
        bauplan.setMemberCachePolicy(MemberCachePolicy.ALL);
        bauplan.enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_VOICE_STATES, GatewayIntent.DIRECT_MESSAGE_TYPING, GatewayIntent.DIRECT_MESSAGES);

        EnumSet<CacheFlag> enumSet = EnumSet.of(CacheFlag.ONLINE_STATUS, CacheFlag.CLIENT_STATUS, CacheFlag.EMOJI, CacheFlag.VOICE_STATE);

        bauplan.enableCache(enumSet);

        bauplan.addEventListeners(new NachrichtenReaktion());
        bauplan.addEventListeners(new WillkommensNachricht());
        bauplan.addEventListeners(new ViewCommand());
        bauplan.addEventListeners(new Rollenverteilung());
        bauplan.addEventListeners(new BanCommand());

        bauplan.addEventListeners(new VerifySystem());
        JDA bot = bauplan.build();
        System.out.println("Der Bot ist nun online!");
        System.out.println("Der Prefix des Bots lautet: " + prefix);

        Guild server = bot.awaitReady().getGuildById("964153588270137414");

        server.updateCommands().addCommands(

                Commands.slash("ban", "Banne einen Nutzer!")
                        .addOption(OptionType.USER, "nutzer", "Nutzer, den du bannen möchtest!" ,true)
                        .addOption(OptionType.STRING, "grund", "Grund für die Bestrafung!", true)
                        .addOption(OptionType.INTEGER, "deldays", "Zu löschende Nachrichten des Nutzers!", true),

                Commands.context(Command.Type.USER, "Nutzer bannen")

                ).queue();

    }

}
