package io.github.CriPsi1.dungeonCrawler.combatmanager;

import io.github.CriPsi1.dungeonCrawler.entity.Entity;

import java.util.List;
import java.util.ArrayList;

public class CombatManager {
    private List<Entity> participants = new ArrayList<>();
    private int currentTurnIndex;

    public void startCombat(List<Entity> participants)
    {
        this.participants = new ArrayList<>(participants);
        currentTurnIndex = 0;
    }

    public Entity getCurrentParticipant()
    {
        return participants.get(currentTurnIndex);
    }

    public void nextTurn()
    {
        currentTurnIndex +=1;
        if (currentTurnIndex == participants.size())
        {
            currentTurnIndex = 0;
        }
    }
}
