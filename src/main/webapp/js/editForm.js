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
        };

        $.each(params.buttons,function(name,obj){
            var buttonClass = name !== 'Cancel' ? 'submit' : '';

            buttonHTML += '<a href="#" class="'+ buttonClass +' button '+obj['class']+'">'+name+'<span></span></a>';
            if(!obj.action){
                obj.action = function(){};
            }
        });

        var markup = [
            '<div id="updateAddOverlay">',
            '<div class="modalWindow">',
            '<div id="updateAddBox">',
            '<h1>',params.title,'</h1>',
            '<div class="updateAdd"></div>',
            '<div id="confirmButtons">',
            buttonHTML,
            '</div></div></div></div>'
        ].join('');

        $(markup).hide().appendTo('body').fadeIn();
        $('.updateAdd').load('/templates/admin/'+params.template+'.html', function( response, status, xhr ) {
            if (status !== "error") {
                clearInputs();
                if(!params.isAdd)
                    fillForm(params.row);
                else
                    if($('#id').attr('name') == 'id')
                        $('#id').parent().remove();
                    else
                        $('#id').prop('readonly', false);
                $(document.body).css('overflow-y', 'hidden');
                $.setInputAutoComplete();
                loadRequiredData(params.row);
            }
        });

        var buttons = $('#updateAddBox .button'), i = 0;

        $.each(params.buttons,function(name,obj){
            buttons.eq(i++).click(function(){
                obj.action(obj.actionParam ? obj.actionParam : null);
                $.confirm.hide();
                return false;
            });
        });
    };

    $.confirm.hide = function(){
        $('#updateAddOverlay').fadeOut(function(){
            $(this).remove();
            $(document.body).css('overflow-y', 'auto');
        });
    };

    $.displayRecords = function (records, elementId) {
        var template = $.get('/templates/admin/elementTag.html', function (data, status) {
            if(status=='success'){
                for(var i = 0; i < records.length; i++){
                    var record = buildObjStr(records[i]);
                    var id = records[i]['id'];
                    $('.selectedItems').append(data);
                    $('.addedElement').attr('data-id', id);
                    $('.elementTagSection').attr('title', record);
                    $('.addedElement .elementTagText').text(record);
                    $('.addedElement').removeClass('addedElement');
                }
            }
        });
    };

    $.setInputAutoComplete = function () {
        if($('.searchInput').length){
            var id = $('.selectedItems').attr('id');
            $('.searchInput').autocomplete({
                serviceUrl: "/main/get_required_data",
                dataType: 'json',
                params: { service: id},
                minChars: 3,
                width: 220,
                showNoSuggestionNotice: true,
                deferRequestBy: 300,
                preserveInput: true,
                transformResult: function (response) {
                    return {
                        suggestions: response[id].map(function (obj) {
                            return buildObjStr(obj);
                        })
                    }
                },
                zIndex: 1080
            });
        }
    };

    $(document).on('click', '.tagButton', function () {
        $(this.parentNode.parentNode).remove();
    });

})(jQuery);