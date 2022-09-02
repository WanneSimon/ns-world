package cc.wanforme.nukkit.nsworld.cmd;

import java.text.MessageFormat;
import java.util.regex.Pattern;

import cc.wanforme.nukkit.nsworld.LangHolder;
import cc.wanforme.nukkit.spring.plugins.command.FixedOrderHandler;
import cc.wanforme.nukkit.spring.util.NukkitServerUtil;
import cn.nukkit.Server;
import cn.nukkit.command.Command;
import cn.nukkit.command.CommandSender;
import cn.nukkit.level.generator.Generator;

/** generate a world
 * /wc {name} [type] [seed]
 * @author wanne
 * @date 2022-08-30
 */
public class WorldCreateHandler extends FixedOrderHandler {
	private static final String[] ARGS = { "{name}", 
			"[0|flat|1|normal|2|default|3|nether|4|the_end]",
			"[seed]"};
	
	public WorldCreateHandler(String main) {
		super(main, ARGS);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		String name = args[0];
		String type = args.length > 1 ? args[1] : null;
		String seed = args.length > 2 ? args[2] : null;
		return this.createWorld(sender, name, type, seed);
	}

    protected boolean createWorld(CommandSender sender, String name, String type, String seedStr) {
    	// wc {name} [type] [seed] 
    	
//    	String name = "test";
//    	Long seed = null;
//    	String type = null;
    	
    	Server server = NukkitServerUtil.getServer();
    	
    	// check name ( 数字字母下划线短横线 )
    	Pattern pattern = Pattern.compile("^([\\w-])+$");
    	if(pattern.matcher(name).matches()) {
    		// illegal name 
    		sender.sendMessage(LangHolder.Get("world-name-illegal"));
    		return true;
    	}
    	
    	if(server.getLevelByName(name)!=null) {
    		// level existed;
    		sender.sendMessage(LangHolder.Get("world-name-existed"));
    		return true;
    	}
    	
    	Long seed = null;
    	if(seedStr == null) {
    		seed = new java.util.Random().nextLong();
    	} else {
        	try {
        		seed = Long.parseLong(seedStr);
    		} catch (NumberFormatException e) {
    			// invalid number
    			sender.sendMessage(LangHolder.Get("world-seed-illegal"));
    			return true;
    		}
    	}
    	
    	// type 支持 name 或者 数字
//    	0 flat    Flat.class
//    	1 normal  Normal.class
//    	2 default Normal.class
//    	3 nether  Nether.class
//    	4 the_end TheEnd.class
    	
    	Class<? extends Generator> genclazz = null;
    	try {
    		int numType = Integer.parseInt(type);
			genclazz = Generator.getGenerator(numType);
		} catch (NumberFormatException e) {
			genclazz = Generator.getGenerator(type);
		}
    	
    	if(type != null && genclazz == null) {
    		// 不匹配的类型
    		sender.sendMessage(LangHolder.Get("world-type-illegal"));
    		return false;
    	}
    	
//    	boolean re = false; 
//    	try {
//        	re = server.generateLevel(name, seed, genclazz);
//		} catch (Exception e) {
//			// log errors 
//			return true;
//		}

//    	if(!re) {
    	if(!server.generateLevel(name, seed, genclazz)) {
    		// fail
    		sender.sendMessage(MessageFormat.format(LangHolder.Get("world-wc-fail"), name));
    	} else {
    		// success
    		sender.sendMessage(MessageFormat.format(LangHolder.Get("world-wc-success"), name));
    	}

    	return true;
    }

}
