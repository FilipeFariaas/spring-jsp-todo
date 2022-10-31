<%@ include file="common/taglibs.jspf"%>
<html>
<head>

    <%@ include file="common/css_imports.jspf"%>

    <title>TODO APP - LIST</title>

</head>
<body>

    <%@include file="common/navigation.jspf" %>

    <div class="container">

        <h1>${username}</h1>
        <hr>
        <h2>Your To-Do List</h2>

        <table class="table">

            <thead>
            <tr>
                <th>DESCRIPTION</th>
                <th>TARGET DATE</th>
                <th>DONE</th>
                <th></th>
                <th></th>
            </tr>
            </thead>

            <tbody>

                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td>${todo.description}</td>
                        <td>${todo.targetDate}</td>
                        <td>${todo.done}</td>
                        <td><a href="update-todo?id=${todo.id}" class="btn btn-success">UPDATE</a></td>
                        <td><a href="delete-todo?id=${todo.id}" class="btn btn-warning">DELETE</a></td>
                    </tr>
                </c:forEach>

            </tbody>

        </table>

        <a href="add-todo" class="btn btn-success">Add To-Do</a>

    </div>

    <%@ include file="common/js_imports.jspf"%>

</body>
</html>