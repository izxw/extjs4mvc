Ext.define('KSI.view.user.List', {
			extend : 'Ext.grid.Panel',
			alias : 'widget.userlist',
			selType : 'rowmodel',
			title : '用户列表',
			store : 'User',
			autoShow : true,
			tbar : ['按条件搜索：', '姓名：', {
						xtype : 'textfield',
						name : 'txtName'
					}, {
						xtype : 'button',
						text : '查询',
						iconCls : 'add',
						id : 'userlist-query',
						plugins : [Ext.create('KSI.utils.AccessRule', {
									featureName : 'userlist-query'
								})]
					}, {
						xtype : 'tbfill'
					}, {
						xtype : 'button',
						text : '新增',
						iconCls : 'add',
						id : 'userlist-new',
						plugins : [Ext.create('KSI.utils.AccessRule', {
									featureName : 'userlist-create'
								})]
					}, {
						xtype : 'button',
						text : '编辑',
						iconCls : 'Applicationedit',
						id : 'userlist-edit',
						plugins : [Ext.create('KSI.utils.AccessRule', {
									featureName : 'userlist-edit'
								})]
					}, {
						xtype : 'button',
						text : '删除',
						iconCls : 'Delete',
						id : 'userlist-delete',
						plugins : [Ext.create('KSI.utils.AccessRule', {
									featureName : 'userlist-delete'
								})]
					}],
			columns : [{
						header : '姓名',
						dataIndex : 'username',
						flex : 1,
						sortable : true,
						editor : 'textfield'
					}, {
						header : '密码',
						dataIndex : 'password',
						flex : 1,
						renderer : function(value) {
							return Ext.String.format(
									'<a href="mailto:{0}">{1}</a>', value,
									value);
						}
					}, {
						header : 'id',
						dataIndex : 'id',
						flex : 2,
						hideable : false
					}],
			initComponent : function() {
				var self = this;
				this.dockedItems = [{
							xtype : 'pagingtoolbar',
							store : 'User',
							dock : 'bottom',
							displayInfo : true,
							displayMsg : '显示用户 {0} - {1} 共 {2}'
						}];
				this.callParent(arguments);
			}
		});
