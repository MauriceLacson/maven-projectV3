
$(".has-error").hide();
$("#submit").click(function (e) {
    e.preventDefault();

    $.ajax({
        method: 'post',
        url: '/post',
        data: $("#regForm").serialize(),
        dataType: 'json',
        success: function (data) {

            if (data.message) {
                $("#regForm").slideUp('slow');
                $("#formStyle2").hide().fadeIn().html('<p class="text-center" >You are successfully registered.</p>');
            } else {

                $("#formMessage").empty();

                $(".has-error").fadeOut().empty();
                $(".error").remove();
                $.each(data, function (a, b) {

                        $("#"+a).after("<p class='error'>"+b+"</p>");
                        $(".has-error").fadeIn();

                });
                $()
            }
        }
    })
});

var dropDownMenu = $("#hamburger");
var dropContent = $(".dropdown-content");

$(dropDownMenu).click(function () {
    dropContent.css = "display: block; position: absolute; z-index: 1;"

});

// $(window).on('resize', function ()
// {
//     if ($(window).width() < 600)
//     {
//         $("#menuNav").show();
//     }
// });
//

