<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录</title>
    <base href="<%=request.getContextPath()+"/"%>"/>
    <link rel="stylesheet" href="resources/css/material-icons.css">
    <link rel="stylesheet" href="resources/css/material.min.css">
    <link rel="stylesheet" href="resources/css/style.css">
    <script type="text/javascript" src="resources/js/material.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery-3.1.1.min.js"></script>
</head>
<body>

<div class="mdl-layout mdl-js-layout">
    <header class="mdl-layout__header mdl-layout__header--scroll mdl-color--grey-50">
        <div class="mdl-layout__header-row">
            <a class="mdl-layout-title mdl-navigation__link mdl-color-text--pink-400"  href="javascript:void(0)">神马论坛</a>
            <div class="mdl-layout-spacer"></div>
            <nav class="mdl-navigation"></nav>
        </div>
    </header>
    <main class="mdl-layout__content">
        <div class="page-content">
            <div class="mdl-grid">
                <div class="mdl-cell mdl-cell-4"></div>
                <div class="mdl-cell mdl-cell-4">
                    <form action="user/userLogin" method="post">
                        <div class="mdl-card mdl-shadow--2dp center" align="center">
                            <div class="mdl-card__title">
                                <h5 class="mdl-color-text--pink-400">用户登录</h5>
                            </div>
                            <div class="mdl-card__subtitle-text">
                                <div class="mdl-textfield mdl-js-textfield">
                                    <input class="mdl-textfield__input" type="text" id="userName" name="userName">
                                    <label class="mdl-textfield__label" for="userName">用户名</label>
                                </div>
                                <div class="mdl-textfield mdl-js-textfield">
                                    <input class="mdl-textfield__input" type="password" id="password" name="password">
                                    <label class="mdl-textfield__label" for="password">密码</label>
                                </div>
                            </div>
                            <div class="mdl-card__actions">
                                <input class="mdl-button mdl-button--raised mdl-js-button mdl-color-text--white mdl-color--pink-400 mdl-color-text--white"
                                       type="submit" value="登录" name="submit">
                                <a class="mdl-button mdl-js-button mdl-button--raised" href="userRegister">注册</a>
                            </div>
                        </div>
                    </form>
                </div>
                <div class="mdl-cell mdl-cell-4"></div>
            </div>
        </div>
    </main>
</div>
</body>
</html>