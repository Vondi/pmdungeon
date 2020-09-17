package de.fhbielefeld.pmdungeon.game.items;

public class Inventory {

    private final int size;
    private final Item[] items;
    private int selectedItem;

    public Inventory(int size) {
        this.size = size;
        items = new Item[size];
    }

    public void setSlot(int index, Item item) {
        if (index >= 0 && index < items.length) {
            items[index] = item;
        }
    }

    public void removeItem(Item item) {
        if (item != null) {
            for (int i = 0; i < items.length; i++) {
                if (items[i] == item) {
                    item.dispose();
                    items[i] = null;
                }
            }
        }
    }

    public Item getSelectedItem() {
        return items[selectedItem];
    }

    public void setSelectedItem(int selectedItem) {
        this.selectedItem = selectedItem;
    }

    public int getSize() {
        return size;
    }

    public Item[] getItems() {
        return items;
    }
}
