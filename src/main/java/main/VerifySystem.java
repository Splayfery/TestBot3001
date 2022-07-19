package main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.buttons.Button;

public class VerifySystem extends ListenerAdapter {

    public void onMessageReceived (MessageReceivedEvent ereignis) {

        if (ereignis.getMessage().getContentStripped().equals("!setup verify")) {

            if (ereignis.getMember().getPermissions().contains(Permission.ADMINISTRATOR)) {

                EmbedBuilder embed = new EmbedBuilder();
                embed.setTitle("Verifiziere dich hier!");
                embed.setThumbnail("https://cdn.discordapp.com/attachments/969194228062232606/978712139181195314/751206846290984972.png");
                embed.setColor(0x42b580);
                embed.setDescription("Verhalte dich immer höflich und respektvoll gegenüber den anderen Nutzern!");
                embed.addField(":question: Wie kann ich mich verifizieren?", "Klicke auf den Button unter dieser Nachricht!", true);

                Button button = Button.success("verify", "Verifiziere dich hier!").withEmoji(Emoji.fromFormatted("✅"));

                ereignis.getChannel().sendMessageEmbeds(embed.build()).setActionRow(button).queue();

            }

        }

    }

    public void onButtonInteraction (ButtonInteractionEvent ereignis) {

        if (ereignis.getButton().getId().equals("verify")) {

            Role verifyRole = ereignis.getGuild().getRoleById("978710631576391681");

            ereignis.getGuild().addRoleToMember(ereignis.getMember(), verifyRole).queue();

            ereignis.reply("Du hast dich erfolgreich verifiziert!").setEphemeral(true).queue();

        }

    }

}
