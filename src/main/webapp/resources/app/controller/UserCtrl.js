Ext.define('KSI.controller.UserCtrl', {
			extend : 'Ext.app.Controller',
			stores : ['User'],

			models : ['User'],

			views : ['user.List'],

			init : function() {
				var self = this;
				this.control({
							'userlist' : {
								afterrender : function(cmp, opts) {
									self.getUserStore().load();
								}
							},
							'userlist button[id=userlist-query]' : {
								click : function(btn) {
									var txtName = btn.up("panel").down("textfield[name=txtName]");
									self.getUserStore().filter({
												id : 'username',
												property : 'EQ_username', 
												value : txtName.getValue()
											});
											
//									self.getUserStore().load({params:{search_EQ_username:txtName.getValue()}});
								}
							}
						});
			}
		});