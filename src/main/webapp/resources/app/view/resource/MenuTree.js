Ext.define("KSI.view.resource.MenuTree", {
	extend : 'Ext.tree.Panel',
	alias : 'widget.menutree',
	store : 'MenuTreeStore',
	displayField : "text",
	rootVisible : false,
	id : 'navTree',
	title : '主菜单',
	collapsible : true,
	margins : '0 5 0 0',
	width : 150
});