function PostSubmit(url, args) {
    layui.use(['jquery'], function () {
        var $ = layui.jquery;
        var body = $(document.body);
        var form = $("<form method='post'></form>");
        var input;
        var csrf = $("meta[name='_csrf']");
        var token = csrf.attr("content");

        form.attr({"action": url});
        input = $("<input type='hidden' name='_csrf' value='" + token + "'/>");
        form.append(input);
        $.each(args, function (key, val) {
            input = $("<input type='hidden'/>");
            input.attr({"name": key});
            input.val(val);
            form.append(input);
        });

        form.appendTo(document.body);
        form.submit().remove();
        //document.body.removeChild(form[0]);
        return;
    });
}
