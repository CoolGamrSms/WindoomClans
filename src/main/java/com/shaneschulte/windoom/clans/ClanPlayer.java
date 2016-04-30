package com.shaneschulte.windoom.clans;

import java.util.UUID;

/**
 * @author CoolGamrSms
 */
public class ClanPlayer {
    private Clan clan;
    private UUID player;
    public enum Rank { OWNER, LEADER, MEMBER, RECRUIT };
    private Rank rank;

    public ClanPlayer(UUID player) {
        this.clan = null;
        this.player = player;
        this.rank = Rank.RECRUIT;
    }

    public ClanPlayer(Clan clan, UUID player, Rank rank) {
        this.clan = clan;
        this.player = player;
        this.rank = rank;
    }

    public UUID getUUID() {
        return player;
    }

    public Clan getClan() {
        return clan;
    }

    public boolean isOwner() {
        return rank == Rank.OWNER;
    }
    public boolean isLeader() {
        return rank == Rank.LEADER || isOwner();
    }
    public boolean isTrusted() {
        return rank == Rank.MEMBER || isLeader();
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }

    public void setClan(Clan clan) {
        this.clan = clan;
    }
}
