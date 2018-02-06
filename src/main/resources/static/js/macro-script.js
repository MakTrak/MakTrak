$(document).ready(() => {
    var selectedIds = [];

    $(".recipe-add-btn").click((e) => {
        const id = e.target.id;
        if(selectedIds.includes(id)) {
            var newVal = parseInt($("#"+id+"-row td:nth-child(7)").text()) + 1;
            $("#"+id+"-row td:nth-child(7)").text(newVal);
            $("#"+id+"-row input:nth-child(9)").attr("th:value", newVal+"");
        } else {
            var content =
                "<tr id=\"" + id + "-row\">\n" +
                "    <td>" + $("#" + id + "-name").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-cal").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-fat").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-carb").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-prot").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-fiber").attr('value') + "</td>\n" +
                "    <td>1</td>\n" +
                "    <input type=\"hidden\" name=\"recipe\" th:value=\"" + $("#" + id + "-id").attr('value') + "\"/>\n" +
                "    <input type=\"hidden\" name=\"recipeAmount\" th:value=\"1\"/>\n" +
                "</tr>";
            $("#item-insert").append(content);
            selectedIds.push(id);
        }
    });

    $(".item-add-btn").click((e) => {
        const id = e.target.id;
        if(selectedIds.includes(id)) {
            var newVal = parseInt($("#"+id+"-row td:nth-child(7)").text()) + 1;
            $("#"+id+"-row td:nth-child(7)").text(newVal);
            $("#"+id+"-row input:nth-child(9)").attr("th:value", newVal+"");
        } else {
            var content =
                "<tr id=\"" + id + "-row\">\n" +
                "    <td>" + $("#" + id + "-name").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-cal").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-fat").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-carb").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-prot").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-fiber").attr('value') + "</td>\n" +
                "    <td>1</td>\n" +
                "    <input type=\"hidden\" name=\"item\" th:value=\"" + $("#" + id + "-id").attr('value') + "\"/>\n" +
                "    <input type=\"hidden\" name=\"itemAmount\" th:value=\"1\"/>\n" +
                "</tr>";
            $("#item-insert").append(content);
            selectedIds.push(id);
        }
    });
});