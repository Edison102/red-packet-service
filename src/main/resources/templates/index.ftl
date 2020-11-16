<!DOCTYPE html>
<html lang="zh-cmn-Hans">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=0,viewport-fit=cover">
    <title>秒杀</title>
    <link rel="stylesheet" type="text/css" href="https://cdn.jsdelivr.net/npm/semantic-ui@2.4.2/dist/semantic.min.css">
    <script src="http://code.jquery.com/jquery-2.1.1.min.js"></script>
</head>
<body>
<div class="pusher">
    <div class="ui inverted vertical masthead center aligned segment">

        <div class="ui text container" style="margin-top: 50px">
            <form id="form">
                <h1 class="ui inverted header">
                    秒杀红包ID: <input type="text" id="redPacketId" name="redPacketId">
                </h1>
                <h2 class="ui inverted header">
                    <input type="radio" id="model" name="model" value="1" checked><label>多用户请求</label>
                    <input type="radio" id="model" name="model" value="2"> <label>单用户请求</label>
                </h2>
                <h1 class="ui inverted header">
                    请求次数: <input type="text" id="count" name="count">
                </h1>
            </form>
            <div class="ui huge primary button" style="margin-top: 50px" id="seckill">开始秒杀 <i
                        class="right arrow icon"></i></div>
        </div>

    </div>

</div>
</body>

<script>
    $(document).ready(function () {
        $("#seckill").click(function () {
            var redPacketId = $("#redPacketId").val()
            var model = $("input[type='radio']:checked").val()
            var count = $("#count").val()

            for (let i = 0; i < count; i++) {
                let user = model == 1 ? '用户' + i : '默认用户'
                let arr = {
                    user: user,
                    redPacketId: redPacketId
                }
                $.ajax(
                    {
                        url: "/api/red-packet-service/redpacket/grab",
                        type: "POST",
                        data: JSON.stringify(arr),
                        contentType: 'application/json; charset=utf-8',
                        dataType: 'json',
                        async: false,
                        success: function(msg) {
                            console.log(msg);
                        }
                    }
                );
            }
            alert("秒杀完成")
        });
    });
</script>

</html>
