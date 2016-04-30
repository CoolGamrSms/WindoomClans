package com.shaneschulte.windoom.clans;

import java.util.HashSet;
import java.util.UUID;

/**
 * @author CoolGamrSms
 */
public class Clan {
    private String name;
    private String prefix;
    private HashSet<UUID> members;

    public Clan(String name, String prefix, ClanPlayer leader) {
        this.name = name;
        this.prefix = prefix;
        members = new HashSet<UUID>();
        members.add(leader.getUUID());
    }

    public String getPrefix() {
        return prefix;
    }

    public String getName() {
        return name;
    }
}
