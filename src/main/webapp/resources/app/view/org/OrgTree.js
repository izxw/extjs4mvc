Ext.define("KSI.view.org.OrgTree", {
	extend : 'Ext.tree.Panel',
	alias : 'widget.orgtree',
	store : 'OrgTreeStore',
	displayField : "text",
	rootVisible : false,
	id : 'navTree',
	title : '主菜单',
	collapsible : true,
	margins : '0 5 0 0',
	width : 150
});