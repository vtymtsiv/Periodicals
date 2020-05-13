var selItem = localStorage.getItem("lang");
$('#locates').val(selItem ? selItem : 'en');
$("#locates").change(function () {
    var selectedlang = $('#locates').val();
    if (selectedlang) {
        localStorage.setItem("lang", selectedlang);
        window.location.replace('?lang=' + selectedlang);
    }
});