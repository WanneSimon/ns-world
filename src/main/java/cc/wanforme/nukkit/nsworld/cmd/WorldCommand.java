package cc.wanforme.nukkit.nsworld.cmd;

import cc.wanforme.nukkit.spring.plugins.command.NSCommand;

/** 世界切换命令
 * @author wanne
 * @date 2022-08-26
 */
public class WorldCommand extends NSCommand {
	private static final String MAIN = "w";
	
	public WorldCommand() {
		super(MAIN);
	}

	@Override
	protected void initCommand() {
		this.addHandler(new WorldListHandler(MAIN));
		this.addHandler(new WorldChangeHandler(MAIN));
	}

}
