'use strict';

function CalendarParentController($scope, $state, RestService) {

		$scope.show = function(id) {
			$state.go('^.show', {id: id});
		};

		$scope.load = function(id) {
			return RestService.get({id: id});
		};

		$scope.loadPage = function(pageNumber) {
			$scope.page = RestService.query();
		};

		$scope.save = function(entity) {

			RestService.save(entity, function(response) {
				$scope.$emit('event:resource.create', {'title': 'calendar.messages.resource.create.success.title', 'text': 'calendar.messages.resource.create.success.message'});
				$state.go('^.list');
			},
			function(error) {				
				$scope.$emit('event:resource.error', {'title': 'calendar.messages.resource.create.error.title', 'text': 'calendar.messages.resource.create.error.message'});
			});

		};

}

angular.module('onleaveApp')
	.controller('CalendarController', function($scope, $state, $stateParams, $injector, CalendarService) {

		$injector.invoke(CalendarParentController, this, {
	    	$scope: $scope,
	    	$state: $state,
	    	RestService: CalendarService
	  	});

		if(angular.isDefined($stateParams.id)) {
			$scope.calendar = $scope.load($stateParams.id);
		} else {
			$scope.calendar = {};
			$scope.loadPage(0);					
		}

		$scope.create = function() {			
			$scope.save(_.clone($scope.calendar, true));
		};

		$scope.update = function() {
			$scope.save(_.clone($scope.calendar, true));
		};

	});