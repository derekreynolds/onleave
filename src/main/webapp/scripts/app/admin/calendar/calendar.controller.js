'use strict';

function CalendarParentController($scope, $state, CalendarService) {

		$scope.show = function(id) {
			$state.go('admin.calendar.show', {id: id});
		};

		$scope.load = function(id) {
			return CalendarService.get({id: id});
		};

		$scope.create = function() {
			$state.go('admin.calendar.create');
		};

		$scope.save = function() {

			var cloneCalendar = _.clone($scope.calendar, true);
			
			CalendarService.save(cloneCalendar, function(response) {
				$scope.$emit('event:resource.create', {'title': 'calendar.messages.resource.create.success.title', 'text': 'calendar.messages.resource.create.success.message'});
				$state.go('admin.calendar.list');
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
	    	CalendarService: CalendarService
	  	});

		if(angular.isDefined($stateParams.id)) {
			$scope.calendar = $scope.load($stateParams.id);
		} else {
			$scope.calendars = CalendarService.query();		
		}

	});