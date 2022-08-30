package cc.wanforme.nukkit.nsworld.cmd;

import java.util.List;
import java.util.Map;

import cc.wanforme.nukkit.nsworld.LangHolder;
import cc.wanforme.nukkit.spring.plugins.command.FixedArgsHandler;
import cc.wanforme.nukkit.spring.util.NukkitServerUtil;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Level;

/** 世界列表处理器
 * @author wanne
 * @date 2022-08-26
 */
public class WorldListHandler extends FixedArgsHandler {

	public WorldListHandler(String main) {
		// 注：null 会被解析成长度为0的空数组
		super(main, null);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		List<String> worlds = this.listWorlds();
		this.reportLevelNames(sender, worlds);
		return true;
	}

	private List<String> listWorlds() {
		Map<Integer, Level> levels = NukkitServerUtil.getServer().getLevels();
		List<String> names = levels.values().stream()
				.map(Level::getName).toList();
		return names;
	}
	
	private void reportLevelNames(CommandSender sender, List<String> names) {
		StringBuilder sb = new StringBuilder();
//		sb.append("=== world ===\n");
		sb.append(LangHolder.Get("world-list-header"))
			.append("\n");
		String str = String.join("\n", names);
		sb.append(str);
//		sb.append("\n=============");
		sb.append("\n")
			.append(LangHolder.Get("world-list-footer"));
		sender.sendMessage(sb.toString());
	}
	
}
