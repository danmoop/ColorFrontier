    function submitProject()
    {
        var projectdata = $('textarea').froalaEditor('html.get');

        document.getElementById("contentInp").value = projectdata;
    }