package si.psrcek.clickableChatNames.misc;

import java.util.Arrays;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class InventoryRegistry {
	public static Inventory playerInv, staffInv, kickInv, banInv;
	
	public static ItemStack website, plot, tpa, tpaHere, tp, tpo, kick, ban, promote, demote;
	
	public static ItemStack kickCaps, kickSpam, kickAdvertising, kickEntitySpam;
	public static ItemStack banCaps,  banSpam,  banAdvertising,  banEntitySpam, banExploitingBugs, banCheating, banServerCrash, banGriefing;
	
	public static ItemStack blankSlot = new ItemStack(Material.AIR);
	
	public static void init() {
		
		website = createItemStack(Material.BOOK_AND_QUILL,     "§b§lOpen website account", "§fGives you a link to the", "§fplayer's website account.");
		
		plot    = createItemStack(Material.COMPASS,            "§b§lGo to plot", "§fTeleports you to", "§fthe player's plot.", "", "§6Equivalent to: §e/p h player");
		
		tpa     = createItemStack(Material.ENDER_PEARL,        "§b§lRequest to teleport", "§fSends the player a", "§fteleportation request.", "", "§6Equivalent to: §e/tpa player");
		tpaHere = createItemStack(Material.EYE_OF_ENDER,       "§b§lRequest to teleport to you", "§fSends the player a", "§frequest to teleport to you.", "", "§6Equivalent to: §e/tpahere player");
		tp      = createItemStack(Material.MINECART,           "§b§lTeleport", "§fTeleports you to", "§fthe player.", "", "§6Equivalent to: §e/tp player");
		tpo     = createItemStack(Material.EXPLOSIVE_MINECART, "§b§lForce Teleport", "§fTeleports you to", "§fthe player, even if", "§fhe/she has teleportation disabled", "", "§6Equivalent to: §e/tpo player");
		
		kick    = createItemStack(Material.DIAMOND_BOOTS,      "§b§lKick", "§fKicks the player.", "§4Warning: you cannot", "§4specify a reason!", "", "§6Equivalent to: §e/kick player");
		ban     = createItemStack(Material.BARRIER,            "§b§lBan", "§fBans the player.", "§4Warning: you cannot", "§4specify a reason!", "", "§6Equivalent to: §e/ban player");
		
		promote = createItemStack(Material.DIAMOND,            "§b§lPromote", "§fPromotes the player.", "", "§6Equivalent to: §e/promote player");
		demote  = createItemStack(Material.COAL,               "§b§lDemote", "§fDemotes the player.", "", "§6Equivalent to: §e/demote player");
		
		playerInv = createInventory("§aPlayer actions",  9, website, blankSlot, blankSlot, blankSlot, plot,      blankSlot, blankSlot, tpa,     tpaHere);
		
		staffInv  = createInventory("§aPlayer actions", 18, website, blankSlot, plot,      blankSlot, tpa,       tpaHere,   blankSlot, tp,      tpo, 
		                                                    kick,    ban,       blankSlot, blankSlot, blankSlot, blankSlot, blankSlot, promote, demote);
		
		
		kickCaps          = createItemStack(Material.SKULL_ITEM, 3,   "§a§lCaps", "§fUse this if the player", "§foveruses capital letters", "§fin the chat.", "", "§6Equivalent to: §e/kick player caps");
		kickSpam          = createItemStack(Material.SKULL_ITEM, 3,   "§a§lSpam", "§fUse this if the player", "§fspams in the chat.", "", "§6Equivalent to: §e/kick player spam");
		kickAdvertising   = createItemStack(Material.SKULL_ITEM, 3,   "§a§lAdvertising", "§fUse this if the player", "§fadvertises in the chat.", "", "§6Equivalent to: §e/kick player advertising");
		
		kickEntitySpam    = createItemStack(Material.MONSTER_EGG,     "§a§lEntity spam", "§fUse this if the player", "§fspams entities.", "", "§6Equivalent to: §e/kick player entity spam");
		
		banCaps           = createItemStack(Material.SKULL_ITEM, 3,   "§4§lCaps", "§fUse this if the player", "§foveruses capital letters", "§fin the chat.", "", "§6Equivalent to: §e/ban player caps");
		banSpam           = createItemStack(Material.SKULL_ITEM, 3,   "§4§lSpam", "§fUse this if the player", "§fspams in the chat.", "", "§6Equivalent to: §e/ban player spam");
		banAdvertising    = createItemStack(Material.SKULL_ITEM, 3,   "§4§lAdvertising", "§fUse this if the player", "§fadvertises in the chat.", "", "§6Equivalent to: §e/ban player advertising");
		
		banEntitySpam     = createItemStack(Material.MONSTER_EGG,     "§4§lEntity spam", "§fUse this if the player", "§fspams entities.", "", "§6Equivalent to: §e/ban player entity spam");
		banExploitingBugs = createItemStack(Material.BARRIER,         "§4§lExploiting bugs", "§fUse this if the player", "§fexploits bugs.", "", "§6Equivalent to: §e/ban player bug exploiting");
		banCheating       = createItemStack(Material.RECORD_11,       "§4§lCheating", "§fUse this if the player", "§fcheats in sv.", "", "§6Equivalent to: §e/ban player cheating in survival");
		banServerCrash    = createItemStack(Material.MONSTER_EGG, 54, "§4§lServer crash", "§fUse this if the player", "§fcrashes the server.", "", "§6Equivalent to: §e/ban player crashing the server");
		banGriefing       = createItemStack(Material.MONSTER_EGG, 58, "§4§lGriefing", "§fUse this if the player", "§fgriefs.", "", "§6Equivalent to: §e/ban player griefing");
		
		
		UUID burger = UUID.fromString("62b1deef-b30e-44f8-b50c-f83e0c32c653"); //xD
		UUID viper  = UUID.fromString("872307c2-ddae-44ea-9b7c-b9531e290bb0"); //xD
		UUID nalaek = UUID.fromString("02489d58-cbef-48ae-b76b-2544a1a910eb"); //xD
		
		kickCaps        = addSkullOwner(kickCaps,        burger);
		kickSpam        = addSkullOwner(kickSpam,        viper );
		kickAdvertising = addSkullOwner(kickAdvertising, nalaek);
		
		banCaps         = addSkullOwner(banCaps,         burger);
		banSpam         = addSkullOwner(banSpam,         viper );
		banAdvertising  = addSkullOwner(banAdvertising,  nalaek);
		
		kickInv = createInventory("§bKick reasons", 9, kickCaps, kickSpam, kickAdvertising, kickEntitySpam);
		
		banInv  = createInventory("§4Ban reasons",  9, banCaps,  banSpam,  banAdvertising,  banEntitySpam, blankSlot, banExploitingBugs, banCheating, banServerCrash, banGriefing);
	}
	
	private static ItemStack addSkullOwner(ItemStack item, UUID uuid) {
		if (item.getType() == Material.SKULL_ITEM) {
			ItemStack newItem = item;
			SkullMeta sm = (SkullMeta) item.getItemMeta();
			OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
			
			sm.setOwner(player.getName());
			newItem.setItemMeta(sm);
			return newItem;
			
		} else {
			return item;
		}
	}
	
	private static ItemStack createItemStack(Material material, String name, String... lore) {
		ItemStack item = new ItemStack(material);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));
		
		item.setItemMeta(meta);
		return item;
	}
	
	private static ItemStack createItemStack(Material material, int data, String name, String... lore) {
		ItemStack item = new ItemStack(material, 1, (short) data);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(name);
		meta.setLore(Arrays.asList(lore));
		
		item.setItemMeta(meta);
		return item;
	}
	
	private static Inventory createInventory(String name, int size, ItemStack... items) {
		if (size % 9 != 0) return null;
		
		Inventory inv = Bukkit.createInventory(null, size, name);
		
		for (int i = 0; i < items.length; i++) {
			inv.setItem(i, items[i]);
		}
		
		return inv;
	}
}
