/*
 Maak een HTML blok (div) toggelbaar door er een link voor te zetten welke de div
 onzichtbaar maakt dan wel zichtbaar.

 Attributen:
 class="toggelbaar"      verplicht, hiermee geef je aan dat een div toggelbaar is
 data-defaultDisplay     verplicht, de default display style; "none" of "block"
 data-showText           optioneel, link tekst voor het tonen van het blok
 data-hideText           optioneel, link tekst voor het verstoppen van het blok
 data-linkClass          optioneel, html class van de gemaakte link

 Voorbeeld:

 <div class="toggelbaar" data-defaultDisplay="none" data-showText="Laat zien" data-hideText="Weg er mee">
 Deze div is default onzichtbaar maar er wordt een link gemaakt welke
 hem toggelbaar maakt.
 </div>
 */


(function($) {

    $.fn.toggelbaar = function () {

        // There's no need to do $(this) because "this" is already a jquery object.
        this.filter('.toggelbaar').each(function() {

            // Dit element al eerder ge-processed? dan overslaan.
            var toggelDiv = $(this);
            if (!toggelDiv.data('processed')) {

                // De settings bevinden zich in parameters.
                var defaultDisplay = toggelDiv.data('defaultDisplay');
                var labelShow = toggelDiv.data('showText') || '>>>';
                var labelHide = toggelDiv.data('hideText') || '<<<';
                var linkClass = toggelDiv.data('linkClass');

                // Parameters controleren.
                if (!(defaultDisplay != 'none' && defaultDisplay != 'block')) {

                    $('<a/>', {href:'#', 'class':linkClass, text: labelShow}).insertBefore(toggelDiv).toggle(defaultDisplay == 'none').bind('click.toggelbaar', function() {
                        $(this).hide().next('a').show().nextAll('div:first').slideDown(100);
                        return false;
                    });

                    $('<a/>', {href:'#', 'class':linkClass, text: labelHide}).insertBefore(toggelDiv).toggle(defaultDisplay == 'block').bind('click.toggelbaar', function() {
                        $(this).hide().prev('a').show().end().nextAll('div:first').slideUp(100);
                        return false;
                    });

                } else {
                    // Parameter defaultDisplay is verplicht.
                    window.console && console.error('toggelbaar: Verplichte parameter "data-defaultDisplay" niet gezet.', toggelDiv)
                }

                toggelDiv.toggle(defaultDisplay == 'block').data('processed', true);
            }
        });

        return this;
    };
})(jQuery);
