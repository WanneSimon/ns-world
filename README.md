一个简单的多世界插件和 `nsplugin` 的简单演示

### Usage

a. 世界列表  
`/w` 

b. 切换世界  
`/w {name}`   
`name` 可以是开头的几个字母，例如 `the` 会匹配到 `the_nether`。  
如果有多个以 `the` 开头的世界名，以匹配到的第一个为准。  

c. 加载、卸载世界  
`/wm {load|unload} {name}`  
`load` 加载一个世界  
`unload` 卸载一个世界  

d. 创建世界
`/wc {name} [type] [seed]`  
`name` - 名称  
`type` - 类型  
`seed` - 种子  
类型支持数字或者名称，数字和名称都是 `pnx` 中定义的

| 类型  | 数字  | 字符名称    |
|:---:|:---:|:-------:|
| 平坦  | 0   | flat    |
| 正常  | 1   | normal  |
| 默认  | 2   | default |
| 下届  | 3   | nether  |
| 末地  | 4   | the_end |
