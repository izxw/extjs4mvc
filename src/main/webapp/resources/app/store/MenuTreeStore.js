Ext.define('KSI.store.MenuTreeStore', {
    extend: 'Ext.data.TreeStore',
    model: 'KSI.model.Resource',
    proxy: {
        type: 'ajax',
        api: {
            read: comm.get("ctx")+'ext/menu/tree'
        } 
    }
   
});