package ltags.objects;

import java.util.List;

public class Tag {

    private String displayname;
    private String identifier;
    private List<String> description;

    public Tag(String displayname, String identifier, List<String> description) {
        this.displayname = displayname;
        this.identifier = identifier;
        this.description = description;
    }

    public String getDisplayname() {
        return displayname;
    }

    public List<String> getDescription() {
        return description;
    }

    public String getIdentifier() {
        return identifier;
    }
}
