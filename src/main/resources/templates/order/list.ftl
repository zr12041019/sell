<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<body>

<div id="wrapper" class="toggled">

    <#--    左边栏-->

    <#include "../common/nav.ftl">
    <#--    主要类容-->
    <div id="page-content-wrapper">
        <div class="container-fluid">
            <div class="row clearfix">
                <div class="col-md-12 column">
                    <table class="table table-hover table-bordered table-condensed">
                        <thead>
                        <tr>
                            <th>订单ID</th>
                            <th>姓名</th>
                            <th>手机号</th>
                            <th>地址</th>
                            <th>金额</th>
                            <th>订单状态</th>
                            <th>支付状态</th>
                            <th>创建时间</th>
                            <th colspan="2">操作</th>
                        </tr>
                        </thead>
                        <tbody>
                        <#list orderDtoPage.content as orderDto>
                            <tr>
                                <td>${orderDto.orderId}</td>
                                <td>${orderDto.buyerName}</td>
                                <td>${orderDto.buyerPhone}</td>
                                <td>${orderDto.buyerAddress}</td>
                                <td>${orderDto.orderAmount}</td>
                                <td>${orderDto.orderStatusEnum.msg}</td>
                                <td>${orderDto.payStatusEnum.msg}</td>
                                <td>${orderDto.createTime}</td>
                                <td>
                                    <a href="/sell/seller/order/detail?orderId=${orderDto.orderId}">详情</a>
                                </td>
                                <td>
                                    <#if orderDto.orderStatusEnum.msg == "新订单">
                                        <a href="/sell/seller/order/cancel?orderId=${orderDto.orderId}">取消</a>
                                    </#if>
                                </td>
                            </tr>
                        </#list>
                        </tbody>
                    </table>
                </div>
            </div>
            <#-- 分页 -->
            <div class="col-md-12 column" >
                <ul class="pagination pagination-sm pull-right">
                    <#if currentPage lte 1>
                        <li class="disabled">
                            <a href="#">上一页</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/sell/seller/order/list?page=${currentPage - 1}&size=${pageSize}">上一页</a>
                        </li>
                    </#if>
                    <#list 1..orderDtoPage.totalPages as index >
                        <#if currentPage == index>
                            <li class="disabled">
                                <a href="#">${index}</a>
                            </li>
                        <#else>
                            <li>
                                <a href="/sell/seller/order/list?page=${index}&size=${pageSize}">${index}</a>
                            </li>
                        </#if>
                    </#list>
                    <#if currentPage gte orderDtoPage.totalPages>
                        <li class="disabled">
                            <a href="#">下一页</a>
                        </li>
                    <#else>
                        <li>
                            <a href="/sell/seller/order/list?page=${currentPage + 1}&size=${pageSize}">下一页</a>
                        </li>
                    </#if>
                </ul>
            </div>
        </div>
    </div>
</div>

<div class="modal fade" id="myModal" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">
                    提醒
                </h4>
            </div>
            <div class="modal-body">
                你有新的订单,请注意查收！
            </div>
            <div class="modal-footer">
                <button type="button" onclick="javascript:document.getElementById('notice').pause()" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" onclick="location.reload()" class="btn btn-primary">查看新的订单</button>
            </div>
        </div>

    </div>

</div>
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://cdn.bootcss.com/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
<audio id="notice" loop="loop">
    <source src="/sell/mp3/song.mp3" type="audio/mpeg">
</audio>
<script>
    var websocket = null;
    if('WebSocket' in window){
        websocket = new WebSocket('ws://localhost:8080/sell/webSocket');
        websocket.onopen=function (event) {
            console.log("建立连接");
        };

        websocket.onclose=function (event) {
            console.log("连接关闭");
        };

        websocket.onmessage=function (event) {
            console.log("收到消息:" + event.data);
            //弹窗
            $('#myModal').modal('show');
            //播放音乐
            document.getElementById('notice').play();
        };

        websocket.onerror=function () {
            console.log("websocket通信发生错误");
        };

        window.onbeforeunload = function () {
            websocket.close();
        };
    }else{
        console.log("该浏览器不支持WebSocket");
    }

</script>

</body>
</html>