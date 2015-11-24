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
	.controller('UserCreateController', function($scope, $state, SelectOptionService, UserService, managers, securityGroups) {

		$scope.user = {};

		managers.$promise.then(function(response) {
			$scope.managers = response;
			$scope.user.manager = response[0];
		});

		securityGroups.$promise.then(function(response) {
			$scope.securityGroups = response;
			$scope.user.securityGroup = response[0];
		});
				

		$scope.save = function() {

			var cloneUser = _.clone($scope.user, true);

			delete cloneUser.confirmPassword;

			SelectOptionService.setOptionId(cloneUser, 'manager');
			SelectOptionService.setOptionId(cloneUser, 'securityGroup');

			UserService.save(cloneUser, function(response) {
				$scope.$emit('event:resource.create', {'title': 'user.messages.resource.create.success.title', 'text': 'user.messages.resource.create.success.message'});
				$state.go('admin.user.list');
			},
			function(error) {				
				$scope.$emit('event:resource.error', {'title': 'user.messages.resource.create.error.title', 'text': 'user.messages.resource.create.error.message'});
			});

		}

	})
	.controller('UserShowController', function($scope, $stateParams, UserService){
		$scope.user = UserService.get({id: $stateParams.id});
	});