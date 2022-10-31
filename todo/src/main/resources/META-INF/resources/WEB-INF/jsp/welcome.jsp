<%@ include file="common/taglibs.jspf"%>
<html>
<head>

    <%@ include file="common/css_imports.jspf"%>

    <title>Home</title>

</head>
<body>

    <%@ include file="common/navigation.jspf" %>

    <div class="container">

        <h1>Welcome, ${username}!</h1>
        <hr>
        <a href="todos">My To-Do List</a>

    </div>

    <%@ include file="common/js_imports.jspf"%>

</body>
</html>