    function submitProject()
    {
        var projectdata = $('textarea').froalaEditor('html.get');

        document.getElementById("contentInp").value = projectdata;
    }

    function sendComment()
    {
        var projectname = document.title;
        document.getElementById("commentInp").value = projectname;
    }