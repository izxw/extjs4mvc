Ext.define('KSI.view.org.List' ,{
    extend: 'Ext.grid.Panel',
    alias : 'widget.orglist',
	selType: 'rowmodel',
    title : '组织机构列表',
    store: 'RoleStore',
   // Use a PagingGridScroller (this is interchangeable with a PagingToolbar)
    verticalScrollerType: 'paginggridscroller',
    // do not reset the scrollbar when the view refreshs
    invalidateScrollerOnRefresh: false,
    // infinite scrolling does not support selection
    disableSelection: true,
	features: [{ ftype: 'grouping' }],
	 plugins: [
        Ext.create('Ext.grid.plugin.RowEditing', {
            clicksToEdit: 1
        })
    ],
    columns: [
        {header: '机构名称',  dataIndex: 'name',  flex: 1,sortable:true, editor: 'textfield'},
        {header:'id',dataIndex:'id',flex:2,hideable:false}
    ],
    tbar:[{xtype:'button',text:'新增'}],
  	
     dockedItems: [{
        xtype: 'pagingtoolbar',
        store: 'OrgStore',   // same store GridPanel is using
        dock: 'bottom',
        displayInfo: true
    }]
});
