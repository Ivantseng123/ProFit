$(function() {
    var dialog, form,
        name = $("#name"),
        tips = $(".validateTips");

    function updateTips(t) {
        tips
            .text(t)
            .addClass("ui-state-highlight");
        setTimeout(function() {
            tips.removeClass("ui-state-highlight", 1500);
        }, 500);
    }

    function checkLength(o, n, min, max) {
        if (o.val().length > max || o.val().length < min) {
            o.addClass("ui-state-error");
            updateTips(n + " 的長度必須在 " + min + " 到 " + max + " 個字元之間。");
            return false;
        } else {
            return true;
        }
    }

    function addModuleName() {
        var valid = true;
        name.removeClass("ui-state-error");

        valid = valid && checkLength(name, "模組名稱", 3, 16);

        if (valid) {
            var rowCount = $("#users tbody tr").length + 1;

            $("#users tbody").append("<tr>" +
                "<td style='text-align: center'>" + rowCount + "</td>" +
                "<td>" + name.val() + "</td>" +
                "<td class='action-buttons'>" +
                "<button class='view'>查看</button> " +
                "<button class='edit'>編輯</button> " +
                "<button class='delete'>刪除</button>" +
                "</td>" +
            "</tr>");

            dialog.dialog("close");
        }
        return valid;
    }

    dialog = $("#dialog-form").dialog({
        autoOpen: false,
        height: 300,
        width: 500,
        modal: true,
        buttons: {
            "Create Module": addModuleName,
            Cancel: function() {
                dialog.dialog("close");
            }
        },
        close: function() {
            form[0].reset();
            name.removeClass("ui-state-error");
        }
    });

    form = $("#dialog-form form").on("submit", function(event) {
        event.preventDefault();
        addModuleName();
    });

    $("#create-Module").button().on("click", function() {
        dialog.dialog("open");
    });
});