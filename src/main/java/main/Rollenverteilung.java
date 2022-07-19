package main;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.SelectMenuInteractionEvent;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.selections.SelectMenu;

public class Rollenverteilung extends ListenerAdapter {

    public void onMessageReceived (MessageReceivedEvent ereignis) {

        if (ereignis.getMessage().getContentStripped().equals("!setup roles")) {

            EmbedBuilder bauplan = new EmbedBuilder();
            bauplan.setColor(0xa8d5fe);
            bauplan.setTitle("**:restroom: │ Wähle dein Geschlecht!**");
            bauplan.setDescription("> Klicke auf das Menü unter dieser Nachricht, um deine Rollen auszuwählen!");
            bauplan.setThumbnail("https://cdn.discordapp.com/attachments/994974047626612756/994974053272137788/unknown.png");
            bauplan.setImage("https://cdn.discordapp.com/attachments/994974047626612756/994982925227532318/dafhadhfubgadgfadhghaghadhghadughfuzad.png");

            SelectMenu menü = SelectMenu.create("geschlecht")
                    .setPlaceholder("Wähle dein Geschlecht!")
                    .addOption("Männlich", "männlich", "Klicke, um dieses Geschlecht auszuwählen!", Emoji.fromFormatted("♂"))
                    .addOption("Weiblich", "weiblich", "Klicke, um dies auszuwählen!", Emoji.fromFormatted("♀"))
                    .addOption("Divers", "divers", "Klicke, um dies auszuwählen!", Emoji.fromFormatted("⚧"))
                    .build();

            ereignis.getChannel().sendMessageEmbeds(bauplan.build()).setActionRow(menü).queue();

            EmbedBuilder bauplan2 = new EmbedBuilder();
            bauplan2.setColor(0x87cfbd);
            bauplan2.setThumbnail("https://cdn.discordapp.com/attachments/994974047626612756/994975562265272400/kisspng-bullseye-computer-icons-goal-darts-shooting-target-shot-5acef76a21dd66.6438888815235131941387.png");
            bauplan2.setTitle("**:trumpet: │ Wähle deine Hobbys!**");
            bauplan2.setDescription("> Klicke auf das Menü unter dieser Nachricht, um deine Rollen auszuwählen!");
            bauplan2.setImage("https://cdn.discordapp.com/attachments/994974047626612756/994982524151414954/dafhadhfubgadgfadhghadhghadhghadughfuzad.png");

            SelectMenu menü2 = SelectMenu.create("hobbys")
                    .setPlaceholder("Wähle deine Hobbys!")
                    .setRequiredRange(1, 4)
                    .addOption("Gaming", "gaming", "Klicke, um dies auszuwählen!", Emoji.fromFormatted("\uD83D\uDD79"))
                    .addOption("Wissenschaft", "wissenschaft", "Klicke, um dies auszuwählen!", Emoji.fromFormatted("⚗"))
                    .addOption("Sport", "sport", "Klicke, um dies auszuwählen!", Emoji.fromFormatted("\uD83E\uDD3F"))
                    .addOption("Kunst", "kunst", "Klicke, um dies auszuwählen!", Emoji.fromFormatted("\uD83C\uDFA8"))
                    .build();

            ereignis.getChannel().sendMessageEmbeds(bauplan2.build()).setActionRow(menü2).queue();

            ereignis.getMessage().delete().queue();
        }

    }

    public void onSelectMenuInteraction (SelectMenuInteractionEvent ereignis) {

        if (ereignis.getSelectMenu().getId().equals("geschlecht")) {

            Role rolle = null;

            switch (ereignis.getValues().get(0)) {

                case "männlich":

                    rolle = ereignis.getGuild().getRoleById("994993122666881194");

                    break;

                case "weiblich":

                    rolle = ereignis.getGuild().getRoleById("994993192246198403");

                    break;

                case "divers":

                    rolle = ereignis.getGuild().getRoleById("994993210831147049");

                    break;

            }

            ereignis.getGuild().removeRoleFromMember(ereignis.getUser(), ereignis.getGuild().getRoleById("994993122666881194")).queue();
            ereignis.getGuild().removeRoleFromMember(ereignis.getUser(), ereignis.getGuild().getRoleById("994993192246198403")).queue();
            ereignis.getGuild().removeRoleFromMember(ereignis.getUser(), ereignis.getGuild().getRoleById("994993210831147049")).queue();

            ereignis.getGuild().addRoleToMember(ereignis.getUser(), rolle).queue();

            ereignis.reply("Deine Rollen wurden erfolgreich aktualisiert!").setEphemeral(true).queue();

        } else if (ereignis.getSelectMenu().getId().equals("hobbys")) {

            ereignis.getGuild().removeRoleFromMember(ereignis.getUser(), ereignis.getGuild().getRoleById("994993430247768145")).queue();
            ereignis.getGuild().removeRoleFromMember(ereignis.getUser(), ereignis.getGuild().getRoleById("994993458307665990")).queue();
            ereignis.getGuild().removeRoleFromMember(ereignis.getUser(), ereignis.getGuild().getRoleById("994993477039427604")).queue();
            ereignis.getGuild().removeRoleFromMember(ereignis.getUser(), ereignis.getGuild().getRoleById("994993493162340393")).queue();

            for (String s : ereignis.getValues()) {

                Role rolle = null;

                switch (s) {

                    case "gaming":

                        rolle = ereignis.getGuild().getRoleById("994993430247768145");

                        break;

                    case "wissenschaft":

                        rolle = ereignis.getGuild().getRoleById("994993458307665990");

                        break;

                    case "sport":

                        rolle = ereignis.getGuild().getRoleById("994993477039427604");

                        break;

                    case "kunst":

                        rolle = ereignis.getGuild().getRoleById("994993493162340393");

                        break;

                }

                ereignis.getGuild().addRoleToMember(ereignis.getUser(), rolle).queue();

            }

            ereignis.reply("Deine Rollen wurden erfolgreich aktualisiert!").setEphemeral(true).queue();

        }

    }

}
