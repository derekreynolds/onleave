'use strict';

angular.module('onleaveApp')
	.controller('UserListController', function($scope, $state, users) {

		$scope.users = users;

		$scope.show = function(id) {
			$state.go('admin.user.show', {id: id});
		};

		$scope.create = function() {
			$state.go('admin.user.create');
		};

		$scope.decorateUsers = function() {

			_.each($scope.users.content, function(user) {				
				if(_.includes(user.roles, 'ROLE_SYS_ADMIN')) {
					user.roleCssClass = 'user-type-system-admin';
				} else if(_.includes(user.roles, 'ROLE_ADMIN')) {
					user.roleCssClass = 'user-type-admin';
				} else if(_.includes(user.roles, 'ROLE_MANAGER')) {
					user.roleCssClass = 'user-type-manager';
				} else {
					user.roleCssClass = 'user-type-user';
				}
			});
		};

		$scope.decorateUsers();

	})
	.controller('UserCreateController', function($scope, $state, UserService, managers, securityGroups) {

		$scope.managers = managers;
		$scope.securityGroups = securityGroups;
		$scope.user = {};		

		$scope.save = function() {

			UserService.save($scope.user, function(response) {
				$scope.$emit('event:resource.create', {'title': 'user.messages.resource.create.success.title', 'text': 'user.messages.resource.create.success.message'});
				$state.go('admin.user.list');
			},
			function(error) {
				debugger
				$scope.$emit('event:resource.error', {'title': 'user.messages.resource.create.error.title', 'text': 'user.messages.resource.create.error.message'});
			});

		}

	})
	.controller('UserShowController', function($scope, $stateParams, UserService){
		$scope.user = UserService.get({id: $stateParams.id});
	});