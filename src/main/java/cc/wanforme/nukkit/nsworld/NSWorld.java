package cc.wanforme.nukkit.nsworld;

import org.springframework.stereotype.Component;

import cc.wanforme.nukkit.nsworld.cmd.WorldCommand;
import cc.wanforme.nukkit.spring.plugins.command.NSPluginBase;

/**
 * @author wanne
 * @date 2022-08-25
 */
@Component
public class NSWorld extends NSPluginBase {

	private LangHolder langHolder;
	
    @Override
    public void onLoad() {
    	
    	// 1. save default config: "config.yml"
    	// 1. 保存默认文件夹
		this.saveDefaultConfig();
    	
		// 2. save and load language 
		// 2. 保存和加载语言配置文件
		String lang = getConfig().get("lang", "en");
		langHolder = new LangHolder(this, lang);
    }

    @Override
    public void onEnable() {
    	this.registerCommands();
    }

    @Override
    public void onDisable() {
    }

    private void registerCommands() {
    	
//    	NSCommand wtp = new NSCommand() {
//			
//		};
		
    	// w 
    	this.registerNSCommand(new WorldCommand());
    	
    	// w {}
    	
    	// wc
    	
    }
    
    public LangHolder getLangHolder() {
		return langHolder;
	}
}
