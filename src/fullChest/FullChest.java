package fullChest;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import com.nijiko.permissions.PermissionHandler;
import com.nijikokun.bukkit.Permissions.Permissions;
import org.bukkit.plugin.Plugin;

public class FullChest extends JavaPlugin {
	public static PermissionHandler permissionHandler;
	static boolean usePermissions;
	public void onDisable() {
		PluginDescriptionFile pdfFile = this.getDescription();
		System.out.println(pdfFile.getName() + " version "
				+ pdfFile.getVersion() + " is disabled.");
	}

	public void onEnable() {
		getCommand("fullchest").setExecutor(new ChestCommand(this));
		PluginDescriptionFile pdfFile = this.getDescription();
		String extramsg = setupPermissions();
		System.out.println(pdfFile.getName() + " version "
				+ pdfFile.getVersion() + " is enabled" + extramsg + ".");
	}

	private String setupPermissions() {
		Plugin permissionsPlugin = this.getServer().getPluginManager()
				.getPlugin("Permissions");

		if (FullChest.permissionHandler == null) {
			if (permissionsPlugin != null) {
				FullChest.permissionHandler = ((Permissions) permissionsPlugin)
						.getHandler();
				FullChest.usePermissions=true;
				return ", using Permissions";
			} else {
				FullChest.usePermissions=false;
				return " (could not find Permissions, using OP)";
			}
		}
		return "";
	}
}
