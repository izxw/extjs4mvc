Ext.define('KSI.controller.MainCtrl', {
			extend : 'Ext.app.Controller',
			mixins: {
				suppleUtil:"KSI.utils.SuppleUtil"
			},
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
				var operationDatas=self.ajax({url:comm.get("ctx")+"ext/operation",params:{}});
				if(operationDatas.success){
					comm.add("operations",operationDatas.operations);
				}
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
