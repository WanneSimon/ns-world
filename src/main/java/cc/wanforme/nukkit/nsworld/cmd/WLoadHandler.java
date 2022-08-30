package cc.wanforme.nukkit.nsworld.cmd;

import cc.wanforme.nukkit.spring.plugins.command.FixedArgsHandler;
import cc.wanforme.nukkit.spring.util.NukkitServerUtil;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.Level;

/** load a world
 * /wm {load|unload} {name}
 * @author wanne
 * @date 2022-08-30
 */
public class WLoadHandler extends FixedArgsHandler {
	private static final String[] ARGS = { "{load|unload}", "{name}" };
	
	public WLoadHandler() {
		super("wm", ARGS);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String type = args[0];
		String name = args[1];
		
		boolean re = false;
		if("load".equals(type)) {
			re = this.loadWorld(name, sender);
		} else if("unload".equals(type)) {
			re = this.unloadWorld(name, sender);
		}
		return re;
	}

    protected boolean loadWorld(String name, CommandSender sender) {
    	Server server = NukkitServerUtil.getServer();
    	
    	if(!server.isLevelGenerated(name)) {
    		// this.getLanguage().translateString("nukkit.level.notFound", name)
    		return true;
    	}
    	
    	if(server.isLevelLoaded(name)) {
    		// loaded
    		return true;
    	}
    	
    	boolean re = server.loadLevel(name);
    	if (re) {
    		// success
    	} else {
    		// fail
    	}
    	return true;
    }

    protected boolean unloadWorld(String name, CommandSender sender) {
    	Server server = NukkitServerUtil.getServer();
    	
    	Level level = server.getLevelByName(name);
    	if(level==null) {
    		// not loaded
    		return true;
    	}
    	
    	boolean re = server.unloadLevel(level);
    	if (re) {
    		// success
    	} else {
    		// fail
    	}
    	return true;
    }
}
