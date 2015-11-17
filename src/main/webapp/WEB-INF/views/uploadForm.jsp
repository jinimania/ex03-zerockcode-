<%--
  @author LeeSoohoon
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
    <style>
        iframe {
            width: 0;
            height: 0;
            border: 0;
        }
    </style>
</head>
<body>
<form id="form1" action="uploadForm" method="post" enctype="multipart/form-data" target="zeroFrame">
    <input type="file" name="file"/>
    <input type="submit"/>
</form>

<iframe name="zeroFrame"></iframe>

<script>
    function addFilePath(msg) {
        alert(msg);
        document.getElementById("form1").reset();
    }
</script>
</body>
</html>
