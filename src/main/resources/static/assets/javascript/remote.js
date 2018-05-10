var users = new Bloodhound({
    datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
    queryTokenizer: Bloodhound.tokenizers.whitespace,
    remote: {
        url: '/suggestion?searchstr=%QUERY',
        wildcard: '%QUERY'
    }
});

$('#remote .typeahead').typeahead(null, {
    hint: true,
    minLenght: 1,
    name: 'all-users',
    display: 'value',
    source: users
});