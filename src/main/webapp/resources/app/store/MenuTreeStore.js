Ext.define('KSI.store.MenuTreeStore', {
    extend: 'Ext.data.TreeStore',
    model: 'KSI.model.Resource',
   	root: {
        name: 'Root',
        expanded: false
    },
     proxy: {
        type: 'ajax',
        api: {
            read: 'ext/menu/tree'
        } 
    }
   
});