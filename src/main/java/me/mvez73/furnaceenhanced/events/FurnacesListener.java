package me.mvez73.furnaceenhanced.events;

import org.bukkit.block.Block;
import org.bukkit.block.BrewingStand;
import org.bukkit.block.Furnace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BrewingStartEvent;
import org.bukkit.event.inventory.FurnaceBurnEvent;
import org.bukkit.event.inventory.FurnaceStartSmeltEvent;
import org.bukkit.event.player.PlayerInteractEvent;


public class FurnacesListener implements Listener {

    public Player player;
    int cookTimeReduction;
    int burnTimeReduction;
    int brewTimeReduction;

    @EventHandler
    public void onPlayerInteract (PlayerInteractEvent event) {
        Block block = event.getClickedBlock();
        if (block != null && (block.getState() instanceof Furnace || block.getState() instanceof BrewingStand)) {
            player = event.getPlayer();
        }
    }

    @EventHandler
    public void onFurnaceBurn (FurnaceBurnEvent event) {
        Block block = event.getBlock();
        if (block.getState() instanceof Furnace) {
            int burnTime = event.getBurnTime();
            int[] timeArray = new int[]{ 90,80,70,60,50,40,30,20,10 };
            for (int percent : timeArray) {
                if (player != null && player.hasPermission("furnaceenhanced." + percent)) {
                    burnTimeReduction = burnTime * percent / 100;
                    event.setBurnTime((short) (burnTime - burnTimeReduction));
                }
            }
        }
    }

    @EventHandler
    public void onFurnaceStartSmelt (FurnaceStartSmeltEvent event) {
        int cookTime = event.getTotalCookTime();
        int[] timeArray = new int[]{ 90,80,70,60,50,40,30,20,10 };
        for (int percent : timeArray) {
            if (player != null && player.hasPermission("furnaceenhanced." + percent)) {
                cookTimeReduction = cookTime*percent/100;
                event.setTotalCookTime(cookTime - cookTimeReduction);
                return;
            }
        }
    }

    @EventHandler
    public void onBrewingStart (BrewingStartEvent event) {
        Block block = event.getBlock();
        if (block.getState() instanceof BrewingStand) {
            int brewTime = event.getTotalBrewTime();
            int[] timeArray = new int[]{ 90,80,70,60,50,40,30,20,10 };
            for (int percent : timeArray) {
                if (player != null && player.hasPermission("furnaceenhanced." + percent)) {
                    brewTimeReduction = brewTime*percent/100;
                    event.setTotalBrewTime(brewTime - brewTimeReduction);
                    return;
                }
            }
        }
    }
}
