/**
 * Wanneer de HTML is ingeladen in de DOM, onderstaande uitvoeren.
 */

$(document).ready(function() {

    if (!contextPath) {
        window.console && console.error('variable "contextPath" niet gezet.')
    }

    $("#mainmenu").ddsmoothmenu({
        orientation: 'v', //Horizontal or vertical menu: Set to "h" or "v"
        classname: 'ddsmoothmenu-v', //class added to menu's outer DIV
        rightArrowImage: contextPath + '/resources/images/right.gif',
        downArrowImage: contextPath + '/resources/images/down.gif'
    }, function () {
        // Nadat menu opgebouwd is, zichtbaar maken.
        $('nav').css('display', 'block');
    });

    // Bij tabellen de rijen alternate achtergrond geven, bij elke tabel weer bovenaan beginnen.
    $('.lijst table tbody').each(function () {
        $('tr:odd').addClass('alt');
    });

    // Bij tabellen met een span met een klasse dichtgeklapteGegevens de bijbehorende rij hidden maken.
    $('.lijst table tr td .dichtgeklapteGegevens').closest('tr').addClass('hidden');

    // Bij buttons met de klasse toggleHiddenRowsInTable de functie: .... koppelen.
    $('input.toggleHiddenRowsInTable').click(function() {
    	if ($(this).prev('table').find('.hidden').size() > 0) {
    		$(this).prev('table').find('.hidden').removeClass('hidden').addClass('toggleBaar').find('td').hide().slideDown(1000);
    	} else {
    	    if ($(this).prev('table').find('.toggleBaar').size() > 0) {
    	      $(this).prev('table').find('.toggleBaar').removeClass('toggleBaar').addClass('hidden').find('td').hide().slideUp(1000);
    	    }
    	}
    	return false;

    });

    // Als op de huidige pagina een form aanwezig is, focus op het eerste veld zetten.
    $("form :input:not(:button):not([type=submit]):not([type=radio]):enabled:visible:not(.nofocus):first").focus();

    // Initialize Elementen en grid. o.a. elementen transformeren (geheim adres enz...)
    homeplate.initialize();

    // Border van message dialog aanpassen aan soort message intern.
    $('.messages:has(.successmessage)').addClass('successmessages');
    $('.messages:has(.warnmessage)').addClass('warnmessages');
    $('.messages:has(.errormessage)').addClass('errormessages');
    $('.messages:has(.fatalmessage)').addClass('fatalmessages');

    // Toon spinner tijdens jQuery ajax request in jQuery.
    $("#spinner").bind({
        ajaxSend: function() {
            $(this).delay(200).fadeIn(100);
        },
        ajaxStop: function() {
            homeplate.initialize();
            $(this).stop(true, true).hide();
        }
    });

    // Toon spinner tijdens jQuery ajax request in JSF.
    if (window.jsf && jsf.ajax) {
        jsf.ajax.addOnEvent(function (data) {
            var status = data.status;
            if (status === "begin") {
                $('#spinner').delay(200).fadeIn(100);
            } else {
                homeplate.initialize();
                $('#spinner').stop(true, true).hide();
            }
        });
    }
        
});

/* Initialize de elementen (on document ready en nadat de dom gewijzigd is door een Ajax call). */
var homeplate = function() {

    // Private methodes.

    /**
     * Activeer alle geheime adressen, maak ze uit- / inklapbaar.
     */
    function initializeElements() {

    }

    /**
     * Blocken in de CSS grid dezelfde hoogte geven.
     */
    function initializeGrid() {

        // Evt oude min-heights eerst verwijderen, inhoud kan in de tussentijd veranderd zijn en daardoor minder ruimte innemen.
        $('.ui-grid').each(function() {

            window.console && console.info('ui-grid: Container element gevonden: ', this);

            // Loop alle div elementen in de ui-grid container langs.
            $(this).find('> div').each(function() {
                $(this).css('min-height', '');
            });
        });

        // Hoogte van blocken in de grid gelijk maken.
        $('.ui-grid').each(function() {

            window.console && console.info('ui-grid: Container element gevonden: ', this);

            var containerWidth = $(this).width();
            var containerHeight = $(this).height();

            window.console && console.info('ui-grid: Breedte en hoogte van container element: ', containerWidth, containerHeight);

            // Loop alle div elementen in de ui-grid container langs.
            $(this).find('> div').each(function() {

                // Op zoek naar ui-block elementen.
                var clazz = $(this).attr('class');
                if (clazz && clazz.indexOf('ui-block-') == 0) {

                    // Bereken de hoogte voor het component.
                    var marginsHeightComponent = $(this).outerHeight(true) - $(this).height();
                    var newComponentHeight = containerHeight - marginsHeightComponent;

                    $(this).css('min-height', newComponentHeight + 'px');

                    window.console && console.info('ui-grid: Nieuwe hoogte voor component gezet: ', newComponentHeight);
                }
            });
        });
    }

    return {

        // Public methodes.

        initialize: function () {
            initializeElements();
            initializeGrid();
        }
    }
}();

