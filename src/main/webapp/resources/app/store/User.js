Ext.define('KSI.store.User', {
			extend : 'Ext.data.Store',
			model : 'KSI.model.User',
			autoLoad : false,
			remoteFilter : true,
			proxy : {
				type : 'ajax',
				api : {
					read : comm.get('ctx') + 'ext/user/list'
				},
				reader : {
					type : 'json',
					root : 'users',
					totalProperty : 'total',
					successProperty : 'success'
				}
			}
		});