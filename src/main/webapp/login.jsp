<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login page</title>
</head>
<body>

    <div class="login_form">

        <h3>Login form</h3>

        <form action="/register" method="post">
            Login:<input type="text" name="userLogin"/><br/><br/>
            Password:<input type="password" name="userPass"/><br/><br/>
            <input type="submit" value="Login!"/>
        </form>

        <form action="/Testing/index.jsp">
            <button class = "back_button" type="submit">
                Back!
            </button>
        </form>

    </div>

</body>
</html>
