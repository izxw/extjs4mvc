Ext.define('KSI.store.OrgTreeStore', {
    extend: 'Ext.data.TreeStore',
    model: 'KSI.model.Org',
   	root: {
        name: 'Root',
        expanded: false
    },
     proxy: {
        type: 'ajax',
        api: {
            read: 'org/tree'
        } 
    }
   
});