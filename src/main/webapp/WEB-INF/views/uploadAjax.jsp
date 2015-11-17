<%--
  @author LeeSoohoon
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        .fileDrop {
            width: 100%;
            height: 200px;
            border: 1px dotted blue;
        }

        small {
            margin-left: 3px;
            font-weight: bold;
            color: grey;
        }
    </style>
</head>
<body>

<h3>Ajax File Upload</h3>
<div class="fileDrop"></div>

<div class="uploadedList"></div>

<script src="/resources/plugins/jQuery/jQuery-2.1.4.min.js"></script>
<script>
    $(".fileDrop").on("dragenter dragover", function (event) {
        event.preventDefault();
    });

    $(".fileDrop").on("drop", function (event) {
        event.preventDefault();

        var files = event.originalEvent.dataTransfer.files;
        var file = files[0];

        console.log(file);

        var formData = new FormData();
        formData.append("file", file);

        $.ajax ({
            url: '/uploadAjax',
            data: formData,
            dataType: 'text',
            processData: false,
            contentType: false,
            type: 'POST',
            success: function (data) {

                console.log(data);
                console.log(checkImageType(data));

                var str = "";

                if (checkImageType(data)) {
                    str = "<div>" +
                          "<a href='displayFile?fileName=" + getImageLink(data) + "'>" +
                          "<img src='displayFile?fileName=" + data + "'/>" + data +
                          "</a></div>";
                } else {
                    str = "<div>" +
                          "<a href='displayFile?fileName=" + data + "'>"
                          + getOriginalName(data) +
                          "</a></div>";
                }
                $(".uploadedList").append(str);
            }
        });
    });

    function checkImageType(fileName) {
        var pattern = /jpg|gif|png|jpeg/i;

        return fileName.match(pattern);
    }

    function getOriginalName(fileName) {
        if (checkImageType(fileName)) {
            return;
        }
        var idx = fileName.indexOf("_") + 1;
        return fileName.substr(idx);
    }

    function getImageLink(fileName) {
        if (!checkImageType(fileName)) {
            return;
        }

        var front = fileName.substr(0, 12);
        var end = fileName.substr(14);

        return front + end;
    }
</script>

</body>
</html>
