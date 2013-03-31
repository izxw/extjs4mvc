Ext.define('KSI.utils.AccessRule', {
			extend : 'Ext.util.Observable',

			alternateClassName : 'KSI.ux.AccessRule',
			alias : 'plugin.accessrule',

			requires : ['Ext.Function', 'Ext.util.Observable'],

			init : function(control) {
				var self = this;
			 
				Ext.applyIf(this, {
							featureName : '',
							byPass : false,
							allowDD : false,
							allowDbClick : false,
							disableAll : false,
							readOnly : true,
							model : 'user',
							hidden:false,
							disabled:false
				});
				
				/**
				 * 权限验证
				 */
				function checkAccess(){
					//var array = comm.get(self.model);
					var operations = comm.get("operations");
					 
					Ext.Array.every(operations,function(operation,index,operations){
						operation = Ext.decode(operation);
						if(operation.featureName == self.featureName){
							if(operation.status=="hidden"){
								self.hidden = Ext.decode( operation.value);
							}else if(operation.status=="disabled"){
								self.disabled = Ext.decode( operation.value);
							}
							
							return false;
						}
						return true;
					});
				}
				
				checkAccess();
				
				if(control.isXType('button')){
					var cfg = {
			          disabled:self.disabled,
			          hidden:self.hidden
			        };
		
		        	Ext.apply(control, cfg);
				 };
				 
				
			}
			
			
		});
