$(document).ready(() => {
    var selectedIds = [];
    var datas = [];
    var totalCal = 0.0;
    var totalFat = 0.0;
    var totalCarb = 0.0;
    var totalProt = 0.0;
    var totalFiber = 0.0;

    var addEditButtons = $(".add-remove-btn");

    const resetTotalColors = () => {
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
    };

    setTimeout(() => {
        $(".obj-ids").each((index, element) => {
            selectedIds.push($(element).attr("id").substring(0, $(element).attr("id").length-4));
            datas.push({
                calories: parseFloat($(element).children("td:nth-child(2)").text()),
                fat: parseFloat($(element).children("td:nth-child(3)").text()),
                carb: parseFloat($(element).children("td:nth-child(4)").text()),
                prot: parseFloat($(element).children("td:nth-child(5)").text()),
                fiber: parseFloat($(element).children("td:nth-child(6)").text())
            });
            totalCal += parseFloat($(element).children("td:nth-child(2)").text())*parseFloat($(element).children("td:nth-child(7)").text());
            totalFat += parseFloat($(element).children("td:nth-child(3)").text())*parseFloat($(element).children("td:nth-child(7)").text());
            totalCarb += parseFloat($(element).children("td:nth-child(4)").text())*parseFloat($(element).children("td:nth-child(7)").text());
            totalProt += parseFloat($(element).children("td:nth-child(5)").text())*parseFloat($(element).children("td:nth-child(7)").text());
            totalFiber += parseFloat($(element).children("td:nth-child(6)").text())*parseFloat($(element).children("td:nth-child(7)").text());
        });
        $("#calories-total").attr("value", totalCal+"");
        $("#fat-total").attr("value", totalFat+"");
        $("#carbs-total").attr("value", totalCarb+"");
        $("#protein-total").attr("value", totalProt+"");
        $("#fiber-total").attr("value", totalFiber+"");
        resetTotalColors();
    }, 300);

    $("#calories-goals, #carbs-goals, #fat-goals, #fiber-goals, #protein-goals").on('change', () => {
        resetTotalColors();
    });

    $(".add-remove-btn").click((e) => {
        var id = e.target.id;
        var mode = id.substring(id.length-4);
        console.log(e);
        id = id.substring(0, id.length-4);
        if(mode == "-add") {
            console.log("click add");
            var newVal = parseInt($("#"+id+"-row td:nth-child(7)").text()) + 1;
            $("#"+id+"-row td:nth-child(7)").text(newVal);
            $("#"+id+"-row input:nth-child(10)").attr("value", newVal+"");
            var idx = selectedIds.indexOf(id);
            var obj = datas[idx];
            totalCal += obj.calories;
            totalCarb += obj.carb;
            totalFat += obj.fat;
            totalProt += obj.prot;
            totalFiber += obj.fiber;
        } else {
            console.log("click minus");
            if(parseInt($("#"+id+"-row td:nth-child(7)").text()) > 0) {
                var newVal = parseInt($("#"+id+"-row td:nth-child(7)").text()) - 1;
                $("#"+id+"-row td:nth-child(7)").text(newVal);
                $("#"+id+"-row input:nth-child(10)").attr("value", newVal+"");
                var idx = selectedIds.indexOf(id);
                var obj = datas[idx];
                totalCal -= obj.calories;
                totalCarb -= obj.carb;
                totalFat -= obj.fat;
                totalProt -= obj.prot;
                totalFiber -= obj.fiber;
            }
        }
        $("#calories-total").attr("value", totalCal+"");
        $("#fat-total").attr("value", totalFat+"");
        $("#carbs-total").attr("value", totalCarb+"");
        $("#protein-total").attr("value", totalProt+"");
        $("#fiber-total").attr("value", totalFiber+"");
        resetTotalColors();
    });

    $(".recipe-add-btn").click((e) => {
        const id = e.target.id;
        if(selectedIds.includes(id)) {
            var newVal = parseInt($("#"+id+"-row td:nth-child(7)").text()) + 1;
            $("#"+id+"-row td:nth-child(7)").text(newVal);
            $("#"+id+"-row input:nth-child(10)").attr("value", newVal+"");
            var idx = selectedIds.indexOf(id);
            var obj = datas[idx];
            totalCal += obj.calories;
            totalCarb += obj.carb;
            totalFat += obj.fat;
            totalProt += obj.prot;
            totalFiber += obj.fiber;
        } else {
            var content =
                "<tr id=\"" + id + "-row\" class=\"obj-ids\">\n" +
                "    <td>" + $("#" + id + "-name").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-cal").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-fat").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-carb").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-prot").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-fiber").attr('value') + "</td>\n" +
                "    <td>1</td>\n" +
                "    <td>\n" +
                "        <i class=\"material-icons add-remove-btn\" id=\""+id+"-add\">add</i>\n" +
                "        <i class=\"material-icons add-remove-btn\" id=\""+id+"-min\">remove</i>\n" +
                "    </td>\n" +
                "    <input type=\"hidden\" name=\"recipe\" value=\"" + $("#" + id + "-id").attr('value') + "\"/>\n" +
                "    <input type=\"hidden\" name=\"recipeAmount\" value=\"1\"/>\n" +
                "</tr>";
            $("#item-insert").append(content);
            $(".add-remove-btn").unbind("click");
            $(".add-remove-btn").click((e) => {
                var id = e.target.id;
                var mode = id.substring(id.length-4);
                console.log(e);
                id = id.substring(0, id.length-4);
                if(mode == "-add") {
                    console.log("click add");
                    var newVal = parseInt($("#"+id+"-row td:nth-child(7)").text()) + 1;
                    $("#"+id+"-row td:nth-child(7)").text(newVal);
                    $("#"+id+"-row input:nth-child(10)").attr("value", newVal+"");
                    var idx = selectedIds.indexOf(id);
                    var obj = datas[idx];
                    totalCal += obj.calories;
                    totalCarb += obj.carb;
                    totalFat += obj.fat;
                    totalProt += obj.prot;
                    totalFiber += obj.fiber;
                } else {
                    console.log("click minus");
                    if(parseInt($("#"+id+"-row td:nth-child(7)").text()) > 0) {
                        var newVal = parseInt($("#"+id+"-row td:nth-child(7)").text()) - 1;
                        $("#"+id+"-row td:nth-child(7)").text(newVal);
                        $("#"+id+"-row input:nth-child(10)").attr("value", newVal+"");
                        var idx = selectedIds.indexOf(id);
                        var obj = datas[idx];
                        totalCal -= obj.calories;
                        totalCarb -= obj.carb;
                        totalFat -= obj.fat;
                        totalProt -= obj.prot;
                        totalFiber -= obj.fiber;
                    }
                }
                $("#calories-total").attr("value", totalCal+"");
                $("#fat-total").attr("value", totalFat+"");
                $("#carbs-total").attr("value", totalCarb+"");
                $("#protein-total").attr("value", totalProt+"");
                $("#fiber-total").attr("value", totalFiber+"");
                resetTotalColors();
            });
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
        $("#fat-total").attr("value", totalFat+"");
        $("#carbs-total").attr("value", totalCarb+"");
        $("#protein-total").attr("value", totalProt+"");
        $("#fiber-total").attr("value", totalFiber+"");
        resetTotalColors();
    });

    $(".item-add-btn").click((e) => {
        const id = e.target.id;
        if(selectedIds.includes(id)) {
            var newVal = parseInt($("#"+id+"-row td:nth-child(7)").text()) + 1;
            $("#"+id+"-row td:nth-child(7)").text(newVal);
            $("#"+id+"-row input:nth-child(10)").attr("value", newVal+"");
            var idx = selectedIds.indexOf(id);
            var obj = datas[idx];
            totalCal += obj.calories;
            totalCarb += obj.carb;
            totalFat += obj.fat;
            totalProt += obj.prot;
            totalFiber += obj.fiber;
        } else {
            var content =
                "<tr id=\"" + id + "-row\" class=\"obj-ids\">\n" +
                "    <td>" + $("#" + id + "-name").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-cal").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-fat").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-carb").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-prot").attr('value') + "</td>\n" +
                "    <td>" + $("#" + id + "-fiber").attr('value') + "</td>\n" +
                "    <td>1</td>\n" +
                "    <td>\n" +
                "        <i class=\"material-icons add-remove-btn\" id=\""+id+"-add\">add</i>\n" +
                "        <i class=\"material-icons add-remove-btn\" id=\""+id+"-min\">remove</i>\n" +
                "    </td>\n" +
                "    <input type=\"hidden\" name=\"item\" value=\"" + $("#" + id + "-id").attr('value') + "\"/>\n" +
                "    <input type=\"hidden\" name=\"itemAmount\" value=\"1\"/>\n" +
                "</tr>";
            $("#item-insert").append(content);
            $(".add-remove-btn").unbind("click");
            $(".add-remove-btn").bind("click", (e) => {
                var id = e.target.id;
                var mode = id.substring(id.length-4);
                console.log(e);
                id = id.substring(0, id.length-4);
                if(mode == "-add") {
                    console.log("click add");
                    var newVal = parseInt($("#"+id+"-row td:nth-child(7)").text()) + 1;
                    $("#"+id+"-row td:nth-child(7)").text(newVal);
                    $("#"+id+"-row input:nth-child(10)").attr("value", newVal+"");
                    var idx = selectedIds.indexOf(id);
                    var obj = datas[idx];
                    totalCal += obj.calories;
                    totalCarb += obj.carb;
                    totalFat += obj.fat;
                    totalProt += obj.prot;
                    totalFiber += obj.fiber;
                } else {
                    console.log("click minus");
                    if(parseInt($("#"+id+"-row td:nth-child(7)").text()) > 0) {
                        var newVal = parseInt($("#"+id+"-row td:nth-child(7)").text()) - 1;
                        $("#"+id+"-row td:nth-child(7)").text(newVal);
                        $("#"+id+"-row input:nth-child(10)").attr("value", newVal+"");
                        var idx = selectedIds.indexOf(id);
                        var obj = datas[idx];
                        totalCal -= obj.calories;
                        totalCarb -= obj.carb;
                        totalFat -= obj.fat;
                        totalProt -= obj.prot;
                        totalFiber -= obj.fiber;
                    }
                }
                $("#calories-total").attr("value", totalCal+"");
                $("#fat-total").attr("value", totalFat+"");
                $("#carbs-total").attr("value", totalCarb+"");
                $("#protein-total").attr("value", totalProt+"");
                $("#fiber-total").attr("value", totalFiber+"");
                resetTotalColors();
            });
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
        $("#fat-total").attr("value", totalFat+"");
        $("#carbs-total").attr("value", totalCarb+"");
        $("#protein-total").attr("value", totalProt+"");
        $("#fiber-total").attr("value", totalFiber+"");
        resetTotalColors();
    });
});