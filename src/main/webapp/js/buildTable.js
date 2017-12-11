function emptyData(data) {
    for(var key in data){
        if(key !== 'id' && !$.isPlainObject(data[key]) && data[key] !== null)
            return false;
        if($.isPlainObject(data[key]))
            for(var key2 in data[key]){
                if(key2 !== 'id' && !$.isPlainObject(data[key2]) && data[key] !== null)
                    return false;
            }
    }
    return true;
}

function build(obj) {
    var tempResult = '';
    for(var key in obj){
        if(obj[key] !== null)
            if(key === 'id')
                tempResult = obj[key] + ' ' + tempResult;
            else
                tempResult += obj[key] + ' ';
    }
    return tempResult;
}

function buildObjStr(obj) {
    return (obj.length > 0 ? obj.reduce(function (temp, elem) {
                                            return temp + build(elem) + '<br/>';
                                         }, '') : build(obj)).trim();
}

function buildTableTitle(tableName, columnTrString, countColumn) {
    var mainHeader = '<tr class="titleTr"><td class="titleTd">'+tableName.toUpperCase()+'</td><td colspan="' + (countColumn ? countColumn : 0) + '"></td><td class="plusTd button"></td></tr>';
    if(columnTrString){
        mainHeader += columnTrString;
    }
    return '<thead>' + mainHeader + '</thead>';
}

function buildTableHead(data) {
    var columnTrString = '';
    var countColumn = 0;

    for(var key in data) {
        columnTrString+='<td id="'+key.toUpperCase()+'">'+key.toUpperCase()+'</td>';
        countColumn++;
    }
    if(typeof data !== 'undefined')
        columnTrString += '<td class="changeData">UPDATE</td><td class="changeData">DELETE</td>';
    return {'head' : '<tr class="headingTr">' + columnTrString + '</tr>',
        'countColumn' : countColumn};
}

function setHtml(nameTable){
    var countRows = Data.length;
    var bodyString = '';
    var j = 0;
    var head={};
    zIndex = 1050;

    if (!emptyData(Data[0])) {
        while (j != countRows) {
            var additionalString = '';
            for (var key in Data[j]) {
                if ($.isPlainObject(Data[j][key]) || $.isArray(Data[j][key])) {
                    futureQueryForID[key] = key;
                    additionalString += '<td class="embeddedTd" id="' + (Data[j][key])['id'] + '" data-toggle="modal" data-target="#modalWindow' + deep + '" onclick="generateModals(this)">' + buildObjStr(Data[j][key]) + '</td>';
                } else
                    additionalString += '<td>' + (Data[j][key] ? Data[j][key] : key + ' not specified')  + '</td>';
            }

            additionalString += '<td class="changeTd" onclick="editRow(this.parentNode)"><div class="settingsIcon"><img src="/images/pencil32.png"/></div>' +
                '<td class="deleteTd" onclick="deleteRow(this.parentNode)"><div class="settingsIcon"><img src="/images/delete.png"/></div></td>';
            bodyString += '<tr id="' + Data[j].id + '">' + additionalString + '</tr>';
            j++;
        }
        head = buildTableHead(Data[0]);
    }
    $('.flatTable').html(buildTableTitle(nameTable, head['head'], head['countColumn']) + '<tbody>'+ bodyString+ '</tbody>');
}

function buildSelectsForForm(data, obj) {
    for(var key in data){
        var options = '';
        for(var i = 0; i < data[key].length; i++){
            var result = '', selected = '';
            for(var value in (data[key])[i]){
                if(((data[key])[i])[value] !== null){
                    if(value == 'id'){
                        result = ((data[key])[i])[value] + ' ' + result;
                        selected = (obj[key])[value] == ((data[key])[i])[value] ? 'selected':'';
                    }
                    else{
                        result += ((data[key])[i])[value] + ' ';
                    }
                }
            }
            options += '<option value="' + ((data[key])[i])['id'] + '" '+ selected +'>' + result.trim() + '</option>';
        }
        $('#'+key).html(options);
    }
}
