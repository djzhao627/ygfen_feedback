<!DOCTYPE html>
<#assign ctx=request.contextPath>
<html>
<head>
    <meta charset="utf-8">
    <title>首页</title>
    <link href="${ctx}/css/bootstrap.css" rel="stylesheet">
    <link href="${ctx}/css/bootstrap-table.css" rel="stylesheet">
    <style>
        body {
            position: relative;
        }

        #message {
            position: absolute;
            width: 100%;
            top: 10px;
            left: 0;
            display: none;
        }
    </style>
</head>
<body>
<div id="message" class="row">
    <div class="alert alert-success col-md-6 col-md-offset-6" role="alert">更新成功！</div>
</div>

<div class="container">

    <div class="row">
        <h2 id="test">留言列表 </h2>
        <hr>
        <table id="table" data-toggle="table" data-side-pagination="server" data-search="true"
               data-pagination="true" data-query-params="queryParams" data-pagination-loop="false"
               data-click-to-select="true" data-url="${ctx}/getTableData">
            <thead>
            <tr>
                <th data-checkbox="true"></th>
                <th data-field="companyName" data-align="center">公司名称</th>
                <th data-field="personalName" data-align="center">个人姓名</th>
                <th data-field="mobile" data-align="center">手机</th>
                <th data-field="email" data-formatter="email" data-align="center">邮箱</th>
                <th data-field="content" data-align="center">内容</th>
                <th data-field="createTime" data-align="center" data-sortable="true" data-sort-name="create_time">提交时间
                </th>
            </tr>
            </thead>
        </table>
    </div>
</div>
<script src="${ctx}/js/jquery.min.js"></script>
<script src="${ctx}/js/bootstrap.js"></script>
<script src="${ctx}/js/bootstrap-table.js"></script>
<script src="${ctx}/js/bootstrap-table-zh-CN.js"></script>
<script src="${ctx}/js/countUp.js"></script>
<script type="text/javascript">
    
    function index(index, row) {
        return index + 1;
    }

    function email(value) {
        return "<a href='mailto://" + value + "'>" + value + "</a>"
    }

    function queryParams(params) {
        var query = {};
        query["limit"] = params.limit;//第几条数据开始
        query["offset"] = params.offset;//数据大小
        query["search"] = params.search;//搜索
        query["sort"] = params.sort;//搜索字段
        query["order"] = params.order;//排序
        return query;
    }

</script>
</body>
</html>