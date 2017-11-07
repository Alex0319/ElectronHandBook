(function($){
    $.confirm = function(params){
        var buttonHTML = '';

        if($('#updateAddOverlay').length){
            // A confirm is already shown on the page:
            return false;
        }
        params.buttons['Cancel'] = {
            'class': 'gray',
            'action': function () {}
        }

        $.each(params.buttons,function(name,obj){
            var buttonClass = name !== 'Cancel' ? 'submit' : '';

            buttonHTML += '<a href="#" class="'+ buttonClass +' button '+obj['class']+'">'+name+'<span></span></a>';
            if(!obj.action){
                obj.action = function(){};
            }
        });

        var markup = [
            '<div id="updateAddOverlay">',
            '<div id="updateAddBox">',
            '<h1>',params.title,'</h1>',
            '<div class="updateAdd"></div>',
            '<div id="confirmButtons">',
            buttonHTML,
            '</div></div></div>'
        ].join('');

        $(markup).hide().appendTo('body').fadeIn();
        $('.updateAdd').load('/templates/admin/'+params.template+'.html', function( response, status, xhr ) {
            if (status !== "error" && typeof params.row !== 'undefined') {
                fillForm(params.row);
            }
        });

        var buttons = $('#updateAddBox .button'),
            i = 0;

        $.each(params.buttons,function(name,obj){
            buttons.eq(i++).click(function(){

                // Calling the action attribute when a
                // click occurs, and hiding the confirm.

                obj.action();
                $.confirm.hide();
                return false;
            });
        });
    }

    $.confirm.hide = function(){
        $('#updateAddOverlay').fadeOut(function(){
            $(this).remove();
        });
    }

})(jQuery);