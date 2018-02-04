(function($){
    $(function(){

        $('.button-collapse').sideNav();
        $('.parallax').parallax();

        $(document).ready(function() {
            $('select').material_select();

            // for HTML5 "required" attribute
            $("select[required]").css({
                display: "inline",
                height: 0,
                padding: 0,
                width: 0
            });
        });
    }); // end of document ready
})(jQuery); // end of jQuery name space


