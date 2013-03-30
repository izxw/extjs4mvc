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
							'userlist button' : {
								click : function(btn) {
									alert(btn.text);
								}
							}
				});

			}
		});