<!DOCTYPE html>
<html lang="en">
<#include "../common/header.ftl">
<body>
<div id="wrapper" class="toggled">
    <#--    主要类容-->
    <div id="page-content-wrapper">
        <div class="row clearfix">
            <div class="col-md-3 column">
            </div>
            <div class="col-md-4 column">
                <h1 class="text-center text-error">
                    卖家管理系统-登陆
                </h1>
                <form class="form-horizontal" role="form" method="post" action="/sell/seller/login">
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" class="form-control" id="inputEmail3" name="username" />
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputPassword3" class="col-sm-2 control-label">密码</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="inputPassword3" name="password"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">登陆</button>
                            <a type="button" class="btn btn-default" href="/sell/seller/registered">去注册</a>
                        </div>
                    </div>
                </form>
            </div>
            <div class="col-md-5 column">
            </div>
        </div>
    </div>
</div>
</body>
</html>

