/**
 * 程序外部组件引用
 */
 //初始化Ext.QuickTips，以使得tip提示可用
Ext.QuickTips.init();
//初始化提示工具框
Ext.tip.QuickTipManager.init();
//启动动态加载机制
//Ext.Loader.setConfig({
//	enabled:true,
//	paths:{
//		baseUx:"core/ux/base",
//		factory:"core/coreApp/util/factory"
//	}
//})
Ext.Loader.setConfig({enabled: true});// 开启动态加载

