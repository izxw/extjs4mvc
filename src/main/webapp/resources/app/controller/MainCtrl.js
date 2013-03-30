Ext.define('KSI.controller.MainCtrl', {
			extend : 'Ext.app.Controller',

			views : ['resource.MenuTree'],
			models : ['Resource'],
			stores : ['MenuTreeStore'],

			init : function() {
				var self = this;
				this.control({
							'viewport > menutree' : {
								itemclick : self.itemclick
							}
						});
			},
			
			itemclick : function(view, record, item, index, e, opts) {
				if (record.data.leaf) {
					var centertab = Ext.getCmp('centertab');

					var tab =centertab.getComponent('tab'+record.data.id);
					if (!tab) {
						var config = eval('(' + record.data.description + ')');
						tab = {
							id: 'tab'+record.data.id,
							title : record.data.text,
							xtype : config.xtype,
							closable : true,
							listeners:{
								close:function(tab){
									console.log(tab.id);
								}
							}
						};
						centertab.add(tab);
					} 
					centertab.setActiveTab(centertab.getComponent('tab'+record.data.id));
				}
			}
		
		});
