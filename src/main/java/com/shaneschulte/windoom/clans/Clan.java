package com.shaneschulte.windoom.clans;

import java.util.HashSet;

/**
 * @author CoolGamrSms
 */
public class Clan {
    private String name;
    private String prefix;
    private HashSet<String> members;

    public Clan() {
        members = new HashSet<String>();
    }
}
