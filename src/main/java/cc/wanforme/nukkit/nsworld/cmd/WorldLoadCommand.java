package cc.wanforme.nukkit.nsworld.cmd;

import cc.wanforme.nukkit.spring.plugins.command.NSCommand;

/** 世界切换命令
 * @author wanne
 * @date 2022-08-26
 */
public class WorldLoadCommand extends NSCommand {
	private static final String MAIN = "wl";
	
	public WorldLoadCommand() {
		super(MAIN);
	}

	@Override
	protected void initCommand() {
		this.addHandler(new WLoadHandler(MAIN));
	}

}
