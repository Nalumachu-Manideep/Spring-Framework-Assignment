<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Order Products</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/order-products.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/home.css">
</head>
<body>
    <div class="navbar">
        <a href="${pageContext.request.contextPath}/product/list">Product List</a>
        <a href="${pageContext.request.contextPath}/order/list">Order List</a>
    </div>
    <div id="wrapper">
        <div id="header">
            <h2>Products for Order</h2>
        </div>
        <div id="container">
            <div id="content">
                <h3 >Order ID: ${order.id}</h3>
                <h4 id="Date_Heading">Order Date: ${order.orderDate}</h4>

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

                <a href="${pageContext.request.contextPath}/order/manage-products?orderId=${order.id}" class="link-button">Manage Products</a>
                <a href="${pageContext.request.contextPath}/order/list" class="back-button">Back to List</a>
            </div>
        </div>
    </div>
</body>
</html>
