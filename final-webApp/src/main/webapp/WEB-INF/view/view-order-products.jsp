<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

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
                <form action="${pageContext.request.contextPath}/order/saveProducts" method="post">
                    <input type="hidden" name="orderId" value="${order.id}" />
                    <table>
                        <thead>
                            <tr>
                                <th>Select</th>
                                <th>Product Id</th>
                                <th>Product Name</th>
                                <th>Price</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="product" items="${allProducts}">
                                <tr>
                                    <td>
                                        <input type="checkbox" name="productIds" value="${product.id}"
                                                            id="product-${product.id}"
                                            <c:forEach var="selectedProduct" items="${order.products}">
                                                <c:if test="${selectedProduct.id == product.id}">
                                                    checked
                                                </c:if>
                                            </c:forEach>
                                        />
                                    </td>
                                    <td>${product.id}</td>
                                    <td>${product.name}</td>
                                    <td>${product.price}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <button type="submit">Update Products</button>
                </form>
                <a href="${pageContext.request.contextPath}/order/list">Back to List</a>
            </div>
        </div>
    </div>
</body>
</html>
