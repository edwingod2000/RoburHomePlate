/** Pisa jQuery plugins. */

(function($) {

    $.fn.geheimadres = function (options) {

        var settings = {
            labelToonAdres : 'Toon Adres',
            labelVerbergAdres : 'Verberg Adres'
        };

        // If options exist, lets merge them with our default settings
        if (options) {
            $.extend(settings, options);
        }

        // There's no need to do $(this) because "this" is already a jquery object.
        this.each(function() {

            // Iiniteel geheim adres niet tonen, maar wel mogelijkheid geven om het te tonen na muisklik.
            var geheimadres = $(this);
            $('<a href="#" class="geheimadrestonenlink">' + settings.labelToonAdres + "</a>").insertBefore(geheimadres);
            $('<a href="#" class="geheimadresverbergenlink">' + settings.labelVerbergAdres + '</a><br />').prependTo(geheimadres);
            $('.geheimadrestonenlink').bind('click.geheimadres', function() {
                $(this).hide().next().show();
                return false;
            });
            $('.geheimadresverbergenlink').bind('click.geheimadres', function() {
                $(this).parent().hide().prev('.geheimadrestonenlink').show();
                return false;
            });
        });

        return this;
    };
})(jQuery);
