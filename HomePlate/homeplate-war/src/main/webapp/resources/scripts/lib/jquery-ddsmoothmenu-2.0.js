/**
 * Based on: http://www.dynamicdrive.com/dynamicindex1/ddsmoothmenu.htm
 *
 * Converted to jQuery plugin by Rob Juurlink.
 */
(function($) {

    $.fn.ddsmoothmenu = function(options, callback) {

        var settings = {
            // Specify full URL to down and right arrow images (23 is padding-right added to top level LIs with drop downs):
            downArrowClass : 'downarrowclass',
            rightArrowClass : 'rightarrowclass',
            downArrowImage : '/images/down.gif',
            rightArrowImage : '/images/right.gif',
            arrowPadding : 23,
            // Speed of slide in/ out animation, in milliseconds per pixel.
            overtime: 1,
            outtime: 1,
            // Set delay in milliseconds before sub menus appear and disappear, respectively.
            showdelay: 10,
            hidedelay: 200
        };

        // If options exist, lets merge them with our default settings
        if (options) {
            $.extend(settings, options);
        }

        // Private functions.

        function buildMenu(mainmenu) {

            window.console && console.info('mainmenu: ', mainmenu.get(0));

            // Add class for styling.
            mainmenu.parent().get(0).className = 'ddsmoothmenu';

            var headers = mainmenu.find("ul").parent();

            // Bij mouse enter- en mouse leave event menu item een selected class geven.
            headers.mouseenter(function() {
                $(this).children('a:eq(0)').addClass('selected')
            });
            headers.mouseleave(function() {
                $(this).children('a:eq(0)').removeClass('selected')
            });

            // Loop through each LI-element which contains a submenu.
            headers.each(function(i) {

                var curobj = $(this).css({zIndex: 100 - i});
                var subul = $(this).find('ul:eq(0)');

                if (subul.size() > 0) {

                    window.console && console.info('curobj', curobj);

                    // Menuitem alleen voor uitklappen, link niet openen.
                    var cura = $('a:eq(0)', curobj);
                    cura.click(function() {
                        return false;
                    });

                    subul.css({display:'block'});
                    subul.data('timers', {});
                    this._dimensions = {
                        w: this.offsetWidth,
                        h: this.offsetHeight,
                        subulw: subul.outerWidth(),
                        subulh: subul.outerHeight()
                    };

                    // Is top level header?
                    this.istopheader = curobj.parents("ul").length == 1;

                    var subul_top = this.istopheader ? this._dimensions.h + "px" : 0;
                    subul.css({top: subul_top});

                    // Add arrow images to submenu's.
                    curobj.children("a:eq(0)").css(this.istopheader ? {paddingRight: settings.arrowPadding} : {}).append(
                            '<img src="' + (this.istopheader ? settings.downArrowImage : settings.rightArrowImage)
                                    + '" class="' + (this.istopheader ? settings.downArrowClass : settings.rightArrowClass)
                                    + '"/>'
                            );

                    // Click- or mouseover event.
                    var entermenu = function(e) {

                        window.console && console.info('Show submenu: ', subul);

                        // Reference UL to reveal.
                        var targetul = subul;
                        // Reference header LI as DOM object.
                        var header = curobj.get(0);

                        clearTimeout(targetul.data('timers').hidetimer);
                        targetul.data('timers').showtimer = setTimeout(function() {

                            header._offsets = {left: curobj.offset().left, top: curobj.offset().top};
                            var menuleft = header.istopheader ? 0 : header._dimensions.w;

                            // Calculate this sub menu's offsets from its parent.
                            menuleft = (header._offsets.left + menuleft + header._dimensions.subulw > $(window).width()) ? (header.istopheader ? -header._dimensions.subulw + header._dimensions.w : -header._dimensions.w) : menuleft;

                            // If 1 or less queued animations.
                            if (targetul.queue().length <= 1) {

                                // Calculate total duration of animation, based on speed.
                                var slide_duration = header._dimensions.subulh * settings.overtime;

                                targetul.css({ left: menuleft + "px", width: header._dimensions.subulw + 'px' });

                                // Slide animation.
                                targetul.slideDown(slide_duration);
                            }
                        }, settings.showdelay);
                    };


                    // Mouse leaves the menu.
                    var leavemenu = function() {

                        window.console && console.info('Hide submenu: ', subul);

                        var targetul = subul;
                        var header = curobj.get(0);
                        clearTimeout(targetul.data('timers').showtimer);
                        targetul.data('timers').hidetimer = setTimeout(function() {

                            // Calculate total duration of animation, based on speed.
                            var slide_duration = header._dimensions.subulh * settings.outtime;

                            targetul.slideUp(slide_duration);

                        }, settings.hidedelay)
                    };

                    curobj.bind('mouseenter', entermenu);
                    curobj.bind('mouseleave', leavemenu);

                    // Enable clicks for mouseevent-less devices (iPhone, iPad, Android).
                    cura.toggle(entermenu, leavemenu);
                }
            });

            mainmenu.find("ul").css({display: 'none', visibility: 'visible'});
        }

        buildMenu(this);

        callback();
    };
})(jQuery);
