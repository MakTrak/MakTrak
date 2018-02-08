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
        //modal trigger
        $(document).ready(function(){
            // the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
            $('.modal').modal();

        });

        //search function
        $("#search-criteria").on("keyup", function() {
            var g = $(this).val().toLowerCase();
            $(".card .card-content span").each(function() {
                var s = $(this).text().toLowerCase();
                $(this).closest('.card')[ s.indexOf(g) !== -1 ? 'show' : 'hide' ]();
            });
        });

    }); // end of document ready


})(jQuery); // end of jQuery name space


