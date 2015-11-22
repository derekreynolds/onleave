/*jshint bitwise: false*/
'use strict';

angular.module('onleaveApp')  
	.factory('ToasterService', ['$rootScope', '$filter', 
    function ($rootScope, $filter) {
        var factory = {}; 
 
 		/**
 		 * Initialises the toaster service, registers a number of events
 		 * (create, update, remove, read, error). On receiving one of these
 		 * events a toaster message will appear.
 		 * @return none
 		 */
        factory.initialise = function() {
			$rootScope.$on('event:resource.create', function(event, message) {				
				factory.displaySuccess(factory.translateMessage(message));
			});
			$rootScope.$on('event:resource.update', function(event, message) { 				
				factory.displaySuccess(factory.translateMessage(message));
			});
			$rootScope.$on('event:resource.remove', function(event, message) { 				
				factory.displaySuccess(factory.translateMessage(message));
			});
			$rootScope.$on('event:resource.read', function(event, message) { 				
				factory.displaySuccess(factory.translateMessage(message));
			});
			$rootScope.$on('event:resource.error', function(event, message) { 				
				factory.displayError(factory.translateMessage(message));
			});	
			$rootScope.$on('event:user.info', function(event, message) { 
				factory.displayInfo(factory.translateMessage(message));
			});	
			$rootScope.$on('event:user.error', function(event, message) { 
				factory.displayError(factory.translateMessage(message));
			});		
			$rootScope.$on('event:user.warn', function(event, message) { 
				factory.displayWarning(factory.translateMessage(message));
			});	
			$rootScope.$on('event:login.error', function(event, message) { 				
				factory.displayError(factory.translateMessage(message));
			});
        };

	    factory.displaySuccess = function(message) {

	    	if(message.title)
	        	toastr.success(message.text, message.title);
	        else
	        	toastr.success(message.text);
	    };
	 
	    factory.displayInfo = function(message) {

	        if(message.title)
	        	toastr.info(message.text, message.title);
	        else
	        	toastr.info(message.text);
	    };

	    factory.displayWarning = function(message) {

	        if(message.title)
	        	toastr.warn(message.text, message.title);
	        else
	        	toastr.warn(message.text);
	    };
	 
	    factory.displayError = function(message) {	        

	        if(message.title)
	        	toastr.error(message.text, message.title);
	        else
	        	toastr.error(message.text);
	    };
	 
	    factory.translateMessage = function(message) {
	    	if(message.title)
	    		message.title = $filter('translate')(message.title);
	    	
	    	message.text = $filter('translate')(message.text);

	    	return message;
	    };

	    return factory;
    }]);