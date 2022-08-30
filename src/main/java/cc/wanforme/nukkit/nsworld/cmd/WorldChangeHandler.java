package cc.wanforme.nukkit.nsworld.cmd;

import java.text.MessageFormat;
import java.util.Collection;
import java.util.Map;

import cc.wanforme.nukkit.nsworld.LangHolder;
import cc.wanforme.nukkit.spring.plugins.command.FixedArgsHandler;
import cc.wanforme.nukkit.spring.util.NukkitServerUtil;
import cn.nukkit.Player;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Level;

/** 世界切换处理器
 * @author wanne
 * @date 2022-08-26
 */
public class WorldChangeHandler extends FixedArgsHandler {
	private static final String[] cmdArgs = {"{name}"};
	
	public WorldChangeHandler(String main) {
		super(main, cmdArgs);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!check(sender)) {
			sender.sendMessage(LangHolder.Get("command-only-player"));
			return true;
		}
		
		String name = args[0];
		Level level = this.findWorld(name);
		if(level == null) {
			sender.sendMessage(MessageFormat.format(LangHolder.Get("world-not-existed"), name));
		}
		
		name = level.getName();
		Player p = (Player) sender;
		p.teleport(level.getSpawnLocation());
		
		sender.sendMessage(MessageFormat.format(LangHolder.Get("world-tp-success"), name));
		return true;
	}

	private boolean check(CommandSender sender){
		return sender instanceof Player;
	}
	
	private Level findWorld(String name) {
		Map<Integer, Level> levels = NukkitServerUtil.getServer().getLevels();
//		List<String> names = levels.values().stream()
//				.map(Level::getName).toList();
		Collection<Level> ls = levels.values();

		// 寻找完全相等的
		Level eq = null;
		// 相似的
		Level first = null;
		for (Level level : ls) {
			String n = level.getName();
			if(n.equals(name)) {
				eq = level;
			} else if(n.startsWith(name)) {
				first = level;
			}
			
			if(eq != null) {
				break;
			}
		}
		
		
		return eq!=null ? eq : first;
	}
	
}
