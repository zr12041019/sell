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


</body>
</html>