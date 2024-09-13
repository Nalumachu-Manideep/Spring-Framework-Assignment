<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
<html lang="en">
    <head>
        <title>
            Save Product
        </title>

        <link type="text/css"
                      rel="stylesheet"
                      href="${pageContext.request.contextPath}/resources/css/add-customer.css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css">
    </head>
    <body>
        <div class="navbar">
            <a href="${pageContext.request.contextPath}/product/list">Product List</a>
            <a href="${pageContext.request.contextPath}/order/list">Order List</a>
        </div>
        <div id="wrapper">
            <div id="header">
                <h2>E Commerce Featured Products</h2>
            </div>
        </div>
        <div id="container">
            <h3>Save Customer</h3>
            <form:form action="saveProduct" modelAttribute="product" method="POST">
                <form:hidden path="id" />
                <table>

                        <tr>
                            <td><label>Product name:</label></td>
                            <td>
                                <form:input path="name"/>
                                <form:errors path="name" cssClass="error" />
                            </td>
                        </tr>
                        <tr>
                            <td><label>Product Description:</label></td>
                            <td>
                                <form:input path="description"/>
                                <form:errors path="description" cssClass="error" />
                            </td>
                        </tr>
                        <tr>
                            <td><label>Product Price:</label></td>
                            <td>
                                <form:input path="price"/>
                                <form:errors path="price" cssClass="error" />
                            </td>
                        </tr>

                        <tr>
                            <td><label></label></td>
                            <td><input type="submit" value="Save" class="save" /></td>
                        </tr>


                </table>
            </form:form>
            <div style="clear; both;"></div>
            <p>
                <a href="${pageContext.request.contextPath}/product/list">Back to List</a>
            </p>
        </div>
    </body>
</html>
