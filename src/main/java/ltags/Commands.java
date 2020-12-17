package ltags;

import com.google.common.collect.Lists;
import ltags.Managers.StorageManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class Commands implements CommandExecutor {

    private StorageManager storage;
    private LTags main;

    public Commands(LTags main) {
        this.main = main;
        this.storage = main.storage;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if (args.length >= 3) {
            String identifier = args[0];
            String display = args[1];

            StringBuilder str = new StringBuilder();
            for (int i = 2; i < args.length;i++) {
                str.append(args[i]).append(" ");
            }
            List<String> lore = Lists.newArrayList(str.toString().split("%nl%"));
            main.saveTag(identifier,display,lore);
            main.sendMessage(p,"&aTag Created!\n&fIdentifier: "+identifier+"\n&fDisplay: "+display+"\nlore: ");
            for (String lorestring: lore) {
                main.sendMessage(p,"&f"+lorestring);
            }
        }

        return false;
    }
}
