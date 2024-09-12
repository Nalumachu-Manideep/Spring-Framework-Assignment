<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<html>
    <head>
        <title>
            Save Product
        </title>

        <link type="text/css"
                      rel="stylesheet"
                      href="${pageContext.request.contextPath}/resources/css/add-customer.css" />
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <h2>E Commerce Featured Products</h2>
            </div>
        </div>
        <div id="container">
            <h3>Save Customer</h3>
            <form:form action="saveOrder" modelAttribute="order" method="POST">
                <form:hidden path="id" />
                <table>
                    <tbody>
                        <tr>
                            <td><label>Order Date:</label></td>
                            <td>
                                <form:input path="orderDate"/>
                                <form:errors path="orderDate" cssClass="error" />
                            </td>
                        </tr>
                        <tr>
                            <td><label></label></td>
                            <td><input type="submit" value="Save" class="save" /></td>
                        </tr>

                    </tbody>
                </table>
            </form:form>
            <div style="clear; both;"></div>
            <p>
                <a href="${pageContext.request.contextPath}/order/list">Back to List</a>
            </p>
        </div>
    </body>
</html>
