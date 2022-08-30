package cc.wanforme.nukkit.nsworld;

import cc.wanforme.nukkit.spring.plugins.lang.ConfigFileType;
import cc.wanforme.nukkit.spring.plugins.lang.PluginLangHolder;
import cn.nukkit.plugin.Plugin;

/** 语言配置
 * @author wanne
 * @date 2022-08-25
 */
public class LangHolder extends PluginLangHolder {
	private static LangHolder instance;
	
	public LangHolder(Plugin plugin, String lang) {
		super(plugin, ConfigFileType.YML, "lang/", lang);
		this.init(false);
		instance = this;
	}

	public static String Get(String key) {
		return instance.get(key);
	}
	
}
