/**
 * Asynchroon data inladen, tenzij al geladen.
 */

$(document).ready(function() {

    // Initieel is in een cookie vastgelegd of de gegevens in- of uitgeklapt moeten zijn.
    if ($.cookie('registreerTonen') == 'true') {
        toonExpanded(true);
    } else {
        toonCollapsed(true);
    }

    function toonCollapsed(animate) {
        if (!animate) {
            $('#registerForm').fadeOut(1000, function() {
            })
            $('#loginForm').slideDown(1000, function() {
            });
        } else {
            $('#registerForm').hide();
            $('#loginForm').show();
        }
        $('#showRegistreerToggle').addClass('collapsed');
        $('#showRegistreerToggle').removeClass('expanded');
    }

    function toonExpanded(animate) {
        if (!animate) {
            $('#loginForm').fadeOut(1000, function() {
            });
            $('#registerForm').slideDown(1000, function() {
            });
        } else {
            $('#loginForm').hide();
            $('#registerForm').show();
        }
        $('#showRegistreerToggle').addClass('expanded');
        $('#showRegistreerToggle').removeClass('collapsed');
    }

    $('#showRegistreerToggle').click(function() {
        if ($.cookie('registreerTonen') == 'true') {
            $.cookie('registreerTonen', 'false', {path:'/'});
            toonCollapsed(true);
        } else {
            $.cookie('registreerTonen', 'true', {path:'/'});
            toonExpanded(true);
        }
        return false;
    });

});
