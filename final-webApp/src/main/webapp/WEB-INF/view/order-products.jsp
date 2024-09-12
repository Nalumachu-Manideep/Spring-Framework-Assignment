<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Products</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/add-customer.css" />
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <h2>Products for Order</h2>
        </div>
        <div id="container">
            <div id="content">
                <h3>Order ID: ${order.id}</h3>
                <h4>Order Date: ${order.orderDate}</h4>

                <table>
                    <thead>
                        <tr>
                            <th>Product Id</th>
                            <th>Product Name</th>
                            <th>Price</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="product" items="${order.products}">
                            <tr>
                                <td>${product.id}</td>
                                <td>${product.name}</td>
                                <td>${product.price}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>

                <a href="${pageContext.request.contextPath}/order/manage-products?orderId=${order.id}">Manage Products</a>
                <a href="${pageContext.request.contextPath}/order/list">Back to List</a>
            </div>
        </div>
    </div>
</body>
</html>
