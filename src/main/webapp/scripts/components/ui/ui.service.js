'use strict';

angular.module('ui', [])
    .factory('formService', function($translate, $filter) {
        var factory = {}; 


        /**
         * Creates a Html label.
         *
         * @param {Object} attributes - The attributes of the input control.
         * @return {String} A Html <label> text.
        */
        factory.createLabel = function(attributes) {
           return '<label class="col-sm-2 control-label">' + $filter('translate')(this.getTranslationKey(attributes) + '.label') + '</label>';
        };

        /**
         * Creates an help.
         * 
         * @param  {Object} attributes - The attributes of the input control.
         * @return {String} A Html help block span input
         */
        factory.createHelp = function(attributes) {
            var helpMessage = $filter('translate')(attributes.entityName + '.form.' + attributes.name + '.help');
            return '<p class="help-block">' + helpMessage + '</p>';        
        };

        /**
         * Creates an text input.
         * 
         * @param  {Object} attributes - The attributes of the input control.
         * @return {String} A Html text input
         */
        factory.createTextInput = function(attributes) {
            return this.createInput(attributes, 'text');        
        };

        /**
         * Creates an text area input.
         * 
         * @param  {Object} attributes - The attributes of the input control.
         * @return {String} A Html text input
         */
        factory.createTextAreaInput = function(attributes) {
            var input =  '<textarea class="form-control" ng-model="model"';
                input += ' placeholder="' + this.getPlaceHolder(attributes) + '"';
                
                angular.forEach(attributes, function(value, key) {
                    // skip these
                    if((key.lastIndexOf('$', 0) === 0) ) 
                        return true;                
                    
                    if(typeof attributes.$attr[key] !== 'undefined') 
                      input += attributes.$attr[key] + '="' + value + '" ';                   
                });
                input += '></textarea>'; 

           return input;       
        };

        /**
         * Create a password input.
         * 
         * @param  {Object} attributes - The attributes of the input control.
         * @return {String} A Html password input
         */
        factory.createPasswordInput = function(attributes) {
            var decoratedInput = '<div class="input-group">' +
                                '<div class="input-group-addon"><i class="fa fa-lock"></i></div>' +
                                this.createInput(attributes, 'password') +
                                '</div>';
            return decoratedInput;        
        };

        /**
         * Create a number input.
         * 
         * @param  {Object} attributes - The attributes of the input control.
         * @return {String} A Html number input
         */
        factory.createNumberInput = function(attributes) {
            return this.createInput(attributes, 'number');        
        };

        /**
         * Creates a input for the type.
         *
         * @param {Object} attributes - The attributes of the input control.
         * @return {String} A Html <label> text.
        */
        factory.createInput = function(attributes, type) {
            attributes.type = type; 
            var input =  '<div class="col-sm-10">';
                input +=  '<input type="' + type + '" class="form-control" ng-model="model"';
                input += ' placeholder="' + this.getPlaceHolder(attributes) + '"';
                angular.forEach(attributes, function(value, key) {
                // skip these
                if((key.lastIndexOf('$', 0) === 0) ) 
                    return true;                
                
                if(typeof attributes.$attr[key] !== 'undefined') 
                  input += attributes.$attr[key] + '="' + value + '" ';                   
                });
                input += '/>';
                input += factory.createHelp(attributes);
                input += '</div>'; 
                input += '<span class="material-input"></span>';
           return input;
        };


        /**
         * Creates a Date Time input.
         *
         * @param {Object} attributes - The attributes of the input control.
         * @return {String} A Html <label> text.
        */
        factory.createDateTimeInput = function(attributes) {
            var input =  '<div class="col-md-6">';                
                input += '<div class="row">'; 
                input += '<div class="col-xs-8">';                      
                input += this.createLabel(attributes);
                input += '<span class="input-group voffset2">';
                input += '<input type="text" class="form-control" ng-model="' + attributes.dateModel + '"';
                input += ' is-open="dateDialogOpened" datepicker-popup="yyyy-MM-dd"';

                if(angular.isDefined(attributes.dateChange))
                    input += ' ng-change="' + attributes.dateChange + '" ';
                if(angular.isDefined(attributes.minDate))
                    input += ' min-date="' + attributes.minDate + '" ';
                if(angular.isDefined(attributes.maxDate))
                    input += ' max-date="' + attributes.maxDate + '" ';
                if(angular.isDefined(attributes.datepickerOptions))
                    input += ' datepicker-options="' + attributes.datepickerOptions + '" ';
                if(angular.isDefined(attributes.dateDisabled))
                    input += ' date-disabled="' + attributes.dateDisabled + '" ';
                if(angular.isDefined(attributes.ngRequired))
                    input += ' ng-required="' + attributes.ngRequired + '" ';
                if(angular.isDefined(attributes.datepickerPopup))
                    input += ' datepicker-popup="' + attributes.datepickerPopup + '" ';

                input += 'close-text="Close" ng-readonly="true"/>';
                input += '<span class="input-group-btn">';
                input += '<button type="button" class="btn btn-default" ng-click="openDateDialog($event)"><i class="fa fa-calendar"></i></button>';                     
                input += '</span>';
                input += '</span>';  
                                      
                input += '</div>';
                
                input += '<div class="col-xs-4">';
                input += '<timepicker ng-model="' + attributes.timeModel + '"';
                if(angular.isDefined(attributes.timeChange))
                    input += ' ng-change="' + attributes.timeChange + '" ';
                input += ' hour-step="hstep" minute-step="mstep" show-meridian="false"></timepicker>';
                input += '</div>';
                input += '</div>';      
         
           return input;
        };

        /**
         * Creates a textarea for the type.
         *
         * @param {Object} attributes - The attributes of the textarea control.
         * @return {String} A Html <label> text.
        */
        factory.createStartEndDateTimeInput = function(attributes) {           

            var model = this.getModelKey(attributes);

            var tempAttributes = {
                entityName: model.substring(0, model.indexOf('.')),
                name: 'timeBox.startDate'
            };

            var input = '<div class="row">';
                input +=  '<div class="col-md-6">';                
                input += '<div class="row">'; 
                input += '<div class="col-xs-8">';                      
                input += this.createLabel(tempAttributes);
                input += '<span class="input-group voffset2">';
                input += '<input type="text" class="form-control" ng-model="' + model + '.startDate"';
                input += ' is-open="startDateDialogOpened" datepicker-popup="yyyy-MM-dd"';
                input += ' ng-change="startDateChange()" ';

                if(angular.isDefined(attributes.minDate))
                    input += ' min-date="\'' + attributes.minDate + '\'" ';
                if(angular.isDefined(attributes.maxDate))
                    input += ' max-date="\'' + attributes.maxDate + '\'" ';
                if(angular.isDefined(attributes.datepickerOptions))
                    input += ' datepicker-options="' + attributes.datepickerOptions + '" ';
                if(angular.isDefined(attributes.dateDisabled))
                    input += ' date-disabled="' + attributes.dateDisabled + '" ';
                if(angular.isDefined(attributes.ngRequired))
                    input += ' ng-required="' + attributes.ngRequired + '" ';
                if(angular.isDefined(attributes.datepickerPopup))
                    input += ' datepicker-popup="' + attributes.datepickerPopup + '" ';

                input += 'show-button-bar="false" ng-readonly="true"/>';
                input += '<span class="input-group-btn">';
                input += '<button type="button" class="btn btn-default" ng-click="openStartDateDialog($event)"><i class="fa fa-calendar"></i></button>';                     
                input += '</span>';
                input += '</span>';  
                                      
                input += '</div>';
                
                input += '<div class="col-xs-4">';
                input += '<timepicker ng-model="' + model + '.startTime"';           
                input += ' ng-change="startTimeChange()" ';
                input += ' hour-step="hstep" minute-step="mstep" show-meridian="false"></timepicker>';
                input += '</div>';
                input += '</div>';
                input += '</div>';

                tempAttributes.name ='timeBox.endDate';

                input +=  '<div class="col-md-6">';                
                input += '<div class="row">'; 
                input += '<div class="col-xs-8">';                      
                input += this.createLabel(tempAttributes);
                input += '<span class="input-group voffset2">';
                input += '<input type="text" class="form-control" ng-model="' + model + '.endDate"';
                input += ' is-open="endDateDialogOpened" datepicker-popup="yyyy-MM-dd"';                    
                input += ' ng-change="endDateChange()" ';

                if(angular.isDefined(attributes.minDate))
                    input += ' min-date="\'' + attributes.minDate + '\'" ';
                if(angular.isDefined(attributes.maxDate))
                    input += ' max-date="\'' + attributes.maxDate + '\'" ';
                if(angular.isDefined(attributes.datepickerOptions))
                    input += ' datepicker-options="' + attributes.datepickerOptions + '" ';
                if(angular.isDefined(attributes.dateDisabled))
                    input += ' date-disabled="' + attributes.dateDisabled + '" ';
                if(angular.isDefined(attributes.ngRequired))
                    input += ' ng-required="' + attributes.ngRequired + '" ';
                if(angular.isDefined(attributes.datepickerPopup))
                    input += ' datepicker-popup="' + attributes.datepickerPopup + '" ';

                input += 'show-button-bar="false" ng-readonly="true"/>';
                input += '<span class="input-group-btn">';
                input += '<button type="button" class="btn btn-default" ng-click="openEndDateDialog($event)"><i class="fa fa-calendar"></i></button>';                     
                input += '</span>';
                input += '</span>';  
                                      
                input += '</div>';
                
                input += '<div class="col-xs-4">';
                input += '<timepicker ng-model="' + model + '.endTime"';
                input += ' ng-change="endTimeChange($event)" ';
                input += ' hour-step="hstep" minute-step="mstep" show-meridian="false"></timepicker>';
                input += '</div>';
                input += '</div>';

                input += '</div>';      
         
           return input;

     
        };           

        /**
         * Creates an error message output area.
         * 
         * @param  {Object} attributes - The attributes of the input control.
         * @return {String} A Html error div
         */
        factory.createError = function(attributes) { 

            var translationKey =  this.getTranslationKey(attributes); 

            var error = '<div class="col-sm-offset-2 has-error" ng-show="form.'; 
            error += attributes.name + '.$dirty && form.';
            error += attributes.name + '.$invalid">'; 

            if(attributes.type === 'email') {
                var errorMessage = this.getTranslation(translationKey + '.validate.email');                       
                error += '<div class="error" ng-message="email">' + errorMessage + '</div>';  
            }          

            angular.forEach(attributes, function(value, key) {
                if((key.lastIndexOf('ng', 0) === 0) && (key.lastIndexOf('ngModel', 0) !== 0)) {
                    var constraint = key.substr(2, key.length).toLowerCase();
                    var errorMessage = $filter('translate')(attributes.entityName + '.form.' + attributes.name + '.validate.' + constraint);
                    error += '<p class="help-block" ng-show="form.';
                    error += attributes.name + '.$error.' + constraint + '">' + errorMessage + '</p>';
                }                         
            });
            error += '</div>';
            return error;
        };

        /**
         * Creates a switch input.
         *
         * @param Object attributes - The attributes of the input control.
         * @return String a Switch input text.
        */
        factory.createSwitchInput = function(attributes) {
            
            var tooltip = this.getTooltip(attributes);
            var input =  '<div class="switch">';                    
                input += '<i id="switcher" class="fa fa-2x fa-fw" ng-class="model.selected ? \'fa-toggle-on\': \'fa-toggle-off\'"';
                if(tooltip !== '') {
                    input += 'tooltip-placement="right" tooltip="' + tooltip + '"';
                }
                input += 'ng-click="model.selected = !model.selected"></i>';                
                input += '</div>'; 

           return input;
        };

        /**
         * Creates a binary option input.
         *
         * @param Object attributes - The attributes of the input control.
         * @return String a Switch input text.
        */
        factory.createBinaryOptionInput = function(attributes) {
            var translationKey =  this.getTranslationKey(attributes); 
            var tooltip = this.getTooltip(attributes);
            var input =  '<div class="binary row">';
                input += '<div class="col-md-2">';
                input += '<button type="button" class="btn" ng-class="model ? \'btn-primary\' : \'btn-default\'" ng-click="toggle()"' ; 
                if(tooltip !== '') {
                    input += 'tooltip-placement="right" tooltip="' + tooltip + '"';
                }   
                input += '>';
                input += '</button>';
                input += '</div>';
                input += '<div class="col-md-10">';
                input += this.getTranslation(translationKey + '.info');
                input += '</div>';               
                input += '</div>'; 

           return input;
        };

        /**
         * Creates a Html text display.
         * 
         * @param  {Object} attributes - The attributes of the input control.
         * @return {String} A Html text displays
         */
        factory.createTextDisplay = function(attributes) { 
            var entityField = attributes.entityName + '.' + attributes.name;
            var textDisplay = '<p class="col-xs-12"><strong>' + this.getTranslation(this.getTranslationKey(attributes) + '.label') + '</strong></p>'            
            textDisplay += '<p class="col-xs-12">{{' + entityField+ '}}</p>';                        
            return textDisplay;            
        };

        /**
         * Creates a Html info display.
         * 
         * @param  {Object} attributes - The attributes of the input control.
         * @return {String} A Html text display
         */
        factory.createInfoDisplay = function(attributes) { 
            
            var translationKey = 'global.anonymous.' + (attributes.model ? 'true' : 'false');
            var textDisplay = '<p class="col-xs-12"><div class="alert" ng-class="$model ? \'alert-info\' : \'alert-warning\'" role="alert">' 
            textDisplay += this.getTranslation(translationKey) + '</div></p>';       
                        
            return textDisplay;            
        };

        /**
         * Translate the key. Sets to the default if the key isn't 
         * found in the translated files.
         * @param  String key                The key to look up.
         * @param  String defaultTranslation The default to use if the key isn't found. Defaults to ''.
         * @return String                   The translated text.
         */
        factory.getTranslation = function(key, placeholders, defaultTranslation) {
            defaultTranslation = defaultTranslation || '';
            var translation = $filter('translate')(key, placeholders);

            if(translation === key) {
                translation = defaultTranslation;
            }

            return translation;
        };

        /**
         * Builds the translation key based on the attributes.
         * 
         * @param  {Object} attributes - The attributes to build the key from.
         * @return {String} 
         */
        factory.getTranslationKey = function(attributes) {
            if('translationKey' in attributes)
                return attributes.translationKey;

            return attributes.entityName + '.form.' + attributes.name;
        };

        /**
         * Returns the entity name from the attributes
         * @param  Object attributes associated with the directive.
         * @return String entity name
         */
        factory.getEntityName = function(attributes) {
            return attributes.entityName;
        }

        /**
         * Returns the input name from the attributes
         * @param  Object attributes associated with the directive.
         * @return String input name
         */
        factory.getName = function(attributes) {
            return attributes.name;
        }

        /**
         * Builds the translation key based on the attributes.
         * 
         * @param  {Object} attributes - The attributes to build the key from.
         * @return {String} 
         */
        factory.getModelKey = function(attributes) {
            return this.getEntityName(attributes) + "." + this.getName(attributes);
        };

        /**
         * Builds the placeholder text.
         * 
         * @param  {Object} attributes - The attributes to build the key from.
         * @return {String} 
         */
        factory.getPlaceHolder = function(attributes) {
            return this.getTranslation(this.getTranslationKey(attributes) + '.placeholder');
        };

        /**
         * Builds the tooltip text.
         * 
         * @param  {Object} attributes - The attributes to build the key from.
         * @return {String} 
         */
        factory.getTooltip = function(attributes) {
            return this.getTranslation(this.getTranslationKey(attributes) + '.tooltip');
        };

        factory.extractAttributesFromModel = function(attributes) {
            
            if(!_.isUndefined(attributes.model)) { 
                var model = attributes.model;           
                attributes['entityName'] = model.substring(0, model.indexOf('.'));
                attributes['name'] = model.substring(model.indexOf('.') + 1, model.length);
            } else if(!_.isUndefined(attributes.dateModel)) {
                var model = attributes.dateModel;
                attributes['entityName'] = model.substring(0, model.indexOf('.'));
                attributes['name'] = model.substring(model.indexOf('.') + 1, model.length);
            }
        };

    return factory;
    });
