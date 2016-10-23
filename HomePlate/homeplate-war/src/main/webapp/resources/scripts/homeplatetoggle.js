/**
 * Asynchroon data inladen, tenzij al geladen.
 */

$(document).ready(function() {

    // Initieel is in een cookie vastgelegd of de gegevens in- of uitgeklapt moeten zijn.
    if ($.cookie('wedstrijdenActueelTonen') == 'true') {
        toonExpanded(true);
    } else {
        toonCollapsed(true);
    }

    function toonCollapsed(animate) {
        if (animate) {
            $('#actuelewedstrijden').fadeOut(1000, function() {
            });
        } else {
            $('#actuelewedstrijden').hide();
        }
        $('#showWedstrijdenActueelToggle').addClass('collapsed');
        $('#showWedstrijdenActueelToggle').removeClass('expanded');
    }

    function toonExpanded(animate) {
        if (animate) {
            $('#actuelewedstrijden').slideDown(1000, function() {
            });
        } else {
            $('#actuelewedstrijden').show();
        }
        $('#showWedstrijdenActueelToggle').addClass('expanded');
        $('#showWedstrijdenActueelToggle').removeClass('collapsed');
    }

    $('#showWedstrijdenActueelToggle').click(function() {
        if ($.cookie('wedstrijdenActueelTonen') == 'true') {
            $.cookie('wedstrijdenActueelTonen', 'false', {path:'/'});
            toonCollapsed(true);
        } else {
            $.cookie('wedstrijdenActueelTonen', 'true', {path:'/'});
            toonExpanded(true);
        }
        return false;
    });

    // Verberg informatie na 5 seconden.
    setTimeout(function() {
        $('#notificatieTable').fadeOut(1000, function(){});
    }, 10000); // <-- time in milliseconds

    $("div .ui-editor-button[title='Insert Image']").click(function() {
       alert("insert image"); 
    });
    
});
