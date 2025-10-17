//Top Menu on hover dropdown
//const $dropdown = $(".dropdown");
//const $dropdownToggle = $(".dropdown-toggle");
//const $dropdownMenu = $(".dropdown-menu");
//const showClass = "show";

//$(window).on("load resize", function () {
//    if (this.matchMedia("(min-width: 768px)").matches) {
//        $dropdown.hover(
//            function () {
//                const $this = $(this);
//                $this.addClass(showClass);
//                $this.find($dropdownToggle).attr("aria-expanded", "true");
//                $this.find($dropdownMenu).addClass(showClass);
//            },
//            function () {
//                const $this = $(this);
//                $this.removeClass(showClass);
//                $this.find($dropdownToggle).attr("aria-expanded", "false");
//                $this.find($dropdownMenu).removeClass(showClass);
//            }
//        );
//    } else {
//        $dropdown.off("mouseenter mouseleave");
//    }
//});
//End Top menu on hover dropdown

//Whats News Slider get news id function
function getUrlValues() {
    var vars = {};
    var parts = window.location.href.replace(/[?&]+([^=&]+)=([^&]*)/gi, function (m, key, value) {
        vars[key] = value;
    });
    return vars;
}

/*------------Font size increase, decrese and reset-------------------*/
var resize;
var resetFont1;
var counter;
$(document).ready(function() {

    resize = new Array('h3','h4','h6','p','.admissionlink a','span.dashtitle','ul.notificationlist li','.couadmservice ul li','ul.topmenulink li a','.breadcrumb-item+.breadcrumb-item','span.introduction','span.notification','.footerthird a','h1',);
    resize = resize.join(',');
    
    //resets the font size when "A" is clicked
var resetFont = $(resize).css('font-size');
    $(".resetfont").click(function() {
      $(resize).css('font-size', resetFont);
      return false;
    });
  
    //increases font size when "A+" is clicked    

    counter = 1;
    $(".increasefont").click(function() {         
        if(counter<=5)
    {
        counter++;
        var originalFontSize = $(resize).css('font-size');
        var originalFontNumber = parseFloat(originalFontSize);
        var newFontSize = originalFontNumber + 1;      
        $(resize).css('font-size', newFontSize);
    }
      return false;
    });
  
    //decrease font size when "A-" is clicked
  
   
    $(".decreasefont").click(function() {
    if(counter>=-5)
    {
        counter--;
        var originalFontSize = $(resize).css('font-size');
        var originalFontNumber = parseFloat(originalFontSize);
        var newFontSize = originalFontNumber - 1;
        $(resize).css('font-size', newFontSize);
    }
    return false;
    });
  
  });
/*------------Font size increase, decrese and reset-------------------*/
function resetfont() {
    // $(resize).css('font-size', resetFont);
    // return false;
        var originalFontSize = $(resize).css('font-size');
        var originalFontNumber = parseFloat(originalFontSize);
        var newFontSize = originalFontNumber -counter;      
        $(resize).css('font-size', newFontSize);
        counter=0;

  };
 function increasefont() {         
    if(counter<=5)
{
    counter++;
    var originalFontSize = $(resize).css('font-size');
    var originalFontNumber = parseFloat(originalFontSize);
    var newFontSize = originalFontNumber + 1;      
    $(resize).css('font-size', newFontSize);
}
  return false;
};

function decreasefont () {
if(counter>=-5)
{
    counter--;
    var originalFontSize = $(resize).css('font-size');
    var originalFontNumber = parseFloat(originalFontSize);
    var newFontSize = originalFontNumber - 1;
    $(resize).css('font-size', newFontSize);
}
return false;
};

function scroll(el) {
    // let el = document.getElementById(id);
    el.scrollIntoView();
  }

 

$(window).ready(function () {
    $('#home-slider').flexslider({
        animation: "fade",
        controlNav: true,
        directionNav: true,
        animationLoop: true,
        pausePlay: true,
        controlNav: true,
        minItems: 1,
        maxItems: 1,
        slideshow: 1,
        slideshowSpeed: 7000,
        animationSpeed: 600,
        initDelay: 0,
        move: 1,
        controlNav: true,
        pauseOnHover: true,
        directionNav: true,
        prevText: "Previous",
        nextText: "Next",
        playText: "",
        pauseText: "",
    });
    $('#minister-carousel').flexslider({
        animation: "slide",
        animationLoop: false,
        itemWidth: 310,
        itemMargin: 25,
        smoothHeight: true,
        minItems: 1,
        maxItems: 3,
        slideshow: 1,
        move: 1,
        controlNav: true,
        playText: "",
        pauseText: "",
    });
    $('#digital-india-carousel').flexslider({
        animation: "fade",
        controlNav: true,
        directionNav: true,
        animationLoop: true,
        pausePlay: true,
        itemWidth: 310,
        itemMargin: 15,
        minItems: 1,
        maxItems: 1,
        slideshow: 1,
        slideshowSpeed: 7000,
        animationSpeed: 600,
        move: 1,
        controlNav: true,
        playText: "",
        pauseText: "",
    });
    $('#services-carousel').flexslider({
        animation: "slide",
        animationLoop: true,
        pausePlay: true,
        itemWidth: 310,
        itemMargin: 15,
        minItems: 1,
        maxItems: 3,
        slideshow: 1,
        slideshowSpeed: 7000,
        animationSpeed: 600,
        move: 1,
        controlNav: true,
        playText: "",
        pauseText: "",
    });

    $('#products-carousel').flexslider({
        animation: "fade",
        controlNav: true,
        directionNav: true,
        animationLoop: true,
        pausePlay: true,
        itemWidth: 310,
        itemMargin: 15,
        minItems: 1,
        maxItems: 1,
        slideshow: 1,
        slideshowSpeed: 7000,
        animationSpeed: 600,
        move: 1,
        controlNav: true,
        playText: "",
        pauseText: "",
    });
    $('#nic-emerging-carousel').flexslider({
        animation: "fade",
        controlNav: true,
        directionNav: true,
        animationLoop: true,
        pausePlay: true,
        itemWidth: 310,
        itemMargin: 15,
        minItems: 1,
        maxItems: 1,
        slideshow: 1,
        slideshowSpeed: 7000,
        animationSpeed: 600,
        move: 1,
        controlNav: true,
        playText: "",
        pauseText: "",
    });

    $('#updates-carousel').flexslider({
        animation: "slide",
        animationLoop: false,
        itemWidth: 210,
        itemMargin: 25,
        minItems: 1,
        maxItems: 3,
        slideshow: 1,
        move: 1,
        controlNav: true,
        playText: "",
        pauseText: "",
    });
    $('#whats-new-carousel').flexslider({
        animation: "fade",
        controlNav: false,
        directionNav: true,
        animationLoop: false,
        pausePlay: true,
        itemWidth: 310,
        itemMargin: 15,
        minItems: 1,
        maxItems: 1,
        slideshow: 0,
        move: 1,
        playText: "",
        pauseText: "",
        startAt: getUrlValues()['newsid'],
        start: function (slider) {
            var index1 = slider.currentSlide;
            if (index1 === 0) {
                $('ul.flex-direction-nav li.flex-nav-prev').css('display', 'none');
            }
        },
        before: function (slider) {
            var index = slider.count;
            var index1 = slider.currentSlide;
            if (index1 === index || index1 + 2 >= index) {
                $('ul.flex-direction-nav li.flex-nav-next').css('display', 'none');
            } else {
                $('ul.flex-direction-nav li.flex-nav-next').css('display', 'block');
            }

        },
        after: function (slider) {
            var index = slider.count;
            var index1 = slider.currentSlide;

            if (index1 === 0) {
                $('ul.flex-direction-nav li.flex-nav-prev').css('display', 'none');
            } else {
                $('ul.flex-direction-nav li.flex-nav-prev').css('display', 'block');
            }
        }
    });
	/*
	var fs = $('#products-carousel'),
		dataItem = fs.data('item'),
		item = fs.find('.item');
	for(var i = 0; i < item.length; i += dataItem) {
		item.slice(i, i + dataItem).wrapAll('<div class="items"></div>');
	}
	$('#products-carousel').flexslider({
		selector: '.slides > .items',
		animation: "slide",
		animationLoop: false,
		directionNav: true,
		slideshow: false,
		smoothHeight: true,
		itemMargin: 0,
		minItems: 1,
		move: 0,
		maxItems: 1,
		controlNav: true,
		playText: "",
		pauseText: "",
	});
	*/

    $('#case-study-carousel').flexslider({
        animation: "slide",
        animationLoop: false,
        itemWidth: 310,
        itemMargin: 15,
        minItems: 1,
        maxItems: 1,
        slideshow: 1,
        move: 1,
        controlNav: true,
        playText: "",
        pauseText: "",
    });
    $('#testimonial').flexslider({
        animation: "fade",
        controlNav: false,
        directionNav: true,
        animationLoop: true,
        pausePlay: true,
        itemWidth: 310,
        itemMargin: 15,
        minItems: 1,
        maxItems: 1,
        slideshow: 1,
        slideshowSpeed: 3000,
        animationSpeed: 600,
        move: 1,
        controlNav: false,
        prevText: "",
        nextText: "",
        playText: "",
        pauseText: "",
    });
    $('#nic-services-carousel').flexslider({
        animation: "fade",
        controlNav: true,
        directionNav: true,
        animationLoop: true,
        pausePlay: true,
        itemWidth: 310,
        itemMargin: 15,
        minItems: 1,
        maxItems: 1,
        slideshow: 1,
        slideshowSpeed: 3000,
        animationSpeed: 600,
        move: 1,
        controlNav: true,
        prevText: "",
        nextText: "",
        playText: "",
        pauseText: "",
    });
    
	/*$('#news-slider').flexslider({
		animation: "slide",
		
		directionNav: true
	});*/
    $('#minister-section-slider').flexslider({
        animation: "slide",
        directionNav: true
    });
    var fs = $('#my-projects-carousel'),
        dataItem = fs.data('item'),
        item = fs.find('.item');
    for (var i = 0; i < item.length; i += dataItem) {
        item.slice(i, i + dataItem).wrapAll('<div class="items"></div>');
    }
    $('#my-projects-carousel').flexslider({
        selector: '.slides > .items',
        animation: "slide",
        animationLoop: false,
        directionNav: true,
        slideshow: false,
        smoothHeight: true,
        itemMargin: 0,
        minItems: 1,
        move: 0,
        maxItems: 1,
        controlNav: true,
        playText: "",
        pauseText: "",
        accessibility: false,
    });
});
$(document).ready(function () {
    $(".overlay-slider li").append('<div class="overlay" id="particle-canvas"> </div>');
	/* $(function() {
		$('a[href*=\\#]').on('click', function(e) {
			e.preventDefault();
			$('html, body').animate({
				scrollTop: $($(this).attr('href')).offset().top
			}, 500, 'linear');
		});
    }); */
    
/****************showing error***********************************  
    if ($(window).width() > 1280) { // min screen size 1280
        
        $('#fullpage').fullpage({
            //anchors: ['firstPage', 'secondPage', '3rdPage'],
            //sectionsColor: ['#C63D0F', '#1BBC9B', '#7E8F7C'],
            responsiveHeight: 600,
            scrollBar: true,
            afterResponsive: function (isResponsive) { }
        });
    }

*******************************************************/


    $("#toggle").click(function () {
        $(this).toggleClass('open');
        $(".menu").slideToggle(100);
    });
});

/*
$(window).scroll(function() {
    if ($(window).width() > 900) { // min screen size 1025
        if ($(window).scrollTop() > 50) {

            $('header').addClass('fixed');

        } else {
            $('header').removeClass('fixed');

        }
    }
});
*/
/*fixed menu on scroll up*/
$(document).ready(function () {
    var senseSpeed = 5;
    var previousScroll = 0;
    $(window).scroll(function (event) {
        var headerHeight = $('header').height() + 200;
        var scroller = $(this).scrollTop();
        if (scroller < headerHeight) {
            $('header').removeClass('fixed');
            $('.top-wrapper').removeClass('sttings-active');
            $('#TheButton, #search-input').removeClass('active');
            $('#cmf-site-setting').removeClass('chide');
        } else {
            if (scroller > previousScroll) {
                $('header').animate({
                    'top': '0'
                    /* 'top': '-200' */
                }, 0);
                $('header').removeClass('fixed');
            } else {
                $('header').animate({
                    'top': '0'
                }, 0);
                /* $('header').addClass('fixed'); */
            }
            previousScroll = scroller;
        }
    });



});
/*fixed menu on scroll up ends here*/
//Accesibility Setting
jQuery(document).ready(function () {
    //Begin show search on click....
    $('#toggleAccessibility').click(function () {
        //$(".search_box").slideUp(500, 'swing', function() {});
        $('#toggle_search').removeClass('active').addClass('not-active');
        var cssss = $(this).attr('class');
        if (cssss === 'not-active' || cssss === undefined) {
            $(this).removeClass('not-active').addClass('active');
            //$(".accessiblity-container").slideDown(500, 'swing', function() {});
            $('.top-wrapper').addClass('sttings-active');
            $('header').css("top", "auto");
        } else {
            $(this).removeClass('active').addClass('not-active');
            //$(".accessiblity-container").slideUp(500, 'swing', function() {});
            $('.top-wrapper').removeClass('sttings-active');

        }
    });
    $(document).keyup(function () {
        $(".logo").click(function (e) {
            //e.preventDefault();
            $().focus();
        });
        $(".menu").click(function (e) {
            //e.preventDefault();
            $().focus();
        });
        $(".menu-item").click(function (e) {
            //e.preventDefault();
            $().focus();
        });
        if ($('.cmf-accessibility a').is(':focus')) {
            //$(".accessiblity-container").slideDown(1000, 'swing', function() {});
            $('#toggleAccessibility').removeClass('not-active').addClass('active');
            $('.top-wrapper').addClass('sttings-active');
        } else {
            //$(".accessiblity-container").slideUp(1000, 'swing', function() {});
            $('.top-wrapper').removeClass('sttings-active');
            $('#toggleAccessibility').removeClass('active').addClass('not-active');
        }
    }).click(function () {
        //$(".accessiblity-container").slideUp(1000, 'swing', function() {});
        $('.top-wrapper').removeClass('sttings-active');
        $('#toggleAccessibility').removeClass('active').addClass('not-active');
    });
    $('.cmf-accessibility').click(function (e) {
        e.stopPropagation();
    })
    //End search box on click
    $(".high-contrast.dark").click(function (e) {
        //e.preventDefault();
        $(".high-contrast.light").focus();
    });
    $(".high-contrast.light").click(function (e) {
        //e.preventDefault();
        $(".high-contrast.dark").focus();
    });
    $(".Skip-content").click(function (e) {
        //e.preventDefault();
        $(".Skip-content").focus();
    });
    $(".logo").click(function (e) {
        //e.preventDefault();
        $().focus();
    });
    $(".twitterwidget").click(function (e) {
        //e.preventDefault();
        $().trigger('blur');
    });


});




//search filter starts
$(document).ready(function () {
    $('.filter-box').hide();
    $('#filter-btn').click(function (e) {
        e.stopPropagation();
        $(".filter-box").toggle();
    });
    $('#cmf-site-filter').click(function (e) {
        e.stopPropagation();
    });
    $(document).keyup(function () {
        // if (!$('.form-btn').is(':focus') && !$('.filter-box').is(':focus')) {
        //$('.filter-box').show();
        // $('.filter-box').hide();
        // alert('inVisible');
        // } 
        // else {
        //$('.filter-box').hide();
        // $('.filter-box').show();
        // alert('visible');
        // }

        if ($('.filter-box input').is(':focus') || $('#filter-btn').is(':focus') || $('#stateselect').is(':focus')) {
            $('.filter-box').show();
            //$('.filter-box').hide();
            //alert('inVisible');
        }
        else {
            $('.filter-box').hide();
            //$('.filter-box').show();
            //alert('visible');
        }
        //e.stopPropagation();
    })
});

$(document).click(function () {
    $('.filter-box').hide();
});

$(document).on('keydown', function (e) {
    if (e.keyCode === 27) { // ESC
        $('.filter-box').hide();
    }
});

//search filter ends



$(document).ready(function () {
    //$(".flexslider").append("<div class="recives-award flex-caption"></div>")
});
$(document).ready(function () {
    //$('.flex-control-nav').appendTo('.change-year .container');
    $(".project-all ul li a").height($(".project-all ul li").height());

});

/*===========Menu==================*/
$(document).ready(function () {
    $(".menu-toggle").click(function () {
        $(this).toggleClass('open');
        $("#menu").removeClass('search_over');
        $("#primary-menu").toggleClass('open');
        $("html").toggleClass('htmlfixed');
        $("header").toggleClass('ztop');
        $(".overlay-popup").toggleClass('menu-over');
        $("#primary-menu li ul.sub-menu").removeClass('sub-child');
        $("#primary-menu li.menu-item-has-children").removeClass('linkactive');
    });

    $("#primary-menu li.menu-item-has-children").click(function () {
        $(this).toggleClass('linkactive');
        $("#primary-menu li ul.sub-menu").toggleClass('sub-child');
    });



    $(".overlay-popup").click(function () {
        $(this).removeClass('menu-over');
        $("#primary-menu").removeClass('open');
        $("html").removeClass('htmlfixed');
        $("header").removeClass('ztop');
        $(".menu-toggle").removeClass('open');
    });

    $("#TheButton").click(function () {
        $(this).toggleClass('active');
        $("#search-input").toggleClass('active');
        $("#menu").toggleClass('search_over');
        $("header").toggleClass('ztop');
        $("#toggleAccessibility").toggleClass('chide');
    });

    //$('.sub-menu').append('<i class="fa fa-caret-right"></i>');
    $('.toggle-nav-bar').click(function () {
        $('#nav').slideToggle();
        $('#nav li').removeClass('open');
        $("#nav li").click(function () {
            $("#nav li").removeClass('open');
            $(this).addClass('open');
        });
    });
    $("#accessibility").hide();
    $("#ico-accessibility a").click(function () {

        //$("header").addClass("static");
        $("ul#accessibility").slideToggle();
        $("#ico-accessibility").toggleClass("active");
    });



});
// particle.min.js hosted on GitHub
// Scroll down for initialisation code


//!function (a) { var b = "object" == typeof self && self.self === self && self || "object" == typeof global && global.global === global && global; "function" == typeof define && define.amd ? define(["exports"], function (c) { b.ParticleNetwork = a(b, c) }) : "object" == typeof module && module.exports ? module.exports = a(b, {}) : b.ParticleNetwork = a(b, {}) }(function (a, b) { var c = function (a) { this.canvas = a.canvas, this.g = a.g, this.particleColor = a.options.particleColor, this.x = Math.random() * this.canvas.width, this.y = Math.random() * this.canvas.height, this.velocity = { x: (Math.random() - .5) * a.options.velocity, y: (Math.random() - .5) * a.options.velocity } }; return c.prototype.update = function () { (this.x > this.canvas.width + 20 || this.x < -20) && (this.velocity.x = -this.velocity.x), (this.y > this.canvas.height + 20 || this.y < -20) && (this.velocity.y = -this.velocity.y), this.x += this.velocity.x, this.y += this.velocity.y }, c.prototype.h = function () { this.g.beginPath(), this.g.fillStyle = this.particleColor, this.g.globalAlpha = .7, this.g.arc(this.x, this.y, 1.5, 0, 2 * Math.PI), this.g.fill() }, b = function (a, b) { this.i = a, this.i.size = { width: this.i.offsetWidth, height: this.i.offsetHeight }, b = void 0 !== b ? b : {}, this.options = { particleColor: void 0 !== b.particleColor ? b.particleColor : "#fff", background: void 0 !== b.background ? b.background : "#1a252f", interactive: void 0 !== b.interactive ? b.interactive : !0, velocity: this.setVelocity(b.speed), density: this.j(b.density) }, this.init() }, b.prototype.init = function () { if (this.k = document.createElement("div"), this.i.appendChild(this.k), this.l(this.k, { position: "absolute", top: 0, left: 0, bottom: 0, right: 0, "z-index": 1 }), /(^#[0-9A-F]{6}$)|(^#[0-9A-F]{3}$)/i.test(this.options.background)) this.l(this.k, { background: this.options.background }); else { if (!/\.(gif|jpg|jpeg|tiff|png)$/i.test(this.options.background)) return console.error("Please specify a valid background image or hexadecimal color"), !1; this.l(this.k, { background: 'url("' + this.options.background + '") no-repeat center', "background-size": "cover" }) } if (!/(^#[0-9A-F]{6}$)|(^#[0-9A-F]{3}$)/i.test(this.options.particleColor)) return console.error("Please specify a valid particleColor hexadecimal color"), !1; this.canvas = document.createElement("canvas"), this.i.appendChild(this.canvas), this.g = this.canvas.getContext("2d"), this.canvas.width = this.i.size.width, this.canvas.height = this.i.size.height, this.l(this.i, { position: "" }), this.l(this.canvas, { "z-index": "20", position: "relative" }), window.addEventListener("resize", function () { return this.i.offsetWidth === this.i.size.width && this.i.offsetHeight === this.i.size.height ? !1 : (this.canvas.width = this.i.size.width = this.i.offsetWidth, this.canvas.height = this.i.size.height = this.i.offsetHeight, clearTimeout(this.m), void (this.m = setTimeout(function () { this.o = []; for (var a = 0; a < this.canvas.width * this.canvas.height / this.options.density; a++)this.o.push(new c(this)); this.options.interactive && this.o.push(this.p), requestAnimationFrame(this.update.bind(this)) }.bind(this), 500))) }.bind(this)), this.o = []; for (var a = 0; a < this.canvas.width * this.canvas.height / this.options.density; a++)this.o.push(new c(this)); this.options.interactive && (this.p = new c(this), this.p.velocity = { x: 0, y: 0 }, this.o.push(this.p), this.canvas.addEventListener("mousemove", function (a) { this.p.x = a.clientX - this.canvas.offsetLeft, this.p.y = a.clientY - this.canvas.offsetTop }.bind(this)), this.canvas.addEventListener("mouseup", function (a) { this.p.velocity = { x: (Math.random() - .5) * this.options.velocity, y: (Math.random() - .5) * this.options.velocity }, this.p = new c(this), this.p.velocity = { x: 0, y: 0 }, this.o.push(this.p) }.bind(this))), requestAnimationFrame(this.update.bind(this)) }, b.prototype.update = function () { this.g.clearRect(0, 0, this.canvas.width, this.canvas.height), this.g.globalAlpha = 1; for (var a = 0; a < this.o.length; a++) { this.o[a].update(), this.o[a].h(); for (var b = this.o.length - 1; b > a; b--) { var c = Math.sqrt(Math.pow(this.o[a].x - this.o[b].x, 2) + Math.pow(this.o[a].y - this.o[b].y, 2)); c > 120 || (this.g.beginPath(), this.g.strokeStyle = this.options.particleColor, this.g.globalAlpha = (120 - c) / 120, this.g.lineWidth = .7, this.g.moveTo(this.o[a].x, this.o[a].y), this.g.lineTo(this.o[b].x, this.o[b].y), this.g.stroke()) } } 0 !== this.options.velocity && requestAnimationFrame(this.update.bind(this)) }, b.prototype.setVelocity = function (a) { return "fast" === a ? 1 : "slow" === a ? .33 : "none" === a ? 0 : .66 }, b.prototype.j = function (a) { return "high" === a ? 5e3 : "low" === a ? 2e4 : isNaN(parseInt(a, 10)) ? 1e4 : a }, b.prototype.l = function (a, b) { for (var c in b) a.style[c] = b[c] }, b });

// Initialisation

// var canvasDiv = document.getElementById('particle-canvas');
// var options = {
//     particleColor: '#888',

//     interactive: true,
//     speed: 'medium',
//     density: 'high'
// };
// var particleCanvas = new ParticleNetwork(canvasDiv, options);







