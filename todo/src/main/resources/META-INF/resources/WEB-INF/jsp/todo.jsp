<%@ include file="common/taglibs.jspf"%>
<html>

<head>

    <%@ include file="common/css_imports.jspf"%>

    <title>New To-Do</title>

</head>

<body>

    <%@ include file="common/navigation.jspf" %>

    <div class="container">

        <h1>Enter To-Do Details</h1>

        <form:form method="post" modelAttribute="todo">

            <fieldset class="mb-3">
                <form:label path="description">Description</form:label>
                <form:input type="text" name="description" path="description" required="required" />
                <form:errors path="description" cssClass="text-warning"/>
            </fieldset>

            <fieldset class="mb-3">
                <form:label path="targetDate">Target Date</form:label>
                <form:input type="text" name="targetDate" path="targetDate" required="required" />
                <form:errors path="targetDate" cssClass="text-warning"/>
            </fieldset>

            <form:input type="hidden" name="id" path="id"/>
            <form:input type="hidden" name="description" path="done"/>

            <input type="submit" class="btn btn-success">

        </form:form>

    </div>

    <%@ include file="common/js_imports.jspf"%>

    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datepicker/1.9.0/js/bootstrap-datepicker.min.js" integrity="sha512-T/tUfKSV1bihCnd+MxKD0Hm1uBBroVYBOYSk1knyvQ9VyZJpc/ALb4P0r6ubwVPSGB2GvjeoMAJJImBG12TiaQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

    <script type="text/javascript">
        $('#targetDate').datepicker({
            format: 'dd-mm-yyyy'
        });
    </script>

</body>

</html>