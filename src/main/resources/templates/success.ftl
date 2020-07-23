<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>SUCCESSFULLY</title>
</head>
<body>
<h1 style="font-weight: 100;" >Submitted successfully <small id="countDown">Close after 3 seconds</small></h1>
</body>
<script>
    //倒计时
    var time = 4;
    var timeSet;
    function loadingclose() {
        time = time - 1;
        if (time === 0) {
            window.close();
        } else {
            document.getElementById("countDown").innerText = "Close after " + time + " seconds";
            timeSet = setTimeout(function () {
                loadingclose();
            }, 1000)
        }
    }
    loadingclose();
</script>
</html>