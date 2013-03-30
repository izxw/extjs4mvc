//Ext.Loader.setConfig({enabled: true});// 开启动态加载
		
Ext.application({
	name : 'KSI', 
	appFolder : 'resources/app',
	autoCreateViewport: true,
	controllers : [
		'MainCtrl','UserCtrl','RoleCtrl','ResourceCtrl','OrgCtrl'
	],
    launch: function() {
        //Ext.tip.QuickTipManager.init();
    }	 
});
