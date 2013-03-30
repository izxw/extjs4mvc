Ext.define('KSI.view.Viewport', {
    extend: 'Ext.container.Viewport',
	layout : 'border',
	items : [{
				region : 'north',
				contentEl:'logo',
				height:50,
				border : false,
				margins : '0 0 5 0'
			},{
				region:'west',
				xtype:'menutree'
			}, {
				region : 'south',
				header:false,
				collapsible : true,
				contentEl:'footer',
				split : false,
				height : 34,
				minHeight : 34,
				margins : '5 0 0 0'
			}, {
				id:'centertab',
				region : 'center',
				xtype : 'tabpanel',
				activeTab : 0, 
				items : {
					title : '主页',
					contentEl:'content'
				}
			}]
});