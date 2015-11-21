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
	.controller('UserCreateController', function($scope, $state, managers, securityGroups) {

		$scope.managers = managers;
		$scope.securityGroups = securityGroups;
		$scope.user = {};		


	})
	.controller('UserShowController', function($scope, $stateParams, UserService){
		$scope.user = UserService.get({id: $stateParams.id});
	});