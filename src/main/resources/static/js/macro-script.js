$(document).ready(() => {
    var selectedIds = [];
    var datas = [];
    var totalCal = 0.0;
    var totalFat = 0.0;
    var totalCarb = 0.0;
    var totalProt = 0.0;
    var totalFiber = 0.0;

    $("#calories-goals, #carbs-goals, #fat-goals, #fiber-goals, #protein-goals").on('change', () => {
        if(parseFloat($("#calories-goals").val())<totalCal) {
            $("#calories-total").css("color", "red");
        } else {
            $("#calories-total").css("color", "rgba(0,0,0,0.42)");
        }
        if(parseFloat($("#fat-goals").val())<totalFat) {
            $("#fat-total").css("color", "red");
        } else {
            $("#fat-total").css("color", "rgba(0,0,0,0.42)");
        }
        if(parseFloat($("#carbs-goals").val())<totalCarb) {
            $("#carbs-total").css("color", "red");
        } else {
            $("#carbs-total").css("color", "rgba(0,0,0,0.42)");
        }
        if(parseFloat($("#protein-goals").val())<totalProt) {
            $("#protein-total").css("color", "red");
        } else {
            $("#protein-total").css("color", "rgba(0,0,0,0.42)");
        }
        if(parseFloat($("#fiber-goals").val())<totalFiber) {
            $("#fiber-total").css("color", "red");
        } else {
            $("#fiber-total").css("color", "rgba(0,0,0,0.42)");
        }
    });

    $(".recipe-add-btn").click((e) => {
        const id = e.target.id;
        if(selectedIds.includes(id)) {
            var newVal = parseInt($("#"+id+"-row td:nth-child(7)").text()) + 1;
            $("#"+id+"-row td:nth-child(7)").text(newVal);
            $("#"+id+"-row input:nth-child(9)").attr("value", newVal+"");
            var idx = selectedIds.indexOf(id);
            var obj = datas[idx];
            totalCal += obj.calories;
            totalCarb += obj.carb;
            totalFat += obj.fat;
            totalProt += obj.prot;
            totalFiber += obj.fiber;
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
                "    <input type=\"hidden\" name=\"recipe\" value=\"" + $("#" + id + "-id").attr('value') + "\"/>\n" +
                "    <input type=\"hidden\" name=\"recipeAmount\" value=\"1\"/>\n" +
                "</tr>";
            $("#item-insert").append(content);
            selectedIds.push(id);
            datas.push({
                calories: parseFloat($("#" + id + "-cal").attr('value')),
                fat: parseFloat($("#" + id + "-fat").attr('value')),
                carb: parseFloat($("#" + id + "-carb").attr('value')),
                prot: parseFloat($("#" + id + "-prot").attr('value')),
                fiber: parseFloat($("#" + id + "-fiber").attr('value'))
            });
            totalCal += parseFloat($("#" + id + "-cal").attr('value'));
            totalFat += parseFloat($("#" + id + "-fat").attr('value'));
            totalCarb += parseFloat($("#" + id + "-carb").attr('value'));
            totalProt += parseFloat($("#" + id + "-prot").attr('value'));
            totalFiber += parseFloat($("#" + id + "-fiber").attr('value'));
        }
        $("#calories-total").attr("value", totalCal+"");
        if(parseFloat($("#calories-goals").val())<totalCal) {
            $("#calories-total").css("color", "red");
        } else {
            $("#calories-total").css("color", "rgba(0,0,0,0.42)");
        }
        $("#fat-total").attr("value", totalFat+"");
        if(parseFloat($("#fat-goals").val())<totalFat) {
            $("#fat-total").css("color", "red");
        } else {
            $("#fat-total").css("color", "rgba(0,0,0,0.42)");
        }
        $("#carbs-total").attr("value", totalCarb+"");
        if(parseFloat($("#carbs-goals").val())<totalCarb) {
            $("#carbs-total").css("color", "red");
        } else {
            $("#carbs-total").css("color", "rgba(0,0,0,0.42)");
        }
        $("#protein-total").attr("value", totalProt+"");
        if(parseFloat($("#protein-goals").val())<totalProt) {
            $("#protein-total").css("color", "red");
        } else {
            $("#protein-total").css("color", "rgba(0,0,0,0.42)");
        }
        $("#fiber-total").attr("value", totalFiber+"");
        if(parseFloat($("#fiber-goals").val())<totalFiber) {
            $("#fiber-total").css("color", "red");
        } else {
            $("#fiber-total").css("color", "rgba(0,0,0,0.42)");
        }
    });

    $(".item-add-btn").click((e) => {
        const id = e.target.id;
        if(selectedIds.includes(id)) {
            var newVal = parseInt($("#"+id+"-row td:nth-child(7)").text()) + 1;
            $("#"+id+"-row td:nth-child(7)").text(newVal);
            $("#"+id+"-row input:nth-child(9)").attr("value", newVal+"");
            var idx = selectedIds.indexOf(id);
            var obj = datas[idx];
            totalCal += obj.calories;
            totalCarb += obj.carb;
            totalFat += obj.fat;
            totalProt += obj.prot;
            totalFiber += obj.fiber;
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
                "    <input type=\"hidden\" name=\"item\" value=\"" + $("#" + id + "-id").attr('value') + "\"/>\n" +
                "    <input type=\"hidden\" name=\"itemAmount\" value=\"1\"/>\n" +
                "</tr>";
            $("#item-insert").append(content);
            selectedIds.push(id);
            datas.push({
                calories: parseFloat($("#" + id + "-cal").attr('value')),
                fat: parseFloat($("#" + id + "-fat").attr('value')),
                carb: parseFloat($("#" + id + "-carb").attr('value')),
                prot: parseFloat($("#" + id + "-prot").attr('value')),
                fiber: parseFloat($("#" + id + "-fiber").attr('value'))
            });
            totalCal += parseFloat($("#" + id + "-cal").attr('value'));
            totalFat += parseFloat($("#" + id + "-fat").attr('value'));
            totalCarb += parseFloat($("#" + id + "-carb").attr('value'));
            totalProt += parseFloat($("#" + id + "-prot").attr('value'));
            totalFiber += parseFloat($("#" + id + "-fiber").attr('value'));
        }
        $("#calories-total").attr("value", totalCal+"");
        if(parseFloat($("#calories-goals").attr("value"))<totalCal) {
            $("#calories-total").css("color", "red");
        } else {
            $("#calories-total").css("color", "rgba(0,0,0,0.42)");
        }
        $("#fat-total").attr("value", totalFat+"");
        if(parseFloat($("#fat-goals").attr("value"))<totalFat) {
            $("#fat-total").css("color", "red");
        } else {
            $("#fat-total").css("color", "rgba(0,0,0,0.42)");
        }
        $("#carbs-total").attr("value", totalCarb+"");
        if(parseFloat($("#carbs-goals").attr("value"))<totalCarb) {
            $("#carbs-total").css("color", "red");
        } else {
            $("#carbs-total").css("color", "rgba(0,0,0,0.42)");
        }
        $("#protein-total").attr("value", totalProt+"");
        if(parseFloat($("#protein-goals").attr("value"))<totalProt) {
            $("#protein-total").css("color", "red");
        } else {
            $("#protein-total").css("color", "rgba(0,0,0,0.42)");
        }
        $("#fiber-total").attr("value", totalFiber+"");
        if(parseFloat($("#fiber-goals").attr("value"))<totalFiber) {
            $("#fiber-total").css("color", "red");
        } else {
            $("#fiber-total").css("color", "rgba(0,0,0,0.42)");
        }
    });
});