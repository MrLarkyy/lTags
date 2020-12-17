package ltags.Managers;

import ltags.LTags;
import ltags.objects.Tag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class StorageManager {

    private LTags main;
    private List<Tag> tags;
    private HashMap<UUID,Tag> TagPlayers;

    public StorageManager(LTags main) {
        this.main = main;
    }

    public void loadTags() {
        List<Tag> loadedTags = new ArrayList<>();
        if (main.getConfigFile().getConfigurationSection("tags")!=null) {
            for (String str : main.getConfigFile().getKeys(false)) {
                String identifier = str;
                String displayname = main.getConfigFile().getString("tags."+str+".display");
                List<String> description = main.getConfigFile().getStringList("tags."+str+".lore");
                if (description==null)
                    description = new ArrayList<String>();
                Tag tag = new Tag(displayname,identifier,description);

                loadedTags.add(tag);
            }
            this.tags = loadedTags;
        }
    }

    public void saveTags() throws IOException {
        for(Tag tag : tags) {
            main.getConfigFile().set("tags."+tag.getIdentifier()+".display",tag.getDisplayname());
            main.getConfigFile().set("tags."+tag.getIdentifier()+".lore",tag.getDescription());
        }
        main.getConfigFile().save(main.getCFile());
    }

    public void addTag(Tag tag) {
        tags.add(tag);
    }

    public boolean doesTagExist(Tag tag) {
        for (Tag existtag : tags) {
            if (existtag.getIdentifier().equals(tag.getIdentifier())) {
                return true;
            }
        }
        return false;
    }
    public List<Tag> getTags() {
        List<Tag>gettags = tags;
        return gettags;
    }


    public void removeTag(Tag tag) throws IOException {
        if(tag!=null && tags!=null) {
            tags.remove(tag);
            }
        main.getConfigFile().set("tags",null);
        saveTags();
    }
}
