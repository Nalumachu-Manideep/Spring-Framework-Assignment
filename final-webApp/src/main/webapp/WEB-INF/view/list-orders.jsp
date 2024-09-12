<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Orders</title>
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
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
            <div id="content">
                <input type="button" value="Add Order" onClick="window.location.href='${pageContext.request.contextPath}/order/showFormForAdd'; return false;"
                       class="add-button" />
                <!-- Add HTML table here -->
                 <table>
                    <tr>
                        <th>Order Id</th>
                        <th>Order Date</th>
                        <th>Action</th>
                        <th>Products</th>
                    </tr>

                    <!-- Loop over and print our orders -->
                     <c:forEach var="tempOrder" items="${orders}">
                        <c:url var="deleteLink" value="/order/delete">
                            <c:param name="orderId" value="${tempOrder.id}" />
                        </c:url>
                        <c:url var="viewProductsLink" value="/order/products">
                            <c:param name="orderId" value="${tempOrder.id}" />
                        </c:url>
                        <tr>
                            <td>${tempOrder.id}</td>
                            <td><fmt:formatDate value="${tempOrder.orderDate}" pattern="yyyy-MM-dd" /></td>
                            <td>
                                <a href="${deleteLink}"
                                   onClick="if (!confirm('Are you sure you want to delete this order?')) return false;">Delete</a>
                            </td>
                            <td>
                                <a href="${viewProductsLink}">View Products</a>
                            </td>
                        </tr>
                     </c:forEach>
                 </table>
            </div>
        </div>
    </body>
</html>
