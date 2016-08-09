package si.psrcek.clickableChatNames.misc;

import java.util.Arrays;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class InventoryRegistry {
	public static Inventory playerInv, staffInv;
	
	public static ItemStack website, plot, tpa, tpaHere, tp, tpo, kick, ban, promote, demote;
	public static ItemStack blankSlot = new ItemStack(Material.AIR);
	
	public static void init() {
		
		website = createItemStack(Material.BOOK_AND_QUILL, "§b§lOpen website account", "§fGives you a link to the", "§fplayer's website account.");
		
		plot    = createItemStack(Material.COMPASS,        "§b§lGo to plot", "§fTeleports you to", "§fthe player's plot.", "", "§6Equivalent to: §e/p h player");
		
		tpa     = createItemStack(Material.ENDER_PEARL,    "§b§lRequest to teleport", "§fSends the player a", "§fteleportation request.", "", "§6Equivalent to: §e/tpa player");
		tpaHere = createItemStack(Material.EYE_OF_ENDER,   "§b§lRequest to teleport to you", "§fSends the player a", "§frequest to teleport to you.", "", "§6Equivalent to: §e/tpahere player");
		tp      = createItemStack(Material.PORTAL,         "§b§lTeleport", "§fTeleports you to", "§fthe player.", "", "§6Equivalent to: §e/tp player");
		tpo     = createItemStack(Material.ENDER_PORTAL,   "§b§lForce Teleport", "§fTeleports you to", "§fthe player, even if", "§fhe/she has teleportation disabled", "", "§6Equivalent to: §e/tpo player");
		
		kick    = createItemStack(Material.DIAMOND_BOOTS,  "§b§lKick", "§fKicks the player.", "§4Warning: you cannot", "§4specify a reason!", "", "§6Equivalent to: §e/kick player");
		ban     = createItemStack(Material.BARRIER,        "§b§lBan", "§fBans the player.", "§4Warning: you cannot", "§4specify a reason!", "", "§6Equivalent to: §e/ban player");
		
		promote = createItemStack(Material.DIAMOND,        "§b§lPromote", "§fPromotes the player.", "", "§6Equivalent to: §e/promote player");
		demote  = createItemStack(Material.COAL,           "§b§lDemote", "§fDemotes the player.", "", "§6Equivalent to: §e/demote player");
		
		playerInv = createInventory("§aPlayer actions", website, blankSlot, plot, tpa, tpaHere);
		staffInv = createInventory("§aPlayer actions", website, blankSlot, plot, tpa, tpaHere, tp, tpo, blankSlot, kick, ban, blankSlot, promote, demote);
		
	}
	
	private static ItemStack createItemStack(Material material, String name, String... lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));
		
		item.setItemMeta(meta);
		return item;
	}
	
	private static Inventory createInventory(String name, ItemStack... items) {
		Inventory inv = Bukkit.createInventory(null, items.length, name);
		
		inv.addItem(items);
		
		return inv;
	}
}
