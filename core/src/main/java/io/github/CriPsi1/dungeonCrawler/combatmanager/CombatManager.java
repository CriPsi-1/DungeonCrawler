package io.github.CriPsi1.dungeonCrawler.combatmanager;

import io.github.CriPsi1.dungeonCrawler.enemy.Enemy;
import io.github.CriPsi1.dungeonCrawler.entity.Entity;
import io.github.CriPsi1.dungeonCrawler.player.Player;

import java.util.List;
import java.util.ArrayList;

public class CombatManager {
    private List<Entity> participants = new ArrayList<>();
    private int currentTurnIndex;

    // create list for fight
    public void startCombat(List<Entity> participants)
    {
        this.participants = new ArrayList<>(participants);
        currentTurnIndex = 0;
    }


    // get current turn participant
    public Entity getCurrentParticipant()
    {
        return participants.get(currentTurnIndex);
    }

    // next turn of fight
    public void nextTurn()
    {
        currentTurnIndex +=1;
        if (currentTurnIndex == participants.size())
        {
            currentTurnIndex = 0;
        }
        getCurrentParticipant().recoverPoints();
        System.out.println("Current points: " + getCurrentParticipant().getCurrentPoints());
    }

    // attack function
    public boolean entityAttack (Entity attacker, Entity target)
    {
        int cost = 2;
        if (!target.isAlive() || !attacker.isAlive() || attacker != getCurrentParticipant() || attacker == target)
        {
            return false;
        }
        if (!attacker.tryToSpendActionPoints(cost))
        {
            System.out.println("Not enough points");
            return false;
        }
        target.takeDmg(3);
        return true;
    }


    // check if the combat has over
    public boolean isCombatOver(){
        boolean hasAlivePlayers = false;
        boolean hasAliveEnemy = false;

        for (int i = 0; i < participants.size(); i++)
        {
            if (!participants.get(i).isAlive())
            {
                continue;
            }
            if (participants.get(i) instanceof Player)
            {
                hasAlivePlayers = true;
            }
            else if (participants.get(i) instanceof Enemy)
            {
                hasAliveEnemy = true;
            }
        }
        return !hasAliveEnemy || !hasAlivePlayers;
    }
}
