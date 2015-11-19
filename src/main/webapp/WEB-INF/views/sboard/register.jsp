<%--
  @author LeeSoohoon
--%>
<%@page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@include file="../include/header.jsp" %>

<style>
    .fileDrop {
        width: 80%;
        height: 100px;
        border: 1px dotted gray;
        background-color: lightslategray;
        margin: auto;
    }
</style>

<section class="content">
    <div class="row">
        <div class="col-md-12">
            <div class="box box-primary">
                <div class="box-header">
                    <h3 class="box-title">REGISTER BOARD</h3>
                </div>

                <form id="registerForm" role="form" method="post">
                    <div class="box-body">
                        <div class="form-group">
                            <label for="title">Title</label>
                            <input type="text" id="title" name="title" class="form-control"
                                   placeholder="Enter Title"/>
                        </div>
                        <div class="form-group">
                            <label for="content">Content</label>
                            <textarea class="form-control" id="content" name="content" rows="3"
                                      placeholder="Enter ..."></textarea>
                        </div>
                        <div class="form-group">
                            <label for="writer">Writer</label>
                            <input type="text" id="writer" name="writer" class="form-control"
                                   placeholder="Enter Writer"/>
                        </div>
                        <div class="form-group">
                            <label for="exampleInputEmail1">File DROP Here</label>
                            <div class="fileDrop"></div>
                        </div>
                    </div>

                    <div class="box-footer">
                        <div>
                            <hr>
                        </div>

                        <ul class="mailbox-attachments clearfix uploadedList"></ul>

                        <button type="submit" class="btn btn-primary">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</section>
</div>

<script src="/resources/js/upload.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.0.4/handlebars.js"></script>
<script id="template" type="text/x-handlebars-template">
    <li>
        <span class="mailbox-attachment-icon has-img"><img src="{{imgsrc}}" alt="Attachment"></span>
        <div class="mailbox-attachment-info">
            <a href="{{getLink}}" class="mailbox-attachment-name">{{fileName}}</a>
            <a href="{{fullName}}"
               class="btn btn-default btn-xs pull-right delbtn"><i class="fa fa-fw fa-remove"></i></a>
            </span>
        </div>
    </li>
</script>

<script>
    var template = Handlebars.compile($("#template").html());

    $(".fileDrop").on("dragenter dragover", function (event) {
        event.preventDefault();
    });

    $(".fileDrop").on("drop", function (event) {
        event.preventDefault();

        var files = event.originalEvent.dataTransfer.files;
        var file = files[0];
        var formData = new FormData();

        formData.append("file", file);

        $.ajax({
                   url: "/uploadAjax",
                   data: formData,
                   dataType: "text",
                   processData: false,
                   contentType: false,
                   type: "POST",
                   success: function (data) {
                       var fileInfo = getFileInfo(data);
                       var html = template(fileInfo);

                       $(".uploadedList").append(html);
                   }
               });
    });

    $("#registerForm").submit(function (event) {
        event.preventDefault();

        var that = $(this);
        var str = "";

        $(".uploadedList .delbtn").each(function (index) {
            str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).attr("href") + "'/>";
        });

        that.append(str);
        that.get(0).submit();
    });

    $(".uploadedList").on("click", ".delbtn", function (event) {
        event.preventDefault();

        var that = $(this).parent();

        $.ajax({
                   url: "/deleteFile",
                   type: "post",
                   data: {
                       fileName: $(this).attr("href")
                   },
                   dataType: "text",
                   success: function (result) {
                       if (result == "deleted") {
                           that.parent("li").remove();
                       }
                   }
               });
    });
</script>

<%@include file="../include/footer.jsp" %>
