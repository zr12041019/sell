<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>操作成功</title>
    <link href="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet">
</head>
<script type="text/javascript">
    window.onload = function(){
        //1.首先声明seconds
        var seconds = 2;
        //2.声明定时器
        var timer = null;
        //3.开启定时器
        timer = setInterval(show,1000);
        //开启定时器后要执行的函数
        function show(){
            if(seconds==0){
                clearInterval(timer);//清除定时器
                window.location.href = "${url}";//跳转到百度首页
                return;
            }
            //将不断变化的秒数显示在页面上
            document.getElementById('spanTime').innerText = seconds + "秒后自动跳转";
            seconds--;
        }
    };
</script>
<body>
<div class="container">
    <div class="row clearfix">
        <div class="col-md-12 column">
            <div class="alert alert-dismissable alert-success">
                <button type="button" class="close" data-dismiss="alert" aria-hidden="true">×</button>
                <h4>
                    成功!
                </h4> <strong>${msg!""}</strong><a href="${url}" id="spanTime" class="alert-link">3秒后自动跳转</a>
            </div>
        </div>
    </div>
</div>
</body>
</html>