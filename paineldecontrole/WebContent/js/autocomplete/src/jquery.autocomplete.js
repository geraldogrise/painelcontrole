(function (factory) { 
    'use strict';
    if (typeof define === 'function' && define.amd) {
        // AMD. Register as an anonymous module.
        define(['jquery'], factory);
    } else {
        // Browser globals
        factory(jQuery);
    }
}(function ($) {
    'use strict';

    var
        utils = (function () {
            return {

                extend: function (target, source) {
                    return $.extend(target, source);
                },

                addEvent: function (element, eventType, handler) {
                    if (element.addEventListener) {
                        element.addEventListener(eventType, handler, false);
                    } else if (element.attachEvent) {
                        element.attachEvent('on' + eventType, handler);
                    } else {
                        throw new Error('Browser doesn\'t support addEventListener or attachEvent');
                    }
                },

                removeEvent: function (element, eventType, handler) {
                    if (element.removeEventListener) {
                        element.removeEventListener(eventType, handler, false);
                    } else if (element.detachEvent) {
                        element.detachEvent('on' + eventType, handler);
                    }
                },

                createNode: function (html) {
                    var div = document.createElement('div');
                    div.innerHTML = html;
                    return div.firstChild;
                }

            };
        }()),

        keys = {
            ESC: 27,
            TAB: 9,
            RETURN: 13,
			ESQUERDA:37,
            UP: 38,
			DIREITA:39,
			DOWN: 40,
			APAGAR:8,
			SHIFTE:16,
			CTRL:17,
			ALT:18,
			FIXA:20,
			BT93:93,
			BT91:91,
			
        };

    function Autocomplete(el, options) {
        var noop = function () { },
            that = this,
            defaults = {
                serviceUrl: null,
                lookup: null,
                onSelect: null,
                width: 'auto',
                minChars: 1,
                maxHeight: 300,
                deferRequestBy: 0,
                params: {},
                formatResult: Autocomplete.formatResult,
                delimiter: null,
                zIndex: 9999,
                type: 'GET',
                noCache: false,
                onSearchStart: noop,
                onSearchComplete: noop,
                containerClass: 'autocomplete-suggestions',
                tabDisabled: false,
                lookupFilter: function (suggestion, originalQuery, queryLowerCase) {
                    return suggestion.value.toLowerCase().indexOf(queryLowerCase) !== -1;
                },
                paramName: 'query',
                transformResult: function(response) {
                    return response.suggestions;
                }
            };

        // Shared variables:
        that.element = el;
        that.el = $(el);
        that.suggestions = [];
        that.badQueries = [];
        that.selectedIndex = -1;
        that.currentValue = that.element.value;
        that.intervalId = 0;
        that.cachedResponse = [];
        that.onChangeInterval = null;
        that.onChange = null;
        that.ignoreValueChange = false;
        that.isLocal = false;
        that.suggestionsContainer = null;
        that.options = defaults;
        that.classes = {
            selected: 'autocomplete-selected',
            suggestion: 'autocomplete-suggestion'
        };

        // Initialize and set options:
        that.initialize();
        that.setOptions(options);
    }

    Autocomplete.utils = utils;

    $.Autocomplete = Autocomplete;

    Autocomplete.formatResult = function (suggestion, currentValue) {
        var reEscape = new RegExp('(\\' + ['/', '.', '*', '+', '?', '|', '(', ')', '[', ']', '{', '}', '\\'].join('|\\') + ')', 'g'),
            pattern = '(' + currentValue.replace(reEscape, '\\$1') + ')';

        return suggestion.value.replace(new RegExp(pattern, 'gi'), '<strong>$1<\/strong>');
    };

    Autocomplete.prototype = {

        killerFn: null,

        initialize: function () {
            var that = this,
                suggestionSelector = '.' + that.classes.suggestion,
                container;

            // Remove autocomplete attribute to prevent native suggestions:
            that.element.setAttribute('autocomplete', 'off');

            that.killerFn = function (e) {
                if ($(e.target).closest('.' + that.options.containerClass).length === 0) {
                    that.killSuggestions();
                    that.disableKillerFn();
                }
            };

            // Determine suggestions width:
            if (!that.options.width || that.options.width === 'auto') {
                that.options.width = that.el.outerWidth();
            }

            that.suggestionsContainer = Autocomplete.utils.createNode('<div class="' + that.options.containerClass + '" style="position: absolute; display: none;"></div>');

            container = $(that.suggestionsContainer);

            container.appendTo('body').width(that.options.width);

            // Listen for mouse over event on suggestions list:
            container.on('mouseover', suggestionSelector, function () {
                that.activate($(this).data('index'));
            });

            // Listen for click event on suggestions list:
            container.on('click', suggestionSelector, function () {
                that.select($(this).data('index'));
            });

            that.fixPosition();

            // Opera does not like keydown:
            if (window.opera) {
                that.el.on('keypress', function (e) { that.onKeyPress(e); });
            } else {
                that.el.on('keydown', function (e) { that.onKeyPress(e); });
            }

            that.el.on('keyup', function (e) { that.onKeyUp(e); });
            that.el.on('blur', function () { that.onBlur(); });
            that.el.on('focus', function () { that.fixPosition(); });
        },

        onBlur: function () {
            this.enableKillerFn();
        },

        setOptions: function (suppliedOptions) {
            var that = this,
                options = that.options;

            utils.extend(options, suppliedOptions);

            that.isLocal = $.isArray(options.lookup);

            if (that.isLocal) {
                options.lookup = that.verifySuggestionsFormat(options.lookup);
            }

            // Adjust height, width and z-index:
            $(that.suggestionsContainer).css({
                'max-height': options.maxHeight + 'px',
                'width': options.width + 'px',
                'z-index': options.zIndex
            });
        },

        clearCache: function () {
            this.cachedResponse = [];
            this.badQueries = [];
        },

        disable: function () {
            this.disabled = true;
        },

        enable: function () {
            this.disabled = false;
        },

        fixPosition: function () {
            var offset = this.el.offset();
            $(this.suggestionsContainer).css({
                top: (offset.top + this.el.outerHeight()) + 'px',
                left: offset.left + 'px'
            });
        },

        enableKillerFn: function () {
            var that = this;
            $(document).on('click', that.killerFn);
        },

        disableKillerFn: function () {
            var that = this;
            $(document).off('click', that.killerFn);
        },

        killSuggestions: function () {
            var that = this;
            that.stopKillSuggestions();
            that.intervalId = window.setInterval(function () {
                that.hide();
                that.stopKillSuggestions();
            }, 300);
        },

        stopKillSuggestions: function () {
            window.clearInterval(this.intervalId);
        },

        onKeyPress: function (e) {
            var that = this;

            // If suggestions are hidden and user presses arrow down, display suggestions:
            if (!that.disabled && !that.visible && e.keyCode === keys.DOWN && that.currentValue) {
                that.suggest();
                return;
            }

            if (that.disabled || !that.visible) {
                return;
            }

            switch (e.keyCode) {
                case keys.ESC:
                    that.el.val(that.currentValue);
                    that.hide();
                    break;
                case keys.TAB:
                case keys.RETURN:
                    if (that.selectedIndex === -1) {
                        that.hide();
                        return;
                    }
                    that.select(that.selectedIndex);
                    if (e.keyCode === keys.TAB && this.options.tabDisabled === false) {
                        return;
                    }
                    break;
                case keys.UP:
                    that.moveUp();
                    break;
                case keys.DOWN:
                    that.moveDown();
                    break;
                default:
                    return;
            }

            // Cancel event if function did not return:
            e.stopImmediatePropagation();
            e.preventDefault();
        },

        onKeyUp: function (e) {
            var that = this;

            if (that.disabled) {
                return;
            }

            switch (e.keyCode) {
                case keys.UP:
                case keys.DOWN:
                    return;
            }

            clearInterval(that.onChangeInterval);

            if (that.currentValue !== that.el.val()) {
                if (that.options.deferRequestBy > 0) {
                    // Defer lookup in case when value changes very quickly:
                    that.onChangeInterval = setInterval(function () {
                        that.onValueChange();
                    }, that.options.deferRequestBy);
                } else {
                    that.onValueChange();
                }
            }
        },

        onValueChange: function () {
            var that = this,
                q;
				

            clearInterval(that.onChangeInterval);
            that.currentValue = that.element.value;

            q = that.getQuery(that.currentValue);
            that.selectedIndex = -1;

            if (that.ignoreValueChange) {
                that.ignoreValueChange = false;
                return;
            }

            if (q === '' || q.length < that.options.minChars) {
                that.hide();
            } else {
                that.getSuggestions(q);
				
            }
        },

        getQuery: function (value) {
            var delimiter = this.options.delimiter,
                parts;

            if (!delimiter) {
                return $.trim(value);
            }
            parts = value.split(delimiter);
            return $.trim(parts[parts.length - 1]);
        },

        getSuggestionsLocal: function (query) {
            var that = this,
                queryLowerCase = query.toLowerCase(),
                filter = that.options.lookupFilter;

            return {
                suggestions: $.grep(that.options.lookup, function (suggestion) {
                    return filter(suggestion, query, queryLowerCase);
                })
            };
        },

        getSuggestions: function (q) {
            var response,
                that = this,
                options = that.options;

            response = that.isLocal ? that.getSuggestionsLocal(q) : that.cachedResponse[q];

            if (response && $.isArray(response.suggestions)) {
                that.suggestions = response.suggestions;
                that.suggest();
            } else if (!that.isBadQuery(q)) {
                options.onSearchStart.call(that.element, q);
                options.params[options.paramName] = q;
                $.ajax({
                    url: options.serviceUrl,
                    data: options.params,
                    type: options.type,
                    dataType: 'text'
                }).done(function (txt) {
                    that.processResponse(txt);
                    options.onSearchComplete.call(that.element, q);
                });
            }
        },

        isBadQuery: function (q) {
            var badQueries = this.badQueries,
                i = badQueries.length;

            while (i--) {
                if (q.indexOf(badQueries[i]) === 0) {
                    return true;
                }
            }

            return false;
        },

        hide: function () {
            var that = this;
            that.visible = false;
            that.selectedIndex = -1;
            $(that.suggestionsContainer).hide();
        },

        suggest: function () {
            if (this.suggestions.length === 0) {
                this.hide();
                return;
            }

            var that = this,
                formatResult = that.options.formatResult,
                value = that.getQuery(that.currentValue),
                className = that.classes.suggestion,
                classSelected = that.classes.selected,
                container = $(that.suggestionsContainer),
                html = '';

            // Build suggestions inner HTML:
            $.each(that.suggestions, function (i, suggestion) {
                html += '<div class="' + className + '" data-index="' + i + '"><input type="hidden" value='+suggestion.data+' />' + formatResult(suggestion, value) + '</div>';
            });

            container.html(html).show();
            that.visible = true;

            // Select first value by default:
            that.selectedIndex = 0;
            container.children().first().addClass(classSelected);
			
        },

        verifySuggestionsFormat: function (suggestions) {
            // If suggestions is string array, convert them to supported format:
            if (suggestions.length && typeof suggestions[0] === 'string') {
                return $.map(suggestions, function (value) {
                    return { value: value, data: null };
                });
            }

            return suggestions;
        },

        processResponse: function (text) {
            var that = this,
                response = $.parseJSON(text);

            response.suggestions = that.verifySuggestionsFormat(that.options.transformResult(response));

            // Cache results if cache is not disabled:
            if (!that.options.noCache) {
                that.cachedResponse[response[that.options.paramName]] = response;
                if (response.suggestions.length === 0) {
                    that.badQueries.push(response[that.options.paramName]);
                }
            }

            // Display suggestions only if returned query matches current value:
            if (response[that.options.paramName] === that.getQuery(that.currentValue)) {
                that.suggestions = response.suggestions;
                that.suggest();
            }
        },

        activate: function (index) {
            var that = this,
                activeItem,
                selected = that.classes.selected,
                container = $(that.suggestionsContainer),
                children = container.children();

            container.children('.' + selected).removeClass(selected);

            that.selectedIndex = index;

            if (that.selectedIndex !== -1 && children.length > that.selectedIndex) {
                activeItem = children.get(that.selectedIndex);
                $(activeItem).addClass(selected);
				$("#get_id_"+that.el.attr("id")).val($(activeItem).find("input").val());				
                return activeItem;
            }

            return null;
        },

        select: function (i) {
            var that = this,
                selectedValue = that.suggestions[i];

            if (selectedValue) {
                that.el.val(selectedValue);
                that.ignoreValueChange = true;
                that.hide();
                that.onSelect(i);
            }
        },

        moveUp: function () {
            var that = this;

            if (that.selectedIndex === -1) {
                return;
            }

            if (that.selectedIndex === 0) {
                $(that.suggestionsContainer).children().first().removeClass(that.classes.selected);
                that.selectedIndex = -1;
                that.el.val(that.currentValue);
                return;
            }

            that.adjustScroll(that.selectedIndex - 1);
        },

        moveDown: function () {
            var that = this;

            if (that.selectedIndex === (that.suggestions.length - 1)) {
                return;
            }

            that.adjustScroll(that.selectedIndex + 1);
        },

        adjustScroll: function (index) {
            var that = this,
                activeItem = that.activate(index),
                offsetTop,
                upperBound,
                lowerBound,
                heightDelta = 25;

            if (!activeItem) {
                return;
            }

            offsetTop = activeItem.offsetTop;
            upperBound = $(that.suggestionsContainer).scrollTop();
            lowerBound = upperBound + that.options.maxHeight - heightDelta;

            if (offsetTop < upperBound) {
                $(that.suggestionsContainer).scrollTop(offsetTop);
            } else if (offsetTop > lowerBound) {
                $(that.suggestionsContainer).scrollTop(offsetTop - that.options.maxHeight + heightDelta);
            }

            that.el.val(that.getValue(that.suggestions[index].value));
        },

        onSelect: function (index) {
            var that = this,
                onSelectCallback = that.options.onSelect,
                suggestion = that.suggestions[index];

            that.el.val(that.getValue(suggestion.value));

            if ($.isFunction(onSelectCallback)) {
                onSelectCallback.call(that.element, suggestion);
            }
        },

        getValue: function (value) {
            var that = this,
                delimiter = that.options.delimiter,
                currentValue,
                parts;

            if (!delimiter) {
                return value;
            }

            currentValue = that.currentValue;
            parts = currentValue.split(delimiter);

            if (parts.length === 1) {
                return value;
            }

            return currentValue.substr(0, currentValue.length - parts[parts.length - 1].length) + value;
        }
    };

    // Create chainable jQuery plugin:
    $.fn.autocomplete = function (options, args) {
		
		var getId="get_id_"+$(this).attr("id");
		
		if($("#"+getId).size()==0)
		{
			$(this).after('<input type="hidden" id="'+getId+'">');
		}
		
		$(this).keydown(function(e)
		{
			//alert(e.keyCode)
			if(e.keyCode!=keys.TAB && 
			e.keyCode!=keys.RETURN && 
			e.keyCode!=keys.ESQUERDA && 
			e.keyCode!=keys.UP && 
			e.keyCode!=keys.DIREITA &&
			e.keyCode!=keys.DOWN &&
			e.keyCode!=keys.SHIFTE &&
			e.keyCode!=keys.CTRL &&
			e.keyCode!=keys.ALT &&
			e.keyCode!=keys.FIXA &&
			e.keyCode!=keys.FIXA &&
			e.keyCode!=keys.BT93 &&
			e.keyCode!=keys.BT91
			)
			{
				$("#"+getId).val("");
			}
			
		});
		$(this).blur(function()
		{
			if($("#"+getId).val()=="")
			{
				$(this).val("");
			}
		})
		
        return this.each(function () {
            var dataKey = 'autocomplete',
                inputElement = $(this),
                instance;

            if (typeof options === 'string') {
                instance = inputElement.data(dataKey);
                if (typeof instance[options] === 'function') {
                    instance[options](args);
                }
            } else {
                instance = new Autocomplete(this, options);
                inputElement.data(dataKey, instance);
            }
        });
    };
}));