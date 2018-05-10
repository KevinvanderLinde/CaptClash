var users = new Bloodhound({
    datumTokenizer: Bloodhound.tokenizers.obj.whitespace('value'),
    queryTokenizer: Bloodhound.tokenizers.whitespace,
    prefetch: '../data/films/post_1960.json', // something like a cache that gets loaded during init
    remote: {
        url: '/suggestion.json?searchTerm=%QUERY',
        wildcard: '%QUERY'
    }
});

$('#remote .typeahead').typeahead(null, {
    name: 'all-users',
    display: 'value',
    source: users
});