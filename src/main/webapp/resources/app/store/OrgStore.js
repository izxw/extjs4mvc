Ext.define('KSI.store.OrgStore', {
    extend: 'Ext.data.Store',
    model: 'KSI.model.Org',
    autoLoad: false,
    proxy: {
        type: 'ajax',
        api: {
            read: 'org/list'
        },
        reader: {
            type: 'json',
            root: 'orgs',
            successProperty: 'success'
        }
    }
});