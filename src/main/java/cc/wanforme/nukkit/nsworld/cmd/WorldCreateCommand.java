package cc.wanforme.nukkit.nsworld.cmd;

import cc.wanforme.nukkit.spring.plugins.command.NSCommand;

/** 世界切换命令
 * @author wanne
 * @date 2022-08-26
 */
public class WorldCreateCommand extends NSCommand {
	private static final String MAIN = "wc";
	
	public WorldCreateCommand() {
		super(MAIN);
	}

	@Override
	protected void initCommand() {
		this.addHandler(new WorldCreateHandler(MAIN));
	}

}
