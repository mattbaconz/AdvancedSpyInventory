package mc.dailycraft.advancedspyinventory.nms.v26_3;

import mc.dailycraft.advancedspyinventory.Main;
import mc.dailycraft.advancedspyinventory.inventory.BaseInventory;
import org.bukkit.craftbukkit.inventory.CraftAbstractInventoryView;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.MenuType;
import org.jetbrains.annotations.NotNull;

public class CustomInventoryView extends CraftAbstractInventoryView {
    private final Player viewer;
    private final BaseInventory container;
    private final Inventory inventory;

    public CustomInventoryView(Player viewer, BaseInventory container) {
        this.viewer = viewer;
        inventory = Main.NMS.createInventory(this.container = container);
    }

    public BaseInventory getContainer() {
        return container;
    }

    @NotNull
    @Override
    public Inventory getTopInventory() {
        return inventory;
    }

    @NotNull
    @Override
    public Inventory getBottomInventory() {
        return viewer.getInventory();
    }

    @NotNull
    @Override
    public HumanEntity getPlayer() {
        return viewer;
    }

    @NotNull
    @Override
    public InventoryType getType() {
        return inventory.getType();
    }

    @NotNull
    @Override
    public MenuType getMenuType() {
        return switch (container.getSize()) {
            case 9 -> MenuType.GENERIC_9X1;
            case 18 -> MenuType.GENERIC_9X2;
            case 27 -> MenuType.GENERIC_9X3;
            case 36 -> MenuType.GENERIC_9X4;
            case 45 -> MenuType.GENERIC_9X5;
            case 54 -> MenuType.GENERIC_9X6;
            default -> throw new IllegalArgumentException("Unsupported inventory size: " + container.getSize());
        };
    }

    @NotNull
    @Override
    public String getTitle() {
        return container.getTitle();
    }

    @NotNull
    @Override
    public String getOriginalTitle() {
        return container.getTitle();
    }

    @Override
    public void setTitle(@NotNull String title) {}
}
