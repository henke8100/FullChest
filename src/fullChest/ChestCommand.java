package fullChest;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import fullChest.FullChest;

public class ChestCommand implements CommandExecutor {
	public ChestCommand(FullChest plugin) {
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String fullcommand, String[] ca) {
		Player player = (Player) sender;
		String prefix = "§a[§bF§6ull§bC§6hest§a]§f ";
		if(FullChest.usePermissions){
			if (!FullChest.permissionHandler.has(player, "FullChest.create")) {
				sender.sendMessage(prefix
						+ "You do not have sufficient permissions.");
				return true;
			}
		}
		else{
			if (!sender.isOp()) {
				sender.sendMessage(prefix
						+ "You're not OP.");
				return true;
			}
		}
		if (ca.length == 0) {
			sender.sendMessage(prefix
					+ "Usage: /fullchest item (ex: /fullchest tnt or /fullchest 46)");
			return true;
		}
		Block tblock = player.getTargetBlock(null, 0);
		Block rtblock = tblock.getRelative(0, 1, 0);
		if (rtblock.getTypeId() != 0) {
			sender.sendMessage(prefix
					+ "The block above your target isn't air.");
			return true;
		}
		Material mat = Material.matchMaterial(ca[0]);
		if (mat == null) {
			sender.sendMessage(prefix + "Could not find " + ca[0]);
			return true;
		}
		rtblock.setTypeId(54);
		Chest chest = (Chest) rtblock.getState();
		// chest.getInventory().addItem(new ItemStack(mat, 64)); //, (short) 0,
		// bytedata
		for (int i = 0; i <= 26; i++) {
			chest.getInventory().addItem(new ItemStack(mat, 64));
		}
		sender.sendMessage(prefix + "Created a full chest of §7" + ca[0]
				+ "§f for you!");
		return true;
	}

}
