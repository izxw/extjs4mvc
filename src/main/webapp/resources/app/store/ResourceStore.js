Ext.define('KSI.store.ResourceStore', {
    extend: 'Ext.data.Store',
    model: 'KSI.model.Resource',
    autoLoad: false,
    proxy: {
        type: 'ajax',
        api: {
            read: 'resource/list'
        },
        reader: {
            type: 'json',
            root: 'resources',
            successProperty: 'success'
        }
    }
});