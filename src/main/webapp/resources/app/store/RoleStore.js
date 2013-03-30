Ext.define('KSI.store.RoleStore', {
    extend: 'Ext.data.Store',
    model: 'KSI.model.Role',
    autoLoad: false,
    proxy: {
        type: 'ajax',
        api: {
            read: 'role/list'
        },
        reader: {
            type: 'json',
            root: 'roles',
            successProperty: 'success'
        }
    }
});