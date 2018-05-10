var users = new Bloodhound({
    datumTokenizer: Bloodhound.tokenizers.obj.whitespace("Value"),
    queryTokenizer: Bloodhound.tokenizers.whitespace,
    remote: {
        url: '/api/suggestion?searchstr=%QUERY',
        wildcard: '%QUERY'
    }
});

$('#remote .typeahead').typeahead(null, {
	highlight: true,
    hint: true,
    minLenght: 1,
    name: 'all-users',
    display: 'value',
    source: users
});
