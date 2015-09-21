/* globals $ */
'use strict';

angular.module('ui')
    .directive('showValidation', function() {
        return {
            restrict: 'A',
            require: 'form',
            link: function (scope, element) {
                element.find('.form-group').each(function() {
                    var $formGroup = $(this);
                    var $inputs = $formGroup.find('input[ng-model],textarea[ng-model],select[ng-model]');

                    if ($inputs.length > 0) {
                        $inputs.each(function() {
                            var $input = $(this);
                            scope.$watch(function() {
                                return $input.hasClass('ng-invalid') && $input.hasClass('ng-dirty');
                            }, function(isInvalid) {
                                $formGroup.toggleClass('has-error', isInvalid);
                            });
                        });
                    }
                });
            }
        };
    })
    .directive('formView', function() {
        return {
            replace: true,
            transclude: true,
            restrict: 'E',
            scope: {
                entityName: '@',
                action:'@'
            },
            templateUrl: '/scripts/components/ui/form.view.html'
        };
    })
    .directive('formGroup', function(formService) {           
        return {
            replace: true,
            transclude: true,
            restrict: 'E',
            templateUrl: '/scripts/components/ui/form.control.html'
        };
    })
    .directive('textInput', function(formService) {
        return {
            restrict: 'E',
            replace: true,
            require: '^form',
            scope: {
                model: '='
            }, 
            templateUrl: '/scripts/components/ui/form.group.html',
            compile: function(element, attr) {                
                formService.extractAttributesFromModel(attr);
                element.append(formService.createLabel(attr));
                element.append(formService.createTextInput(attr));
                element.append(formService.createError(attr));
                return {
                  pre: function(scope, iElem, iAttrs){
                    
                  },
                  post: function(scope, iElem, iAttrs){
                   
                  }
                }
            }
        };
    })
    .directive('textAreaInput', function(formService) {
        return {
            restrict: 'E',
            replace: true,
            require: '^form', 
            scope: {
                model: '='
            },
            templateUrl: '/scripts/components/ui/form.group.html',
            compile: function(element, attr) {  
                formService.extractAttributesFromModel(attr);              
                if(_.isUndefined(attr.hideLabel)) {
                    element.append(formService.createLabel(attr));
                }
                element.append(formService.createTextAreaInput(attr));
                element.append(formService.createError(attr));
                return {
                  pre: function(scope, iElem, iAttrs){
                    
                  },
                  post: function(scope, iElem, iAttrs){
                   
                  }
                }
            }
        };
    })
    .directive('numberInput', function(formService) {
        return {
            restrict: 'E',
            replace: true,
            require: '^form',
            scope: {
                model: '='
            }, 
            templateUrl: '/scripts/components/ui/form.group.html',
            compile: function(element, attr) {
                formService.extractAttributesFromModel(attr);
                element.append(formService.createLabel(attr));
                element.append(formService.createNumberInput(attr));
                element.append(formService.createError(attr));
                return {
                  pre: function(scope, iElem, iAttrs){
                    
                  },
                  post: function(scope, iElem, iAttrs){
                   
                  }
                }
            }
        };
    })    
    .directive('startEndDateTimeInput', function(formService, DateTimeService) {
        return {
            restrict: 'E',
            replace: true,
            require: '^form',  
            compile: function(element, attr) {                
                formService.extractAttributesFromModel(attr);                
                element.append(formService.createStartEndDateTimeInput(attr));
                return {
                  pre: function(scope, iElem, iAttrs) {
                    var timeBox = scope[iAttrs.entityName][iAttrs.name]; 
                    scope.startDateDialogOpened = false;

                    scope.openStartDateDialog = function(event) { 
                        event.preventDefault();
                        event.stopPropagation();
                                    
                        scope.startDateDialogOpened = true;
                    }; 

                    scope.endDateDialogOpened = false;

                    scope.openEndDateDialog = function(event) { 
                        event.preventDefault();
                        event.stopPropagation();
                                    
                        scope.endDateDialogOpened = true;
                    };

                    scope.startDateChange = function() {                               
                           
                        var startDate = moment(timeBox.startDate);
                        var startTime = moment(timeBox.startTime);
                        var endDate = moment(timeBox.endDate);
                        var endTime = moment(timeBox.endTime);

                        if(startDate.isAfter(endDate)) {
                            timeBox.endDate = DateTimeService.formatDate(startDate.toDate());
                            timeBox.endTime = startTime.add(1, 'h').toDate();                
                        } else if(startDate.isSame(endDate) && (startTime.isAfter(endTime) 
                            || startTime.isSame(endTime))) {
                            timeBox.endTime = startTime.add(1, 'h').toDate();
                        } 
                        timeBox.startDate = DateTimeService.formatDate(startDate.toDate());        
                    };

                    scope.endDateChange = function() {
            
                        var startDate = moment(timeBox.startDate);
                        var startTime = moment(timeBox.startTime);
                        var endDate = moment(timeBox.endDate);
                        var endTime = moment(timeBox.endTime);

                        if(endDate.isBefore(startDate)) {
                            timeBox.endDate = DateTimeService.formatDate(startDate.toDate());
                            timeBox.endTime = startTime.add(1, 'h').toDate(); 
                        } else if(endDate.isSame(startDate) && (endTime.isAfter(startTime) 
                            || endTime.isSame(startTime))) {                
                            timeBox.endTime = startTime.add(1, 'h').toDate();
                        }
                        timeBox.endDate = DateTimeService.formatDate(endDate.toDate()); 
                    };

                    scope.startTimeChange = function() {
                        
                        var startDate = moment(timeBox.startDate);
                        var startTime = moment(timeBox.startTime);
                        var endDate = moment(timeBox.endDate);
                        var endTime = moment(timeBox.endTime);
                
                        if(endDate.isSame(startDate) && (DateTimeService.isMomentTimeAfter(startTime, endTime))
                            || (DateTimeService.isMomentTimeSame(startTime, endTime))) {
                            timeBox.endTime = startTime.add(1, 'h').toDate();
                        }
                    };

                    scope.endTimeChange = function() {
                        
                        var startDate = moment(timeBox.startDate);
                        var startTime = moment(timeBox.startTime);
                        var endDate = moment(timeBox.endDate);
                        var endTime = moment(timeBox.endTime);

                        if(endDate.isSame(startDate) && (DateTimeService.isMomentTimeAfter(startTime, endTime))
                            || (DateTimeService.isMomentTimeSame(startTime, endTime))) {
                            timeBox.endTime = startTime.add(1, 'h').toDate();
                        }
                    };   
                  },
                  post: function(scope, iElem, iAttrs) {
                    
                  }
                }
            }
        };
    })
    .directive('dateTimeInput', function(formService) {
        return {
            restrict: 'E',
            replace: true,
            scope: {
                dateModel: '=',
                timeModel: '=',
                dateChange: '&',
                timeChange: '&'
            },         
            compile: function(element, attr) {
                formService.extractAttributesFromModel(attr);                
                element.append(formService.createDateTimeInput(attr));               
                return {
                  pre: function(scope, iElem, iAttrs){ 
                    scope.dateDialogOpened = false;
                    scope.openDateDialog = function(event) { 
                        event.preventDefault();
                        event.stopPropagation();
                                    
                        scope.dateDialogOpened = true;
                    };                    
                  },
                  post: function(scope, iElem, iAttrs){                   
                  }
                }
            }
        };
    })
    .directive('dateInput', function(formService) {
        return {
            restrict: 'E',
            replace: true,
            require: '^form',
            controller: 'DateInputController',            
            compile: function(element, attr) {
                formService.extractAttributesFromModel(attr);
                element.append(formService.createLabel(attr));
                element.append(formService.createDateInput(attr));
                //element.append(formService.createError(attr));
                return {
                  pre: function(scope, iElem, iAttrs){
                    var dateDialogFunctionName = 'open' + iAttrs.name + 'DateDialog';
                    scope[dateDialogFunctionName] = function($event) {
                        $event.preventDefault();
                        $event.stopPropagation();

                        scope[iAttrs.name + 'Opened'] = true;
                    };
                  },
                  post: function(scope, iElem, iAttrs){
                   
                  }
                }
            }
        };
    })
    .directive('groupDisplay', function(formService) {
        return {            
            replace: true,
            restrict: 'E',
            transclude: true,
            template: '<div class="row" ng-transclude></div>'

        };
    })
    .directive('textDisplay',function(formService) {
        return {            
            replace: true,
            restrict: 'E',
            //templateUrl: '/views/partials/display-control.html',
            compile: function(element, attr) {                          
                element.append(formService.createTextDisplay(attr));
                return {
                  pre: function(scope, iElem, iAttrs){
                    
                  },
                  post: function(scope, iElem, iAttrs){
                   
                  }
                }               
            }          

        };
    })
    .directive('infoDisplay',function(formService) {
        return {            
            replace: true,
            restrict: 'E',
            //templateUrl: '/views/partials/display-control.html',
            compile: function(element, attr) {                          
                element.append(formService.createInfoDisplay(attr));
                return {
                  pre: function(scope, iElem, iAttrs){
                    
                  },
                  post: function(scope, iElem, iAttrs){
                   
                  }
                }               
            }          

        };
    })
    .directive('switchInput', function($compile, formService) {
        return {
            restrict: 'E',
            replace: true,
            require: '^form',
            scope: {
                model: '='
            },
            templateUrl: '/scripts/components/ui/form.group.html',
            compile: function(element, attr) {
                formService.extractAttributesFromModel(attr);
                element.append(formService.createLabel(attr));
                element.append(formService.createSwitchInput(attr));
                return {
                  pre: function(scope, iElem, iAttrs){
                   
                  
                  },
                  post: function(scope, iElem, iAttrs){
                   
                  }
                }
            }            
    
        };
    })
    .directive('binaryOptionInput', function($compile, $filter, formService) {
        return {
            restrict: 'E',
            replace: true,
            require: '^form',
            scope: {
                model: '='
            },
            templateUrl: '/scripts/components/ui/form.group.html',
            compile: function(element, attr) {  
                element.append(formService.createLabel(attr));
                element.append(formService.createBinaryOptionInput(attr));   
                return {
                  pre: function(scope, iElem, iAttrs){
                    scope.toggle = function() {
                        scope.model = !scope.model;
                        scope.setButtonText();
                    };
                    scope.setButtonText = function() {
                        var $button = iElem.find('button');
                        if(scope.model) {
                            $button.html($filter('translate')('global.form.on'));                            
                        } else {
                            $button.html($filter('translate')('global.form.off'));
                        }
                    };
                    scope.setButtonText();
                  },
                  post: function(scope, iElem, iAttrs){
                        
                  }
                }
            }            
    
        };
    });