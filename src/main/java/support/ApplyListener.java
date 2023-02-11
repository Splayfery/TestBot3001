package support;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.ModalInteractionEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.Modal;
import net.dv8tion.jda.api.interactions.components.buttons.Button;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import org.w3c.dom.Text;

public class ApplyListener extends ListenerAdapter {

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (event.getName().equals("setup-bewerbung")) {
            if (event.getMember().hasPermission(Permission.ADMINISTRATOR)) {

                EmbedBuilder builder = new EmbedBuilder();
                builder.setTitle("Starte deine Bewerbung!");
                builder.setDescription("Als Teammitglied wirkst du bei der Moderation, Entwicklung und dem Support des Servers mit.");
                builder.addField("Wie bewerbe ich mich?", "Du kannst dich über den Button unter dieser Nachricht bewerben!", true);
                builder.setThumbnail("https://cdn-icons-png.flaticon.com/512/3238/3238024.png");
                builder.setColor(0xe2b768);

                event.getChannel().sendMessageEmbeds(builder.build()).setActionRow(Button.primary("applybutton", "Bewirb dich jetzt!")).queue();
                event.deferReply(true).queue();
            }
        }
    }

    public void onButtonInteraction(ButtonInteractionEvent event) {
        if (event.getButton().getId().equals("applybutton")) {

            TextInput rang = TextInput.create("rang", "Rang für die Bewerbung", TextInputStyle.SHORT)
                    .setPlaceholder("Gib hier deinen Rang an")
                    .build();

            TextInput erfahrung = TextInput.create("erfahrung", "Deine Erfahrung", TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Gib an, welche Erfahrung du schon hast")
                    .setMinLength(30)
                    .setMaxLength(500)
                    .build();

            TextInput persönlichkeit = TextInput.create("persönlichkeit", "Deine Persönlichkeit", TextInputStyle.PARAGRAPH)
                    .setPlaceholder("Erzähl etwas über dich")
                    .setMinLength(30)
                    .build();

            Modal modal = Modal.create("applymodal", "Bewerbungsformular")
                    .addActionRows(ActionRow.of(rang), ActionRow.of(erfahrung), ActionRow.of(persönlichkeit))
                    .build();

            event.replyModal(modal).queue();

        }
    }

    public void onModalInteraction(ModalInteractionEvent event) {
        if (event.getModalId().equals("applymodal")) {

            EmbedBuilder builder = new EmbedBuilder();
            builder.setTitle("Bewerbung von " + event.getMember().getEffectiveName());
            builder.addField("Rang", event.getValue("rang").getAsString(), false);
            builder.addField("Erfahrung", event.getValue("erfahrung").getAsString(), false);
            builder.addField("Persönlichkeit", event.getValue("persönlichkeit").getAsString(), false);
            builder.setThumbnail(event.getMember().getEffectiveAvatarUrl());

            event.getGuild().getTextChannelById("994974047626612756").sendMessageEmbeds(builder.build()).queue();

            event.reply("Danke für deine Bewerbung!").setEphemeral(true).queue();

        }
    }

}
